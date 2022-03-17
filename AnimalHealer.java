import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AnimalHealer {
    private ArrayList<Prescription> healingList;
    private ArrayList<Animal> cages;

    public AnimalHealer(ArrayList<Animal> cages)
    {
        healingList = new ArrayList<Prescription>();
        this.cages = cages;
    }//AnimalHealer constructor

    public void addPrescription(String cageID,String medType, int unitsOfMed)
    {
        Prescription newPrescription = new Prescription();
        newPrescription.setCageID(cageID);
        newPrescription.setUnitsOfMed(unitsOfMed);	
        newPrescription.setMedType(medType);
        healingList.add(newPrescription);
    }//addPrescription

    public void printHealingList()
    {
        try{
            FileWriter report = new FileWriter("HealingList.txt");
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
            String month = sdf.format(new Date());
            sdf = new SimpleDateFormat("dd");
            String day = sdf.format(new Date());
            sdf = new SimpleDateFormat("yyyy");
            String year = sdf.format(new Date());
            report.write("Healing List - "+ day+ " " + month+ " "+ year);
            ArrayList<Prescription> aPrescriptions = new ArrayList<Prescription>();
            ArrayList<Prescription> bPrescriptions = new ArrayList<Prescription>();
            ArrayList<Prescription> cPrescriptions = new ArrayList<Prescription>();
            ArrayList<Prescription> dPrescriptions = new ArrayList<Prescription>();
            ArrayList<ArrayList<Prescription>> totalPrescriptions = new ArrayList<ArrayList<Prescription>>();
        // Organize the Feeding List by Zone
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
            
        }
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
            //System.out.println(zoneLabels[i]);
            report.write("\n"+"\n"+zoneLabels[i]);
            if(totalPrescriptions.get(i).size() == 0)
            {
                report.write("\n[No medical attention required]");
            }
            for(int j = 0; j< totalPrescriptions.get(i).size();j++)
            {
                Animal tempAnimal = getAnimal(totalPrescriptions.get(i).get(j).getCageID());
                //System.out.println(tempAnimal.getName()+ " "+tempAnimal.getSpecies()+" "+totalPrescriptions.get(i).get(j).getFoodAmt() + " "+totalPrescriptions.get(i).get(j).getFoodType() );
                report.write("\n"+tempAnimal.getName()+ " "+tempAnimal.getSpecies()+" (health: "+tempAnimal.getHealthStatus()+") needs "+totalPrescriptions.get(i).get(j).getUnitsOfMed() + " units of "+totalPrescriptions.get(i).get(j).getMedType());
                for(int z = 0; z< medType.length; z++)
                {
                    if(totalPrescriptions.get(i).get(j).getMedType().equalsIgnoreCase(medType[z]))
                    {
                        medSummary[z]+=totalPrescriptions.get(i).get(j).getUnitsOfMed();
                    }
                }
            }
            //System.out.println("Food Summary");
            
        }
        int totalMedicine = 0;
        for(int x = 0; x < medSummary.length;x++)
            {
                totalMedicine+=medSummary[x];
            }
        report.write("\n"+"\n" + "Summary of Medicine: "+ totalMedicine+ " units");
            
            report.close();
            // System.out.println("Report Printed");
        }//end try
        catch(IOException e)
        {
            System.out.println("Error");
        }//end catch
    }//printHealingList

    public void simHealing()
    {
        for(int i = 0; i <healingList.size(); i++)
        {
            Animal tempAnimal = getAnimal(healingList.get(i).getCageID());
            tempAnimal.takeMedicine(healingList.get(i).getUnitsOfMed());
        }
    }//simHealing

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

    public String getMedType(String cageID)
    {
        for(int i = 0; i < healingList.size(); i++)
        {
            if(healingList.get(i).getCageID().equals(cageID))
            {
                return healingList.get(i).getMedType();
            }
        }
        return null;
    }   //getFoodType

    public int getUnitsOfMed(String cageID)
    {
        for(int i = 0; i < healingList.size(); i++)
        {
            if(healingList.get(i).getCageID().equals(cageID))
            {
                return healingList.get(i).getUnitsOfMed();
            }
        }
        return 0;
    }   //getUnitsOfMed

    public ArrayList<Prescription> getHealingList() {
        return healingList;
    }
    public int getHealingListSize() {
        return healingList.size();
    }
    public void setHealingList(ArrayList<Prescription> healingList) {
        this.healingList = healingList;
    }
}//class AnimalHealer
