package sample.service.impl;


import sample.handler.*;
import sample.model.FileResult;
import sample.service.CountService;
import sample.util.ArgsUtil;
import sample.util.GenerateUtil;

import java.io.*;

import static sample.Controller.fileData;

/**
 * @ClassName CountServiceImpl
 * @Description 文件数数实现类
 * @Author huange7
 * @Date 2020-03-18 19:52
 * @Version 1.0
 */
public class CountServiceImpl implements CountService {

    private String fileName;

    @Override
    public void doHandler(String fileDir, String[] args) throws IOException {

        // 校验参数是否正确
        if (!ArgsUtil.verifyArgs(args)) {
            return;
        }

        // 获取处理链
        Handler head = GenerateUtil.generateChain(args);

        // 获取文件名称
        fileName = ArgsUtil.getFileName(args);

        // 将文件名进行转换
        fileName = fileName.replaceAll("[*?]", ".*");

        // 获取文件夹
        File file = new File(fileDir);

        // 如果输入了错误的文件夹
        if (!file.exists()){
            ArgsUtil.alertTip("文件夹不存在！");
            return;
        }

        // 获取文件夹下的文件
        File[] files = file.listFiles();

        if (files != null) {
            for (File f :
                    files) {
                readFile(head, f);
            }
        }
    }

    @Override
    public void readFile(Handler head, File file) throws IOException {
        if (!file.exists()){
            ArgsUtil.alertTip("文件或者文件夹不存在！");
        }

        // 处理文件类型
        if (!file.isDirectory()){
            if (!file.getName().matches(fileName)){
                return;
            }
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            BufferedReader reader = new BufferedReader(fileReader);

            String line;

            // 一行一行进行处理
            while ((line = reader.readLine()) != null) {
                head.handleRequest(line);
            }

            // 对结果进行处理和展示
            generateResult(head, file);
        }

        // 当文件属于文件夹类型，需要查看是否处于 递归 模式
        if (ArgsUtil.isRecursion && file.isDirectory()){
            File[] files = file.listFiles();
            for (File f :
                    files) {
                readFile(head, f);
            }
        }

    }

    private void generateResult(Handler head, File file){
        FileResult fileResult = new FileResult();
        fileResult.setLocation(file.getAbsolutePath());
        Handler temp = head;
        while (temp != null){
            if (temp instanceof CharHandler){
                fileResult.setCharCount(temp.getCount() - 2);
            }else if (temp instanceof LineHandler){
                fileResult.setLineCount(temp.getCount());
            }else if (temp instanceof WordHandler){
                fileResult.setWordCount(temp.getCount());
            }else if (temp instanceof SpecialLineHandler){
                fileResult.setAnnotationCount(((SpecialLineHandler) temp).getAnnotationLine());
                fileResult.setCodeCount(((SpecialLineHandler) temp).getCodeLine());
                fileResult.setEmptyCount(((SpecialLineHandler) temp).getEmptyLine());

                // 清除数据
                ((SpecialLineHandler) temp).setAnnotationLine(0);
                ((SpecialLineHandler) temp).setEmptyLine(0);
                ((SpecialLineHandler) temp).setCodeLine(0);
            }
            temp.setCount(0);
            temp = temp.getSuccessor();
        }
        fileData.add(fileResult);
    }
}
