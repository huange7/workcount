package handler;

/**
 * @ClassName CharHandler
 * @Description 字符处理模块
 * @Author huange7
 * @Date 2020-03-17 17:13
 * @Version 1.0
 */
public class CharHandler extends Handler {

    @Override
    public void handleRequest(String line) {
        setCount(getCount() + line.length() + System.lineSeparator().length());
        if (getSuccessor() != null){
            getSuccessor().handleRequest(line);
        }
    }

    @Override
    public void printCount() {
        System.out.println("统计出来的字符结果：【" + (getCount() - 2) + "】");
    }
}
