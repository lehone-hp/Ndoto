package ndoto.com.ndoto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Sindjoung on 09/05/2018.
 */

public class LoginDisplayActivity extends Activity{


    public static final String EXTRA_PHONE_NUMBER = "ndoto.com.ndoto.phoneNumber";
    public static final String EXTRA_PASSWORD = "ndoto.com.ndoto.password";

    private EditText phoneNumber;
    private EditText password;

    private TextView loginErrorMessageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_activity);

        final Button signupButton = (Button) findViewById(R.id.signUp);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginDisplayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // Authenticate user an log in if successful
    public void attemptLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);

        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        password = (EditText) findViewById(R.id.password);

        String phone = phoneNumber.getText().toString();
        String pass = password.getText().toString();

        User currentUser;
        for (int i=0; i<MockUsers.users.length; i++) {
            currentUser = MockUsers.users[i];
            if (currentUser.getPhoneNumber().equals(phone) && currentUser.getPassword().equals(pass)) {
                startActivity(intent);
            } else {
                loginErrorMessageView = (TextView) findViewById(R.id.loginErrorMessage);
                loginErrorMessageView.setText("Unable to login! Enter valid phone number and password");
            }
        }

    }
}
