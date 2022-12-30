package com.example.triviaapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.triviaapp.controller.AppController;
import com.example.triviaapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    ArrayList<Question> questionArrayList = new ArrayList<>();
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    public List<Question> getQuestions() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    Log.d("TAG", "onCreate: " + response.toString());
                }, error -> {

        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return null;
    }

}
