package com.hjwblog.robo_cmp.service.impl;

import com.hjwblog.robo_cmp.service.PodService;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PodServiceImpl implements PodService {
    @Autowired
    KubernetesClient client;
    public List<Pod> getPodByNamespace(String namespace,String service) {
//        if (Strings.isNotBlank(service)) {
//            String serviceName = service.split("app")[0];
//            serviceName = serviceName.substring(0, serviceName.length()-1);
//            return client.pods().inNamespace(namespace).withLabel("app="+serviceName).list().getItems();
//        }
        return client.pods().inNamespace(namespace).withLabel("run="+service).list().getItems();

    }
}
