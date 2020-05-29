package com.hjwblog.robo_cmp.service;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.apps.Deployment;

import java.util.List;

public interface ServiceService {
    public List<Service> listService(String namespace);
    public Deployment setImage(String namespace, String service, String image);
    public Deployment scale(String namespace, String service, int n);
}
