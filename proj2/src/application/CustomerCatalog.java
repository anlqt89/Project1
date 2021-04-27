package application;



import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;


public class CustomerCatalog extends Application
{
    private Label item1Catalog; private Label itemSummary1;
    private Label item2Catalog; private Label itemSummary2;
    private Label item3Catalog; private Label itemSummary3;
    int item1 = 1, item2 = 2, item3 = 3;
    String description1 = ("Small build, migthy sound. Our most compact wireless earbuds deliver crips and powerful beats for your everyday grind.");
    private Label checkoutTitle;
    double product1M = 3.99, product1P = 1.99;
    double product2M = 4.99, product2P = 2.99;
    double product3M = 10.99, product3P = 6.99;
    Stage window;
    Scene scene, scene2, scene3;

    //Session 
    int customerID;
    //Catalog
  
   
    //end catalog
   
    //Left menu bar
    Button catalogButton;
    Button loginButton;
    Button viewOrderButton;
    Button profileButton;
    Button cartButton;
    Button signoutandExit;
    VBox layout ;
    //End LeftMenu
    int leftSceneWidth;
    int leftSceneHeight;
    {
    	//Session
    	this.customerID = 0;
    	
    	//End Sesson
    	
    	//Left menu bar
    	this.loginButton = new Button("Login");
    	this.viewOrderButton = new Button("View Order");
    	this.profileButton = new Button("Profile");
    	this.cartButton = new Button("Cart");
    	this.catalogButton = new Button("Browse Catalog");
        this.signoutandExit = new Button("Sign Out & Exit");
    	this.leftSceneWidth = 600;
    	this.leftSceneHeight = 800;
    	
    	layout = new VBox(20);
        layout.setPrefWidth(20);
        layout.getChildren().addAll(loginButton, 
        		signoutandExit, catalogButton, viewOrderButton, profileButton, cartButton);
        layout.setPadding(new Insets(10));
        layout.getChildren().forEach(node ->
            node.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-pref-width: 130px;"));
        layout.setStyle("-fx-pref-width: 130px;");
        //End left menu bar
        scene = new Scene(layout, 800, 600);
    }
    public static void main(String[] args)
    {
        launch(args);
    }

    public Scene getSceneViewOrderDetail(String sceneName, int orderID, int customerID) {
    	//Set up layout [leftMenu][right frame]
	  	    //[right frame]:  	
	  	    //			[leftMenu] [tranNum]		[value]
	  	    //			[leftMenu] [name]			[value] 
	  	    //			[leftMenu] [memberShip] 	[value]
	  	    //			[leftMenu] [OrderDate]		[value]
        
	      	//			[leftMenu] [Item 1]			[cost]
	      	//			[leftMenu] [Item n]			[cost]
        
	  	    //			[leftMenu] [PaymentDate]	[value]
	  	    //			[leftMenu] [CreditCardNum]	[value]
	  	    //			[leftMenu] [PaymentMethod]	[value]
	        //			[leftMenu] [Items		]	[value]
		  	//			[leftMenu] [Total]			[value]
	        //			[leftMenu] [Status]			[value]
		  	    
    	 if (sceneName.equals("ViewOneOrder")) {
         	//Get Order Information
         	this.customerID = 123456789;
         	CustomerOrder customerOrder = new Order();
   	      	String[][] orderList=  customerOrder.requestOneOrder(orderID, customerID);
         	
 	  	    GridPane gridpane = new GridPane();
	        gridpane.setHgap(6);
	        gridpane.setVgap(20);
 	        
	        int numberOfItems = Integer.parseInt(orderList[0][11]);
 	           	
	        Label orderIDHeaderLable = new Label("Transaction Number:");
  	      	Label customerNameHeaderLable = new Label("Customer Name: ");
  	      	Label customerMemberShipHeaderLable = new Label("Membership:");
  	      	Label itemsName[] = new Label[numberOfItems];
	      	Label orderDateHeaderLable= new Label("Order Date:");
	      	Label paymentDateHeaderLable= new Label("Order Date:");
	      	Label paymentMethodHeaderLable = new Label("Payment Method:");
	      	Label numberOfItemsHeaderLabel = new Label("Items:");
	      	Label totalHeaderLable= new Label("Total:");
	      	Label statusHeaderLable= new Label("Status:");

	      	
  	      	Label orderIDLable = new Label(orderList[0][0]);
  	      	Label customerNameLable = new Label(orderList[0][2]);
  	      	Label customerMemberShip = new Label(orderList[0][3]);
  	      	Label itemsPrice[] = new Label[numberOfItems];
  	      	Label orderDateLabel= new Label(orderList[0][4]);
  	      	Label paymentDateLable = new Label(orderList[0][5]);
  	      	Label paymentMethodLabel = new Label(orderList[0][8]);
  	      	Label numberOfItemsLabel = new Label(orderList[0][11]);
  	      	Label totalLable = new Label(orderList[0][9]);
  	      	Label statusLable= new Label(orderList[0][10]);
  	      	
  	      int gridRow= 0;
  	      	//                               x,v
  	      	gridpane.add(orderIDHeaderLable, 0, gridRow);  gridpane.add(orderIDLable, 1, gridRow);
  	      	gridRow++;
  	      	gridpane.add(customerNameHeaderLable, 0, gridRow);  gridpane.add(customerNameLable, 1, gridRow); 
  	      	gridRow++;
  	      	gridpane.add(customerMemberShipHeaderLable, 0, gridRow);  gridpane.add(customerMemberShip, 1, gridRow);
  	      	gridRow++;
  	      	
  	      	int i = 0;
  	      	String splitItem[];
  	      	for(i = 0;i<numberOfItems; i++) {
  	      		splitItem =orderList[1][i].split(",");
  	      	    itemsName[i] = new Label(splitItem[0]);
  	      	    itemsPrice[i] = new Label(splitItem[1]);
  	      	    gridpane.add(itemsName[i], 0, gridRow);  gridpane.add(itemsPrice[i], 1, gridRow);
  	      	    gridRow++;
  	      	}
  	      	gridpane.add(orderDateHeaderLable, 0, gridRow);  gridpane.add(orderDateLabel, 1, gridRow);   
  	      	gridRow++;
  	      	gridpane.add(paymentDateHeaderLable, 0, gridRow);  gridpane.add(paymentDateLable, 1, gridRow);   
	      	gridRow++;
	      	gridpane.add(paymentMethodHeaderLable, 0, gridRow);  gridpane.add(paymentMethodLabel, 1, gridRow);   
  	      	gridRow++;
  	      	gridpane.add(numberOfItemsHeaderLabel, 0, gridRow);  gridpane.add(numberOfItemsLabel, 1, gridRow);   
	      	gridRow++;
	      	gridpane.add(totalHeaderLable, 0, gridRow);  gridpane.add(totalLable, 1, gridRow);   
  	      	gridRow++;
  	      	gridpane.add(statusHeaderLable, 0, gridRow);  gridpane.add(statusLable, 1, gridRow);   
	      	gridRow++;
  	      	
	      	gridpane.setStyle("-fx-pref-width: 550px; -fx-pref-height: 20px;");
            HBox layout2 = new HBox(20);
            VBox leftBox = new VBox(gridpane);
            layout2.getChildren().addAll(layout, leftBox);

            scene2 = new Scene(layout2, 800, 800);
            return scene2;
         	

         }
		return scene;
    }
    
    public Scene getScene(String sceneName) {

        Scene scene = null;

        if (sceneName.equals("ViewAllOrder")) {
        	//Get Order Information
        	this.customerID = 123456789;
        	CustomerOrder customerOrder = new Order();
  	      	String[][] orderList=  customerOrder.requestAllOrders(this.customerID);
        	
  	      	Button viewOrderDetailButton[] = new Button[10];
  	      	
	      	Label tranNumHeaderLable = new Label("Transaction Number");
	      	Label orderDateHeaderLable= new Label("Order Date");
	      	Label totalHeaderLable= new Label("Total");
	      	Label statusHeaderLable= new Label("Status");
	      	
  	      	Label customerNameLable = new Label("Name: " + orderList[2][2]);
  	      	Label tranNumLable ;
  	      	Label orderDateLable;
  	      	Label totalLable;
  	      	Label statusLable;
	  	    GridPane gridpane = new GridPane();
	        gridpane.setHgap(6);
	        gridpane.setVgap(10);
	        
	        gridpane.add(customerNameLable, 0, 0); 
	        gridpane.add(tranNumHeaderLable, 0, 1); 
	        gridpane.add(orderDateHeaderLable, 1, 1); 
	        gridpane.add(statusHeaderLable, 2, 1);
	        gridpane.add(totalHeaderLable, 3, 1);
	        
	        int gridRow =2;
	        for(int i=0; i<10; i+=2) {
	        	if(orderList[i][0] == null) break;
	        	//test
	        	//System.out.println(orderList[i][0]+" " + orderList[i][4]+" " + orderList[i][10] +" "+ orderList[i][9]);
	        	tranNumLable = new Label( orderList[i][0]);
	  	      	orderDateLable= new Label( orderList[i][4]);
	  	      	totalLable= new Label( orderList[i][10]);
	  	        statusLable= new Label( orderList[i][9]);
	  	        
	        	gridpane.add(tranNumLable, 0, gridRow); 
		        gridpane.add(orderDateLable, 1, gridRow); 
		        gridpane.add(totalLable, 2, gridRow);
		        gridpane.add(statusLable, 3, gridRow);
		        viewOrderDetailButton[gridRow] = new Button("View Detail");
		        gridpane.add(viewOrderDetailButton[gridRow], 4, gridRow);
		      
		        int orderID = Integer.parseInt(orderList[i][0]);
		        int CustomerID = Integer.parseInt(orderList[i][1]);
		        viewOrderDetailButton[gridRow].setOnAction(e -> window.setScene(this.getSceneViewOrderDetail("ViewOneOrder", orderID, CustomerID)));
		        
		        gridRow++;
	        }
	      	  	   
            
            HBox layout2 = new HBox(20);
            VBox leftBox = new VBox(gridpane);
            layout2.getChildren().addAll(layout, leftBox);

            scene2 = new Scene(layout2, 800, 800);
            return scene2;
            
        }
   
        
        if (sceneName.equals("BrowseCataLog")) {
        	Label catalogLabel = new Label("-Supplier's Catalog-");
            Label item1Catalog = new Label("Product1 - Member: $3.99\n\t\t  Premium: $1.99");
            Label item2Catalog = new Label("Product2 - Member: $4.99\n\t\t  Premium: $2.99");
            Label item3Catalog = new Label("Product3 - Member: $10.99\n\t\t  Premium: $6.99");
            String description1Catalog = ("Small build, migthy sound. Our most compact\nwireless earbuds deliver crips and powerful\nbeats for your everyday grind.");
            Label itemSummary1 = new Label(description1Catalog);
            Label itemSummary2 = new Label(description1Catalog);
            Label itemSummary3 = new Label(description1Catalog);
            Button addtoCart1 = new Button("Add to Cart");
            Button addtoCart2 = new Button("Add to Cart");
            Button addtoCart3 = new Button("Add to Cart");
            Button proceedCheckout = new Button("Proceed to Checkout");
        	           
            GridPane gridpaneCatalog = new GridPane();
            gridpaneCatalog.setHgap(10);
            gridpaneCatalog.setVgap(10);
            gridpaneCatalog.add(item1Catalog, 30, 0); gridpaneCatalog.add(itemSummary1, 30, 1);
            gridpaneCatalog.add(item2Catalog, 30, 10); gridpaneCatalog.add(itemSummary2, 30, 11);
            gridpaneCatalog.add(item3Catalog, 30, 20); gridpaneCatalog.add(itemSummary3, 30, 21);
            gridpaneCatalog.add(addtoCart1, 31, 0); gridpaneCatalog.add(addtoCart2, 31, 10); gridpaneCatalog.add(addtoCart3, 31, 20);
            gridpaneCatalog.add(proceedCheckout, 30, 30);
            
            checkoutTitle = new Label("-Cart Checkout-");
            
            //Change scene
            proceedCheckout.setOnAction(e -> window.setScene(this.getScene("CheckOut")));
            
            
            GridPane gridpaneCheckout = new GridPane();
            gridpaneCheckout.setHgap(10);
            gridpaneCheckout.setVgap(10);
            gridpaneCheckout.add(checkoutTitle, 30, 10);
            
            
            signoutandExit.setOnAction(e -> window.close());
            addtoCart1.setOnAction(new AddtoCart1());
            addtoCart2.setOnAction(new AddtoCart2());
            addtoCart3.setOnAction(new AddtoCart3());
            
            
            addtoCart1.setOnAction(new AddtoCart1());
            addtoCart2.setOnAction(new AddtoCart2());
            addtoCart3.setOnAction(new AddtoCart3());
            
            HBox layout2 = new HBox(20);
            VBox rightBox = new VBox(gridpaneCatalog);
            layout2.getChildren().addAll(layout, rightBox);
            scene2 = new Scene(layout2, 800, 800);
            return scene2;
        }

        if (sceneName.equals("CheckOut")) {
            VBox v = new VBox();
            Label l = new Label("CheckOut");
            v.getChildren().addAll(l);
            Scene CheckOutScene = new Scene(v, 800, 600);
            
           // proceedCheckout.setOnAction("Show message");
            
            //mov cart snce 
            //proceedCheckout.setOnAction(e -> window.setScene(this.getScene("Cart")));
            return CheckOutScene;
        }
        
        if (sceneName.equals("Login")) {
            
        	//Set up your layabout and 
           
            HBox layout2 = new HBox(20);
            VBox leftBox = new VBox();
            layout2.getChildren().addAll(layout, leftBox);

            scene2 = new Scene(layout2, 800, 800);
            return scene2;
        }
       
        return scene;
        
    }
    @Override
    public void start(Stage primaryStage)
    {
        window = primaryStage;
       
        //CataLog Actions
        catalogButton.setOnAction(e -> window.setScene(this.getScene("BrowseCataLog")));
        //End CataLog Actions
        
       //CataLog Actions
        loginButton.setOnAction(e -> window.setScene(this.getScene("Login")));
        //End CataLog Actions

        
        //Order Actions
        viewOrderButton.setOnAction(e -> window.setScene(this.getScene("ViewAllOrder")));
        //End Order Actions

        
        layout.setPadding(new Insets(10));
        window.setScene(scene);
        window.setTitle("Store");
        window.show();

        
        
    }
    class viewOrder implements EventHandler<ActionEvent>
    {
      int customerID;
      {
    	  this.customerID = 0;
      }
      @Override
      public void handle(ActionEvent event)
      {
   
	      CustomerOrder customerOrder = new Order();
	      String[][] orderList=  customerOrder.requestAllOrders(this.customerID);
	      
      }
    }

    class AddtoCart1 implements EventHandler<ActionEvent>
    {
      @Override
      public void handle(ActionEvent event)
      {
        try
        {
          FileWriter file = new FileWriter("Cart.txt", true);
          PrintWriter cartHolder = new PrintWriter(file);
          cartHolder.println(item1 + "\t" + "item1" + "\t" + description1);
          cartHolder.close();
        }
        catch (FileNotFoundException e)
        {
          e.printStackTrace();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
    class AddtoCart2 implements EventHandler<ActionEvent>
    {
      @Override
      public void handle(ActionEvent event)
      {
        try
        {
          FileWriter file = new FileWriter("Cart.txt", true);
          PrintWriter cartHolder = new PrintWriter(file);
          cartHolder.println(item2 + "\t" + "item2" + "\t" + description1);
          cartHolder.close();
        }
        catch (FileNotFoundException e)
        {
          e.printStackTrace();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
    class AddtoCart3 implements EventHandler<ActionEvent>
    {
      @Override
      public void handle(ActionEvent event)
      {
        try
        {
          FileWriter file = new FileWriter("Cart.txt", true);
          PrintWriter cartHolder = new PrintWriter(file);
          cartHolder.println(item3 + "\t" + "item3" + "\t" + description1);
          cartHolder.close();
        }
        catch (FileNotFoundException e)
        {
          e.printStackTrace();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
   
}


