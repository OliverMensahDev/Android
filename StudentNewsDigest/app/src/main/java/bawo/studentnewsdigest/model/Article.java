package bawo.studentnewsdigest.model;

import java.io.Serializable;

public class Article  implements Serializable{
    private String title;
    private String details;
    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
