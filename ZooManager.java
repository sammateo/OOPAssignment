// Mateo Sam
// 400006967
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
public class ZooManager extends JFrame implements ActionListener
{
    public static void main(String[] args)
    {
        ZooManager zm = new ZooManager();
        Welcome msg = new Welcome();
        msg.displayWelcome();
    }   //main

    public ZooManager()
    {
        setLayout(new BorderLayout());
            setSize(900,700);
            setTitle("Zoo Manager");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
