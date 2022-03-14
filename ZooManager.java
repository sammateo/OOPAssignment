// Mateo Sam
// 400006967
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.IOException;

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
    private int cagePosition;
    private Zoo theZoo;
    private JButton addFoodButton;
    private JButton printFeedingList;
    private JTextField hayAmount;  
    private JTextField fruitAmount;
    private JTextField grainAmount;
    private JTextField fishAmount;     
    private JTextField meatAmount;
    private boolean isFed;     
    private GridBagConstraints gbc = new GridBagConstraints();
    public static void main(String[] args) throws IOException
    {
        // Welcome msg = new Welcome();
        // msg.displayWelcome();
        // if(msg.getZooKeeperName()==null||msg.getZooKeeperName().trim().isEmpty())
        // {
        //     System.exit(0);
        //     return;
        // }
        ZooManager zm = new ZooManager();
        
    }   //main

    public void displayAnimalPanel(int position) throws IOException
    {
        remove(animalPanel);
        
        // theZoo.readAnimals();
        int i = position;
        JLabel animalID = new JLabel();
        JLabel animalNameLabel = new JLabel();
        JLabel animalAge = new JLabel();
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
        
        animalPanel.add(animalID);
        animalPanel.add(animalNameLabel); 
        animalPanel.add(animalSpecies);
        animalPanel.add(animalType);
        animalPanel.add(animalHunger);
        animalPanel.add(animalHealth);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(animalPanel,gbc);
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
        JPanel foodInfoPanel = new JPanel();
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
        foodPanel.add(foodInfoPanel);

        
        foodPanel.add(addFoodButton);

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
        foodPanel.add(foodTotalsPanel);
    }

    public void displayFeedListPanel()
    {
        JPanel fReportPanel = new JPanel();
        JPanel info = new JPanel();
        
            //JLabel animalAge = new JLabel("Age: "+Integer.toString(theZoo.getCages().get(i).getAge()));
        fReportPanel.setLayout(new GridLayout(6,10,10,10));
        info.setLayout(new GridLayout(2,2,2,2));

        JLabel test = new JLabel("This is a test");
        JScrollPane scroll = new JScrollPane(info);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        fReportPanel.add(test);
        fReportPanel.add(scroll);
        
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
        fReportPanel.setBorder(BorderFactory.createTitledBorder("Feeding Report"));
        foodReportPanel.add(fReportPanel);

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

    public ZooManager() throws IOException
    {

        theZoo = new Zoo(); //initializes the zoo object
        theZoo.readAnimals();   //reads animals from text file
        animalFeeder = new AnimalFeeder(theZoo.getCages()); //initializes animal feeder object with cages
        foodTotals = new int[5][4]; //[rows][columns]
        setLayout(new GridBagLayout());
        setSize(900,700);
        setTitle("Zoo Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nextButton = new JButton("Next ->");
        printFeedingList = new JButton("Print List");
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
        addFoodButton.setEnabled(false);
        buttonswitch();

        // foodPanel.setLayout(new BoxLayout(foodPanel, BoxLayout.Y_AXIS)); 
        animalPanel.setBorder(BorderFactory.createTitledBorder("Animal"));
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
                System.out.println(fruitAmount.getText());
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
        
        if(theZoo.getCages().size() > 0 )
            displayAnimalPanel(0);
        displayWelcomePanel();
        displayFoodPanel();
        displayFeedListPanel();
        //displayMedListPanel();
        // add("West",westPanel);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.weighty = 0.1;
        gbc.weightx = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(animalPanel,gbc);
        // gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nextButton,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        // gbc.fill = GridBagConstraints.VERTICAL;
        add(welcomePanel,gbc);
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        // gbc.anchor = GridBagConstraints.PAGE_START;
        add(foodPanel,gbc);
        // westPanel.add(animalPanel);
        // westPanel.add(welcomePanel);
        add(foodReportPanel,gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        // gbc.gridheight=2;
    
        add(foodReportPanel,gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;

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
                        isFed=false;
                        buttonswitch();
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
                        // TODO Auto-generated catch block
                        // e1.printStackTrace();
                        System.out.println("Error: "+ e1);
                    }
                }
            } catch (Exception err) {
                //TODO: handle exception
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
                animalFeeder.printFeedingList();
            } catch (Exception err) {
                //TODO: handle exception
                System.out.println(err.getMessage());
            }
            
        }
        // if(e.getSource()==hayAmount)
        // {
        //     System.out.println("Hi");
        // }

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
