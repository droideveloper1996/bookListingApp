package scorecard.project.com.booklistingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                drawPath();
            }
        }, 2000);

        TextView textView = (TextView) findViewById(R.id.skip);

        textView.setVisibility(View.GONE);
        ImageView imageView=(ImageView)findViewById(R.id.imageMain);
        imageView.animate().scaleXBy(.2f).scaleYBy(0.2f).setDuration(1000);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), searchActivity.class);
                startActivity(i);
            }
        });
    }
    public void drawPath()
    {

        TextView textView = (TextView) findViewById(R.id.skip);
        textView.setVisibility(View.VISIBLE);
        textView.animate().alpha(1.0f).setDuration(2000);
        Log.i("info","Delayed for 5 second");
        TextView apptitle=(TextView)findViewById(R.id.appTitle);
        apptitle.animate().alpha(1.0f).setDuration(1000);
    }

}



