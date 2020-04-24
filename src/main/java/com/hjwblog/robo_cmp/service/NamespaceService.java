package com.hjwblog.robo_cmp.service;

import io.fabric8.kubernetes.api.model.Namespace;

import java.util.List;

public interface NamespaceService {
    public List<Namespace> listNamespace() ;
}
