// Mateo Sam and Robali Sewitt
// 400006967 and 400007056
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class ZooManager extends JFrame implements ActionListener
{   
    private AnimalFeeder animalFeeder;//creates the reference to an AnimalFeeder object
    private AnimalHealer animalHealer;//creates the reference to an AnimalHealer object
    private Zoo theZoo;//creates the reference to a Zoo object
    private ZooKeeper zooKeeper; //creates the reference to a ZooKeeper object
    private JPanel animalPanel;//creates JPanel reference to the animalPanel
    private JPanel welcomePanel;//creates JPanel reference for the welcomePanel
    private JPanel foodPanel;//creates foodPanel reference for the foodPanel
    private JPanel foodReportPanel;//creates JPanel reference for the foodReportPanel
    private JPanel medReportPanel;//creates JPanel reference for the medReportPanel
    private JPanel medicinePanel;//creates JPanel reference for the medicinePanel
    private int foodTotals[][];//Creates a two dimensional array to tally the food totals by zone
    private int medicineTotals[][];//Creates a two dimensional array to tally the medicine totals by zone
    private int cagePosition;   //to keep track of the current animal being worked on

    //Creates JButton references for the following button variables
    private JButton nextButton;
    private JButton addFoodButton;
    private JButton addMedicineButton;
    private JButton printFeedingList;
    private JButton printMedicineList;
    private JButton printFoodReport;
    private JButton printMedReport;
    private JButton feedButton;
    private JButton healButton;

    //Creates JTextField references for the following textfield variables
    private JTextField hayAmount;  
    private JTextField fruitAmount;
    private JTextField grainAmount;
    private JTextField fishAmount;     
    private JTextField meatAmount;
    private JTextField herbicineAmount;
    private JTextField omnicineAmount;
    private JTextField carnicineAmount;

    
    private boolean isFed;//Checks if an animal is fed already
    private boolean isMedicated;//Checks if an animal is medicated  
    private ImageIcon zooLogo;//creates ImageIcon reference for the zooLogo   
    public static void main(String[] args) throws IOException
    {
        ZooManager zm = new ZooManager();
        
    }//main

    //This function displays the animal information at the respective cage position
    public void displayAnimalPanel(int position) throws IOException
    {
        theZoo.setPosition(position);
        theZoo.setAnimalPanel(animalPanel);
        theZoo.setNextButton(nextButton);
        theZoo.showAnimals();
        revalidate();
        repaint();
    }//displayAnimalPanel

    //This function enables the respective buttons to be used to select the food to feed an animal depending on the category of the animal
    public void foodFieldManager()
    {
        if(theZoo.getCages().get(cagePosition).getCategory().equalsIgnoreCase("Herbivore"))
        {
            hayAmount.setEnabled(true);
            fruitAmount.setEnabled(true);
            grainAmount.setEnabled(true);
            fishAmount.setEnabled(false);
            meatAmount.setEnabled(false);
        }

        else if(theZoo.getCages().get(cagePosition).getCategory().equalsIgnoreCase("Carnivore"))
        {
            hayAmount.setEnabled(false);
            fruitAmount.setEnabled(false);
            grainAmount.setEnabled(false);
            fishAmount.setEnabled(true);
            meatAmount.setEnabled(true);
        }
        else if(theZoo.getCages().get(cagePosition).getCategory().equalsIgnoreCase("Omnivore"))
        {
            hayAmount.setEnabled(true);
            fruitAmount.setEnabled(true);
            grainAmount.setEnabled(false);
            fishAmount.setEnabled(true);
            meatAmount.setEnabled(false);
        }
        foodPanel.revalidate();
        foodPanel.repaint();
    }//foodFieldManager

    //This enables the respective buttons to be used to select the medicine to heal an animal depending on the category of the animal
    public void medicineFieldManager()
    {
        if(theZoo.getCages().get(cagePosition).getCategory().equalsIgnoreCase("Herbivore"))
        {
            herbicineAmount.setEnabled(true);
            omnicineAmount.setEnabled(false);
            carnicineAmount.setEnabled(false);
        }
        else if(theZoo.getCages().get(cagePosition).getCategory().equalsIgnoreCase("Carnivore"))
        {
            herbicineAmount.setEnabled(false);
            omnicineAmount.setEnabled(false);
            carnicineAmount.setEnabled(true);
        }
        else if(theZoo.getCages().get(cagePosition).getCategory().equalsIgnoreCase("Omnivore"))
        {
            herbicineAmount.setEnabled(false);
            omnicineAmount.setEnabled(true);
            carnicineAmount.setEnabled(false);
        }
        medicinePanel.revalidate();
        medicinePanel.repaint();
    }//medicineFieldManager

    //Displays the welcome panel and all of its contents(Welcome message, Zookeeper name and Zoo image)
    public void displayWelcomePanel()
    {
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        JLabel welcomeMessage = new JLabel("Welcome to the Zoo Manager System "+ zooKeeper.getName());//gets the name of the ZooKeeper and sets it to a JLabel object
        welcomeMessage.setAlignmentX(CENTER_ALIGNMENT);
        welcomePanel.add(welcomeMessage);

        JLabel zooIcon = new JLabel(new ImageIcon(zooLogo.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));//gets and sets the Zoo image sets it to a JLabel object
        zooIcon.setAlignmentX(CENTER_ALIGNMENT);

        welcomePanel.add(zooIcon);
        
    }//displayWelcomePanel

    //Displays the panel which stores the information for foodtypes, JText fields references and the two dimensional arrays
    public void displayFoodPanel()
    {
        foodPanel.setLayout(new BoxLayout(foodPanel, BoxLayout.Y_AXIS));
        JPanel foodInfoPanel = new JPanel();
        JPanel print = new JPanel();
        JPanel allFood = new JPanel();
        print.setLayout(new FlowLayout());
        foodInfoPanel.setLayout(new GridLayout(6,2,10,10));

        //Declaring new JLabel objects for the related food variables 
        JLabel typeLabel = new JLabel("Type");
        JLabel hayLabel = new JLabel("Hay");
        JLabel fruitLabel = new JLabel("Fruit");
        JLabel grainLabel = new JLabel("Grain");
        JLabel fishLabel = new JLabel("Fish");
        JLabel meatLabel = new JLabel("Meat");
        JLabel amountLabel = new JLabel("Amount");
        

        foodFieldManager();
        hayAmount.setText("0");
        fruitAmount.setText("0");
        grainAmount.setText("0");
        fishAmount.setText("0");
        meatAmount.setText("0");
        //headings
        foodInfoPanel.add(typeLabel);
        foodInfoPanel.add(amountLabel);
        // info
        foodInfoPanel.add(hayLabel);
        foodInfoPanel.add(hayAmount);

        foodInfoPanel.add(fruitLabel);
        foodInfoPanel.add(fruitAmount);
        
        foodInfoPanel.add(grainLabel);
        foodInfoPanel.add(grainAmount);

        foodInfoPanel.add(fishLabel);
        foodInfoPanel.add(fishAmount);

        foodInfoPanel.add(meatLabel);
        foodInfoPanel.add(meatAmount);
        foodInfoPanel.setBorder(BorderFactory.createTitledBorder("Food"));
        allFood.add(foodInfoPanel);

        
        allFood.add(addFoodButton);
        print.add(printFeedingList);
        print.add(feedButton);

        JPanel foodTotalsPanel = new JPanel();
        foodTotalsPanel.setLayout(new GridLayout(6,4,15,15));

        //Creating new JLabel objects to store the zone headings
        JLabel aLabel = new JLabel("A");
        JLabel bLabel = new JLabel("B");
        JLabel cLabel = new JLabel("C");
        JLabel dLabel = new JLabel("D");

        foodTotalsPanel.add(aLabel);
        foodTotalsPanel.add(bLabel);
        foodTotalsPanel.add(cLabel);
        foodTotalsPanel.add(dLabel);
        for(int i = 0; i < 5;i++)
        {
            for(int j = 0;j<4;j++)
            {
                JLabel totalLabel = new JLabel(""+ foodTotals[i][j]);
                foodTotalsPanel.add(totalLabel);
            }//endfor
        }//endfor
        foodTotalsPanel.setBorder(BorderFactory.createTitledBorder("Totals"));
        allFood.add(foodTotalsPanel);
        foodPanel.add(allFood);
        foodPanel.add(print);
    }//displayFoodPanel

    //Displays the panel which stores the information for medicine types, JText fields references and the two dimensional arrays
    public void displayMedicinePanel()
    {
        medicinePanel.setLayout(new BoxLayout(medicinePanel, BoxLayout.Y_AXIS));
        JPanel medicineInfoPanel = new JPanel();
        JPanel printMedicinePanel = new JPanel();
        JPanel allMedicine = new JPanel();
        printMedicinePanel.setLayout(new FlowLayout());
        medicineInfoPanel.setLayout(new GridLayout(4,2,10,10));

        //Declaring new JLabel objects for the related medicine variables 
        JLabel typeLabel = new JLabel("Type");
        JLabel herbicineLabel = new JLabel("Herbicine");
        JLabel omnicineLabel = new JLabel("Omnicine");
        JLabel carnicineLabel = new JLabel("Carnicine");
        JLabel medAmountLabel = new JLabel("Amount");

        medicineFieldManager();
        herbicineAmount.setText("0");
        omnicineAmount.setText("0");
        carnicineAmount.setText("0");
        //headings
        medicineInfoPanel.add(typeLabel);
        medicineInfoPanel.add(medAmountLabel);
        // info
        medicineInfoPanel.add(herbicineLabel);
        medicineInfoPanel.add(herbicineAmount);

        medicineInfoPanel.add(omnicineLabel);
        medicineInfoPanel.add(omnicineAmount);
        
        medicineInfoPanel.add(carnicineLabel);
        medicineInfoPanel.add(carnicineAmount);

        medicineInfoPanel.setBorder(BorderFactory.createTitledBorder("Medicine"));
        allMedicine.add(medicineInfoPanel);

        
        allMedicine.add(addMedicineButton);
        printMedicinePanel.add(printMedicineList);
        printMedicinePanel.add(healButton);

        JPanel medicineTotalsPanel = new JPanel();
        medicineTotalsPanel.setLayout(new GridLayout(4,4,14,14));

        //Creating new JLabel objects to store the zone headings
        JLabel aLabel = new JLabel("A");
        JLabel bLabel = new JLabel("B");
        JLabel cLabel = new JLabel("C");
        JLabel dLabel = new JLabel("D");

        medicineTotalsPanel.add(aLabel);
        medicineTotalsPanel.add(bLabel);
        medicineTotalsPanel.add(cLabel);
        medicineTotalsPanel.add(dLabel);
        for(int i = 0; i <  medicineTotals.length;i++)  //3
        {
            for(int j = 0;j< medicineTotals[1].length;j++) //4
            {
                JLabel totalLabel = new JLabel(""+ medicineTotals[i][j]);
                medicineTotalsPanel.add(totalLabel);
            }//endfor
        }//endfor
        medicineTotalsPanel.setBorder(BorderFactory.createTitledBorder("Totals"));
        allMedicine.add(medicineTotalsPanel);
        medicinePanel.add(allMedicine);
        medicinePanel.add(printMedicinePanel);
    }//displayMedicinePanel

    //This function displays all the contents of the FeedingReport Panel(JScrollPane object is created)
    public void displayFeedReportPanel()
    {
            JPanel fReportPanel = new JPanel();
            JPanel info = new JPanel();
            JPanel button = new JPanel();
            fReportPanel.setPreferredSize(new Dimension(300, 200));
            fReportPanel.setLayout(new BoxLayout(fReportPanel, BoxLayout.Y_AXIS));
            info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

            JScrollPane scroll = new JScrollPane(info);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            fReportPanel.add(scroll);
            JLabel date = new JLabel(getDate());
            
            JLabel animalsFed = new JLabel("AnimalsFed: "+ animalFeeder.getFeedingListSize());
            
            ArrayList<Animal> deadAnimals = new ArrayList<Animal>();

            //This loop goes through the animals and finds the dead ones and appends them to an arraylist
            for(int i=0; i<animalFeeder.getFeedingListSize();i++)
            {
                Animal tempFedAnimal = animalFeeder.getAnimal(animalFeeder.getFeedingList().get(i).getCageID());
                if(tempFedAnimal.getHungerStatus() > tempFedAnimal.getMaxHunger())
                {
                    deadAnimals.add(tempFedAnimal);
                }//endfor
            }//endfor
            JLabel okAnimals = new JLabel("OK: "+(animalFeeder.getFeedingListSize() - deadAnimals.size()));
            JLabel deathAnimals = new JLabel("Deaths: "+ deadAnimals.size());
            
            info.add(date);
            info.add(animalsFed);
            info.add(okAnimals);
            info.add(deathAnimals);

            //This loop gets the information of any dead animals
            for(int i=0;i<deadAnimals.size(); i++)
            {
                String deadAnimalInfo2 = deadAnimals.get(i).getCageID()+" "+ deadAnimals.get(i).getName()+ " "+ deadAnimals.get(i).getSpecies()+" Original Hunger Status: "+ (deadAnimals.get(i).hungerStatus - animalFeeder.getFoodAmt(deadAnimals.get(i).getCageID()))+" Food Amount: "+ animalFeeder.getFoodAmt(deadAnimals.get(i).getCageID())+ " Food Type: "+ animalFeeder.getFoodType(deadAnimals.get(i).getCageID());
                JLabel deadAnimalInfo = new JLabel(deadAnimalInfo2);   
                info.add(deadAnimalInfo);
            }//endfor
            button.add(printFoodReport);
            fReportPanel.add(button);
            fReportPanel.setBorder(BorderFactory.createTitledBorder("Feeding Report"));
            foodReportPanel.add(fReportPanel);

    }//displayFeedReportPanel

    public void printFReport()
    {
        theZoo.setAnimalFeeder(animalFeeder);
        theZoo.printHungerReport();
    }//printFReport

    public void printMReport()
    {
        theZoo.setAnimalHealer(animalHealer);
        theZoo.printHealthReport();
    }//printMReport
    public void displayMedReportPanel()
    {
        JPanel mReportPanel = new JPanel();
            JPanel mInfo = new JPanel();
            JPanel mButton = new JPanel();
            mReportPanel.setPreferredSize(new Dimension(300, 200));
            mReportPanel.setLayout(new BoxLayout(mReportPanel, BoxLayout.Y_AXIS));
            mInfo.setLayout(new BoxLayout(mInfo, BoxLayout.Y_AXIS));

            JScrollPane scroll = new JScrollPane(mInfo);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            mReportPanel.add(scroll);
            JLabel date = new JLabel(getDate());
            
            JLabel animalsHealed = new JLabel("AnimalsHealed: "+ animalHealer.getHealingListSize());
            
            ArrayList<Animal> deadAnimals = new ArrayList<Animal>();

            
            //This loop goes through the animals and finds the dead ones and appends them to an arraylist
            for(int i=0; i<animalHealer.getHealingListSize();i++)
            {
                Animal tempHealedAnimal = animalHealer.getAnimal(animalHealer.getHealingList().get(i).getCageID());
                if(tempHealedAnimal.getHealthStatus() > tempHealedAnimal.getMaxHealth())
                {
                    deadAnimals.add(tempHealedAnimal);
                }
            }//endfor
            JLabel okAnimals = new JLabel("OK: "+(animalHealer.getHealingListSize() - deadAnimals.size()));
            JLabel deathAnimals = new JLabel("Deaths: "+ deadAnimals.size());
            
            mInfo.add(date);
            mInfo.add(animalsHealed);
            mInfo.add(okAnimals);
            mInfo.add(deathAnimals);

            
            //This loop find the information of the dead animals
            for(int i=0;i<deadAnimals.size(); i++)
            {
                String deadAnimalInfo2 = deadAnimals.get(i).getCageID()+" "+ deadAnimals.get(i).getName()+ " "+ deadAnimals.get(i).getSpecies()+" Original Health Status: "+ (deadAnimals.get(i).healthStatus - animalHealer.getUnitsOfMed(deadAnimals.get(i).getCageID()))+" Medication Amount: "+ animalHealer.getUnitsOfMed(deadAnimals.get(i).getCageID())+ " Medication Type: "+ animalHealer.getMedType(deadAnimals.get(i).getCageID());
                JLabel deadAnimalInfo = new JLabel(deadAnimalInfo2);   
                mInfo.add(deadAnimalInfo);
            }//endfor
            mButton.add(printMedReport);
            mReportPanel.add(mButton);
            mReportPanel.setBorder(BorderFactory.createTitledBorder("Healing Report"));
            medReportPanel.add(mReportPanel);
    }//displayMedReportPanel

    //This function changes the status of the "Next" button in the GUI depending on the condition
    public void nextButtonSwitch()
    {
        if(isMedicated && isFed)
        {
            nextButton.setEnabled(true);
        }
        else{
            nextButton.setEnabled(false);
        }
    }//nextButtonSwitch

    //This function changes the status of the "Add" button for the food in the GUI depending on the condition
    public void foodButtonSwitch()
    {
        
        if(isFed)
        {
            addFoodButton.setEnabled(false);
        }

        else if(!isFed)
        {
            addFoodButton.setEnabled(true);
        }
        nextButtonSwitch();
        
    }//foodButtonSwitch

    //This function changes the status of the "Add" button for the medicine in the GUI depending on the condition
    public void medButtonSwitch()
    {
        
        if(isMedicated)
        {
            addMedicineButton.setEnabled(false);
        }
        else if (!isMedicated)
        {
            addMedicineButton.setEnabled(true);
        }
        nextButtonSwitch();
        
    }//medButtonSwitch

    
    //Gets the current date 
    public String getDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        String month = sdf.format(new Date());
        sdf = new SimpleDateFormat("dd");
        String day = sdf.format(new Date());
        sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(new Date());
        return(day+ " "+ month+" "+ year);
    }
    public ZooManager() throws IOException
    {
        Welcome msg = new Welcome();
        msg.displayWelcome();

        //Checks to see if the person enters valid information into the text field(A proper name) and ensures that it is not empty or if they want to come out of the program
        if(msg.getZooKeeperName()==null||msg.getZooKeeperName().trim().isEmpty())
        {
            // System.exit(0);
            return;
        }
        zooKeeper = new ZooKeeper();
        zooKeeper.setName(msg.getZooKeeperName());//Sets the name of the ZooKeeper entered at the welcome message popup
        theZoo = new Zoo(); //initializes the zoo object
        theZoo.readAnimals();   //reads animals from text file
        animalFeeder = new AnimalFeeder(theZoo.getCages()); //initializes animal feeder object with cages
        animalHealer = new AnimalHealer(theZoo.getCages()); //initializes
        foodTotals = new int[5][4]; //[rows][columns]
        medicineTotals = new int[3][4]; //[rows][columns]
        setLayout(new GridLayout(2,3));
        setSize(1080,720);
        setTitle("The Cave Hill Zoo Manager System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        zooLogo = new ImageIcon("Cave Hill Zoo.png");

        //Creating button objects for the respective variables
        nextButton = new JButton("Next ->");
        printFeedingList = new JButton("Print List");
        printMedicineList = new JButton("Print List");
        printFoodReport = new JButton("Print Report");
        printMedReport = new JButton("Print Report");
        feedButton = new JButton("Feed");
        healButton = new JButton("Heal");
        addFoodButton = new JButton("Add ->");
        addMedicineButton = new JButton("Add ->");
        foodButtonSwitch();

        //Setting the default condition for the buttons
        printMedReport.setEnabled(false);
        printFoodReport.setEnabled(false);
        printFeedingList.setEnabled(false);
        feedButton.setEnabled(false);
        printMedicineList.setEnabled(false);
        healButton.setEnabled(false);
        addFoodButton.setEnabled(false);
        addMedicineButton.setEnabled(false);
        
        //Creating panel objects for the respective variables
        welcomePanel = new JPanel();
        animalPanel = new JPanel();
        foodPanel = new JPanel();

        //Creating textfield objects for the respective variables
        hayAmount= new JTextField(4);
        fruitAmount = new JTextField();
        grainAmount = new JTextField();
        fishAmount = new JTextField();
        meatAmount = new JTextField();
        herbicineAmount = new JTextField();
        omnicineAmount = new JTextField();
        carnicineAmount = new JTextField();

        //Setting the default conditions for feeding and medication for the animals
        isFed = false;
        isMedicated = false;

        //Creating panel objects for the respective variables
        foodReportPanel = new JPanel();
        medReportPanel = new JPanel();
        medicinePanel = new JPanel();
        
        animalPanel.setLayout(new BoxLayout(animalPanel, BoxLayout.Y_AXIS));
        cagePosition = 0;//initializes cagePosition to 0

        //Ensures that if a value is added to the hayAmount variable, that you cannot add any other food types
        hayAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
                    //Checks that if the value is empty that no food of the respective type is added
                    if(hayAmount.getText() == null) {
                        foodFieldManager();
                        addFoodButton.setEnabled(false);
                        return;
                    }
                else if(hayAmount.getText().trim().isEmpty())
                {
                    foodFieldManager();
                    addFoodButton.setEnabled(false);

                }
                //If food is being entered, then only the food of that type can be entered
                else if(Integer.parseInt(hayAmount.getText()) > 0)
                {
                    fruitAmount.setEnabled(false);
                    grainAmount.setEnabled(false);
                    fishAmount.setEnabled(false);
                    meatAmount.setEnabled(false);
                    foodButtonSwitch();
                }
                else{
                    foodFieldManager();
                    addFoodButton.setEnabled(false);

                }
                } catch (Exception err) {
                    foodFieldManager();
                    addFoodButton.setEnabled(false);
                    hayAmount.setText("0");
                    System.out.println(err.getMessage());
                }
                
            }
        });

        //Ensures that if a value is added to the fruitAmount variable, that you cannot add any other food types
        fruitAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
                    //Checks that if the value is empty that no food of the respective type is added
                    if(fruitAmount.getText() == null) {
                        foodFieldManager();
                        addFoodButton.setEnabled(false);
                        return;
                    }
                else if(fruitAmount.getText().trim().isEmpty())
                {
                    foodFieldManager();
                    addFoodButton.setEnabled(false);

                }

                 //If food is being entered, then only the food of that type can be entered
                else if(Integer.parseInt(fruitAmount.getText()) > 0)
                {
                    hayAmount.setEnabled(false);
                    grainAmount.setEnabled(false);
                    fishAmount.setEnabled(false);
                    meatAmount.setEnabled(false);
                    foodButtonSwitch();

                }
                else{
                    foodFieldManager();
                    addFoodButton.setEnabled(false);

                }
                } catch (Exception err) {
                    foodFieldManager();
                    fruitAmount.setText("0");
                    addFoodButton.setEnabled(false);
                }
                
            }
        });

        //Ensures that if a value is added to the grainAmount variable, that you cannot add any other food types
        grainAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
                    //Checks that if the value is empty that no food of the respective type is added
                    if(grainAmount.getText() == null) {
                        foodFieldManager();
                        addFoodButton.setEnabled(false);
                        return;
                    }
                else if(grainAmount.getText().trim().isEmpty())
                {
                    foodFieldManager();
                    addFoodButton.setEnabled(false);

                }

                //If food is being entered, then only the food of that type can be entered
                else if(Integer.parseInt(grainAmount.getText()) > 0)
                {
                    hayAmount.setEnabled(false);
                    fruitAmount.setEnabled(false);
                    fishAmount.setEnabled(false);
                    meatAmount.setEnabled(false);
                    foodButtonSwitch();

                }
                else{
                    foodFieldManager();
                    addFoodButton.setEnabled(false);

                }
                } catch (Exception err) {
                    foodFieldManager();
                    grainAmount.setText("0");
                    addFoodButton.setEnabled(false);
                    System.out.println(err.getMessage());
                }
                
            }
        });

        //Ensures that if a value is added to the fishAmount variable, that you cannot add any other food types
        fishAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
                    //Checks that if the value is empty that no food of the respective type is added
                    if(fishAmount.getText() == null) {
                        foodFieldManager();
                        addFoodButton.setEnabled(false);
                        return;
                    }
                else if(fishAmount.getText().trim().isEmpty())
                {
                    foodFieldManager();
                    addFoodButton.setEnabled(false);

                }

                //If food is being entered, then only the food of that type can be entered
                else if(Integer.parseInt(fishAmount.getText()) > 0)
                {
                    hayAmount.setEnabled(false);
                    grainAmount.setEnabled(false);
                    fruitAmount.setEnabled(false);
                    meatAmount.setEnabled(false);
                    foodButtonSwitch();

                }
                else{
                    foodFieldManager();
                    addFoodButton.setEnabled(false);

                }
                } catch (Exception err) {
                    foodFieldManager();
                    fishAmount.setText("0");
                    addFoodButton.setEnabled(false);
                    System.out.println(err.getMessage());
                }
                
            }
        });

        //Ensures that if a value is added to the meatAmount variable, that you cannot add any other food types
        meatAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
                    //Checks that if the value is empty that no food of the respective type is added
                    if(meatAmount.getText() == null) {
                        foodFieldManager();
                        addFoodButton.setEnabled(false);
                        return;
                    }
                else if(meatAmount.getText().trim().isEmpty())
                {
                    foodFieldManager();
                    addFoodButton.setEnabled(false);

                }

                //If food is being entered, then only the food of that type can be entered
                else if(Integer.parseInt(meatAmount.getText()) > 0)
                {
                    hayAmount.setEnabled(false);
                    grainAmount.setEnabled(false);
                    fishAmount.setEnabled(false);
                    fruitAmount.setEnabled(false);
                    foodButtonSwitch();
                }
                else{
                    foodFieldManager();
                    addFoodButton.setEnabled(false);

                }
                } catch (Exception err) {
                    foodFieldManager();
                    meatAmount.setText("0");
                    addFoodButton.setEnabled(false);
                }
                
            }
        });

        //Ensures that if a value is added to the herbicineAmount variable, that you cannot add any other food types
        herbicineAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
                    //Checks that if the value is empty that no food of the respective type is added
                    if(herbicineAmount.getText() == null) {
                        medicineFieldManager();
                        addMedicineButton.setEnabled(false);
                        return;
                    }
                else if(herbicineAmount.getText().trim().isEmpty())
                {
                    medicineFieldManager();
                    addMedicineButton.setEnabled(false);

                }

                //If medicine is being entered, then only the medicine of that type can be entered
                else if(Integer.parseInt(herbicineAmount.getText()) > 0)
                {
                    carnicineAmount.setEnabled(false);
                    omnicineAmount.setEnabled(false);
                    medButtonSwitch();
                }
                else{
                    medicineFieldManager();
                    addMedicineButton.setEnabled(false);

                }
                } catch (Exception err) {
                    medicineFieldManager();
                    herbicineAmount.setText("0");
                    addMedicineButton.setEnabled(false);
                    System.out.println(err.getMessage());
                }
                
            }
        });

        //Ensures that if a value is added to the omnicineAmount variable, that you cannot add any other food types
        omnicineAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
                    //Checks that if the value is empty that no food of the respective type is added
                    if(omnicineAmount.getText() == null) {
                        medicineFieldManager();
                        addMedicineButton.setEnabled(false);
                        return;
                    }
                else if(omnicineAmount.getText().trim().isEmpty())
                {
                    medicineFieldManager();
                    addMedicineButton.setEnabled(false);

                }

                //If medicine is being entered, then only the medicine of that type can be entered
                else if(Integer.parseInt(omnicineAmount.getText()) > 0)
                {
                    carnicineAmount.setEnabled(false);
                    herbicineAmount.setEnabled(false);
                    medButtonSwitch();
                }
                else{
                    medicineFieldManager();
                    addMedicineButton.setEnabled(false);

                }
                } catch (Exception err) {
                    medicineFieldManager();
                    omnicineAmount.setText("0");
                    addMedicineButton.setEnabled(false);
                    System.out.println(err.getMessage());
                }
                
            }
        });
        
        //Ensures that if a value is added to the carnicineAmount variable, that you cannot add any other food types
        carnicineAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
                    //Checks that if the value is empty that no food of the respective type is added
                    if(carnicineAmount.getText() == null) {
                        medicineFieldManager();
                        addMedicineButton.setEnabled(false);
                        return;
                    }
                else if(carnicineAmount.getText().trim().isEmpty())
                {
                    medicineFieldManager();
                    addMedicineButton.setEnabled(false);

                }

                //If medicine is being entered, then only the medicine of that type can be entered
                else if(Integer.parseInt(carnicineAmount.getText()) > 0)
                {
                    omnicineAmount.setEnabled(false);
                    herbicineAmount.setEnabled(false);
                    medButtonSwitch();
                }
                else{
                    medicineFieldManager();
                    addMedicineButton.setEnabled(false);

                }
                } catch (Exception err) {
                    medicineFieldManager();
                    carnicineAmount.setText("0");
                    addMedicineButton.setEnabled(false);
                    System.out.println(err.getMessage());
                }
                
            }
        });

        //Adding the button event listener for the buttons related to the GUI
        nextButton.addActionListener(this);
        addFoodButton.addActionListener(this);
        printFeedingList.addActionListener(this);
        feedButton.addActionListener(this);
        printFoodReport.addActionListener(this);
        printMedReport.addActionListener(this);
        printMedicineList.addActionListener(this);
        healButton.addActionListener(this);
        addMedicineButton.addActionListener(this);

        if(theZoo.getCages().size() == 0 )
        {
            // do something if zoo is empty
            JOptionPane.showMessageDialog(null,"Empty Zoo");
            return;
        }
        //These display functions show the related panels on the JFrame
        displayAnimalPanel(cagePosition);
        displayWelcomePanel();
        displayFoodPanel();
        displayFeedReportPanel();
        displayMedicinePanel();
        displayMedReportPanel();
        
        //Adds the JFrame panels in the order that was shown in the instructions
        add(animalPanel);
        add(foodPanel);
        add(foodReportPanel);
        add(welcomePanel);
        add(medicinePanel);
        add(medReportPanel);

        centerFrame();
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        //The functionality for the nextButton variable
        if(e.getSource()==nextButton){
            try 
            {
                
                cagePosition++;//increments the cage position every time the button is clicked

                if(cagePosition < theZoo.getCages().size())
                {
                    printFeedingList.setEnabled(false);
                    feedButton.setEnabled(false);
                    isFed=false;
                    isMedicated = false;

                    //If the animal is already healthy, it is left alone
                    if(theZoo.getCages().get(cagePosition).getHungerStatus()>=5)
                    {
                        isFed = true;
                    }
                    if(theZoo.getCages().get(cagePosition).getHealthStatus()>=10)
                    {
                        isMedicated = true;
                    }

                    //Allows you to switch the information on the panel to the next animal
                    foodButtonSwitch();
                    addFoodButton.setEnabled(false);
                    addMedicineButton.setEnabled(false);
                    printFoodReport.setEnabled(false);
                    printMedReport.setEnabled(false);
                    animalPanel.removeAll();
                    animalPanel.revalidate();
                    animalPanel.repaint();
                    displayAnimalPanel(cagePosition);
                    foodPanel.removeAll();
                    foodPanel.revalidate();
                    foodPanel.repaint();
                    displayFoodPanel();
                    medicinePanel.removeAll();
                    medicinePanel.revalidate();
                    medicinePanel.repaint();
                    displayMedicinePanel();
                }
                else

                //Allows you to feed or medicate the animal as required before you are allowed to go to the next one
                {
                    nextButton.setEnabled(false);
                    printFeedingList.setEnabled(true);
                    feedButton.setEnabled(true);
                    hayAmount.setEnabled(false);
                    fruitAmount.setEnabled(false);
                    grainAmount.setEnabled(false);
                    fishAmount.setEnabled(false);
                    meatAmount.setEnabled(false);
                    herbicineAmount.setEnabled(false);
                    omnicineAmount.setEnabled(false);
                    carnicineAmount.setEnabled(false);
                    printMedicineList.setEnabled(true);
                    healButton.setEnabled(true);
                }
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }

        //Handles the functionality for the addFoodButton
        if(e.getSource()==addFoodButton)
        {
            try 
            {
            isFed = true;
            foodButtonSwitch();
            int rowPosition = 0;//initializes rowPosition to 0
            int foodAmt = 0;//initializes foodAmt to 0
            String foodType = "";//initializes foodType to an empty String

            //Checks the respective textfield that is selected and gets the amount of food entered by the user 
            if(hayAmount.getText() != null && !hayAmount.getText().equals("")&& Integer.parseInt(hayAmount.getText())>0)
            {
                foodType = "Hay";
                rowPosition = 0;
                foodAmt= Integer.parseInt(hayAmount.getText());
            }
            else if(fruitAmount.getText() != null&& !fruitAmount.getText().equals("") && Integer.parseInt(fruitAmount.getText())>0)
            {
                foodType = "Fruit";

                rowPosition = 1;
                foodAmt = Integer.parseInt(fruitAmount.getText());
            }
            else if(grainAmount.getText() != null && !grainAmount.getText().equals("") && Integer.parseInt(grainAmount.getText())>0)
            {
                foodType = "Grain";
                rowPosition = 2;
                foodAmt = Integer.parseInt(grainAmount.getText());
            }
            else if(fishAmount.getText() != null && !fishAmount.getText().equals("") && Integer.parseInt(fishAmount.getText())>0)
            {
                foodType = "Fish";
                rowPosition = 3;
                foodAmt = Integer.parseInt(fishAmount.getText());
            }
            else if(meatAmount.getText() != null && !meatAmount.getText().equals("") && Integer.parseInt(meatAmount.getText())>0)
            {
                foodType = "Meat";
                rowPosition = 4;
                foodAmt = Integer.parseInt(meatAmount.getText());
            }
            String[] cageLetter = theZoo.getCages().get(cagePosition).getCageID().split("-");
                int zonePosition = 0;
                if(cageLetter[0].equals("A"))
                {
                    zonePosition = 0;
                }
                else if(cageLetter[0].equals("B"))
                {
                    zonePosition = 1;
                }
                else if(cageLetter[0].equals("C"))
                {
                    zonePosition = 2;
                }
                else if(cageLetter[0].equals("D"))
                {
                    zonePosition = 3;
                }
                foodTotals[rowPosition][zonePosition] += foodAmt;//Adds the food amount entered to the totals panel
                animalFeeder.createMeal(theZoo.getCages().get(cagePosition).getCageID(), foodType, foodAmt);
                animalFeeder.addMeal();
                foodPanel.removeAll();
                foodPanel.revalidate();
                foodPanel.repaint();
                displayFoodPanel();
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }

        //Functionality for the addMedicineButton
        if(e.getSource()==addMedicineButton)
        {
            try 
            {
            isMedicated = true;
            medButtonSwitch();
            int rowPosition = 0;//initializes rowPosition to 0
            int medicineAmt = 0;//initializes medicineAmt to 0
            String medicineType = "";//initializes medicineType to an empty String

            //Checks the respective textfield that is selected and gets the amount of medicine entered by the user 
            if(herbicineAmount.getText() != null && !herbicineAmount.getText().equals("")&& Integer.parseInt(herbicineAmount.getText())>0)
            {
                medicineType = "Herbicine";
                rowPosition = 0;
                medicineAmt= Integer.parseInt(herbicineAmount.getText());
            }
            else if(omnicineAmount.getText() != null&& !omnicineAmount.getText().equals("") && Integer.parseInt(omnicineAmount.getText())>0)
            {
                medicineType = "Omnicine";

                rowPosition = 1;
                medicineAmt = Integer.parseInt(omnicineAmount.getText());
            }
            else if(carnicineAmount.getText() != null && !carnicineAmount.getText().equals("") && Integer.parseInt(carnicineAmount.getText())>0)
            {
                medicineType = "Carnicine";
                rowPosition = 2;
                medicineAmt = Integer.parseInt(carnicineAmount.getText());
            }
            String[] cageLetter = theZoo.getCages().get(cagePosition).getCageID().split("-");
                int zonePosition = 0;
                if(cageLetter[0].equals("A"))
                {
                    zonePosition = 0;
                }
                else if(cageLetter[0].equals("B"))
                {
                    zonePosition = 1;
                }
                else if(cageLetter[0].equals("C"))
                {
                    zonePosition = 2;
                }
                else if(cageLetter[0].equals("D"))
                {
                    zonePosition = 3;
                }
                medicineTotals[rowPosition][zonePosition] += medicineAmt;//Adds the medicine amount entered to the totals panel
                animalHealer.createPrescription(theZoo.getCages().get(cagePosition).getCageID(), medicineType, medicineAmt);
                animalHealer.addPrescription();
                medicinePanel.removeAll();
                medicinePanel.revalidate();
                medicinePanel.repaint();
                displayMedicinePanel();
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }

        //Functionality for the printFeedingList
        if(e.getSource()==printFeedingList)
            {
                try
                {
                    animalFeeder.printFeedingList();//Prints the FeedingList to the text file 
                    printFeedingList.setEnabled(false);//Disables the button so that the file is not printed twice
                }

                catch (Exception err) {
                System.out.println(err.getMessage());
                }
            }

        //Functionality for feedButton
        if(e.getSource() == feedButton)
        {
            //Feeds all animals and then disables the feed button, then it enables the printFoodReport button
            try {
                animalFeeder.simFeeding();
                foodReportPanel.removeAll();
                displayFeedReportPanel();
                foodReportPanel.revalidate();
                foodReportPanel.repaint();
                feedButton.setEnabled(false);
                printFoodReport.setEnabled(true);
            }
            catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }

        //Functionality for printFoodReport
        if(e.getSource()==printFoodReport)
        {
            try
            {
                printFReport();//Prints FoodReport to a text file
                printFoodReport.setEnabled(false);//Disables the button so you cannot print the file twice
            }
            catch(Exception err)
            {
                System.out.println(err.getMessage());   
            }

        }

        //Functionality for printMedicineList
        if(e.getSource() == printMedicineList)
        {
            animalHealer.printHealingList();//Prints the HealingList to a text file
            printMedicineList.setEnabled(false);//Disables the button so that the file can't be printed twice
        }

        //Functionality for healButton
        if(e.getSource() == healButton)
        {
            try {
                //Heals all animals and then disables the heal button, then it enables the printMedReport button
                animalHealer.simHealing();
                medReportPanel.removeAll();
                displayMedReportPanel();
                medReportPanel.revalidate();
                medReportPanel.repaint();
                healButton.setEnabled(false);
                printMedReport.setEnabled(true);
            }
            catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }

        //Functionality for printMedReport
        if(e.getSource()==printMedReport)
        {
            try
            {
                printMReport();//Prints the HealReport to a text file
                printMedReport.setEnabled(false);//Disables the button so that the file can't be printed twice
            }
            catch(Exception err)
            {
                System.out.println(err.getMessage());   
            }

        }
    }

    //Places the frame to the centre of the screen
    private void centerFrame() 
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        setLocation(x, y);
    }//centerFrame
}   //ZooManager
