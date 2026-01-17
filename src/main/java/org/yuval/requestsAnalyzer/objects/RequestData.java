package org.yuval.requestsAnalyzer.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestData {
    // region Members
    private String country;
    private String operatingSystem;
    private String browser;
    // endregion

    public RequestData(String country, String operatingSystem, String browser) {
        this.country = country;
        this.operatingSystem = operatingSystem;
        this.browser = browser;
    }
}
