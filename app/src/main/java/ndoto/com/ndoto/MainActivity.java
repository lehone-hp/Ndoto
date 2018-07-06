package ndoto.com.ndoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText firstName, lastName, phoneNumber, password;
    private Button signUp;

    private DatabaseReference mDatabase;

    public static final String EXTRA_PHONENUMBER = "phoneNumber";
    public static final String EXTRA_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        password = (EditText) findViewById(R.id.password1);
        signUp = (Button) findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fn = firstName.getText().toString().trim();
                String ln = lastName.getText().toString().trim();
                String phone = phoneNumber.getText().toString().trim();
                String pass = password.getText().toString().trim();

                User user = new User(fn, ln, phone, pass);

                if (TextUtils.isEmpty(fn)) {
                    Toast.makeText(getApplicationContext(), "Enter First Name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(ln)) {
                    Toast.makeText(getApplicationContext(), "Enter Last Name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Enter Phone Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    mDatabase.child("users").child(phone).setValue(user);
                    Intent intent = new Intent(MainActivity.this, LoginDisplayActivity.class);
                    intent.putExtra(EXTRA_PHONENUMBER, phone);
                    intent.putExtra(EXTRA_PASSWORD, pass);
                    startActivity(intent);
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Cannot connect to server!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

class User{
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String password;

    public User() {}

    public User(String l, String f, String p, String pa){
        lastName=l;
        firstName=f;
        phoneNumber=p;
        password=pa;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

class MockUsers{
    public static User[] users = {
            new User("Lenya", "Hope", "675230094", "12345"),
            new User("Akwa", "Man", "675230094", "12345")
    };
}