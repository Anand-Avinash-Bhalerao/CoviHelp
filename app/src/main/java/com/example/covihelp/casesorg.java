package com.example.covihelp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class casesorg extends AppCompatActivity {
    Button covidOrg;
    //    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String LOG_TAG = "DebugK";
    public static final String covid_url = "https://data.covid19india.org/v4/min/data.min.json";
    public ArrayList<CovidCityStats> toDisplay;
    public List<CovidCityStats> covidCityStats = new ArrayList<>();
    public ListView listView;
    public RecyclerView recyclerView;
//    public AdapterC adapterC;
    public RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casesorg);

        covidOrg = findViewById(R.id.covid19india);

        Log.d(LOG_TAG, "at the start the covid stats has " + covidCityStats.size());


        try {
//            adapterC = new AdapterC(this,  covidCityStats);
            CovidAsyncTask covidAsyncTask = (CovidAsyncTask) new CovidAsyncTask(this, this).execute();

        } catch (Exception e) {
            Log.e("Crash", "oncreate me ho raha hai problem");
        }
        covidOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                covidSiteRedirect();
            }
        });
    }

    public void covidSiteRedirect() {
        String url = "https://www.covid19india.org";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public class CovidAsyncTask extends AsyncTask<String, String, Void> {

        BufferedInputStream inputStream;
        JSONArray jsonArray;
        String result = "";
        Activity activity;
        Context context;

        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;

            }
            return url;
        }

        public CovidAsyncTask(Activity activity, Context context) {
            this.activity = activity;
            this.context = context;
        }

        @Override
        protected Void doInBackground(String... strings) {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            URL url = createUrl(covid_url);
            try {
                assert url != null;
                urlConnection = (HttpURLConnection) url.openConnection();
                Log.d(LOG_TAG, "after OpenConnection");
                urlConnection.setRequestMethod("GET");
                Log.d(LOG_TAG, "after setReq");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                Log.d(LOG_TAG, "after readTimeout");
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                Log.d(LOG_TAG, "after connectTime");
                urlConnection.connect();
                Log.d(LOG_TAG, "after connect()");
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                    Log.d(LOG_TAG, "The connection was made");
                } else {
                    Log.d(LOG_TAG, "The connection was not made");
                }
                result = jsonResponse;
                Log.d("bekar", "" + result);
            } catch (IOException e) {
            } catch (Exception e) {
                Log.e(LOG_TAG, "kuch toh hua");
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @NonNull
        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        @Override
        protected void onPostExecute(Void unused) {
            try {
                JSONObject baseJsonResponse = new JSONObject(result);
                Log.d(LOG_TAG, "Base length is: " + baseJsonResponse.length());
                JSONObject maharashtra = baseJsonResponse.optJSONObject("MH");
                Log.d(LOG_TAG, "Maharashtra length is: " + maharashtra.length());
                JSONObject disData = maharashtra.optJSONObject("districts");
                Log.d(LOG_TAG, "District length is: " + disData.length());
//                JSONObject pune = disData.optJSONObject("Pune");
                for (int i = 0; i < disData.names().length(); i++) {
                    CovidCityStats temp = new CovidCityStats();
                    String city = disData.names().optString(i);
                    JSONObject current = disData.getJSONObject(city);
                    JSONObject total = current.getJSONObject("total");
                    int confirmed = total.optInt("confirmed");
                    int ded = total.optInt("deceased");
                    int recovered = total.optInt("recovered");
                    int active = confirmed - ded - recovered;
                    temp.setCity(city);
                    temp.setConfirmed(confirmed);
                    temp.setActive(active);
                    temp.setRecovered(recovered);
                    temp.setDed(ded);
                    covidCityStats.add(temp);
                    Log.d(LOG_TAG, "City = " + city + ", Confirmed = " + confirmed + ",Active  = " + active + ",Recovered  = " + recovered + ", ded = " + ded);
                }
//                Toast.makeText(MainActivity.this, "Execution khatam", Toast.LENGTH_SHORT).show();
//                MainActivity.this.setTheList();
//                Toast.makeText(MainActivity.this, "The number of cities added are: "+covidCityStats.size(), Toast.LENGTH_SHORT).show();
                Log.d(LOG_TAG, "ListView k just upar");
                listView = this.activity.findViewById(R.id.info_list);
                recyclerView = this.activity.findViewById(R.id.recycler);
                try {
                    Log.d(LOG_TAG, "list view ko set karne k just bad ka line hai ye");
//                    AdapterC adapterC = new AdapterC(MainActivity.this, 0, covidCityStats);
//                    AdapterC adapterC = new AdapterC(this.context, 0, covidCityStats);
                    Log.d(LOG_TAG, "Adapter ko initialize karne k baad ka hai ye");
                    Log.d(LOG_TAG, covidCityStats.size() + " is the size of covid city stats");
                    for (int i = 0; i < covidCityStats.size(); i++) {
                        Log.d(LOG_TAG, covidCityStats.get(i).getCity() + " " + covidCityStats.get(i).getConfirmed());
                    }
//                    listView.setAdapter(adapterC);
                    Log.d(LOG_TAG, "at the end the covid stats has " + covidCityStats.size());
                    recyclerAdapter = new RecyclerAdapter(context, covidCityStats);
                    recyclerView.setAdapter(recyclerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    Log.d(LOG_TAG, "Adapter set karne k baad ka hai ye");
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Adapter me kuch toh problem hai");
                }
//                TextView infoVala = this.activity.findViewById(R.id.info);
//                StringBuilder temp = new StringBuilder("");
//                for (int i = 0; i < covidCityStats.size(); i++) {
//                    StringBuilder append = temp.append(covidCityStats.get(i).getCity() + " " + covidCityStats.get(i).getCases() + "\n");
//                }
//                infoVala.setText(temp.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(casesorg.this, "Kuch toh problem hai daya", Toast.LENGTH_SHORT).show();
            }
        }
    }
}