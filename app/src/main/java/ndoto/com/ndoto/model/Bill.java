package ndoto.com.ndoto.model;

import java.util.Date;

public class Bill {
    private int billId;
    private double amount;
    private Date dueDate;
    private boolean payed;

    public Bill() {

    }

    public Bill(int billId, double amount, Date dueDate) {
        this.billId = billId;
        this.amount = amount;
        this.dueDate = dueDate;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}

