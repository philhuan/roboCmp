package com.hjwblog.robo_cmp.service.impl;

import com.hjwblog.robo_cmp.model.CmpResult;
import com.hjwblog.robo_cmp.model.CmpResultCriteria;
import com.hjwblog.robo_cmp.model.mapper.CmpResultMapper;
import com.hjwblog.robo_cmp.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private CmpResultMapper cmpResultMapper;

    @Override
    public List<CmpResult> list(String service) {
        CmpResultCriteria cmpResultCriteria =new CmpResultCriteria();
        if (service == null||service.isEmpty()){
            cmpResultCriteria.or().andServiceIsNotNull();
        }else{
            cmpResultCriteria.or().andServiceEqualTo(service);
        }
        List<CmpResult> list =  cmpResultMapper.selectByExampleWithBLOBs(cmpResultCriteria);
        return list;
    }
}
