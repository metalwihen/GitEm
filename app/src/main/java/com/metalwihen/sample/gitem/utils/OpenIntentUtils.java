package com.metalwihen.sample.gitem.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Patterns;
import android.widget.Toast;

import com.metalwihen.sample.gitem.R;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenIntentUtils {

    private OpenIntentUtils() {
    }

    public static void openUrl(Context context, String urlString) {
        URL url;
        try {
            domainToURL(urlString); // TODO Test this if it works fine

            url = new URL(urlString);
            Uri uri = Uri.parse(url.toURI().toString());
            context.startActivity(new Intent(Intent.ACTION_VIEW, uri));

        } catch (MalformedURLException e1) {
            Toast.makeText(context, R.string.msg_unable_to_open_link, Toast.LENGTH_LONG).show();
        } catch (URISyntaxException e) {
            Toast.makeText(context, R.string.msg_unable_to_open_link, Toast.LENGTH_LONG).show();
        }
    }

    public static String domainToURL(String domain) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(domain);

        if (m.matches()) {
            String protocol = m.group(1);
            String host = m.group(2);

            if (protocol == null) {
                protocol = "https";
            }

            return String.format("%s://%s", protocol, host);
        }
        return null;
    }
}
