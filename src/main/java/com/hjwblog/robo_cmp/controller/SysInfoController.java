package com.hjwblog.robo_cmp.controller;

import com.hjwblog.robo_cmp.bean.JSONResult;
import com.hjwblog.robo_cmp.bean.SysInfo;
import com.hjwblog.robo_cmp.service.SysInfoService;
import io.fabric8.kubernetes.api.model.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys")
public class SysInfoController {
    @Autowired
    SysInfoService sysInfoService;

    @GetMapping("/get")
    public ResponseEntity listNamespace (){

        SysInfo sysInfo = sysInfoService.get();
        return ResponseEntity.ok(new JSONResult<>(sysInfo));
    }

}
