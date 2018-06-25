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

public class PayAsYouGo extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_payment:
//                    mTextMessage.setText(R.string.title_home);

                    return true;
                case R.id.navigation_insights:
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_alert:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_more:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_as_you_go);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        final Button pay1 = (Button) findViewById(R.id.pay1);
        pay1.setOnClickListener(new View.OnClickListener() {
            final EditText amount=(EditText) findViewById(R.id.amount);
            final TextView amount2=(TextView) findViewById(R.id.amount2);

            @Override
            public void onClick(View v) {
                amount2.setText(amount.getText().toString()+" FCFA");

            }
        });

    }

}
