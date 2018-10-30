package bawo.firstmvp.login;

public class LoginModel implements  LoginActivityMVP.Model{
    private LoginRepository repository;

    public LoginModel(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(String fname, String lastName) {
        this.repository.saveUser( new User(fname, lastName));
    }

    @Override
    public User getUser() {
        return this.repository.getUser();
    }
}
