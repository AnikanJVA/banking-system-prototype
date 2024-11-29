public class Transaction{
    private int transactionId, transferedTo;
    private double amount;

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTransferedTo(int transferedTo) {
        this.transferedTo = transferedTo;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public int getTransferedTo() {
        return transferedTo;
    }
}