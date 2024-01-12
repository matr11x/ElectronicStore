import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ElectronicStoreView extends Pane implements ElectronicView{
    private ListView<Product> popularList;
    private ListView<String> stockList, cartList;
    private TextField salesField, rField, profitMargin;
    private ElectronicStoreButtonPane buttonpane;
    private Label cartLabel;
    public ElectronicStoreButtonPane getButtonpane(){return buttonpane;}
    public String getSelectionProduct(){return stockList.getSelectionModel().getSelectedItem();}
    public int getSelectionIndex(){return cartList.getSelectionModel().getSelectedIndex();}
    private double currentVal = 0.00;
    private String revenue = "0.00";
    private String sales = "0";
    private String margin = "N/A";
    public void setAddCurrentVal(Product p){currentVal += p.getPrice();}
    public void setRemoveCurrentVal(Product p){currentVal -= p.getPrice();}
    public void setCurrentValCompleteSale(){currentVal = 0;}
    public void setRevenue(double rev){
        revenue = String.valueOf(String.format("%.2f", rev));
    }
    public void setSales(int sale){
        sales = String.valueOf(sale);
    }
    public void setprofitMarginText(double profit){
        margin = String.valueOf(String.format("%.2f", profit));
    }

    public ElectronicStoreView(){
        //create the labels
        Label summaryLabel = new Label("Store Summary");
        summaryLabel.relocate(47,20);
        Label stockLabel = new Label("Store Stock:");
        stockLabel.relocate(301,20);
        cartLabel = new Label();
        cartLabel.relocate(575,20);
        Label numberOfSales = new Label("# Sales:");
        numberOfSales.relocate(40,45);
        Label revLabel = new Label("Revenue:");
        revLabel.relocate(32,79);
        Label profitMarginLabel = new Label("$ / Sale:");
        profitMarginLabel.relocate(39,112);
        Label popularLabel = new Label("Most Popular Items:");
        popularLabel.relocate(35,150);

        //create the textfields
        salesField = new TextField();
        salesField.relocate(88, 40);
        salesField.setPrefSize(95,25);

        rField = new TextField();
        rField.relocate(88, 74);
        rField.setPrefSize(95,25);

        profitMargin = new TextField();
        profitMargin.relocate(88, 107);
        profitMargin.setPrefSize(95,25);

        //create the list views
        stockList = new ListView<String>();
        stockList.relocate(194, 40);
        stockList.setPrefSize(285,293);

        cartList = new ListView<String>();
        cartList.relocate(492,40);
        cartList.setPrefSize(285, 293);

        popularList = new ListView<Product>();
        popularList.relocate(11,172);
        popularList.setPrefSize(172,160);

        //buttonpane
        buttonpane = new ElectronicStoreButtonPane();
        buttonpane.relocate(25,345);
        buttonpane.setPrefSize(777,45);


        getChildren().addAll(summaryLabel, stockLabel, cartLabel, numberOfSales, revLabel, profitMarginLabel, popularLabel, salesField, rField, profitMargin, stockList, cartList, popularList, buttonpane);
        setPrefSize(800, 400);
    }
    public ListView<String> getStocklist(){return stockList;}
    public ListView<String> getCartList(){return cartList;}

    @Override
    public void update(ElectronicStore model) {
        stockList.setItems((FXCollections.observableArrayList(model.getStringStock())));
        cartList.setItems((FXCollections.observableArrayList(model.getCurrentCart())));
        if (model.getPopularItems().isEmpty()){
            popularList.setItems(FXCollections.observableArrayList(model.getStock().subList(0,3)));
        }
        else{
            popularList.setItems(FXCollections.observableArrayList(model.getPopularItems()));
        }

        String stockSelection = stockList.getSelectionModel().getSelectedItem();
        buttonpane.getAddCart().setDisable(stockSelection == null);

        String cartSelection = cartList.getSelectionModel().getSelectedItem();
        buttonpane.getRemoveCart().setDisable(cartSelection == null);

        buttonpane.getCompleteSale().setDisable(model.getCurrentCart().size() == 0);

        cartLabel.setText(String.format("Current Cart ($%.2f", currentVal) +"):");
        rField.setText(revenue);
        salesField.setText(sales);
        profitMargin.setText(margin);
    }
}
