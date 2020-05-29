package com.hjwblog.robo_cmp.service.impl;

import com.hjwblog.robo_cmp.service.ServiceService;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    KubernetesClient client;
    @Override
    public List<Service> listService(String namespace) {
        return client.services().inNamespace(namespace).list().getItems();
    }

    @Override
    public Deployment setImage(String namespace, String service, String image) {
        Deployment deployment = client.apps().deployments().inNamespace(namespace)
                .withName(service).edit()
                .editSpec().editTemplate().editSpec()
                .editContainer(0).withImage(image).endContainer()
                .endSpec().endTemplate().endSpec().done();
        return deployment;
    }

    @Override
    public Deployment scale(String namespace, String service, int n) {
        return client.apps().deployments().inNamespace(namespace).withName(service).scale(n);
    }
}
