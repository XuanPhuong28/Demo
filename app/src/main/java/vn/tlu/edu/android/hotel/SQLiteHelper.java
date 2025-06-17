package vn.tlu.edu.android.hotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_PATH_SUFFIX = "/databases/";
    public static final String TABLE_USERS = "users";
    public static final String KEY_ID = "id";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    private final Context context;
    private SQLiteDatabase database;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        copyDatabaseFromAssetsIfNeeded();
    }

    private void copyDatabaseFromAssetsIfNeeded() {
        String dbPath = getDatabasePath();
        File dbFile = new File(dbPath);
        if (!dbFile.exists()) {
            try {
                InputStream input = context.getAssets().open(DATABASE_NAME);
                File dbDir = new File(context.getApplicationInfo().dataDir + DATABASE_PATH_SUFFIX);
                if (!dbDir.exists()) dbDir.mkdir();

                OutputStream output = new FileOutputStream(dbPath);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }

                output.flush();
                output.close();
                input.close();
                Log.d("SQLiteHelper", "Database copied from assets.");
            } catch (IOException e) {
                Log.e("SQLiteHelper", "Failed to copy database: " + e.getMessage());
            }
        }
    }

    private String getDatabasePath() {
        return context.getApplicationInfo().dataDir + DATABASE_PATH_SUFFIX + DATABASE_NAME;
    }

    public SQLiteDatabase openDatabase() throws SQLException {
        if (database == null || !database.isOpen()) {
            database = SQLiteDatabase.openDatabase(getDatabasePath(), null, SQLiteDatabase.OPEN_READWRITE);
        }
        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Không cần tạo bảng nếu sử dụng DB từ assets
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Không nâng cấp
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = openDatabase();
        Cursor cursor = db.query("users",
                new String[]{"id"},
                "email=?",
                new String[]{email},
                null, null, null);
        boolean exists = cursor != null && cursor.moveToFirst();
        if (cursor != null) cursor.close();
        return exists;
    }

    public User authenticate(User user) {
        SQLiteDatabase db = openDatabase();
        Cursor cursor = db.query("users",
                new String[]{"id", "username", "email", "password"},
                "email=?",
                new String[]{user.email},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            User foundUser = new User(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            cursor.close();

            if (user.password.equalsIgnoreCase(foundUser.password)) {
                return foundUser;
            }
        }
        return null;
    }
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.userName);
        values.put(KEY_EMAIL, user.email);
        values.put(KEY_PASSWORD, user.password);
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

}
