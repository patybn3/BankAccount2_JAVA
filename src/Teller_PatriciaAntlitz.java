import java.util.*;
//teller outer class
public class Teller_PatriciaAntlitz {

    //calling scanner again, personal preference
    Scanner scnr = new Scanner(System.in);
    //this boolean will allow the program to run certain calls based on the user's entry
    boolean checkTrans;
    String userChoice;

    //this object was placed here so it can be used on the following method
    BankAccount2_PatriciaAntlitz getMethods = new BankAccount2_PatriciaAntlitz();

    //this method will print a menu and ask the user to make a selection
    public void depositOrWithdraw(Scanner scnr){
        /*
        The user has the option of making a deposit or withdraw for either a Checking acct or a Savings acct
        It also allows the user to create a new account from 0
        The user can quit the program from this meny
         */
        System.out.println("\nTo Deposit Money Press 'd': ");
        System.out.println("To Withdraw Money Press 'w': ");
        System.out.println("To Open a New Account Press 'n': ");
        System.out.println("To QUIT Press 'q': ");

        //while loop lets the method to run until a correct entry is made
        while(scnr.hasNextLine()){
            userChoice = scnr.nextLine();

            /* to run the inner if statements the userChoice string must not be empty
               without this if statement, the program was going back to the deposit or withdraw method
               on the super class, ofter this was ran the first time, yielding the incorrect desired outcome
               it was also printing the error message right after the menu
             */
            if(!userChoice.isEmpty()) {
                //if the user picks 'w'
                if(userChoice.equals("w") || userChoice.equals("W")) {
                    //the boolean will be true
                    checkTrans = true;
                    break;
                } //if 'd'
                else if(userChoice.equals("d") || userChoice.equals("D")) {
                    //it will then be false
                    checkTrans = false;
                    break;
                } //if n, it calls the super class' method that runs all the methods in the class
                else if(userChoice.equals("n") || userChoice.equals("N")) {
                    getMethods.runProgram(scnr);
                    //it also calls the following method to finish running all methods
                    generateAccounts();
                    break;
                } //if 'q'
                else if (userChoice.equals("q") || userChoice.equals("Q")) {
                    //quits the program
                   System.exit(0);
                }
                else {
                    System.out.println("Incorrect Entry. Please Try Again!");
                }
            }
        }
    }

    //required method, generateAccts
    public void generateAccounts() {
        //objects created for main/super class, Savings and Checking classes.
        BankAccount2_PatriciaAntlitz getBalances = new BankAccount2_PatriciaAntlitz(getMethods.initialDeposit,
                getMethods.acctBalance);
        Savings_PatriciaAntlitz getSavings = new Savings_PatriciaAntlitz(scnr, getMethods.initialDeposit,
                getMethods.acctBalance);
        Checking_PatriciaAntlitz getChecking = new Checking_PatriciaAntlitz(scnr, getMethods.initialDeposit,
                getMethods.acctBalance);

        //this loop will check the status of boolean checkTrans and the boolean at the main class, that checks if
        //the user selected to create a savings acct or checking
        //will loop so the previous method does not have to be hard typed multiple times
        while(scnr.hasNextLine()) {
            //calls the previous methods
            depositOrWithdraw(scnr);

            //if user selected to create a savings account, and selected to make a withdraw
            if (getMethods.checkAcct && checkTrans) {
                //it will calls the withdraw method under the Savings class
                getSavings.runWithdrawSavings(scnr);
            }
            //if the user selected to create a savings account, and to make a deposit
            else if (getMethods.checkAcct && !checkTrans) {
                //the deposit method will be called
                getSavings.depositMoneyIntoAcct(scnr);
            }
            //if the user selected to create a checking account, and make a withdraw
            else if (!getBalances.checkAcct && checkTrans) {
                //will calls the withdraw method from the Checking class
                getChecking.withdrawMoney(scnr);
            }
            //checking account and deposit
            else if (!getBalances.checkAcct && !checkTrans) {
                //deposit method
                getChecking.runDepositChecking(scnr);
            }
            else {
                break;
            }
        }
    }
    //end of generateAcct
}
//end of Teller class
