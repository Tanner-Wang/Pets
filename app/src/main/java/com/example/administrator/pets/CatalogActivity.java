package com.example.administrator.pets;

import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.pets.data.PetContract.PetEntry;

import static com.example.administrator.pets.R.id.fab;

public class CatalogActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<Cursor> {

    static ViewHolder holder;

    private static final int PET_LOADER = 0;

    PetCursorAdapter mCursorAdapter;

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

        holder.petListView = (ListView) findViewById(R.id.list_view_pet);
        View emptyView = findViewById(R.id.empty_view);
        holder.petListView.setEmptyView(emptyView);

        mCursorAdapter = new PetCursorAdapter(this,null);
        holder.petListView.setAdapter(mCursorAdapter);

        holder.petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(CatalogActivity.this,EditorActivity.class);
                //形成被点击的特定宠物的Content Uri并将id包含在其后面
                Uri currentPetUri = ContentUris.withAppendedId(PetEntry.CONTENT_URI,id);
                intent.setData(currentPetUri);

                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(PET_LOADER,null,this);
    }

    private void deleteAllPets() {
        int rowsDeleted = getContentResolver().delete(PetEntry.CONTENT_URI, null, null);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                PetEntry._ID,
                PetEntry.COLUMN_PET_NAME,
                PetEntry.COLUMN_BREED
        };

        return new CursorLoader(this,
                PetEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

    static class ViewHolder {
        FloatingActionButton fab;
        ListView petListView;
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
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deleteAllPets();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
