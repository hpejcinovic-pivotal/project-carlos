package com.acme;

import static org.springframework.xd.tuple.TupleBuilder.tuple;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.util.StringUtils;
import org.springframework.xd.tuple.Tuple;


@Configuration
@EnableIntegration
public class ModuleConfiguration {

	@Autowired
	GenericTransformer<String,Tuple> transformer;
	
	@Autowired
	GenericSelector<String> selector;

	@Bean
	public MessageChannel input() {
		return new DirectChannel();
	}

	@Bean
	MessageChannel output() {
		return new DirectChannel();
	}

	@Bean
	public IntegrationFlow myFlow() {
		return IntegrationFlows.from(this.input())
				.filter(selector)
				.transform(transformer)
				.channel(this.output())
				.get();
	}
}

@Configuration
@Profile({"use-both","default"})
class TaxiRideConfiguration {
	 static int recordCounter=0;
	 static long timeAt10k_records = System.currentTimeMillis();

	@Bean
	GenericTransformer<String, Tuple> transformer() {
		return new GenericTransformer<String, Tuple>() {
			@Override
			public Tuple transform(String payload) {
				
				String items[] = StringUtils.delimitedListToStringArray(payload, ",");
				
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
   								     .put("record_id", recordCounter)
								     .build();
				
				return tuple;
			}
		};
	}
	
	
	@Bean 
	GenericSelector<String> select(){
		
		return new GenericSelector<String>() {
			

			
			@Override
			public boolean accept(String payload) {
				recordCounter++;

				
				if(recordCounter % 10000 == 0){
					long currentTime = System.currentTimeMillis();
					System.out.println("It took to process 10k records:" + (currentTime - timeAt10k_records)/1000 );
					timeAt10k_records = System.currentTimeMillis();
				}
				
				System.out.println("Record no:" + recordCounter);
				String items[] = StringUtils.delimitedListToStringArray(payload, ",");
				
				Set<Integer> skipValidationIndexes = new HashSet<Integer>((Arrays.asList(4,12,13,14,15)));
				Set<Integer> dateFields = new HashSet<Integer>((Arrays.asList(2,3)));
				
				for(int i=0; i< items.length;i++){
					
					if(!skipValidationIndexes.contains(i)){
						
						String string = items[i];
						
						if(NumberUtils.isNumber(string) && NumberUtils.createDouble(string) == 0 ) {
							System.err.println("invalid numeric value for item: "+i +" = "+string);
							return false;
						}
						
						if(StringUtils.isEmpty(string)) {
							System.err.println("invalid string value for item: "+i +" = "+string);
							return false;
						}
						
						if(dateFields.contains(i) && !isValidDate(string)) {
							System.err.println("invalid date value for item: "+i +" = "+string);
							return false;
						}
					}
				}
				return true;
			}
		};
		
	}
	

	public static boolean isValidDate(String inDate) {
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss"); //2013-01-01 00:00:00 
		//dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (Exception pe) {
			System.err.println(pe);
			return false;
		}
		return true;
	}

}

