import java.util.*;
public class Main{
    public static Scanner input = new Scanner(System.in);
    public static Login login = new Login();
    public static Balance balance = new Balance();
    public static void main(String[] args) {
        int accountNum = login.runLogin();
        int choice = 0;
        double amount = 0;
        boolean flag = true;
        while(flag){
            Withdraw withdraw = new Withdraw(accountNum);
            Deposit deposit = new Deposit(accountNum);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("[1] Withdraw");
            System.out.println("[2] Deposit");
            System.out.println("[3] Show Balance");
            System.out.println("[4] Log Out");
            System.out.println("[5] Exit");
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
                        withdraw.AmountWithdraw(amount);
                        System.out.println("Withdraw successful.");
                        break;

                    case 2:
                        System.out.println("================== DEPOSIT ==================");
                        System.out.printf("Current Balance: %.2f%n", balance.getCurrentBalance(accountNum));
                        System.out.print("Enter amount to deposit: ");
                        amount = Double.parseDouble(input.nextLine());
                        deposit.AmountDeposit(amount);
                        System.out.println("Deposit successful.");
                        break;

                    case 3:
                        System.out.println("================== BALANCE ==================");
                        System.out.printf("Current Balance: %.2f%n", balance.getCurrentBalance(accountNum));
                        // System.out.printf("Total Amount Deposited: %.2f%n", balance.AllAmountDeposit(accountNum));
                        // System.out.printf("Total Amount Withdrawn: %.2f%n", balance.AllAmountWithdraw(accountNum));
                        break;

                    case 4:
                        System.out.println("Logging out.\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        accountNum = login.runLogin();
                        break;
                    
                    case 5:
                        flag = false;
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
}
