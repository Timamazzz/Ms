package ms.lab1;

public class Message {
    protected String text;
    protected String status;

    Message(String Text){
        this.text = Text;
    };
    public void setText(String Text){
        this.text = Text;
    }
    public void setStatus(String Status){
        this.status = Status;
    }
    public String getText(){
        return this.text;
    }
    public String getStatus(){
        return this.status;
    }
}