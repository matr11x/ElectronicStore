import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ElectronicStoreButtonPane extends Pane {
    private Button resetStore, addCart, removeCart, completeSale;
    public Button getResetStore(){return resetStore;}
    public Button getAddCart(){return addCart;}
    public Button getRemoveCart(){return removeCart;}
    public Button getCompleteSale(){return completeSale;}


    public ElectronicStoreButtonPane() {
        Pane innerPane = new Pane();

        //create the buttons
        resetStore = new Button("Reset Store");
        resetStore.setStyle("-fx-font: 13 arial; -fx-base: rgb(255,255,255); -fx-text-fill: rgb(0,0,0);");
        resetStore.relocate(0,0);
        resetStore.setPrefSize(145, 45);

        addCart = new Button("Add to Cart");
        addCart.setStyle("-fx-font: 13 arial; -fx-base: rgb(255,255,255); -fx-text-fill: rgb(0,0,0);");
        addCart.relocate(245,0);
        addCart.setPrefSize(145,45);

        removeCart = new Button("Remove from Cart");
        removeCart.setStyle("-fx-font: 13 arial; -fx-base: rgb(255,255,255); -fx-text-fill: rgb(0,0,0);");
        removeCart.relocate(468, 0);
        removeCart.setPrefSize(143, 45);

        completeSale = new Button("Complete Sale");
        completeSale.setStyle("-fx-font: 13 arial; -fx-base: rgb(255,255,255); -fx-text-fill: rgb(0,0,0);");
        completeSale.relocate(611, 0);
        completeSale.setPrefSize(143,45);

        // Add all three buttons to the pane
        innerPane.getChildren().addAll(resetStore, addCart, removeCart, completeSale);

        getChildren().addAll(innerPane);
    }

}
