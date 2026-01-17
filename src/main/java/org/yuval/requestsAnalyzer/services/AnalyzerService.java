package org.yuval.requestsAnalyzer.services;

import org.yuval.requestsAnalyzer.objects.RequestData;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AnalyzerService {

    // region Protected Methods
    protected abstract String getFieldValue(RequestData requestData);
    // endregion

    // region Public Methods
    public HashMap<String, Double> getStatistics(ArrayList<RequestData> requestDataList) {
        HashMap<String, Double> retVal = new HashMap<>();

        if (requestDataList != null && !requestDataList.isEmpty()) {
            for (RequestData requestData : requestDataList) {
                String fieldValue = getFieldValue(requestData);
                retVal.put(fieldValue, retVal.getOrDefault(fieldValue, 0.0) + 1.0);
            }

            double totalRequests = requestDataList.size();
            for (String key : retVal.keySet()) {
                double percentage = (retVal.get(key) / totalRequests) * 100.0;
                retVal.put(key, percentage);
            }
        }

        return retVal;
    }
    // endregion
}

