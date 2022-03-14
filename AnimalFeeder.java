import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
public class AnimalFeeder 
{
    // public static void main(String[] args)
    // {
    //     ArrayList<Animal> cages = new ArrayList<Animal>();
    //     cages.add(new Animal());
    //     AnimalFeeder f = new AnimalFeeder(cages);
    //     f.printFeedingList();

    // }
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
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        String month = sdf.format(new Date());
        sdf = new SimpleDateFormat("dd");
        String day = sdf.format(new Date());
        sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(new Date());
        System.out.println("Feeding List - "+ day+ " " + month+ " "+ year);
        for(int i = 0; i <feedingList.size(); i++)
        {
            System.out.println("");
        }
    }//printFeedingList

    public void simFeeding()
    {
        
    }//simFeeding
    
}//AnimalFeeder
