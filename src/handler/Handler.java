package handler;

/**
 * @ClassName Handler
 * @Description 责任链抽象类
 * @Author huange7
 * @Date 2020-03-17 17:05
 * @Version 1.0
 */
public abstract class Handler {

    /**
     * 后继对象
     */
    private Handler successor;

    /**
     * 计算数
     */
    private Integer count = 0;

    /**
     * 处理对应的责任链
     * @param line 处理的行
     */
    public abstract void handleRequest(String line);

    /**
     * 打印统计结果
     */
    public abstract void printCount();

    /**
     * 取值方法
     */
    public Handler getSuccessor() {
        return successor;
    }

    /**
     * 赋值方法，设置后继的责任对象
     */
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    /**
     * 获取数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 赋值方法，设置数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }
    
}
