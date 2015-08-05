package demo.abhi.mynotes.mynotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AbhishekShetty on 8/4/2015.
 */
class DatabaseHelper extends SQLiteOpenHelper {
     static final String DB_Name = "MyNotesDb";
     static final String TABLE_NAME = "MyNotesTable";
     static final int DB_Version = 1;//helps in identifying different database schema ....accordingly on upgrade will be called
     static final String PRIMARY_KEY = "_id";
     static final String HEADER_COLL = "Header";
     static final String CONTENT_COLL = "Content";

    public DatabaseHelper(Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatement = "CREATE TABLE " +
                TABLE_NAME +
                " ( " +
                PRIMARY_KEY +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HEADER_COLL +
                " VARCHAR(255), " +
                CONTENT_COLL +
                " VARCHAR(255) " +
                " );";
        try {
            db.execSQL(createStatement);

            db.execSQL("INSERT INTO MyNotesTable (Header,Content)" +
                    "VALUES ('Head1','content1');");

            db.execSQL("INSERT INTO MyNotesTable (Header,Content)" +
                    "VALUES ('Head2','content2');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
