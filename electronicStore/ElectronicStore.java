//Class representing an electronic store
//Has an array of products that represent the items the store can sell
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ElectronicStore {
    private String name;
    private List<Product> stock; //Array to hold all products
    private List<String> currentCart;
    private double revenue;
    private ArrayList<String> stringStock;
    private ArrayList<Product> popularItems;
    private double cartVal;
    private int intSales;
    private double profitMargin;

    public ElectronicStore(String initName) {
        name = initName;
        revenue = 0.0;
        intSales = 0;
        profitMargin = 0.0;
        stock = new ArrayList<Product>();
        currentCart = new ArrayList<String>();
        stringStock = new ArrayList<String>();
        popularItems = new ArrayList<Product>();
    }

    public void setItemsBought(String p) {

        for (int i = 0; i < currentCart.size(); i++) {
            if (currentCart.get(i).contains(p)) {
                currentCart.remove(currentCart.get(i));
            }
        }
        for (Product product : stock){
            if (product.toString().contains(p)){
                product.setProductCartQuantity();
                product.setAddStockQuantity();
                String s = product.getProductCartQuantity() + " x " + p;
                currentCart.add(s);
                if (product.getStockQuantity() == 0){
                    stringStock.remove(product.toString());
                }
            }
        }
    }

    public void setCartRemove(int integer){

        String s = currentCart.get(integer);
        String[] currentCartString = s.split(" x ");
        int cartQuantity = Integer.parseInt(currentCartString[0].strip());

        if (cartQuantity == 1){
            currentCart.remove(integer);

            for (Product p : stock) {
                if (p.toString().contains(currentCartString[1].strip())) {
                    p.setRemoveStockQuantity();
                    p.setProductRemoveCartQuantity();
                }
            }
        }

        else{
            if (cartQuantity == 10 && !stringStock.contains(currentCartString[1].strip())){
                stringStock.add(currentCartString[1].strip());
            }
            cartQuantity--;
            currentCart.remove(integer);
            String product = cartQuantity + " x " + currentCartString[1];
            currentCart.add(integer, product);

            for (Product p : stock) {
                if (p.toString().contains(currentCartString[1].strip())) {
                    p.setRemoveStockQuantity();
                    p.setProductRemoveCartQuantity();
                    System.out.println(p + "And stock quantity " + p.getStockQuantity());
                }
            }
        }
    }

    public void setPopularItems(){

        popularItems.clear();

        ArrayList<Product> sortedItems = new ArrayList<Product>(stock);

        sortedItems.sort(new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                if (p2.getStockQuantity() < p1.getStockQuantity()){
                    return 1;
                }
                return -1;
            }
        });

        for (int i = 0; i < 3; i++){
            popularItems.add(sortedItems.get(i));
        }
    }

    public ArrayList<Product> getPopularItems(){return popularItems;}
    public void setAddCartVal(Product p){cartVal += p.getPrice();}
    public void setRemoveCartVal(Product p){cartVal -= p.getPrice();}

    public void setCompleteSale(){

        for (Product p : stock){
            p.setCompleteSaleCartQuantity();
        }
        currentCart.clear();
    }

    public double setRevenue(){
        revenue += cartVal;
        cartVal = 0;
        return revenue;
    }
    public int setIntSales(){
        intSales += 1;
        return intSales;
    }
    public double setProfitMargin(){
        profitMargin = revenue/intSales;
        return profitMargin;
    }

    public String getName() {
        return name;
    }

    //Adds a product and returns true if there is space in the array
    //Returns false otherwise
    public boolean addProduct(Product newProduct){
        for (Product p : stock){
            if (p.toString().contains(newProduct.toString()))
                return false;
        }
        stock.add(newProduct);
        stringStock.add(newProduct.toString());
        return true;
    }

    public List<Product> getStock(){return stock;}
    public List<String> getCurrentCart(){return currentCart;}
    public List<String> getStringStock(){return stringStock;}

    public static ElectronicStore createStore() {
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }
}
