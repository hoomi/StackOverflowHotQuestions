package ostovari.android.com.stackoverflowhotquestions;

import android.test.InstrumentationTestCase;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import ostovari.android.com.stackoverflowhotquestions.communications.RestClient;
import ostovari.android.com.stackoverflowhotquestions.model.Owner;
import ostovari.android.com.stackoverflowhotquestions.model.Question;
import ostovari.android.com.stackoverflowhotquestions.model.Questions;
import ostovari.android.com.stackoverflowhotquestions.utils.TestUtils;

/**
 * Created by hostova1 on 03/08/2014.
 */
public class RestClientTest extends InstrumentationTestCase {
    final static String testJson = "{\n" +
            "    \"items\": [\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"clojure\",\n" +
            "                \"clojurescript\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 11039,\n" +
            "                \"user_id\": 190822,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 89,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/67ec98818d7fb6395d9a327081cd5472?s=128&d=identicon&r=PG\",\n" +
            "                \"display_name\": \"Zubair\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/190822/zubair\"\n" +
            "            },\n" +
            "            \"is_answered\": true,\n" +
            "            \"view_count\": 604,\n" +
            "            \"accepted_answer_id\": 20443693,\n" +
            "            \"answer_count\": 2,\n" +
            "            \"score\": 7,\n" +
            "            \"last_activity_date\": 1407070269,\n" +
            "            \"creation_date\": 1386432976,\n" +
            "            \"question_id\": 20443545,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/20443545/private-def-in-clojure-clojurescript\",\n" +
            "            \"title\": \"Private def in clojure/clojurescript\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"javascript\",\n" +
            "                \"css-selectors\",\n" +
            "                \"casperjs\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 612,\n" +
            "                \"user_id\": 1555863,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 62,\n" +
            "                \"profile_image\": \"http://i.stack.imgur.com/9VOcX.png?s=128&g=1\",\n" +
            "                \"display_name\": \"user1555863\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/1555863/user1555863\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 8,\n" +
            "            \"answer_count\": 1,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070251,\n" +
            "            \"creation_date\": 1407055785,\n" +
            "            \"question_id\": 25103063,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25103063/capsperjs-click-on-all-links-matching-a-selector\",\n" +
            "            \"title\": \"CapsperJS Click on all links matching a selector\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"android\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 3,\n" +
            "                \"user_id\": 3649152,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 20,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/91cf6140b06d84f62a0f84392a047587?s=128&d=identicon&r=PG&f=1\",\n" +
            "                \"display_name\": \"user3649152\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3649152/user3649152\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 2,\n" +
            "            \"answer_count\": 0,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070250,\n" +
            "            \"creation_date\": 1407070250,\n" +
            "            \"question_id\": 25104816,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104816/null-string-weird-situation\",\n" +
            "            \"title\": \"Null string ...weird situation\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"android-fragments\",\n" +
            "                \"flow\",\n" +
            "                \"master\",\n" +
            "                \"detail\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 1,\n" +
            "                \"user_id\": 3050148,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"profile_image\": \"http://graph.facebook.com/580019477/picture?type=large\",\n" +
            "                \"display_name\": \"user3050148\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3050148/user3050148\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 1,\n" +
            "            \"answer_count\": 0,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070250,\n" +
            "            \"creation_date\": 1407070250,\n" +
            "            \"question_id\": 25104815,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104815/android-master-detail-flow-project-how-to-inflate-a-new-fragment-to-replace-th\",\n" +
            "            \"title\": \"Android Master Detail Flow project - how to inflate a new fragment to replace the Detail Fragment on button click\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"visual-studio-2010\",\n" +
            "                \"visual-studio-2012\",\n" +
            "                \"dll\",\n" +
            "                \"64bit\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 1,\n" +
            "                \"user_id\": 3903847,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/35732b3ba196d29de51e48990df1673c?s=128&d=identicon&r=PG&f=1\",\n" +
            "                \"display_name\": \"yosriz\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3903847/yosriz\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 2,\n" +
            "            \"answer_count\": 0,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070249,\n" +
            "            \"creation_date\": 1407070249,\n" +
            "            \"question_id\": 25104814,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104814/visual-studio-cannot-add-to-toolbox-anycpu-dll-with-reference-to-32bit-dll\",\n" +
            "            \"title\": \"Visual Studio - cannot add to toolbox AnyCPU DLL with reference to 32bit dll\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"haskell\",\n" +
            "                \"equality\",\n" +
            "                \"algebraic-data-types\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 29131,\n" +
            "                \"user_id\": 342235,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 83,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/88e5a9eb011a73ab970ddc8cfb05ff85?s=128&d=identicon&r=PG\",\n" +
            "                \"display_name\": \"Freewind\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/342235/freewind\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 8,\n" +
            "            \"answer_count\": 1,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070245,\n" +
            "            \"creation_date\": 1407069454,\n" +
            "            \"question_id\": 25104709,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104709/why-in-algebraic-data-types-if-i-can-define-a-special-from-and-to-function\",\n" +
            "            \"title\": \"Why in algebraic data types, if I can define a special `from` and `to` function for two types, the two types can be considered equality?\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"reflection\",\n" +
            "                \"go\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 2653,\n" +
            "                \"user_id\": 1246987,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 88,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/63cf77a600870b0160b683f024618abd?s=128&d=identicon&r=PG&f=1\",\n" +
            "                \"display_name\": \"bodokaiser\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/1246987/bodokaiser\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 24,\n" +
            "            \"answer_count\": 2,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070238,\n" +
            "            \"creation_date\": 1407053363,\n" +
            "            \"last_edit_date\": 1407055710,\n" +
            "            \"question_id\": 25102827,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25102827/struct-value-of-pointer-array-and-slice\",\n" +
            "            \"title\": \"Struct value of pointer, array and slice\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"python\",\n" +
            "                \"linux\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 1,\n" +
            "                \"user_id\": 3530862,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/06e8f10337eee94d1a0a9185a10b77bb?s=128&d=identicon&r=PG&f=1\",\n" +
            "                \"display_name\": \"user3530862\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3530862/user3530862\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 14,\n" +
            "            \"answer_count\": 0,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070234,\n" +
            "            \"creation_date\": 1407068723,\n" +
            "            \"last_edit_date\": 1407070234,\n" +
            "            \"question_id\": 25104606,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104606/a-proactive-folder-watcher-in-linux\",\n" +
            "            \"title\": \"a proactive folder watcher in Linux\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"ruby-on-rails\",\n" +
            "                \"ruby\",\n" +
            "                \"ruby-on-rails-4\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 6,\n" +
            "                \"user_id\": 3902843,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/1d95611bcdd9a395ade00c27c7a1c64b?s=128&d=identicon&r=PG&f=1\",\n" +
            "                \"display_name\": \"Martin T.\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3902843/martin-t\"\n" +
            "            },\n" +
            "            \"is_answered\": true,\n" +
            "            \"view_count\": 25,\n" +
            "            \"answer_count\": 1,\n" +
            "            \"score\": -1,\n" +
            "            \"last_activity_date\": 1407070232,\n" +
            "            \"creation_date\": 1407014028,\n" +
            "            \"last_edit_date\": 1407023093,\n" +
            "            \"question_id\": 25099472,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25099472/perform-multiple-actions-with-1-post\",\n" +
            "            \"title\": \"Perform multiple actions with 1 post\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"php\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 828,\n" +
            "                \"user_id\": 1071,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 78,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/8dab45e5ca5f4ca03e3ba355d2410d00?s=128&d=identicon&r=PG\",\n" +
            "                \"display_name\": \"tim\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/1071/tim\"\n" +
            "            },\n" +
            "            \"is_answered\": true,\n" +
            "            \"view_count\": 91003,\n" +
            "            \"accepted_answer_id\": 45966,\n" +
            "            \"answer_count\": 13,\n" +
            "            \"score\": 131,\n" +
            "            \"last_activity_date\": 1407070232,\n" +
            "            \"creation_date\": 1220625828,\n" +
            "            \"last_edit_date\": 1220798661,\n" +
            "            \"question_id\": 45953,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/45953/php-execute-a-background-process\",\n" +
            "            \"title\": \"php execute a background process\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"swift\",\n" +
            "                \"closures\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 297,\n" +
            "                \"user_id\": 883360,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/8a3ae3040204636f0ac9d207cacdee95?s=128&d=identicon&r=PG\",\n" +
            "                \"display_name\": \"boxi\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/883360/boxi\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 4,\n" +
            "            \"answer_count\": 1,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070228,\n" +
            "            \"creation_date\": 1407069342,\n" +
            "            \"last_edit_date\": 1407069773,\n" +
            "            \"question_id\": 25104689,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104689/how-can-i-get-initial-closure-variable-in-swift\",\n" +
            "            \"title\": \"How can I get initial closure variable in Swift?\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"resharper\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 1434,\n" +
            "                \"user_id\": 512915,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 99,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/084255cacb5f9189e684769b08b8d570?s=128&d=identicon&r=PG\",\n" +
            "                \"display_name\": \"Robert\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/512915/robert\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 5,\n" +
            "            \"answer_count\": 1,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070227,\n" +
            "            \"creation_date\": 1406906282,\n" +
            "            \"question_id\": 25083430,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25083430/how-to-substitute-a-return-value-based-on-the-return-type-of-a-method-with-resha\",\n" +
            "            \"title\": \"How to substitute a return value based on the return type of a method with Resharper Macros?\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"java\",\n" +
            "                \"regex\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 8,\n" +
            "                \"user_id\": 3469310,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/4e450d7649a97b40917b0d14d3d47502?s=128&d=identicon&r=PG&f=1\",\n" +
            "                \"display_name\": \"user3469310\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3469310/user3469310\"\n" +
            "            },\n" +
            "            \"is_answered\": true,\n" +
            "            \"view_count\": 35,\n" +
            "            \"accepted_answer_id\": 25104633,\n" +
            "            \"answer_count\": 4,\n" +
            "            \"score\": 1,\n" +
            "            \"last_activity_date\": 1407070212,\n" +
            "            \"creation_date\": 1407067669,\n" +
            "            \"last_edit_date\": 1407067782,\n" +
            "            \"question_id\": 25104486,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104486/java-regular-expressions-escape-characters\",\n" +
            "            \"title\": \"Java regular expressions escape characters\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"php\",\n" +
            "                \"mysql\",\n" +
            "                \"arrays\",\n" +
            "                \"session\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 26,\n" +
            "                \"user_id\": 2627892,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 67,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/25d129ca81294069187f39dbbcb3d17a?s=128&d=identicon&r=PG\",\n" +
            "                \"display_name\": \"Aayush\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/2627892/aayush\"\n" +
            "            },\n" +
            "            \"is_answered\": true,\n" +
            "            \"view_count\": 7951,\n" +
            "            \"answer_count\": 3,\n" +
            "            \"score\": 1,\n" +
            "            \"last_activity_date\": 1407070209,\n" +
            "            \"creation_date\": 1377727584,\n" +
            "            \"question_id\": 18499034,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/18499034/how-to-store-an-array-into-a-session-variable-in-php\",\n" +
            "            \"title\": \"How to store an array into a session variable in php\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"java\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 41,\n" +
            "                \"user_id\": 3164482,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 44,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/5786cbf22d3706add59c65ea0f164dd1?s=128&d=identicon&r=PG&f=1\",\n" +
            "                \"display_name\": \"John\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3164482/john\"\n" +
            "            },\n" +
            "            \"is_answered\": true,\n" +
            "            \"view_count\": 21,\n" +
            "            \"answer_count\": 1,\n" +
            "            \"score\": -2,\n" +
            "            \"last_activity_date\": 1407070194,\n" +
            "            \"creation_date\": 1407067377,\n" +
            "            \"question_id\": 25104447,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104447/how-to-get-url-using-regular-expression-in-java\",\n" +
            "            \"title\": \"How to get URL using regular expression in java?\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"file\",\n" +
            "                \"junit\",\n" +
            "                \"gradle\",\n" +
            "                \"folder\",\n" +
            "                \"temporary\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 709,\n" +
            "                \"user_id\": 1732936,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 81,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/f107992a766854d72c417ca7d27456aa?s=128&d=identicon&r=PG\",\n" +
            "                \"display_name\": \"Xelian\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/1732936/xelian\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 2,\n" +
            "            \"answer_count\": 0,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070187,\n" +
            "            \"creation_date\": 1407070187,\n" +
            "            \"question_id\": 25104806,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104806/how-to-create-temporary-file-folder-using-public-gradle-api\",\n" +
            "            \"title\": \"How to create temporary file/folder using public Gradle API?\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"sql\",\n" +
            "                \"oracle\",\n" +
            "                \"plsql\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 30,\n" +
            "                \"user_id\": 3808188,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 92,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/7a7c8de4b5428cb8437884b733aa1205?s=128&d=identicon&r=PG&f=1\",\n" +
            "                \"display_name\": \"user3808188\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3808188/user3808188\"\n" +
            "            },\n" +
            "            \"is_answered\": true,\n" +
            "            \"view_count\": 71,\n" +
            "            \"bounty_amount\": 50,\n" +
            "            \"bounty_closes_date\": 1407627013,\n" +
            "            \"answer_count\": 2,\n" +
            "            \"score\": 4,\n" +
            "            \"last_activity_date\": 1407070165,\n" +
            "            \"creation_date\": 1406827864,\n" +
            "            \"last_edit_date\": 1406834355,\n" +
            "            \"question_id\": 25065372,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25065372/query-to-assign-the-same-id-to-row-being-inserted-if-it-it-already-exists-in-the\",\n" +
            "            \"title\": \"Query to assign the same ID to row being inserted if it it already exists in the table\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"c#\",\n" +
            "                \"xaml\",\n" +
            "                \"windows-phone-8\",\n" +
            "                \"longlistselector\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 41,\n" +
            "                \"user_id\": 3731330,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 100,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/c13f6edaffc30931809e64aaba927950?s=128&d=identicon&r=PG&f=1\",\n" +
            "                \"display_name\": \"Leek\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3731330/leek\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 46,\n" +
            "            \"answer_count\": 2,\n" +
            "            \"score\": 1,\n" +
            "            \"last_activity_date\": 1407070162,\n" +
            "            \"creation_date\": 1406999426,\n" +
            "            \"last_edit_date\": 1407070162,\n" +
            "            \"question_id\": 25097495,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25097495/longlist-is-not-populated-with-items\",\n" +
            "            \"title\": \"Longlist is not populated with items\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"python\",\n" +
            "                \"pdf\",\n" +
            "                \"decode\",\n" +
            "                \"encode\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 7,\n" +
            "                \"user_id\": 1190288,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 40,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/3f3a4ab6b8bda1e2cc89ab50ca04e6f4?s=128&d=identicon&r=PG\",\n" +
            "                \"display_name\": \"user1190288\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/1190288/user1190288\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 3,\n" +
            "            \"answer_count\": 0,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070156,\n" +
            "            \"creation_date\": 1407070156,\n" +
            "            \"question_id\": 25104804,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104804/pypdf2-returning-only-blank-lines-endecode-issue\",\n" +
            "            \"title\": \"PyPDF2 - Returning only blank lines. En(de)code issue?\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"python\",\n" +
            "                \"beautifulsoup\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 602,\n" +
            "                \"user_id\": 1707938,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 64,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/f025df15ff5e39971ea54463a02f9c38?s=128&d=identicon&r=PG\",\n" +
            "                \"display_name\": \"CptNemo\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/1707938/cptnemo\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 9,\n" +
            "            \"answer_count\": 1,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070152,\n" +
            "            \"creation_date\": 1407066724,\n" +
            "            \"question_id\": 25104365,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104365/parse-multiple-paragraph-in-a-single-loop-cycle-with-beautifulsoup\",\n" +
            "            \"title\": \"Parse multiple paragraph in a single loop-cycle with BeautifulSoup\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"xpages\",\n" +
            "                \"lotus-domino\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 1,\n" +
            "                \"user_id\": 3903618,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"profile_image\": \"http://i.stack.imgur.com/vVEKo.jpg?s=128&g=1\",\n" +
            "                \"display_name\": \"Palmi lord\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3903618/palmi-lord\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 16,\n" +
            "            \"answer_count\": 1,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070148,\n" +
            "            \"creation_date\": 1407058852,\n" +
            "            \"question_id\": 25103409,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25103409/execute-standby-dialog-from-link\",\n" +
            "            \"title\": \"execute standby dialog from link\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"java\",\n" +
            "                \"sql\",\n" +
            "                \"oracle\",\n" +
            "                \"date\",\n" +
            "                \"to-date\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 1,\n" +
            "                \"user_id\": 3903898,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/e9c6c702e99929b6004b1d5ebf6b726d?s=128&d=identicon&r=PG&f=1\",\n" +
            "                \"display_name\": \"shashin\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3903898/shashin\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 7,\n" +
            "            \"answer_count\": 0,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070143,\n" +
            "            \"creation_date\": 1407070017,\n" +
            "            \"last_edit_date\": 1407070143,\n" +
            "            \"question_id\": 25104784,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104784/java-sql-sqlexception-ora-01843-not-a-valid-month-error\",\n" +
            "            \"title\": \"java.sql.SQLException: ORA-01843: not a valid month error\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"javascript\",\n" +
            "                \"css\",\n" +
            "                \"angularjs\",\n" +
            "                \"css3\",\n" +
            "                \"ng-animate\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 95,\n" +
            "                \"user_id\": 1027860,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 89,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/882297473e8a4ba9b4e9133f0a5b9738?s=128&d=identicon&r=PG&f=1\",\n" +
            "                \"display_name\": \"Mads K\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/1027860/mads-k\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 11,\n" +
            "            \"answer_count\": 1,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070140,\n" +
            "            \"creation_date\": 1407068158,\n" +
            "            \"last_edit_date\": 1407068500,\n" +
            "            \"question_id\": 25104540,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104540/ng-view-fades-in-on-purpose-but-then-fades-out\",\n" +
            "            \"title\": \"ng-view fades in on purpose, but then fades out\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"openerp\",\n" +
            "                \"xml-rpc\",\n" +
            "                \"odoo\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 389,\n" +
            "                \"user_id\": 218208,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 71,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/f5dd6b8738cba9c2bbf3e6c30e483d36?s=128&d=identicon&r=PG\",\n" +
            "                \"display_name\": \"Jason\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/218208/jason\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 5,\n" +
            "            \"answer_count\": 0,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070138,\n" +
            "            \"creation_date\": 1407034423,\n" +
            "            \"last_edit_date\": 1407070138,\n" +
            "            \"question_id\": 25101195,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25101195/details-on-the-odoo-xml-rpc-data-structures\",\n" +
            "            \"title\": \"Details on the Odoo XML-RPC data structures?\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"android\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 1,\n" +
            "                \"user_id\": 2558388,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/80fb9ed55d4021640eb1480564460467?s=128&d=identicon&r=PG\",\n" +
            "                \"display_name\": \"user2558388\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/2558388/user2558388\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 28,\n" +
            "            \"closed_date\": 1407026239,\n" +
            "            \"answer_count\": 1,\n" +
            "            \"score\": -6,\n" +
            "            \"last_activity_date\": 1407070130,\n" +
            "            \"creation_date\": 1407023373,\n" +
            "            \"last_edit_date\": 1407070130,\n" +
            "            \"question_id\": 25100371,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25100371/food-ordering-app-for-android\",\n" +
            "            \"closed_reason\": \"too broad\",\n" +
            "            \"title\": \"Food Ordering app for Android\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"drupal\",\n" +
            "                \"drupal-8\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 1,\n" +
            "                \"user_id\": 3903892,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"profile_image\": \"http://i.stack.imgur.com/7RmYn.jpg?s=128&g=1\",\n" +
            "                \"display_name\": \"imrancluster\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3903892/imrancluster\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 3,\n" +
            "            \"answer_count\": 0,\n" +
            "            \"score\": -2,\n" +
            "            \"last_activity_date\": 1407070128,\n" +
            "            \"creation_date\": 1407070128,\n" +
            "            \"question_id\": 25104801,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104801/need-recommendation-for-drupal-8\",\n" +
            "            \"title\": \"Need recommendation for drupal 8\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"java\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 11,\n" +
            "                \"user_id\": 3554953,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/dcecde04bf3ce12dce6b48487a744658?s=128&d=identicon&r=PG&f=1\",\n" +
            "                \"display_name\": \"Lifter\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3554953/lifter\"\n" +
            "            },\n" +
            "            \"is_answered\": true,\n" +
            "            \"view_count\": 35,\n" +
            "            \"answer_count\": 1,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070127,\n" +
            "            \"creation_date\": 1407068262,\n" +
            "            \"last_edit_date\": 1407068630,\n" +
            "            \"question_id\": 25104550,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104550/downcasting-in-inheritance\",\n" +
            "            \"title\": \"Downcasting in Inheritance\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"jquery\",\n" +
            "                \"forms\",\n" +
            "                \"validation\",\n" +
            "                \"jquery-validate\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 197,\n" +
            "                \"user_id\": 3112128,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 87,\n" +
            "                \"profile_image\": \"http://i.stack.imgur.com/ep58c.jpg?s=128&g=1\",\n" +
            "                \"display_name\": \"Arbiter\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3112128/arbiter\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 3,\n" +
            "            \"answer_count\": 0,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070124,\n" +
            "            \"creation_date\": 1407070124,\n" +
            "            \"question_id\": 25104800,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104800/jquery-validate-typeerror-a-validator-methodsmethod\",\n" +
            "            \"title\": \"JQuery .validate() TypeError: a.validator.methods[method]\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"ruby-on-rails\",\n" +
            "                \"ruby-on-rails-4\",\n" +
            "                \"nested-attributes\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 631,\n" +
            "                \"user_id\": 1419884,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 74,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/9ab5ef0479dac48e6e84a56bf46250f9?s=128&d=identicon&r=PG\",\n" +
            "                \"display_name\": \"Peter Tretyakov\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/1419884/peter-tretyakov\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 7,\n" +
            "            \"answer_count\": 1,\n" +
            "            \"score\": 0,\n" +
            "            \"last_activity_date\": 1407070121,\n" +
            "            \"creation_date\": 1407069212,\n" +
            "            \"question_id\": 25104667,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104667/nested-model-doesnt-update\",\n" +
            "            \"title\": \"Nested model doesn&#39;t update\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"c#\",\n" +
            "                \"c#-4.0\",\n" +
            "                \"c#-3.0\",\n" +
            "                \"helper\",\n" +
            "                \"c#-5.0\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 1,\n" +
            "                \"user_id\": 3693829,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"profile_image\": \"http://graph.facebook.com/100000174316312/picture?type=large\",\n" +
            "                \"display_name\": \"user3693829\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/3693829/user3693829\"\n" +
            "            },\n" +
            "            \"is_answered\": false,\n" +
            "            \"view_count\": 6,\n" +
            "            \"answer_count\": 0,\n" +
            "            \"score\": -3,\n" +
            "            \"last_activity_date\": 1407070115,\n" +
            "            \"creation_date\": 1407070115,\n" +
            "            \"question_id\": 25104798,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25104798/formatting-numbers\",\n" +
            "            \"title\": \"Formatting Numbers?\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"has_more\": true,\n" +
            "    \"quota_max\": 300,\n" +
            "    \"quota_remaining\": 280\n" +
            "}";
    final static String shortTestJson = "{\n" +
            "    \"items\": [\n" +
            "        {\n" +
            "            \"tags\": [\n" +
            "                \"javascript\",\n" +
            "                \"jquery\",\n" +
            "                \"scope\"\n" +
            "            ],\n" +
            "            \"owner\": {\n" +
            "                \"reputation\": 298,\n" +
            "                \"user_id\": 1302438,\n" +
            "                \"user_type\": \"registered\",\n" +
            "                \"accept_rate\": 82,\n" +
            "                \"profile_image\": \"https://www.gravatar.com/avatar/ade3d093c3d5d7d8a690a087b37a1dfa?s=128&d=identicon&r=PG\",\n" +
            "                \"display_name\": \"RecoJohnson\",\n" +
            "                \"link\": \"http://stackoverflow.com/users/1302438/recojohnson\"\n" +
            "            },\n" +
            "            \"is_answered\": true,\n" +
            "            \"view_count\": 28,\n" +
            "            \"answer_count\": 4,\n" +
            "            \"score\": 1,\n" +
            "            \"last_activity_date\": 1407073864,\n" +
            "            \"creation_date\": 1406946691,\n" +
            "            \"last_edit_date\": 1407073864,\n" +
            "            \"question_id\": 25091100,\n" +
            "            \"link\": \"http://stackoverflow.com/questions/25091100/how-do-i-make-a-jquery-id-selection-equal-to-this-inside-a-function\",\n" +
            "            \"title\": \"How do I make a jquery id selection equal to this inside a function?\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"has_more\": true,\n" +
            "    \"quota_max\": 10000,\n" +
            "    \"quota_remaining\": 9973\n" +
            "}";

