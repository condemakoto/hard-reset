package com.kun.hardreset.service;

import com.kun.hardreset.data.RestApi;
import com.kun.hardreset.model.*;
import dk.ange.octave.OctaveEngine;
import dk.ange.octave.OctaveEngineFactory;
import dk.ange.octave.type.Octave;
import dk.ange.octave.type.OctaveDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RiskyOperationsService {

    private RestApi restApi;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

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
        String accountId = "2200";
        new RiskyOperationsService().getTransfers(accountId);
    }


    public void getTransfers(String accountId) {
        restApi = new RestApi();
        List<Transfer> transfers = restApi.getTransfersByAccount(accountId);

        OctaveEngine octave = new OctaveEngineFactory().getScriptEngine();
        octave.put("t1", Octave.scalar(5));
        octave.put("t2", Octave.scalar(10));
        octave.eval("result = t1 + t2");
        OctaveDouble result = octave.get(OctaveDouble.class, "result");
        octave.close();
        final double integral = result.get(1);
        System.out.println(integral);

        int length = transfers.size();
        double[] dates = new double[length];
        double[] prices = new double[length];


        for (int i = 0; i < length; i++) {
            Transfer transfer = transfers.get(i);
            try {
                dates[i] = simpleDateFormat.parse(transfer.getTransaction_Date()).getTime();
            } catch (Exception ex) {
                System.out.println("Error parsing the date...");
            }
            prices[i] = transfer.getAmount();
        }


        double tetha1 = 0, tetha2 = 0;
        int repeat = 100;
        double alfa = .3;

        for (int i = 0; i < repeat; i++) {
            double temp1 = tetha1;
            double temp2 = tetha2;

            double lastTerm = 0;
            double lastTerm2 = 0;
            for (int j = 0; j < length; j++) {
                lastTerm += tetha1 + dates[j] * tetha2 - prices[j];
                lastTerm2 += (tetha1 + dates[j] * tetha2 - prices[j]) * dates[j];
            }

            tetha1 = temp1 - alfa / length * lastTerm;
            tetha2 = temp2 - alfa / length * lastTerm2;
        }

        System.out.println(tetha1);
        System.out.println(tetha2);
    }

    @RequestMapping("/harvest")
    public void harvest() {
        this.restApi = new RestApi();
        harvestData();
    }

    @RequestMapping()
    public List<CustomerToCompare> clusterUsers() {
        List<CustomerToCompare> data = getCustomersToCompare();

        return data;
    }

    @RequestMapping("/test")
    public List<CustomerToCompare> clusterUsersAndExport() {

        List<CustomerToCompare> data = getCustomersToCompare();

        exportCustomersDataToCSV(data);

        return data;
    }

    private List<CustomerToCompare> getCustomersToCompare() {
        List<Customer> customers = customerService.getCustomers();

        ArrayList<CustomerToCompare> data = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerToCompare c = new CustomerToCompare();
            c.setCostumer(customer);
            List<Account> accounts = accountsService.findCustomerAccounts(customer.getId());
            c.setAccounts(accounts);

            for (Account account : accounts) {
                List<Transfer> transfers = restApi.getTransfersByAccount(account.getId());
                c.setTransfers(transfers);
            }

            data.add(c);
        }

        return data;
    }

    private void exportCustomersDataToCSV(List<CustomerToCompare> customers) {
        try {
            FileWriter file = new FileWriter("customers.csv");
            BufferedWriter writer = new BufferedWriter(file);
            final String separator = ",";
            for (int i = 0; i < customers.size(); i++) {
                CustomerToCompare c = customers.get(i);

                if (i > 0) {
                    writer.newLine();
                }
                StringBuffer sb = new StringBuffer();

                sb.append(c.getAccountId());
                sb.append(separator).append(c.getLat());
                sb.append(separator).append(c.getLng());
                sb.append(separator).append(c.getAccountCount());
                sb.append(separator).append(c.getTotalBalance());
                sb.append(separator).append(c.getTotalRewards());
                sb.append(separator).append(c.getTransferCount());
                sb.append(separator).append(c.getTotalTransfersAmount());

                writer.write(sb.toString());
            }
            writer.close();
            file.close();
        } catch (Exception ex) {
            System.out.println("Can't write to file...");
        }
    }

    //region harvest
    private void harvestData() {
        harvestCustomers();
        harvestMerchants();
        harvestAccounts();
        harvestBranches();
        //harvestTransfers();
    }

    private void harvestMerchants() {
        merchantService.deleteAll();
        List<Merchant> merchants = restApi.getMerchants();
        merchantService.create(merchants);

        merchants = merchantService.findAll();
        System.out.println("merchants : " + String.valueOf(merchants.size()));
    }

    private void harvestCustomers() {

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

    private void harvestTransfers() {
        transferService.deleteAll();

        ArrayList<Transfer> transfers = new ArrayList<>();
        List<Account> accounts = accountsService.findAll();
        for (Account account : accounts) {
            if (account.getId() == null) {
                System.out.println("account id is null");
            }
            List<Transfer> transfersForAccount = restApi.getTransfersByAccount(account.getId());
            if (transfersForAccount == null) {
                System.out.println("Couldn't retrieve data for account: " + account.getId());
            } else {
                transfers.addAll(transfersForAccount);
            }

            try {
                Thread.sleep(200);
            } catch (Exception ex) {
                System.out.println("Requesting more transfers for the next account...");
            }
        }
        transferService.create(transfers);


        List<Transfer> myTransfers = transferService.findAll();
        System.out.println("Transfers retrieved: " + String.valueOf(myTransfers.size()));
    }
    //endregion


}
