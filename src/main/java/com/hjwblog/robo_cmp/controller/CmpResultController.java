package com.hjwblog.robo_cmp.controller;

import com.hjwblog.robo_cmp.bean.JSONResult;
import com.hjwblog.robo_cmp.model.CmpResult;
import com.hjwblog.robo_cmp.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/result")
public class CmpResultController {
    @Autowired
    private ResultService resultService;

    @GetMapping("/list")
    private ResponseEntity list(String service) {
        try {
            List<CmpResult> list = resultService.list(service);
            return ResponseEntity.ok(new JSONResult<>(list));
        } catch (Exception e) {
            return ResponseEntity.ok(new JSONResult<>(e.toString()));
        }

    }
}