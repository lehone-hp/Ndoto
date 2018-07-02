package ndoto.com.ndoto;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    private TextView mTextMessage, phoneNumberView, passwordView;
    private Button first,second;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            first=(Button)findViewById(R.id.first);
            second=(Button)findViewById(R.id.second);
            switch (item.getItemId()) {
                case R.id.navigation_payment:
                    first.setVisibility(View.VISIBLE);
                    second.setVisibility(View.VISIBLE);
//                    mTextMessage.setText(R.string.title_home);

                    return true;
                case R.id.navigation_insights:
                    first.setVisibility(View.INVISIBLE);
                    second.setVisibility(View.INVISIBLE);
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_alert:
                    first.setVisibility(View.INVISIBLE);
                    second.setVisibility(View.INVISIBLE);
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_more:
                    first.setVisibility(View.INVISIBLE);
                    second.setVisibility(View.INVISIBLE);
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String phone = intent.getStringExtra(LoginDisplayActivity.EXTRA_PHONE_NUMBER);
        String pass = intent.getStringExtra(LoginDisplayActivity.EXTRA_PASSWORD);

        phoneNumberView = (TextView) findViewById(R.id.textViewPhoneNumber);
        passwordView = (TextView) findViewById(R.id.textViewPassword);

        phoneNumberView.setText(phone);
        passwordView.setText(pass);
        */

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        first = (Button) findViewById(R.id.first);
        second = (Button) findViewById(R.id.second);
        first.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Pay.class);
                startActivity(intent);

            }
        });
    }
}

