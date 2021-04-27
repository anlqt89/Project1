package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements CustomerOrder{
	private int OrderID;
	private int CustomerID;
	private int customerMembership;
	private String CustomerName;
	private Date OrderDate;
	private String Items[]; //itermName price
	private double TotalAmount;
	private Date PaymentDate;
	private String PaymentName;
	private int PaymentAccountNumber;
	private String PaymentMethod;
	private String pathOrder;
	private SimpleDateFormat dateFormat;
	
	{
		this.OrderID = 0;
		this.CustomerID = 0;
		this.customerMembership = 0;
		this.CustomerName = "";
		this.Items = new String[10000];
		this.TotalAmount = 0;
		this.PaymentAccountNumber = 0;
		this.PaymentMethod = "Credit Card";
		this.pathOrder = System.getProperty("user.dir") + "/src/application/files/order";
		this.dateFormat = new SimpleDateFormat("MM/dd/yyy");
		
		try {
			this.PaymentDate = this.dateFormat.parse("01/01/1711");
			this.OrderDate = this.dateFormat.parse("01/01/1711");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Order() {
		
	}
	public Order(int orderID, int customerID, int customerMembership, String customerName, Date orderDate,
			String[] items, double totalAmount, Date paymentDate, String paymentName, int paymentAccountNumber,
			String paymentMethod) {
		super();
		OrderID = orderID;
		CustomerID = customerID;
		this.customerMembership = customerMembership;
		CustomerName = customerName;
		OrderDate = orderDate;
		Items = items;
		TotalAmount = totalAmount;
		PaymentDate = paymentDate;
		PaymentName = paymentName;
		PaymentAccountNumber = paymentAccountNumber;
		PaymentMethod = paymentMethod;
	}

	
	@Override
	public String[][] requestAllOrders(int CustomerID) {
		// TODO Auto-generated method stub
		//OrderID	CustomerID	CustomerName CustomerMemberShip	OrderDate	PaymentDate	PaymentName	PaymentAccountNumber	PaymentMethod
		//Item1Name,price	Item2Name,price	Item3,price
		String orderInfo;
		String breakOrderInfo[] = new String[30];
		String orderItems;
		String order[][] = new String[100000][30];
		int i=0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.pathOrder));
			orderInfo= reader.readLine();
			orderItems = reader.readLine();
			while(orderInfo != null) {
				orderInfo= reader.readLine();
				orderItems = reader.readLine();
				if(orderInfo == null) break;
				breakOrderInfo = orderInfo.split("\t");
				if(breakOrderInfo[1].equals(String.valueOf(CustomerID))) {
					order[i++] = breakOrderInfo;
					order[i++] = orderItems.split("\t");
				}
				
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		
		return order;
	}

	@Override
	public String[][] requestOneOrder(int OrderID, int CustomerID) {
		// TODO Auto-generated method stub
		String orderInfo;
		String breakOrderInfo[];
		String orderItems;
		String orderList[][] = new String[2][30];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.pathOrder));
			orderInfo= reader.readLine();
			orderItems = reader.readLine();
			while(orderItems != null) {
				
				orderInfo= reader.readLine();
				orderItems = reader.readLine();
				if(orderInfo == null) break;
				breakOrderInfo = orderInfo.split("\t");
				
				if(breakOrderInfo[0].equals(String.valueOf(OrderID)) && breakOrderInfo[1].equals(String.valueOf(CustomerID))) {
					orderList[0] = breakOrderInfo;
					orderList[1] = orderItems.split("\t");
					break;
				}
				
			}
			reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orderList;
	}

}
