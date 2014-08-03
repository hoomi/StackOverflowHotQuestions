package ostovari.android.com.stackoverflowhotquestions.communications;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.http.AndroidHttpClient;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.zip.GZIPInputStream;

import ostovari.android.com.stackoverflowhotquestions.model.Question;
import ostovari.android.com.stackoverflowhotquestions.model.Questions;
import ostovari.android.com.stackoverflowhotquestions.utils.Logger;

/**
 * Created by hostova1 on 03/08/2014.
 */
public final class RestClient implements Closeable {

    private static Gson GSON;
    private static RestClient restClient;
    private static AndroidHttpClient androidHttpClient;
    private static final String UA = "Unknown";

    public static void init(Context context) {
        restClient = new RestClient(context);
    }

    private static String generateUA(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String userAgent = "Unknown";
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.DONT_KILL_APP);
            if (packageInfo != null) {
                userAgent = String.format("Name:%s Version:%s",
                        packageInfo.applicationInfo.name, packageInfo.versionName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return userAgent;
    }

    public RestClient(Context context) {
        GSON = new Gson();
        androidHttpClient = AndroidHttpClient.newInstance(generateUA(context), context);
    }

    public synchronized static RestClient getInstance() {
        if (restClient == null) {
            throw new IllegalStateException("The RestClient has not bee initialized yet. Call init() before any other call");
        }
        return restClient;
    }

    public Questions getTheListOfQuestions() {
        if (androidHttpClient == null) {
            androidHttpClient = AndroidHttpClient.newInstance(UA);
        }
        HttpGet get = new HttpGet("http://api.stackexchange.com/2.2/questions?order=desc&sort=hot&site=stackoverflow");
        try {
            HttpResponse response = androidHttpClient.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return parseQuestions(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Questions parseQuestions(@NonNull HttpResponse response) throws IOException {
        String encoding = null;
        Header header = response.getFirstHeader("Content-Encoding");
        if (header != null) {
            encoding = header.getValue();
        }

        String s;
        if ("gzip".equals(encoding)) {
            s = decompress(response.getEntity().getContent());
        } else {
            s = EntityUtils.toString(response.getEntity());
        }
        Logger.d(this, "Compressed S: " + s);
        return parseQuestions(s);
    }

    /**
     * It parses the llist of questions from the given json string
     *
     * @param jsonString It must be decompressed
     * @return
     */
    private Questions parseQuestions(String jsonString) {
        Questions questions = null;
        if (!TextUtils.isEmpty(jsonString)) {
            Logger.d(this, "Json String: " + jsonString);
            try {
                questions = GSON.fromJson(jsonString, Questions.class);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }
        return questions;
    }

    public static String decompress(InputStream is) throws IOException {
        GZIPInputStream zis = new GZIPInputStream(new BufferedInputStream(is));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int value = 0; value != -1; ) {
            value = zis.read();
            if (value != -1) {
                baos.write(value);
            }
        }
        zis.close();
        baos.close();
        return new String(baos.toByteArray(), "UTF-8");
    }

    public void closeClient() {
        if (androidHttpClient != null) {
            androidHttpClient.close();
            androidHttpClient = null;
        }
    }


    @Override
    public void close() {
        closeClient();
        GSON = null;
        restClient = null;
    }
}
