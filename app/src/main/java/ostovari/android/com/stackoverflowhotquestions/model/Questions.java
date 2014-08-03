package ostovari.android.com.stackoverflowhotquestions.model;

import java.util.List;

/**
 * Created by hostova1 on 03/08/2014.
 */

/*
"items"=[],
 "has_more": true,
  "quota_max": 10000,
  "quota_remaining": 9996
 */
public class Questions {
    public List<Question> items;
    public boolean has_more = false;
    public int quota_max;
    public int quota_remaining;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Questions questions = (Questions) o;

        if (has_more != questions.has_more) return false;
        if (quota_max != questions.quota_max) return false;
        if (quota_remaining != questions.quota_remaining) return false;
        if (items != null ? !items.equals(questions.items) : questions.items != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = items != null ? items.hashCode() : 0;
        result = 31 * result + (has_more ? 1 : 0);
        result = 31 * result + quota_max;
        result = 31 * result + quota_remaining;
        return result;
    }
}
