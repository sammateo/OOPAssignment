public class Prescription {
    private String cageID;
    private String medType;
    private int unitsOfMed;

    public Prescription()
    {
        cageID = null;
        medType = null;
        unitsOfMed = 0;
    }//Prescription constructor
    public void setCageID(String x)
    {
        cageID = x;
    }//setcageID

    public String getCageID()
    {
        return cageID;
    }//getcageID

    public void setMedType(String x)
    {
        medType = x;
    }//setmedType

    public String getMedType()
    {
        return medType;
    }//getmedType

    public void setUnitsOfMed(int x)
    {
        unitsOfMed = x;
    }//setUnits_med

    public int getUnitsOfMed()
    {
        return unitsOfMed;
    }//getUnits_med

    
}//Prescription
