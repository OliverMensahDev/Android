package bawo.photoshare;
import com.parse.Parse;
import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("CLwCat1EL6RVG167t0VGoDWJJwjXVRaAk2clGONd")
                .clientKey("DJqY9XLXCtISQbt1wMMI6vIpFUyiAJrlqC5hBNyj")
                .server("http://localhost:1337/parse/")
                .build()
        );
    }
}