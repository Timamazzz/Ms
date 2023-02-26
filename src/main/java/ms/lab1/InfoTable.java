package ms.lab1;

public class InfoTable {
    protected float time;
    protected int countMessage = 0;
    protected int countError = 0;
    protected int countMainSent = 0;
    protected int countBackupSent = 0;
    protected String newMessage = "";
    protected String mainChanelText = "";
    protected String backupChanelText = "";

    InfoTable(float Time){
        this.time = Time;
    };
    public void setTime(float Time){
        this.time = Time;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    public void setCountError(int countError) {
        this.countError = countError;
    }

    public void setCountMainSent(int countMainSent) {
        this.countMainSent = countMainSent;
    }

    public void setCountBackupSent(int countBackupSent) {
        this.countBackupSent = countBackupSent;
    }

    public void setCountMessage(int countMessage) {
        this.countMessage = countMessage;
    }
    public void setMainChanelText(String Text){
        this.mainChanelText = Text;
    }
    public void setBackupChanelText(String Text){
        this.backupChanelText = Text;
    }
    public float getTime(){
        return this.time;
    }

    public int getCountBackupSent() {
        return countBackupSent;
    }

    public int getCountMainSent() {
        return countMainSent;
    }

    public int getCountError() {
        return countError;
    }

    public String getNewMessage() {
        return newMessage;
    }

    public int getCountMessage() {
        return countMessage;
    }

    public String getMainChanelText(){
        return this.mainChanelText;
    }
    public String getBackupChanelText(){
        return this.backupChanelText;
    }
}
