// Mateo Sam
// 400006967
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// import java.util.Scanner;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.io.FileWriter;


public class ZooManager extends JFrame implements ActionListener
{   
    private AnimalFeeder animalFeeder;
    private JPanel westPanel;
    private JPanel animalPanel;
    private JPanel welcomePanel;
    private JPanel foodPanel;
    private JPanel foodReportPanel;
    private int foodTotals[][];
    private JPanel medReportPanel;
    private JButton nextButton;
    private int cagePosition;   //to keep track of the current animal being worked on
    private Zoo theZoo;
    private JButton addFoodButton;
    private JButton printFeedingList;
    private JButton printFoodReport;
    private JButton feedButton;
    private JTextField hayAmount;  
    private JTextField fruitAmount;
    private JTextField grainAmount;
    private JTextField fishAmount;     
    private JTextField meatAmount;
    private boolean isFed;     
    public static void main(String[] args) throws IOException
    {
        Welcome msg = new Welcome();
        msg.displayWelcome();
        if(msg.getZooKeeperName()==null||msg.getZooKeeperName().trim().isEmpty())
        {
            System.exit(0);
            return;
        }
        ZooManager zm = new ZooManager();
        
    }   //main

    public void displayAnimalPanel(int position) throws IOException
    {
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        JPanel nxtContainer = new JPanel();
        nxtContainer.setLayout(new FlowLayout());
        info.setPreferredSize(new Dimension(200, 200));
        // theZoo.readAnimals();
        int i = position;
        JLabel animalID = new JLabel();
        JLabel animalNameLabel = new JLabel();
        // JLabel animalAge = new JLabel();
        JLabel animalSpecies = new JLabel();
        JLabel animalType = new JLabel();
        JLabel animalHunger = new JLabel();
        JLabel animalHealth = new JLabel();
        
        animalID.setText("Cage ID: " + theZoo.getCages().get(i).getCageID());
        animalNameLabel.setText("Name: "+theZoo.getCages().get(i).getName());
        // animalAge.setText("Age: "+Integer.toString(theZoo.getCages().get(i).getAge()));
        animalSpecies.setText("Species: "+theZoo.getCages().get(i).getSpecies());
        animalType.setText("Category: "+theZoo.getCages().get(i).getCategory());
        animalHunger.setText("Hunger: "+theZoo.getCages().get(i).getHungerStatus()+"/5");
        animalHealth.setText("Health: "+theZoo.getCages().get(i).getHealthStatus()+"/10");
        
        info.add(animalID);
        info.add(animalNameLabel); 
        info.add(animalSpecies);
        info.add(animalType);
        info.add(animalHunger);
        info.add(animalHealth);
        info.setBorder(BorderFactory.createTitledBorder("Animal"));
        nxtContainer.add(nextButton);

        animalPanel.add(info);
        animalPanel.add(nxtContainer);
        revalidate();
        repaint();
    }
    public void categoryFieldManager()
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
    public void displayWelcomePanel()
    {
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        JLabel welcomeMessage = new JLabel("Welcome to the Zoo Manager System");
        welcomePanel.add(welcomeMessage);
        JButton logo = new JButton("ZooKeeper 2.0 LOGO");
        welcomePanel.add(logo);
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
        

        categoryFieldManager();
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
        foodTotalsPanel.setLayout(new GridLayout(6,4,10,10));
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

    public void displayFeedListPanel()
    {
            JPanel fReportPanel = new JPanel();
            JPanel info = new JPanel();
            JPanel button = new JPanel();
            fReportPanel.setPreferredSize(new Dimension(400, 200));
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
            System.out.println("Error");
        }
    }

