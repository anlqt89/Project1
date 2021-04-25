package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//implements Bank interface
public class BankTransaction implements Bank{
	private Date transactionDate;
	private int confirmNumber;
	private boolean status;
	private int currentTransactionNumber;
	private double chargedAmount;
	private CreditCard credit;
	private String fileTransaction;
	private String fileTransactionCurrentNumber;
	private SimpleDateFormat dateFormat;
	
	//initial variables
	{
		//get directory of transaction file and transactionCurrent number file
		this.fileTransaction = System.getProperty("user.dir") + "/src/application/files/transaction";
		this.fileTransactionCurrentNumber = System.getProperty("user.dir") + "/src/application/files/TransactionCurrentNumber";
		
		//initial value for: 
		this.transactionDate = Calendar.getInstance().getTime();
		this.confirmNumber = -1;
		this.status = false;
		this.chargedAmount = 0;
		this.credit = new CreditCard();
		
		//get newest transaction number, this is a bad way to encypt the transaction number
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.fileTransactionCurrentNumber));
			
			String line = reader.readLine();
			this.currentTransactionNumber = Integer.parseInt(line);
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.currentTransactionNumber = -1;
		}
		
		dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	}
	
	@Override
	public boolean verifyTransaction(int AcountNumber, Date ExpiredDate, String nameHolder) {
		// Check if credit card is valid
		CreditCard cret = new CreditCard( AcountNumber, ExpiredDate, nameHolder);
		if(cret.verifyAccount() == false) return false;
		if(cret.iskExpiredDate() == true) return false;
		
		return true;
	}

	@Override
	public int charge(int AcountNumber, Date ExpiredDate, String nameHolder, double amount) {
		
		CreditCard cret = new CreditCard( AcountNumber, ExpiredDate, nameHolder);
		
		//if the transaction is valid(check creditcard expired day) 
		if(this.verifyTransaction(AcountNumber, ExpiredDate, nameHolder) == true) {
			
			//if the balance is valid, update information to the variables then save it to the file, return the confirmNumber, and charge on creditcard
			if(cret.checkBalance(amount) == true) {
				//test
				//System.out.println("in");
				
				//update information to the variables
				this.transactionDate = Calendar.getInstance().getTime();
				this.confirmNumber = ++this.currentTransactionNumber;
				this.status = true;
				this.chargedAmount = amount;
				this.credit = cret;
				
				//save transaction
				this.saveTransaction();
				
				//charge
				this.credit.charge(amount);
				
				//test
				//System.out.println(this.transactionDate + " " + this.confirmNumber + " " + this.status +" " + this.chargedAmount + " " + this.credit.getBalance()
				//									+ " " + this.credit.getAccountNumber() + " " +this.credit.getNameHolder() + " " + this.credit.getExpiredDate());
				
				return this.currentTransactionNumber;
			}
		}
		// TODO Auto-generated method stub
		return -1;
	}
	
	
	private void saveTransaction() {
		
		//concating the transaction information
		String saveString = "\n" + String.valueOf(this.confirmNumber)+"\t"
							+String.valueOf(this.credit.getAccountNumber())+"\t"
							+String.valueOf(dateFormat.format(this.credit.getExpiredDate()))+"\t"
							+this.credit.getNameHolder()+"\t"
							+String.valueOf(this.chargedAmount)+"\t"
							+String.valueOf(dateFormat.format(this.transactionDate))+"\t"
							+String.valueOf(this.status) ;
		
		//write transaction to the end of the file
		BufferedWriter writer;
		try {
			//write to transaction file
			writer = new BufferedWriter(new FileWriter(fileTransaction, true));
		    writer.append(saveString);
		    writer.close();
		    
		    //write to TransactionCurrentNumber
		    writer = new BufferedWriter(new FileWriter(fileTransactionCurrentNumber));
		    writer.append(String.valueOf(this.currentTransactionNumber));
		    writer.close();
		} catch (IOException ioe) {
		    System.err.format("IOException: %s%n", ioe);
		    
		}

			
			
	}	
		
	
}
