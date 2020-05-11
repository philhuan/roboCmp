package com.hjwblog.robo_cmp.controller;

import com.hjwblog.robo_cmp.bean.JSONResult;
import com.hjwblog.robo_cmp.service.ProxyService;
import io.fabric8.kubernetes.api.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/proxy")
@Controller
public class ProxyController {

    @Autowired
    ProxyService proxyService;

    @GetMapping("/request")
    public ResponseEntity request(String namespace,String service,String param) {
        JSONResult result = proxyService.request(namespace,service, param);
        return ResponseEntity.ok(result);
    }
}
