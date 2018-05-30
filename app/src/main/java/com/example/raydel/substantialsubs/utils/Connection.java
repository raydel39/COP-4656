package com.example.raydel.substantialsubs.utils;


import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.raydel.substantialsubs.utils.Utils.checkDistance;
import static com.example.raydel.substantialsubs.utils.Utils.currentOrder;

public class Connection {

    public static void get (final Activity activity, String url){

        final ProgressDialog loading = ProgressDialog.show(activity,
                "Validating Address", "Please wait...", false, false);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(activity);


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseResponse(response);
                        checkDistance(activity);
                        loading.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "Error validating address" , Toast.LENGTH_SHORT).show();
                System.out.println(error.getMessage());
                loading.dismiss();
                //TODO: Continue to next fragment
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private static void parseResponse(String response){

        try {
            JSONObject jsonObject = new JSONObject(response);

            JSONObject jsonRespRouteDistance = jsonObject
                    .getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray ("elements")
                    .getJSONObject(0)
                    .getJSONObject("distance");

            String distanceStr = jsonRespRouteDistance.get("text").toString();
            double distance = Double.parseDouble(distanceStr.replaceAll(",", "").split(" ")[0]);

            currentOrder.setDistanceToAddress(distance);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
