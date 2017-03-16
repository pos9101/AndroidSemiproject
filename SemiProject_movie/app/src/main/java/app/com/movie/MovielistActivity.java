package app.com.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MovielistActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movielist);

        GridView gridview1 = (GridView)findViewById(R.id.moviegridview);
        gridview1.setAdapter(new TestAdapter(this));
    }
}
