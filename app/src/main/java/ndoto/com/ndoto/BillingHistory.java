package ndoto.com.ndoto;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ndoto.com.ndoto.model.Bill;

public class BillingHistory extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private List<Bill> bills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_history);

        bills = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = mDatabase.child("bills");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Bill currentBill = postSnapshot.getValue(Bill.class);
                    bills.add(currentBill);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        if (bills.size() > 0) {
            TableLayout billingHistory = (TableLayout) findViewById(R.id.billing_history_table);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1
            );

            for (Bill bill : bills) {
                TableRow tr = new TableRow(this);

                TextView date = new TextView(this);
                date.setText(bill.getDueDate().toString());
                date.setPadding(3, 3, 3, 3);
                date.setLayoutParams(params);
                tr.addView(date);

                TextView amount = new TextView(this);
                amount.setText(bill.getAmount()+"XAF");
                amount.setPadding(3, 3, 3, 3);
                amount.setLayoutParams(params);
                tr.addView(amount);

                TextView consumed = new TextView(this);
                consumed.setText("150kwh");
                consumed.setPadding(3, 3, 3, 3);
                consumed.setLayoutParams(params);
                tr.addView(consumed);

                billingHistory.addView(tr);
            }
        } else {

        }
    }
}
