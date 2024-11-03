import java.util.*;
import java.io.*;
public class Deposit{
    private File depositFile;

    public Deposit(int accountNum){
        depositFile = new File(accountNum + "_deposits.txt");
    }

    public ArrayList<Transaction> getDepositList(){
        ArrayList<Transaction> depositList = new ArrayList<>();
        try{
            Scanner scan = new Scanner(new FileReader(depositFile));
            while(scan.hasNextLine()){
                try{
                    Transaction transaction = new Transaction();
                    transaction.setTransId(scan.nextInt());
                    transaction.setAmount(scan.nextDouble());
                    depositList.add(transaction);
                }
                catch(Exception err){
                    continue;
                }
            }
            scan.close();
        }
        catch(FileNotFoundException e){
            // e.printStackTrace();
        }
        return depositList;
    }

    public void AmountDeposit(double amount){
        ArrayList<Transaction> depositList = getDepositList();
        FileWriter fw;
        BufferedWriter bw;
        int transId = 0;
        for (int i = 0; i < depositList.size(); i++) {
            transId++;
        }

        try{
            fw = new FileWriter(depositFile, true);
            bw = new BufferedWriter(fw);
            bw.write(transId + " " + amount + "\n");
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showBalance(int accountNum){
        Balance balanceManager = new Balance();
        Login accountListGetter = new Login();
        ArrayList<Account> accountList = accountListGetter.getAllAccounts();
        double balance = 0;

        for (Account account : accountList) {
            if (account.getAccountNum() == accountNum) {
                balance = account.getBalance() + balanceManager.AllAmountDeposit(account.getAccountNum());
                balance -= balanceManager.AllAmountWithdraw(account.getAccountNum());
            }
        }
        System.out.printf("Current Balance: %.2f%n", balance);
    }
}
