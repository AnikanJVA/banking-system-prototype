import java.util.*;
public class Balance{

    public double allAmountWithdraw(int accountNum){
        Withdraw withdraw = new Withdraw(accountNum);
        ArrayList<Transaction> withdrawList = withdraw.getWithdrawList();
        double total = 0;
        for (Transaction transaction : withdrawList) {
            total += transaction.getAmount();
        }
        return total;
    }

    public double allAmountDeposit(int accountNum){
        Deposit deposit = new Deposit(accountNum);
        ArrayList<Transaction> depositList = deposit.getDepositList();
        double total = 0;
        for (Transaction transaction : depositList) {
            total += transaction.getAmount();
        }
        return total;
    }

    public double allAmountPayment(int accountNum){
        Payment payment = new Payment(accountNum);
        ArrayList<Transaction> paymentList = payment.getPaymentList();
        double total = 0;
        for (Transaction transaction : paymentList) {
            total += transaction.getAmount();
        }
        return total;
    }

    public double allAmountTransfer(int accountNum){
        Transfer transfer = new Transfer(accountNum);
        ArrayList<Transaction> transferList = transfer.getTransferList();
        double total = 0;
        for (Transaction transaction : transferList) {
            total += transaction.getAmount();
        }
        return total;
    }

    public ArrayList<Account> computeBalance(){
        Login accountsListGetter = new Login();
        ArrayList<Account> accountList = accountsListGetter.getAllAccounts();
        double currentBalance = 0;

        for (Account account : accountList) {
            if(account.getType().equals("d")){
                currentBalance = account.getBalance() + allAmountDeposit(account.getAccountNum());
                currentBalance -= allAmountWithdraw(account.getAccountNum()) + allAmountTransfer(account.getAccountNum());
                account.setBalance(currentBalance);
                continue;
            }
            else{
                currentBalance = allAmountWithdraw(account.getAccountNum()) + allAmountTransfer(account.getAccountNum());
                currentBalance -= allAmountPayment(account.getAccountNum());
                account.setBalance(currentBalance);
            }
        }

        return accountList;
    }

    public double getCurrentBalance(int accountNum){
        ArrayList<Account> accountList = computeBalance();
        double currentBalance = 0;
        for (Account account : accountList) {
            if(account.getAccountNum() == accountNum){
                currentBalance = account.getBalance();
            }
        }
        return currentBalance;
    }

}
