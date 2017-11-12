package com.kun.hardreset.api.responses;

import com.kun.hardreset.model.CustomerToCompare;

import java.util.List;

public class CustomerClusteringResult {

    private List<CustomerToCompare> clusterCenters;

    private List<CustomerToCompare> dataClusterered;


    public List<CustomerToCompare> getClusterCenters() {
        return clusterCenters;
    }

    public void setClusterCenters(List<CustomerToCompare> clusterCenters) {
        this.clusterCenters = clusterCenters;
    }

    public List<CustomerToCompare> getDataClusterered() {
        return dataClusterered;
    }

    public void setDataClusterered(List<CustomerToCompare> dataClusterered) {
        this.dataClusterered = dataClusterered;
    }
}
