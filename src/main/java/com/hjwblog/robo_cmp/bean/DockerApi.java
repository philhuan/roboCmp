package com.hjwblog.robo_cmp.bean;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.jaxrs.JerseyDockerCmdExecFactory;
import com.github.dockerjava.netty.NettyDockerCmdExecFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerApi {

    @Bean
    public DockerClient getDockerClient() {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://localhost:2375")
                .build();
        DockerCmdExecFactory dockerCmdExecFactory = new NettyDockerCmdExecFactory()
                .withReadTimeout(1000)
                .withConnectTimeout(1000);
        DockerClient dockerClient = DockerClientBuilder.getInstance(config)
                .withDockerCmdExecFactory(dockerCmdExecFactory)
                .build();
        return dockerClient;
    }

}
