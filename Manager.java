import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/* Arlo Insigne
    CMIS 242
    Project
    Purpose: Create a program that allows user to load media information from a text file, create an object using
    the information from the file, find the created object, modify the object, and rent the object.
 */
public class Manager extends JFrame {

    //Creating arraylist to story media object created using the text file
    ArrayList<Media> mediaList = new ArrayList<>();

    public static void main(String[] args) {

        //Creating the window that will hold the menu
        JFrame window = new Manager();
        window.setSize(400, 200);
        window.setVisible(true);

    }

    //Initializing a file chooser that will be used to load the txt file
    private JFileChooser fileDialog;

    //Constructor for the manager class that allows the creation of the menu bar
    public Manager(){
        super("Welcome to Media Rental System");

        //Creating a menu bar and using the makeMenu method as a parameter to show the menu options
        setJMenuBar( makeMenus());

    }

    //Method that allows the menu components
    private JMenuBar makeMenus() {

            while(true) {

                //Creating an action listener that will be used by each menu button to perform certain tasks when selected
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String cmd = e.getActionCommand();

                        //if-else statement that will trigger function when the string cmd matches the string values
                        if (cmd.equalsIgnoreCase("Load Media objects...")) {
                            loadMedia();
                        } else if (cmd.equalsIgnoreCase("Find Media objects...")) {
                            findMedia();
                        } else if (cmd.equalsIgnoreCase("Rent Media objects...")) {
                            rentMedia();
                        } else if (cmd.equalsIgnoreCase("Quit")) {
                            quit();
                        }
                    }

                };

                //Creating the menu
                JMenu fileMenu = new JMenu("Menu");

                //Creating the load menu and assigning the action listener
                JMenuItem loadCmd = new JMenuItem("Load Media objects...");
                loadCmd.addActionListener(listener);
                fileMenu.add(loadCmd);
                loadCmd.setEnabled(true);


                //Creating the find menu and assigning the action listener
                JMenuItem findCmd = new JMenuItem("Find Media objects...");
                findCmd.addActionListener(listener);
                fileMenu.add(findCmd);
                findCmd.setEnabled(true);


                //Creating the rent menu and assigning the action listener
                JMenuItem rentCmd = new JMenuItem("Rent Media objects...");
                rentCmd.addActionListener(listener);
                fileMenu.add(rentCmd);
                rentCmd.setEnabled(true);

                fileMenu.addSeparator();


                //Creating the quit menu and assigning the action listener
                JMenuItem quitCmd = new JMenuItem("Quit");
                quitCmd.addActionListener(listener);
                fileMenu.add(quitCmd);

                quitCmd.setEnabled(true);

