public class Account{
    private int accountNum, pin;
    private double balance;
    
    public void setAccountNum(int AccountNum){
        this.accountNum = AccountNum;
    }

    public void setPin(int pin){
        this.pin = pin;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public int getAccountNum(){
        return accountNum;
    }

    public int getPin(){
        return pin;
    }

    public double getBalance(){
        return balance;
    }
}