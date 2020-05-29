package com.hjwblog.robo_cmp.service;

import io.fabric8.kubernetes.api.model.Node;

import java.util.List;

public interface NodeService {
    public List<Node> list();
}
