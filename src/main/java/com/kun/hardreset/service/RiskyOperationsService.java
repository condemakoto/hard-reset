package com.kun.hardreset.service;

import com.kun.hardreset.data.RestApi;
import com.kun.hardreset.model.Transfer;
import dk.ange.octave.OctaveEngine;
import dk.ange.octave.OctaveEngineFactory;
import dk.ange.octave.type.Octave;
import dk.ange.octave.type.OctaveDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

public class RiskyOperationsService {

    private RestApi restApi;

    public static void main(String[] args) {
        String accountId = "2200";
        new RiskyOperationsService().getTransfers(accountId);
    }

    public void getTransfers(String accountId) {
        List<Transfer> transfers = restApi.getTransfersByAccount(accountId);

        OctaveEngine octave = new OctaveEngineFactory().getScriptEngine();
        octave.put("t1", Octave.scalar(5));
        octave.put("t2", Octave.scalar(10));
        octave.eval("result = t1 + t2");
        OctaveDouble result = octave.get(OctaveDouble.class, "result");
        octave.close();
        final double integral = result.get(1);
        System.out.println(integral);

    }


}
