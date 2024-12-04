public class UserAccount {
    private int accountID, debitAccountNum, creditAccountNum;
    private String username, password;
    private Account debitAccount, creditAccount;

    public int getAccountID(){
        return accountID;
    }

    public void setAccountID(int accountID){
        this.accountID = accountID;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public int getDebitAccountNum(){
        return debitAccountNum;
    }

    public void setDebitAccountNum(int debitAccountNum){
        this.debitAccountNum = debitAccountNum;
    }

    public int getCreditAccountNum(){ 
        return creditAccountNum; 
    }
    
    public void setCreditAccountNum(int creditAccountNum){
        this.creditAccountNum = creditAccountNum;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
    }

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    public Account getDebitAccount() {
        return debitAccount;
    }
}


