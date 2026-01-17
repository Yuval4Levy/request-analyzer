package org.yuval.requestsAnalyzer.services;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CountryResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

public class GeoIpService {
    // region Members
    private final DatabaseReader reader;
    // endregion

    // region Public Methods
    public GeoIpService() throws IOException {
        InputStream dbStream = getClass().getClassLoader().getResourceAsStream("geoip/GeoLite2-Country.mmdb");

        if (dbStream == null) {
            throw new IllegalStateException("GeoLite2 database not found in resources");
        }

        this.reader = new DatabaseReader.Builder(dbStream).build();
    }

    public String countryForIp(String ip) {
        String retVal = "Unknown";

        if (ip != null && !ip.isBlank() && !ip.equals("-")) {
            try {
                InetAddress     address  = InetAddress.getByName(ip);
                CountryResponse response = reader.country(address);

                if (response.getCountry() != null && response.getCountry().getName() != null &&
                    !response.getCountry().getName().isBlank()) {

                    retVal = response.getCountry().getName();
                }

            }
            catch (Exception e) {
                // all failures map to "Unknown"
            }
        }

        return retVal;
    }
    // endregion
}
