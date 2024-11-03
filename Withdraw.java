import java.util.*;
import java.io.*;
public class Withdraw{
    private File withdrawFile;

    public Withdraw(int accountNum){
        withdrawFile = new File(accountNum + "_withdraws.txt");
    }

    public ArrayList<Transaction> getWithdrawList(){
        ArrayList<Transaction> withdrawList = new ArrayList<>();
        try{
            Scanner scan = new Scanner(new FileReader(withdrawFile));
            while(scan.hasNextLine()){
                try{
                    Transaction transaction = new Transaction();
                    transaction.setTransId(scan.nextInt());
                    transaction.setAmount(scan.nextDouble());
                    withdrawList.add(transaction);
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
        return withdrawList;
    }

    public void AmountWithdraw(double amount){ 
        ArrayList<Transaction> withdrawList = getWithdrawList();
        FileWriter fw;
        BufferedWriter bw;
        int transId = 0;
        for (int i = 0; i < withdrawList.size(); i++) {
            transId++;
        }

        try{
            fw = new FileWriter(withdrawFile, true);
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
        Login accountGetter = new Login();
        ArrayList<Account> accountList = accountGetter.getAllAccounts();
        double balance = 0;
        
        for (Account account : accountList) {
            if(account.getAccountNum() == accountNum){
                balance = account.getBalance() + balanceManager.AllAmountDeposit(account.getAccountNum());
                balance -= balanceManager.AllAmountWithdraw(account.getAccountNum());
            }
        }
        System.out.printf("Current Balance: %.2f%n", balance);
    }
}