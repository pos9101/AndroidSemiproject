package app.com.movie;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvId;
    TextView tvEmail;
    TextView tvTel;
    String strId= "";
    String strName="";
    String strEmail="";
    String strTel="";
    String strPw="";
    RequestToServerDAO reqdao = new RequestToServerDAO();
    Handler mHandler = new Handler();
    SignVO vo= new SignVO();
    SignVO vo2= new SignVO();
    Button btnUpdate;
    Button btnDelete;
    Boolean result;
    boolean del_passCheck =false;
    boolean up_passCheck =false;

    ListView statusView;
    Map<String,String> tempMap = new HashMap<String, String>();
    List<Map<String,String>> mapList  = new ArrayList<Map<String, String>>();
    String[] mapKey = new String[]{"title","content"};
    int[] layoutId = new int[]{android.R.id.text1,android.R.id.text2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Log.i("StatusActivity","onCreate");
        tvName = (TextView)findViewById(R.id.tv_name);
        tvId = (TextView)findViewById(R.id.tv_id);
        tvEmail = (TextView)findViewById(R.id.tv_email);
        tvTel = (TextView)findViewById(R.id.tv_tel);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        statusView = (ListView)findViewById(R.id.lv_status);
        SharedPreferences sp2 = getSharedPreferences("session",MODE_PRIVATE);
        strId = sp2.getString("id","guest");
        vo.setId(strId);
        Log.i("StatusActivity","id>>"+strId);



        new Thread(){
            @Override
            public void run() {
                super.run();
                func_search();
            }
        }.start();//end Thread

         View.OnClickListener btn = new View.OnClickListener() {

             @Override
             public void onClick(View v) {

                switch(v.getId()){
                    case R.id.btnUpdate :
                        func_update();
                        break;

                    case R.id.btnDelete :
                       func_delete();
                        break;
                }//end switch
             }
         };//end OnClickListener
        btnUpdate.setOnClickListener(btn);
        btnDelete.setOnClickListener(btn);

    }//end onCreate

    private void func_search(){
        try {
            Log.i("StatusActivity","inThread..");
            vo2=reqdao.search(vo);
            strName=vo2.getName();
            strEmail=vo2.getEmail();
            strTel= vo2.getTel();
            Log.i("StatusActivity>>",strId+":"+strName+":"+strEmail+":"+strTel);
            tempMap.put("title","Name");
            tempMap.put("content",strName);
            mapList.add(tempMap);
            tempMap.put("title","ID");
            tempMap.put("content",strId);
            mapList.add(tempMap);
            tempMap.put("title","Email");
            tempMap.put("content",strEmail);
            mapList.add(tempMap);
            tempMap.put("title","Tel");
            tempMap.put("content",strTel);
            mapList.add(tempMap);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                statusView.setAdapter(new SimpleAdapter(
                        StatusActivity.this,
                        mapList,
                        android.R.layout.simple_list_item_2,
                        mapKey,
                        layoutId));
                tvName.setText(strName);
                tvId.setText(strId);
                tvEmail.setText(strEmail);
                tvTel.setText(strTel);
            }
        });//end Handler
    }//end func_swerch

    private void func_update(){
            Log.i("StatusActivity","func_update()..");
            LayoutInflater factory = LayoutInflater.from(this);

        final View textEntryView = factory.inflate(R.layout.alert_dialog_text_entry, null);
        final EditText password_edit = (EditText)textEntryView.findViewById(R.id.password_edit);

        new android.app.AlertDialog.Builder(StatusActivity.this)
                .setIconAttribute(android.R.attr.alertDialogIcon)
                .setTitle("Insert Password")
                .setView(textEntryView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        strPw = password_edit.getText().toString();
                        vo.setPw(strPw);
                        Log.i("Status>>>",password_edit.getText().toString());

                        new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                up_passCheck =reqdao.loginCheck(vo);


                                        if(up_passCheck==true){
                                            startActivity(new Intent(StatusActivity.this,UpdateActivity.class)
                                                    .putExtra("id",strId)
                                                    .putExtra("email",strEmail)
                                                    .putExtra("tel",strTel)
                                                    .putExtra("pw",strPw)
                                                    .setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
                                        }else{
                                            mHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                            Toast.makeText(StatusActivity.this.getApplicationContext(),
                                                    "wrong password",Toast.LENGTH_SHORT).show();
                                                }//end run()
                                            });//end Handler
                                        }


                            }//end rudn()
                        }.start();//end Thread
                    }
                })//end positive button
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        /* User clicked cancel so do some stuff */
                    }
                }).show();//end dialog
    }//end func_update

        public void func_delete(){
            Log.i("StatusActivity","func_delete()..");

            LayoutInflater factory = LayoutInflater.from(this);

            final View textEntryView = factory.inflate(R.layout.alert_dialog_text_entry, null);
            final EditText password_edit = (EditText)textEntryView.findViewById(R.id.password_edit);

                    new android.app.AlertDialog.Builder(StatusActivity.this)
                            .setIconAttribute(android.R.attr.alertDialogIcon)
                            .setTitle("Insert Password")
                            .setView(textEntryView)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    vo.setPw(password_edit.getText().toString());
                                    Log.i("Status>>>",password_edit.getText().toString());

                                    new Thread(){
                                        @Override
                                        public void run() {
                                            super.run();
                                             del_passCheck =reqdao.loginCheck(vo);
                                            mHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if(del_passCheck==true){
                                                        new Thread(){
                                                            @Override
                                                            public void run() {
                                                                super.run();
                                                                result=reqdao.delete(vo);
                                                            }
                                                        }.start();//end inner Thread
                                                        Toast.makeText(StatusActivity.this.getApplicationContext(),
                                                                "delete success",Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(StatusActivity.this,LoginActivity.class)
                                                                .setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
                                                    }else{
                                                        Toast.makeText(StatusActivity.this.getApplicationContext(),
                                                                "wrong password",Toast.LENGTH_SHORT).show();
                                                    }
                                                }//end run()
                                            });//end Handler

                                        }//end rudn()
                                    }.start();//end Thread
                                }
                            })//end positive button
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                        /* User clicked cancel so do some stuff */
                                }
                            }).show();//end dialog
        }//end func_delete

}//end class
