import java.util.ArrayList;
public class AnimalFeeder 
{
    private ArrayList<Meal> feedingList;    //holds an array list of the animals in the zoo
    private ArrayList<Animal> cages;    //holds an array list of the animals in the zoo
    public AnimalFeeder(ArrayList<Animal> cages)
    {
        feedingList = new ArrayList<Meal>();
        this.cages = cages;
    }//AnimalFeeder

    public void addMeal(String cageID,String foodType, int foodAmt)
    {
        Meal newMeal = new Meal();
        newMeal.setCageID(cageID);
        newMeal.setFoodAmt(foodAmt);
        newMeal.setFoodType(foodType);
        feedingList.add(newMeal);

    }//addMeal

    public void printFeedingList()
    {

    }//printFeedingList

    public void simFeeding()
    {
        
    }//simFeeding
    
}//AnimalFeeder
