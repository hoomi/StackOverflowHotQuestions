package ostovari.android.com.stackoverflowhotquestions.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

import ostovari.android.com.stackoverflowhotquestions.R;
import ostovari.android.com.stackoverflowhotquestions.communications.RestClient;
import ostovari.android.com.stackoverflowhotquestions.fragments.QuestionsFragment;


public class MyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new QuestionsFragment(),"questionFragment")
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RestClient.getInstance().close();
    }
}
