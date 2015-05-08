package com.pivotal.projcarlos.sprinxd.module;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.xd.reactor.Processor;
import org.springframework.xd.tuple.Tuple;
import org.springframework.xd.tuple.TupleBuilder;

import reactor.rx.Stream;

public class DayTransformation implements Processor<Tuple, TaxiRide> {

    private static final DateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
    private static final DateFormat outputFormat = new SimpleDateFormat("EEE");

    public DayTransformation() {
    }


    @Override
    public Stream<TaxiRide> process(Stream<Tuple> inputStream) {
        return inputStream.map(tuple -> transformDate(tuple));
    }

    private static TaxiRide transformDate(Tuple tuple) {
        List<Object> values = new ArrayList<>(tuple.getValues());
        List<String> fieldNames = new ArrayList<>(tuple.getFieldNames());
        Date date = getDateFromString(tuple.getString("dropoff_datetime"));
        fieldNames.add("dayOfWeek");
        values.add(getDayOfWeek(date));
        
        tuple = TupleBuilder.tuple().ofNamesAndValues(fieldNames, values);
        
        
        TaxiRide ride = new TaxiRide();
        	
        ride.setDropoff_datetime(tuple.getString("dropoff_datetime"));
    	ride.setDropoff_latitude(tuple.getString("dropoff_latitude"));
    	ride.setDropoff_longitude(tuple.getString("dropoff_longitude"));
    	ride.setFare_amount(tuple.getString("fare_amount"));
    	ride.setHack_licence(tuple.getString("hack_licence"));
    	ride.setMedallion(tuple.getString("medallion"));
    	ride.setMta_tax(tuple.getString("mta_tax"));
    	ride.setPayment_type(tuple.getString("payment_type"));
    	ride.setPickup_datetime(tuple.getString("pickup_datetime"));
    	ride.setPickup_latitude(tuple.getString("pickup_latitude"));
    	ride.setPickup_longitude(tuple.getString("pickup_longitude"));
    	ride.setSurcharge(tuple.getString("surcharge"));
    	ride.setTip_amount(tuple.getString("tip_amount"));
    	ride.setTolls_amount(tuple.getString("tolls_amount"));
    	ride.setTotal_amount(tuple.getString("total_amount"));
    	ride.setTrip_distance(tuple.getString("trip_distance"));
    	ride.setTrip_tim_in_secs(tuple.getString("trip_tim_in_secs"));
    	ride.setDropoff_Cell(tuple.getString("dropoff_Cell"));
    	ride.setPickup_Cell(tuple.getString("pickup_Cell"));
    	ride.setDayOfWeek(tuple.getString("dayOfWeek"));
    	
    	return ride;
        
    }

    private static String getDayOfWeek(Date date) {

        return outputFormat.format(date);
    }

    private static Date getDateFromString(String dateString) {
        try {
            return inputFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
