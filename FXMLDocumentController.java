/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elliottbregnifinalassignment;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 *
 * 
 * @author bregn
 *  Elliott Bregni
 *  Hira Herrington
 *  ISYS 316-001 
 * This is a Controller class for an ATM interface built in scene builder
 * Inputs from the FXML document include include btnInput btnWithdraw, 
 * btnBalance, btnDeposit, btnEnter, btnCancel, btnClear
 * Input for account information comes from AccountData.txt 
 * outputs include outputScreen and Accounts created from AccountData and there current balances
 * Int number tracks numeric value entered from FXML buttons
 * Set<Account> is a hash set of created accounts
 * Account variable is an instance of the account class and is used to load accounts into the set<Account> 
 * as well as store current users account
 *String display is for String output that needs formating  
 * 
 */
 
public class FXMLDocumentController implements Initializable {
    
  
    @FXML
    private TextField outputScreen;
    
    private int number = 0;
    private Set<Account> set;
    private boolean WD = false;
    private Account account;
    private String display;

    
    
   /**
    *  
    * @param url
    * @param rb 
    * try catch block for File IO
    * loads accounts
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            account(file());
           
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       outputScreen.setText("Enter account number");
    } 
    /**
     * 
     * @return
     * @throws Exception 
     */
    private Scanner file() throws Exception{
            try{
                DataInputStream input = new DataInputStream( new FileInputStream("AccountDataIn.txt"));
                Scanner s = new Scanner(input);
                return s;
                
            } catch (Exception e){
                System.out.print("error");
            }
         return null;
    }
    /**
     * 
     * @param Scanner s 
     * ini the set and loads accounts found in Account Data file 
     */
    private void account(Scanner s){
       set = new HashSet<>();
        do{
            String i = s.next();
            String[] index = i.split(",");
            account = new Account();
            account.setId(Integer.parseInt(index[1]));
            account.setBalance(Integer.parseInt(index[2]));
            if(index[0].equals("C")){
                set.add(account);
            }
           
        }while(true == s.hasNext());
        account = null;
    }
    /**
     * 
     * @param event 
     * Responsible for all numeric buttons
     */
    @FXML
    private void btnInput(ActionEvent event)  {
      Button x = (Button) event.getSource();
       String text = x.getText();
       handleInput(text);
       
    }
    /**
     * 
     * @param event 
     *  Withdraw button 
     * Checks for Valid account, insufficient funds and 
     */
    @FXML
    private void btnWithdrawl(ActionEvent event) {
        //Check for entered number
        if(account != null && WD == false){
              outputScreen.setText("Please Enter Number first");
            number = 0;
        }
        if (account == null){
            outputScreen.setText("Please enter account number");
            number = 0;
            
        } else if (WD == true) {
            if ( account.getBalance() - number < 0 && WD == true){
                outputScreen.setText("insufficient funds");
                number = 0;
               
            } else{
            account.withdraw(number);
            display = String.format("Withdaw of $%s  was succsesful add to your account", number);
            outputScreen.setText(display);
            number = 0;
            }
            
        } 
        
            
        
        
    }
/**
 * 
 * @param event
 * if account is valid this button will show current balance
 */
    @FXML
    private void btnBalance(ActionEvent event) {
         if (account == null){
            outputScreen.setText("Please enter account number");
            number = 0;
        } else {
            display = String.format("Your account balance is $%S", account.getBalance());
            outputScreen.setText(display);
            number = 0;
            
        }
        
    }
    /**
     * 
     * @param event 
     * Checks if the account is valid  and deposits into account
     */
    @FXML
    private void btnDeposit(ActionEvent event) {
        if (account == null){
            outputScreen.setText("Please enter account number");
            number = 0;
        } else {
            if(WD == true){
            account.deposit(number);
            display = String.format("Deposit of $%s succsesful", number);
            outputScreen.setText(display);
            WD = false;
            number = 0;
            } else {
            outputScreen.setText("Please enter Nnmber first");
            number = 0;
            }
          
        }
           
            
        
    }
/**
 * 
 * @param event 
 * button for entering account number and amounts to be withdrew or deposited 
 */
    @FXML
    private void btnEnter(ActionEvent event) {
       
       
        if (account == null){
            account = getAccount();
  
            outputScreen.setText("Your Account is in our system \n Enter transaction ");
            number = 0;
         
        
            if (account == null){
            outputScreen.setText("You are not a current member");
            number = 0;
            }
        } else {
            WD = true;
           
        }
        
        
             
    }
    /**
     * 
     * @return  Account if id is found inside the hash set returns null if not 
     */
    private Account getAccount(){
        for(Account list1: set){
            System.out.print(list1.getBalance());
            if(number == list1.getId()){
                
                return list1;
            } 
        }
        
        return null;
    }

/**
 * 
 * @param text 
 * responsible for displaying numeric input 
 */

    private void handleInput(String text) {
        
        int i = Integer.parseInt(text);
        
        if(number == 0){
        number = i;
        outputScreen.setText(String.valueOf(number));
        } else {
            number = number* 10 + i;
            outputScreen.setText(String.valueOf(number)); 
        
    }
        
    }
/**
 * 
 * @param event 
 * Sets number to 0
 */
    @FXML
    private void btnCancel(ActionEvent event) {
        number = 0;
        outputScreen.setText(String.valueOf(number)); 
    }
/**
 * 
 * @param event 
 */
  
/**
 * 
 * @param event 
 * Clears Account
 */
    @FXML
    private void btnClear(ActionEvent event) {
        account = null;
        number = 0;
        
        outputScreen.setText("Enter Account Number"); 
    }
  


    
}
