package app.com.movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//////////////////////////////////////////// 제거 해야함 바로 MovieActivity에 가기 위해 이렇게 작성
        Intent intent = new Intent(
                getApplicationContext(),
                MovieActivity.class);
        startActivity(intent);
        ////////////////////////////////////////////
    }
}
