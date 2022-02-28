package geolocator;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;

/**
 * Interface for obtaining geolocation information of an IP address. The actual
 * implementation uses <a href="http://ip-api.com">IP-API</a>
 * {@code static} interface method {@link #newInstance()} is providing to obtain
 * {@code GeoLocator} object
 */

public interface GeoLocator {

    /**
     * Return geolocation information about the JVM running the application
     *
     * @return an object wrapping the geolocation information returned
     * @throws feign.FeignException if an error occurs
     */

    @RequestLine("GET")
    GeoLocation getGeoLocation();

    /**
     * Return geolocaton information about the IP address or host name specified
     * @param ipOrHostName the IP address or host name
     * @return an object wrapping the geolocation information returned
     * @throws feign.FeignException if an error occurs
     */

    @RequestLine("GET /{ipOrHostName}")
    GeoLocation getGeoLocation(@Param("ipOrHostName") String ipOrHostName);

    /**
     * Creates and returns an object that implemets the interface
     *
     * @return the object that implemets interface
     */

    static GeoLocator newInstance() {
        return Feign.builder()
            .decoder(new JacksonDecoder())
            .target(GeoLocator.class, "http://ip-api.com/json/");
    }

}
