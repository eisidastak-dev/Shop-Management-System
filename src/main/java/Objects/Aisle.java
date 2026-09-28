package Objects;

import com.example.ass1data.HelperMethods;

public class Aisle {
    private String aisleName = "";
    private int aisleLength = 0;
    private int aisleWidth = 0;
    private int aisleHeight = 0;
    private String temperature = "";
    private String floorAreaDesignation = "";

    public Aisle(String name, int length, int width, int height, String temperature, String floorAreaDesignation){
        setAisleName(name);
        setAisleLenght(length);
        setAisleWidth(width);
        setAisleHeight(height);
        setTemperature(temperature);
        setFloorAreaDesignation(floorAreaDesignation);
    }

    //-----------------SETTERS-------------------------
    public void setFloorAreaDesignation(String floorAreaDesignation){
        this.floorAreaDesignation = floorAreaDesignation;
    }

    public void setAisleName(String name){
        this.aisleName = HelperMethods.truncateString(name,20);
    }

    public void setAisleLenght(int length){
        if(length <= 2000)
            this.aisleLength = length;
    }

    public void setAisleWidth(int width){
        if(width <= 200)
            this.aisleWidth = width;
    }

    public void setAisleHeight(int height){
        if(height <= 500)
            this.aisleHeight = height;
    }

    public void setTemperature(String temperature){
        this.temperature = temperature;
    }
    //-----------------GETTERS-------------------------
    public String getFloorAreaDesignation(){
        return floorAreaDesignation;
    }

    public String getAisleName(){
        return aisleName;
    }

    public int getAisleLength(){
        return aisleLength;
    }

    public int getAisleWidth(){
        return aisleWidth;
    }

    public int getAisleHeight(){
        return aisleHeight;
    }

    public String getTemperature(){
        return temperature;
    }


}