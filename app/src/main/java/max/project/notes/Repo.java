package max.project.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Repo extends SQLiteOpenHelper {



    public  interface Listener{
        void onDataChange();
    }


    private static Repo sInstance;

    public static Repo getInstance(Context context){
        if(sInstance == null){
            sInstance = new Repo(context.getApplicationContext());
        }
        return sInstance;
    }

    private static final String DB_NAME = "notes";
    private static final int VERSION = 6;
    private static final String  TABLE_NAME = "Notes";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DATA = "date";
    private static final String DESC = "description";

    private static final String CREATE_SQL =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY, " +
                    TITLE + " TEXT NOT NULL, " +
                    DATA + " DATE NOT NULL, " +
                    DESC + " TEXT NOT NULL"
            + ")";


    private final Set<Listener> mListener = new HashSet<>();

    private Repo(Context context){

        super( context , DB_NAME , null , VERSION );

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    private void addNotesInner(SQLiteDatabase db, String title, String data , String description ) {
        ContentValues cv = new ContentValues();
        cv.put(TITLE, title);
        cv.put(DATA , data);
        cv.put(DESC, description);
        db.insert(TABLE_NAME, null, cv);
    }



    public List<Notes> getNotes(){

        String[] cols = {ID , TITLE , DATA , DESC};
        Cursor c = getReadableDatabase().query(TABLE_NAME , cols , null ,
                null , null,
                null, ID + " DESC");

        List<Notes> list = new ArrayList<>(c.getColumnCount());

        while (c.moveToNext()){
            long id = c.getLong(0);
            String title = c.getString(1);
            String data = c.getString(2);
            String description = c.getString(3);
            list.add(new Notes(id , title , data , description));
        }
        c.close();
        return list;

    }


    public Notes getNote(long id) {

        String[] cols = {ID , TITLE , DATA , DESC };
        try(Cursor c = getReadableDatabase().query(TABLE_NAME , cols , ID + " = " + id
        ,null , null , null , null)) {
            if(c.moveToNext()){
                return new Notes(c.getLong(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3));
            }
        }
        return null;

    }

    public void addNote(String title , String date,  String description ){
        addNotesInner(getWritableDatabase() ,  title, date , description);
        notifyChange();
    }



    public void updateNote(long id , String title , String date , String description ){

        ContentValues cv = new ContentValues();
        cv.put(TITLE , title);
        cv.put(DATA , date);
        cv.put(DESC , description);
        getWritableDatabase().update(TABLE_NAME , cv , ID + " = " + id , null);
        notifyChange();

    }

    public void deleteNote(long id){
        getWritableDatabase().delete(TABLE_NAME , ID + " = " + id , null);
        notifyChange();
    }

    public void notifyChange(){
        for (Listener listener : mListener){
            listener.onDataChange();
        }
    }


    public void addListener(Listener listener) {
        mListener.add(listener);

    }

    public void removeListener(Listener listener){
        mListener.remove(listener);
    }

}
