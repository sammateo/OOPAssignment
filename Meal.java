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
    }

    public void setCageID(String cageID) 
    {
        this.cageID = cageID;
    }

    public int getFoodAmt() 
    {
        return foodAmt;
    }
    public void setFoodAmt(int foodAmt) 
    {
        this.foodAmt = foodAmt;
    }

    public String getFoodType() 
    {
        return foodType;
    }

    public void setFoodType(String foodType) 
    {
        this.foodType = foodType;
    }

}
