// Mateo Sam and Robali Sewitt
// 400006967 and 400007056

import javax.swing.*;
public class Welcome
{
    private String zooKeeperName;
    private JFrame welcomeFrame;
    public Welcome() 
    {
        zooKeeperName = null;
    }//Welcome constructor

    public void displayWelcome()
    {
        
        zooKeeperName = JOptionPane.showInputDialog(null, "Welcome to the Cave Hill Zoo Manager System \nEnter your name","Welcome", JOptionPane.INFORMATION_MESSAGE);

       //Exits the program if the cancel button is clicked
        if(zooKeeperName==null)
        {
            return;
        }

        //Checks to see if a name is entered in the text field
        while (zooKeeperName.trim().isEmpty())
        {
            JOptionPane.showMessageDialog(welcomeFrame, "The amount you entered is invalid");
            zooKeeperName = JOptionPane.showInputDialog(welcomeFrame, "Welcome to the Zoo Manager System \nEnter your name");
            if(zooKeeperName==null)
            {
                return;
            }
        }
        
    }//displayWelcome

    public String getZooKeeperName() {
        return zooKeeperName;
    }//getZooKeeperName
}//Welcome
