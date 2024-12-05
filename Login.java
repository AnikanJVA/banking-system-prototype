import java.util.*;
import java.io.*;
public class Login{
    private Scanner input = new Scanner(System.in);
    private File accountFile, userAccountFile;
    
    public Login(){
        accountFile = new File("accounts.txt");
        userAccountFile = new File("useraccounts.txt");
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

    public ArrayList<UserAccount> getAllUserAccounts(){
        ArrayList<UserAccount> userAccountList = new ArrayList<>();
        ArrayList<Account> accountList = getAllAccounts();
        try{
            Scanner scan = new Scanner(new FileReader(userAccountFile));
            while(scan.hasNextLine()){
                try{
                    UserAccount userAccount = new UserAccount();
                    userAccount.setAccountID(scan.nextInt());
                    userAccount.setUsername(scan.next());
                    userAccount.setPassword(scan.next());
                    userAccount.setDebitAccountNum(scan.nextInt());
                    userAccount.setCreditAccountNum(scan.nextInt());

                    for (Account account : accountList) {
                        if(userAccount.getDebitAccountNum() == account.getAccountNum()){
                            userAccount.setDebitAccount(account);
                        }
                        if(userAccount.getCreditAccountNum() == account.getAccountNum()){
                            userAccount.setCreditAccount(account);
                        }
                    }

                    userAccountList.add(userAccount);
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
        return userAccountList;
    }

    public UserAccount runLogin(){
        String username,password;
        while (true) { 
            System.out.print("Enter username: ");
            try {
                username = input.nextLine();
                System.out.print("Enter password: ");
                password = input.nextLine();

            UserAccount account = checkAccount(username,password);
            if(account != null){
                return account;
            }
            } catch (Exception e) {
                return runLogin();
            }
        }
    }

    public UserAccount checkAccount(String userName, String passWord){
        ArrayList<UserAccount> userAccountsList = getAllUserAccounts();
        for(UserAccount account: userAccountsList){
            if(account.getUsername().equals(userName) && account.getPassword().equals(passWord)){
                System.out.println("Login successful.");
                return  account;
            }
        }
        System.out.println("Login failed. Please try again.");
        return null;
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
}