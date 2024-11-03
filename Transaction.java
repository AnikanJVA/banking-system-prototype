public class Transaction{
    private int transId;
    private double amount;

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTransId() {
        return transId;
    }

    public double getAmount() {
        return amount;
    }
}