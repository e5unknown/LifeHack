package com.dev420.lifehack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev420.lifehack.data.Company;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class CompanyCard extends AppCompatActivity {

    private ImageView ivCompanyCardImage;
    private TextView tvCompanyCardName;
    private final String BASE_URL = "http://megakohz.bget.ru/test_task/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_card);
        tvCompanyCardName = findViewById(R.id.tvCompanyCardName);
        ivCompanyCardImage = findViewById(R.id.ivCompanyCardImage);
        String stringCompany = getIntent().getStringExtra("companyString");
        Company company = new Gson().fromJson(stringCompany, Company.class) ;
        if (company != null) {
            tvCompanyCardName.setText(company.getName());
            String sURL = BASE_URL + company.getImg();
            Picasso.get().load(sURL).placeholder(R.drawable.company_card).into(ivCompanyCardImage);
        }
    }
}
