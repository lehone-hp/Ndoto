package ndoto.com.ndoto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Sindjoung on 09/05/2018.
 */

public class LoginDisplayActivity extends Activity{

    private EditText phoneNumber;
    private EditText password;
    private TextView loginErrorMessageView;

    private DatabaseReference mDatabase;
    // User to login
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_activity);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        try {
            Intent intent = getIntent();
            String phone = intent.getStringExtra(MainActivity.EXTRA_PHONENUMBER);
            String pass = intent.getStringExtra(MainActivity.EXTRA_PASSWORD);

            if (!phone.isEmpty() && !pass.isEmpty()) {
                Toast.makeText(getApplicationContext(),
                        "User with phone " + phone + " created!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {}

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
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        password = (EditText) findViewById(R.id.password);

        String phone = phoneNumber.getText().toString().trim();
        final String pass = password.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getApplicationContext(), "Enter Phone Number!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
            return;
        }

        // read user from the database
        DatabaseReference ref = mDatabase.child("users");
        ref.child(phone).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}

        });

        if (user != null) {
            if (user.getPassword().equals(pass)) {
                Toast.makeText(getApplicationContext(), "Login successful for "+user.getLastName()+" "
                        +user.getFirstName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid Phone Number or Password!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Phone Number or Password!", Toast.LENGTH_SHORT).show();
        }

    }
}
