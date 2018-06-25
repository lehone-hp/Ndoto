package ndoto.com.ndoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pay extends AppCompatActivity {


    private  Button first,second;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
        setContentView(R.layout.activity_pay);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        first = (Button) findViewById(R.id.first);
        second = (Button) findViewById(R.id.second);
        second.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pay.this, PayAsYouGo.class);
                startActivity(intent);

            }
        });
        first.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pay.this, PayBill.class);
                startActivity(intent);

            }
        });
    }

}
