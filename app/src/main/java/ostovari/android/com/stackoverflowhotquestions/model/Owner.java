package ostovari.android.com.stackoverflowhotquestions.model;

/**
 * Created by hostova1 on 03/08/2014.
 */

/**
 * "owner": {
 * "reputation": 6,
 * "user_id": 1789839,
 * "user_type": "registered",
 * "profile_image": "https://www.gravatar.com/avatar/9ca0ffbf7268386e4321490e38dc05c3?s=128&d=identicon&r=PG",
 * "display_name": "Sourabh Jain",
 * "link": "http://stackoverflow.com/users/1789839/sourabh-jain"
 * }
 */
public class Owner {
    public int reputation;
    public int accept_rate;
    public long user_id;
    public String user_type;
    public String profile_image;
    public String display_name;
    public String link;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Owner owner = (Owner) o;

        if (reputation != owner.reputation) return false;
        if (accept_rate != owner.accept_rate) return false;
        if (user_id != owner.user_id) return false;
        if (display_name != null ? !display_name.equals(owner.display_name) : owner.display_name != null)
            return false;
        if (link != null ? !link.equals(owner.link) : owner.link != null) return false;
        if (profile_image != null ? !profile_image.equals(owner.profile_image) : owner.profile_image != null)
            return false;
        if (user_type != null ? !user_type.equals(owner.user_type) : owner.user_type != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reputation;
        result = 31 * result + (int) (user_id ^ (user_id >>> 32));
        result = 31 * result + (user_type != null ? user_type.hashCode() : 0);
        result = 31 * result + (profile_image != null ? profile_image.hashCode() : 0);
        result = 31 * result + (display_name != null ? display_name.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }
}
