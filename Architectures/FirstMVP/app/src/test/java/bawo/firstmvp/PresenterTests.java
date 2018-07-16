package bawo.firstmvp;

import org.junit.Before;
import org.junit.Test;

import bawo.firstmvp.login.LoginActivityMVP;
import bawo.firstmvp.login.LoginActvitiyPresenter;
import bawo.firstmvp.login.User;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresenterTests {
    LoginActivityMVP.Model  mockLoginModel;
    LoginActivityMVP.View  mockView;
    LoginActivityMVP.Presenter  presenter;
    User user;

    @Before
    public void setup(){
        mockLoginModel = mock(LoginActivityMVP.Model.class);
        user = new User("Fox", "Mulder");
        //when(mockLoginModel.getUser()).thenReturn(user);

        mockView  = mock(LoginActivityMVP.View.class);

        presenter = new LoginActvitiyPresenter(mockLoginModel);
        presenter.setView(mockView);

    }

    /**
     * TESTS
     */


    public void noIteractionWithView(){
        presenter.getCurrentUser();
        verifyZeroInteractions(mockView);
    }

    @Test
    public void shouldCreateErrorMessageIfFieldsAreEmpty(){
        when(mockView.getFirstName()).thenReturn("");
        presenter.loginButtonClicked();

        verify(mockView, times(1)).getFirstName();
        verify(mockView,never()).getSecondName();
        verify(mockView, times(1)).showInputError();

        when(mockView.getFirstName()).thenReturn("Diana");
        when(mockView.getSecondName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockView, times(2)).getFirstName();
        verify(mockView,times(1)).getSecondName();
        verify(mockView, times(2)).showInputError();

    }
    public void shouldShowErrorMessageWhenUserIsNull(){
        when(mockLoginModel.getUser()).thenReturn(null);
        presenter.getCurrentUser();

        //verify model interactions
        verify(mockLoginModel, times(1)).getUser();

        //verify model interactions
        verify(mockView, never()).setFirstName("Fox");
        verify(mockView, never()).setLastName("Mulder");
        verify(mockView, times(1)).showNotAvailableUSer();

    }
    public void loadTheUserFromTheRepositoryWhenValidUserIsPresent(){
        when(mockLoginModel.getUser()).thenReturn(user);
        presenter.getCurrentUser();

        //verify model interactions
        verify(mockLoginModel, times(1)).getUser();

        //verify model interactions
        verify(mockView, times(1)).setFirstName("Fox");
        verify(mockView, times(1)).setLastName("Mulder");
        verify(mockView, never()).showNotAvailableUSer();
    }

}
