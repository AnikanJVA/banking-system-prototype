public class Transaction{
    private int accountNum, transId;
    private double amount;

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public int getAccountNum() {
        return accountNum;
    }

    public int getTransId() {
        return transId;
    }

    public double getAmount() {
        return amount;
    }
}