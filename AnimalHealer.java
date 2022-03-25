// Mateo Sam and Robali Sewitt
// 400006967 and 400007056

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AnimalHealer {
    private ArrayList<Prescription> healingList; //holds an array list of the animals in the zoo
    private ArrayList<Animal> cages;//holds an array list of the animals in the zoo
    private Prescription aPrescription;//Holds a new meal to be added to the HealingList
    public AnimalHealer(ArrayList<Animal> cages)
    {
        healingList = new ArrayList<Prescription>();
        this.cages = cages;
    }//AnimalHealer constructor

    public void addPrescription()
    {
        healingList.add(aPrescription);
    }//addPrescription
    
    public void createPrescription(String cageID,String medType, int unitsOfMed)
    {
        aPrescription = new Prescription();
        aPrescription.setCageID(cageID);
        aPrescription.setUnitsOfMed(unitsOfMed);	
        aPrescription.setMedType(medType);
    }//addPrescription

    //This function puts together and writes the contents of the healing list to a text file
    public void printHealingList()
    {
        try
        {
            FileWriter report = new FileWriter("HealingList.txt");
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM");//Gets the present date
            String month = sdf.format(new Date());
            sdf = new SimpleDateFormat("dd");
            String day = sdf.format(new Date());
            sdf = new SimpleDateFormat("yyyy");
            String year = sdf.format(new Date());
            report.write("Healing List - "+ day+ " " + month+ " "+ year);

            //ArrayLists of prescription to store prescriptions by the different zones
            ArrayList<Prescription> aPrescriptions = new ArrayList<Prescription>();
            ArrayList<Prescription> bPrescriptions = new ArrayList<Prescription>();
            ArrayList<Prescription> cPrescriptions = new ArrayList<Prescription>();
            ArrayList<Prescription> dPrescriptions = new ArrayList<Prescription>();
            ArrayList<ArrayList<Prescription>> totalPrescriptions = new ArrayList<ArrayList<Prescription>>();//This ArrayList stores the contents of each ArrayList by their respective zones

            // Organize the Healing List by Zone
            for(int i = 0; i <healingList.size(); i++)
            {
                String[] cageLetter = healingList.get(i).getCageID().split("-");
                if(cageLetter[0].equals("A"))
                {
                    aPrescriptions.add(healingList.get(i));
                }
                else if(cageLetter[0].equals("B"))
                {
                    bPrescriptions.add(healingList.get(i));
                }
                else if(cageLetter[0].equals("C"))
                {
                    cPrescriptions.add(healingList.get(i));
                }
                else if(cageLetter[0].equals("D"))
                {
                    dPrescriptions.add(healingList.get(i));
                }
                
            }//endfor
            // Add zoned lists to a total list of everything
            totalPrescriptions.add(aPrescriptions);
            totalPrescriptions.add(bPrescriptions);
            totalPrescriptions.add(cPrescriptions);
            totalPrescriptions.add(dPrescriptions);
            String[] zoneLabels = {"(A) African Savanna","(B) Amazonian Jungle","(C) Eurasian Wild","(D) Frozen Tundra"};
            int[] medSummary = {0,0,0}; //herbicine, omnicine,carnicine
            String[] medType = {"Herbicine", "Omnicine", "Carnicine"};
            for(int i = 0; i< totalPrescriptions.size(); i++)
            {
                report.write("\n"+"\n"+zoneLabels[i]);
                if(totalPrescriptions.get(i).size() == 0)
                {
                    report.write("\n[No medical attention required]");
                }
                for(int j = 0; j< totalPrescriptions.get(i).size();j++)
                {
                    Animal tempAnimal = getAnimal(totalPrescriptions.get(i).get(j).getCageID());
                    report.write("\n"+tempAnimal.getName()+ " "+tempAnimal.getSpecies()+" (health: "+tempAnimal.getHealthStatus()+") needs "+totalPrescriptions.get(i).get(j).getUnitsOfMed() + " units of "+totalPrescriptions.get(i).get(j).getMedType());

                    //Populates the heal summary array with the amount of medicine added
                    for(int z = 0; z< medType.length; z++)
                    {
                        if(totalPrescriptions.get(i).get(j).getMedType().equalsIgnoreCase(medType[z]))
                        {
                            medSummary[z]+=totalPrescriptions.get(i).get(j).getUnitsOfMed();
                        }
                    }//endfor
                }//endfor
                
                
            }//endfor

            int totalMedicine = 0;

            //Writing the information of the Heal summary to the text file
            for(int x = 0; x < medSummary.length;x++)
                {
                    totalMedicine+=medSummary[x];
                }//endfor
            report.write("\n"+"\n" + "Summary of Medicine: "+ totalMedicine+ " units");
                
                report.close();
        
        }//end try
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }//end catch
    }//printHealingList

    //this function heals every animal in the healing list 
    public void simHealing()
    {
        for(int i = 0; i <healingList.size(); i++)
        {
            Animal tempAnimal = getAnimal(healingList.get(i).getCageID());
            try 
            {
                tempAnimal.takeMedicine(healingList.get(i).getUnitsOfMed());

            } catch (OverdosingException e) 
            {
                System.out.println(e);
            }
        }//endfor
    }//simHealing

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

    //This function returns the medicine type for the corresponding cage ID
    public String getMedType(String cageID)
    {
        for(int i = 0; i < healingList.size(); i++)
        {
            if(healingList.get(i).getCageID().equals(cageID))
            {
                return healingList.get(i).getMedType();
            }
        }//endfor
        return null;
    }   //getFoodType

    //This function returns the amount of the medicine type entered to the cage ID
    public int getUnitsOfMed(String cageID)
    {
        for(int i = 0; i < healingList.size(); i++)
        {
            if(healingList.get(i).getCageID().equals(cageID))
            {
                return healingList.get(i).getUnitsOfMed();
            }
        }//endfor
        return 0;
    }   //getUnitsOfMed

    public ArrayList<Prescription> getHealingList()
    {
        return healingList;
    }//getHealingList

    public int getHealingListSize()
    {
        return healingList.size();
    }//getHealingListSize

    public void setHealingList(ArrayList<Prescription> healingList)
    {
        this.healingList = healingList;
    }//setHealingList

}//class AnimalHealer
