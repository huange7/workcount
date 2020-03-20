package sample.handler;

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
        String emptyPattern = "[\\s{}]*";

        // /*注释开始标记
        String startAnnotationA = "^[\\s]*/\\*.*";

        // /*注释行文中开始标记
        String startAnnotationB = ".*[;})][\\s]*/\\*.*";

        // //注释开始标记
        String startAnnotationC = "^[\\s]*//.*";

        // //注释行文中开始标记
        String startAnnotationD = ".*[;})][\\s]*//.*";

        // */注释结束标记
        String endAnnotation = ".*\\*/[\\s]*$";

        if (isAnnotation && line.matches(endAnnotation)){
            // 匹配到已经是注释模式且已经找到结束位置
            isAnnotation = false;
            annotationLine++;
        } else if (isAnnotation){
            annotationLine++;
        } else if (line.matches(startAnnotationA) || line.matches(startAnnotationB)){
            if (!line.matches(endAnnotation)){
                isAnnotation = true;
            }
            annotationLine++;
        } else if (line.matches(startAnnotationC) || line.matches(startAnnotationD)){
            annotationLine++;
        } else if (line.matches(emptyPattern)){
            emptyLine++;
        } else {
            codeLine++;
        }

        if (line.matches(startAnnotationB) || line.matches(startAnnotationD)){
            codeLine++;
        }

        setCount(getCount() + 1);

        if (getSuccessor() != null){
            getSuccessor().handleRequest(line);
        }

    }

    @Override
    public void printCount() {
        System.out.println("统计出来的空行结果：【" + emptyLine +"】");
        System.out.println("统计出来的代码行结果：【" + codeLine +"】");
        System.out.println("统计出来的注释行结果：【" + annotationLine +"】");
    }

    public Integer getEmptyLine() {
        return emptyLine;
    }

    public Integer getCodeLine() {
        return codeLine;
    }

    public Integer getAnnotationLine() {
        return annotationLine;
    }

    public void setEmptyLine(Integer emptyLine) {
        this.emptyLine = emptyLine;
    }

    public void setCodeLine(Integer codeLine) {
        this.codeLine = codeLine;
    }

    public void setAnnotationLine(Integer annotationLine) {
        this.annotationLine = annotationLine;
    }
}
