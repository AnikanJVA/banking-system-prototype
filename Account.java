public class Account{
    private int accountNum, pin;
    private double startingBalance, currentBalance;
    
    public void setAccountNum(int AccountNum){
        this.accountNum = AccountNum;
    }

    public void setPin(int pin){
        this.pin = pin;
    }

    public void setStartingBalance(double startingBalance){
        this.startingBalance = startingBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getAccountNum(){
        return accountNum;
    }

    public int getPin(){
        return pin;
    }

    public double getStartingBalance(){
        return startingBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }
}