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

        assertEquals(0, bankAccount.getBalance());

        //Equivalence Class- balance >1000
        BankAccount bankAccount3 = new BankAccount("a@b.com", 5000);

        assertEquals(5000, bankAccount.getBalance());




    }

    @Test
    void withdrawTest() throws InsufficientFundsException {

        //Equivalence Class-valid withdraw amount
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());


        //Equivalence Class-Invalid withdraw amount
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(0));

        //Equivalence Class-InsufficientFundsException

        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(101));

        //Equivalence Class-valid withdraw amount >1000
        BankAccount bankAccount2 = new BankAccount("a@b.com", 2000);
        bankAccount.withdraw(1500);
        assertEquals(500, bankAccount.getBalance());




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
    }

}