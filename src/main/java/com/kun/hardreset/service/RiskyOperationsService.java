package com.kun.hardreset.service;

import com.kun.hardreset.api.responses.CustomerClusteringResult;
import com.kun.hardreset.data.RestApi;
import com.kun.hardreset.model.*;
import com.kun.hardreset.model.TransferFE.TypeRisk;

import dk.ange.octave.OctaveEngine;
import dk.ange.octave.OctaveEngineFactory;
import dk.ange.octave.type.Octave;
import dk.ange.octave.type.OctaveDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

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

    //temporal cache
    private List<TransferFE> myTransfersFE;
    private List<CustomerLoan> myLoansFE;


    @RequestMapping("/entities")
    public List<String> getEntitiesToCluster() {
        return Arrays.asList("Customers");
    }

    @RequestMapping("/features")
    public List<String> getFeaturesToCompare() {
        return Arrays.asList("Distance", "Account count", "Total balance", "Total Rewards", "Transfers count", "Total transfers amount");
    }

    @RequestMapping("/transfersFE")
    public List<TransferFE> getTransfersFE() {

        if (myTransfersFE != null && !myTransfersFE.isEmpty()) {
            return myTransfersFE;
        }

        restApi = new RestApi();
        List<Account> accounts = restApi.getAccounts();

        ArrayList<TransferFE> data = new ArrayList<>();

        for (Account account : accounts) {
            if (account.getId() == null) {
                System.out.println("account id is null");
            }
            List<Transfer> transfersForAccount = restApi.getTransfersByAccount(account.getId());
            if (transfersForAccount == null) {
                System.out.println("Couldn't retrieve data for account: " + account.getId());
            } else {
                for (Transfer transfer :transfersForAccount) {
                    TransferFE t = new TransferFE();
                    t.setAccountType(transfer.getType());
                    t.setTransaction_Date(transfer.getTransaction_Date());
                    t.setStatus(transfer.getStatus());
                    t.setMedium(transfer.getMedium());
                    t.setPlayer_Id(transfer.getPlayer_Id());
                    t.setPayee_Id(transfer.getPayee_Id());
                    t.setAmount(transfer.getAmount());
                    t.setDescription(transfer.getDescription());
                    t.setScoreRisk(1);
                    t.setNameTypeRisk(TypeRisk.BLACKLIST);

                    t.setAccountId(account.getId());
                    t.setAccountType(account.getType());
                    t.setNickname(account.getNickname());
                    t.setRewards(account.getRewards());
                    t.setBalance(account.getBalance());
                    t.setAccountNumber(account.getAccount_Number());
                    t.setCustomerId(account.getCustomer_Id());

                    data.add(t);
                }
            }



        }

        myTransfersFE = data;
        return data;
    }

    @RequestMapping("/loansFE")
    public List<CustomerLoan> getLoanFE() {

        if (myLoansFE != null && !myLoansFE.isEmpty()) {
            return myLoansFE;
        }
    	restApi = new RestApi();
    	
    	List<Customer> customers = restApi.getCustomers();
    	ArrayList<CustomerLoan> customerLoanList = new ArrayList<>();
    	for (Customer customer : customers) {
    		List<Account> accounts = restApi.getAccountByCustomer(customer.getId());
    		Integer i=0;
    		for (Account account : accounts) {
    			List<Loan> loans = restApi.getLoanByAccount(account.getId());
    			if (loans != null){
	    			for (Loan loan : loans) {
	    				i++;
	    			}
    			}
    			else{
    				//System.out.println("No Loans");
    			}
    		}
    		CustomerLoan customerLoan = new CustomerLoan();
    		customerLoan.setFirst_Name(customer.getFirst_Name());
    		customerLoan.setId(customer.getId());
    		customerLoan.setLoan(String.valueOf(i));
    		customerLoan.setState(customer.getState());
    		customerLoan.setStreet_name(customer.getStreet_name());
    		customerLoan.setStreet_number(customer.getStreet_number());
    		customerLoan.setLat(customer.getLat());
    		customerLoan.setLng(customer.getLng());
    		customerLoan.setZip(customer.getZip());
    		customerLoan.setCity(customer.getCity());
    		customerLoan.setGender(customer.getGender().trim());
    		customerLoan.setDoc_Number(customer.getDoc_Number());
    		customerLoan.setLast_Name(customer.getLast_Name());
    		
    		customerLoanList.add(customerLoan);	
    	}

    	myLoansFE = customerLoanList;
    	return customerLoanList;
    }

    @RequestMapping("/loanRequester")
    public double getLoanRequesterType(@RequestParam("lat") double lat,
                                    @RequestParam("lng") double lng,
                                    @RequestParam("accounts") double accounts,
                                    @RequestParam("amount") double amount,
                                    @RequestParam("rewards") double rewards,
                                    @RequestParam("transaction") double transactionNumber,
                                    @RequestParam("transactionTotalAmounts") double transacationsAmount) {

        OctaveEngine octave = new OctaveEngineFactory().getScriptEngine();
        octave.eval("neural_network;");
        octave.put("lat", Octave.scalar(lat));
        octave.put("lng", Octave.scalar(lng));
        octave.put("accountsNumber", Octave.scalar(accounts));
        octave.put("balance", Octave.scalar(amount));
        octave.put("rewards", Octave.scalar(rewards));
        octave.put("transfersCounts", Octave.scalar(transactionNumber));
        octave.put("transfersAmount", Octave.scalar(transacationsAmount));


        octave.eval("customer = [lat, lng, accountsNumber, balance, rewards, transfersCounts, transfersAmount];");

        octave.eval("mu = mean(customer);");
        octave.eval("sigma = std(customer);");
        octave.eval("customer = (customer - mu) ./ sigma;");
        octave.eval("out = sim(net, customer')");
        OctaveDouble result = octave.get(OctaveDouble.class, "out");
        double r = result.getData()[0];

        if (r < 0) {
            return 0;
        }
        return r;
    }

    @RequestMapping("/cluster/customer")
    public CustomerClusteringResult test(@RequestParam("k") int k) {

        OctaveEngine octave = new OctaveEngineFactory().getScriptEngine();

        octave.eval("a = [1, 2, 3, 4; 5, 6, 7, 8; 9, 10, 11, 12];");
        OctaveDouble a = octave.get(OctaveDouble.class, "a");

        octave.eval("pkg load statistics;");
        octave.eval("data = load (\"customers.csv\");");
        octave.eval("data_norm = data(:,4:8);");
        octave.eval("mu = mean(data_norm);");
        octave.eval("sigma = std(data_norm);");
        octave.eval("data_norm = (data_norm - mu) ./ sigma;");
        octave.put("k", Octave.scalar(k));
        octave.eval("[result, centers] = kmeans(data_norm, k);");

        //get and parse the result
        OctaveDouble result = octave.get(OctaveDouble.class, "result");
        double[] centers = octave.get(OctaveDouble.class, "centers").getData();


        CustomerClusteringResult response = new CustomerClusteringResult();
        double[] index = result.getData();
        List<CustomerToCompare> data = getCustomersToCompare();
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setGroupIndex(index[i]);
        }
        response.setDataClusterered(data);

        //centers
        CustomerToCompare[] customerCenters = new CustomerToCompare[k];
        int j;
        for (int i = 0; i < k; i++) {
            CustomerToCompare c = new CustomerToCompare();
            j = i;
            c.setAccountCount(centers[j]);
            j += k;
            c.setTotalBalance(centers[j]);
            j += k;
            c.setTotalRewards(centers[j]);
            j += k;
            c.setTransferCount(centers[j]);
            j += k;
            c.setTotalTransfersAmount(centers[j]);
            customerCenters[i] = c;
        }
        response.setClusterCenters(Arrays.asList(customerCenters));

        System.out.println(result);

        return response;
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

    @RequestMapping("/saveLoansAsVector")
    public void saveLoansAsVector() {
        try {
            List<CustomerLoan> customers =  getLoanFE();

            FileWriter file = new FileWriter("loans.csv");
            BufferedWriter writer = new BufferedWriter(file);

            for (int i = 0; i < customers.size(); i++) {
                CustomerLoan c = customers.get(i);

                if (i > 0) {
                    writer.newLine();
                }
                writer.write(c.getLoan());
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
