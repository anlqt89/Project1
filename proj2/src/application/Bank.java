package application;

import java.util.Date;

interface Bank {
	
	//charge customer, return -1 if not valid, else transtion number > 0
	public int	charge(int AcountNumber, Date ExpiredDate, String nameHolder, double amount);
	
	//verify if transaction is valid
	public boolean	verifyTransaction(int AcountNumber, Date ExpiredDate, String nameHolder);
	
}
