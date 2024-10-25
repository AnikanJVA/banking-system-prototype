import java.util.*;
import java.io.*;
public class Login{
    private File accountFile;
    
    public Login(){
        accountFile = new File("accounts.txt");
    }

    public ArrayList<Account> getAllAccounts(){
        ArrayList<Account> accountList = new ArrayList<>();
        try{
            Scanner scan = new Scanner(new FileReader(accountFile));
            while(scan.hasNextLine()){
                try{
                    Account account = new Account();
                    account.setAccountNum(scan.nextInt());
                    account.setPin(scan.nextInt());
                    account.setBalance(scan.nextDouble());
                    accountList.add(account);
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
        return accountList;
    }

    public boolean isPinCorrect(int accountNum, int pin){
        ArrayList<Account> accountList = getAllAccounts();
        for (Account account : accountList) {
            if(account.getAccountNum() == accountNum && account.getPin() == pin){
                System.out.println("Login successfull.");
                return true;
            }
        }
        System.out.println("Login failed. Please try again.");
        return false;
    }
}