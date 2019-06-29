package com.example.movieguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.example.movieguide.Adapters.MovieCreditsCastAdapter;
import com.example.movieguide.Adapters.MovieCreditsCrewAdapter;
import com.example.movieguide.Adapters.MoviePosterImagesAdapter;
import com.example.movieguide.Adapters.MovieProductionCompaniesAdapter;
import com.example.movieguide.Adapters.MovieVideosAdapter;
import com.example.movieguide.Interface.RetrofitService;
import com.example.movieguide.Klienti.KlientiRetrofit;
import com.example.movieguide.Model.MovieCredits;
import com.example.movieguide.Model.MovieCreditsCast;
import com.example.movieguide.Model.MovieCreditsCrew;
import com.example.movieguide.Model.MovieDetails;
import com.example.movieguide.Model.MovieDetailsGenres;
import com.example.movieguide.Model.MovieDetailsProductionCompanies;
import com.example.movieguide.Model.MovieDetailsProductionCountries;
import com.example.movieguide.Model.MovieImages;
import com.example.movieguide.Model.MovieImagesBackdropsAndPosters;
import com.example.movieguide.Model.MovieVideos;
import com.example.movieguide.Model.MovieVideosResults;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private KenBurnsView movieDetailPosterImageView;
    private CircleImageView movieDetailBackDropPosterCircleImageView;
    private ArcProgress movieRatingBar;


    private LinearLayoutCompat movieDetailOriginalTitleLayout;
    private LinearLayoutCompat movieDetailOriginalLanguageLayout;
    private LinearLayoutCompat movieDetailAdultLayout;
    private LinearLayoutCompat movieDetailStatusLayout;
    private LinearLayoutCompat movieDetailRuntimeLayout;
    private LinearLayoutCompat movieDetailBudgetLayout;
    private LinearLayoutCompat movieDetailRevenueLayout;
    private LinearLayoutCompat movieDetailGenreLayout;
    private LinearLayoutCompat movieDetailProductionCountryLayout;
    private LinearLayoutCompat movieDetailReleaseDateLayout;
    private LinearLayoutCompat movieDetailOverviewLayout;
    private LinearLayoutCompat movieDetailHomepageLayout;
    private LinearLayoutCompat movieDetailsCastLayout;
    private LinearLayoutCompat movieDetailsCrewLayout;
    private LinearLayoutCompat movieDetailsProductionCompanyLayout;
    private LinearLayoutCompat movieDetailsImagesLayout;
    private LinearLayoutCompat movieDetailsVideosLayout;


    private AppCompatTextView movieDetailTitle;
    private AppCompatTextView movieDetailOriginalTitle;
    private AppCompatTextView movieDetailOriginalLanguage;
    private AppCompatTextView movieDetailAdult;
    private AppCompatTextView movieDetailStatus;
    private AppCompatTextView movieDetailRuntime;
    private AppCompatTextView movieDetailBudget;
    private AppCompatTextView movieDetailRevenue;
    private AppCompatTextView movieDetailGenre;
    private AppCompatTextView movieDetailProductionCountry;
    private AppCompatTextView movieDetailReleaseDate;
    private AppCompatTextView movieDetailOverview;
    private AppCompatTextView movieDetailHomepage;

    private RecyclerView movieDetailsCastRecyclerView;
    private RecyclerView movieDetailsCrewRecyclerView;
    private RecyclerView movieDetailsProductionCompanyRecyclerView;
    private RecyclerView movieDetailsImagesRecyclerView;
    private RecyclerView movieDetailsVideosRecyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();

        RetrofitService retrofitService = KlientiRetrofit.getClient().create(RetrofitService.class);

        ThumbnailLoader.initialize(BuildConfig.GOOGLE_CLOUD_API_KEY);


        movieDetailPosterImageView = findViewById(R.id.movie_details_poster_image_view);
        movieDetailBackDropPosterCircleImageView = findViewById(R.id.movie_detail_poster_circle_image_view);
        movieRatingBar = findViewById(R.id.movie_detail_rating_bar);

        movieDetailOriginalTitleLayout = findViewById(R.id.movie_detail_original_title_layout);
        movieDetailOriginalLanguageLayout = findViewById(R.id.movie_detail_original_language_layout);
        movieDetailAdultLayout = findViewById(R.id.movie_detail_adult_layout);
        movieDetailStatusLayout = findViewById(R.id.movie_detail_status_layout);
        movieDetailRuntimeLayout = findViewById(R.id.movie_detail_runtime_layout);
        movieDetailBudgetLayout = findViewById(R.id.movie_detail_budget_layout);
        movieDetailRevenueLayout = findViewById(R.id.movie_detail_revenue_layout);
        movieDetailGenreLayout = findViewById(R.id.movie_detail_genre_layout);
        movieDetailProductionCountryLayout = findViewById(R.id.movie_detail_production_country_layout);
        movieDetailReleaseDateLayout = findViewById(R.id.movie_detail_release_date_layout);
        movieDetailHomepageLayout = findViewById(R.id.movie_detail_homepage_layout);
        movieDetailOverviewLayout = findViewById(R.id.movie_detail_overview_layout);

        movieDetailsCastLayout = findViewById(R.id.movie_details_cast_layout);
        movieDetailsCrewLayout = findViewById(R.id.movie_details_crew_layout);
        movieDetailsProductionCompanyLayout = findViewById(R.id.movie_details_production_company_layout);
        movieDetailsImagesLayout = findViewById(R.id.movie_details_images_layout);
        movieDetailsVideosLayout = findViewById(R.id.movie_details_videos_layout);

        movieDetailTitle = findViewById(R.id.movie_detail_title);
        movieDetailOriginalTitle = findViewById(R.id.movie_detail_original_title);
        movieDetailOriginalLanguage = findViewById(R.id.movie_detail_original_langauge);
        movieDetailAdult = findViewById(R.id.movie_detail_adult);
        movieDetailStatus = findViewById(R.id.movie_detail_status);
        movieDetailRuntime = findViewById(R.id.movie_detail_runtime);
        movieDetailBudget = findViewById(R.id.movie_detail_budget);
        movieDetailRevenue = findViewById(R.id.movie_detail_revenue);
        movieDetailGenre = findViewById(R.id.movie_detail_genre);
        movieDetailProductionCountry = findViewById(R.id.movie_detail_production_country);
        movieDetailReleaseDate = findViewById(R.id.movie_detail_release_date);
        movieDetailHomepage = findViewById(R.id.movie_detail_homepage);
        movieDetailOverview = findViewById(R.id.movie_detail_overview);


        movieDetailsCastRecyclerView = findViewById(R.id.movie_details_cast_recycler_view);
        movieDetailsCastRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieDetailsCrewRecyclerView = findViewById(R.id.movie_details_crew_recycler_view);
        movieDetailsCrewRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieDetailsProductionCompanyRecyclerView = findViewById(R.id.movie_details_production_company_recycler_view);
        movieDetailsProductionCompanyRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieDetailsImagesRecyclerView = findViewById(R.id.movie_details_images_recycler_view);
        movieDetailsImagesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieDetailsVideosRecyclerView = findViewById(R.id.movie_details_videos_recycler_view);
        movieDetailsVideosRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        if(intent != null && intent.getExtras() != null)
        {
            if (intent.getExtras().getString("id") != null) {
                int id = Integer.parseInt(Objects.requireNonNull(intent.getExtras().getString("id")));

                Call<MovieDetails> movieDetailsCall = retrofitService.getMovieDetailsById(id, BuildConfig.MOVIE_DB_API_KEY);

                movieDetailsCall.enqueue(new Callback<MovieDetails>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response) {

                        MovieDetails movieDetailsResponse = response.body();

                        if (movieDetailsResponse != null) {
                            prepareMovieDetails(movieDetailsResponse);

                        }

                        else {
                            Toast.makeText(MovieDetailActivity.this, "Any details not found", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieDetails> call, @NonNull Throwable t) {

                            Toast.makeText(MovieDetailActivity.this, "Any details not found", Toast.LENGTH_SHORT).show();


                    }
                });

                Call<MovieCredits> movieCreditsCall = retrofitService.getMovieCreditsById(id, BuildConfig.MOVIE_DB_API_KEY);

                movieCreditsCall.enqueue(new Callback<MovieCredits>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieCredits> call, @NonNull Response<MovieCredits> response) {

                        MovieCredits movieCreditsResponse = response.body();

                        if (movieCreditsResponse != null) {
                            List<MovieCreditsCast> movieCreditsCastList = movieCreditsResponse.getCast();
                            if (movieCreditsCastList != null && movieCreditsCastList.size() > 0) {
                                MovieCreditsCastAdapter movieCreditsCastAdapter = new MovieCreditsCastAdapter(MovieDetailActivity.this, movieCreditsCastList);
                                movieDetailsCastRecyclerView.setAdapter(movieCreditsCastAdapter);

                                movieDetailsCastLayout.setVisibility(View.VISIBLE);
                                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieDetailActivity.this, R.anim.layout_slide_right);
                                movieDetailsCastRecyclerView.setLayoutAnimation(controller);
                                movieDetailsCastRecyclerView.scheduleLayoutAnimation();
                            } else {
                                movieDetailsCastLayout.setVisibility(View.GONE);
                            }

                            List<MovieCreditsCrew> movieCreditsCrewList = movieCreditsResponse.getCrew();
                            if (movieCreditsCrewList != null && movieCreditsCrewList.size() > 0) {
                                MovieCreditsCrewAdapter movieCreditsCrewAdapter = new MovieCreditsCrewAdapter(MovieDetailActivity.this, movieCreditsCrewList);
                                movieDetailsCrewRecyclerView.setAdapter(movieCreditsCrewAdapter);

                                movieDetailsCrewLayout.setVisibility(View.VISIBLE);
                                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieDetailActivity.this, R.anim.layout_slide_right);
                                movieDetailsCrewRecyclerView.setLayoutAnimation(controller);
                                movieDetailsCrewRecyclerView.scheduleLayoutAnimation();
                            } else {
                                movieDetailsCrewLayout.setVisibility(View.GONE);
                            }

                        } else {
                            movieDetailsCastLayout.setVisibility(View.GONE);
                            movieDetailsCrewLayout.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieCredits> call, @NonNull Throwable t) {

                    }
                });

                Call<MovieImages> movieImagesCall = retrofitService.getMovieImagesById(id, BuildConfig.MOVIE_DB_API_KEY);
                movieImagesCall.enqueue(new Callback<MovieImages>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieImages> call, @NonNull Response<MovieImages> response) {

                        MovieImages movieImages = response.body();

                        if (movieImages != null) {
                            ArrayList<MovieImagesBackdropsAndPosters> movieImagesBackdropsAndPostersArrayList = new ArrayList<>();

                            List<MovieImagesBackdropsAndPosters> movieImagesBackdropsList = movieImages.getMovieImagesBackdropsAndPostersList();
                            List<MovieImagesBackdropsAndPosters> movieImagesPostersList = movieImages.getMovieImagesBackdropsAndPostersList();

                            if (movieImagesBackdropsList != null && movieImagesBackdropsList.size() > 0) {
                                if (movieImagesPostersList != null && movieImagesPostersList.size() > 0) {
                                    movieImagesBackdropsAndPostersArrayList.addAll(movieImagesBackdropsList);
                                    movieImagesBackdropsAndPostersArrayList.addAll(movieImagesPostersList);
                                } else {
                                    movieImagesBackdropsAndPostersArrayList.addAll(movieImagesBackdropsList);
                                }
                            } else if (movieImagesPostersList != null && movieImagesPostersList.size() > 0) {
                                movieImagesBackdropsAndPostersArrayList.addAll(movieImagesPostersList);
                            } else {
                                movieImagesBackdropsAndPostersArrayList.clear();
                                movieDetailsImagesLayout.setVisibility(View.GONE);
                            }

                            if (movieImagesBackdropsAndPostersArrayList.size() > 0) {
                                MoviePosterImagesAdapter moviePosterImagesAdapter = new MoviePosterImagesAdapter(MovieDetailActivity.this, movieImagesBackdropsAndPostersArrayList);
                                movieDetailsImagesRecyclerView.setAdapter(moviePosterImagesAdapter);

                                movieDetailsImagesLayout.setVisibility(View.VISIBLE);
                                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieDetailActivity.this, R.anim.layout_slide_right);
                                movieDetailsImagesRecyclerView.setLayoutAnimation(controller);
                                movieDetailsImagesRecyclerView.scheduleLayoutAnimation();
                            }

                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieImages> call, @NonNull Throwable t) {

                    }
                });

                Call<MovieVideos> movieVideosCall = retrofitService.getMovieVideosById(id, BuildConfig.MOVIE_DB_API_KEY);
                movieVideosCall.enqueue(new Callback<MovieVideos>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieVideos> call, @NonNull Response<MovieVideos> response) {

                        MovieVideos movieVideos = response.body();

                        if (movieVideos != null) {

                            List<MovieVideosResults> movieVideosResultsList = movieVideos.getResults();

                            if (movieVideosResultsList != null && movieVideosResultsList.size() > 0) {
                                movieDetailsVideosLayout.setVisibility(View.VISIBLE);

                                MovieVideosAdapter adapter = new MovieVideosAdapter(MovieDetailActivity.this, movieVideosResultsList);
                                movieDetailsVideosRecyclerView.setAdapter(adapter);

                                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieDetailActivity.this, R.anim.layout_slide_right);

                                movieDetailsVideosRecyclerView.setLayoutAnimation(controller);
                                movieDetailsVideosRecyclerView.scheduleLayoutAnimation();

                            }
                            else {
                                movieDetailsVideosLayout.setVisibility(View.GONE);
                            }


                        } else {
                            movieDetailsVideosLayout.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieVideos> call, @NonNull Throwable t) {

                    }
                });
            }
        }
    }

    private void prepareMovieDetails(MovieDetails movieDetailsResponse)
      {
        String posterPath = movieDetailsResponse.getPoster_path();
        String  backdropPath = movieDetailsResponse.getBackdrop_path();
        String title = movieDetailsResponse.getTitle();
        Double voteAverage = movieDetailsResponse.getVote_average() * 10;
        String originalTitle = movieDetailsResponse.getOriginal_title();
        String originalLanguage = movieDetailsResponse.getOriginal_language();
        boolean adult = movieDetailsResponse.isAdult();
        String status = movieDetailsResponse.getStatus();
        Integer runtime = movieDetailsResponse.getRuntime();
        Integer budget = movieDetailsResponse.getBudget();
        Integer revenue = movieDetailsResponse.getRevenue();

        List<MovieDetailsGenres> movieDetailsGenresList = movieDetailsResponse.getGenres();
        List<MovieDetailsProductionCountries> movieDetailsProductionCountriesList = movieDetailsResponse.getProduction_countries();
        List<MovieDetailsProductionCompanies> movieDetailsProductionCompaniesList = movieDetailsResponse.getProduction_companies();

        String releaseDate = movieDetailsResponse.getRelease_date();
        String homepage = movieDetailsResponse.getHomepage();
        String overview = movieDetailsResponse.getOverview();

        Picasso.with(this).load(posterPath).into(movieDetailPosterImageView);
        Picasso.with(this).load(backdropPath).into(movieDetailBackDropPosterCircleImageView);

        int rating = voteAverage.intValue();
        movieRatingBar.setProgress(rating);



        movieDetailTitle.setText(title);

        if(originalTitle!=null)
        {
            if (originalTitle.length() > 0 )
            {
                movieDetailOriginalTitle.setText(originalTitle);
                movieDetailOriginalTitleLayout.setVisibility(View.VISIBLE);
            }
            else
                {
                movieDetailOriginalTitleLayout.setVisibility(View.GONE);
            }
        }

        else {
            movieDetailOriginalTitleLayout.setVisibility(View.GONE);
        }

        if(originalLanguage!=null)
        {
            if (originalLanguage.length() > 0 )
            {
                movieDetailOriginalLanguage.setText(originalLanguage);
                movieDetailOriginalLanguageLayout.setVisibility(View.VISIBLE);
            }
            else {
                movieDetailOriginalLanguageLayout.setVisibility(View.GONE); }
        }

        else {
            movieDetailOriginalLanguageLayout.setVisibility(View.GONE);
        }



        if (adult)
        {
            movieDetailAdult.setText("Yes");
            movieDetailAdultLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            movieDetailAdult.setText("No");
            movieDetailAdultLayout.setVisibility(View.VISIBLE);
        }

        if(status!=null)
        {
            if (status.length() > 0 )
            {
                movieDetailStatus.setText(status);
                movieDetailStatusLayout.setVisibility(View.VISIBLE);
            }
            else {
                movieDetailStatusLayout.setVisibility(View.GONE); }
        }

        else {
            movieDetailStatusLayout.setVisibility(View.GONE);
        }

        if(runtime !=null) {


            if (runtime != 0) {
                movieDetailRuntime.setText(String.valueOf(runtime));
                movieDetailRuntimeLayout.setVisibility(View.VISIBLE);

            } else {
                movieDetailRuntimeLayout.setVisibility(View.GONE);
            }
        }

        else {
            movieDetailRuntimeLayout.setVisibility(View.GONE);
        }


        if (budget != null) {

            if (budget != 0) {
                movieDetailBudget.setText(String.valueOf(budget));
                movieDetailBudgetLayout.setVisibility(View.VISIBLE);
            } else {
                movieDetailBudgetLayout.setVisibility(View.GONE);
            }
        }

        else {
            movieDetailBudgetLayout.setVisibility(View.GONE);
        }

        if (revenue != null) {
            if (revenue != 0) {
                movieDetailRevenue.setText(String.valueOf(revenue));
                movieDetailRevenueLayout.setVisibility(View.VISIBLE);
            }
            else {
                movieDetailRevenueLayout.setVisibility(View.GONE);
            }
        }

        else {
            movieDetailRevenueLayout.setVisibility(View.GONE);
        }


          if (movieDetailsGenresList!=null && movieDetailsGenresList.size() > 0)
        {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i=0; i<movieDetailsGenresList.size(); i++)
            {
                if (i == movieDetailsGenresList.size() - 1)
                {
                    stringBuilder.append(movieDetailsGenresList.get(i).getName());
                }
                else
                {
                    stringBuilder.append(movieDetailsGenresList.get(i).getName()).append(", ");
                }
            }

            movieDetailGenre.setText(stringBuilder.toString());
            movieDetailGenreLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            movieDetailGenreLayout.setVisibility(View.GONE);
        }

        if (movieDetailsProductionCountriesList!=null && movieDetailsProductionCountriesList.size() > 0)
        {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i=0; i<movieDetailsProductionCountriesList.size(); i++)
            {
                if (i == movieDetailsProductionCountriesList.size() - 1)
                {
                    stringBuilder.append(movieDetailsProductionCountriesList.get(i).getName());
                }
                else
                {
                    stringBuilder.append(movieDetailsProductionCountriesList.get(i).getName()).append(", ");
                }
            }

            movieDetailProductionCountry.setText(stringBuilder.toString());
            movieDetailProductionCountryLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            movieDetailProductionCountryLayout .setVisibility(View.GONE);
        }

        if(releaseDate!=null)
        {
            if (releaseDate.length() > 0 )
            {
                movieDetailReleaseDate.setText(releaseDate);
                movieDetailReleaseDateLayout.setVisibility(View.VISIBLE);
            }
            else {
                movieDetailReleaseDateLayout.setVisibility(View.GONE); }
        }

        else {
            movieDetailReleaseDateLayout.setVisibility(View.GONE);
        }

        if(homepage!=null)
        {
            if (homepage.length() > 0 )
            {
                movieDetailHomepage.setText(homepage);
                movieDetailHomepageLayout.setVisibility(View.VISIBLE);
            }
            else {
                movieDetailHomepageLayout.setVisibility(View.GONE); }
        }

        else {
            movieDetailHomepageLayout.setVisibility(View.GONE);
        }

        if(overview!=null)
        {
            if (overview.length() > 0 )
            {
                movieDetailOverview.setText(overview);
                movieDetailOverviewLayout.setVisibility(View.VISIBLE);
            }
            else {
                movieDetailOverviewLayout.setVisibility(View.GONE); }
        }

        else {
            movieDetailOverviewLayout.setVisibility(View.GONE);
        }

        if (movieDetailsProductionCompaniesList != null && movieDetailsProductionCompaniesList.size() > 0)
        {
            MovieProductionCompaniesAdapter productionCompaniesAdapter = new MovieProductionCompaniesAdapter(MovieDetailActivity.this, movieDetailsProductionCompaniesList);
            movieDetailsProductionCompanyRecyclerView.setAdapter(productionCompaniesAdapter);

            movieDetailsProductionCompanyLayout.setVisibility(View.VISIBLE);
            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieDetailActivity.this, R.anim.layout_slide_right);
            movieDetailsProductionCompanyLayout.setLayoutAnimation(controller);
            movieDetailsProductionCompanyLayout.scheduleLayoutAnimation();
        }
        else
        {
            movieDetailsProductionCompanyLayout.setVisibility(View.GONE);
        }


    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
