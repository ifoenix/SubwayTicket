package android.subwayticket.view;

import android.content.Intent;
import android.os.Bundle;
import android.subwayticket.R;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FindPasswordActivity extends AppCompatActivity {

//    Button submitButton;
//    PasswordEditText pwText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_forgetoldpassword);

//        submitButton =  (Button)findViewById(R.id.submit_button);
//        pwText = (PasswordEditText)findViewById(R.id.input_password);
    }

    public void submit(View view){

        Intent intent = new Intent(FindPasswordActivity.this,NewPasswordActivity.class);

        startActivity(intent);

    }
}
