package bawo.studentnewsdigest.model.fcm;

public class FbCloudMessage {
    private String to;
    private Data  data;

    public FbCloudMessage(String to, Data data) {
        this.to = to;
        this.data = data;
    }

    public FbCloudMessage(){
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FbCloudMessage{" +
                "to='" + to + '\'' +
                ", data=" + data +
                '}';
    }
}
