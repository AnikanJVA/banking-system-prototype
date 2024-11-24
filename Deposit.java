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
                    transaction.setTransactionId(scan.nextInt());
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

    public void amountDeposit(double amount){
        ArrayList<Transaction> depositList = getDepositList();
        FileWriter fw;
        BufferedWriter bw;
        int transactionId = 0;
        for (int i = 0; i < depositList.size(); i++) {
            transactionId++;
        }

        try{
            fw = new FileWriter(depositFile, true);
            bw = new BufferedWriter(fw);
            bw.write(transactionId + " " + amount + "\n");
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}