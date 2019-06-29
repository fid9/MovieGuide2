package com.example.movieguide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.example.movieguide.Adapters.PersonProfileImagesAdapter;
import com.example.movieguide.Interface.RetrofitService;
import com.example.movieguide.Klienti.KlientiRetrofit;
import com.example.movieguide.Model.PersonDetails;
import com.example.movieguide.Model.PersonImageProfiles;
import com.example.movieguide.Model.PersonImages;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Integer.parseInt;

public class PersonDetailActivity extends AppCompatActivity {

    private KenBurnsView personDetailsProfileImageView;

    private LinearLayoutCompat personDetailsAlsoKnownAsLayout;
    private LinearLayoutCompat personDetailsBirthdayLayout;
    private LinearLayoutCompat personDetailsPlaceOfBirthLayout;
    private LinearLayoutCompat personDetailsDeathDayLayout;
    private LinearLayoutCompat personDetailsDepartmentLayout;
    private LinearLayoutCompat personDetailsBiographyLayout;
    private LinearLayoutCompat personDetailsHomepageLayout;
    private LinearLayoutCompat personDetailProfileImagesLayout;


    private AppCompatTextView personDetailsName;
    private AppCompatTextView personDetailsAlsoKnownAs;
    private AppCompatTextView personDetailsBirthday;
    private AppCompatTextView personDetailsPlaceOfBirth;
    private AppCompatTextView personDetailsDeathDay;
    private AppCompatTextView personDetailsDepartment;
    private AppCompatTextView personDetailsBiography;
    private AppCompatTextView personDetailsHomepage;

    private RecyclerView personDetailProfileImagesRecyclerView;

    private PersonProfileImagesAdapter personProfileImagesAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        Intent intent = getIntent();


        RetrofitService retrofitService = KlientiRetrofit.getClient().create(RetrofitService.class);

        personDetailsProfileImageView = findViewById(R.id.person_details_profile_image_view);

        personDetailsAlsoKnownAsLayout = findViewById(R.id.person_details_also_known_as_layout);
        personDetailsBirthdayLayout = findViewById(R.id.person_details_birthday_layout);
        personDetailsPlaceOfBirthLayout = findViewById(R.id.person_details_place_of_birth_layout);
        personDetailsDeathDayLayout = findViewById(R.id.person_details_deathday_layout);
        personDetailsDepartmentLayout = findViewById(R.id.person_details_known_for_department_layout);
        personDetailsBiographyLayout = findViewById(R.id.person_details_biography_layout);
        personDetailsHomepageLayout = findViewById(R.id.person_details_homepage_layout);
        personDetailProfileImagesLayout = findViewById(R.id.person_details_profile_images_layout);


        personDetailsName = findViewById(R.id.person_details_name);
        personDetailsAlsoKnownAs = findViewById(R.id.person_details_also_known_as);
        personDetailsBirthday = findViewById(R.id.person_details_birthday);
        personDetailsPlaceOfBirth = findViewById(R.id.person_details_place_of_birth);
        personDetailsDeathDay = findViewById(R.id.person_details_deathday);
        personDetailsDepartment = findViewById(R.id.person_details_known_for_department);
        personDetailsBiography = findViewById(R.id.person_details_biography);
        personDetailsHomepage = findViewById(R.id.person_details_homepage);

