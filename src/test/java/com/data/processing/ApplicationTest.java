package com.data.processing;

import com.data.processing.async.AsyncService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private AsyncService asyncService;


    @Test
    public void contextLoads() {
    }

    @Test
    public void threadTest() {
        File files = new File("H:\\日志信息\\2018-10-31");
        String[] names = files.list();
        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String[]> arrayList = new ArrayList<>();

        for (String name : names){
            asyncService.writeTxt("H:\\日志信息\\2018-10-31\\"+name);
            System.out.println(name);
        }
    }


}