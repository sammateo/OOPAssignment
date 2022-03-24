// Mateo Sam and Robali Sewitt
// 400006967 and 400007056
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Zoo 
{
    private ArrayList<Animal> cages;    //holds an array list of the animals in the zoo
    private AnimalFeeder animalFeeder;
    private AnimalHealer animalHealer;
    private JButton nextButton;
    private JPanel animalPanel;
    private int position;
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
            int sameName;
            String newName = animalArray[1];

            //This loop checks to make sure that if two animals with the same name exist, that it notifies the user and enables them to set a new name
            do{
                sameName = 0;
                for(int x = 0;x<cages.size();x++)
                {

                    if(cages.get(x).getName().equals(newName) && cages.get(x).getSpecies().equals(species))
                    {
                        sameName++;
                        newName= JOptionPane.showInputDialog(null, "An animal of the species "+species+ " with the name " + animalArray[1] + " already exists.\nEnter another name","Rename animal", JOptionPane.INFORMATION_MESSAGE);
                        while (newName == null ||newName.trim().isEmpty())
                        {
                            JOptionPane.showMessageDialog(null, "The name you entered is invalid");
                            newName = JOptionPane.showInputDialog(null, "Enter a new name");
                            
                        }
                        tempAnimal.setName(newName);
                    }
                }
            } while(sameName!=0);
            
            cages.add(tempAnimal);
        } //while

    }//readAnimals

    public void setCages(ArrayList<Animal> cages) 
    {
        this.cages = cages;
    }   //setCages

    public ArrayList<Animal> getCages() 
    {
        return cages;
    }   //getCages

    public void addAnimal(Animal a)
    {
        cages.add(a);
    }   //addAnimal
    public void setPosition(int newPosition)
    {
        position = newPosition;
    }//setPosition
    public void setAnimalPanel(JPanel newAnimalPanel)
    {
        animalPanel = newAnimalPanel;
    }

    public void setNextButton(JButton newNextButton)
    {
        nextButton = newNextButton;
    }
    public void showAnimals()
    {
        JPanel info = new JPanel();
        info.setLayout(new GridLayout(6,2));
        JPanel nxtContainer = new JPanel();
        nxtContainer.setLayout(new FlowLayout());
        info.setPreferredSize(new Dimension(200, 200));
        int i = position;
        JLabel animalIDLabel = new JLabel("Cage ID: " );
        JLabel animalNameLabel = new JLabel("Name: ");
        JLabel animalSpeciesLabel = new JLabel("Species: ");
        JLabel animalTypeLabel = new JLabel("Category: ");
        JLabel animalHungerLabel = new JLabel("Hunger: ");
        JLabel animalHealthLabel = new JLabel("Health: ");
        
        JLabel animalID = new JLabel();
        JLabel animalName = new JLabel();
        JLabel animalSpecies = new JLabel();
        JLabel animalType = new JLabel();
        JLabel animalHunger = new JLabel();
        JLabel animalHealth = new JLabel();
        
        animalID.setText( cages.get(i).getCageID());
        animalName.setText(cages.get(i).getName());
        animalSpecies.setText(cages.get(i).getSpecies());
        animalType.setText(cages.get(i).getCategory());
        animalHunger.setText(cages.get(i).getHungerStatus()+"/5");
        animalHealth.setText(cages.get(i).getHealthStatus()+"/10");
        if(cages.get(i).getHealthStatus()< 8)
        {
            animalHealth.setForeground(Color.RED);
        }
        else{
            animalHealth.setForeground(Color.BLACK);
        }
        info.add(animalIDLabel);
        info.add(animalID);
        info.add(animalNameLabel); 
        info.add(animalName); 
        info.add(animalSpeciesLabel);
        info.add(animalSpecies);
        info.add(animalTypeLabel);
        info.add(animalType);
        info.add(animalHungerLabel);
        info.add(animalHunger);
        info.add(animalHealthLabel);
        info.add(animalHealth);
        info.setBorder(BorderFactory.createTitledBorder("Animal"));
        ImageIcon zoneImage;
        String imgPath = "";
        if(cages.get(i).getCageID().split("-")[0].equals("A"))
        {
            imgPath = "iconZoneA-savannah.png";
        }
        if(cages.get(i).getCageID().split("-")[0].equals("B"))
        {
            imgPath = "iconZoneB-amazonia.png";
        }
        if(cages.get(i).getCageID().split("-")[0].equals("C"))
        {
            imgPath = "iconZoneC-eurasia.png";
        }
        if(cages.get(i).getCageID().split("-")[0].equals("D"))
        {
            imgPath = "iconZoneD-tundra.png";
        }
        zoneImage = new ImageIcon(imgPath);
        JLabel zoneIcon = new JLabel(new ImageIcon(zoneImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        nxtContainer.add(zoneIcon);
        nxtContainer.add(nextButton);

        animalPanel.add(info);
        animalPanel.add(nxtContainer);
        
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
    public String getDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        String month = sdf.format(new Date());
        sdf = new SimpleDateFormat("dd");
        String day = sdf.format(new Date());
        sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(new Date());
        return(day+ " "+ month+" "+ year);
    }   //getDate
    public void setAnimalFeeder(AnimalFeeder newFeeder)
    {
        animalFeeder = newFeeder;
    }   //setAnimalFeeder
    public void setAnimalHealer(AnimalHealer newHealer)
    {
        animalHealer = newHealer;
    }   //setAnimalHealer
    public void printHungerReport()
    {
        try
        {
            FileWriter report = new FileWriter("FeedingReport.txt");
            String date2 = getDate();
            report.write(date2);

            String animalsFed2 = "AnimalsFed: "+ animalFeeder.getFeedingListSize();
            report.write("\n"+"\n"+animalsFed2);

            ArrayList<Animal> deadAnimals = new ArrayList<Animal>();
            for(int i=0; i<animalFeeder.getFeedingListSize();i++)
            {
                Animal tempFedAnimal = animalFeeder.getAnimal(animalFeeder.getFeedingList().get(i).getCageID());
                if(tempFedAnimal.getHungerStatus() > 5)
                {
                    deadAnimals.add(tempFedAnimal);
                }
            }

            String okAnimals2 = "OK: "+(animalFeeder.getFeedingListSize() - deadAnimals.size());
            report.write("\n"+okAnimals2);

            String deathAnimals2 = "Deaths: "+ deadAnimals.size();
            report.write("\n"+deathAnimals2);

            for(int i=0;i<deadAnimals.size(); i++)
            {
                String deadAnimalInfo2 = deadAnimals.get(i).getCageID()+" "+ deadAnimals.get(i).getName()+ " "+ deadAnimals.get(i).getSpecies()+" Original Hunger Status: "+ (deadAnimals.get(i).hungerStatus - animalFeeder.getFoodAmt(deadAnimals.get(i).getCageID()))+" Food Amount: "+ animalFeeder.getFoodAmt(deadAnimals.get(i).getCageID())+ " Food Type: "+ animalFeeder.getFoodType(deadAnimals.get(i).getCageID());
                report.write("\n"+"\n"+ deadAnimalInfo2); 
            }
            System.out.println("Report Printed");
            report.close();

        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }   //printHungerReport

    public void printHealthReport()
    {
        try
        {
            FileWriter report = new FileWriter("HealingReport.txt");
            String date2 = getDate();
            report.write(date2);

            String animalsHealed2 = "Animals Medicated: "+ animalHealer.getHealingListSize();
            report.write("\n"+"\n"+animalsHealed2);

            ArrayList<Animal> deadAnimals = new ArrayList<Animal>();
            for(int i=0; i<animalHealer.getHealingListSize();i++)
            {
                Animal tempHealedAnimal = animalHealer.getAnimal(animalHealer.getHealingList().get(i).getCageID());
                if(tempHealedAnimal.getHealthStatus() > 10)
                {
                    deadAnimals.add(tempHealedAnimal);
                }
            }

            String okAnimals2 = "OK: "+(animalHealer.getHealingListSize() - deadAnimals.size());
            report.write("\n"+okAnimals2);

            String deathAnimals2 = "Deaths: "+ deadAnimals.size();
            report.write("\n"+deathAnimals2);

            for(int i=0;i<deadAnimals.size(); i++)
            {
                String deadAnimalInfo2 = deadAnimals.get(i).getCageID()+" "+ deadAnimals.get(i).getName()+ " "+ deadAnimals.get(i).getSpecies()+" Original Health Status: "+ (deadAnimals.get(i).healthStatus - animalHealer.getUnitsOfMed(deadAnimals.get(i).getCageID()))+" Medicine Amount: "+ animalHealer.getUnitsOfMed(deadAnimals.get(i).getCageID())+ " Medicine Type: "+ animalHealer.getMedType(deadAnimals.get(i).getCageID());
                report.write("\n"+"\n"+ deadAnimalInfo2); 
            }
            System.out.println("Report Printed");
            report.close();

        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }   //printHealthReport
}   //Zoo
