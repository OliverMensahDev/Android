package bawo.adaptiveui.model;

import android.content.Context;

public class Post {
    private String postName;
    private String postImage;
    private String postDescription;

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostImage() {
        return postImage;
    }
    public int getImageResourceId(Context context) {
        return context.getResources().getIdentifier(this.postImage, "drawable", context.getPackageName());
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }
}
