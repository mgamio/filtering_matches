package com.spark.filtering.rest.geo;

public class GeoServiceImpl implements GeoService {

    @Override
    public double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {

        //Ref: https://www.geodatasource.com/developers/java

        if ((latitude1 == latitude2) && (longitude1 == longitude2)) {
            return 0;
        }
        else {
            double theta = longitude1 - longitude2;
            double distance = Math.sin(Math.toRadians(latitude1)) * Math.sin(Math.toRadians(latitude2))
                    + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) * Math.cos(Math.toRadians(theta));
            distance = Math.acos(distance);
            distance = Math.toDegrees(distance);
            distance = distance * 60 * 1.1515;
            distance = distance * 1.609344; // to Km.

            return (distance);
        }
    }

}
