package com.hjwblog.robo_cmp.controller;

import com.hjwblog.robo_cmp.bean.JSONResult;
import com.hjwblog.robo_cmp.service.PodService;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.Pod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/pods")
public class PodController {
    @Autowired
    PodService podService;

    @GetMapping("/list")
    public ResponseEntity listNamespace (String namespace){
        List<Pod> list=  podService.getPodByNamespace(namespace);
        return ResponseEntity.ok(new JSONResult<List>(list));
    }
}
