package org.yuval.requestsAnalyzer.services;

import ua_parser.Client;
import ua_parser.Parser;

public class UserAgentService {
    // region Members
    private static final Parser userAgentParser = new Parser();
    // endregion

    // region Public Methods
    public String[] parseUserAgent(String userAgent) {
        String[] retVal;
        String   operatingSystem = "Unknown";
        String   browser         = "Unknown";

        Client client = userAgentParser.parse(userAgent);

        if (client != null) {
            if (client.os != null && client.os.family != null && !client.os.family.isBlank()) {
                operatingSystem = client.os.family;
            }
            if (client.userAgent != null && client.userAgent.family != null && !client.userAgent.family.isBlank()) {
                browser = client.userAgent.family;
            }
        }

        retVal = new String[]{operatingSystem, browser};

        return retVal;
    }
    // endregion
}
