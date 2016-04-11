package com.example.mikae.rockyreader2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @author      mikael bergström
 * @version     0.4
 * @since       2015-11-03
 */
public class MainActivity extends AppCompatActivity {
    private static WebView webView;
    private String date;
    private String rockyURL;
    private DateFormat df = new SimpleDateFormat("yyyyMMdd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkDate();
        updateTextView();
        createURL(date);
        System.out.println(rockyURL);
        createWebView();
        webView.loadUrl(rockyURL);

    }


    public void handleClick(View view) throws Exception{
        switch (view.getId())   {
            case R.id.bNext:
                change(date, +1);
                changeRocky();
                break;

            case R.id.bPrev:
                change(date, -1);
                changeRocky();
                break;
        }
    }

    /*
    check the current date
     */
    public void checkDate(){
        Date today = new Date();
        date = df.format(today);
        System.out.println("i checkdate" + date);

    }
    /*
    Increase or decrease the date
     */
    private void change(String date, int days) throws Exception {
        Date d = df.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, days);
        d = c.getTime();
        date = df.format(d);
        this.date = date;
        //return df.format(d);
    }
    /*
    Creates the first URL when starting the app. Should make it operational when flipping
    pictures (todaysRocky)
    */
    public void createURL(String date) {
        rockyURL = "http://dn.se/PageFiles/73862/" + date + ".gif";
        System.out.println("i create URL" + rockyURL);

    }
    /*
    Uppdaterar den lilla visaren för att se vilket datum man kollar på.
    */
    public void updateTextView(){
        TextView dateTextView = (TextView) findViewById(R.id.dateTextView);
        dateTextView.setText(date);
    }

    public void createWebView(){
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

    }
    /*
    Gör allt för att uppdatera webviewen när datumet ändrats.
     */
    public void changeRocky(){
        updateTextView();
        createURL(date);
        webView.loadUrl(rockyURL);
    }
}
