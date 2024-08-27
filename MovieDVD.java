/* Arlo Insigne
    CMIS 242
    Project
    Purpose: Create a program that allows user to load media information from a text file, create an object using
    the information from the file, find the created object, modify the object, and rent the object.
 */
public class MovieDVD extends Media{

    //Additional moviedvd attribute that sepcifies the size of the movie
    private double size;

    //Constructor
    MovieDVD(String title, boolean available, int id, int year, double size) {
        super(title, available, id, year);
        this.size = size;
    }

    //Set method for the size attribute
    public void setSize(double size){
        this.size = size;
    }

    //Get method for the size attribute
    public double getSize(){
        return this.size;
    }

    //Method that will print MovieDVD objects' attributes when called
    @Override
    public String toString(){
        return "MovieDVD" + "[" + "title=" + getTitle() + ", " + "year=" + getYear() + ", " + "Id=" + getId() + ", " +
                "available=" + getAvailable() + ", " + "Size=" + getSize() + "MB" + "]" + "\n";
    }
}
