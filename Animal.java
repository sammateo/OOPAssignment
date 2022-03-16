// Mateo Sam
// 400006967
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
    protected String category;
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
    }
    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }
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
    }
    public String getCageID() {
        return cageID;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void eatFood(int amount) 
    {
        this.hungerStatus += amount;
        // Record the causeOfDeath of the animal
        if(this.hungerStatus>5)
        {
            this.causeOfDeath = "Overfeeding";
        }
        else if(this.hungerStatus<=0)
        {
            this.causeOfDeath = "Starvation";
        }

        // Record the animal as being dead and print the notification to the Zoo Keeper
        // if(this.hungerStatus>5 || this.hungerStatus <=0)
        // {
        //     this.healthStatus = 0;
        //     this.hungerStatus = 0;
        //     // System.out.println("The " + this.species+" named "+ this.name+" has died of "+ this.causeOfDeath);
        // }
    }   //eatFood

    public void takeMedicine(int amount) 
    {
        this.healthStatus += amount;
        // Record the causeOfDeath of the animal
        if(this.healthStatus> 10){
            this.causeOfDeath = "Overdosing";
        }
        else if (this.healthStatus <= 0)
        {
            this.causeOfDeath = "Died of under medication";
        }
        
        // Record the animal as being dead and print the notification to the Zoo Keeper
        // if(this.healthStatus> 10 || this.healthStatus<=0)
        // {
        //     this.healthStatus = 0;
        //     this.hungerStatus = 0;
        //     // System.out.println("The " + this.species+" named "+ this.name+" has died of "+ this.causeOfDeath);

        // }
    }   //takeMedicine

    public void speak()
    {
        System.out.println("make noise");
    }   //speak

    public void showAnimal()
    {
        System.out.println("Cage ID: "+ cageID);
        System.out.println("Category: "+ category);
        System.out.println("Species: "+species);
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
        System.out.println("Hunger Status: "+hungerStatus);
        System.out.println("Health Status: "+healthStatus);
        // Displays The Cause of Death of the animal if it is dead
        if(this.healthStatus == 0)
        {
            System.out.println("This animal is dead");
            System.out.println("Cause of death: "+this.causeOfDeath);
        }
        System.out.println("---------------------------------");

    }   //showAnimal
}   //Animal