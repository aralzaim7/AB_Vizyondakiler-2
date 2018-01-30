package com.example.mehseti.ab_vizyondakiler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    TextView login;
    ArrayList<User> users;
    Intent reservation;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        users = createDummyUserData();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!TextUtils.isEmpty(email.getText().toString()) || !TextUtils.isEmpty(password.getText().toString()))
                {
                    for(int i=0;i<users.size();i++)
                    {

                        if((users.get(i).email.equals(email.getText().toString().trim())) && (users.get(i).password.equals(password.getText().toString().trim())))
                        {
                           reservation = new Intent(LoginActivity.this,Reservation.class);
                            Bundle data = new Bundle();
                            data.putSerializable("user_info",  users.get(i));
                            reservation.putExtras(data);
                            startActivity(reservation);

                        }
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Lütfen alanları boş bırakmayınız",Toast.LENGTH_LONG).show();
                }
            }
        });




    }

    public ArrayList<User> createDummyUserData() {
        ArrayList<User> users = new ArrayList<>();

        users.add(0, new User(1, "nevalgoksel@gmail.com", "parola1",  false));
        users.add(1, new User(2, "mirzayevanatavan@gmail.com", "parola2",  false));

        return  users;
    }

}
