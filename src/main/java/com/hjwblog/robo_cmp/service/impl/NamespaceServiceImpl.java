package com.hjwblog.robo_cmp.service.impl;

import com.hjwblog.robo_cmp.service.NamespaceService;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NamespaceServiceImpl implements NamespaceService {
    @Autowired
    KubernetesClient client;
    @Override
    public List<Namespace> listNamespace() {
        return client.namespaces().list().getItems();
    }
}
