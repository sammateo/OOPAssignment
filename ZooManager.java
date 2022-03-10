// Mateo Sam
// 400006967
import java.util.Scanner;
public class ZooManager 
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Zoo Keeper Program\nEnter your Zoo Keeper name: ");
        String zooKeeperName = scanner.nextLine();
        ZooKeeper zooKeeper = new ZooKeeper();
        zooKeeper.setName(zooKeeperName);
        Zoo theZoo = new Zoo(); //Create a new Zoo object named theZoo
        int choice = 0;

        // Menu to allow task selection
        System.out.println("Enter the number corresponding to the action you wish to do:");
        System.out.println("-------------------------------------");
        System.out.println("Menu");
        System.out.println("-------------------------------------");
        System.out.println("1. Add Animals to the zoo\n2. View all of the animals in the zoo");
        System.out.println("3. Feed animals\n4. Heal animals");
        System.out.println("5. Terminate the Program");
        System.out.print("Choice: ");
        choice = scanner.nextInt();
        System.out.println("-------------------------------------");
        String getline = scanner.nextLine();

        while(choice !=5)
        {
            if(choice == 1)
            {
                Animal newAnimal = new Animal(); //create a new animal
                System.out.println("Enter the species of the animal: ");
                String species = scanner.nextLine();
                System.out.println("Enter the name of the animal: ");
                String name = scanner.nextLine();
                System.out.println("Enter the age of the animal: ");
                int age = scanner.nextInt();
                newAnimal.setSpecies(species);  
                newAnimal.setName(name);
                newAnimal.setAge(age);
                theZoo.addAnimal(newAnimal);//Add the new animal to the zoo
                getline = scanner.nextLine();
            }
            else if(choice == 2)
            {
                theZoo.showAnimals();
            }
            else if (choice == 3)
            {
                zooKeeper.feedAnimals(theZoo);
            }
            else if (choice == 4)
            {
                zooKeeper.healAnimals(theZoo);
            }
            else
            {
                System.out.println("Incorrect Option");
            }
            // Menu to allow task selection
            System.out.println("Enter the number corresponding to the action you wish to do:");
            System.out.println("-------------------------------------");
            System.out.println("Menu");
            System.out.println("-------------------------------------");
            System.out.println("1. Add Animals to the zoo\n2. View all of the animals in the zoo");
            System.out.println("3. Feed animals\n4. Heal animals");
            System.out.println("5. Terminate the Program");
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            System.out.println("-------------------------------------");

            getline = scanner.nextLine();
        }
        System.out.println("Thank you for using the Zoo Keeper program " + zooKeeper.getName());
        scanner.close();
    }   //main
}   //ZooManager
