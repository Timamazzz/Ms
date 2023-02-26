package ms.lab1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.animation.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class HelloController {
    public TextField t1Field;
    public TextField t2Field;
    public TextField t3Field;
    public TextField t4Field;
    public TextField t5Field;
    public TextField TField;
    public Button runButton;
    public TableView<InfoTable> tableView;
    public TableColumn<InfoTable, Float> timeColumn;
    public TableColumn<InfoTable, String> mainColumn;
    public TableColumn<InfoTable, String> backupColumn;
    public TableColumn<InfoTable, Integer> countMessageColumn;
    public TableColumn<InfoTable, String> newMessageColumn;
    public TableColumn<InfoTable, Integer> countErrorColumn;
    public TableColumn<InfoTable, Integer> mainSentColumn;
    public TableColumn<InfoTable, Integer> backupSentColumn;
    public ProgressBar mainProgress;
    public ProgressBar backupProgress;
    public ScrollBar speedScrollBar;
    public Label mainChanelStatusLabel;
    public Label backupChanelStatusLabel;
    public Label mainChanelMessageSentLabel;
    public Label backupChanelMessageSentLabel;
    public Label messageListLabel;
    public Label endInfoLabel;
    int countBreakMessage;
    int countBackupActivity;
    int t1;
    int t2;
    int t3;
    int t4;
    int t5;
    int T;
    public int time;
    public double speed;
    float mainChanelSendingTime;
    float backupChanelSendingTime;
    float redirectTime;
    float reloadTime;
    boolean isActiveMainChanel;
    boolean isActiveBackupChanel;
    boolean reload;
    ObservableList<InfoTable> infoList;
    List<Message> mainMessageList;
    List<Message> mainCompleteMessageList;
    List<Message> backupMessageList;
    List<Message> backupCompleteMessageList;
    List<Message> messageList;
    InfoTable info;
    float i;
    int countError;
    Timeline timeline;
    public void working() {
        info = new InfoTable(i);
        info.setCountError(countError);
        info.setCountMainSent(mainCompleteMessageList.size());
        info.setCountBackupSent(backupCompleteMessageList.size());

        if (i % t4 == 0 && i !=0) {
            messageList.add(new Message("Message"));
            messageListLabel.setText("Messages in storage:" + messageList.size());
            info.setNewMessage("Message added");
        }

        if (i % t3 == 0 && i !=0) {
            isActiveMainChanel = false;
            isActiveBackupChanel = true;
            reload = true;
            mainProgress.setProgress(0f);
            mainChanelSendingTime = 1;
            if(!mainMessageList.isEmpty()) {
                messageList.add(0, mainMessageList.get(0));
                mainMessageList.remove(mainMessageList.get(0));
                countBreakMessage++;
            }
            countError++;
            info.setCountError(countError);
        }

        if (reload) reloading();

        if (isActiveBackupChanel) backupChanelWorking();

        if (isActiveMainChanel) mainChanelWorking();

        i = i + 0.5f;
        infoList.add(info);
        tableView.setItems(infoList);
    }

    public void reloading() {
        if (reloadTime <= t2) {
            reloadTime = reloadTime + 0.5f;
            info.setMainChanelText("Reloading");
            mainChanelStatusLabel.setText("Status: reloading");
        }
        else {
            info.setMainChanelText("Reloaded");
            mainChanelStatusLabel.setText("Status: reloaded");
            reloadTime = 0f;
            isActiveMainChanel = true;
            reload = false;
        }
    }

    public void mainChanelWorking() {
        if (!isActiveBackupChanel){
            info.setBackupChanelText("No working");
            backupChanelStatusLabel.setText("Status: no working");
        }
        if (mainMessageList.isEmpty() && messageList.isEmpty()) {
            info.setMainChanelText("No message in chanel");
            mainChanelStatusLabel.setText("Status: no message in chanel");
        }
        else {
            if(mainMessageList.isEmpty()){
                mainMessageList.add(messageList.get(0));
                messageList.remove(messageList.get(0));
                messageListLabel.setText("Messages in storage:" + messageList.size());
                info.setCountMessage(messageList.size());
            }
            if (mainChanelSendingTime < t1) {
                mainChanelSendingTime = mainChanelSendingTime + 0.5f;
                info.setMainChanelText("Sending");
                mainChanelStatusLabel.setText("Status: sending");
                mainProgress.setProgress(mainChanelSendingTime / (float) t1);
            } else {
                mainChanelSendingTime = 0f;
                info.setMainChanelText("Accept");
                mainChanelStatusLabel.setText("Status: accept");
                mainCompleteMessageList.add(mainMessageList.get(0));
                info.setCountMainSent(mainCompleteMessageList.size());
                mainChanelMessageSentLabel.setText("Messages sent:" + mainCompleteMessageList.size());
                mainMessageList.remove(mainMessageList.get(0));
                mainProgress.setProgress(0f);
            }
        }
    }

    public void backupChanelWorking() {
        if (!backupMessageList.isEmpty() || !isActiveMainChanel) {
            if (redirectTime <= T) {
                redirectTime = redirectTime + 0.5f;
                info.setBackupChanelText("Preparing");
                backupChanelStatusLabel.setText("Status: preparing");
                if(redirectTime ==T) countBackupActivity++;
            }
            else {
                if (backupMessageList.isEmpty() && messageList.isEmpty()) {
                    info.setBackupChanelText("No message in chanel");
                    backupChanelStatusLabel.setText("Status: no message in chanel");
                    messageListLabel.setText("Messages in storage:" + messageList.size());
                    info.setCountMessage(messageList.size());
                }
                else {
                    if(backupMessageList.isEmpty()){
                        backupMessageList.add(messageList.get(0));
                        messageList.remove(messageList.get(0));
                    }
                    if (backupChanelSendingTime <= t5) {
                        backupChanelSendingTime = backupChanelSendingTime + 0.5f;
                        info.setBackupChanelText("Sending");
                        backupChanelStatusLabel.setText("Status: sending");
                        backupProgress.setProgress(backupChanelSendingTime / (float) t5);
                    } else {
                        backupChanelSendingTime = 0f;
                        info.setBackupChanelText("Accept");
                        backupChanelStatusLabel.setText("Status: accept");
                        backupCompleteMessageList.add(backupMessageList.get(0));
                        info.setCountBackupSent(backupCompleteMessageList.size());
                        backupChanelMessageSentLabel.setText("Messages sent:" + backupCompleteMessageList.size());
                        backupMessageList.remove(backupMessageList.get(0));
                        backupProgress.setProgress(0f);
                    }
                }
            }
        } else {
            redirectTime = 0f;
            isActiveBackupChanel = false;
        }
    }

    public void runButtonClick() {
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        countMessageColumn.setCellValueFactory(new PropertyValueFactory<>("countMessage"));
        countErrorColumn.setCellValueFactory(new PropertyValueFactory<>("countError"));
        mainSentColumn.setCellValueFactory(new PropertyValueFactory<>("countMainSent"));
        backupSentColumn.setCellValueFactory(new PropertyValueFactory<>("countBackupSent"));
        newMessageColumn.setCellValueFactory(new PropertyValueFactory<>("newMessage"));
        mainColumn.setCellValueFactory(new PropertyValueFactory<>("mainChanelText"));
        backupColumn.setCellValueFactory(new PropertyValueFactory<>("backupChanelText"));

        t1 = Integer.parseInt(t1Field.getText());
        t2 = Integer.parseInt(t2Field.getText());
        t3 = Integer.parseInt(t3Field.getText());
        t4 = Integer.parseInt(t4Field.getText());
        t5 = Integer.parseInt(t5Field.getText());
        T = Integer.parseInt(TField.getText());
        time = 3600;
        speed = speedScrollBar.getValue();
        runButton.setDisable(true);

        mainMessageList = new ArrayList<>();
        mainCompleteMessageList = new ArrayList<>();
        backupMessageList = new ArrayList<>();
        backupCompleteMessageList = new ArrayList<>();
        messageList = new ArrayList<>();

        mainChanelSendingTime = 0f;
        backupChanelSendingTime = 0f;
        redirectTime = 0f;
        reloadTime = 0f;
        i = 0f;
        countError = 0;
        countBackupActivity = 0;
        countBreakMessage = 0;
        isActiveMainChanel = true;
        isActiveBackupChanel = false;
        reload = false;

        infoList = FXCollections.observableArrayList();

        KeyFrame frame = new KeyFrame(Duration.seconds(1), e->{

            if (speed != speedScrollBar.getValue()) {
                speed = speedScrollBar.getValue();
                runButton.setDisable(false);
                timeline.stop();
                timeline.setRate(speed);
                timeline.play();
            }
            if (i <= time) {
                working();
            }
            else {
                timeline.stop();
                runButton.setDisable(false);
                endInfoLabel.setText("Break message:"+countBreakMessage+"\n"+
                        "Backup activity:"+countBackupActivity);

            }
        });

        timeline = new Timeline(frame);
        timeline.setRate(speed);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }
}