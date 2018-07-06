package ndoto.com.ndoto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import ndoto.com.ndoto.model.Bill;

public class PayBill extends AppCompatActivity {

    private TextView mTextName;
    private TextView mTextDate;
    private TextView mTextAmount;
    private Button payBillButton;
    private EditText billNumber;

    private DatabaseReference mDatabase;

    // the bill to be paid
    private Bill bill;

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
        setContentView(R.layout.activity_pay_bill);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void findBill(View view) {

        billNumber = (EditText) findViewById(R.id.bill_number);
        String billNo = billNumber.getText().toString().trim();

        if (TextUtils.isEmpty(billNo)) {
            Toast.makeText(getApplicationContext(), "Enter Bill Number!", Toast.LENGTH_SHORT).show();
            return;
        }

        // read user from the database
        DatabaseReference ref = mDatabase.child("bills");
        ref.child(billNo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bill = dataSnapshot.getValue(Bill.class);
                Toast.makeText(getApplicationContext(),
                        "Read bill "+bill.getBillId()+" "+bill.getAmount(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        if (bill == null) {
            Toast.makeText(getApplicationContext(),
                    "Bill Number "+billNo+" does not exist", Toast.LENGTH_SHORT).show();
        } else {

            mTextName = (TextView) findViewById(R.id.textView8);
            mTextDate = (TextView) findViewById(R.id.textView9);
            mTextAmount = (TextView) findViewById(R.id.textView10);
            payBillButton = (Button) findViewById(R.id.button_pay_my_bill);

            mTextName.setText("Hi Hope!");
            mTextDate.setText("Your bill due for July 2018 is");
            mTextAmount.setText(bill.getAmount()+"XAF");
            payBillButton.setVisibility(View.VISIBLE);
        }

    }

    public void payBill(View view) {
        if (bill.isPayed()) {
            Toast.makeText(getApplicationContext(), "Bill has already been paid", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Your Account will be debited by "+bill.getAmount()+"XAF");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Confirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        bill.setPayed(true);
                        String billNo = ""+bill.getBillId();
                        mDatabase.child("bills").child(billNo).setValue(bill);
                        Toast.makeText(getApplicationContext(), "Bill paid", Toast.LENGTH_SHORT).show();

                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();



    }

}
