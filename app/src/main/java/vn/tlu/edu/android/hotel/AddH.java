package vn.tlu.edu.android.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
public class AddH extends AppCompatActivity{

    String Greek,HJK,M,HIN,HLU,g,jk,m,in,l;
    EditText e1,e2,ed1,ed2;
    TextView t,at;
    ImageView image;
    DatePickerDialog d1,d2;
    Button btn;
    String CheckIn,CheckOut;
    long id,value,amount,days;
    int counter;

    String HOTEL;


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
            Intent intent = new Intent(AddH.this, DeleteOfList.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Refersh) {
            recreate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        t = findViewById(R.id.tex);
        image = findViewById(R.id.img);
        e1 = findViewById(R.id.Ename);
        e2 = findViewById(R.id.Enum);
        ed1 = findViewById(R.id.inDate);
        ed2 = findViewById(R.id.outDate);
        //at = findViewById(R.id.AM);
        btn = findViewById(R.id.confirm);
        final Database obj_database = new Database(getApplicationContext());
        //  at.setText(""+amount);
        Bundle  i = getIntent().getExtras() ;
        Greek = i.getString("GP");
        HJK = i.getString("JK");
        M = i.getString("MP");
        HIN = i.getString("HI");
        HLU = i.getString("HL");
        // Toast.makeText(this, ""+Greek, Toast.LENGTH_SHORT).show();

        g = "Greek Palace";
        jk = "Hotel JK";
        m = "Mumta Palace";
        in = "Holiday Inn";
        l = "Hotel Luxury";

        if(g.equals(Greek))
        {
            t.setText("Welcome To Greek Palace");
            image.setImageResource(R.drawable.h1);
            counter=1;
        }

        else if(m.equals(M))
        {
            t.setText("Welcom To Mumta Palace ");
            image.setImageResource(R.drawable.h2);
            counter=3;
        }
        else if(in.equals(HIN))
        {
            t.setText("Welcom To Holiday Inn");
            image.setImageResource(R.drawable.h3);
            counter=4;
        }
        else if(l.equals(HLU))
        {
            t.setText("Welcom To Hotel Luxury");
            image.setImageResource(R.drawable.h4);
            counter =5;
        }
        //if(jk.equals(HJK))
        else
        {
            t.setText("Welcome To Hotel JK");
            image.setImageResource(R.drawable.hotel);
            counter=2;
        }
        // Snackbar.make(btn, "Counter = "+counter, Snackbar.LENGTH_LONG).show();


        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                final int presentDay=calendar.get(Calendar.DAY_OF_MONTH);
                final int presentMonth=calendar.get(Calendar.MONTH);
                final int presentYear=calendar.get(Calendar.YEAR);
                d1=new DatePickerDialog(AddH.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if(year>=presentYear) {
                            if(month>=presentMonth) {
                                if(month==presentMonth) {
                                    if(dayOfMonth>=presentDay) {
                                        month = month + 1;
                                        ed1.setText(dayOfMonth + "-" + month + "-" + year);
                                        CheckIn = dayOfMonth + "-" + month + "-" + year;
                                        ed1.setError(null);
                                    }
                                    else{
                                        ed1.setError("You have entered the wrong date the date is before the present date");
                                        ed1.setText("");
                                    }

                                }
                                else
                                {
                                    month = month + 1;
                                    ed1.setText(dayOfMonth + "-" + month + "-" + year);
                                    CheckIn = dayOfMonth + "-" + month + "-" + year;
                                    ed1.setError(null);
                                }
                            }
                            else
                            {
                                ed1.setError("You have entered the wrong date the date is before the present date");
                                ed1.setText("");
                            }
                        }
                        else
                        {
                            ed1.setError("You have entered the wrong date the date is before the present date");
                            ed1.setText("");
                        }
                    }

                },presentYear,presentMonth,presentDay);
                d1.show();

            }
        });

        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                final int presentDay=calendar.get(Calendar.DAY_OF_MONTH);
                final int presentMonth=calendar.get(Calendar.MONTH);
                final  int presentYear=calendar.get(Calendar.YEAR);
                d2=new DatePickerDialog(AddH.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //  Toast.makeText(Main4Activity.this, ""+CheckIn, Toast.LENGTH_SHORT).show();

                        if(year>=presentYear) {
                            if(month>=presentMonth) {
                                if(month==presentMonth) {
                                    if(dayOfMonth>=presentDay) {
                                        month = month + 1;
                                        ed2.setText(dayOfMonth + "-" + month + "-" + year);
                                        CheckOut = dayOfMonth + "-" + month + "-" + year;
                                        ed2.setError(null);
                                    }
                                    else{
                                        ed2.setError("The date is before the present date");
                                        ed2.setText("");
                                    }
                                }
                                else
                                {
                                    month = month + 1;
                                    ed2.setText(dayOfMonth + "-" + month + "-" + year);
                                    CheckOut = dayOfMonth + "-" + month + "-" + year;
                                    ed2.setError(null);
                                }
                            }
                            else
                            {
                                ed2.setError("The date is before the present date");
                            }
                        }
                        else
                        {
                            ed2.setError("The date is before the present date");
                            ed2.setText("");
                        }


                    }

                },presentYear,presentMonth,presentDay);
                d2.show();
            }

        });

        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {



                try {

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

                    Date TODATE1 = simpleDateFormat.parse(CheckIn);
                    Date FROMDATE1 = simpleDateFormat.parse(CheckOut);
                    if (TODATE1.compareTo(FROMDATE1) <= 0) {
                        value = 1;
                        //Toast.makeText(Main4Activity.this, "if: " + value, Toast.LENGTH_SHORT).show();

                        long min = 1000000;
                        long max = 9999999;
                        id = (long) (Math.random()*(max-min+1)+min);

                        if(counter ==1 )
                        {
                            HOTEL = "Greek Palace";
                            amount = 2500;

                        }
                        else if(counter==2)
                        {
                            HOTEL = "Hotel JK";
                            amount = 3500;
                        }
                        else if(counter==3)
                        {
                            HOTEL = "Mumta Palace";
                            amount = 4000;
                        }
                        else if(counter==4)
                        {
                            HOTEL = "Holiday Inn";
                            amount = 4200;
                        }
                        else if(counter==5)
                        {
                            HOTEL = "Hotel Luxury";
                            amount = 6000;
                        }
                        else
                        {
                            HOTEL = "Hotel AID";
                        }
                        long difference = Math.abs(TODATE1.getTime() - FROMDATE1.getTime());
                        long val =  (difference / (24 * 60 * 60 * 1000));
                        if(val==0)
                        {
                            amount= val+amount;
                            days = val+days;
                        }
                        else
                        {
                            amount = val*amount;
                            days = val;
                        }

                        //  Snackbar.make(btn, "Id = "+id+"\nHotel"+HOTEL, Snackbar.LENGTH_LONG).show();
                        String n1 = e1.getText().toString();
                        long p1= Long.parseLong(e2.getText().toString());
                        obj_database.add(id,HOTEL,n1,p1,CheckIn,CheckOut,amount,days);
                        //Snackbar.make(btn,"Booking Confirmed Sucessfully.",Snackbar.ANIMATION_MODE_SLIDE).setDuration(8000).show();
                        Toast.makeText(AddH.this, "Booking Confirmed Sucessfully.", Toast.LENGTH_SHORT).show();

                        Intent next = new Intent(AddH.this,View.class);
                        next.putExtra("I",id);
                        next.putExtra("HN",HOTEL);
                        next.putExtra("N",n1);
                        next.putExtra("PN",p1);
                        next.putExtra("CI",CheckIn);
                        next.putExtra("CO",CheckOut);
                        next.putExtra("A",amount);
                        next.putExtra("D",days);
                        startActivity(next);
                        e1.setText("");
                        e2.setText("");
                        ed1.setText("");
                        ed2.setText("");

                    } else {

                        long difference = Math.abs(TODATE1.getTime() - FROMDATE1.getTime());
                        value = difference / (24 * 60 * 60 * 1000);
                        //Toast.makeText(Main4Activity.this, "" + value, Toast.LENGTH_SHORT).show();
                        Snackbar.make(btn, "CheckOut Date can't be less than CheckIn...", Snackbar.LENGTH_LONG).show();


                    }

                } catch (Exception e) {

                }
            }
        });

    }

    void dbms()
    {

    }
}

