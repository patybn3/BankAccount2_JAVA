/* LAB BankAccount - OBJECTIVE PART 1:  Students will gain experience using methods and setting parameter/argument lists
OBJECTIVE PART 2: To demonstrate the concepts of Method Overloading and Inheritance in code

Name: Patricia Antlitz
Date: 11/19/2021
Class: CIS-160 Computer Science I - NECC Fall 2021
Professor: Kristen Sparrow

 * This program generates a bank account
 * User is asked to enter their personal information
 * User is asked to enter a deposit amount to open the account
 * Initial deposit cannot be less than $50.00 for checking accounts
 * Initial deposit cannot be less than $1000 for savings
 * User is given options to deposit more money, and withdraw, create a new acct and quit the program
 * Program will print basic acct information
 * Account number is randomly generated
 * Routing number is a set number for this bank
 */
/**
 * * @author Patricia N. Antlitz
 *  * @version 5.0
 */

//import all necessary functions
import javax.swing.*;
import java.awt.*;
import java.util.*;

/* 1.	Create a class called BankAccount_yourName save it as BankAccount_yourName
a.	It will be a generic simple type of BankAccount (not Checking, not Savings just BankAccount)
*/
public class BankAccount2_PatriciaAntlitz extends JFrame {
//global variables used throughout this program
    //static variables can be used in the main method
    //type double
    static double acctBalance;
    static double initialDeposit;
    double withdrawMoney;
    double depositMoney;
    double totalDeposit;
    //ints
    static int randomNum;
    int routNumber;
    int max;
    int min;
    //strings
    String userName;
    //holds user's entry for the type of acct desired (savings or checking)
    String accountType;
    //this boolean will allow certain calls to run based on the above selection
    static boolean checkAcct;
    //generate a random number within a range that will be assigned as the account number

    //just a label for the program at opening, first thing shown
    public void bankOpening(){
        System.out.println("--------------------");
        System.out.println("ANTLITZ CREDIT UNION");
        System.out.println("--------------------\n");
    }

    /*this method generated a random acct number for each account created
    it also sets a routing number for this "bank" uses global variables for easy access
     */
    public void generateAcctNum(){
        max = 30000000;
        min = 10000000;
        randomNum = (int)(Math.random() * (max - min)) +1;
        //set bank's routing number
        routNumber = 10045872;
    }

    /*this method will get the user's name, it was placed separately so the user is not asked
     * to enter the same information multiple times
     * a Scanner call is not used on this file, it is present on the Teller outer class
     * all scanners will be passed as parameters
     */
    public void getUserName(Scanner scnr){
        System.out.println("Enter Account Holder's Name:");
        userName = scnr.nextLine();
    }

    //this method asks the user the type of account they want to create
    public void setAcct(Scanner scnr) {
        //basic user's instructions
        System.out.println("Press 's' to open a savings account");
        System.out.println("Press 'c' to open a checking account");

        //loop will allow the user to type until the inner conditional statement is met
        while (scnr.hasNextLine()) {
            //read user's entry
            accountType = scnr.nextLine();
            //by selecting 's' as an entry
            if (accountType.equals("s") || accountType.equals("S")) {
                /*the balance will be set to 0, this will allow the balance to reset if the
                 * user decided to create another saving account
                 */
                acctBalance = 0.00;
                //setting the boolean to truth will make sure some other calls run correctlu
                checkAcct = true;
                System.out.println("Initial Deposit Minimum: $1,000.00 -> Interest Rate of 0.01%\n");
                break;

            } // this else is for if the user selects checking instead of savings
            else if (accountType.equals("c") || accountType.equals("C")) {
                acctBalance = 0.00;
                checkAcct = false;
                break;

            } // else, will allow the user to enter multiple times
            else {
                System.out.println("Error, please try again!");
            }
        }
    }

    //this method askes the user for an initial deposit to open the new account
    public void initialDeposit(Scanner scnr){
        System.out.println("Initial Deposit Amount:");

        while(scnr.hasNextLine()){
            initialDeposit = scnr.nextDouble();
            //a saving account cannot be opened with an initial deposit of less than $1000
            if (checkAcct && initialDeposit < 1000.00) {
                System.out.println("Deposit Must be of $1000.00 or More.");
                System.out.println("Deposit Amount:");
            } // a checking account cannot be opened with a initial deposit of less than $50
            else if (!checkAcct && initialDeposit < 50.00) {
                System.out.println("Deposit Must be of $50.00 or More.");
                System.out.println("Deposit Amount:");
            }
            else {
                break;
            }
        }
        //will print the total deposit
        System.out.println("ACCOUNT CREATED");
        System.out.format("INITIAL DEPOSIT: $%.2f", initialDeposit);
        System.out.println();

        //if checkAcct is true = savings account
        if(checkAcct) {
            //this variable holds the percentage calculation to be added to the deposit
            totalDeposit = initialDeposit * 0.0001;
            //gives a printout of the deposit plus the interest
            System.out.format("INTEREST: $%.2f", totalDeposit);
            System.out.println();
            System.out.format("TOTAL: $%.2f", initialDeposit + totalDeposit);
        }
        System.out.println("\n");
    }

