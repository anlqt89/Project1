package application;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
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

    
    //Catalog
  
   
    //end catalog
   
    //Left menu
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
      layout.getChildren().addAll(loginButton, signoutandExit, catalogButton, viewOrderButton, profileButton, cartButton);
      layout.setPadding(new Insets(10));
      layout.getChildren().forEach(node -> node.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-pref-width: 130px;"));

      scene = new Scene(layout, 800, 600);
    }
    public static void main(String[] args)
    {
        launch(args);
    }

    public Scene getScene(String choice) {

        Scene scene = null;
        
        if (choice.equals("ViewOrder")) {
        	  System.out.println("in");
            VBox ViewOrder = new VBox();
            Label titleViewOrder = new Label("View Order");
            ViewOrder.getChildren().addAll(layout, titleViewOrder);
            Scene newScene = new Scene(ViewOrder, 800, 600);
            return  newScene;
        }
        
        if (choice.equals("BrowseCataLog")) {
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
            
            VBox layout2 = new VBox(20);
            layout2.getChildren().addAll(layout, gridpaneCatalog);
            scene2 = new Scene(layout2, 800, 800);
            return scene2;
        }

        if (choice.equals("CheckOut")) {
            VBox v = new VBox();
            Label l = new Label("CheckOut");
            v.getChildren().addAll(l);
            Scene CheckOutScene = new Scene(v, 800, 600);
            return CheckOutScene;
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
        
        //Order Actions
        viewOrderButton.setOnAction(e -> window.setScene(this.getScene("ViewOrder")));
        //End Order Actions

        layout.setPadding(new Insets(10));
        window.setScene(scene);
        window.setTitle("Store");
        window.show();
        
        
        
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
