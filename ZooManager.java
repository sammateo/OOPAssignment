// Mateo Sam
// 400006967
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.IOException;

public class ZooManager extends JFrame implements ActionListener
{   
    JPanel westPanel;
    JPanel animalPanel;
    JPanel welcomePanel;
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

    public void displayAnimalPanel() throws IOException
    {
        Zoo theZoo = new Zoo();
        theZoo.readAnimals();
        // Animal animal = new Animal();
        // animal.setName("John");
        // animal.setAge(20);
        // animal.setSpecies("Pig");
        for(int i=0; i< theZoo.getCages().size();i++)
        {
            JLabel animalNameLabel = new JLabel("Name: "+theZoo.getCages().get(i).getName());
            JLabel animalAge = new JLabel("Age: "+Integer.toString(theZoo.getCages().get(i).getAge()));
            animalPanel.add(animalNameLabel);
            animalPanel.add(animalAge);
        }
        
    }

    public void displayWelcomePanel()
    {
        JLabel welcomeMessage = new JLabel("Welcome to the Zoo Manager System");
        welcomePanel.add(welcomeMessage);
    }

    public ZooManager() throws IOException
    {
        setLayout(new BorderLayout());
            setSize(900,700);
            setTitle("Zoo Manager");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            westPanel = new JPanel();
            westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
            welcomePanel = new JPanel();
            animalPanel = new JPanel();
            animalPanel.setBorder(BorderFactory.createTitledBorder("Animal"));
            animalPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            animalPanel.setLayout(new BoxLayout(animalPanel, BoxLayout.Y_AXIS));
            // Initialize elementsdv
            // toFahrenheit = new JButton("Convert to Fahrenheit");
            // toCelsius = new JButton("Convert to Celsius");
            // tempIn = new JTextField(5);
            // enterTemp = new JLabel("Enter temperature: ");
            // resultLabel = new JLabel("Result: ");
            // resultValue = new JLabel("---");
            // Set up the event listeners
            // tempIn.addActionListener(this);
            // toFahrenheit.addActionListener(this);
            // toCelsius.addActionListener(this);
            // Organize the top panel
            // JPanel top = new JPanel();
            // add(enterTemp);
            // add(tempIn);
            // add(toFahrenheit);
            // add(toCelsius);
            // add(resultLabel);
            // add(resultValue);
            // setLayout(new FlowLayout());
            // position the top panel at the north of the layout
            // add("North",top);
            displayAnimalPanel();
            displayWelcomePanel();
            add("West",westPanel);
            westPanel.add(animalPanel);
            westPanel.add(welcomePanel);
            centerFrame();
            // maxFrame();
            setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {

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
