package com.hjwblog.robo_cmp.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.Statistics;
import com.github.dockerjava.core.InvocationBuilder;
import com.hjwblog.robo_cmp.bean.JSONResult;
import com.hjwblog.robo_cmp.service.NamespaceService;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.NodeMetrics;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.PodMetrics;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    KubernetesClient client;

    @Autowired
    DockerClient dockerClient;

    @GetMapping("/")
    public ResponseEntity listNamespace (){
        //client.apps().deployments().inNamespace("default").withName("cmp-app").scale(3);
        InvocationBuilder.AsyncResultCallback<Statistics> callback = new InvocationBuilder.AsyncResultCallback<>();
        dockerClient.statsCmd("status").withContainerId("567248d8211652380c0a18791e5869432ace60f3de87fb2088a03ebc50dda827").exec(callback);
        Statistics stats = null;
        try {
            stats = callback.awaitResult();
//            float cpuPercent = stats.getCpuStats().getCpuUsage().getTotalUsage().floatValue()/stats.getCpuStats().getSystemCpuUsage().floatValue();
            float cpuPercent = 0.0f;
            float cpuDelta = stats.getCpuStats().getCpuUsage().getTotalUsage().floatValue();
            float systemDelta = stats.getCpuStats().getSystemCpuUsage().floatValue() ;
            cpuPercent = (cpuDelta/systemDelta)*stats.getCpuStats().getCpuUsage().getPercpuUsage().size();
            callback.close();
            return ResponseEntity.ok(new JSONResult<>(cpuPercent));
        } catch (RuntimeException | IOException e) {
            return ResponseEntity.ok(new JSONResult<>(e));
        }


    }
}
