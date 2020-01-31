package edu.ithaca.dragon.bank;

import java.math.BigDecimal;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= 0){
            throw new InsufficientFundsException("Cannot draw a negative amount of money");
        } else if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }
    /**
     *  limits the double to only two decimal places
     *  amount cannot be less than 1
     *  Returns either true or false
     */
    public static boolean isAmountValid(double amount) {


        return BigDecimal.valueOf(amount).scale() <= 2 && amount > 0;

    }

    public static boolean isEmailValid(String email) {
        // equivalence class: email prefix; not border case
        if (email.indexOf('@') == -1) {
            return false;
        // equivalence class: email prefix; not border case
        } else if (email.contains("-@") || email.contains("..") || email.startsWith(".") || email.contains(".@")) {
            return false;
        } else if (email.contains("&")){
            return false;
        // equivalence class: email domain; not border case
        } else if (email.contains("#") || !email.contains(".com")) {
            return false;
        } else {
            return true;
        }
        //no border cases clearly identifiable in a string from the tests provided?
    }
}
