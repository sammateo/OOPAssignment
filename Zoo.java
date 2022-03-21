// Mateo Sam
// 400006967
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

public class Zoo 
{
    private ArrayList<Animal> cages;    //holds an array list of the animals in the zoo

    public Zoo()
    {
        cages = new ArrayList<Animal>();
    }   //Zoo

    public void readAnimals() throws IOException
    {
        File animalFile = new File("animals.txt"); 
        Scanner readFile = new Scanner(animalFile);
        while (readFile.hasNext()) //read data from file & place into the list
        {
            Animal tempAnimal = new Animal();
            String animalRecord = readFile.nextLine();
            String[] animalArray = animalRecord.split(" ");
            String tempSpecies = animalArray[2];
            String[] tempSpeciesArray = tempSpecies.split("-");
            String species = "";
            for(int i=0;i<tempSpeciesArray.length;i++)
            {
                species+=tempSpeciesArray[i]+" ";
            }
            tempAnimal.setCageID(animalArray[0]);
            tempAnimal.setName(animalArray[1]);
            tempAnimal.setSpecies(species);
            tempAnimal.setAge(Integer.parseInt(animalArray[3]));
            tempAnimal.setHungerStatus(Integer.parseInt(animalArray[4]));
            tempAnimal.setHealthStatus(Integer.parseInt(animalArray[5]));
            tempAnimal.setCategory(animalArray[6]);
            // for(int x = 0;x<cages.size();x++)
            // {
            //     if(cages.get(x).getName().equals(animalArray[1]) && cages.get(x).getSpecies().equals(species))
            //     {
            //         String newName = JOptionPane.showInputDialog(null, "Welcome to the Cave Hill Zoo Manager System \nEnter your name","Welcome", JOptionPane.INFORMATION_MESSAGE);

            //     }
            // }
            cages.add(tempAnimal);
        } //while

    }

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
