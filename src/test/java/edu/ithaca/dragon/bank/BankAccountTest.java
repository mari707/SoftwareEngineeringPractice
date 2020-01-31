package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class  BankAccountTest {

    @Test
    void getBalanceTest() {
        //Equivalence Class-valid balance
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());

        //Equivalence Class- 0 starting balance
        BankAccount bankAccount2 = new BankAccount("a@b.com", 0);

        assertEquals(0, bankAccount2.getBalance());

        //Equivalence Class- balance >1000
        BankAccount bankAccount3 = new BankAccount("a@b.com", 5000);

        assertEquals(5000, bankAccount3.getBalance());




    }

    @Test
    void withdrawTest() throws InsufficientFundsException {

        //Equivalence Class-valid withdraw amount
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());


        //Equivalence Class-InsufficientFundsException

        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(101));


        //Equivalence Class-IllegalArgumentException more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(1045.078));
        //Equivalence Class-IllegalArgumentException more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(1.02839));
        //Equivalence Class-Invalid withdraw amount 0
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(0));


        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-1));

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-10));





    }

    @Test
    void depositTest(){
        //Equivalence class: valid amount-positive int
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.deposit(100);
        assertEquals(300, bankAccount.getBalance());

        bankAccount.deposit(1);
        assertEquals(301, bankAccount.getBalance());

        bankAccount.deposit(5000);
        assertEquals(5301, bankAccount.getBalance());

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-1));

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-10));

        //Equivalence Class-IllegalArgumentException  more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(1.908));





    }



    @Test
    void transferTest() throws InsufficientFundsException {

        //Equivalence class: valid amount-positive int $1
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        BankAccount bankAccount2 = new BankAccount("a@b.com", 300);
        bankAccount.transfer(bankAccount,bankAccount2,1);
        assertEquals(199, bankAccount.getBalance());
        assertEquals(301, bankAccount2.getBalance());

        //Equivalence class: valid amount-positive int >1
        bankAccount.transfer(bankAccount,bankAccount2,100);
        assertEquals(99, bankAccount.getBalance());
        assertEquals(401, bankAccount2.getBalance());

        //Equivalence class: valid amount -decimals
        bankAccount.transfer(bankAccount,bankAccount2,1.0);
        assertEquals(98, bankAccount.getBalance());
        assertEquals(402, bankAccount2.getBalance());

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(bankAccount,bankAccount2,-1));

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () -> bankAccount2.transfer(bankAccount,bankAccount2,-10));

        //Equivalence Class-Invalid transfer amount 0
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(bankAccount2,bankAccount,0));

        //Equivalence Class-InsufficientFundsException
        assertThrows(InsufficientFundsException.class, () -> bankAccount.transfer(bankAccount,bankAccount2,4000));





    }


    @Test
    void isAmountValidTest(){
        //Equivalence class: valid amount-positive int
        assertTrue(BankAccount.isAmountValid(1));
        //Equivalence class: valid decimal- no decimal
        assertTrue(BankAccount.isAmountValid(10));
        //Equivalence class: valid decimal- starts with a decimal
        assertTrue(BankAccount.isAmountValid(.01));
        //Equivalence class: valid decimal- two ints before decimal
        assertTrue(BankAccount.isAmountValid(01.00));
        //Equivalence class: valid decimal- only one decimal
        assertTrue(BankAccount.isAmountValid(100.0));
        //Equivalence class: valid decimal- two  decimals
        assertTrue(BankAccount.isAmountValid(1003.05));

        //Equivalence class: invalid decimal- three  decimals
        assertFalse(BankAccount.isAmountValid(0.054));
        //Equivalence class: invalid decimal- four  decimals
        assertFalse(BankAccount.isAmountValid(100.9084));
        //Equivalence class: invalid decimal- 10  decimals
        assertFalse(BankAccount.isAmountValid(100.9773543789));
        //Equivalence class: invalid amount - 0
        assertFalse(BankAccount.isAmountValid(0));
        //Equivalence class: invalid amount- negative
        assertFalse(BankAccount.isAmountValid(-30));


    }
    @Test
    void isEmailValidTest(){
        //Equivalence class: valid email
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        //Equivalence class: valid email- dot in the sub domain
        assertTrue(BankAccount.isEmailValid( "a@b.c.com"));
        //Equivalence class: valid email- quotes around email
        assertTrue(BankAccount.isEmailValid( "\"a\"@b.com"));
        //Equivalence class: valid email- quotes around email
        assertTrue(BankAccount.isEmailValid( "\"a\"@b.com"));
        //Equivalence class: valid email- digits in email
        assertTrue(BankAccount.isEmailValid( "1234@b.com"));
        //Equivalence class: valid email- digits in email
        assertTrue(BankAccount.isEmailValid( "1234@b.com"));
        //Equivalence class: valid email- underscore in email
        assertTrue(BankAccount.isEmailValid( "__a@b.com"));
        //Equivalence class: valid email- dash
        assertTrue(BankAccount.isEmailValid( "a-b@b.com"));



        //Equivalence class: invalid email-consecutive periods
        assertFalse( BankAccount.isEmailValid("abc..def@mail.com"));
        //Equivalence class: invalid email-starting with a period
        assertFalse( BankAccount.isEmailValid(".abc@mail.com"));
        //Equivalence class: invalid email-Ony using Special characters
        assertFalse( BankAccount.isEmailValid("&%^@mail.com"));
        //Equivalence class: invalid email-Special characters in sub domain
        assertFalse( BankAccount.isEmailValid("abc.def@mail#archive.com"));
        //Equivalence class: invalid email-missing email domain
        assertFalse( BankAccount.isEmailValid("abc.def@mail"));
        //Equivalence class: invalid email-two @ signs
        assertFalse( BankAccount.isEmailValid("abc.def@@mail"));
        //Equivalence class: invalid email-missing @
        assertFalse( BankAccount.isEmailValid("abc.defmail"));
        //Equivalence class: invalid email-trailing period
        assertFalse( BankAccount.isEmailValid("abc.@defmail.com"));
        //Equivalence class: empty string
        assertFalse( BankAccount.isEmailValid(""));
    }



    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);


        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));

        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", -1));

        //check for exception thrown correctly- 0 starting balance
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 0));

        //check for exception thrown correctly- more than 2 decimal places starting balance
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 123.8903));

        //check for exception thrown correctly- $1 starting balance
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 1));
    }

}