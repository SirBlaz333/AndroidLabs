package com.arsenii.android.lab5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.Optional;

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "lab5";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "users";
    public static final String USER_ID = "user_id";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String IMAGE = "image";

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRSTNAME + " TEXT NOT NULL, "
                + LASTNAME + " TEXT NOT NULL, "
                + EMAIL + " TEXT NOT NULL, "
                + ADDRESS + " TEXT NOT NULL, "
                + IMAGE + " TEXT);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldValue, int newValue) {
        String dropTable = "DROP TABLE " + TABLE_NAME + ";";
        sqLiteDatabase.execSQL(dropTable);
        onCreate(sqLiteDatabase);
    }

    public void deleteUser(String id) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME, USER_ID + "=?", new String[]{id});
        database.close();
    }

    public void addUser(String id, String firstName, String lastName, String email, String address, String photo){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, Integer.parseInt(id));
        contentValues.put(FIRSTNAME, firstName);
        contentValues.put(LASTNAME, lastName);
        contentValues.put(EMAIL, email);
        contentValues.put(ADDRESS, address);
        contentValues.put(IMAGE, photo);
        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }

    public void update(String id, String firstName, String lastName, String email, String address, String photo) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(!firstName.isEmpty()){
            contentValues.put(FIRSTNAME, firstName);
        }
        if(!lastName.isEmpty()){
            contentValues.put(LASTNAME, lastName);
        }
        if(!email.isEmpty()){
            contentValues.put(EMAIL, email);
        }
        if(!address.isEmpty()){
            contentValues.put(ADDRESS, address);
        }
        if(!photo.isEmpty()){
            contentValues.put(IMAGE, photo);
        }
        if(contentValues.size() > 0) {
            database.update(TABLE_NAME, contentValues, USER_ID + "=?", new String[]{id});
        }
        database.close();
    }

    public Optional<User> select(String id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME,
                new String[]{
                    USER_ID,
                    FIRSTNAME,
                    LASTNAME,
                    EMAIL,
                    ADDRESS,
                    IMAGE
                },
                USER_ID + "=?",
                new String[]{id},
                null, null, null
                );
        User user = null;
        if(cursor.moveToNext()) {
            String firstName = cursor.getString(1);
            String lastName = cursor.getString(2);
            String email = cursor.getString(3);
            String address = cursor.getString(4);
            String photo = cursor.getString(5);
            user = new User(firstName, lastName, email, address, photo);
        }
        cursor.close();
        return Optional.ofNullable(user);
    }
}
