// Mateo Sam
// 400006967
import java.util.ArrayList;
public class Zoo 
{
    private ArrayList<Animal> cages;    //holds an array list of the animals in the zoo

    public Zoo()
    {
        cages = new ArrayList<Animal>();
    }   //Zoo

    public void setCages(ArrayList<Animal> cages) 
    {
        this.cages = cages;
    }   //setCages

    public ArrayList<Animal> getCages() 
    {
        return cages;
    }   //getCages

    public void addAnimal(Animal newAnimal)
    {
        cages.add(newAnimal);
    }   //addAnimal

    public void showAnimals()
    {
        if(cages.size() == 0)
        {
            System.out.println("No animals present");
            return;
        }
        for(int i = 0; i < cages.size(); i++)
        {
            System.out.println("Animal # " +(i+1)+"/"+cages.size()); //Displays the position of the animal in the list of animals
            cages.get(i).showAnimal(); //Uses the showAnimal method from the Animal class
        }
    }   //showAnimal

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
    
    public void removeAnimal(String cageID)
    {
        for(int i = 0; i < cages.size(); i++)
        {
            if(cages.get(i).getCageID().equals(cageID))
            {
                cages.remove(i);
                return;
            }
        }
    }   //removeAnimal

    public void printHungerReport()
    {
        if(cages.size() == 0)
        {
            System.out.println("No animals present");
            return;
        }
        for(int i = 0; i < cages.size(); i++)
        {
            System.out.println("Animal # " +(i+1)+"/"+cages.size()); //Displays the position of the animal in the list of animals
            System.out.println("Name: "+cages.get(i).getName()); //Uses the getName method from the Animal class
            System.out.println("Species: "+cages.get(i).getSpecies()); //Uses the getSpecies method from the Animal class
            System.out.println("Hunger Status: "+cages.get(i).getHungerStatus()); //Uses the getHungerStatus method from the Animal class
        }

    }   //printHungerReport

    public void printHealthReport()
    {

    }   //printHealthReport
}   //Zoo
