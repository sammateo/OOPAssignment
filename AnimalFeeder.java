import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        try{
            FileWriter report = new FileWriter("FeedingList.txt");
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
            String month = sdf.format(new Date());
            sdf = new SimpleDateFormat("dd");
            String day = sdf.format(new Date());
            sdf = new SimpleDateFormat("yyyy");
            String year = sdf.format(new Date());
            //System.out.println("Feeding List - "+ day+ " " + month+ " "+ year);
            report.write("Feeding List - "+ day+ " " + month+ " "+ year);
            ArrayList<Meal> aMeals = new ArrayList<Meal>();
            ArrayList<Meal> bMeals = new ArrayList<Meal>();
            ArrayList<Meal> cMeals = new ArrayList<Meal>();
            ArrayList<Meal> dMeals = new ArrayList<Meal>();
            ArrayList<ArrayList<Meal>> totalMeals = new ArrayList<ArrayList<Meal>>();
        // Organize the Feeding List by Zone
        for(int i = 0; i <feedingList.size(); i++)
        {
            String[] cageLetter = feedingList.get(i).getCageID().split("-");
            if(cageLetter[0].equals("A"))
            {
                aMeals.add(feedingList.get(i));
            }
            else if(cageLetter[0].equals("B"))
            {
                bMeals.add(feedingList.get(i));
            }
            else if(cageLetter[0].equals("C"))
            {
                cMeals.add(feedingList.get(i));
            }
            else if(cageLetter[0].equals("D"))
            {
                dMeals.add(feedingList.get(i));
            }
            
        }
        // Add zoned lists to a total list of everything
        totalMeals.add(aMeals);
        totalMeals.add(bMeals);
        totalMeals.add(cMeals);
        totalMeals.add(dMeals);
        String[] zoneLabels = {"(A) African Savanna","(B) Amazonian Jungle","(C) Eurasian Wild","(D) Frozen Tundra"};
        for(int i = 0; i< totalMeals.size(); i++)
        {
            //System.out.println(zoneLabels[i]);
            report.write("\n"+"\n"+zoneLabels[i]);
            int[] foodSummary = {0,0,0,0,0}; //hay,fruit,grain,fish,meat
            String[] foodType = {"Hay","Fruit","Grain","Fish","Meat"};
            for(int j = 0; j< totalMeals.get(i).size();j++)
            {
                Animal tempAnimal = getAnimal(totalMeals.get(i).get(j).getCageID());
                //System.out.println(tempAnimal.getName()+ " "+tempAnimal.getSpecies()+" "+totalMeals.get(i).get(j).getFoodAmt() + " "+totalMeals.get(i).get(j).getFoodType() );
                report.write("\n"+tempAnimal.getName()+ " "+tempAnimal.getSpecies()+" "+totalMeals.get(i).get(j).getFoodAmt() + " "+totalMeals.get(i).get(j).getFoodType());
                for(int z = 0; z< foodType.length; z++)
                {
                    if(totalMeals.get(i).get(j).getFoodType().equalsIgnoreCase(foodType[z]))
                    {
                        foodSummary[z]+=totalMeals.get(i).get(j).getFoodAmt();
                    }
                }
            }
            //System.out.println("Food Summary");
            report.write("\n"+"\n" + "Food Summary");
            for(int x = 0; x < foodSummary.length;x++)
            {
                if(foodSummary[x] > 0)
                    {
                        //System.out.println(foodSummary[x]+" "+ foodType[x]);
                        report.write("\n"+foodSummary[x]+" "+ foodType[x]);
                    }
            }
           
        }
            report.close();
            // System.out.println("Report Printed");
        }//end try
        catch(IOException e)
        {
            System.out.println("Error");
        }//end catch
       
    }//printFeedingList

    public void simFeeding()
    {
        for(int i = 0; i <feedingList.size(); i++)
        {
            Animal tempAnimal = getAnimal(feedingList.get(i).getCageID());
            tempAnimal.eatFood(feedingList.get(i).getFoodAmt());
        }
    }//simFeeding

    public ArrayList<Meal> getFeedingList() {
        return feedingList;
    }
    public int getFeedingListSize() {
        return feedingList.size();
    }
    public void setFeedingList(ArrayList<Meal> feedingList) {
        this.feedingList = feedingList;
    }
    public Animal getAnimal(String cageID)
    {   
        for(int i = 0; i < cages.size(); i++)
        {
            if(cages.get(i).getCageID().equals(cageID))
            {
                return cages.get(i);
            }
        }
        return null;
    }   //getAnimal
    
    public String getFoodType(String cageID)
    {
        for(int i = 0; i < feedingList.size(); i++)
        {
            if(feedingList.get(i).getCageID().equals(cageID))
            {
                return feedingList.get(i).getFoodType();
            }
        }
        return null;
    }   //getFoodType

    public int getFoodAmt(String cageID)
    {
        for(int i = 0; i < feedingList.size(); i++)
        {
            if(feedingList.get(i).getCageID().equals(cageID))
            {
                return feedingList.get(i).getFoodAmt();
            }
        }
        return 0;
    }   //getFoodType

}//AnimalFeeder
