package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @ClassName FileResult
 * @Description 处理文件之后的结果
 * @Author huange7
 * @Date 2020-03-20 0:31
 * @Version 1.0
 */
public class FileResult {

    /**
     * 文件位置
     */
    private StringProperty location;

    public StringProperty locationProperty(){
        if (location == null){
            location = new SimpleStringProperty(this, "location");
        }
        return location;
    }

    /**
     * 字符数量
     */
    private StringProperty charCount;

    public StringProperty charProperty(){
        if (charCount == null){
            charCount = new SimpleStringProperty(this, "charCount");
        }
        return charCount;
    }

    /**
     * 单词数量
     */
    private StringProperty wordCount;

    public StringProperty wordProperty(){
        if (wordCount == null){
            wordCount = new SimpleStringProperty(this, "wordCount");
        }
        return wordCount;
    }

    /**
     * 行数
     */
    private StringProperty lineCount;

    public StringProperty lineProperty(){
        if (lineCount == null){
            lineCount = new SimpleStringProperty(this, "lineCount");
        }
        return lineCount;
    }

    /**
     * 空行
     */
    private StringProperty emptyCount;

    public StringProperty emptyProperty(){
        if (emptyCount == null){
            emptyCount = new SimpleStringProperty(this, "emptyCount");
        }
        return emptyCount;
    }

    /**
     * 代码行
     */
    private StringProperty codeCount;

    public StringProperty codeProperty(){
        if (codeCount == null){
            codeCount = new SimpleStringProperty(this, "codeCount");
        }
        return codeCount;
    }

    /**
     * 注释行
     */
    private StringProperty annotationCount;

    public StringProperty annotationProperty(){
        if (annotationCount == null){
            annotationCount = new SimpleStringProperty(this, "annotationCount");
        }
        return annotationCount;
    }

    public void setLocation(String location) {
        this.locationProperty().set(location);
    }


    public void setCharCount(Integer charCount) {
        this.charProperty().set(String.valueOf(charCount));
    }


    public void setWordCount(Integer wordCount) {
        this.wordProperty().set(String.valueOf(wordCount));
    }


    public void setLineCount(Integer lineCount) {
        this.lineProperty().set(String.valueOf(lineCount));
    }


    public void setEmptyCount(Integer emptyCount) {
        this.emptyProperty().set(String.valueOf(emptyCount));
    }


    public void setCodeCount(Integer codeCount) {
        this.codeProperty().set(String.valueOf(codeCount));
    }

    public void setAnnotationCount(Integer annotationCount) {
        this.annotationProperty().set(String.valueOf(annotationCount));
    }
}
