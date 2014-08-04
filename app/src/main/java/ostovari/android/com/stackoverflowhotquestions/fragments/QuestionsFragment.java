package ostovari.android.com.stackoverflowhotquestions.fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ostovari.android.com.stackoverflowhotquestions.R;
import ostovari.android.com.stackoverflowhotquestions.communications.RestClient;
import ostovari.android.com.stackoverflowhotquestions.model.Question;
import ostovari.android.com.stackoverflowhotquestions.model.Questions;

/**
 * Created by hostova1 on 03/08/2014.
 */
public class QuestionsFragment extends ListFragment {

    private AsyncTask<Void, Void, Questions> questionTask;
    private QuestionAdapter questionAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setEmptyText(getString(R.string.no_question_found));
        downloadQuestions();
        getView().setBackgroundColor(getResources().getColor(android.R.color.black));
    }


    private void downloadQuestions() {
        if (questionTask == null) {
            questionTask = new AsyncTask<Void, Void, Questions>() {
                @Override
                protected void onPreExecute() {
                    setListShown(false);
                    super.onPreExecute();
                }

                @Override
                protected Questions doInBackground(Void... params) {
                    Questions questions = null;
                    try {
                        questions = RestClient.getInstance().getTheListOfQuestions();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                    return questions;
                }

                @Override
                protected void onPostExecute(Questions result) {
                    if (questionAdapter == null) {
                        questionAdapter = new QuestionAdapter(getActivity(), R.layout.question_row);
                        setListAdapter(questionAdapter);
                    }
                    if (result != null) {
                        questionAdapter.setQuestions(result);
                    }
                    setListShown(true);
                    questionTask = null;

                }
            };
            questionTask.execute();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.my, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_refresh:
                downloadQuestions();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private final class QuestionAdapter extends ArrayAdapter<Question> {
        private List<Question> questionList;

        public QuestionAdapter(Context context, int resource) {
            super(context, resource);
        }

        public void setQuestions(Questions questions) {
            questionList = questions.items;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return questionList == null ? 0 : questionList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            Question question = questionList.get(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.question_row, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.titleTextView.setText(question.title);
            return convertView;
        }


        final class ViewHolder {
            ViewHolder(View v) {
                this.titleTextView = (TextView) v.findViewById(R.id.question_title_TextView);
            }

            TextView titleTextView;
        }
    }
}
