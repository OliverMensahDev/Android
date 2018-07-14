package bawo.firstmvp.login;

import android.support.annotation.Nullable;

public class LoginActvitiyPresenter  implements  LoginActivityMVP.Presenter{

    @Nullable
    private  LoginActivityMVP.View view;
    private LoginActivityMVP.Model model;

    public LoginActvitiyPresenter(LoginActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LoginActivityMVP.View view) {
        this.view  = view;
    }

    @Override
    public void loginButtonClicked() {
        if(view != null){
            if(view.getFirstName().trim().equals("") || view.getSecondName().equals("")){
                view.showInputError();
            }else{
                model.createUser(view.getFirstName(), view.getSecondName());
                view.showSavedMessage();
            }
        }

    }

    @Override
    public void getCurrentUser() {
        User user = model.getUser();
        if(user == null){
            if(view != null){
                view.showInputError();
            }
        }else{
            if(view != null){
                view.setFirstName(user.getFirstName());
                view.setLastName(user.getSecondName());
            }
        }
    }
}
