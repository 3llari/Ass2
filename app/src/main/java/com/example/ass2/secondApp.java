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
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class secondApp extends AppCompatActivity {

    final String url = "https://exchangerate-api.p.rapidapi.com/rapid/latest/USD";
    final String apiKey = "a8e5f067c4mshcd8f1077035542ep1678dajsn7664d53068bb";
    TextView txtResult;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_app);

        txtResult = findViewById(R.id.txtResult);
        btn1 = findViewById(R.id.btnFetchRates);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getExchangeRate();
            }
        });
    }

    public void getExchangeRate() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONObject rates = json.getJSONObject("rates");
                            Iterator<String> keys = rates.keys();
                            StringBuilder ratesBuilder = new StringBuilder();
                            while(keys.hasNext()) {
                                String key = keys.next();
                                Double value = rates.getDouble(key);
                                ratesBuilder.append(key).append(": ").append(value).append("\n");
                            }
                            txtResult.setText(ratesBuilder.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(secondApp.this, "Error.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(secondApp.this, "Error, " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                headers.put("X-RapidAPI-Host", "exchangerate-api.p.rapidapi.com");
                return headers;
            }
        };

        queue.add(stringRequest);
    }
}
