public class Prescription {
    private String cageID;
    private String medType;
    private int unitsOfMed;

    public void setcageID(String x)
    {
        cageID = x;
    }//setcageID

    public String getcageID()
    {
        return cageID;
    }//getcageID

    public void setmedType(String x)
    {
        medType = x;
    }//setmedType

    public String getmedType()
    {
        return medType;
    }//getmedType

    public void setUnits_med(int x)
    {
        unitsOfMed = x;
    }//setUnits_med

    public int getUnits_med()
    {
        return unitsOfMed;
    }//getUnits_med

    public Prescription()
    {

    }//Prescription constructor
}//Prescription
