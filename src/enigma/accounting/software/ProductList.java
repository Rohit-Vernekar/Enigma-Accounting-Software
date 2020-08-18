package enigma.accounting.software;

public class ProductList {
    
    private String product,packing,quantity,mrp;
    
    public ProductList(String prod,String pkg,String qty,String mrp){
        product=prod;
        packing=pkg;
        quantity=qty;
        this.mrp=mrp;
    }
    public String getProduct(){
        return product;
    }
    public String getPacking(){
        return packing;
    }
    public String getQuantity(){
        return quantity;
    }
    public String getMrp(){
        return mrp;
    }
    
}
