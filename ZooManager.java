// Mateo Sam and Robali Sewitt
// 400006967 and 400007056
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.io.FileWriter;

public class ZooManager extends JFrame implements ActionListener
{   
    private AnimalFeeder animalFeeder;
    private AnimalHealer animalHealer;
    private JPanel animalPanel;
    private JPanel welcomePanel;
    private JPanel foodPanel;
    private JPanel foodReportPanel;
    private int foodTotals[][];
    private JPanel medicinePanel;
    private JPanel medReportPanel;
    private int medicineTotals[][];
    private JButton nextButton;
    private int cagePosition;   //to keep track of the current animal being worked on
    private Zoo theZoo;
    private JButton addFoodButton;
    private JButton addMedicineButton;
    private JButton printFeedingList;
    private JButton printMedicineList;
    private JButton printFoodReport;
    private JButton printMedReport;
    private JButton feedButton;
    private JButton healButton;
    private JTextField hayAmount;  
    private JTextField fruitAmount;
    private JTextField grainAmount;
    private JTextField fishAmount;     
    private JTextField meatAmount;
    private JTextField herbicineAmount;
    private JTextField omnicineAmount;
    private JTextField carnicineAmount;
    private boolean isFed;
    private boolean isMedicated; 
    private ImageIcon zooLogo;
    private ZooKeeper zooKeeper;    
    public static void main(String[] args) throws IOException
    {
        ZooManager zm = new ZooManager();
        
    }   //main

