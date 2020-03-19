package service.impl;

import handler.Handler;
import service.CountService;
import util.ArgsUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName CountServiceImpl
 * @Description 文件数数实现类
 * @Author huange7
 * @Date 2020-03-18 19:52
 * @Version 1.0
 */
public class CountServiceImpl implements CountService {
    @Override
    public void doHandler(Handler head, String fileName) throws IOException {

        // 获取文件
        File file = new File(fileName);

        if (!file.isDirectory()){
            // 处理单个文件
            readFile(head, file);
        }else{
            // 说明是文件夹
            if (!ArgsUtil.isRecursion){
                throw new RuntimeException("传输的不是一个文件！");
            }
        }


    }

    @Override
    public void readFile(Handler head, File file) throws IOException {
        if (!file.exists()){
            throw new RuntimeException("文件不存在！");
        }

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(fileReader);

        String line;

        while ((line = reader.readLine()) != null) {
            head.handleRequest(line);
        }
    }
    
    private void readDirectory(String fileName){

    }
}