    public void displayMedListPanel()
    {
        JPanel mReportPanel = new JPanel();
        JPanel info = new JPanel();
        
            //JLabel animalAge = new JLabel("Age: "+Integer.toString(theZoo.getCages().get(i).getAge()));

        mReportPanel.setLayout(new GridLayout(6,10,10,10));
        info.setLayout(new GridLayout(2,2,2,2));

        JLabel test = new JLabel("This is a test");
        JScrollPane scroll = new JScrollPane(info);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        mReportPanel.add(test);
        mReportPanel.add(scroll);
        
        for(int i=0; i< theZoo.getCages().size();i++)
        {
            JLabel animalID = new JLabel();
            JLabel animalNameLabel= new JLabel();
            JLabel animalSpecies = new JLabel();
            JLabel animalType = new JLabel(); 
            JLabel animalHunger= new JLabel();
            JLabel animalHealth= new JLabel();

            animalID.setText("Cage ID: " + theZoo.getCages().get(i).getCageID());
            animalNameLabel.setText("Name: "+theZoo.getCages().get(i).getName()); 
            animalSpecies.setText("Species: "+theZoo.getCages().get(i).getSpecies());
            animalType.setText("Category: "+theZoo.getCages().get(i).getCategory());
            animalHunger.setText("Hunger: "+theZoo.getCages().get(i).getHungerStatus()+"/5");
            animalHealth.setText("Health: "+theZoo.getCages().get(i).getHealthStatus()+"/10");
            //animalPanel.add(animalAge);

            info.add(animalID);
        }
        mReportPanel.setBorder(BorderFactory.createTitledBorder("Feeding Report"));
        foodReportPanel.add(mReportPanel);

    }

    public void buttonswitch()
        {
            
            if(isFed == true)
            {
                addFoodButton.setEnabled(false);
                nextButton.setEnabled(true);
            }

            else
            {
                addFoodButton.setEnabled(true);
                nextButton.setEnabled(false);
            }
            
        }//buttonswitch

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