    public void displayAnimalPanel(int position) throws IOException
    {
        theZoo.setPosition(position);
        theZoo.setAnimalPanel(animalPanel);
        theZoo.setNextButton(nextButton);
        theZoo.showAnimals();
        revalidate();
        repaint();
    }
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
    }
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
    }
    public void displayWelcomePanel()
    {
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        JLabel welcomeMessage = new JLabel("Welcome to the Zoo Manager System "+ zooKeeper.getName());
        welcomeMessage.setAlignmentX(CENTER_ALIGNMENT);
        welcomePanel.add(welcomeMessage);
        JLabel zooIcon = new JLabel(new ImageIcon(zooLogo.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
                zooIcon.setAlignmentX(CENTER_ALIGNMENT);

        welcomePanel.add(zooIcon);
        // JButton logo = new JButton("ZooKeeper 2.0 LOGO");
        // welcomePanel.add(logo);
    }

    public void displayFoodPanel()
    {
        foodPanel.setLayout(new BoxLayout(foodPanel, BoxLayout.Y_AXIS));
        JPanel foodInfoPanel = new JPanel();
        JPanel print = new JPanel();
        JPanel allFood = new JPanel();
        print.setLayout(new FlowLayout());
        foodInfoPanel.setLayout(new GridLayout(6,2,10,10));
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
        //foodInfoPanel.add(print);

        JPanel foodTotalsPanel = new JPanel();
        foodTotalsPanel.setLayout(new GridLayout(6,4,15,15));
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
            }
        }
        foodTotalsPanel.setBorder(BorderFactory.createTitledBorder("Totals"));
        allFood.add(foodTotalsPanel);
        foodPanel.add(allFood);
        foodPanel.add(print);
    }

    public void displayMedicinePanel()
    {
        medicinePanel.setLayout(new BoxLayout(medicinePanel, BoxLayout.Y_AXIS));
        JPanel medicineInfoPanel = new JPanel();
        JPanel printMedicinePanel = new JPanel();
        JPanel allMedicine = new JPanel();
        printMedicinePanel.setLayout(new FlowLayout());
        medicineInfoPanel.setLayout(new GridLayout(4,2,10,10));
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
        //foodInfoPanel.add(print);

        JPanel medicineTotalsPanel = new JPanel();
        medicineTotalsPanel.setLayout(new GridLayout(4,4,14,14));
        JLabel aLabel = new JLabel("A");
        JLabel bLabel = new JLabel("B");
        JLabel cLabel = new JLabel("C");
        JLabel dLabel = new JLabel("D");

        medicineTotalsPanel.add(aLabel);
        medicineTotalsPanel.add(bLabel);
        medicineTotalsPanel.add(cLabel);
        medicineTotalsPanel.add(dLabel);
        for(int i = 0; i < 3;i++)
        {
            for(int j = 0;j<4;j++)
            {
                JLabel totalLabel = new JLabel(""+ medicineTotals[i][j]);
                medicineTotalsPanel.add(totalLabel);
            }
        }
        medicineTotalsPanel.setBorder(BorderFactory.createTitledBorder("Totals"));
        allMedicine.add(medicineTotalsPanel);
        medicinePanel.add(allMedicine);
        medicinePanel.add(printMedicinePanel);
    }

    public void displayFeedReportPanel()
    {
            JPanel fReportPanel = new JPanel();
            JPanel info = new JPanel();
            JPanel button = new JPanel();
            fReportPanel.setPreferredSize(new Dimension(300, 200));
            //JLabel animalAge = new JLabel("Age: "+Integer.toString(theZoo.getCages().get(i).getAge()));
            fReportPanel.setLayout(new BoxLayout(fReportPanel, BoxLayout.Y_AXIS));
            info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

            // JLabel test = new JLabel("This is a test");
            JScrollPane scroll = new JScrollPane(info);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            // fReportPanel.add(test);
            fReportPanel.add(scroll);
            JLabel date = new JLabel(getDate());
            
            JLabel animalsFed = new JLabel("AnimalsFed: "+ animalFeeder.getFeedingListSize());
            
            ArrayList<Animal> deadAnimals = new ArrayList<Animal>();
            for(int i=0; i<animalFeeder.getFeedingListSize();i++)
            {
                Animal tempFedAnimal = animalFeeder.getAnimal(animalFeeder.getFeedingList().get(i).getCageID());
                if(tempFedAnimal.getHungerStatus() > 5)
                {
                    deadAnimals.add(tempFedAnimal);
                }
            }
            JLabel okAnimals = new JLabel("OK: "+(animalFeeder.getFeedingListSize() - deadAnimals.size()));
            JLabel deathAnimals = new JLabel("Deaths: "+ deadAnimals.size());
            
            info.add(date);
            info.add(animalsFed);
            info.add(okAnimals);
            info.add(deathAnimals);
            for(int i=0;i<deadAnimals.size(); i++)
            {
                String deadAnimalInfo2 = deadAnimals.get(i).getCageID()+" "+ deadAnimals.get(i).getName()+ " "+ deadAnimals.get(i).getSpecies()+" Original Hunger Status: "+ (deadAnimals.get(i).hungerStatus - animalFeeder.getFoodAmt(deadAnimals.get(i).getCageID()))+" Food Amount: "+ animalFeeder.getFoodAmt(deadAnimals.get(i).getCageID())+ " Food Type: "+ animalFeeder.getFoodType(deadAnimals.get(i).getCageID());
                JLabel deadAnimalInfo = new JLabel(deadAnimalInfo2);   
                info.add(deadAnimalInfo);
            }
            button.add(printFoodReport);
            fReportPanel.add(button);
            fReportPanel.setBorder(BorderFactory.createTitledBorder("Feeding Report"));
            foodReportPanel.add(fReportPanel);

    }

    public void printFReport()
    {
        theZoo.setAnimalFeeder(animalFeeder);
        theZoo.printHungerReport();
    }

    public void printMReport()
    {
        theZoo.setAnimalHealer(animalHealer);
        theZoo.printHealthReport();
    }
    public void displayMedReportPanel()
    {
        JPanel mReportPanel = new JPanel();
            JPanel mInfo = new JPanel();
            JPanel mButton = new JPanel();
            mReportPanel.setPreferredSize(new Dimension(300, 200));
            //JLabel animalAge = new JLabel("Age: "+Integer.toString(theZoo.getCages().get(i).getAge()));
            mReportPanel.setLayout(new BoxLayout(mReportPanel, BoxLayout.Y_AXIS));
            mInfo.setLayout(new BoxLayout(mInfo, BoxLayout.Y_AXIS));

            // JLabel test = new JLabel("This is a test");
            JScrollPane scroll = new JScrollPane(mInfo);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            // mReportPanel.add(test);
            mReportPanel.add(scroll);
            JLabel date = new JLabel(getDate());
            
            JLabel animalsHealed = new JLabel("AnimalsHealed: "+ animalHealer.getHealingListSize());
            
            ArrayList<Animal> deadAnimals = new ArrayList<Animal>();
            for(int i=0; i<animalHealer.getHealingListSize();i++)
            {
                Animal tempHealedAnimal = animalHealer.getAnimal(animalHealer.getHealingList().get(i).getCageID());
                if(tempHealedAnimal.getHealthStatus() > 10)
                {
                    deadAnimals.add(tempHealedAnimal);
                }
            }
            JLabel okAnimals = new JLabel("OK: "+(animalHealer.getHealingListSize() - deadAnimals.size()));
            JLabel deathAnimals = new JLabel("Deaths: "+ deadAnimals.size());
            
            mInfo.add(date);
            mInfo.add(animalsHealed);
            mInfo.add(okAnimals);
            mInfo.add(deathAnimals);
            for(int i=0;i<deadAnimals.size(); i++)
            {
                String deadAnimalInfo2 = deadAnimals.get(i).getCageID()+" "+ deadAnimals.get(i).getName()+ " "+ deadAnimals.get(i).getSpecies()+" Original Health Status: "+ (deadAnimals.get(i).healthStatus - animalHealer.getUnitsOfMed(deadAnimals.get(i).getCageID()))+" Medication Amount: "+ animalHealer.getUnitsOfMed(deadAnimals.get(i).getCageID())+ " Medication Type: "+ animalHealer.getMedType(deadAnimals.get(i).getCageID());
                JLabel deadAnimalInfo = new JLabel(deadAnimalInfo2);   
                mInfo.add(deadAnimalInfo);
            }
            mButton.add(printMedReport);
            mReportPanel.add(mButton);
            mReportPanel.setBorder(BorderFactory.createTitledBorder("Healing Report"));
            medReportPanel.add(mReportPanel);
    }

    public void nextButtonSwitch()
    {
        if(isMedicated && isFed)
        {
            nextButton.setEnabled(true);
        }
        else{
            nextButton.setEnabled(false);
        }
    }
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
        if(msg.getZooKeeperName()==null||msg.getZooKeeperName().trim().isEmpty())
        {
            System.exit(0);
            return;
        }
        zooKeeper = new ZooKeeper();
        zooKeeper.setName(msg.getZooKeeperName());
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
        nextButton = new JButton("Next ->");
        printFeedingList = new JButton("Print List");
        printMedicineList = new JButton("Print List");
        printFoodReport = new JButton("Print Report");
        printMedReport = new JButton("Print Report");
        printMedReport.setEnabled(false);
        printFoodReport.setEnabled(false);
        printFeedingList.setEnabled(false);
        
        feedButton = new JButton("Feed");
        feedButton.setEnabled(false);
        healButton = new JButton("Heal");
        printMedicineList.setEnabled(false);
        healButton.setEnabled(false);
        welcomePanel = new JPanel();
        animalPanel = new JPanel();
        foodPanel = new JPanel();
        hayAmount= new JTextField(4);
        fruitAmount = new JTextField();
        grainAmount = new JTextField();
        fishAmount = new JTextField();
        meatAmount = new JTextField();
        herbicineAmount = new JTextField();
        omnicineAmount = new JTextField();
        carnicineAmount = new JTextField();
        isFed = false;
        isMedicated = false;
        foodReportPanel = new JPanel();
        medReportPanel = new JPanel();
        addFoodButton = new JButton("Add ->");
        addMedicineButton = new JButton("Add ->");
        medicinePanel = new JPanel();
        foodButtonSwitch();
        addFoodButton.setEnabled(false);
        addMedicineButton.setEnabled(false);

        animalPanel.setLayout(new BoxLayout(animalPanel, BoxLayout.Y_AXIS));
        cagePosition = 0;
        hayAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
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
                // System.out.println(hayAmount.getText());
                } catch (Exception err) {
                    foodFieldManager();
                    addFoodButton.setEnabled(false);
                    hayAmount.setText("0");
                    System.out.println(err.getMessage());
                }
                
            }
        });
        fruitAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
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
                // System.out.println(fruitAmount.getText());
                } catch (Exception err) {
                    foodFieldManager();
                    fruitAmount.setText("0");
                    addFoodButton.setEnabled(false);
                    // System.out.println(err.getMessage());
                }
                
            }
        });

        grainAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
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
                // System.out.println(grainAmount.getText());
                } catch (Exception err) {
                    foodFieldManager();
                    grainAmount.setText("0");
                    addFoodButton.setEnabled(false);
                    System.out.println(err.getMessage());
                }
                
            }
        });

        fishAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
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
                // System.out.println(fishAmount.getText());
                } catch (Exception err) {
                    foodFieldManager();
                    fishAmount.setText("0");
                    addFoodButton.setEnabled(false);
                    System.out.println(err.getMessage());
                }
                
            }
        });

        meatAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
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
                System.out.println(meatAmount.getText());
                } catch (Exception err) {
                    foodFieldManager();
                    meatAmount.setText("0");
                    addFoodButton.setEnabled(false);
                    // System.out.println(err.getMessage());
                }
                
            }
        });

        herbicineAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
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
                // System.out.println(hayAmount.getText());
                } catch (Exception err) {
                    medicineFieldManager();
                    herbicineAmount.setText("0");
                    addMedicineButton.setEnabled(false);
                    System.out.println(err.getMessage());
                }
                
            }
        });

        omnicineAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
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
                // System.out.println(hayAmount.getText());
                } catch (Exception err) {
                    medicineFieldManager();
                    omnicineAmount.setText("0");
                    addMedicineButton.setEnabled(false);
                    System.out.println(err.getMessage());
                }
                
            }
        });
        
        carnicineAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
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
                // System.out.println(hayAmount.getText());
                } catch (Exception err) {
                    medicineFieldManager();
                    carnicineAmount.setText("0");
                    addMedicineButton.setEnabled(false);
                    System.out.println(err.getMessage());
                }
                
            }
        });

        nextButton.addActionListener(this);
        addFoodButton.addActionListener(this);
        printFeedingList.addActionListener(this);
        feedButton.addActionListener(this);
        printFoodReport.addActionListener(this);
        printMedReport.addActionListener(this);
        printMedicineList.addActionListener(this);
        healButton.addActionListener(this);
        addMedicineButton.addActionListener(this);

        if(theZoo.getCages().size() > 0 )
        {
            // do something is zoo is empty
        }
        displayAnimalPanel(0);
        displayWelcomePanel();
        displayFoodPanel();
        displayFeedReportPanel();
        displayMedicinePanel();
        displayMedReportPanel();
        //displayMedReportPanel();
        // add("West",westPanel);
        // foodPanel.setBorder(BorderFactory.createTitledBorder("Food Totals"));
        add(animalPanel);
        add(foodPanel);
        add(foodReportPanel);
        add(welcomePanel);
        add(medicinePanel);

        // westPanel.add(animalPanel);
        // westPanel.add(welcomePanel);
    
        add(medReportPanel);

        centerFrame();
        // maxFrame();
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==nextButton){
            try 
            {
                
                cagePosition++;
                if(cagePosition < theZoo.getCages().size())
                {
                    printFeedingList.setEnabled(false);
                    feedButton.setEnabled(false);
                    isFed=false;
                    isMedicated = false;
                    if(theZoo.getCages().get(cagePosition).getHungerStatus()>=5)
                    {
                        isFed = true;
                    }
                    if(theZoo.getCages().get(cagePosition).getHealthStatus()>=10)
                    {
                        isMedicated = true;
                    }
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

        if(e.getSource()==addFoodButton)
        {
            try 
            {
                isFed = true;
            foodButtonSwitch();
            int rowPosition = 0;
            int foodAmt = 0;
            String foodType = "";
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
                foodTotals[rowPosition][zonePosition] += foodAmt;
                animalFeeder.createMeal(theZoo.getCages().get(cagePosition).getCageID(), foodType, foodAmt);
                animalFeeder.addMeal();
                foodPanel.removeAll();
                foodPanel.revalidate();
                foodPanel.repaint();
                displayFoodPanel();
                // System.out.println(cageLetter[0]+" "+foodTotals[rowPosition][zonePosition]);
                //animalFeeder.printFeedingList();
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }

        if(e.getSource()==addMedicineButton)
        {
            try 
            {
            isMedicated = true;
            medButtonSwitch();
            int rowPosition = 0;
            int medicineAmt = 0;
            String medicineType = "";
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
                medicineTotals[rowPosition][zonePosition] += medicineAmt;
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
        if(e.getSource()==printFeedingList)
            {
                try
                {
                    animalFeeder.printFeedingList();
                    printFeedingList.setEnabled(false);
                }

                catch (Exception err) {
                System.out.println(err.getMessage());
                }
            }
        if(e.getSource() == feedButton)
        {
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

        if(e.getSource()==printFoodReport)
        {
            try
            {
                printFReport();
                printFoodReport.setEnabled(false);
            }
            catch(Exception err)
            {
                System.out.println(err.getMessage());   
            }

        }
        if(e.getSource() == printMedicineList)
        {
            animalHealer.printHealingList();
            printMedicineList.setEnabled(false);
        }
        if(e.getSource() == healButton)
        {
            try {
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
        if(e.getSource()==printMedReport)
        {
            try
            {
                printMReport();
                printMedReport.setEnabled(false);
            }
            catch(Exception err)
            {
                System.out.println(err.getMessage());   
            }

        }
    }
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
