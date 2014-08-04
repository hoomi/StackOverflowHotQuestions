package ostovari.android.com.stackoverflowhotquestions;

import android.annotation.TargetApi;
import android.app.UiAutomation;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ostovari.android.com.stackoverflowhotquestions.activities.MyActivity;
import ostovari.android.com.stackoverflowhotquestions.fragments.QuestionsFragment;
import ostovari.android.com.stackoverflowhotquestions.utils.TestUtils;

/**
 * Created by hostova1 on 03/08/2014.
 */
public class QuestionFragmentTest extends ActivityInstrumentationTestCase2<MyActivity> {

    private QuestionsFragment questionsFragment;

    public QuestionFragmentTest() {
        super(MyActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        questionsFragment = (QuestionsFragment) getActivity().getSupportFragmentManager().findFragmentByTag("questionFragment");
        assertNotNull(questionsFragment);
    }

    public void testDownloadUI() {
        try {
            Method method = TestUtils.getMethod("downloadQuestions", QuestionsFragment.class);
            method.invoke(questionsFragment);
            assertEquals(View.GONE, questionsFragment.getListView().getVisibility());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            assertFalse("Could not find the method", true);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            assertFalse("Could not invoke the method", true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            assertFalse("Could not access the given method", true);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void testRotation() {
        try {
            UiAutomation uiAutomation = getInstrumentation().getUiAutomation();
            uiAutomation.setRotation(UiAutomation.ROTATION_FREEZE_0);
            uiAutomation.setRotation(UiAutomation.ROTATION_FREEZE_90);
            uiAutomation.setRotation(UiAutomation.ROTATION_FREEZE_180);
            uiAutomation.setRotation(UiAutomation.ROTATION_FREEZE_270);
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        questionsFragment = null;
    }
}
