package Objects;
import com.example.ass1data.HelperMethods;

public  class FloorArea {
    private String floorAreaTitle = "Untitled";
    private int level = 0;

    public FloorArea(String title, int level){
        setFloorAreaTitle(title);
        setLevel(level);
    }
    //----------------SETTERS--------------------
    public void setFloorAreaTitle(String title){

        this.floorAreaTitle = HelperMethods.truncateString(title, 30);
    }

    public void setLevel(int level){
        if(level <= 20)
            this.level = level;
    }
    //----------------GETTERS--------------------
    public String getFloorAreaTitle(){
        return floorAreaTitle;
    }

    public int getLevel(){
        return level;
    }
}