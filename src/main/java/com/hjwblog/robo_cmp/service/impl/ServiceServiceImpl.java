package com.hjwblog.robo_cmp.service.impl;

import com.hjwblog.robo_cmp.service.ServiceService;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    KubernetesClient client;
    @Override
    public List<Service> listService() {
        return client.services().list().getItems();
    }
}
