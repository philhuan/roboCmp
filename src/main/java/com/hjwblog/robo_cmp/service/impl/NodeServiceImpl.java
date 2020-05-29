package com.hjwblog.robo_cmp.service.impl;

import com.hjwblog.robo_cmp.service.NodeService;
import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class NodeServiceImpl implements NodeService {
    @Autowired
    KubernetesClient client;
    @Override
    public List<Node> list() {
        List<Node> nodes = client.nodes().list().getItems();
        return nodes;
    }
}
