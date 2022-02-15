//extends BankAccount2
import java.util.Scanner;
//allows the user's calculations for a checking account
public class Checking_PatriciaAntlitz extends BankAccount2_PatriciaAntlitz{

    private double overDraftFee;
    //starts the scanner
    static Scanner scnr;

    //overloaded constructor
    public Checking_PatriciaAntlitz(Scanner scnr, double getDeposit, double getBalance){
        super(getDeposit, getBalance);
        this.scnr = scnr;
    }

    //setter method, calculated the withdraw penalty
    public void setFee(){
        this.overDraftFee = acctBalance - 35.00;
    }
    //getter
    public double getFee(){
        return overDraftFee;
    }

    //regular deposit
    public void runDepositChecking(Scanner scnr){
        //calls all calculations on the super class' method for the deposit
        super.depositMoneyIntoAcct(scnr);
        //prints the balance
        System.out.print("Your account balance is: ");
        System.out.format("$%.2f ", acctBalance);
        System.out.println();
    }

    @Override
    //override method - takes the super class' method for withdraw and adds to it
    public void withdrawMoney(Scanner scnr){
        //all calcs from withdraw, allows the method to be shorten by not having to type everything again
        super.withdrawMoney(scnr);
        //sets and sets fee cals, I am calling these for the sake of it, since a setter and a getter were required
        //in reality these are not used
        setFee();
        getFee();

        //additional calls
        //if the balance is negative
        if(acctBalance < 0.00){
            //the balance is now equal to the withdraw plus the fee
            acctBalance = overDraftFee;
            //prints the balance
            System.out.print("Your account balance is: ");
            System.out.format("$%.2f ", acctBalance);
            System.out.println("\n");
        } //now if the balance is NOT negative
        else {
            //it prints the balance as is
            System.out.print("You account balance is: ");
            System.out.format("$%.2f ", acctBalance);
            System.out.println();
        }
    } //end of withdraw
}
//end of Checking class
