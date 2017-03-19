package com.example.administrator.pets;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.pets.data.PetDbHelper;

import static com.example.administrator.pets.R.id.fab;
import static com.example.administrator.pets.data.PetContract.PetEntry.*;

public class CatalogActivity extends AppCompatActivity {

    static ViewHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        holder = new ViewHolder();


        // Setup FAB to open EditorActivity
        holder.fab = (FloatingActionButton) findViewById(fab);
        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        displayDatabaseInfo();

    }

    static class ViewHolder {
        FloatingActionButton fab;
        TextView displayView;
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        PetDbHelper mDbHelper = new PetDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        String[] projection = {
                _ID,
                COLUMN_PET_NAME,
                COLUMN_BREED,
                COLUMN_GENDER,
                COLUMN_WEIGHT
        };
        //参数selection指定了我们感兴趣的列
        String selection = COLUMN_GENDER + "=?";
        //参数selectionArgs指定了我们感兴趣的行
        String[] selectionArgs = {"1"};
        //.query()方法返回的是一个cursor对象，这是一个包含数据库中的多行内容的对象
        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        holder.displayView = (TextView) findViewById(R.id.text_view_pet);
        try {

            holder.displayView.setText("There are " + cursor.getCount() + " pet(s)\n");
            holder.displayView.append(
                    _ID + "-" +
                            COLUMN_PET_NAME + "-" +
                            COLUMN_BREED + "-" +
                            COLUMN_GENDER + "-" +
                            COLUMN_WEIGHT + "\n\n");

            int IdColumnIndex = cursor.getColumnIndex(_ID);
            int NameColumnIndex = cursor.getColumnIndex(COLUMN_PET_NAME);
            int BreedColumnIndex = cursor.getColumnIndex(COLUMN_BREED);
            int GenderColumnIndex = cursor.getColumnIndex(COLUMN_GENDER);
            int WeightColumnIndex = cursor.getColumnIndex(COLUMN_WEIGHT);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(IdColumnIndex);
                String name = cursor.getString(NameColumnIndex);
                String breed = cursor.getString(BreedColumnIndex);
                int mGender = cursor.getInt(GenderColumnIndex);
                String gender;
                if (mGender == 0) {
                    gender = "Unknown";
                } else if (mGender == 1) {
                    gender = "Male";
                } else {
                    gender = "Female";
                }
                int weight = cursor.getInt(WeightColumnIndex);

                holder.displayView.append(id + "-" + name + "-" + breed + "-" + gender + "-" + weight + "\n");
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    //重写onStart方法，从EditorActivity返回,CatalogActivity重启时宠物列表将刷新并包含新宠物列表的信息
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
