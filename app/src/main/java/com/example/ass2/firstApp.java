package com.example.ass2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class firstApp extends AppCompatActivity {

    final String url = "https://who-covid-19-data.p.rapidapi.com/api/data";
    final String apiKey = "a8e5f067c4mshcd8f1077035542ep1678dajsn7664d53068bb";
    TextView txtResult;
    //RequestQueue queue;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_app);

        txtResult = findViewById(R.id.txtResult);
        btn1 = findViewById(R.id.btnFetchData);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }
    public void getData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray json = new JSONArray(response);
                            StringBuilder info = new StringBuilder();
                            for (int i = 0; i < json.length(); i++) {
                                JSONObject countryData = json.getJSONObject(i);
                                String name = countryData.getString("name");
                                int cases = countryData.getInt("cases");
                                int deaths = countryData.getInt("deaths");
                                info.append("Country: ").append(name).append("\nCases: ").append(cases)
                                        .append("\nDeaths: ").append(deaths).append("\n\n");
                            }
                            txtResult.setText(info.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(firstApp.this, "Error, " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            /* i tried many times to do like weather app and it not working and not giv me any data
            and i think first that there is problems an i change api's then keeps not giving me data
            at last put my code to see what the error then all internet advised me to use headers
            i put at last headers and it working good and give me data and i understand this new part fully */
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-RapidAPI-Key", apiKey);
                headers.put("X-RapidAPI-Host", "who-covid-19-data.p.rapidapi.com");
                return headers;
            }
        };

        queue.add(stringRequest);
    }
}
