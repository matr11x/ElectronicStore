import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ElectronicStoreApp extends Application {
    private ElectronicStore model;
    public ElectronicStoreApp(){
        model = ElectronicStore.createStore();
    }

    public void start(Stage primaryStage){

        ElectronicStoreView view = new ElectronicStoreView();

        Pane aPane = new Pane();

        aPane.getChildren().add(view);

        //add to cart button enable when item selected
        view.getStocklist().setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });

        //remove from cart button enabled when item selected
        view.getCartList().setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });

        view.getButtonpane().getAddCart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.setItemsBought(view.getSelectionProduct());
                for (Product product : model.getStock()) {
                    if (product.toString().contains(view.getSelectionProduct())) {
                        view.setAddCurrentVal(product);
                        model.setAddCartVal(product);
                    }
                }
                view.update(model);
            }
        });

        view.getButtonpane().getRemoveCart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (Product product : model.getStock()) {
                    if (model.getCurrentCart().get(view.getSelectionIndex()).contains(product.toString())){
                        view.setRemoveCurrentVal(product);
                        model.setRemoveCartVal(product);
                    }
                }
                model.setCartRemove(view.getSelectionIndex());
                view.update(model);
            }
        });

        view.getButtonpane().getCompleteSale().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.setCompleteSale();
                view.setRevenue(model.setRevenue());
                view.setCurrentValCompleteSale();
                view.setSales(model.setIntSales());
                view.setprofitMarginText(model.setProfitMargin());
                model.setPopularItems();
                view.update(model);
            }
        });

        view.getButtonpane().getResetStore().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ElectronicStoreApp resetApp = new ElectronicStoreApp();
                resetApp.start(primaryStage);
            }
        });

        primaryStage.setTitle("Electronic Store Application - " + model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();

        view.update(model);
    }

}
