package bawo.firstmvp.root;

import javax.inject.Singleton;

import bawo.firstmvp.login.LoginActivity;
import bawo.firstmvp.login.LoginModule;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {
    void inject(LoginActivity  target);
}
