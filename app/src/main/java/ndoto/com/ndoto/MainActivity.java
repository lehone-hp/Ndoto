package ndoto.com.ndoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    final String EXTRA_LASTNAME = "lastName";
    final String EXTRA_FIRSTNAME = "firstName";
    final String EXTRA_PHONENUMBER = "phoneNumber";
    final String EXTRA_PASSWORD = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

// ...

        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button loginButton = (Button) findViewById(R.id.signUp);
        loginButton.setOnClickListener(new View.OnClickListener() {
            final EditText lastName=(EditText) findViewById(R.id.lastName);
            final EditText firstName=(EditText) findViewById(R.id.firstName);
            final EditText phoneNumber=(EditText) findViewById(R.id.phoneNumber);
            final EditText password=(EditText) findViewById(R.id.password);

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginDisplayActivity.class);
                intent.putExtra(EXTRA_LASTNAME,lastName.getText());
                intent.putExtra(EXTRA_FIRSTNAME,firstName.getText());
                intent.putExtra(EXTRA_PHONENUMBER,phoneNumber.getText());
                User user=new User(lastName.getText().toString(),firstName.getText().toString(),phoneNumber.getText().toString(),password.getText().toString());
//                String s=
                mDatabase.child("users").child(lastName.getText().toString()).setValue(lastName.getText().toString());
//                intent.putExtra(EXTRA_PASSWORD,password.getText());
                startActivity(intent);
            }
        });

//        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

class User{
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String password;

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