package max.project.notes;


public class Notes {

    public final long id;
    public final String title;
    public final String description;
    public final String date;

    public Notes(long id , String title , String date  , String description ){
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
    }


}
