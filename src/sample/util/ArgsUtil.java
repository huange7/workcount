package sample.util;

import javafx.scene.control.Alert;
import sample.operation.OperationEnum;

import java.io.File;
import java.util.regex.Pattern;

/**
 * @ClassName ArgsUtil
 * @Description 校验工具类
 * @Author huange7
 * @Date 2020-03-17 14:43
 * @Version 1.0
 */
public class ArgsUtil {

    public static boolean isRecursion = false;

    public static boolean isGraphical = false;

    public static boolean verifyArgs(String[] args, boolean isXCall){

        boolean hasArg = false;

        if (args.length <= 0){
            if (isXCall) {
                alertTip("参数传输错误！请校验参数！");
            }else {
                System.out.println("参数传输错误！请校验参数！");
            }
            return false;
        }
        for (int i = 0; i < args.length; i++){
            String arg = args[i];
            // 查看是否是已经有的枚举类
            if (arg.startsWith("-")){
                // 如果是-s则不用校验
                if ("-s".equals(arg)){
                    continue;
                }
                if (isXCall && "-x".equals(arg)){
                    alertTip("当前已经处于图形界面模式！");
                    return false;
                }
                if (!isGraphical && "-x".equals(arg)){
                    isGraphical = true;
                    continue;
                }
                try {
                    OperationEnum.valueOf(arg.substring(1).toUpperCase());
                } catch (IllegalArgumentException e) {
                    if (isXCall) {
                        alertTip("传输了错误的参数类型：【" + arg + "】");
                    }else {
                        System.out.println("传输了错误的参数类型：【" + arg + "】");
                    }
                    return false;
                }
                hasArg = true;
            }

            if (i == args.length - 1){
                String fileName = args[i];
                if (!Pattern.matches(".*\\.(c|java|txt|go|py|log)", fileName)) {
                    if (isXCall) {
                        alertTip("未支持的文件类型！目前支持c,java,txt,go,py,log");
                    }else {
                        System.out.println("未支持的文件类型！目前支持c,java,txt,go,py,log");
                    }
                    return false;
                }
            }
        }

        if (!hasArg){
            if (isXCall) {
                alertTip("未输入执行的参数！有效的参数为：【-a】,【-c】,【-w】,【-l】");
            }else {
                System.out.println("未输入执行的参数！有效的参数为：【-a】,【-c】,【-w】,【-l】");
            }
            return false;
        }

        return true;
    }

    public static String getFileName(String[] args){
        String dir = args[args.length - 1];
        if (dir.contains("\\")){
            dir =  dir.substring(dir.lastIndexOf("\\") + 1);
        }
        return dir;
    }

    public static void alertTip(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.titleProperty().set("提醒");
        alert.headerTextProperty().set(message);
        alert.showAndWait();
    }

    public static String getSelectDir(String[] args){
        // 获取最后一个文件名称
        String fileName = args[args.length - 1];

        // 截取前面的文件夹
        String dir = System.getProperty("user.dir");
        if (fileName.contains("\\")){
            dir = fileName.substring(0, fileName.lastIndexOf("\\"));
        }
        return dir;






    }

}
