package com.data.processing.controller;

import com.data.processing.async.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/test")
    public String updateDB(){
        File files = new File("H:\\日志信息\\2018-10-30");
        String[] names = files.list();

        for (String name : names){
            asyncService.writeTxt("H:\\日志信息\\2018-10-30\\"+name);
            //System.out.println(name);
        }
        return "sucess";
    }

}
