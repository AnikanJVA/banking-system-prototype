import java.util.*;
import java.io.*;
public class Transfer{
    private File transferFile;

    public Transfer(int accountNum){
        transferFile = new File(accountNum + "_transfers.txt");
    }

    public ArrayList<Transaction> getTransferList(){
        ArrayList<Transaction> transferList = new ArrayList<>();
        try{
            Scanner scan = new Scanner(new FileReader(transferFile));
            while(scan.hasNextLine()){
                try{
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(scan.nextInt());
                    transaction.setAmount(scan.nextDouble());
                    transaction.setTransferedTo(scan.nextInt());
                    transferList.add(transaction);
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
        return transferList;
    }

    public void amountTransfer(double amount, int receiverAccountNum, int senderAcountNum){ 
        Login login = new Login();

        if(login.retrieveAccountType(receiverAccountNum).equals("c")){
            Payment payment = new Payment(receiverAccountNum);
            payment.amountPayment(amount, senderAcountNum, true);
        }
        else{
            Deposit deposit = new Deposit(receiverAccountNum);
            deposit.amountDeposit(amount);
        }
        

        ArrayList<Transaction> transferList = getTransferList();
        FileWriter fw;
        BufferedWriter bw;
        int transactionId = 0;
        for (int i = 0; i < transferList.size(); i++) {
            transactionId++;
        }

        try{
            fw = new FileWriter(transferFile, true);
            bw = new BufferedWriter(fw);
            bw.write(transactionId + " " + amount + " " + receiverAccountNum  + "\n");
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}