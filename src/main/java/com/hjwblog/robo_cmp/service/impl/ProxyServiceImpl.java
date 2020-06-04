package com.hjwblog.robo_cmp.service.impl;

import com.google.gson.Gson;
import com.hjwblog.robo_cmp.bean.HttpResp;
import com.hjwblog.robo_cmp.bean.JSONResult;
import com.hjwblog.robo_cmp.model.CmpResult;
import com.hjwblog.robo_cmp.model.mapper.CmpResultMapper;
import com.hjwblog.robo_cmp.service.ProxyService;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

@Service
public class ProxyServiceImpl implements ProxyService {

    @Autowired
    private CmpResultMapper cmpResultMapper;


    private Hashtable<String,Boolean> hashtable = new Hashtable<>();
    private List<String> free = new Vector<>();
    private List<String> busy = new Vector<>();
    @Autowired
    KubernetesClient client;

    public ProxyServiceImpl() {
//        List<Pod> podList = client.pods().list().getItems();
//        for(Pod pod :podList) {
//            free.add(pod.getMetadata().getName());
//        }

    }

    private Boolean lockPod(String podName) {
        return hashtable.putIfAbsent(podName,true);
    }

    @Override
    public JSONResult request(String namespace, String service,String param) {
        if (namespace == null) {
            namespace = "default";
        }
        boolean find = false;
        Gson gson = new Gson();
        while (!find) {
            List<Pod> pods = client.pods().inNamespace(namespace).withLabel("run="+service).list().getItems();
            if (pods.size() == 0) {
                return new JSONResult<>("service without any pod");
            }
            for (Pod pod:pods) {
                String podName = pod.getMetadata().getName();
                Boolean b = hashtable.get(podName);
                if ( b== null || !b){
                    hashtable.put(podName,true);
                    String urlParam = URLEncoder.encode(param);
                    String url = "http://"+pod.getStatus().getPodIP() + "?param="+urlParam;
                    try {
                        String ret = get(url);
                        HttpResp resp =  gson.fromJson(ret, HttpResp.class);

                        CmpResult result = new CmpResult();
                        result.setService(service);
                        result.setContainer(podName);
                        result.setParams(param);
                        result.setResult(resp.getData().getResult()+"");
                        cmpResultMapper.insert(result);
                        return new JSONResult<>(resp);
                    } catch (IOException e) {
                        return new JSONResult<>(e.toString());
                    }finally {
                        hashtable.put(podName,false);
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return new JSONResult<>("time out");
    }

    public static String get(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String line = null;
        StringBuilder result = new StringBuilder();
        while ((line = br.readLine()) != null) {
            result.append(line + "\n");
        }
        connection.disconnect();

        return result.toString();
    }

    public static String post(String urlStr, String data) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        PrintWriter pw = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
        pw.write(data);
        pw.flush();
        pw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String line = null;
        StringBuilder result = new StringBuilder();
        while ((line = br.readLine()) != null) {
            result.append(line + "\n");
        }
        connection.disconnect();

        return result.toString();
    }
}
