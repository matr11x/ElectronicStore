//Base class for all products the store will sell
public abstract class Product {
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    private int productCartQuantity;

    public Product(double initPrice, int initQuantity) {
        price = initPrice;
        stockQuantity = initQuantity;
        productCartQuantity = 0;
    }

    public int getProductCartQuantity(){return productCartQuantity;}
    public void setProductCartQuantity(){productCartQuantity++;}
    public void setProductRemoveCartQuantity(){productCartQuantity--;}
    public void setCompleteSaleCartQuantity(){productCartQuantity = 0;}

    public int getStockQuantity() {
        return stockQuantity;
    }
    public double getPrice() {
        return price;
    }
    public void setAddStockQuantity() {
        stockQuantity -= 1;
    }
    public void setRemoveStockQuantity() {
        stockQuantity += 1;
    }
}