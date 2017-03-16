package app.com.movie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText updateEmail;
    EditText updateTel;
    EditText updatePw;
    Button btnUpdate;
    SignVO vo= new SignVO();
    RequestToServerDAO reqdao = new RequestToServerDAO();
    String id="";
    String email="";
    String tel="";
    String pw="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        updateEmail = (EditText)findViewById(R.id.update_email);
        updateTel = (EditText)findViewById(R.id.update_tel);
        updatePw = (EditText)findViewById(R.id.update_pw);
        btnUpdate = (Button)findViewById(R.id.btn_upsubmit);

        SharedPreferences sp2 = getSharedPreferences("session",MODE_PRIVATE);
        Intent intent = getIntent();
        id=sp2.getString("id","guest");

        updateEmail.setText(intent.getStringExtra("email"));
        updateTel.setText(intent.getStringExtra("tel"));
        updatePw.setText(intent.getStringExtra("pw"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = updateEmail.getText().toString();
                tel= updateTel.getText().toString();
                pw= updatePw.getText().toString();

                if(email.equals("")|tel.equals("")|pw.equals("")){
                    Toast.makeText(UpdateActivity.this,"fill in the blank",Toast.LENGTH_SHORT).show();
                }else{
                    vo.setId(id);
                    vo.setEmail(email);
                    vo.setTel(tel);
                    vo.setPw(pw);

                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            reqdao.update(vo);
                        }
                    };
                    Toast.makeText(UpdateActivity.this,"update success",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateActivity.this,StatusActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));

                }//end else
            }
        });//end onclick


    }//end onCreate
}//end class
