package domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip implements Serializable {

    public static String DATE_FORMAT = "yyyy-MM-dd HH:mm:SS";

    private String medallion;
    private String hackLicense;
    private Date pickupDatetime;
    private Date dropoffDatetime;
    private double tripTimeInSecs;
    private double tripDistance;
    private double pickupLongitude;
    private double pickupLatitude;
    private double dropoffLongitude;
    private double dropoffLatitude;
    private String paymentType;
    private double fareAmount;
    private String startCell;
    private String endCell;


    public Trip(String medallion, String hackLicense, String pickupDatetime, String dropoffDatetime,
                double tripTimeInSecs, double tripDistance,
                double pickupLongitude, double pickupLatitude, double dropoffLongitude, double dropoffLatitude,
                String paymentType, double fareAmount) {
        super();

        this.medallion = medallion;
        this.hackLicense = hackLicense;
        this.pickupDatetime = parse(pickupDatetime);
        this.dropoffDatetime = parse(dropoffDatetime);
        this.tripTimeInSecs = tripTimeInSecs;
        this.tripDistance = tripDistance;
        this.pickupLongitude = pickupLongitude;
        this.pickupLatitude = pickupLatitude;
        this.dropoffLongitude = dropoffLongitude;
        this.dropoffLatitude = dropoffLatitude;
        this.paymentType = paymentType;
        this.fareAmount = fareAmount;

        this.startCell = new Cell(this.pickupLongitude, this.pickupLatitude).toString();
        this.endCell = new Cell(this.dropoffLongitude, this.dropoffLatitude).toString();

    }


    private Date parse(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "TripInfo [medallion=" + medallion + ", hackLicense=" + hackLicense + ", " + this.getPickupDatetime() + ", " + this.getDropoffDatetime() + ", " + this.getStartCell() + ", " + this.getEndCell();
    }


    class Cell {

        private double START_LONG = 41.474937;
        private double START_LAT = -74.913585;
        private int xIndex;
        private int yIndex;

        public Cell(double longitude, double latitude) {
            double xDistance = haversine(START_LAT, START_LONG, latitude, START_LONG);
            double yDistance = haversine(START_LAT, START_LONG, START_LAT, longitude);
            xIndex = (int) xDistance * 1000 / 500;
            yIndex = (int) yDistance * 1000 / 500;

        }

        public String toString() {
            return xIndex + "." + yIndex;
        }

        /**
         * Calculates the distance in km between two lat/long points
         * using the haversine formula
         * http://stackoverflow.com/questions/18861728/calculating-distance-between-two-points-represented-by-lat-long-upto-15-feet-acc
         */
        public double haversine(
                double lat1, double lng1, double lat2, double lng2) {
            int r = 6371; // average radius of the earth in km
            double dLat = Math.toRadians(lat2 - lat1);
            double dLon = Math.toRadians(lng2 - lng1);
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                            * Math.sin(dLon / 2) * Math.sin(dLon / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double d = r * c;
            return d;
        }

    }

    public String getMedallion() {
        return medallion;
    }

    public void setMedallion(String medallion) {
        this.medallion = medallion;
    }

    public String getHackLicense() {
        return hackLicense;
    }

    public void setHackLicense(String hackLicense) {
        this.hackLicense = hackLicense;
    }

    public Date getPickupDatetime() {
        return pickupDatetime;
    }

    public void setPickupDatetime(Date pickupDatetime) {
        this.pickupDatetime = pickupDatetime;
    }

    public Date getDropoffDatetime() {
        return dropoffDatetime;
    }

    public void setDropoffDatetime(Date dropoffDatetime) {
        this.dropoffDatetime = dropoffDatetime;
    }

    public double getTripTimeInSecs() {
        return tripTimeInSecs;
    }

    public void setTripTimeInSecs(double tripTimeInSecs) {
        this.tripTimeInSecs = tripTimeInSecs;
    }

    public double getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    public double getPickupLongitude() {
        return pickupLongitude;
    }

    public void setPickupLongitude(double pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    public double getPickupLatitude() {
        return pickupLatitude;
    }

    public void setPickupLatitude(double pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    public double getDropoffLongitude() {
        return dropoffLongitude;
    }

    public void setDropoffLongitude(double dropoffLongitude) {
        this.dropoffLongitude = dropoffLongitude;
    }

    public double getDropoffLatitude() {
        return dropoffLatitude;
    }

    public void setDropoffLatitude(double dropoffLatitude) {
        this.dropoffLatitude = dropoffLatitude;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getFareAmount() {
        return fareAmount;
    }

    public void setFareAmount(double fareAmount) {
        this.fareAmount = fareAmount;
    }

    public String getStartCell() {
        return startCell;
    }

    public void setStartCell(String startCell) {
        this.startCell = startCell;
    }

    public String getEndCell() {
        return endCell;
    }

    public void setEndCell(String endCell) {
        this.endCell = endCell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;

        if (dropoffDatetime != null ? !dropoffDatetime.equals(trip.dropoffDatetime) : trip.dropoffDatetime != null)
            return false;
        if (hackLicense != null ? !hackLicense.equals(trip.hackLicense) : trip.hackLicense != null) return false;
        if (medallion != null ? !medallion.equals(trip.medallion) : trip.medallion != null) return false;
        if (pickupDatetime != null ? !pickupDatetime.equals(trip.pickupDatetime) : trip.pickupDatetime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = medallion != null ? medallion.hashCode() : 0;
        result = 31 * result + (hackLicense != null ? hackLicense.hashCode() : 0);
        result = 31 * result + (pickupDatetime != null ? pickupDatetime.hashCode() : 0);
        result = 31 * result + (dropoffDatetime != null ? dropoffDatetime.hashCode() : 0);
        return result;
    }
}