        personDetailProfileImagesRecyclerView = findViewById(R.id.person_details_profile_images_recycler_view);
        personDetailProfileImagesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        if (intent!=null && intent.getExtras()!=null)
        {

            if (intent.getExtras().getString("id")!=null)
            {
                int id = Integer.parseInt(Objects.requireNonNull(intent.getExtras().getString("id")));

                Call<PersonDetails> personDetailsCall = retrofitService.getPersonDetailsById(id, BuildConfig.MOVIE_DB_API_KEY);
                    personDetailsCall.enqueue(new Callback<PersonDetails>() {
                        @Override
                        public void onResponse(@NonNull Call<PersonDetails> call,@NonNull Response<PersonDetails> response)
                        {
                            PersonDetails personDetailsResponse = response.body();

                            if(personDetailsResponse!=null)
                            {
                                preparePersonDetails(personDetailsResponse);
                            }

                            else {
                                Toast.makeText(PersonDetailActivity.this, "Any details not found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<PersonDetails> call,@NonNull Throwable t) {
                            Toast.makeText(PersonDetailActivity.this, "Any details not found", Toast.LENGTH_SHORT).show();

                        }
                    });

                    Call<PersonImages> personImagesCall = retrofitService.getPersonImagesById(id, BuildConfig.MOVIE_DB_API_KEY);
                    personImagesCall.enqueue(new Callback<PersonImages>() {
                        @Override
                        public void onResponse(@NonNull Call<PersonImages> call,@NonNull Response<PersonImages> response) {

                            PersonImages personImages = response.body();

                            if(personImages!=null)
                            {
                                List<PersonImageProfiles> personImageProfilesList = personImages.getProfiles();

                                if (personImageProfilesList!=null && personImageProfilesList.size() > 0)
                                {
                                    personDetailProfileImagesLayout.setVisibility(View.VISIBLE);
                                    personProfileImagesAdapter = new PersonProfileImagesAdapter(PersonDetailActivity.this, personImageProfilesList);
                                    personDetailProfileImagesRecyclerView.setAdapter(personProfileImagesAdapter);

                                    LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(PersonDetailActivity.this, R.anim.layout_slide_right);
                                    personDetailProfileImagesRecyclerView.setLayoutAnimation(controller);
                                    personDetailProfileImagesRecyclerView.scheduleLayoutAnimation();
                                }

                                else {
                                    personDetailProfileImagesLayout.setVisibility(View.GONE);

                                }
                            }

                        }

                        @Override
                        public void onFailure(@NonNull Call<PersonImages> call,@NonNull Throwable t) {

                        }
                    });

            }


        }
    }

    private void preparePersonDetails(PersonDetails personDetailsResponse)
    {
        String profilePath = personDetailsResponse.getProfile_path();
        String name = personDetailsResponse.getName();
        String birthday = personDetailsResponse.getBirthday();
        String placeOfBirth = personDetailsResponse.getPlace_of_birth();
        String deathDay = personDetailsResponse.getDeathday();
        String department = personDetailsResponse.getKnown_for_department();
        String biography = personDetailsResponse.getBiography();
        String homepage = personDetailsResponse.getHomepage();

        List<String> alsoKnownAsList = personDetailsResponse.getAlso_known_as();

        Picasso.with(this).load(profilePath).into(personDetailsProfileImageView);

        if (name!=null)
        {
            if(name.length()>0)
            {
                personDetailsName.setText(name);
                personDetailsName.setVisibility(View.VISIBLE);
            }

            else
            {
                personDetailsName.setVisibility(View.GONE);
            }
        }

        else
        {
            personDetailsName.setVisibility(View.GONE);
        }

        if (birthday!=null)
        {
            if(birthday.length()>0)
            {
                personDetailsBirthday.setText(birthday);
                personDetailsBirthdayLayout.setVisibility(View.VISIBLE);
            }

            else
            {
                personDetailsBirthdayLayout.setVisibility(View.GONE);
            }
        }

        else
        {
            personDetailsBirthdayLayout.setVisibility(View.GONE);
        }

        if (placeOfBirth!=null)
        {
            if(placeOfBirth.length()>0)
            {
                personDetailsPlaceOfBirth.setText(placeOfBirth);
                personDetailsPlaceOfBirthLayout.setVisibility(View.VISIBLE);
            }

            else
            {
                personDetailsPlaceOfBirthLayout.setVisibility(View.GONE);
            }
        }

        else
        {
            personDetailsPlaceOfBirthLayout.setVisibility(View.GONE);
        }

        if (deathDay!=null)
        {
            if(deathDay.length()>0)
            {
                personDetailsDeathDay.setText(deathDay);
                personDetailsDeathDayLayout.setVisibility(View.VISIBLE);
            }

            else
            {
                personDetailsDeathDayLayout.setVisibility(View.GONE);
            }
        }

        else
        {
            personDetailsDeathDayLayout.setVisibility(View.GONE);
        }

        if (biography!=null)
        {
            if(biography.length()>0)
            {
                personDetailsBiography.setText(biography);
                personDetailsBiographyLayout.setVisibility(View.VISIBLE);
            }

            else
            {
                personDetailsBiographyLayout.setVisibility(View.GONE);
            }
        }

        else
        {
            personDetailsBiographyLayout.setVisibility(View.GONE);
        }

        if (department!=null)
        {
            if(department.length()>0)
            {
                personDetailsDepartment.setText(department);
                personDetailsDepartmentLayout.setVisibility(View.VISIBLE);
            }

            else
            {
                personDetailsDepartmentLayout.setVisibility(View.GONE);
            }
        }

        else
        {
            personDetailsDepartmentLayout.setVisibility(View.GONE);
        }

        if (homepage!=null)
        {
            if(homepage.length()>0)
            {
                personDetailsHomepage.setText(homepage);
                personDetailsHomepageLayout.setVisibility(View.VISIBLE);
            }

            else
            {
                personDetailsHomepageLayout.setVisibility(View.GONE);
            }
        }

        else
        {
            personDetailsHomepageLayout.setVisibility(View.GONE);
        }


        if(alsoKnownAsList!=null)
        {
            if (alsoKnownAsList.size()>0)
            {
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0 ; i<alsoKnownAsList.size(); i++)
                {
                    if (i == alsoKnownAsList.size() - 1)
                    {
                        stringBuilder.append(alsoKnownAsList.get(i));
                    }
                    else
                    {
                        stringBuilder.append(alsoKnownAsList.get(i)).append(", ");
                    }
                }

                personDetailsAlsoKnownAs.setText(stringBuilder.toString());
                personDetailsAlsoKnownAsLayout.setVisibility(View.VISIBLE);

            }

            else
            {
                personDetailsAlsoKnownAsLayout.setVisibility(View.GONE);
            }
        }





    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
