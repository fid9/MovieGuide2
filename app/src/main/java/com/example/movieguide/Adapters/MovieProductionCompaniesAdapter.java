package com.example.movieguide.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieguide.Model.MovieCreditsCast;
import com.example.movieguide.Model.MovieDetailsProductionCompanies;
import com.example.movieguide.PersonDetailActivity;
import com.example.movieguide.R;
import com.example.movieguide.ViewHolders.MovieCreditsViewHolder;
import com.example.movieguide.ViewHolders.MovieProductionCompaniesViewHolder;

import java.util.List;



public class MovieProductionCompaniesAdapter extends RecyclerView.Adapter<MovieProductionCompaniesViewHolder> {

    private Activity activity;
    private List<MovieDetailsProductionCompanies> movieDetailsProductionCompaniesList;



    public MovieProductionCompaniesAdapter(Activity activity, List<MovieDetailsProductionCompanies> movieDetailsProductionCompaniesList) {
        this.activity = activity;
        this.movieDetailsProductionCompaniesList = movieDetailsProductionCompaniesList;
    }

    @NonNull
    @Override
    public MovieProductionCompaniesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.production_company_layout, viewGroup, false);

        return new MovieProductionCompaniesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieProductionCompaniesViewHolder productionCompaniesViewHolder, int i) {
        final MovieDetailsProductionCompanies productionCompanies = movieDetailsProductionCompaniesList.get(i);

        productionCompaniesViewHolder.setProductionCompanyImageView(activity, productionCompanies.getLogo_path());

        productionCompaniesViewHolder.productionCompanyName.setText(productionCompanies.getName());
    }

    @Override
    public int getItemCount() {
        return movieDetailsProductionCompaniesList.size();
    }
}
