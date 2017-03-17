package app.com.movie;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;
import java.util.Random;
import java.util.Timer;

public class SensorActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    Timer timer =new Timer();
    Random random = new Random();
    int rnd=0;
    ImageView imageView ;
    Handler handler = new Handler();
    Sensor sensor;
    SensorEvent event2;
    long lastTime =0;
    long currTime = 0;

    @Override
    protected void onStart() {
        super.onStart();
        final List<Sensor> sensorList = sensorManager.getSensorList(
                Sensor.TYPE_PROXIMITY);//end sensorList


        if (sensorList.size()>0){
            Log.i("SensorActivity","in if....");
            sensorManager.registerListener(
                    SensorActivity.this,sensorList.get(0),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }//end if
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        imageView= (ImageView)findViewById(R.id.whatmovie_img);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);



    }//end onCreate

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
        timer.cancel();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        event2=event;
        sensor = event.sensor;
        Log.i("SensorActivity","onSensorChange");
        Log.i("SensorActivity0000000",":  "+event.values[0]+"");
        Log.i("SensorActivity1111111",":  "+event.values[1]+"");
        Log.i("SensorActivity2222222",":  "+event.values[2]+"");


            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (event2.values[0]==0) {
                        switch (sensor.getType()) {
                            case Sensor.TYPE_PROXIMITY:
                                rnd = random.nextInt(3);
                                if (rnd == 0) {
                                    imageView.setImageResource(R.drawable.beautyandbeast);
                                    Log.i("SensorActivity", "kong");
                                } else if (rnd == 1) {
                                    imageView.setImageResource(R.drawable.kong);
                                    Log.i("SensorActivity", "heabing");
                                } else if (rnd == 2) {
                                    imageView.setImageResource(R.drawable.logan);
                                    Log.i("SensorActivity", "logan");
                                }
                                break;
                            default:
                                break;
                        }//end switch
                    }//end inner if
                }});//end handler
    }//end onSensorChange

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
