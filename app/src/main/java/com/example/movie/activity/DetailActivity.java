package com.example.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.example.movie.model.response.Movie;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

public class DetailActivity extends AppCompatActivity {

    TextView tvName, tvDescription, showtimes, like_share;
    ImageView ivCover, like, share, MyList;
    Button btnPlay;

    RatingBar ratingBar;

    float myRating = 0;

    MaterialFavoriteButton favoriteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra("MOVIE");

//
//        like = findViewById(R.id.like);
//        like_share = findViewById(R.id.like_share);
//        share = findViewById(R.id.share);
        ratingBar = findViewById(R.id.RatingBar);
        favoriteButton = findViewById(R.id.favorite);


        favoriteButton.setOnFavoriteAnimationEndListener(new MaterialFavoriteButton.OnFavoriteAnimationEndListener() {
            @Override
            public void onAnimationEnd(MaterialFavoriteButton buttonView, boolean favorite) {

            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int rating  = (int) v;
                String message = null;
                myRating = ratingBar.getRating();
                switch (rating){
                    case 1:
                        message = "Sorry to hear that! :(";
                        break;
                    case 2:
                        message = "We always accept suggestions";
                        break;
                    case 3:
                        message = "Good enough! ";
                        break;
                    case 4:
                        message = "Good! Thank you!";
                        break;
                    case 5:
                        message = "Awesome! you are the best!";
                        break;
                }
            }
        });

        tvDescription = findViewById(R.id.tvDes);
        tvName = findViewById(R.id.tvName);
        ivCover = findViewById(R.id.ivCover);
        showtimes = findViewById(R.id.showtimes);
        tvName.setText(movie.getName());
        tvDescription.setText(movie.getContent());
        showtimes.setText(movie.getLang());
        like_share.setText(movie.getLike_share());
//        ratingBar.setRating(Float.parseFloat(movie.getRating()));
//        App.favourites.stream().filter(movie1 -> movie1.getId() == .);
//        App.setListFavourites();

        Glide.with(this).load(movie.getThumbUrl()).into(ivCover);

        btnPlay = (Button) findViewById(R.id.btnPlay);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie1 = movie;
                Intent intent1 = new Intent(DetailActivity.this, VideoActivity.class);
                intent1.putExtra("videoData", movie1);
                startActivity(intent1);
            }
        });
//        share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1 = new Intent(Intent.ACTION_SEND);
//                intent1.setType("text/plain");
//                String shareBody = "Your body here";
//                String shareSub = "Your Subject here";
//                intent1.putExtra(Intent.EXTRA_SUBJECT, shareSub);
//                intent1.putExtra(Intent.EXTRA_TEXT, shareBody);
//                startActivity(Intent.createChooser(intent1, "share using"));
//            }
//        });

        MyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}