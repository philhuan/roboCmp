package com.hjwblog.robo_cmp.bean;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class K8sApi {
    String master = "192.168.182.128:8080/";
    Config config = new ConfigBuilder().withMasterUrl(master).build();


    @Bean
    public KubernetesClient getK8sClient() {
        return new DefaultKubernetesClient(config);
    }
}