    static Questions shortJsonQuestions;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RestClient.init(getInstrumentation().getTargetContext());
        initShortQuestion();
    }

    private void initShortQuestion() {
        shortJsonQuestions = new Questions();
        Owner owner = new Owner();
        owner.reputation = 298;
        owner.user_id = 1302438;
        owner.user_type = "registered";
        owner.accept_rate = 82;
        owner.profile_image = "https://www.gravatar.com/avatar/ade3d093c3d5d7d8a690a087b37a1dfa?s=128&d=identicon&r=PG";
        owner.display_name = "RecoJohnson";
        owner.link = "http://stackoverflow.com/users/1302438/recojohnson";
        Question question = new Question();
        question.tags = new String[]{"javascript", "jquery", "scope"};
        question.owner = owner;
        question.is_answered = true;
        question.view_count = 28;
        question.answer_count = 4;
        question.score = 1;
        question.question_id = 25091100;
        question.link = "http://stackoverflow.com/questions/25091100/how-do-i-make-a-jquery-id-selection-equal-to-this-inside-a-function";
        question.title = "How do I make a jquery id selection equal to this inside a function?";
        question.last_activity_date = 1407073864;
        question.creation_date = 1406946691;
        question.last_edit_date = 1407073864;
        shortJsonQuestions.has_more = true;
        shortJsonQuestions.items = new ArrayList<Question>(1);
        shortJsonQuestions.items.add(question);
        shortJsonQuestions.quota_max = 10000;
        shortJsonQuestions.quota_remaining = 9973;
    }

    public void testParsingFunctionality() {
        RestClient restClient = RestClient.getInstance();
        try {
            Method parseQuestions = TestUtils.getMethod("parseQuestions", RestClient.class, String.class);
            Questions questions = (Questions) parseQuestions.invoke(restClient, testJson);
            assertNotNull(questions);
            assertNotNull(questions.items);
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

    public void testParsingCorrectness() {
        RestClient restClient = RestClient.getInstance();
        try {
            Method parseQuestions = TestUtils.getMethod("parseQuestions", RestClient.class, String.class);
            Questions questions = (Questions) parseQuestions.invoke(restClient, shortTestJson);
            assertEquals(shortJsonQuestions, questions);
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

    public void testParsingEmpty() {
        RestClient restClient = RestClient.getInstance();
        try {
            Method parseQuestions = TestUtils.getMethod("parseQuestions", RestClient.class, String.class);
            Questions questions = (Questions) parseQuestions.invoke(restClient, "");
            assertNull(questions);
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

    public void testParsingIncorrectFormat() {
        RestClient restClient = RestClient.getInstance();
        try {
            Method parseQuestions = TestUtils.getMethod("parseQuestions", RestClient.class, String.class);
            Questions questions = (Questions) parseQuestions.invoke(restClient, "rugjie{rgieg}");
            assertNull(questions);
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

    public void testParsingHttpResponse() {
        RestClient restClient = RestClient.getInstance();
        try {
            HttpResponse response = generateResponse(testJson, "deflate");
            Method parseQuestions = TestUtils.getMethod("parseQuestions", RestClient.class, HttpResponse.class);
            Questions questions = (Questions) parseQuestions.invoke(restClient, response);
            assertNotNull(questions);
            assertNotNull(questions.items);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            assertFalse("Could not find the method", true);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            assertFalse("Could not invoke the method", true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            assertFalse("Could not access the given method", true);
        } catch (IOException e) {
            e.printStackTrace();
            assertFalse("Could not generate stream", true);
        }

    }

    public void testParsingHttpResponseGzip() {
        RestClient restClient = RestClient.getInstance();
        try {
            HttpResponse response = generateResponse(testJson, "gzip");
            Method parseQuestions = TestUtils.getMethod("parseQuestions", RestClient.class, HttpResponse.class);
            Questions questions = (Questions) parseQuestions.invoke(restClient, response);
            assertNotNull(questions);
            assertNotNull(questions.items);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            assertFalse("Could not find the method", true);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            assertFalse("Could not invoke the method", true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            assertFalse("Could not access the given method", true);
        } catch (IOException e) {
            e.printStackTrace();
            assertFalse("Could not generate stream", true);
        }

    }

    public void testParsingCorrectnessHttpResponse() {
        RestClient restClient = RestClient.getInstance();
        try {
            HttpResponse response = generateResponse(shortTestJson, "");
            Method parseQuestions = TestUtils.getMethod("parseQuestions", RestClient.class, HttpResponse.class);
            Questions questions = (Questions) parseQuestions.invoke(restClient, response);
            assertEquals(shortJsonQuestions, questions);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            assertFalse("Could not find the method", true);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            assertFalse("Could not invoke the method", true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            assertFalse("Could not access the given method", true);
        } catch (IOException e) {
            e.printStackTrace();
            assertFalse("Could not generate stream", true);
        }

    }


    private HttpResponse generateResponse(String string, String encoding) throws IOException {
        HttpResponse response = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), HttpStatus.SC_OK, "OK"));
        response.addHeader("Content-Encoding", encoding);
        InputStream stream;
        if ("gzip".equals(encoding)) {
            stream = new GZIPInputStream(new ByteArrayInputStream(string.getBytes(), 0, string.length()), string.length());
        } else {
            stream = new ByteArrayInputStream(string.getBytes());
        }
        HttpEntity httpEntity = new InputStreamEntity(stream, string.length());
        response.setEntity(httpEntity);
        return response;
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        RestClient.getInstance().close();
        shortJsonQuestions = null;
    }
}
