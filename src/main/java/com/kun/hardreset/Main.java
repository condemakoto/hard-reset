package com.kun.hardreset;

import com.kun.hardreset.data.RestApi;
import com.kun.hardreset.model.Account;
import com.kun.hardreset.model.Branch;
import com.kun.hardreset.model.Customer;
import com.kun.hardreset.model.Merchant;
import com.kun.hardreset.service.AccountsService;
import com.kun.hardreset.service.BranchService;
import com.kun.hardreset.service.CustomerService;
import com.kun.hardreset.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

@SpringBootApplication
public class Main {

    private RestApi restApi;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private AccountsService accountsService;
    @Autowired
    private BranchService branchService;



    public static void main(String[] args) {

        GenericXmlApplicationContext factory = new GenericXmlApplicationContext();
        factory.load("/bean.xml");
        factory.refresh();


        Main app = factory.getBean(Main.class);
        app.harvestData();

    }

    public Main() {
        this.restApi = new RestApi();
    }

    private void harvestData() {
        harvestCustomers();
        harvestMerchants();
        harvestAccounts();
        harvestBranches();
    }

    private void harvestMerchants() {
        merchantService.deleteAll();
        List<Merchant> merchants = restApi.getMerchants();
        merchantService.create(merchants);

        merchants = merchantService.findAll();
        System.out.println("merchants : " + String.valueOf(merchants.size()));
    }

    private void harvestCustomers() {
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

    private void harvestAccounts() {
        accountsService.deleteAll();
        List<Account> accounts = restApi.getAccounts();
        accountsService.create(accounts);
        System.out.println("Accounts retrieved.");
    }

    private void harvestBranches() {
        branchService.deleteAll();
        List<Branch> branches = restApi.getBranches();
        branchService.create(branches);
        System.out.println("Branches retrieved.");
    }


}
