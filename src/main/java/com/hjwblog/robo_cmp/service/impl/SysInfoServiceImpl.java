package com.hjwblog.robo_cmp.service.impl;

import com.hjwblog.robo_cmp.bean.SysInfo;
import com.hjwblog.robo_cmp.service.SysInfoService;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

@Service
public class SysInfoServiceImpl implements SysInfoService {

    @Override
    public SysInfo get() {
            SysInfo t = new SysInfo();
            SystemInfo systemInfo = new SystemInfo();
            OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
            CentralProcessor processor = systemInfo.getHardware().getProcessor();
            t.cpuLoad = processor.getSystemCpuLoad() * 100;

            t.processorCount = Runtime.getRuntime().availableProcessors();

            t.physicalMemorySize = osmxb.getTotalPhysicalMemorySize()
                    / 1024.0 / 1024 / 1024;

            t.usedPhysicalMemorySize = (systemInfo.getHardware().getMemory().getTotal()
                    - systemInfo.getHardware().getMemory().getAvailable()) * 1.0 / 1024 / 1024 / 1024;

            return t;
        }
    }

