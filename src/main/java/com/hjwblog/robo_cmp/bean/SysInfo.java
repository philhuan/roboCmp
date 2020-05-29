package com.hjwblog.robo_cmp.bean;

import com.sun.management.OperatingSystemMXBean;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;


public class SysInfo {
    public Double cpuLoad;
    public Integer processorCount;
    public Double physicalMemorySize;
    public Double usedPhysicalMemorySize;
}
