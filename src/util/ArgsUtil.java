package util;

import operation.OperationEnum;

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

    public static void verifyArgs(String[] args){

        if (args.length <= 0){
            throw new RuntimeException("参数传输错误！请校验参数！");
        }
        for (int i = 0; i < args.length; i++){
            String arg = args[i];
            // 查看是否是已经有的枚举类
            if (arg.startsWith("-")){
                try {
                    OperationEnum.valueOf(arg.substring(1).toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("传输了错误的参数类型：【" + arg + "】");
                }
            }

            if (i == args.length - 1){
                String fileName = args[i];
                if (!Pattern.matches("[\\u4e00-\\u9fa5a-zA-Z*0-9]+.(c|java|txt|go|py|log)", fileName)) {
                    throw new RuntimeException("未支持的文件类型！目前支持c,java,txt,go,py,log");
                }
            }
        }
    }

    public static String getFileName(String[] args){
        return args[args.length - 1];
    }

}
