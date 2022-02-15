//outer class, extends BankAccount2

//imports the scanner
import java.util.Scanner;
//this class will allow the user to make the transactions on a savings account
public class Savings_PatriciaAntlitz extends BankAccount2_PatriciaAntlitz {

    private double interestRate;
    //scanner, not initialized here (Teller)
    static Scanner scnr;

    //overloaded constructor, gets a scanner, allows the scanner to be used throughout the program once the object
    //is called at Teller
    public Savings_PatriciaAntlitz(Scanner scnr, double getDeposit, double getBalance) {
        //gets the values from the super class
        super(getDeposit, getBalance);
        this.scnr = scnr;
    }

    //required set method, calculates the interest rate
    public void setInterestRate() {
        this.interestRate = (initialDeposit + acctBalance) * 0.0001;
    }

    //getting the value stored in interestRate
    public double getInterestRate() {
        return interestRate;
    } //end of getter

    //savings withdraw
    public void runWithdrawSavings(Scanner scnr) {
        //calls the withdraw method from the super class
        super.withdrawMoney(scnr);
        //as long as the accBalance's total, from the super class is greater than 0
        if (acctBalance > 0.00) {
            //print the balance after the withdraw is deducted
            System.out.print("Your account balance is: ");
            System.out.format("$%.2f ", acctBalance);
            System.out.println();
        } //if acctBalance is negative
        else {
            //will let the user know savings cannot be negative
            System.out.println("Savings Balance Cannot be Negative.");
            //these next calls will tell the user that only the remaining balance was withdraw
            //balance is now 0.00
            System.out.print("Only ");
            System.out.format("$%.2f ", acctBalance + withdrawMoney);
            System.out.print("Removed.");
            System.out.println();
            acctBalance = 0.00;
        }
    }

    @Override
    //overrides the super class' method depositMoneyIntoAcct
    public void depositMoneyIntoAcct(Scanner scnr){
        //gets all the calculations that already exist in the super class' method
        super.depositMoneyIntoAcct(scnr);
        //calls the setter and getter
        setInterestRate();
        getInterestRate();
        //adds the interest to the total balance
        acctBalance += interestRate;
        //prints the results
        System.out.print("Your account balance is: ");
        System.out.format("$%.2f ", acctBalance);
        System.out.println();
    }
    // end of deposit method
}
//end of Savings class