    //basic account information, username, account number and routing number
    public void acctInfo(){
        System.out.println("Account information:");
        System.out.println("---------------------------------------------");
        System.out.println("Account Holder: " + userName);
        System.out.println("Account Number: " + randomNum);
        System.out.println("Routing Number: " + routNumber);
        System.out.println("---------------------------------------------");

    }

    //overloading - will add the current balance to the above method
    public void acctInfo(double balance){
        System.out.println("Balance: ");
        System.out.format("$%.2f", balance);
        System.out.println();
        System.out.println("---------------------------------------------");

    }

    /*withdraw method - this method will be extended on outer class Savings_PatriciaAntlitz
     * and on Checking_PatriciaAntlitz class
     */
    public void withdrawMoney(Scanner scnr){
        System.out.println("Amount to Withdraw:");
        //gets user's entry
        withdrawMoney = scnr.nextDouble();

        //this conditional statement runs the first time an account is created
        if (acctBalance == 0.00 && checkAcct) {
            //calculates the interest again
            totalDeposit = initialDeposit * 0.0001;
            //adds to acctBalance with the deposit, and subtracts the amount entered by the user
            acctBalance = (initialDeposit + totalDeposit) - withdrawMoney;

            initialDeposit = 0.00;
        }
        else if(acctBalance == 0.00 && !checkAcct){
            //adds to acctBalance with the deposit, and subtracts the amount entered by the user
            acctBalance = initialDeposit - withdrawMoney;
            initialDeposit = 0.00;
        }
        //if second and on, simply deduct
        else {
            acctBalance = acctBalance - withdrawMoney;
        }
    }

    //deposit method - will be extended on outer classes
    public void depositMoneyIntoAcct(Scanner scnr) {
        System.out.println("Amount to Deposit:");
        //gets the deposit
        depositMoney = scnr.nextDouble();
        //in case it is the first deposit (after the initial deposit)
        if (acctBalance == 0.0) {
            //this if statement will check if the deposit isn't 0, after initial deposit it won't be, so this will run
            if (initialDeposit != 0) {
                acctBalance = depositMoney + initialDeposit;
                //it will then set the deposit to 0 to allow a correct calculation once the account is a 0 balance
                //this will make the else run in case the account is 0
                initialDeposit = 0;
            }
            else {
                //if the account is at 0 after withdraw/deposit, get user deposit and set it as balance
                acctBalance = depositMoney;
            }
        } //the else statement runs when account balance is not = 0
        else {
            acctBalance = acctBalance + depositMoney;
        }
    }

    //overloaded constructors
    public BankAccount2_PatriciaAntlitz(double getDeposit, double getBalance){
        initialDeposit = getDeposit;
        acctBalance = getBalance;
    }

    public BankAccount2_PatriciaAntlitz(){
        System.out.println();
    }

    //this is the method that runs most of the methods in this program
    public void runProgram(Scanner scnr){
        generateAcctNum();
        getUserName(scnr);
        setAcct(scnr);
        initialDeposit(scnr);
        //prints the basic account info
        acctInfo();
        // if its a savings account,
        if(checkAcct){
            //it will print the overloaded method as well with the initial deposit plus the interest
            acctInfo(totalDeposit + initialDeposit);
        } //else - prints the overloaded methos with the deposit only
        else {
            acctInfo(initialDeposit);
        }
    }
    //end of all methods

    //main method
    public static void main(String[] args){
        //create objects for both constructors
        BankAccount2_PatriciaAntlitz getMethods = new BankAccount2_PatriciaAntlitz();
        //this gets the totals for initial deposit and accBalance, passes as parameters
        BankAccount2_PatriciaAntlitz getBalances = new BankAccount2_PatriciaAntlitz(initialDeposit, acctBalance);
        //created a third object Teller to use its scan
        Teller_PatriciaAntlitz runTeller = new Teller_PatriciaAntlitz();

        //uses the first object to print the opening
        getMethods.bankOpening();
        //uses second object to run the method runProgram, using the Teller object's scanner
        getBalances.runProgram(runTeller.scnr);
        //calls the method generateAccounts from Teller
        runTeller.generateAccounts();
    }
    //end of main method
}
//end of BankAccount2 class