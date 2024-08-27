/* Arlo Insigne
    CMIS 242
    Project
    Purpose: Create a program that allows user to load media information from a text file, create an object using
    the information from the file, find the created object, modify the object, and rent the object.
 */
public class Ebook extends Media{

    //Additional attribute for the Ebook object that specifies the number of chapters
    private int chapters;

    //Ebook constructor
    Ebook(String title, boolean available, int id, int year, int chapters) {
        super(title, available, id, year);
        this.chapters = chapters;
    }

    //Set method for the chapters attribute

    public void setChapters(int chapters){
        this.chapters = chapters;
    }

    //Get method for the chapters attribute
    public int getChapters(){
        return this.chapters;
    }

    //Method that will print Ebook objects' attributes when called
    @Override
    public String toString(){

        return "EBook" + "[" + "title=" + getTitle() + ", " + "year=" + getYear() + ", " + "Id=" + getId() + ", " +
                "available=" + getAvailable() + ", " + "chapters=" + getChapters() + "]" + "\n";
    }
}
