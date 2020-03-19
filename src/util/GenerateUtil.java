package util;

import com.sun.deploy.util.StringUtils;
import handler.*;
import operation.OperationEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GenerateUtil
 * @Description 构建处理链工具类
 * @Author huange7
 * @Date 2020-03-18 17:30
 * @Version 1.0
 */
public class GenerateUtil {

    private static Map<String, Handler> handlerMap = new HashMap<>(4);

    static{
        handlerMap.put(OperationEnum.C.getOperation(), new CharHandler());
        handlerMap.put(OperationEnum.W.getOperation(), new WordHandler());
        handlerMap.put(OperationEnum.L.getOperation(), new LineHandler());
        handlerMap.put(OperationEnum.A.getOperation(), new SpecialLineHandler());
    }

    public static Handler generateChain(String[] args) {
        Handler head = null, now = null;
        for (String arg :
                args) {
            if (handlerMap.containsKey(arg)){
                Handler handler = handlerMap.get(arg);
                if (head == null){
                    head = handler;
                    now = head;
                }else {
                    now.setSuccessor(handler);
                    now = now.getSuccessor();
                }
            }else if (OperationEnum.S.getOperation().equals(arg)){
                ArgsUtil.isRecursion = true;
            }
        }
        if (now != null) {
            now.setSuccessor(null);
        }
        return head;
    }

}
