package com.edutilos.client.worker.utils;

import com.edutilos.client.worker.model.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edutilos on 05.08.18.
 */
public class WorkerParser {
    //{id=1, name=foo, age=10, wage=100.0, active=true}
    public Worker parseCurlyWorker(String input) {
        input = input.substring(1, input.length()-1);
        String [] splitted = input.split(",");
        Worker w = new Worker();
        w.setId(Long.parseLong(splitted[0].split("=")[1]));
        w.setName(splitted[1].split("=")[1]);
        w.setAge(Integer.parseInt(splitted[2].split("=")[1]));
        w.setWage(Double.parseDouble(splitted[3].split("=")[1]));
        w.setActive(Boolean.parseBoolean(splitted[4].split("=")[1]));
        return w;
    }

    public List<Worker> parseCurlyWorkers(List workersStr) {
        List<Worker> all = new ArrayList<>();
        for(Object workerStr: workersStr) {
            all.add(parseCurlyWorker(workerStr.toString()));
        }
        return all;
    }
}
