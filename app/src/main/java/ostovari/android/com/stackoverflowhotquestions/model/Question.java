package ostovari.android.com.stackoverflowhotquestions.model;

import java.util.Arrays;

/**
 * Created by hostova1 on 03/08/2014.
 */

/*
{
      "tags": [
        "php",
        "wordpress-plugin"
      ],
      "owner": {
        "reputation": 6,
        "user_id": 1789839,
        "user_type": "registered",
        "profile_image": "https://www.gravatar.com/avatar/9ca0ffbf7268386e4321490e38dc05c3?s=128&d=identicon&r=PG",
        "display_name": "Sourabh Jain",
        "link": "http://stackoverflow.com/users/1789839/sourabh-jain"
      },
      "is_answered": false,
      "view_count": 1390,
      "answer_count": 2,
      "score": 1,
      "last_activity_date": 1407060923,
      "creation_date": 1351720418,
      "question_id": 13167992,
      "link": "http://stackoverflow.com/questions/13167992/convert-doc-file-into-docx-with-php",
      "title": "Convert Doc File into Docx with php?"
    }
 */
public class Question {
    public String[] tags;
    public Owner owner;
    public boolean is_answered = false;
    public int view_count;
    public int score;
    public int answer_count;
    public long accepted_answer_id;
    public long last_activity_date;
    public long creation_date;
    public long last_edit_date;
    public long question_id;
    public String link;
    public String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (answer_count != question.answer_count) return false;
        if (creation_date != question.creation_date) return false;
        if (is_answered != question.is_answered) return false;
        if (last_activity_date != question.last_activity_date) return false;
        if (question_id != question.question_id) return false;
        if (score != question.score) return false;
        if (view_count != question.view_count) return false;
        if (link != null ? !link.equals(question.link) : question.link != null) return false;
        if (owner != null ? !owner.equals(question.owner) : question.owner != null) return false;
        if (title != null ? !title.equals(question.title) : question.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tags != null ? Arrays.hashCode(tags) : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (is_answered ? 1 : 0);
        result = 31 * result + view_count;
        result = 31 * result + score;
        result = 31 * result + answer_count;
        result = 31 * result + (int) (last_activity_date ^ (last_activity_date >>> 32));
        result = 31 * result + (int) (creation_date ^ (creation_date >>> 32));
        result = 31 * result + (int) (question_id ^ (question_id >>> 32));
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
