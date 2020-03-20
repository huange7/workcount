package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import sample.model.FileResult;
import sample.service.CountService;
import sample.service.impl.CountServiceImpl;

import java.io.File;
import java.io.IOException;

import static sample.util.ArgsUtil.alertTip;


public class Controller {

    @FXML
    private TableColumn<FileResult, String> wordNumber;

    @FXML
    private TextField argField;

    @FXML
    private TextField argsShow;

    @FXML
    private TableColumn<FileResult, String> charNumber;

    @FXML
    private TextField selectedDir;

    @FXML
    private TableColumn<FileResult, String> fileLocation;

    @FXML
    private TableColumn<FileResult, String> lineNumber;

    @FXML
    private TableColumn<FileResult, String> codeNumber;

    @FXML
    private Button selectFile;

    @FXML
    private TextField runTime;

    @FXML
    private TableView<FileResult> table;

    @FXML
    private TableColumn<FileResult, String> emptyNumber;

    @FXML
    private TableColumn<FileResult, String> annotationNumber;

    @FXML
    private Button doHandler;

    /**
     * 存储表格数据
     */
    public static ObservableList<FileResult> fileData = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        fileLocation.setCellValueFactory(cellData->cellData.getValue().locationProperty());
        wordNumber.setCellValueFactory(cellData->cellData.getValue().wordProperty());
        charNumber.setCellValueFactory(cellData->cellData.getValue().charProperty());
        lineNumber.setCellValueFactory(cellData->cellData.getValue().lineProperty());
        codeNumber.setCellValueFactory(cellData->cellData.getValue().codeProperty());
        emptyNumber.setCellValueFactory(cellData->cellData.getValue().emptyProperty());
        annotationNumber.setCellValueFactory(cellData->cellData.getValue().annotationProperty());
        table.setItems(fileData);
    }


    @FXML
    public void doHandler(ActionEvent event) {

        // 判断用户是否已经选择了对应的文件夹
        if ("".equals(selectedDir.getText())){
            alertTip("请先选择一个文件夹");
            return;
        }
        long startTime = System.currentTimeMillis();

        // 展示用户所输入的参数
        argsShow.setText(argField.getText());
        CountService countService = new CountServiceImpl();
        try {
            // 对上一次统计的遗留数据进行移除
            fileData.clear();

            // 开始执行计算
            countService.doHandler(selectedDir.getText(), argField.getText().split(" "));
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - startTime;
        runTime.setText(time + "ms");
        argField.setText("");

    }

    @FXML
    public void selectFile(ActionEvent event) {
        DirectoryChooser directoryChooser=new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("E:\\"));
        Stage selectFile = new Stage();
        directoryChooser.setTitle("请选择文件夹");
        File directory = directoryChooser.showDialog(selectFile);
        if (directory == null){
            return;
        }
        selectedDir.setText(directory.getAbsolutePath());
    }

}
