import handler.Handler;
import service.CountService;
import service.impl.CountServiceImpl;
import util.GenerateUtil;
import util.ArgsUtil;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        // 校验参数是否正确
        ArgsUtil.verifyArgs(args);

        // 获取处理链
        Handler head = GenerateUtil.generateChain(args);

        // 获取文件名称
        String fileName = ArgsUtil.getFileName(args);

        CountService countService = new CountServiceImpl();

        countService.doHandler(head, fileName);

        while (head != null){
            head.printCount();
            head = head.getSuccessor();
        }
    }
}
