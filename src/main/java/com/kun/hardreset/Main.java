package com.kun.hardreset;

import com.kun.hardreset.data.RestApi;
import com.kun.hardreset.model.*;
import com.kun.hardreset.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
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
    @Autowired
    private TransferService transferService;


    public static void main(String[] args) {

        GenericXmlApplicationContext factory = new GenericXmlApplicationContext();
        factory.load("classpath*/bean.xml");
        factory.refresh();

        //RiskyOperationsService serv = new RiskyOperationsService();
        //RiskyOperationsService serv = factory.getBean(RiskyOperationsService.class);
        //serv.clusterUsers();
        //serv.getTransfers("2223");


        SpringApplication.run(Main.class, args);

    }

    public Main() {
        this.restApi = new RestApi();

        //List<Transfer> transfers = restApi.getTransfersByAccount("2237");
        //ArrayList<Transfer> list = new ArrayList<>();

        /*
        if (transfers == null) {
            System.out.println("transfers are null..");
        } else {
            list.addAll(transfers);
            System.out.println("transfers saved");
        }*/

    }

}
