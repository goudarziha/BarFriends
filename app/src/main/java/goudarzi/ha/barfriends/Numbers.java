package goudarzi.ha.barfriends;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

public class Numbers {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "persons_name";
    public static final String KEY_NUMBER = "persons_number";

    private static final String DATABASE_NAME = "Numberdb";
    private static final String DATABASE_TABLE = "peopleTable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private Context ourContext;
    private SQLiteDatabase ourDatabase;

    public String getName(long l) throws SQLException {
        String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_NUMBER };
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
        if ( c != null) {
            c.moveToFirst();
            String name = c.getString(1);
            return name;
        }
        return null;
    }

    public String getNumber(long l) throws SQLException {
        String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_NUMBER };
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
        if ( c != null) {
            c.moveToFirst();
            String number = c.getString(3);
            return number;
        }
        return null;
    }

    public void updateEntry(int lRow, String sName, String sNumber) throws SQLException  {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_NAME, sName);
        cvUpdate.put(KEY_NUMBER, sNumber);
        ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
    }

    public void deleteEntry(int iRow) throws SQLException {
        ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + iRow, null);
    }

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_NUMBER + " TEXT NOT NULL);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i2) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public Numbers(Context c) {
        ourContext = c;
    }

    public Numbers open() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }

    public long createEntry(String names, String numbers) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, names);
        cv.put(KEY_NUMBER, numbers);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData() {
        String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_NUMBER };
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iNumber = c.getColumnIndex(KEY_NUMBER);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRow) + " " + c.getString(iName) + "\t\t\t" +
                    c.getString(iNumber) + "\n";
        }
        return result;
    }
}
