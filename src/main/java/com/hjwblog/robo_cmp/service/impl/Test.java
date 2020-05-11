package com.hjwblog.robo_cmp.service.impl;

import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Test {
    @Autowired
    KubernetesClient client;
    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.client);
    }
}
