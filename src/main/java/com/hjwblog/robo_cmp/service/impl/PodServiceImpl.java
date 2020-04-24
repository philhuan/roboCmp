package com.hjwblog.robo_cmp.service.impl;

import com.hjwblog.robo_cmp.service.PodService;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PodServiceImpl implements PodService {
    @Autowired
    KubernetesClient client;
    public List<Pod> getPodByNamespace(String namespace) {
        return client.pods().inNamespace(namespace).list().getItems();
    }
}
