package bawo.firstmvp.login;

public interface LoginActivityMVP {
    interface  View {
        String getFirstName();
        String getSecondName();

        void setFirstName(String firstName);
        void setLastName(String lastName);

        void showInputError();
        void showSavedMessage();
        void showNotAvailableUSer();

    }

    interface  Presenter {
        void setView(LoginActivityMVP.View view);
        void loginButtonClicked();
        void getCurrentUser();

    }

    interface Model {
        void createUser(String fname, String lastName);

        User getUser();
    }
}
