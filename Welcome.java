import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Welcome
{
    private String zooKeeperName;
    private JFrame welcomeFrame;
    public Welcome() 
    {
        
        // welcomeMessage = new JLabel("Welcome to the Zoo Manager System");
        zooKeeperName = null;
        
        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }//Welcome constructor

    public void displayWelcome()
    {
        welcomeFrame = new JFrame();
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        zooKeeperName = JOptionPane.showInputDialog(welcomeFrame, "Welcome to the Zoo Manager System \nEnter your name");
        if(zooKeeperName==null)
        {
            return;
        }
        while (zooKeeperName.trim().isEmpty())
        {
            JOptionPane.showMessageDialog(welcomeFrame, "The amount you entered is invalid");
            zooKeeperName = JOptionPane.showInputDialog(welcomeFrame, "Welcome to the Zoo Manager System \nEnter your name");

        }
    }//displayWelcome

    public String getZooKeeperName() {
        return zooKeeperName;
    }
}//Welcome
