import java.util.*;
public class Main{
    public static Scanner input = new Scanner(System.in);
    public static Login login = new Login();
    public static void main(String[] args) {
        int accountNum = runLogin();
        Withdraw withdraw = new Withdraw();
        // System.out.print("Enter amount to withdraw: ");
        // double amount = Double.parseDouble(input.nextLine());
        // withdraw.withdrawamount(accountNum, amount);
        Balance balance = new Balance();
        System.out.printf("Total withdraw: %.2f%n", balance.AllAmountWithdraw(accountNum));
        System.out.printf("Balance: %.2f", ( withdraw.showBalance(accountNum)));
    }

    public static int runLogin(){
        int accountNum, pin;
        while(true){
            System.out.print("Enter account number: ");
            accountNum = Integer.parseInt(input.nextLine());
            System.out.print("Enter pin: ");
            pin = Integer.parseInt(input.nextLine());
            if(login.isPinCorrect(accountNum, pin)){
                return accountNum;
            }
        }
    }
}