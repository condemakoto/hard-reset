package com.kun.hardreset;

import com.kun.hardreset.data.RestApi;
import com.kun.hardreset.model.Customer;
import com.kun.hardreset.model.Merchant;
import com.kun.hardreset.service.CustomerService;
import com.kun.hardreset.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

@SpringBootApplication
public class Main {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private MerchantService merchantService;

    public static void main(String[] args) {

        GenericXmlApplicationContext factory = new GenericXmlApplicationContext();
        factory.load("/bean.xml");
        factory.refresh();


        Main app = factory.getBean(Main.class);
        app.onApplicationStart2();
        //app.appTwo();


    }

    private void onApplicationStart2() {
        RestApi restApi = new RestApi();
        List<Merchant> merchants = restApi.getMerchants();

        merchantService.create(merchants);

        merchants = merchantService.findAll();
        System.out.println("merchants : " + String.valueOf(merchants.size()));
    }

    private void onApplicationStart() {
        RestApi restApi = new RestApi();
        List<Customer> customers = restApi.getCustomers();

        customerService.clearEntity();
        customerService.save(customers);
        if (customers == null) {
            System.out.println("customer is null");
        } else {
            System.out.println("Customers retrieved: " + String.valueOf(customers.size()));
        }
    }

    private void appTwo() {
        List<Customer> customers = customerService.getCustomers();
        if (customers == null) {
            System.out.println("customer is null");
        } else {
            System.out.println("Customers retrieved: " + String.valueOf(customers.size()));
        }
    }


}
