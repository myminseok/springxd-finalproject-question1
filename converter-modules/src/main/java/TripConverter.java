import domain.Trip;

import java.text.SimpleDateFormat;

public class TripConverter {

    public Trip translate(String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Trip.DATE_FORMAT);
        String[] splits = input.split(",");
        Trip obj = new Trip(
                splits[0],
                splits[1],
                splits[2],
                splits[3],
                Double.valueOf(splits[4]).doubleValue(),
                Double.valueOf(splits[5]).doubleValue(),
                Double.valueOf(splits[6]).doubleValue(),
                Double.valueOf(splits[7]).doubleValue(),
                Double.valueOf(splits[8]).doubleValue(),
                Double.valueOf(splits[9]).doubleValue(),
                splits[10],//type
                Double.valueOf(splits[11]).doubleValue());//

        System.out.println("input:" + input + " =>" + obj);
        return obj;
    }
}