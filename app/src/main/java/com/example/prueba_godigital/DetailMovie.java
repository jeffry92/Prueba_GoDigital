package com.example.prueba_godigital;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailMovie extends AppCompatActivity {

    TextView textTitle;
    ImageView imgeDetail;
    TextView textDetail;
    TextView textSubTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailmovie);

        textTitle = findViewById(R.id.txtTitle);
        imgeDetail = findViewById(R.id.imgViewDetail);
        textDetail = findViewById(R.id.txtDetail);
        textSubTitle = findViewById(R.id.txtSubTitle);

        Bundle datos = this.getIntent().getExtras();
        textTitle.setText(datos.getString("name"));
        textSubTitle.setText(datos.getString("subtitle"));
        String buil = "https://image.tmdb.org/t/p/w500" + datos.getString("image");
        try {
            Picasso.get().load(buil).fit().into(imgeDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        textDetail.setText(datos.getString("descrip"));

    }
}
