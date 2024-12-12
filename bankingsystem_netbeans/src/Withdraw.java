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
                    transaction.setTransactionId(scan.nextInt());
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

    public void amountWithdraw(double amount){ 
        ArrayList<Transaction> withdrawList = getWithdrawList();
        FileWriter fw;
        BufferedWriter bw;
        int transactionId = 0;
        for (int i = 0; i < withdrawList.size(); i++) {
            transactionId++;
        }

        try{
            fw = new FileWriter(withdrawFile, true);
            bw = new BufferedWriter(fw);
            bw.write(transactionId + " " + amount + "\n");
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}