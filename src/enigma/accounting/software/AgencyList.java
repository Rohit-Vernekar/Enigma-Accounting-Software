package enigma.accounting.software;

public class AgencyList {
    private String agency,location,due;
    
    public AgencyList(String a,String l,String d){
        agency=a;
        location=l;
        due=d;
        
    }
    public String getAgency(){
        return agency;
    }
    public String getLocation(){
        return location;
    }
    public String getDue(){
        return due;
    }
}
