package com.spark.filtering.rest.geo;

public interface GeoService {

    /**
     * Calculating distance between two points represented by latitude,longitude
     *
     * @param latitude1
     * @param longitude1
     * @param latitude2
     * @param longitude2
     * @return
     */
    double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2);
}
