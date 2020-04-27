package com.hjwblog.robo_cmp.service;

import io.fabric8.kubernetes.api.model.Pod;

import java.util.List;

public interface PodService {
    public List<Pod> getPodByNamespace(String namespace,String service);
}
