import java.util.*;
import java.io.*;
public class Balance{
    private File accountFile;

    public Balance(){
        accountFile = new File("accounts.txt");
    }

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

    public void computeBalance(){
        Login accountsListGetter = new Login();
        ArrayList<Account> accountList = accountsListGetter.getAllAccounts();
        double currentBalance = 0;

        for (Account account : accountList) {
            currentBalance = account.getStartingBalance() + AllAmountDeposit(account.getAccountNum());
            currentBalance -= AllAmountWithdraw(account.getAccountNum());
            account.setCurrentBalance(currentBalance);
        }

        String format = "";
        for (Account account : accountList) {
            format += account.getAccountNum() + " " + account.getPin() + " " + account.getStartingBalance() + " " + account.getCurrentBalance() + "\n";
        }

        try{
            Formatter formatFile = new Formatter(accountFile);
            formatFile.format("%S", format);
            formatFile.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
