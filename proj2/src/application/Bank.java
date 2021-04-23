package application;

import java.util.Date;

interface Bank {
	public int	charge(int AcountNumber, Date ExpiredDate, String nameHolder, double amount);
	public boolean	verifyTransaction(int AcountNumber, Date ExpiredDate, String nameHolder);
	
}
