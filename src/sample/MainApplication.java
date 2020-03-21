package sample;

import sample.graphic.Graphic;
import sample.service.CountService;
import sample.service.impl.CountServiceImpl;
import sample.util.ArgsUtil;

import java.io.IOException;

/**
 * @ClassName MainApplication
 * @Description
 * @Author huange7
 * @Date 2020-03-21 15:45
 * @Version 1.0
 */
public class MainApplication {

    public static void main(String[] args) {
        if (!ArgsUtil.verifyArgs(args, false)){
            return;
        }

        // 非图形界面进行操作
        CountService countService = new CountServiceImpl();
        String selectDir = ArgsUtil.getSelectDir(args);
        try {
            countService.doHandler(selectDir, args);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (ArgsUtil.isGraphical) {
            Graphic.show(args);
        }
    }

}
