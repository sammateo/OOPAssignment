// Mateo Sam and Robali Sewitt
// 400006967 and 400007056

public class Meal {
    private String cageID;
    private String foodType;
    private int foodAmt;
    public Meal()
    {
        cageID = null;
        foodType = null;
        foodAmt = 0;
    }   //Meal

    public String getCageID() 
    {
        return cageID;
    }//getcageID

    public void setCageID(String cageID) 
    {
        this.cageID = cageID;
    }//setCageID

    public int getFoodAmt() 
    {
        return foodAmt;
    }//getFoodAmt
    public void setFoodAmt(int foodAmt) 
    {
        this.foodAmt = foodAmt;
    }//setFoodAmt

    public String getFoodType() 
    {
        return foodType;
    }//getFoodType

    public void setFoodType(String foodType) 
    {
        this.foodType = foodType;
    }//setFoodType

}//Meal
