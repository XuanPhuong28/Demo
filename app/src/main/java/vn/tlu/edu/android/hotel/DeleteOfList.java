package vn.tlu.edu.android.hotel;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DeleteOfList extends AppCompatActivity{
    ListView l;
    Button del;
    EditText e;
    String a;
    long b;
    int counter = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.m,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Re1) {
            recreate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        l = findViewById(R.id.lis);
        e = findViewById(R.id.input);
        del = findViewById(R.id.del);

        final Database obj_Database = new Database(getApplicationContext());
        try {
            ArrayList<String> arrayList = obj_Database.viewBooking();

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DeleteOfList.this,
                    android.R.layout.simple_list_item_1,arrayList);
            l.setAdapter(arrayAdapter);
            l.setSelector(R.color.colorAccent);
            l.deferNotifyDataSetChanged();
        }
        catch (Exception e)
        {

        }
        del.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                a = e.getText().toString();

                if(a.isEmpty())
                {
                    e.setError("Field Missing");
                }
                else {
                    b = Long.parseLong(a);
                    final AlertDialog.Builder builder = new AlertDialog.Builder(DeleteOfList.this);
                    builder.setTitle("DELETE BOOKING!").setCancelable(false).setMessage("Do You Want To Delete Your Booking").
                            setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    obj_Database.delete(b);
                                    Snackbar.make(del,"Booking Deleted Sucessfully...Click On Refresh ",Snackbar.ANIMATION_MODE_SLIDE).setDuration(8000).show();

                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                    e.setText("");


                }

            }
        });



    }
}

