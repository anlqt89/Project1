package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
;

public class CreditCard {
	private int accountNumber;
	private Date expiredDate;
	private String nameHolder;
	private double balance;
	private String fileCreditCard;
	//private BufferedReader reader;
	{
		this.fileCreditCard = System.getProperty("user.dir") + "/src/application/files/creditcard";
	}
	public boolean verifyAccount() {
		
		BufferedReader reader;
		try {;
			reader = new BufferedReader(new FileReader(this.fileCreditCard));
			String line = reader.readLine();
			line = reader.readLine();
			String acc[];
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
			
			while(line != null) {
				 acc = line.split("\t");
				 String exprDate = dateFormat.format(this.expiredDate);
				 
				 //test
				 //System.out.println(acc[0] +" "+ acc[1]+" " + acc[2] + "||\n" + this.accountNumber+ " " + dateFormat.format(this.expiredDate) + " "+this.nameHolder);
				if(acc[0].equals(String.valueOf(this.accountNumber)) && acc[1].equals(exprDate) && acc[2].equals(this.nameHolder)) {
					reader.close();
					return true;
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Account is invalid");
			return false;
		}
		return false;			
	}
	
	public boolean checkBalance(double chargedAmount){
		if(this.verifyAccount() == true) {
			if(this.balance < chargedAmount) return false;
		}
		return true;
	}
	
	public boolean iskExpiredDate(){
		if(this.verifyAccount() == true) {
					
			if(this.expiredDate.before(Calendar.getInstance().getTime())) return true;
		}
		return false;
	}
	
	public void charge(double amount){
		this.balance = this.balance - amount;
		File file = new File(this.fileCreditCard);
		
		
		//replace an old string in a specific line from file
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(this.fileCreditCard));
			String line = reader.readLine(); 
			String OldContent = line;
			line = reader.readLine();
			
			String acc[];
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			while(line != null) {
				 acc = line.split("\t");
				 String exprDate = dateFormat.format(this.expiredDate);
				 
				//test
				// System.out.println(acc[0] +" "+ acc[1]+" " + acc[2] + "||\n" + this.accountNumber+ " " + exprDate + " "+this.nameHolder);
				 
				 if(acc[0].equals(String.valueOf(this.accountNumber)) && acc[1].equals(exprDate) && acc[2].equals(this.nameHolder)) {
					 
					 OldContent = OldContent + "\n" + this.accountNumber + "\t" + exprDate + "\t" + this.nameHolder + "\t" + this.balance;
					 
				}
				 else {
					 OldContent = OldContent + "\n" +line;
				 }
				line = reader.readLine();
			}
			writer.write(OldContent);
			reader.close();
			writer.close();
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			System.out.print("Account is invalid");
			
		}
	    
	}
	{
		this.accountNumber = 0;
		try {
			this.expiredDate = new SimpleDateFormat("MM/dd/yyy").parse("01/01/1711");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.nameHolder = "";
		this.balance = 0;
	}

	public CreditCard( int accountNumber, Date expiredDate, String nameHolder) {
		this.accountNumber = accountNumber;
		this.expiredDate = expiredDate;
		this.nameHolder = nameHolder;
		this.balance = this.loadBanalce();
	}
	
	private double loadBanalce() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.fileCreditCard));
			String line = reader.readLine();
			line = reader.readLine();
			String acc[];
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
			
			while(line != null) {
				 acc = line.split("\t");
				 String exprDate = dateFormat.format(this.expiredDate);
				 
				 //test
				// System.out.println(acc[0] +" "+ acc[1]+" " + acc[2] + "||\n" + this.accountNumber+ " " + dateFormat.format(this.expiredDate) + " "+this.nameHolder);
				if(acc[0].equals(String.valueOf(this.accountNumber)) && acc[1].equals(exprDate) && acc[2].equals(this.nameHolder)) {
					reader.close();
					return Double.parseDouble(acc[3]);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Account is invalid");
			return 0;
		}
		return 0;
	}
	public CreditCard() {
		
	}


	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}


	public Date getExpiredDate() {
		return expiredDate;
	}


	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}


	public String getNameHolder() {
		return nameHolder;
	}


	public void setNameHolder(String nameHolder) {
		this.nameHolder = nameHolder;
	}
	

	
}