        theZoo = new Zoo(); //initializes the zoo object
        theZoo.readAnimals();   //reads animals from text file
        animalFeeder = new AnimalFeeder(theZoo.getCages()); //initializes animal feeder object with cages
        foodTotals = new int[5][4]; //[rows][columns]
        setLayout(new GridLayout(2,3));
        setSize(1080,720);
        setTitle("The Cave Hill Zoo Manager System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nextButton = new JButton("Next ->");
        printFeedingList = new JButton("Print List");
        printFoodReport = new JButton("Print Report");
        printFoodReport.setEnabled(false);
        printFeedingList.setEnabled(false);
        feedButton = new JButton("Feed");
        feedButton.setEnabled(false);
        westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        welcomePanel = new JPanel();
        animalPanel = new JPanel();
        foodPanel = new JPanel();
        hayAmount= new JTextField(4);
        fruitAmount = new JTextField();
        grainAmount = new JTextField();
        fishAmount = new JTextField();
        meatAmount = new JTextField();
        isFed = false;
        foodReportPanel = new JPanel();
        addFoodButton = new JButton("Add ->");
        
        buttonswitch();
        addFoodButton.setEnabled(false);
        // foodPanel.setLayout(new BoxLayout(foodPanel, BoxLayout.Y_AXIS)); 
        // animalPanel.setBorder(BorderFactory.createTitledBorder("Animal"));
        // animalPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        animalPanel.setLayout(new BoxLayout(animalPanel, BoxLayout.Y_AXIS));
        cagePosition = 0;
        // hayAmount.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         System.out.println(hayAmount.getText());
        //     }
        // });

    
        hayAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
                    if(hayAmount.getText() == null) {
                        categoryFieldManager();
                        addFoodButton.setEnabled(false);
                        return;
                    }
                else if(hayAmount.getText().trim().isEmpty())
                {
                    categoryFieldManager();
                    addFoodButton.setEnabled(false);

                }
                else if(Integer.parseInt(hayAmount.getText()) > 0)
                {
                    fruitAmount.setEnabled(false);
                    grainAmount.setEnabled(false);
                    fishAmount.setEnabled(false);
                    meatAmount.setEnabled(false);
                    buttonswitch();
                }
                else{
                    categoryFieldManager();
                    addFoodButton.setEnabled(false);

                }
                // System.out.println(hayAmount.getText());
                } catch (Exception err) {
                    categoryFieldManager();
                    addFoodButton.setEnabled(false);
                    System.out.println(err.getMessage());
                }
                
            }
        });
        fruitAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try 
                {
                    if(fruitAmount.getText() == null) {
                        categoryFieldManager();
                        addFoodButton.setEnabled(false);
                        return;
                    }
                else if(fruitAmount.getText().trim().isEmpty())
                {
                    categoryFieldManager();
                    addFoodButton.setEnabled(false);

                }
                else if(Integer.parseInt(fruitAmount.getText()) > 0)
                {
                    hayAmount.setEnabled(false);
                    grainAmount.setEnabled(false);
                    fishAmount.setEnabled(false);
                    meatAmount.setEnabled(false);
                    buttonswitch();

                }
                else{
                    categoryFieldManager();
                    addFoodButton.setEnabled(false);

                }
                // System.out.println(fruitAmount.getText());
                } catch (Exception err) {
                    categoryFieldManager();
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
                        categoryFieldManager();
                        addFoodButton.setEnabled(false);
                        return;
                    }
                else if(grainAmount.getText().trim().isEmpty())
                {
                    categoryFieldManager();
                    addFoodButton.setEnabled(false);

                }
                else if(Integer.parseInt(grainAmount.getText()) > 0)
                {
                    hayAmount.setEnabled(false);
                    fruitAmount.setEnabled(false);
                    fishAmount.setEnabled(false);
                    meatAmount.setEnabled(false);
                    buttonswitch();

                }
                else{
                    categoryFieldManager();
                    addFoodButton.setEnabled(false);

                }
                // System.out.println(grainAmount.getText());
                } catch (Exception err) {
                    categoryFieldManager();
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
                        categoryFieldManager();
                        addFoodButton.setEnabled(false);
                        return;
                    }
                else if(fishAmount.getText().trim().isEmpty())
                {
                    categoryFieldManager();
                    addFoodButton.setEnabled(false);

                }
                else if(Integer.parseInt(fishAmount.getText()) > 0)
                {
                    hayAmount.setEnabled(false);
                    grainAmount.setEnabled(false);
                    fruitAmount.setEnabled(false);
                    meatAmount.setEnabled(false);
                    buttonswitch();

                }
                else{
                    categoryFieldManager();
                    addFoodButton.setEnabled(false);

                }
                // System.out.println(fishAmount.getText());
                } catch (Exception err) {
                    categoryFieldManager();
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
                        categoryFieldManager();
                        addFoodButton.setEnabled(false);
                        return;
                    }
                else if(meatAmount.getText().trim().isEmpty())
                {
                    categoryFieldManager();
                    addFoodButton.setEnabled(false);

                }
                else if(Integer.parseInt(meatAmount.getText()) > 0)
                {
                    hayAmount.setEnabled(false);
                    grainAmount.setEnabled(false);
                    fishAmount.setEnabled(false);
                    fruitAmount.setEnabled(false);
                    buttonswitch();
                }
                else{
                    categoryFieldManager();
                    addFoodButton.setEnabled(false);

                }
                System.out.println(meatAmount.getText());
                } catch (Exception err) {
                    categoryFieldManager();
                    addFoodButton.setEnabled(false);
                    // System.out.println(err.getMessage());
                }
                
            }
        });


        nextButton.addActionListener(this);
        addFoodButton.addActionListener(this);
        printFeedingList.addActionListener(this);
        feedButton.addActionListener(this);
        printFoodReport.addActionListener(this);
        
        if(theZoo.getCages().size() > 0 )
            displayAnimalPanel(0);
        displayWelcomePanel();
        displayFoodPanel();
        displayFeedListPanel();
        //displayMedListPanel();
        // add("West",westPanel);
        add(animalPanel);
        add(foodPanel);
        add(foodReportPanel);
        add(welcomePanel);

        // westPanel.add(animalPanel);
        // westPanel.add(welcomePanel);
    
        //add(medReportPanel);

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
                    try 
                    {
                        printFeedingList.setEnabled(false);
                        feedButton.setEnabled(false);
                        isFed=false;
                        buttonswitch();
                        addFoodButton.setEnabled(false);
                        printFoodReport.setEnabled(false);
                        animalPanel.removeAll();
                        animalPanel.revalidate();
                        animalPanel.repaint();
                        displayAnimalPanel(cagePosition);
                        hayAmount.setText("");
                        fruitAmount.setText("");
                        grainAmount.setText("");
                        fishAmount.setText("");
                        meatAmount.setText("");
                        foodPanel.removeAll();
                        foodPanel.revalidate();
                        foodPanel.repaint();
                        displayFoodPanel();
                        // System.out.println(cagePosition);
                    } catch (IOException e1) 
                    {
                        // e1.printStackTrace();
                        System.out.println("Error: "+ e1);
                    }
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
                }
            } catch (Exception err) {
            }
        }

        if(e.getSource()==addFoodButton)
        {
            try 
            {
                isFed = true;
            buttonswitch();
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
                animalFeeder.addMeal(theZoo.getCages().get(cagePosition).getCageID(), foodType, foodAmt);
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
                displayFeedListPanel();
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

    }
    private void centerFrame() 
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        setLocation(x, y);
    }
}   //ZooManager
