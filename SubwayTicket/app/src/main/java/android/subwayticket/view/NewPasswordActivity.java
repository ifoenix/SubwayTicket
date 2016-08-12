package android.subwayticket.view;

import android.os.Bundle;
import android.subwayticket.R;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Charles on 2016/7/28.
 */
public class NewPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_findnewpassword);
    }

    public void comfirmPassword(View view){

        Toast.makeText(NewPasswordActivity.this, "密码修改成功",Toast.LENGTH_SHORT).show();

    }
}
