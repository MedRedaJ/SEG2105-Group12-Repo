package com.example.lab3databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "productname";
    private static final String COLUMN_PRODUCT_PRICE = "price";
    private static final String DATABASE_NAME = "productDB.db";
    private static final int DATABASE_VERSION = 1;

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_cmd = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_PRODUCT_NAME + " TEXT, " +
                COLUMN_PRODUCT_PRICE + " DOUBLE " + ")";
        db.execSQL(create_table_cmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getProductName());
        values.put(COLUMN_PRODUCT_PRICE, product.getProductPrice());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor findProduct(String name, double price) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> selectionArgs = new ArrayList<>();
        StringBuilder selection = new StringBuilder();

        // Build the "WHERE" clause dynamically
        if (name != null && !name.isEmpty()) {
            selection.append(COLUMN_PRODUCT_NAME + " LIKE ?");
            selectionArgs.add(name + "%"); // Use '%' for wildcard prefix search
        }

        if (price >= 0) {
            if (selection.length() > 0) {
                selection.append(" AND ");
            }
            selection.append(COLUMN_PRODUCT_PRICE + " = ?");
            selectionArgs.add(String.valueOf(price));
        }

        String whereClause = selection.length() > 0 ? selection.toString() : null;
        String[] finalArgs = selectionArgs.toArray(new String[0]);

        // Perform the query
        return db.rawQuery("SELECT * FROM " + TABLE_NAME +
                        (whereClause != null ? " WHERE " + whereClause : ""),
                finalArgs);
    }
    public boolean deleteProduct(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;

        // Use a case-insensitive comparison for the deletion
        // The delete() method returns the number of rows affected.
        int rowsDeleted = db.delete(TABLE_NAME, COLUMN_PRODUCT_NAME + " = ? COLLATE NOCASE", new String[]{name});

        if (rowsDeleted > 0) {
            result = true;
        }

        db.close();
        return result;
    }
    public String getColumnProductName() {
        return COLUMN_PRODUCT_NAME;
    }

    public String getColumnProductPrice() {
        return COLUMN_PRODUCT_PRICE;
    }
}
