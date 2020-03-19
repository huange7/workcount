package handler;

/**
 * @ClassName LineHandler
 * @Description 行数处理类
 * @Author huange7
 * @Date 2020-03-18 11:56
 * @Version 1.0
 */
public class LineHandler extends Handler{

    @Override
    public void handleRequest(String line) {

        setCount(getCount() + 1);

        if (getSuccessor() != null){
            getSuccessor().handleRequest(line);
        }
    }

    @Override
    public void printCount() {
        System.out.println("统计出来的行数结果：【" + getCount() +"】");
    }
}
