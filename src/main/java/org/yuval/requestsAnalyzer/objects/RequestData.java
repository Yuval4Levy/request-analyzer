package org.yuval.requestsAnalyzer.objects;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
