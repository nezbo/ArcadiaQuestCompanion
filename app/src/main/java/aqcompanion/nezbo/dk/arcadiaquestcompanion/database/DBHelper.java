package aqcompanion.nezbo.dk.arcadiaquestcompanion.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Emil on 15-11-2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    // PRIVATE FIELDS

    private final Context context;
    private final String db_name;

    public DBHelper(Context c, String db_name) {
        super(c, db_name, null, DB_VERSION);
        this.context = c;
        this.db_name = db_name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        executeSQLFile(db, db_name + "-source.sql");
        System.out.println("Database created.");
        this.onUpgrade(db, 0, DB_VERSION);
    }

    private void executeSQLFile(SQLiteDatabase db, String sql_filename) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.context.getAssets().open(sql_filename)));
            while (reader.ready()) {
                try {
                    String stmt = reader.readLine();
                    if(!stmt.startsWith("--")) {
                        System.out.println(stmt);
                        db.execSQL(stmt);
                    }
                }catch(SQLException e) {
                    if(!e.getMessage().contains("not an error")) {
                        throw e;
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // File not found, do nothing
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // UPDATE

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Try to perform iterative upgrades for all versions
        for (int i = oldVersion + 1; i <= newVersion; i++) {
            executeSQLFile(db, db_name + "-update-v" + i + ".sql");
            System.out.println("Database upgraded to version " + i);
        }
    }
}

