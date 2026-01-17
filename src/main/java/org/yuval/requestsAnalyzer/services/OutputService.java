package org.yuval.requestsAnalyzer.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputService {
    // region Public Methods
    public void printStatistics(String metricName, HashMap<String, Double> statistics) {
        if (statistics == null || statistics.isEmpty()) {
            System.out.println(metricName + ": No data available");
            return;
        }

        LinkedHashMap<String, Double> sortedStatistics =
                statistics.entrySet().stream().sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue())).collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        StringBuilder output = new StringBuilder(metricName + ": \n");
        StringBuilder stats  = new StringBuilder();

        for (Map.Entry<String, Double> entry : sortedStatistics.entrySet()) {
            stats.append(String.format(" %s %.2f%%\n", entry.getKey(), entry.getValue()));
        }

        output.append(stats);
        System.out.println(output);
    }
    // endregion
}
