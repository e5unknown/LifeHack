package com.dev420.lifehack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.dev420.lifehack.adapter.CompanyAdapter;
import com.dev420.lifehack.api.ApiFactory;
import com.dev420.lifehack.api.ApiService;
import com.dev420.lifehack.data.Company;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rwCompanies;
    private CompanyAdapter adapter;
    private final String STRINGURL = "http://megakohz.bget.ru/test_task/test.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rwCompanies = findViewById(R.id.rwCompanies);
        rwCompanies.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CompanyAdapter();
        rwCompanies.setAdapter(adapter);
        adapter.setCompanies(new ArrayList<Company>());
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        apiService.getCompanies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Company>>() {
                    @Override
                    public void accept(final List<Company> companies) throws Exception {
                        adapter.setCompanies(companies);
                        adapter.setOnCompanyClickListener(new CompanyAdapter.OnCompanyClickListener() {
                            @Override
                            public void onCompanyClick(int position) {
                                Intent intent = new Intent(MainActivity.this, CompanyCard.class);
                                intent.putExtra("companyString", new Gson().toJson(companies.get(position)));
                                startActivity(intent);
                            }
                        });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
