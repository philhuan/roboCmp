package com.hjwblog.robo_cmp.service;

import com.hjwblog.robo_cmp.bean.JSONResult;

public interface ProxyService {
    public JSONResult request(String namespace, String service,String param);
}
