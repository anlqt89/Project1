package application;

public interface CustomerOrder{
	public String[][] requestAllOrders(int CustomerID);
	public String[][] requestOneOrder(int OrderID, int CustomerID);

}
