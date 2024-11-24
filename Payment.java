import java.io.*;
import java.util.*;
public class Payment{
    private File paymentFile;

    public Payment(int accountNum){
        paymentFile = new File(accountNum + "_payments.txt");
    }

    public ArrayList<Transaction> getPaymentList(){
        ArrayList<Transaction> paymentList = new ArrayList<>();
        try{
            Scanner scan = new Scanner(new FileReader(paymentFile));
            while(scan.hasNextLine()){
                try{
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(scan.nextInt());
                    transaction.setAmount(scan.nextDouble());
                    paymentList.add(transaction);
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
        return paymentList;
    }

    public void amountPayment(double amount){
        ArrayList<Transaction> paymentList = getPaymentList();
        FileWriter fw;
        BufferedWriter bw;
        int transactionId = 0;
        for (int i = 0; i < paymentList.size(); i++) {
            transactionId++;
        }

        try{
            fw = new FileWriter(paymentFile, true);
            bw = new BufferedWriter(fw);
            bw.write(transactionId + " " + amount + "\n");
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}