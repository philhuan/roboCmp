package com.hjwblog.robo_cmp.controller;

import com.hjwblog.robo_cmp.bean.JSONResult;
import com.hjwblog.robo_cmp.service.ServiceService;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {
    @Autowired
    ServiceService serviceService;

    @GetMapping("/list")
    public ResponseEntity listService(String namespace){
        try {
            List<Service> list= serviceService.listService(namespace);
            return ResponseEntity.ok(new JSONResult<List>(list));
        } catch (Exception e) {
            return ResponseEntity.ok(new JSONResult<>(e.toString()));
        }
    }

    @GetMapping("/set_image")
    public ResponseEntity setImage(String namespace, String service, String image){
        try {
            Deployment deployment = serviceService.setImage(namespace,service,image);
            return ResponseEntity.ok(new JSONResult<>(deployment));
        } catch (Exception e) {
            return ResponseEntity.ok(new JSONResult<>(e.toString()));
        }
    }

    @GetMapping("/scale")
    public ResponseEntity scale(String namespace, String service, int n){
        try {
            Deployment deployment = serviceService.scale(namespace,service,n);
            return ResponseEntity.ok(new JSONResult<>(deployment));
        } catch (Exception e) {
            return ResponseEntity.ok(new JSONResult<>(e.toString()));
        }
    }
}
