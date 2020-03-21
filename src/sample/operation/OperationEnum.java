package sample.operation;

public enum OperationEnum {

    /**
     *  C ：返回字符数
     */
    C("-c"),
    /**
     *  W ：返回单词数
     */
    W("-w"),
    /**
     *  L ：返回行数
     */
    L("-l"),
    /**
     *  S ：递归处理目录下符合条件的文件
     */
    S("-s"),
    /**
     *  A ：返回更复杂的数据
     */
    A("-a"),
    /**
     *  X ：启动图形界面
     */
    X("-x");

    private String operation;

    OperationEnum(String operation){
        this.operation = operation;
    }

    public String getOperation(){
        return this.operation;
    }

}
