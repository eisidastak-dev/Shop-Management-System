package Objects;

import com.example.ass1data.HelperMethods;

public class Item {
    private String description = "No Description";
    private int weight = 0;
    private double unitPrice = 0;
    private int quantity = 0;
    private String storageTemperature = "Unassigned";
    private String photoUrl = "";
    private int shelfDesignation = 0;

    public Item(String Description, int weight, double unitPrice, int quantity, String storageTemperature, String photoUrl, int shelfDesignation){
        setDescription(Description);
        setWeight(weight);
        setUnitPrice(unitPrice);
        setQuantity(quantity);
        setStorageTemperature(storageTemperature);
        setPhotoUrl(photoUrl);
        setShelfDesignation(shelfDesignation);
    }
    //------------------SETTERS----------------------------------------

    public void setShelfDesignation(int shelfDesignation){
        this.shelfDesignation = shelfDesignation;
    }

    public void setDescription(String description){
        this.description = HelperMethods.truncateString(description, 20);
    }

    public void setWeight(int weight){
        if(weight <= 20000)
            this.weight = weight;
    }

    public void setUnitPrice(double unitPrice){
        if(unitPrice <= 1000)
            this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity){
        if(quantity <= 999)
            this.quantity = quantity;
    }

    public void setStorageTemperature(String storageTemperature){
        this.storageTemperature = storageTemperature;
    }

    public void setPhotoUrl(String photoUrl){
        this.photoUrl = HelperMethods.truncateString(photoUrl, 100);
    }
    //-----------------GETTERS-----------------------------------------

    public int getShelfDesignation(){
        return shelfDesignation;
    }

    public String getDescription(){
        return description;
    }

    public int getWeight(){
        return weight;
    }

    public double getUnitPrice(){
        return unitPrice;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getStorageTemperature(){
        return storageTemperature;
    }

    public String getPhotoUrl(){
        return photoUrl;
    }

}