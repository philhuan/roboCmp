package com.hjwblog.robo_cmp.controller;

import com.hjwblog.robo_cmp.bean.JSONResult;
import com.hjwblog.robo_cmp.service.ServiceService;
import io.fabric8.kubernetes.api.model.Service;
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
    public ResponseEntity listService(){
        List<Service> list= serviceService.listService();
        return ResponseEntity.ok(new JSONResult<List>(list));
    }
}
