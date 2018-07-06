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
import android.widget.Toast;

import com.google.android.gms.flags.impl.DataUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import ndoto.com.ndoto.model.Bill;

public class PayAsYouGo extends AppCompatActivity {

    private TextView mTextMessage;
    private AtomicInteger atomicInteger;

    private DatabaseReference mDatabase;

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

        atomicInteger = new AtomicInteger();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        final Button pay1 = (Button) findViewById(R.id.pay1);
        pay1.setOnClickListener(new View.OnClickListener() {
            final EditText amount=(EditText) findViewById(R.id.amount);
            final TextView amount2=(TextView) findViewById(R.id.amount2);

            @Override
            public void onClick(View v) {
                Random random = new Random();
                int id = random.nextInt((20 - 1) + 1) + 1;

                Bill bill = new Bill();
                int amt = Integer.parseInt(amount.getText().toString()) * 1000;

                bill.setAmount(amt);
                bill.setDueDate(new Date());
                bill.setBillId(id);
                bill.setPayed(false);

                amount2.setText(amt+" FCFA");

                try {
                    String amoutToSend = ""+bill.getBillId();
                    mDatabase.child("bills").child(amoutToSend).setValue(bill);
                    Toast.makeText(getApplicationContext(),
                            "Bill number "+bill.getBillId()+" added", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Cannot connect to server!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
