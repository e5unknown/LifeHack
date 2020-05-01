package com.dev420.lifehack.api;

import com.dev420.lifehack.data.Company;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET ("test.php")
    Observable<List<Company>> getCompanies();
}
