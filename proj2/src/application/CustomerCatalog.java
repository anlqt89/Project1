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

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        window = primaryStage;
        Label catalogLabel = new Label("-Supplier's Catalog-");
        Button catalogButton = new Button("Browse Catalog");
        Button signoutandExit = new Button("Sign Out & Exit");

        item1Catalog = new Label("Product1 - Member: $3.99\n\t\t  Premium: $1.99");
        item2Catalog = new Label("Product2 - Member: $4.99\n\t\t  Premium: $2.99");
        item3Catalog = new Label("Product3 - Member: $10.99\n\t\t  Premium: $6.99");
        String description1Catalog = ("Small build, migthy sound. Our most compact\nwireless earbuds deliver crips and powerful\nbeats for your everyday grind.");
        itemSummary1 = new Label(description1Catalog);
        itemSummary2 = new Label(description1Catalog);
        itemSummary3 = new Label(description1Catalog);
        Button addtoCart1 = new Button("Add to Cart");
        Button addtoCart2 = new Button("Add to Cart");
        Button addtoCart3 = new Button("Add to Cart");
        Button proceedCheckout = new Button("Proceed to Checkout");

        catalogButton.setOnAction(e -> window.setScene(scene2));
        signoutandExit.setOnAction(e -> window.close());
        addtoCart1.setOnAction(new AddtoCart1());
        addtoCart2.setOnAction(new AddtoCart2());
        addtoCart3.setOnAction(new AddtoCart3());
        proceedCheckout.setOnAction(e -> window.setScene(scene3));

        GridPane gridpaneCatalog = new GridPane();
        gridpaneCatalog.setHgap(10);
        gridpaneCatalog.setVgap(10);
        gridpaneCatalog.add(item1Catalog, 30, 0); gridpaneCatalog.add(itemSummary1, 30, 1);
        gridpaneCatalog.add(item2Catalog, 30, 10); gridpaneCatalog.add(itemSummary2, 30, 11);
        gridpaneCatalog.add(item3Catalog, 30, 20); gridpaneCatalog.add(itemSummary3, 30, 21);
        gridpaneCatalog.add(addtoCart1, 31, 0); gridpaneCatalog.add(addtoCart2, 31, 10); gridpaneCatalog.add(addtoCart3, 31, 20);
        gridpaneCatalog.add(proceedCheckout, 30, 30);

        checkoutTitle = new Label("-Cart Checkout-");

        GridPane gridpaneCheckout = new GridPane();
        gridpaneCheckout.setHgap(10);
        gridpaneCheckout.setVgap(10);
        gridpaneCheckout.add(checkoutTitle, 30, 10);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(catalogLabel, catalogButton, signoutandExit);
        scene = new Scene(layout, 300, 300);

        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(layout, gridpaneCatalog);
        scene2 = new Scene(layout2, 800, 800);

        VBox layout3 = new VBox(20);
        layout3.getChildren().addAll(gridpaneCheckout);
        scene3 = new Scene(layout3, 800, 800); 

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
