package vn.tlu.edu.android.hotel;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
public class Signup extends AppCompatActivity{

    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPassword;

    Button buttonRegister;

    SQLiteHelper sqliteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sqliteHelper = new SQLiteHelper(this);
        initTextViewLogin();
        initViews();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String UserName = editTextUserName.getText().toString();
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();


                    if (!sqliteHelper.isEmailExists(Email)) {


                        sqliteHelper.addUser(new User(null, UserName, Email, Password));
                        Snackbar.make(buttonRegister, "User Created Successfully! Please  Login ", Snackbar.LENGTH_LONG).setDuration(4000).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                            }
                        }, Snackbar.LENGTH_LONG);

                        editTextEmail.setText("");
                        editTextPassword.setText("");
                        editTextUserName.setText("");
                    }else {

                        Snackbar.make(buttonRegister, "User Already Exists With Same Email ", Snackbar.LENGTH_LONG).show();
                    }


                }

            }
        });

    }

    private void initTextViewLogin() {
        TextView textViewLogin =  findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initViews() {
        editTextEmail =  findViewById(R.id.editTextEmail2);
        editTextPassword = findViewById(R.id.editTextPassword2);
        editTextUserName =  findViewById(R.id.editTextName2);
        buttonRegister =  findViewById(R.id.buttonSignup);

    }

    public boolean validate() {
        boolean valid = false;


        String UserName = editTextUserName.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        if (UserName.isEmpty()) {
            valid = false;
            editTextUserName.setError("Please enter valid username!");
        } else {
            if (UserName.length() > 3) {
                valid = true;
                editTextUserName.setError(null);
            } else {
                valid = false;
                editTextUserName.setError("Username is to short!");

            }
        }


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
            if (Password.length() > 7) {
                valid = true;
                editTextPassword.setError(null);
            } else {
                valid = false;
                editTextPassword.setError("Password is to short!");
            }
        }


        return valid;
    }

}