package com.example.movieguide;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.movieguide.Adapters.MovieSearchAdapter;
import com.example.movieguide.Adapters.PersonSearchAdapter;
import com.example.movieguide.Interface.RetrofitService;
import com.example.movieguide.Klienti.KlientiRetrofit;
import com.example.movieguide.Model.MovieResponse;
import com.example.movieguide.Model.MovieResponseResults;
import com.example.movieguide.Model.PersonResponse;
import com.example.movieguide.Model.PersonResponseResults;
import com.google.gson.Gson;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private NiceSpinner sourceSpinner;
    private AppCompatEditText queryEditText;
    private AppCompatButton querySearchButton;
    private RecyclerView resultRecyclerView;

    private String movie = "Kërko filma";
    private String person = "Kërko aktorë";

    private RetrofitService retrofitService;

    private MovieSearchAdapter movieSearchAdapter;
    private PersonSearchAdapter personSearchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //disable the keyword on start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        sourceSpinner = findViewById(R.id.source_spinner);
        queryEditText = findViewById(R.id.query_edit_text);
        querySearchButton = findViewById(R.id.query_search_button);
        resultRecyclerView = findViewById(R.id.result_recyclerview);
        resultRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        Paper.init(this);

        retrofitService = KlientiRetrofit.getClient().create(RetrofitService.class);

        final ArrayList<String> category = new ArrayList<>();

        //set list for sourceSpinner

        category.add(movie);
        category.add(person);

        sourceSpinner.attachDataSource(category);

        if (Paper.book().read("position")!=null)
        {
            int position = Paper.book().read("position");
            sourceSpinner.setSelectedIndex(position);
        }


        int position = sourceSpinner.getSelectedIndex();

        if (position == 0) {
            queryEditText.setHint("Sheno nje titull filmi...");
        } else {

            queryEditText.setHint("Sheno nje emer aktori...");
        }
        sourceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //kur klikohet sourceSpinner ndryshohet teksti te edit text

                if (position == 0) {
                    queryEditText.setHint("Sheno nje titull filmi...");
                } else {

                    queryEditText.setHint("Sheno nje emer aktori...");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (Paper.book().read("cache")!=null)
        {
            String results = Paper.book().read("cache");
            if(Paper.book().read("source")!=null)
            {
                String source = Paper.book().read("source");

                if(source.equals("movie"))
                {
                        MovieResponse movieResponse = new Gson().fromJson(results, MovieResponse.class);

                    if (movieResponse != null)
                    {
                        List<MovieResponseResults> movieResponseResults = movieResponse.getResults();
                        movieSearchAdapter = new MovieSearchAdapter(MainActivity.this, movieResponseResults);

                        resultRecyclerView.setAdapter(movieSearchAdapter);

                        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_slide_right);
                        resultRecyclerView.setLayoutAnimation(controller);
                        resultRecyclerView.scheduleLayoutAnimation();

                        //ruan te dhenet ne paper database per me iu qas offline
                        Paper.book().write("cache", new Gson().toJson(movieResponse));

                        //aktivizon spinnerin ne launch
                        Paper.book().write("source", "movie");


                    }

                }
                else
                {
                    PersonResponse personResponse = new Gson().fromJson(results, PersonResponse.class);

                    if (personResponse != null)
                    {
                        List<PersonResponseResults> personResponseResults = personResponse.getResults();
                        personSearchAdapter = new PersonSearchAdapter(MainActivity.this, personResponseResults);

                        resultRecyclerView.setAdapter(personSearchAdapter);

                        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_slide_right);
                        resultRecyclerView.setLayoutAnimation(controller);
                        resultRecyclerView.scheduleLayoutAnimation();


                        Paper.book().write("cache", new Gson().toJson(personResponse));

                        Paper.book().write("source", "person");


                    }
                }
            }
        }

        //get the query from user
        querySearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (queryEditText.getText() != null) {
                    String query = queryEditText.getText().toString();

                    if (query.equals("") || query.equals(" ")) {
                        Toast.makeText(MainActivity.this, "Ju lutem shkruani tekstin...", Toast.LENGTH_SHORT).show();
                    } else {
                        queryEditText.setText("");

                        String finalQuery = query.replaceAll(" ", "+");

                        //zgjedh kategorite per te kerkuar queries (titulli ose aktori)

                        if (category.size() > 0) {
                            String categoryName = category.get(sourceSpinner.getSelectedIndex());

                            if (categoryName.equals(movie))
                            {
                                Call<MovieResponse> movieResponseCall = retrofitService.getMoviesByQuery(BuildConfig.MOVIE_DB_API_KEY, finalQuery);

                                movieResponseCall.enqueue(new Callback<MovieResponse>() {
                                    @Override
                                    public void onResponse(@NonNull Call<MovieResponse> call,@NonNull Response<MovieResponse> response) {
                                        MovieResponse movieResponse = response.body();

                                        if (movieResponse != null)
                                        {
                                            List<MovieResponseResults> movieResponseResults = movieResponse.getResults();
                                            movieSearchAdapter = new MovieSearchAdapter(MainActivity.this, movieResponseResults);

                                            resultRecyclerView.setAdapter(movieSearchAdapter);

                                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_slide_right);
                                            resultRecyclerView.setLayoutAnimation(controller);
                                            resultRecyclerView.scheduleLayoutAnimation();

                                            Paper.book().write("cache", new Gson().toJson(movieResponse));

                                            Paper.book().write("source", "movie");


                                        }


                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<MovieResponse> call,@NonNull Throwable t) {

                                    }
                                });

                            }
                            else
                                {

                                    Call<PersonResponse> personResponseCall = retrofitService.getPersonsByQuery(BuildConfig.MOVIE_DB_API_KEY, finalQuery);
                                    personResponseCall.enqueue(new Callback<PersonResponse>() {
                                        @Override
                                        public void onResponse(@NonNull Call<PersonResponse> call,@NonNull Response<PersonResponse> response)
                                        {
                                            PersonResponse personResponse = response.body();

                                            if (personResponse != null)
                                            {
                                                List<PersonResponseResults> personResponseResults = personResponse.getResults();
                                                personSearchAdapter = new PersonSearchAdapter(MainActivity.this, personResponseResults);

                                                resultRecyclerView.setAdapter(personSearchAdapter);

                                                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_slide_right);
                                                resultRecyclerView.setLayoutAnimation(controller);
                                                resultRecyclerView.scheduleLayoutAnimation();

                                                Paper.book().write("cache", new Gson().toJson(personResponse));

                                                Paper.book().write("source", "person");

                                            }

                                        }

                                        @Override
                                        public void onFailure(@NonNull Call<PersonResponse> call,@NonNull Throwable t) {

                                        }
                                    });

                                }


                        }
                    }

                    {

                    }


                }

            }


        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        Paper.book().write("position", sourceSpinner.getSelectedIndex());
    }
}