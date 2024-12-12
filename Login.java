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
                    account.setType(scan.next());
                    account.setAccountNum(scan.nextInt());
                    account.setPin(scan.nextInt());
                    account.setBalance(scan.nextDouble());
                    account.setLimit(scan.nextDouble());
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

    public Account retrieveAccount(int accountNum){
        ArrayList<Account> accountList = getAllAccounts();
        for (Account account : accountList) {
            if(account.getAccountNum() == accountNum){
                return account;
            }
        }
        return null;
    }

    public String retrieveAccountType(int accountNum){
        ArrayList<Account> accountList = getAllAccounts();
        for (Account account : accountList) {
            if(account.getAccountNum() == accountNum){
                return account.getType();
            }
        }
        return null;
    }
}