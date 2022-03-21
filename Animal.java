// Mateo Sam and Robali Sewitt
// 400006967 and 400007056
import java.util.Random;

public class Animal
{
    protected String name;    //holds the name of the animal
    protected String species; //holds the species of the animal
    protected String causeOfDeath; //A string to hold the cause of death of an animal
    protected int age;    //holds the age of the animal
    protected int hungerStatus;   //holds the hungry status of the animal
    protected int healthStatus;   //holds the health status of the animal
    protected final String defaultString = null;  //holds the default string value
    protected final int defaultInt = 0;   //holds the default integer value
    protected Random generator;   //holds the random generator
    protected String category;//holds the type of animal, for eg. Carnivore
    protected String cageID;


    public Animal()
    {
        species = defaultString;
        name=defaultString; 
        causeOfDeath =defaultString;
        age=defaultInt;
        generator = new Random();   //initializes the random generator
        hungerStatus = generator.nextInt(5)+1; //Generate a random integer from 1 to 5
        healthStatus = generator.nextInt(10)+1; //Generate a random integer from 1 to 10
    }   //Animal

    public String getCauseOfDeath() {
        return causeOfDeath;
    }//getCauseOfDeath
    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }//setCauseOfDeath
    public void setSpecies(String species) 
    {
        this.species = species;
    }   //setSpecies

    public String getSpecies() 
    {
        return species;
    }   //getSpecies

    public void setName(String name) 
    {
        this.name = name;
    }   //setName
    public String getName() 
    {
        return name;
    }   //getName

    public void setAge(int age) 
    {
        this.age = age;
    }   //setAge

    public int getAge() 
    {
        return age;
    }   //getAge

    public void setHungerStatus(int hungerStatus) 
    {
        this.hungerStatus = hungerStatus;
    }   //setHungerStatus
    public int getHungerStatus() 
    {
        return hungerStatus;
    }   //setHungerStatus

    public void setHealthStatus(int healthStatus) 
    {
        this.healthStatus = healthStatus;
    }   //setHealthStatus
    public int getHealthStatus() 
    {
        return healthStatus;
    }   //getHealthStatus

    public void setCageID(String cageID) {
        this.cageID = cageID;
    }//setCageID
    public String getCageID() {
        return cageID;
    }//getCageID

    public void setCategory(String category) {
        this.category = category;
    }//setCategory

    public String getCategory() {
        return category;
    }//getCategory

    public void eatFood(int amount) throws OverFeedingException
    {
        this.hungerStatus += amount;
        // Record the causeOfDeath of the animal
        if(this.hungerStatus>5)
        {
                this.causeOfDeath = "Overfeeding";

            throw new OverFeedingException();
        }
        else if(this.hungerStatus<=0)
        {
            this.causeOfDeath = "Starvation";
        }

    }   //eatFood

    public void takeMedicine(int amount) throws OverdosingException
    {
        this.healthStatus += amount;
        // Record the causeOfDeath of the animal
        if(this.healthStatus> 10){
            this.causeOfDeath = "Overdosing";
            throw new OverdosingException();
        }
        else if (this.healthStatus <= 0)
        {
            this.causeOfDeath = "Died of under medication";
        }
    }   //takeMedicine

    public void speak()
    {
        System.out.println("make noise");
    }   //speak

}   //Animal