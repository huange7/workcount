package handler;

/**
 * @ClassName SpecialLineHandler
 * @Description 特殊行处理类
 * @Author huange7
 * @Date 2020-03-19 1:00
 * @Version 1.0
 */
public class SpecialLineHandler extends Handler {

    /**
     * 空行
     */
    private Integer emptyLine = 0;

    /**
     * 代码行
     */
    private Integer codeLine = 0;

    /**
     * 注释行
     */
    private Integer annotationLine = 0;

    /**
     * 是否正处于注释
     */
    private boolean isAnnotation = false;

    @Override
    public void handleRequest(String line) {
        // 设置空行正则表达式
        String emptyPattern = "[\\s{}]+";

        // 设置注释表达式
        String annotationOpenPattern = ".*;?.*/[*|/].*";

        String annotationEndPattern = ".*\\*/";

        if (line.matches(annotationOpenPattern)){
            if (line.matches(annotationEndPattern)){
                isAnnotation = true;
            }else if (line.startsWith("//")){

            }
            annotationLine++;
        } else if (line.matches(annotationEndPattern)){
            annotationLine++;
            isAnnotation = false;
        } else if (isAnnotation){
            annotationLine++;
        } else if (line.matches(emptyPattern)){
            emptyLine++;
        } else if ("".equals(line)){
            emptyLine++;
        } else{
            codeLine++;
        }
    }

    @Override
    public void printCount() {
        System.out.println("统计出来的空行结果：【" + emptyLine +"】");
        System.out.println("统计出来的代码行结果：【" + codeLine +"】");
        System.out.println("统计出来的注释行结果：【" + annotationLine +"】");
    }
}
