package edu.quinnipiac.ser210.starbuzz;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzz";
    private static final int DB_VERSION = 2;

    StarbuzzDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase,0,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        updateMyDatabase(sqLiteDatabase,oldVersion,newVersion);
    }

    private static void insertDrink(SQLiteDatabase db, String name, String desc, int resourceId){
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", desc);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("DRINK",null,drinkValues);
    }

    private void updateMyDatabase(SQLiteDatabase db,int oldVersion,int newVersion){
        if(oldVersion < 1){
            db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER) ;"
            );
            insertDrink(db,"Latte","Espresso and steamed milk", R.drawable.latte);
            insertDrink(db,"Cappuccino","Espresso, hot milk and steamed-milk foam",R.drawable.cappuccino);
            insertDrink(db,"Filter","Our best drip coffee",R.drawable.filter);
        }
        if(oldVersion < 2){
            //add extra column
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }
    }
}
