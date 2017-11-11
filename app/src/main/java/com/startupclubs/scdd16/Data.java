package com.startupclubs.scdd16;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Data {

    public enum NetworkRequest { Success, Failure, UnableToConnect }

    public static final String PREFERENCE_NAME = "SCDD_data";
    public static final String FIRST_USE_KEY = "first_use";
    public static final String EVENT_ITEM_CLICKED_KEY = "event_item_clicked";

    public static String returnedString = "";
    private static boolean loggedIn;

    public static NetworkRequest validateLogin(String username, String password) {
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart("username", username)
                    .addFormDataPart("password", password)
                    .build();
            Request request = new Request.Builder()
                    .url("http://www.startups-club.comlu.com/login.php")
                    .method("POST", formBody)
                    .build();
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            if(result.contains("<!-- Hosting24 Analytics Code -->")) {
                int index = result.indexOf("<!-- Hosting24 Analytics Code -->");
                result = result.substring(0, index);
            }
            returnedString = result;
            if(result.startsWith("true"))
                return loadData();
            else
                return NetworkRequest.Failure;
        } catch (IOException ex) {
            ex.printStackTrace();
            return NetworkRequest.UnableToConnect;
        }
    }

    public static NetworkRequest loadData() {
        //TODO load the data required to display from the server
        try {
            //fetch the xml file from a URL
            URL url = new URL("http://www.startups-club.comlu.com/events.xml");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            InputStream stream = conn.getInputStream();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(stream, null);

            //now parse the xml
            int type = parser.getEventType();
            Event event = new Event();
            while (type != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                switch (type) {
                    case XmlPullParser.START_TAG:
                        if (name.equals("Event")) {
                            event = new Event();
                            event.setCity(parser.getAttributeValue(null, "city"));
                            event.setTitle(parser.getAttributeValue(null, "title"));
                            event.setYear(parser.getAttributeValue(null, "year"));
                            event.setDate(parser.getAttributeValue(null, "date"));
                            event.setDay(parser.getAttributeValue(null, "day"));
                        } else if (name.equals("Speaker")) {
                            Event.Speaker speaker = new Event.Speaker();
                            speaker.setName(parser.getAttributeValue(null, "name"));
                            if (!parser.getAttributeValue(null, "photo").equals("null")) {
                                URL photoUrl = new URL(parser.getAttributeValue(null, "photo"));
                                Bitmap photo = BitmapFactory.decodeStream(photoUrl.openConnection().getInputStream());
                                speaker.setPhoto(photo);
                            }
                            //else {
                                //speaker.setPhoto(some default photo or something like that);
                            //}
                            String about = parser.nextText();
                            while (about.contains("  ")) {
                                about = about.replaceAll("  ", " ");
                            }
                            speaker.setAbout(about);
                            event.addSpeaker(speaker);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        switch (name) {
                            case "Event":
                                Event.addEvent(event);
                                break;
                            case "Agenda":
                                Event.Agenda agenda = new Event.Agenda();
                                agenda.setTiming(parser.getAttributeValue(null, "timing"));
                                agenda.setTitle(parser.getAttributeValue(null, "title"));
                                event.addAgenda(agenda);
                                break;
                            case "Sponsor":
                                Event.Sponsor sponsor = new Event.Sponsor();
                                sponsor.setName(parser.getAttributeValue(null, "name"));
                                if (!parser.getAttributeValue(null, "logo").equals("null")) {
                                    URL photoUrl = new URL(parser.getAttributeValue(null, "photo"));
                                    Bitmap photo = BitmapFactory.decodeStream(photoUrl.openConnection().getInputStream());
                                    sponsor.setLogo(photo);
                                }
                                //else {
                                //speaker.setPhoto(some default photo or something like that);
                                //}
                                break;
                        }
                        break;
                }
                type = parser.next();
            }
            stream.close();
            return NetworkRequest.Success;
        } catch (XmlPullParserException | IOException ex) {
            ex.printStackTrace();
            return NetworkRequest.UnableToConnect;
        }
    }

    /**
     * Load all the data from the local database (SharedPreferences)
     * @param context The context to use to load all the data
     */
    public static void load(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        loggedIn = preferences.getBoolean(FIRST_USE_KEY, true);
        //TODO load other stuff
    }

    public static void save(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(FIRST_USE_KEY, loggedIn);
        //TODO save other stuff
        editor.apply();
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }
    public static void setLoggedIn(boolean loggedIn) {
        Data.loggedIn = loggedIn;
    }
}
