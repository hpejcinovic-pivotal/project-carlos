package latlong.carlos.util;

import org.springframework.xd.reactor.Processor;
import org.springframework.xd.tuple.Tuple;
import org.springframework.xd.tuple.TupleBuilder;
import reactor.rx.Stream;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LatLongTransformation implements Processor<Tuple, Tuple> {


    public LatLongTransformation() {
    }


    @Override
    public Stream<Tuple> process(Stream<Tuple> inputStream) {
        return inputStream.map(tuple -> calculateCellIds(tuple));
    }

    private static Tuple calculateCellIds(Tuple tuple) {
    	System.out.println("In LatLong Module recordId" + tuple.getString("record_id"));
        Double pickup_longitude = Double.parseDouble((String) tuple.getValue("pickup_longitude"));
        Double pickup_latitude = Double.parseDouble((String) tuple.getValue("pickup_latitude"));
        Double dropoff_longitude = Double.parseDouble((String) tuple.getValue("dropoff_longitude"));
        Double dropoff_latitude = Double.parseDouble((String) tuple.getValue("dropoff_latitude"));
        List<Object> values = new ArrayList<>(tuple.getValues());
        List<String> fieldNames = new ArrayList<>(tuple.getFieldNames());
        Cell pickup_cell = LatLongUtil.calcCellId(new LatLong((double) pickup_latitude, (double) pickup_longitude));
        Cell dropoff_cell = LatLongUtil.calcCellId(new LatLong((double) dropoff_latitude, (double) dropoff_longitude));
        if (pickup_cell == null || dropoff_cell == null) {
            System.err.println("This entries cell locations are outside the given area" + tuple.toString());
        } else {
            fieldNames.add("pickup_Cell");
            fieldNames.add("dropoff_Cell");
            values.add(pickup_cell.toString());
            values.add(dropoff_cell.toString());
        }
        return TupleBuilder.tuple().ofNamesAndValues(fieldNames, values);
    }

}
