package com.pivotal.projcarlos.sprinxd.module;

import org.springframework.xd.reactor.Processor;
import org.springframework.xd.tuple.Tuple;
import reactor.rx.Stream;
import org.springframework.xd.tuple.TupleBuilder;
import reactor.rx.Streams;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayTransformation implements Processor<Tuple, Tuple> {

    private static final DateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
    private static final DateFormat outputFormat = new SimpleDateFormat("EEE");

    public DayTransformation() {
    }


    @Override
    public Stream<Tuple> process(Stream<Tuple> inputStream) {
        return inputStream.map(tuple -> transformDate(tuple));
    }

    private static Tuple transformDate(Tuple tuple) {
        List<Object> values = new ArrayList<>(tuple.getValues());
        List<String> fieldNames = new ArrayList<>(tuple.getFieldNames());
        fieldNames.add("dayOfWeek");
        values.add(getDayOfWeek(tuple.getString("dropoff_datetime")));
        return TupleBuilder.tuple().ofNamesAndValues(fieldNames, values);
    }

    private static String getDayOfWeek(String dropoff_datetime) {
        Date date = null;
        try {
            date = inputFormat.parse(dropoff_datetime);
        } catch (ParseException e) {
            e.printStackTrace();
            return "N/A";
        }
        return outputFormat.format(date);
    }

}