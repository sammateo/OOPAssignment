import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Welcome
{
    protected JLabel welcomeMessage;
    protected JTextField zooKeeperName;
    protected JButton continueButton;
    protected JPanel panel;
    public Welcome() 
    {
        welcomeMessage = new JLabel("Welcome to the Zoo Manager System");
        zooKeeperName = new JTextField();
        continueButton = new JButton("Continue");
        panel = new JPanel();
        

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }//Welcome constructor

    public void displayWelcome()
    {
        
        panel.add(welcomeMessage);
        panel.add(zooKeeperName);
        panel.add(continueButton);

    }//displayWelcome
}//Welcome