                //adding the filemenu created above to the menu bar
                JMenuBar bar = new JMenuBar();
                bar.add(fileMenu);
                return bar;

            }
    }

    //Load media objects
    public void loadMedia(){
                //FOLLOWED THE CLASS MATERIAL TO HELP CREATE THIS METHOD


                //Initializing the fileDialog created earlier
                fileDialog = new JFileChooser();

                //Creating an open button
                fileDialog.setDialogTitle("Open");

                fileDialog.setSelectedFile(null);

                int option = fileDialog.showOpenDialog(this);

                if (option != JFileChooser.APPROVE_OPTION) {

                    return;
                }

                //Getting the selected txt file
                File selectedFile = fileDialog.getSelectedFile();

                Scanner in;

                //Scanning the selected file to get the content
                try {

                    in = new Scanner(selectedFile);

                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(this, "Error has occured");

                    return;
                }

                //Using a string builder to store the content
                StringBuilder input = new StringBuilder();

                try {
                    //Parsing through the file and adding the content to the string builder
                    while (in.hasNextLine()) {
                        String line = in.nextLine();
                        if (line == null)
                            break;

                        input.append(line);

                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error has occurred");
                    return;

                } finally {
                    in.close();
                }

                //Using the sortMedia method to create the objects that will be stored in the array list
                sortMedia(String.valueOf(input));

        }

     //Method to find the object that matches user provided title
    public void findMedia(){

        //Getting user input for the title
        String input = JOptionPane.showInputDialog("Enter the title", null);
        
        try {

                String result = "";

                //Iterating through the array list to find the media object that matches the title
                for (int i = 0; i < mediaList.size(); i++) {
                    if (input.equalsIgnoreCase(mediaList.get(i).getTitle())) {

                        //Calls the toString method of the object that contains the user provided title
                        String message = mediaList.get(i).toString();
                        result += message + "\n";
                    }
                }

                //If-else statement that will return a message if the user input doesn match any object with the provided title
                if(!(result.isEmpty())) {
                    JOptionPane.showMessageDialog(null, result);
                } else {
                    JOptionPane.showMessageDialog(null, "There is no media with title=" + input);
                }

        } catch (Exception e){

            //Error message if the search field is left blank and the dialog is exited
            JOptionPane.showMessageDialog(null, "Invalid title value", "Message",
                    JOptionPane.ERROR_MESSAGE);
        }


    }

    public void rentMedia(){

        //Getting user input for the id to search
        String input = JOptionPane.showInputDialog("Enter the id", null);
        try {

            int id = Integer.parseInt(input);

            //Parsing through the array list to find the user provided id
            for(Media m : mediaList){
                //If the user provided id matches the id of an object set the available attribute to false
                if(m.getId() == id){
                    m.setAvailable(false);

                    //Message that will appear if the user successfully rented the media
                    JOptionPane.showMessageDialog(null, "Media was successfully rented. Rental fee = 2.00");
                    return;
                }
            }

            //Used if the id is not found
            for(Media n : mediaList){
                if(n.getId() != id){
                    JOptionPane.showMessageDialog(null, "The media object id=" + input +
                            " is not found");
                    break;
                } else {
                    return;
                }
            }


        }catch (Exception e) {
            //Not specified in the assignment prompt, but a message will appear if the form is left blank and the user closes the window
            JOptionPane.showMessageDialog(null, "Invalid id value", "Message", JOptionPane.ERROR_MESSAGE);

        }


    }

    //Method to terminate the program
    public void quit(){
        System.exit(0);
    }

    //Method that implements polymorphism to create an object based on the content of a txt file
    private void sortMedia(String list){

        String[] mediaStrings = list.split(":");
        if(mediaStrings[0].equalsIgnoreCase("EBook")){
            addEbook(mediaStrings[1], Boolean.parseBoolean(mediaStrings[2]), Integer.parseInt(mediaStrings[3]),
                    Integer.parseInt(mediaStrings[4]), Integer.parseInt(mediaStrings[5]));
        } else if (mediaStrings[0].equalsIgnoreCase("MovieDVD")){
            addMovieDVD(mediaStrings[1], Boolean.parseBoolean(mediaStrings[2]), Integer.parseInt(mediaStrings[3]),
                    Integer.parseInt(mediaStrings[4]), Double.parseDouble(mediaStrings[5]));
        } else if (mediaStrings[0].equalsIgnoreCase("MusicCD")){
            addMusicCD((mediaStrings[1]), Boolean.parseBoolean(mediaStrings[2]), Integer.parseInt(mediaStrings[3]),
                    Integer.parseInt(mediaStrings[4]), Double.parseDouble(mediaStrings[5]));
        }

    }

    //The following three private methods is used to add the object to the medialist array
    private void addEbook(String title, boolean available, int id, int year, int chapters){
        mediaList.add(new Ebook(title, available, id, year, chapters));

    }

    private void addMovieDVD(String title, boolean available, int id, int year, double size){
        mediaList.add(new MovieDVD(title, available, id, year, size));

    }

    private void addMusicCD(String title, boolean available, int id, int year, double size){
        mediaList.add(new MusicCD(title, available, id, year,size));

    }
}
