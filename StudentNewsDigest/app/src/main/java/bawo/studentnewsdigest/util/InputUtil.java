package bawo.studentnewsdigest.util;

import android.support.design.widget.TextInputLayout;
import android.util.Patterns;

public class InputUtil {
    public static boolean validateInput(String input, TextInputLayout inputEditText, String errorMessage) {
        if(input.isEmpty()){
            inputEditText.setError(errorMessage);
            return false;
        }else
        {
            inputEditText.setErrorEnabled(false);
            return true;
        }
    }

    public static boolean  isValidEmail(String email, TextInputLayout inputEditText, String errorMessage) {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            inputEditText.setError(errorMessage);
            return  false;
        }else{
            inputEditText.setErrorEnabled(false);
            return true;
        }
    }
}
