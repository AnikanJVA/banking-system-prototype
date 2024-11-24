import java.util.*;
public class Main{
    public static Scanner input = new Scanner(System.in);
    public static Login login = new Login();
    public static Balance balance = new Balance();
    public static Withdraw withdraw;
    public static Deposit deposit;
    public static Payment payment;
    public static void main(String[] args) {
        Account account;
        boolean flag = true;
        int choice = 0;
        
        while (flag) {
            System.out.println("[1] Login");
            System.out.println("[2] Exit");
            System.out.print("Enter number of choice: ");
            try{
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        account = login.runLogin();
                        if (account.getType() == 0) {
                            runDebit(account);
                        }
                        else {
                            runCredit(account);
                        }
                        break;

                    case 2:
                        System.out.println("Exiting...");
                        flag = false;
                        break;

                    default:
                        throw new ErrorHandler("Error. Enter only numbers 1 and 2.");
                }
            }
            catch(ErrorHandler e){
                System.out.println(e.getMessage());
            }
            catch(Exception e){
                System.out.println("Error: Value must be a number or must not be empty. Thank you!");
                flag = continuePropmt();
            }
        }
        
    }

    public static boolean continuePropmt(){
        char choice;
        System.out.println("Do you want to continue? Y/N");
        try{
            choice = input.nextLine().toUpperCase().charAt(0);
        } catch(Exception e){
            System.out.println("Value must be Y or N only or must not be empty.");
            System.out.println("Program Stopping.");
            return false;
        }

        if(choice == 'Y'){
            return true;
        }
        else{
            return false;
        }
    }

    public static void runCredit(Account account){
        double amount = 0;
        int choice = 0;
        boolean flag = true;
        int accountNum = account.getAccountNum();
        while(flag){
            withdraw = new Withdraw(accountNum);
            payment = new Payment(accountNum);
            System.out.println("================ CREDIT ACCOUNT ===============");
            System.out.println("[1] Withdraw");
            System.out.println("[2] Payment");
            System.out.println("[3] Show Balance");
            System.out.println("[4] Log out");
            System.out.print("Enter number of choice: ");
            try{
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("================== WITHDRAW ==================");
                        System.out.printf("Current Balance to Pay: %.2f%n", balance.getCurrentBalance(accountNum));
                        System.out.print("Enter amount to withdraw: ");
                        amount = Double.parseDouble(input.nextLine());
                        if(amount > account.getLimit() || (amount + balance.getCurrentBalance(accountNum)) > account.getLimit()){
                            System.out.println("Withdraw failed. Credit limit exeeded.");
                            break;
                        }
                        withdraw.amountWithdraw(amount);
                        System.out.println("Withdraw successful.");
                        break;

                    case 2:
                        System.out.println("================== PAYMENT ==================");
                        System.out.printf("Current Balance to Pay: %.2f%n", balance.getCurrentBalance(accountNum)); 
                        System.out.print("Enter amount to pay: ");
                        amount = Double.parseDouble(input.nextLine());
                        if(amount > account.getLimit() ||  amount > balance.getCurrentBalance(accountNum)){
                            System.out.println("Payment failed. Overpament not allowed.");
                            break;
                        }
                        payment.amountPayment(amount);
                        System.out.println("Payment successful."); 
                        break;

                    case 3:
                        System.out.println("================== BALANCE ==================");
                        System.out.printf("Current Balance to Pay: %.2f%n", balance.getCurrentBalance(accountNum));
                        System.out.printf("Account Limit: %.2f%n", account.getLimit());
                        break;
                    
                    case 4:
                        flag = false;
                        System.out.println("Logging out.\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        break;

                    default:
                        throw new ErrorHandler ("Please input number from 1-5 only.");
                }
            }
            catch(ErrorHandler e){
                System.out.println(e.getMessage());
            }
            catch(Exception e){
                System.out.println("Error: Value must be a number or must not be empty. Thank you!");
                flag = continuePropmt();
            }
        }
    }

    public static void runDebit(Account account){
        double amount = 0;
        int choice = 0;
        boolean flag = true;
        int accountNum = account.getAccountNum();
        while(flag){
            withdraw = new Withdraw(accountNum);
            deposit = new Deposit(accountNum);
            System.out.println("================ DEBIT ACCOUNT ===============");
            System.out.println("[1] Withdraw");
            System.out.println("[2] Deposit");
            System.out.println("[3] Show Balance");
            System.out.println("[4] Log out");
            System.out.print("Enter number of choice: ");
            try{
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("================== WITHDRAW ==================");
                        System.out.printf("Current Balance: %.2f%n", balance.getCurrentBalance(accountNum));
                        System.out.print("Enter amount to withdraw: ");
                        amount = Double.parseDouble(input.nextLine());
                        if(amount > balance.getCurrentBalance(accountNum)){
                            System.out.println("Withdraw failed. Insufficient balance.");
                            break;
                        }
                        withdraw.amountWithdraw(amount);
                        System.out.println("Withdraw successful.");
                        break;

                    case 2:
                        System.out.println("================== DEPOSIT ==================");
                        System.out.printf("Current Balance: %.2f%n", balance.getCurrentBalance(accountNum));
                        System.out.print("Enter amount to deposit: ");
                        amount = Double.parseDouble(input.nextLine());
                        deposit.amountDeposit(amount);
                        System.out.println("Deposit successful.");
                        break;

                    case 3:
                        System.out.println("================== BALANCE ==================");
                        System.out.printf("Current Balance: %.2f%n", balance.getCurrentBalance(accountNum));
                        break;
                    
                    case 4:
                        flag = false;
                        System.out.println("Logging out.\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        break;

                    default:
                        throw new ErrorHandler ("Please input number from 1-5 only.");
                }
            }
            catch(ErrorHandler e){
                System.out.println(e.getMessage());
            }
            catch(Exception e){
                System.out.println("Error: Value must be a number or must not be empty. Thank you!");
                flag = continuePropmt();
            }
        }
    }
}
