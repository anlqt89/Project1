package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	{
		this.fileTransaction = System.getProperty("user.dir") + "/src/application/files/transaction";
		this.fileTransactionCurrentNumber = System.getProperty("user.dir") + "/src/application/files/TransactionCurrentNumber";
		
		this.transactionDate = Calendar.getInstance().getTime();
		this.confirmNumber = -1;
		this.status = false;
		this.chargedAmount = 0;
		this.credit = new CreditCard();
		try {
			//BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/application/files/TransactionCurrentNumber"));
			BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "../src/application/files/TransactionCurrentNumber"));
			
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
		// TODO Auto-generated method stub
		CreditCard cret = new CreditCard( AcountNumber, ExpiredDate, nameHolder);
		if(cret.verifyAccount() == false) return false;
		if(cret.iskExpiredDate() == true) return false;
		
		return true;
	}

	@Override
	public int charge(int AcountNumber, Date ExpiredDate, String nameHolder, double amount) {
		CreditCard cret = new CreditCard( AcountNumber, ExpiredDate, nameHolder);
		if(this.verifyTransaction(AcountNumber, ExpiredDate, nameHolder) == true) {
			if(cret.checkBalance(amount) == true) {
				//test
				//System.out.println("in");
				
				this.transactionDate = Calendar.getInstance().getTime();
				this.confirmNumber = ++this.currentTransactionNumber;
				this.status = true;
				this.chargedAmount = amount;
				this.credit = cret;
				this.saveTransaction();
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
		
		String saveString = "\n" + String.valueOf(this.confirmNumber)+"\t"
							+String.valueOf(this.credit.getAccountNumber())+"\t"
							+String.valueOf(dateFormat.format(this.credit.getExpiredDate()))+"\t"
							+this.credit.getNameHolder()+"\t"
							+String.valueOf(this.chargedAmount)+"\t"
							+String.valueOf(dateFormat.format(this.transactionDate))+"\t"
							+String.valueOf(this.status) ;
		
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(fileTransaction, true));
		    writer.append(saveString);
		    writer.close();
		    
		    writer = new BufferedWriter(new FileWriter(fileTransactionCurrentNumber));
		    writer.append(String.valueOf(this.currentTransactionNumber));
		    writer.close();
		} catch (IOException ioe) {
		    System.err.format("IOException: %s%n", ioe);
		    
		}

			
			
	}	
		
	
}
