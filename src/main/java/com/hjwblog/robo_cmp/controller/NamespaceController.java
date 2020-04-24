package com.hjwblog.robo_cmp.controller;

import com.hjwblog.robo_cmp.bean.JSONResult;
import com.hjwblog.robo_cmp.service.NamespaceService;
import io.fabric8.kubernetes.api.model.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/namespaces")
public class NamespaceController {
    @Autowired
    NamespaceService namespaceService;

    @GetMapping("/list")
    public ResponseEntity listNamespace (HttpServletRequest request){

        List<Namespace> list =  namespaceService.listNamespace();
        return ResponseEntity.ok(new JSONResult<List>(list));
    }
}
