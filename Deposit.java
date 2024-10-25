import java.util.*;
import java.io.*;
public class Deposit{
    private File depositFile;

    public Deposit(){
        depositFile = new File("deposits.txt");
    }

    public ArrayList<Transaction> getDepositList(){
        ArrayList<Transaction> depositList = new ArrayList<>();
        try{
            Scanner scan = new Scanner(new FileReader(depositFile));
            while(scan.hasNextLine()){
                try{
                    Transaction Transaction = new Transaction();
                    Transaction.setAccountNum(scan.nextInt());
                    Transaction.setTransId(scan.nextInt());
                    Transaction.setAmount(scan.nextDouble());
                    depositList.add(Transaction);
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
        return depositList;
    }
}