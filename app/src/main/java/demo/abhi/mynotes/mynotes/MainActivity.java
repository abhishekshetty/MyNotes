package demo.abhi.mynotes.mynotes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.software.shell.fab.ActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar mtoolbar;
    private DatabaseHelper mdbhelper;
    private RecyclerView mRecyclerView;
    private NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtoolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mtoolbar);
        ActionButton actionButton = (ActionButton) findViewById(R.id.action_button);
        actionButton.setImageResource(R.drawable.fab_plus_icon);
        actionButton.setButtonColor(getResources().getColor(R.color.accentColor));
        actionButton.setButtonColorPressed(getResources().getColor(R.color.accentColor900));

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "pressed", Toast.LENGTH_SHORT).show();
            }
        });
        mdbhelper = new DatabaseHelper(this);
        SQLiteDatabase msqldb = mdbhelper.getWritableDatabase();
        mRecyclerView = (RecyclerView) findViewById(R.id.notes_list);
        adapter = new NotesAdapter(this,getDbData(mdbhelper));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<NotesContent> getDbData(DatabaseHelper mdbhelper) {
        List <NotesContent> data = new ArrayList<>(); //check-here
        SQLiteDatabase msqldb = mdbhelper.getWritableDatabase();
        String[] columns = {DatabaseHelper.PRIMARY_KEY, DatabaseHelper.HEADER_COLL, DatabaseHelper.CONTENT_COLL};
        Cursor mCursor = msqldb.query(DatabaseHelper.TABLE_NAME,columns,null,null,null,null,null);
        while(mCursor.moveToNext()){
            NotesContent currentData = new NotesContent();
            currentData.Header = mCursor.getString(1);
            currentData.Content = mCursor.getString(2);
            data.add(currentData);
        }
        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
