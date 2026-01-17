package org.yuval.requestsAnalyzer.handlers;

import org.yuval.requestsAnalyzer.logParsers.ApacheLogParser;
import org.yuval.requestsAnalyzer.objects.RequestData;
import org.yuval.requestsAnalyzer.services.AnalyzerService;
import org.yuval.requestsAnalyzer.services.GeoIpService;
import org.yuval.requestsAnalyzer.services.OutputService;
import org.yuval.requestsAnalyzer.services.UserAgentService;

import java.util.ArrayList;
import java.util.HashMap;

public class ApacheHandler implements ResourceHandler {
    // region Public Methods
    @Override
    public void printRequestsStatistics(String logContent) throws Exception {
        UserAgentService userAgentService = new UserAgentService();
        GeoIpService     geoIpService     = new GeoIpService();
        ApacheLogParser  parser           = new ApacheLogParser(userAgentService, geoIpService);
        OutputService    outputService    = new OutputService();

        ArrayList<RequestData> requestData = parser.getRequestDataList(logContent);

        HashMap<String, Double> countryStatistics = new AnalyzerService() {
            @Override
            protected String getFieldValue(RequestData requestData) {
                return requestData.getCountry();
            }
        }.getStatistics(requestData);

        HashMap<String, Double> osStatistics = new AnalyzerService() {
            @Override
            protected String getFieldValue(RequestData requestData) {
                return requestData.getOperatingSystem();
            }
        }.getStatistics(requestData);

        HashMap<String, Double> browserStatistics = new AnalyzerService() {
            @Override
            protected String getFieldValue(RequestData requestData) {
                return requestData.getBrowser();
            }
        }.getStatistics(requestData);

        outputService.printStatistics("Country", countryStatistics);
        outputService.printStatistics("Operating System", osStatistics);
        outputService.printStatistics("Browser", browserStatistics);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.APACHE;
    }
    // endregion
}

