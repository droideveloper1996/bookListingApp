package scorecard.project.com.booklistingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class searchActivity extends AppCompatActivity {

    EditText editText;

    public void searchBook(View view) {
        String search = editText.getText().toString();
        Log.i("searchTriggered", search);
        Intent i = new Intent(getApplicationContext(), homeActivity.class);
        i.putExtra("bookname", search);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText = (EditText) findViewById(R.id.bookSearchEditTextField);
    }
}
