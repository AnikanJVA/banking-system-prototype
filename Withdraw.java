import java.util.*;
import java.io.*;
public class Withdraw{
    private File withdrawFile;

    public Withdraw(){
        withdrawFile = new File("withdraws.txt");
    }

    public ArrayList<Transaction> getWithdrawList(){
        ArrayList<Transaction> withdrawList = new ArrayList<>();
        try{
            Scanner scan = new Scanner(new FileReader(withdrawFile));
            while(scan.hasNextLine()){
                try{
                    Transaction Transaction = new Transaction();
                    Transaction.setAccountNum(scan.nextInt());
                    Transaction.setTransId(scan.nextInt());
                    Transaction.setAmount(scan.nextDouble());
                    withdrawList.add(Transaction);
                }
                catch(Exception err){
                    continue;
                }
            }
            scan.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return withdrawList;
    }

    public void AmountWithdraw(int accountNum, double amount){ 
        Balance balance = new Balance();
        ArrayList<Transaction> withdrawList = getWithdrawList();
        FileWriter fw;
        BufferedWriter bw;
        int transId = 0;
        for (Transaction transaction : withdrawList) {
            if(transaction.getAccountNum() == accountNum){
                transId++;
            }
        }
        

        try{
            fw = new FileWriter(withdrawFile, true);
            bw = new BufferedWriter(fw);
            bw.write(accountNum + " " + transId + " " + amount + "\n");
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        balance.computeBalance();
    }

    // public double showBalance(int accountNum){
    //     Login accountGetter = new Login();
    //     ArrayList<Account> accounts = accountGetter.getAllAccounts();
    //     double balance = 0;
        
    //     for (Account account : accounts) {
    //         if(account.getAccountNum() == accountNum){
    //             balance = account.getCurrentBalance();
    //         }
    //     }
    //     return balance;
    // }
}