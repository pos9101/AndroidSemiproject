package app.com.movie;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class SignupActivity extends AppCompatActivity {
    EditText etId;
    EditText etPw;
    EditText etPw2;
    EditText etName;
    EditText etTel;
    EditText etEmail;
    RadioGroup rg;
    Button btnSubmit;
    Handler mHandler = new Handler();
    boolean result =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etId = (EditText)findViewById(R.id.et_id);
        etPw = (EditText)findViewById(R.id.et_pw);
        etPw2 = (EditText)findViewById(R.id.et_pw2);
        etName = (EditText)findViewById(R.id.et_name);
        etTel = (EditText)findViewById(R.id.et_tel);
        etEmail = (EditText)findViewById(R.id.et_email);
        btnSubmit = (Button)findViewById(R.id.btn_submit);

        Log.i("SignupActivity>>","onCreate");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("SignupActivity>>","onClick");
                final SignVO vo=new SignVO();
                vo.setId(etId.getText().toString());
                vo.setPw(etPw.getText().toString());
                vo.setName(etName.getText().toString());
                vo.setEmail(etEmail.getText().toString());
                vo.setTel(etTel.getText().toString());

                final RequestToServerDAO reqdao = new RequestToServerDAO();

                new Thread(){
                    public void run(){
                        Log.i("SignupActivity>>","run()");
                        result= reqdao.insert(vo);
                        Log.i("SignupActivity>>",result+"");
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(result==true){
                                    new AlertDialog.Builder(SignupActivity.this)
                                            .setIconAttribute(android.R.attr.alertDialogIcon)
                                            .setTitle("Success Sign up")
                                            .setMessage("thank you for joining us")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(SignupActivity.this.getApplicationContext()
                                                    ,LoginActivity.class);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            })
                                            .show();
                                }else{
                                    new AlertDialog.Builder(SignupActivity.this)
                                            .setIconAttribute(android.R.attr.alertDialogIcon)
                                            .setTitle("fail Sign up")
                                            .setMessage("failed")
                                            .setPositiveButton("OK", null)
                                            .show();
                                }
                            }
                        });

                    }
                }.start();



            }
        });//end onclick

    }//end oncreate

//    public void test(){
//        Log.i("test()","in..");
//
////        mHandler.post(new Runnable() {
////            @Override
////            public void run() {
////                Log.i("test()","mHanlder.run()....");
////
////                Log.i("test()","getText");
////            }
////        });
//    }

}//end class
