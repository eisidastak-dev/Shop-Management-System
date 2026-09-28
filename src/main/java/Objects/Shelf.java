package Objects;

public class Shelf {
    private int shelfNumber = 0;
    private String shelfDesignation;

    public Shelf(int shelfNumber, String shelfDesignation){
        setShelfNumber(shelfNumber);
        setShelfDesignation(shelfDesignation);
    }

    //Setters ----------------------------------------------------

    public void setShelfDesignation(String shelfDesignation){
        this.shelfDesignation = shelfDesignation;
    }

    public void setShelfNumber(int number){
        if(number <= 999)
            this.shelfNumber = number;
    }

    //Getter -----------------------------------------------------

    public String getShelfDesignation(){
        return shelfDesignation;
    }

    public int getShelfNumber(){
        return shelfNumber;
    }

}