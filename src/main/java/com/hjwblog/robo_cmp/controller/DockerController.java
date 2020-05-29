package com.hjwblog.robo_cmp.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.Info;
import com.hjwblog.robo_cmp.bean.JSONResult;
import io.fabric8.kubernetes.api.model.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/docker")
public class DockerController {
    @Autowired
    DockerClient dockerClient;

    @GetMapping("/info")
    public ResponseEntity info (){
        Info info = dockerClient.infoCmd().exec();
        return ResponseEntity.ok(new JSONResult<Info>(info));
    }

    @GetMapping("/images")
    public ResponseEntity listImages (){
        List<Image> images = dockerClient.listImagesCmd().withShowAll(true).exec();
        return ResponseEntity.ok(new JSONResult<List<Image> >(images));
    }
}
