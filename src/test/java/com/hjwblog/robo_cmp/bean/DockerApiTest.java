package com.hjwblog.robo_cmp.bean;
//
//import com.github.dockerjava.api.DockerClient;
//import com.github.dockerjava.api.model.Info;
//import com.hjwblog.robo_cmp.RoboCmpApplication;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes= RoboCmpApplication.class)
//class DockerApiTest{
//    @Autowired
//    DockerClient dockerClient;
//
//    @Test
//    void getDockerClient() {
//        Info info = dockerClient.infoCmd().exec();
//        System.out.print(info);
//    }
//}