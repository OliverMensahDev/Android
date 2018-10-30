package bawo.newsapp.data;

import java.util.ArrayList;


public interface ArticleListAsyncResponse {
    void processFinish(ArrayList<Article> articles);
}
