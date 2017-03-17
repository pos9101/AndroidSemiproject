package app.com.movie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MovieTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_movie_time);

        WebView webview = new WebView(this);

        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                MovieTimeActivity.this.setProgress(progress * 1000);
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(MovieTimeActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return super.shouldOverrideKeyEvent(view, event);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

        });

        Intent intent = getIntent();
        int pageNum = intent.getIntExtra("pageNum",1);

        Log.i("MovieTimeActivity","pageNum>>>>"+pageNum);

        SharedPreferences sp2 = getSharedPreferences("session",MODE_PRIVATE);
        String strId = sp2.getString("id","guest");
        Log.i("MovieTimeActivity",strId);

        if (pageNum==1) {
            webview.loadUrl("http://192.168.0.161:8090/Semiproject_movie/beautyandbeast.do?id="+strId);
        }else if (pageNum==2) {
            webview.loadUrl("http://192.168.0.161:8090/Semiproject_movie/kong.do?id="+strId);
        }else if (pageNum==3) {
            webview.loadUrl("http://192.168.0.161:8090/Semiproject_movie/logan.do?id="+strId);
        }

            getWindow().requestFeature(Window.FEATURE_PROGRESS);

        webview.getSettings().setJavaScriptEnabled(true);
        setContentView(webview);



    }
}
