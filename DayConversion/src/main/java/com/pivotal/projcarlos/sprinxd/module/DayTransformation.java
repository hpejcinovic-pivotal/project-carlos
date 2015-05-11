package com.pivotal.projcarlos.sprinxd.module;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.transformer.MessageTransformationException;
import org.springframework.xd.tuple.Tuple;

@MessageEndpoint
public class DayTransformation {
	private static final DateFormat inputFormat = new SimpleDateFormat(
			"yyyy-mm-dd hh:MM:ss");
	private static final DateFormat outputFormat = new SimpleDateFormat("EEE");

	public DayTransformation() {
	}

	@Transformer(inputChannel = "input", outputChannel = "output")
	public String transform(Tuple taxiRide) {
		try {
			System.out.println("In DayTransformer recordId" + taxiRide.getString("record_id"));
			Date dayOfWeekDate = getDateFromString(taxiRide.getString(
					"dropoff_datetime"));
			String pickup_datetime = taxiRide.getString("pickup_datetime");
			String dropoff_datetime = taxiRide.getString("dropoff_datetime");
			String pickup_Cell = taxiRide.getString("pickup_Cell");
			String dropoff_Cell = taxiRide.getString("dropoff_Cell");

			String dayOfWeek = getDayOfWeek(dayOfWeekDate);

			String returnString = dayOfWeek + "," + pickup_datetime + ","
					+ dropoff_datetime + "," + pickup_Cell + "," + dropoff_Cell;
			System.out.println("return string:" + returnString);
			return returnString;
		} catch (Exception e) {
			throw new MessageTransformationException(
					"Unable to transform taxiRide: " + e.getMessage(), e);
		}
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
