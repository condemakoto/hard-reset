package com.kun.hardreset.data;

import com.kun.hardreset.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface WebServiceInterface {

    @GET("apiCustomer/Customer")
    Call<List<Customer>> getCustomers(@Query("apikey") String apiKey);

    @GET("apiMerchant/Merchant")
    Call<List<Merchant>> getMerchants(@Query("apikey") String apiKey);

    @GET("apiAccount/Account")
    Call<List<Account>> getAccounts(@Query("apikey") String apiKey);

    @GET("apiPurchase/Account/{accountId}/Purchase")
    Call<List<Purchase>> getPurchasesByAccount(@Path("accountId") String accountId, @Query("apikey") String apiKey);

    @GET("apiTransfer/Account/{accountId}/Transfer")
    Call<List<Transfer>> getTransfersByAccount(@Path("accountId") String accountId, @Query("apikey") String apiKey);

    @GET("apiBranch/Branch")
    Call<List<Branch>> getBranches(@Query("apikey") String apiKey);
    
    @GET("apiLoan/Account/{accountId}/Loan")
    Call<List<Loan>> getLoanByAccount(@Path("accountId") String accountId, @Query("apikey") String apiKey);
    
    @GET("apiCustomer/customer/{customerId}/Account")
    Call<List<Account>> getAccountByCustomer(@Path("customerId") String customerId, @Query("apikey") String apiKey);
    
    

}
