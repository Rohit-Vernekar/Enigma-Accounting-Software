
package enigma.accounting.software;

public class BatchList {
    private String batch,exp,qty,mrp;
    public BatchList(String b,String e,String q,String m){
        batch=b;
        exp=e;
        qty=q;
        mrp=m;
    }
    public String getBatch(){
        return batch;
    }
    public String getExp(){
        return exp;
    }
    public String getQty(){
        return qty;
    }
    public String getMrp(){
        return mrp;
    }
}
