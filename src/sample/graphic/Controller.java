package sample.graphic;

import javafx.application.Platform;
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
import sample.util.ArgsUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

import static sample.util.ArgsUtil.alertTip;

/**
 *  控制类
 */
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
     * 线程池执行异步任务
     */
    public static ExecutorService executorService = Executors.newFixedThreadPool(1);

    private boolean isHandlering = false;

    /**
     * 存储表格数据
     */
    public static ObservableList<FileResult> fileData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        fileLocation.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        wordNumber.setCellValueFactory(cellData -> cellData.getValue().wordProperty());
        charNumber.setCellValueFactory(cellData -> cellData.getValue().charProperty());
        lineNumber.setCellValueFactory(cellData -> cellData.getValue().lineProperty());
        codeNumber.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
        emptyNumber.setCellValueFactory(cellData -> cellData.getValue().emptyProperty());
        annotationNumber.setCellValueFactory(cellData -> cellData.getValue().annotationProperty());
        table.setItems(fileData);
    }


    @FXML
    public void doHandler(ActionEvent event) {

        if (isHandlering) {
            alertTip("当前已有任务正在执行！");
            return;
        }

        // 判断用户是否已经选择了对应的文件夹
        if ("".equals(selectedDir.getText())) {
            alertTip("请先选择一个文件夹");
            return;
        }

        // 校验参数是否正确
        if (!ArgsUtil.verifyArgs(argField.getText().split(" "), true)) {
            return;
        }

        // 异步执行任务
        executorService.execute(() -> {
            isHandlering = true;
            runTime.setText("计算中..");
            long startTime = System.currentTimeMillis();

            // 展示用户所输入的参数
            argsShow.setText(argField.getText());
            CountService countService = new CountServiceImpl();
            // 对上一次统计的遗留数据进行移除
            fileData.clear();

            // 开始执行计算
            try {
                countService.doHandler(selectedDir.getText(), argField.getText().split(" "));
            } catch (IOException e) {
                e.printStackTrace();
            }
            long time = System.currentTimeMillis() - startTime;
            runTime.setText(time + "ms");
            argField.setText("");
            isHandlering = false;
        });
    }

    @FXML
    public void selectFile(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("E:\\"));
        Stage selectFile = new Stage();
        directoryChooser.setTitle("请选择文件夹");
        File directory = directoryChooser.showDialog(selectFile);
        if (directory == null) {
            return;
        }
        selectedDir.setText(directory.getAbsolutePath());
    }

}
