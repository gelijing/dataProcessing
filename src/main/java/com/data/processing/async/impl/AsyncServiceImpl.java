package com.data.processing.async.impl;

import com.data.processing.async.AsyncService;
import com.data.processing.domain.UserInfo;
import com.data.processing.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

@Service
public class AsyncServiceImpl implements AsyncService {

    private static Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class.getName());

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    @Async
    public void writeTxt(String fileName) {

        logger.info("线程-" + Thread.currentThread().getId() + "在执行写入" + fileName);

        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String[]> arrayList = new ArrayList<>();
        try {
            File file = new File(fileName);
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader bf = new BufferedReader(inputReader);
            // 按行读取字符串
            String str;
            int i = 0;
            while ((str = bf.readLine()) != null) {
                String[] strs = str.split("\t");
                if (strs.length < 15) {
                    continue;
                }
                if(arrayList.contains(strs)) {
                    continue;
                }
                arrayList.add(strs);
//                logger.info(arrayList.get(i++)[6]);
//                2018-10-30 (2) 23:53:12
                String[] urltimes = arrayList.get(i)[2].split(" ");
                String urltime = urltimes[0]+" "+urltimes[2];
                UserInfo userInfo = new UserInfo(arrayList.get(i)[4], arrayList.get(i)[3],
                        Integer.parseInt(arrayList.get(i)[7]), arrayList.get(i)[5],
                        arrayList.get(i)[6], urltime, Integer.parseInt(arrayList.get(i)[8]), arrayList.get(i)[14]);
                UserInfo result = userInfoRepository.save(userInfo);
                i++;

            }

            bf.close();
            inputReader.close();

            logger.info("线程-" + Thread.currentThread().getId()+"线程结束");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
