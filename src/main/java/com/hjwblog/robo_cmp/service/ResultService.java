package com.hjwblog.robo_cmp.service;

import com.hjwblog.robo_cmp.model.CmpResult;

import java.util.List;

public interface ResultService {
    List<CmpResult> list(String service);
}
