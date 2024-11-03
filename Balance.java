import java.util.*;
public class Balance{

    public double AllAmountWithdraw(int accountNum){
        Withdraw withdraw = new Withdraw(accountNum);
        ArrayList<Transaction> withdrawList = withdraw.getWithdrawList();
        double total = 0;
        for (Transaction transaction : withdrawList) {
            // System.out.println("id: " + transaction.getTransId() + "\namount: " + transaction.getAmount());
            total += transaction.getAmount();
        }
        return total;
    }

    public double AllAmountDeposit(int accountNum){
        Deposit deposit = new Deposit(accountNum);
        ArrayList<Transaction> depositList = deposit.getDepositList();
        double total = 0;
        for (Transaction transaction : depositList) {
            // System.out.println("id: " + transaction.getTransId() + "\namount: " + transaction.getAmount());
            total += transaction.getAmount();
        }
        return total;
    }

    public ArrayList<Account> computeBalance(){
        Login accountsListGetter = new Login();
        ArrayList<Account> accountList = accountsListGetter.getAllAccounts();
        double currentBalance = 0;

        for (Account account : accountList) {
            currentBalance = account.getBalance() + AllAmountDeposit(account.getAccountNum());
            currentBalance -= AllAmountWithdraw(account.getAccountNum());
            account.setBalance(currentBalance);
        }

        return accountList;
    }
}
