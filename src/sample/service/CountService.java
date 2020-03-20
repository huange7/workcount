package sample.service;


import sample.handler.Handler;

import java.io.File;
import java.io.IOException;

/**
 * @InterfaceName CountService
 * @Description 数接口
 * @Author huange7
 * @Date 2020-03-18 19:51
 * @Version 1.0
 */
public interface CountService {

    /**
     * @title : 处理文件方法
     * @param :[args]
     * @return : boolean
     * @author : huange7
     * @date : 2020-03-18 19:52
     */
    void doHandler(String fileDir, String[] args) throws IOException;

    /**
     * @title : 处理单个文件
     * @param :[head, file]
     * @return : void
     * @author : huange7
     * @date : 2020-03-19 1:03
     */
    void readFile(Handler head, File file) throws IOException;
}
