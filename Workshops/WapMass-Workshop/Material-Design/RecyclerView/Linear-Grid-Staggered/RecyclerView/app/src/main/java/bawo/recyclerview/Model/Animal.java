package bawo.recyclerview.Model;

public class Animal {

    private String title;
    private int imageId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "title='" + title + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
