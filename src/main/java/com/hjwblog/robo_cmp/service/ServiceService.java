package com.hjwblog.robo_cmp.service;

import io.fabric8.kubernetes.api.model.Service;

import java.util.List;

public interface ServiceService {
    public List<Service> listService(String namespace);
}
