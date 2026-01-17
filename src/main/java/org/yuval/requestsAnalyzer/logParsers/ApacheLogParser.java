package org.yuval.requestsAnalyzer.logParsers;

import org.yuval.requestsAnalyzer.objects.RequestData;
import org.yuval.requestsAnalyzer.services.GeoIpService;
import org.yuval.requestsAnalyzer.services.UserAgentService;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApacheLogParser implements LogParser {
    // region Members
    private static final String           LOG_PATTERN =
            "^([\\d.]+) - - \\[[^]]+\\] \"[^\"]*\" \\d{3} (\\d+|-) \"[^\"]*\" \"([^\"]*)\"$";
    private static final Pattern          pattern     = Pattern.compile(LOG_PATTERN);
    private final        UserAgentService userAgentService;
    private final        GeoIpService     geoIpService;
    // endregion

    // region Public Methods
    public ApacheLogParser(UserAgentService userAgentService, GeoIpService geoIpService) {
        this.userAgentService = userAgentService;
        this.geoIpService = geoIpService;
    }

    @Override
    public LogParserType getType() {
        return LogParserType.APACHE;
    }

    @Override
    public ArrayList<RequestData> getRequestDataList(String logContent) throws Exception {
        ArrayList<RequestData> retVal = new ArrayList<>();

        if (logContent == null || logContent.isBlank()) {
            return retVal;
        }

        String[] lines = logContent.split("\\R");

        for (String line : lines) {
            if (line == null || line.isBlank()) {
                continue;
            }

            Matcher matcher = pattern.matcher(line);
            if (!matcher.matches()) {
                continue;
            }

            String ipAddress = matcher.group(1);
            String userAgent = matcher.group(3);

            String operatingSystem = "Unknown";
            String browser         = "Unknown";
            String country         = "Unknown";

            if (userAgent != null && !userAgent.isBlank() && !userAgent.equals("-")) {
                String[] osAndBrowser = userAgentService.parseUserAgent(userAgent);
                operatingSystem = osAndBrowser[0];
                browser = osAndBrowser[1];
            }

            country = geoIpService.countryForIp(ipAddress);

            RequestData data = new RequestData(country, operatingSystem, browser);

            retVal.add(data);
        }

        return retVal;
    }
    // endregion
}
