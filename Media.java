/* Arlo Insigne
    CMIS 242
    Project
    Purpose: Create a program that allows user to load media information from a text file, create an object using
    the information from the file, find the created object, modify the object, and rent the object.
 */
public class Media {

    //Media attributes
    private String title;
    private boolean available;
    private int id;
    private int year;

    //Constructor
    public Media(String title, boolean available, int id, int year){
        this.title = title;
        this.available = available;
        this.id  = id;
        this.year = year;
    }

    //Set method for the title
    public void setTitle(String title){
        this.title = title;
    }

    //Get method for the title
    public String getTitle(){
        return this.title;
    }

    //Set method for the available attribute
    public void setAvailable(boolean available){
        this.available = available;
    }

    //Get method for the available attribute
    public boolean getAvailable(){
        return this.available;
    }

    //Set method for the id attribute
    public void setId(int id){
        this.id = id;
    }

    //Get method for the id attribute
    public int getId(){
        return this.id;
    }

    //Set method for the year attribute
    public void setYear(int year){
        this.year = year;
    }

    //Get method for the year attribute
    public int getYear(){
        return this.year;
    }

    //Method that will print all the attribute
    public String toString(){
        return getTitle() + getId() + getAvailable() + getYear();
    }
}
