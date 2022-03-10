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
        Animal obj = new Animal();
        return obj;
    }   //getAnimal
    
    public void removeAnimal(String cageID)
    {
    
    }   //removeAnimal

    public void printHungerReport()
    {

    }   //printHungerReport

    public void printHealthReport()
    {

    }   //printHealthReport
}   //Zoo
