// Mateo Sam and Robali Sewitt
// 400006967 and 400007056

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;

public class AnimalFeeder 
{
    private ArrayList<Meal> feedingList;    //holds an array list of the animals in the zoo
    private ArrayList<Animal> cages;    //holds an array list of the animals in the zoo
    private Meal aMeal; //Holds a new meal to be added to the FeedingList

    public AnimalFeeder(ArrayList<Animal> cages)
    {
        feedingList = new ArrayList<Meal>();
        this.cages = cages;
    }//AnimalFeeder

    public void addMeal()
    {
        feedingList.add(aMeal);
    }//addMeal

    public void createMeal(String cageID,String foodType, int foodAmt)
    {
        aMeal = new Meal();
        aMeal.setCageID(cageID);
        aMeal.setFoodAmt(foodAmt);
        aMeal.setFoodType(foodType);
    }//createMeal

    //This function puts together and writes the contents of the feeding list to a text file
    public void printFeedingList()
    {
        try{
            FileWriter report = new FileWriter("FeedingList.txt");
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM");//To get the present month
            String month = sdf.format(new Date());
            sdf = new SimpleDateFormat("dd");
            String day = sdf.format(new Date());
            sdf = new SimpleDateFormat("yyyy");
            String year = sdf.format(new Date());

            report.write("Feeding List - "+ day+ " " + month+ " "+ year);

            //ArrayLists of meals to store meals by the different zones
            ArrayList<Meal> aMeals = new ArrayList<Meal>();
            ArrayList<Meal> bMeals = new ArrayList<Meal>();
            ArrayList<Meal> cMeals = new ArrayList<Meal>();
            ArrayList<Meal> dMeals = new ArrayList<Meal>();
            ArrayList<ArrayList<Meal>> totalMeals = new ArrayList<ArrayList<Meal>>();//This ArrayList stores the contents of each ArrayList by their respective zones

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
            
        }//endfor

        // Add zoned lists to a total list of everything
        totalMeals.add(aMeals);
        totalMeals.add(bMeals);
        totalMeals.add(cMeals);
        totalMeals.add(dMeals);
        String[] zoneLabels = {"(A) African Savanna","(B) Amazonian Jungle","(C) Eurasian Wild","(D) Frozen Tundra"};
        for(int i = 0; i< totalMeals.size(); i++)
        {
            report.write("\n"+"\n"+zoneLabels[i]);
            int[] foodSummary = {0,0,0,0,0}; //hay,fruit,grain,fish,meat
            String[] foodType = {"Hay","Fruit","Grain","Fish","Meat"};
            for(int j = 0; j< totalMeals.get(i).size();j++)
            {
                Animal tempAnimal = getAnimal(totalMeals.get(i).get(j).getCageID());
                report.write("\n"+tempAnimal.getName()+ " "+tempAnimal.getSpecies()+" "+totalMeals.get(i).get(j).getFoodAmt() + " "+totalMeals.get(i).get(j).getFoodType());

                //Populates the food summary array with the amount of food added
                for(int z = 0; z< foodType.length; z++)
                {
                    if(totalMeals.get(i).get(j).getFoodType().equalsIgnoreCase(foodType[z]))
                    {
                        foodSummary[z]+=totalMeals.get(i).get(j).getFoodAmt();
                    }
                }//endfor
            }//endfor
            
            report.write("\n"+"\n" + "Food Summary");
            //Writing the information of the food summary to the text file
            for(int x = 0; x < foodSummary.length;x++)
            {
                if(foodSummary[x] > 0)
                    {
                        report.write("\n"+foodSummary[x]+" "+ foodType[x]);
                    }
            }//endfor
        }//endfor
            report.close();
        
        }//end try
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }//end catch
    }//printFeedingList
    
    //This function feeds all the animals in the feeding list
    public void simFeeding()
    {
        for(int i = 0; i <feedingList.size(); i++)
        {
            Animal tempAnimal = getAnimal(feedingList.get(i).getCageID());
            try {
                tempAnimal.eatFood(feedingList.get(i).getFoodAmt());
            } catch (OverFeedingException e) {
                System.out.println(e);
            }
            
        }//endfor
    }//simFeeding

    public ArrayList<Meal> getFeedingList() {
        return feedingList;
    }//getFeedingList

    public int getFeedingListSize() {
        return feedingList.size();
    }//getFeedingListSize

    public void setFeedingList(ArrayList<Meal> feedingList) {
        this.feedingList = feedingList;
    }//setFeedingList

    //This function returns the animal at the corresponding cage ID
    public Animal getAnimal(String cageID)
    {   
        for(int i = 0; i < cages.size(); i++)
        {
            if(cages.get(i).getCageID().equals(cageID))
            {
                return cages.get(i);
            }
        }//endfor
        return null;
    }   //getAnimal
    
    //This function returns the food type for the corresponding cage ID
    public String getFoodType(String cageID)
    {
        for(int i = 0; i < feedingList.size(); i++)
        {
            if(feedingList.get(i).getCageID().equals(cageID))
            {
                return feedingList.get(i).getFoodType();
            }
        }//endfor
        return null;
    }   //getFoodType

    //This function returns the amount of the food type entered to the cage ID
    public int getFoodAmt(String cageID)
    {
        for(int i = 0; i < feedingList.size(); i++)
        {
            if(feedingList.get(i).getCageID().equals(cageID))
            {
                return feedingList.get(i).getFoodAmt();
            }
        }//endfor
        return 0;
    }   //getFoodType

}//AnimalFeeder
