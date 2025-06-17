package vn.tlu.edu.android.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    ImageView img;
    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonLogin;
    SQLiteHelper sqliteHelper;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqliteHelper = new SQLiteHelper(this);
        initCreateAccountTextView();
        initViews();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (validate()) {


                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();


                    User currentUser = sqliteHelper.authenticate(new User(null, null, Email, Password));


                    if (currentUser != null) {
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                        Intent i = new Intent(MainActivity.this, DisplayH.class);
                        startActivity(i);


                    } else {


                        Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                    }
                }
                editTextEmail.setText("");
                editTextPassword.setText("");
            }
        });


    }

    public void initCreateAccountTextView() {
        TextView textViewCreateAccount = findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>I don't have account yet. </font><font color='#0c0099'>create one</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent);
            }
        });
    }


    private void initViews() {
        img = findViewById(R.id.imageView);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

    }

    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }


    public boolean validate() {
        boolean valid = false;

        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            editTextEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            editTextEmail.setError(null);
        }

        if (Password.isEmpty()) {
            valid = false;
            editTextPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                editTextEmail.setError(null);
            } else {
                valid = false;
                editTextPassword.setError("Password is to short!");
            }
        }
        return valid;
    }
}
