import latlong.carlos.util.LatLongTransformation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;
import org.springframework.xd.reactor.Processor;
import org.springframework.xd.tuple.Tuple;
import reactor.Environment;
import reactor.rx.Stream;
import reactor.rx.broadcast.Broadcaster;
import reactor.rx.broadcast.SerializedBroadcaster;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.springframework.xd.tuple.TupleBuilder.tuple;

public class LatLongTransformationTupleTest {


    protected Environment env;

    @Before
    public void loadEnv() {
        env = Environment.initializeIfEmpty().assignErrorJournal();
    }

    @After
    public void closeEnv() {
        Environment.terminate();
    }

    @Test
    public void latlongTransformation() throws IOException {

        final Broadcaster<Object> broadcaster = SerializedBroadcaster.create();

        Processor processor = new LatLongTransformation();
        Stream<?> outputStream = processor.process(broadcaster);


        outputStream.consume(o -> {
            System.out.println("processed : " + o);
        });

        FileInputStream inputStream = new FileInputStream("../data/10Entries.csv");
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            String entry = scanner.nextLine();
            String items[] = StringUtils.delimitedListToStringArray(entry, ",");

            Tuple tuple = tuple().put("medallion", items[0])
                    .put("hack_licence", items[1])
                    .put("pickup_datetime", items[2])
                    .put("dropoff_datetime", items[3])
                    .put("trip_tim_in_secs", items[4])
                    .put("trip_distance", items[5])
                    .put("pickup_longitude", items[6])
                    .put("pickup_latitude", items[7])
                    .put("dropoff_longitude", items[8])
                    .put("dropoff_latitude", items[9])
                    .put("payment_type", items[10])
                    .put("fare_amount", items[11])
                    .put("surcharge", items[12])
                    .put("mta_tax", items[13])
                    .put("tip_amount", items[14])
                    .put("tolls_amount", items[15])
                    .put("total_amount", items[16])
                    .build();
            broadcaster.onNext(tuple);
        }
        //System.in.read();

    }
}
