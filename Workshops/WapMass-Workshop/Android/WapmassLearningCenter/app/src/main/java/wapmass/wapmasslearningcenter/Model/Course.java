package wapmass.wapmasslearningcenter.Model;

public class Course {
    private String cImage;
    private String cTitle;
    private String cDesc;
    private String cDateAdded;
    private String cLink;
    private int cId;

    public String getcImage() {
        return cImage;
    }

    public void setcImage(String cImage) {
        this.cImage = cImage;
    }

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public String getcDesc() {
        return cDesc;
    }

    public void setcDesc(String cDesc) {
        this.cDesc = cDesc;
    }

    public String getcDateAdded() {
        return cDateAdded;
    }

    public void setcDateAdded(String cDateAdded) {
        this.cDateAdded = cDateAdded;
    }

    public String getcLink() {
        return cLink;
    }

    public void setcLink(String cLink) {
        this.cLink = cLink;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cImage='" + cImage + '\'' +
                ", cTitle='" + cTitle + '\'' +
                ", cDesc='" + cDesc + '\'' +
                ", cDateAdded='" + cDateAdded + '\'' +
                ", cLink='" + cLink + '\'' +
                ", cId=" + cId +
                '}';
    }
}
