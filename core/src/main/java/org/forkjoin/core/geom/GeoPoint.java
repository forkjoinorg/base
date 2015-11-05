package org.forkjoin.core.geom;

import static java.lang.Math.*;

/**
 * @author zuoge85 on 15/5/14.
 */
public class GeoPoint {
    public static final double EARTH_RADIUS_KM = 6371.01;

    private double longitude;
    private double latitude;
    private String info;

    public GeoPoint() {
    }

    public GeoPoint(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public GeoPoint(double longitude, double latitude, String info) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.info = info;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 返回距离，单位千米
     * @return 距离单位千米
     */
    public final double distance(GeoPoint position) {
        double lat1 = toRadians(latitude);
        double lat2 = toRadians(position.latitude);
        double lon1 = toRadians(longitude);
        double lon2 = toRadians(position.longitude);
        double deltaLon = lon2 - lon1;
        double cosLat2 = cos(lat2);
        double cosLat1 = cos(lat1);
        double sinLat1 = sin(lat1);
        double sinLat2 = sin(lat2);
        double cosDeltaLon = cos(deltaLon);
        double top = sqrt(sqr(cosLat2 * sin(deltaLon))
                + sqr(cosLat1 * sinLat2 - sinLat1 * cosLat2 * cosDeltaLon));
        double bottom = sinLat1 * sinLat2 + cosLat1 * cosLat2 * cosDeltaLon;
        double distance = EARTH_RADIUS_KM * atan2(top, bottom);
        return abs(distance);
    }

    private double sqr(double d) {
        return d * d;
    }

    @Override
    public String toString() {
        return "GeoPoint{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", info='" + info + '\'' +
                '}';
    }
}
