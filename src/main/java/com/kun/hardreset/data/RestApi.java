package com.kun.hardreset.data;

import com.kun.hardreset.model.*;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RestApi {

    private WebServiceInterface service;
    private final String BASE_URL = "https://api.bancogalicia.com.ar/";
    private final String API_KEY = "l7xxe6ae9e6a58f747f0b867e04bb9ff6947";

    public RestApi() {

        OkHttpClient client = getClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        service = retrofit.create(WebServiceInterface.class);
    }

    public List<Customer> getCustomers() {
        Call<List<Customer>> task = service.getCustomers(API_KEY);
        try {
            List<Customer> customers = task.execute().body();
            return customers;
        } catch(Exception ex) {
            System.out.println("Error retrieving customers.");
            return null;
        }
    }

    public List<Merchant> getMerchants() {
        Call<List<Merchant>> task = service.getMerchants(API_KEY);
        try {
            List<Merchant> merchants = task.execute().body();
            return merchants;
        } catch(Exception ex) {
            System.out.println("Error retrieving customers.");
            return null;
        }
    }

    public List<Account> getAccounts() {
        Call<List<Account>> task = service.getAccounts(API_KEY);
        try {
            List<Account> accounts = task.execute().body();
            return accounts;
        } catch(Exception ex) {
            System.out.println("Error retrieving accounts");
        }
        return null;
    }

    public List<Purchase> getPurchasesByAccount(String accountId) {
        Call<List<Purchase>> task = service.getPurchasesByAccount(accountId, API_KEY);
        try {
            List<Purchase> purchases = task.execute().body();
            return purchases;
        } catch(Exception ex) {
            System.out.println("Error retrieving purchases");
        }
        return null;
    }

    public List<Loan> getLoanByAccount(String accountId) {
        Call<List<Loan>> task = service.getLoanByAccount(accountId, API_KEY);
        try {
            List<Loan> loans = task.execute().body();
            return loans;
        } catch(Exception ex) {
            //System.out.println("Error retrieving transfers");
        }
        return null;
    }
    
    public List<Transfer> getTransfersByAccount(String accountId) {
        Call<List<Transfer>> task = service.getTransfersByAccount(accountId, API_KEY);
        try {
            List<Transfer> transfers = task.execute().body();
            return transfers;
        } catch(Exception ex) {
            System.out.println("Error retrieving transfers");
        }
        return null;
    }
    
    public List<Account> getAccountByCustomer(String customerId) {
        Call<List<Account>> task = service.getAccountByCustomer(customerId, API_KEY);
        try {
            List<Account> accounts = task.execute().body();
            return accounts;
        } catch(Exception ex) {
            //System.out.println("Error retrieving transfers");
        }
        return null;
    }

    public List<Branch> getBranches() {
        Call<List<Branch>> task = service.getBranches(API_KEY);
        try {
            List<Branch> branches = task.execute().body();
            return branches;
        } catch(Exception ex) {
            System.out.println("Error retrieving branches");
        }
        return null;
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }
}
