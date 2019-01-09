/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package elliottbregnifinalassignment;

/**
 *
 * Elliott Bregni
 * Hira Herrington
 * ISYS 316-001
 *  
 */
public class Account {
 private int id;
 private double balance;
 private static double annualInterestRate = 0;
 private final java.util.Date dateCreated;
 public Account() {
 dateCreated = new java.util.Date();
 }
 public Account(int newId, double newBalance) {
 id = newId;
 balance = newBalance;
 dateCreated = new java.util.Date();
 }
 public int getId() {
 return id;
 }
 public double getBalance() {
 return balance;
 }
 public static double getAnnualInterestRate() {
 return annualInterestRate;
 }
 public void setId(int newId) {
 id = newId;
 }
 public void setBalance(double newBalance) {
 balance = newBalance;
 }
 public static void setAnnualInterestRate(double
newAnnualInterestRate) {
 annualInterestRate = newAnnualInterestRate;
 }
 public double getMonthlyInterest() {
 return balance * (annualInterestRate / 1200);
 }
 public java.util.Date getDateCreated() {
 return dateCreated;
 }
 public void withdraw(double amount) {
 balance -= amount;
 }
 public void deposit(double amount) {
 balance += amount;
 }
}
