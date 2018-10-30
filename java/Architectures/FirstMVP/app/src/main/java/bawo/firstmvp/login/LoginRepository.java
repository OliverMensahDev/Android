package bawo.firstmvp.login;

public interface LoginRepository {
    User getUser();
    void saveUser(User user);
}
