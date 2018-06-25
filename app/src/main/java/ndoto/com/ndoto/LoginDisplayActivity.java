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


    final String EXTRA_PHONENUMBER = "phoneNumber";
    final String EXTRA_PASSWORD = "password";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_activity);
        Intent intent = getIntent();
        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        EditText password = (EditText) findViewById(R.id.password);
//Vers la page d'accueuil
        final Button loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            final EditText phoneNumber=(EditText) findViewById(R.id.phoneNumber);
            final EditText password=(EditText) findViewById(R.id.password);

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginDisplayActivity.this, LoginActivity.class);
                intent.putExtra(EXTRA_PHONENUMBER,phoneNumber.getText());
                intent.putExtra(EXTRA_PASSWORD,password.getText());
                startActivity(intent);
            }
        });
//Vers la page de création de compte
        final Button signupButton = (Button) findViewById(R.id.signUp);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginDisplayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
