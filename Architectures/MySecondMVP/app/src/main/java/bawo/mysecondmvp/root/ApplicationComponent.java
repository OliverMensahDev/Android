package bawo.mysecondmvp.root;

import javax.inject.Singleton;

import bawo.mysecondmvp.topmovies.TopMoviesActivity;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(TopMoviesActivity target);
}
