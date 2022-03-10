// Mateo Sam
// 400006967
import java.util.Scanner;
public class ZooKeeper 
{
    private String name;    //holds the name of the ZooKeeper
    public ZooKeeper()
    {
        
    }//Zookeeper constructor
    public void setName(String name) 
    {
        this.name = name;
    }   //setName
    public String getName() 
    {
        return name;
    }   //getName
    public void feedAnimals(Zoo theZoo) 
    {
        // Checks to ensure that animals are present in the zoo
        if(theZoo.getCages().size() == 0)
        {
            System.out.println("No animals present in the zoo");
            return;
        }

         // Checks to see if there are any alive animals present in the zoo
        int numOfAliveAnimals = 0;
        for(int i = 0;i<theZoo.getCages().size();i++)
        {
            // if the animal is alive then increment numOfAliveAnimals
            if(theZoo.getCages().get(i).getHealthStatus() != 0 )
            {
                numOfAliveAnimals++;
            }
        }
        // Returns from the function if there are no alive animals to feed
        if(numOfAliveAnimals==0)
        {
            System.out.println("No animals in the zoo are alive");
            return;
        }
        Scanner myScanner = new Scanner(System.in);
        for(int i = 0;i<theZoo.getCages().size();i++) 
        {
            //If the animal is dead then move on to the next animal
            if(theZoo.getCages().get(i).getHealthStatus() == 0) 
            {
                continue;
            }
            System.out.println("Enter the amount of food to feed the following animal: ");
            theZoo.getCages().get(i).showAnimal();
            int numberOfFood = 0;
            System.out.print("Amount of food: ");
            numberOfFood = myScanner.nextInt();

            // Does not allow the Zoo Keeper to enter negative food
            while(numberOfFood< 0)
            {
                System.out.println("Food cannot be less than 0: ");
                System.out.print("Amount of food: ");
                numberOfFood = myScanner.nextInt();
            }

            theZoo.getCages().get(i).eatFood(numberOfFood);
        }
    }   //feedAnimals

    public void healAnimals(Zoo theZoo)
    {
        // Checks to ensure that animals are present in the zoo
        if(theZoo.getCages().size() == 0)
        {
            System.out.println("No animals present in the zoo");
            return;
        }

        // Checks to see if there are any sick animals present in the zoo
        int numOfSickAnimals = 0;
        for(int i = 0;i<theZoo.getCages().size();i++)
        {
            // if the animal is sick and not dead then increment the number of sick animals
            if(theZoo.getCages().get(i).getHealthStatus() <8 && theZoo.getCages().get(i).getHealthStatus() != 0 )
            {
                numOfSickAnimals++;
            }
        }
        // Returns from the function if there are no sick animals to heal
        if(numOfSickAnimals==0)
        {
            System.out.println("No Sick animals present in the zoo");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        for(int i = 0;i<theZoo.getCages().size();i++) 
        {
            //If the animal is dead or healthy then move on to the next animal
            if(theZoo.getCages().get(i).getHealthStatus() == 0 || theZoo.getCages().get(i).getHealthStatus() >=8)
            {
                continue;
            }

            System.out.println("Enter the amount of medicine for the following animal: ");
            theZoo.getCages().get(i).showAnimal();
            int amountOfMedicine = 0;
            System.out.print("Amount of medicine: ");
            amountOfMedicine = scanner.nextInt();

            // Does not allow the Zoo Keeper to enter negative medicine
            while(amountOfMedicine < 0)
            {
                System.out.println("Medicine cannot be less than 0: ");
                System.out.print("Amount of medicine: ");
                amountOfMedicine= scanner.nextInt();
            }
            theZoo.getCages().get(i).takeMedicine(amountOfMedicine);
        }
    }   //healAnimals
}   //ZooKeeper
