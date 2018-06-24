package wapmass.wapmasslearningcenter.Data;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;


public class DatabaseAsyncHandler extends AsyncQueryHandler{

    public DatabaseAsyncHandler(ContentResolver cr) {
        super(cr);
    }
}
