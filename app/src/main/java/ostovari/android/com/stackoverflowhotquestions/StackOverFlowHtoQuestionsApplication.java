package ostovari.android.com.stackoverflowhotquestions;

import android.app.Application;

import ostovari.android.com.stackoverflowhotquestions.communications.RestClient;

/**
 * Created by hostova1 on 03/08/2014.
 */
public class StackOverFlowHtoQuestionsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RestClient.init(this);
    }
}
