package sample.handler;


import sample.util.StringUtil;

/**
 * @ClassName WordHandler
 * @Description 词数处理器
 * @Author huange7
 * @Date 2020-03-18 12:02
 * @Version 1.0
 */
public class WordHandler extends Handler {

    @Override
    public void handleRequest(String line) {
        // 将c文件中的符号转换为
        String pattern = "[,;{}()#\"':<>.\\s=%+\\-*/0-9]";

        System.out.println(line);

        setCount(getCount() + StringUtil.split(line.replaceAll(pattern, " "), " ").length);

        if (getSuccessor() != null){
            getSuccessor().handleRequest(line);
        }
    }

    @Override
    public void printCount() {
        System.out.println("统计的单词数量为：【" + getCount() +"】");
    }

}
