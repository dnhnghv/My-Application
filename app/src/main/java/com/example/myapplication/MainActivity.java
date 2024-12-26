
package com.example.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.JavascriptInterface;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private MediaPlayer mediaPlayerMp3, mediaPlayerMp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo WebView
        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Cấu hình WebView
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/index.html");

        // Tạo bridge để giao tiếp với JavaScript
        webView.addJavascriptInterface(new WebAppInterface(), "Android");

        // Khởi tạo MediaPlayer
        mediaPlayerMp3 = MediaPlayer.create(this, R.raw.audio);
        mediaPlayerMp4 = MediaPlayer.create(this, R.raw.video);
    }

    public class WebAppInterface {
        @JavascriptInterface
        public void playMp3() {
            if (mediaPlayerMp3 != null) {
                mediaPlayerMp3.start();
            }
        }

        @JavascriptInterface
        public void playMp4() {
            if (mediaPlayerMp4 != null) {
                mediaPlayerMp4.start();
            }
        }

        @JavascriptInterface
        public void generateOtp(JavascriptCallback callback) {
            int otp = (int) (Math.random() * 900000) + 100000;
            callback.onCallback(String.valueOf(otp));
        }
    }

    public interface JavascriptCallback {
        void onCallback(String otp);
    }
}
