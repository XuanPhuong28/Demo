package vn.tlu.edu.android.hotel;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class DisplayH extends AppCompatActivity{

    // private static final String IMAGE = "image";
    final String gp ="Greek Palace";
    final String  jk= "Hotel JK";
    final String  hi= "Holiday Inn";
    final String mp ="Mumta Palace";
    final String lh = "Hotel Luxury";
    Button b1,b2,b3,b4,b5;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Vieww) {
            startActivity(new Intent(DisplayH.this, DeleteOfList.class));
            return true;
        } else if (id == R.id.Refersh) {
            recreate();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        b1 = findViewById(R.id.buttonView1);
        b2 = findViewById(R.id.buttonView2);
        b3 = findViewById(R.id.buttonView3);
        b4 = findViewById(R.id.buttonView4);
        b5 = findViewById(R.id.buttonView5);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DisplayH.this,AddH.class);
                i.putExtra("GP",gp);
                startActivity(i);



                // Toast.makeText(Main3Activity.this, "1", Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DisplayH.this,AddH.class);

                i.putExtra("HJK",jk);
                startActivity(i);


                // Toast.makeText(Main3Activity.this, "2", Toast.LENGTH_SHORT).show();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DisplayH.this,AddH.class);
                i.putExtra("MP",mp);
                startActivity(i);



                //   Toast.makeText(Main3Activity.this, "3", Toast.LENGTH_SHORT).show();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DisplayH.this,AddH.class);
                i.putExtra("HI",hi);

                startActivity(i);

                //   Toast.makeText(Main3Activity.this, "4", Toast.LENGTH_SHORT).show();
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DisplayH.this,AddH.class);
                i.putExtra("HL",lh);
                startActivity(i);



                //Toast.makeText(Main3Activity.this, "5", Toast.LENGTH_SHORT).show();
            }
        });

    }

}

