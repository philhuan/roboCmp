package com.hjwblog.robo_cmp.service.impl;

import com.google.gson.Gson;
import com.hjwblog.robo_cmp.bean.HttpResp;
import com.hjwblog.robo_cmp.bean.JSONResult;
import com.hjwblog.robo_cmp.service.ProxyService;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

@Service
public class ProxyServiceImpl implements ProxyService {


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
            for (Pod pod:pods) {
                Boolean b = hashtable.get(pod.getMetadata().getName());
                if ( b== null || !b){
                    String url = "http://"+pod.getStatus().getPodIP() + "?param="+param;
                    try {
                        String ret = get(url);
                        HttpResp resp =  gson.fromJson(ret, HttpResp.class);
                        return new JSONResult<>(resp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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
