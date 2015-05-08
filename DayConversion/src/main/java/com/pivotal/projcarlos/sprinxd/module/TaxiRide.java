package com.pivotal.projcarlos.sprinxd.module;

import java.io.Serializable;

public class TaxiRide implements Serializable{

	private String medallion;
	private String hack_licence;
	private String pickup_datetime;
	private String dropoff_datetime;
	private String trip_tim_in_secs;
	private String trip_distance;
	private String pickup_longitude;
	private String pickup_latitude;
	private String dropoff_longitude;
	private String dropoff_latitude;
	private String payment_type;
	private String fare_amount;
	private String surcharge;
	private String mta_tax;
	private String tip_amount;
	private String tolls_amount;
	private String total_amount;
	private String dayOfWeek;
	private String pickup_Cell;
	private String dropoff_Cell;
	
	public String getMedallion() {
		return medallion;
	}
	public void setMedallion(String medallion) {
		this.medallion = medallion;
	}
	public String getHack_licence() {
		return hack_licence;
	}
	public void setHack_licence(String hack_licence) {
		this.hack_licence = hack_licence;
	}
	public String getPickup_datetime() {
		return pickup_datetime;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getPickup_Cell() {
		return pickup_Cell;
	}
	public void setPickup_Cell(String pickup_Cell) {
		this.pickup_Cell = pickup_Cell;
	}
	public String getDropoff_Cell() {
		return dropoff_Cell;
	}
	public void setDropoff_Cell(String dropoff_Cell) {
		this.dropoff_Cell = dropoff_Cell;
	}
	public void setPickup_datetime(String pickup_datetime) {
		this.pickup_datetime = pickup_datetime;
	}
	public String getDropoff_datetime() {
		return dropoff_datetime;
	}
	public void setDropoff_datetime(String dropoff_datetime) {
		this.dropoff_datetime = dropoff_datetime;
	}
	public String getTrip_tim_in_secs() {
		return trip_tim_in_secs;
	}
	public void setTrip_tim_in_secs(String trip_tim_in_secs) {
		this.trip_tim_in_secs = trip_tim_in_secs;
	}
	public String getTrip_distance() {
		return trip_distance;
	}
	public void setTrip_distance(String trip_distance) {
		this.trip_distance = trip_distance;
	}
	public String getPickup_longitude() {
		return pickup_longitude;
	}
	public void setPickup_longitude(String pickup_longitude) {
		this.pickup_longitude = pickup_longitude;
	}
	public String getPickup_latitude() {
		return pickup_latitude;
	}
	public void setPickup_latitude(String pickup_latitude) {
		this.pickup_latitude = pickup_latitude;
	}
	public String getDropoff_longitude() {
		return dropoff_longitude;
	}
	public void setDropoff_longitude(String dropoff_longitude) {
		this.dropoff_longitude = dropoff_longitude;
	}
	public String getDropoff_latitude() {
		return dropoff_latitude;
	}
	public void setDropoff_latitude(String dropoff_latitude) {
		this.dropoff_latitude = dropoff_latitude;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getFare_amount() {
		return fare_amount;
	}
	public void setFare_amount(String fare_amount) {
		this.fare_amount = fare_amount;
	}
	public String getSurcharge() {
		return surcharge;
	}
	public void setSurcharge(String surcharge) {
		this.surcharge = surcharge;
	}
	public String getMta_tax() {
		return mta_tax;
	}
	public void setMta_tax(String mta_tax) {
		this.mta_tax = mta_tax;
	}
	public String getTip_amount() {
		return tip_amount;
	}
	public void setTip_amount(String tip_amount) {
		this.tip_amount = tip_amount;
	}
	public String getTolls_amount() {
		return tolls_amount;
	}
	public void setTolls_amount(String tolls_amount) {
		this.tolls_amount = tolls_amount;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}
	@Override
	public String toString() {
		return "TaxiRide [medallion=" + medallion + ", hack_licence="
				+ hack_licence + ", pickup_datetime=" + pickup_datetime
				+ ", dropoff_datetime=" + dropoff_datetime
				+ ", trip_tim_in_secs=" + trip_tim_in_secs + ", trip_distance="
				+ trip_distance + ", pickup_longitude=" + pickup_longitude
				+ ", pickup_latitude=" + pickup_latitude
				+ ", dropoff_longitude=" + dropoff_longitude
				+ ", dropoff_latitude=" + dropoff_latitude + ", payment_type="
				+ payment_type + ", fare_amount=" + fare_amount
				+ ", surcharge=" + surcharge + ", mta_tax=" + mta_tax
				+ ", tip_amount=" + tip_amount + ", tolls_amount="
				+ tolls_amount + ", total_amount=" + total_amount
				+ ", dayOfWeek=" + dayOfWeek + ", pickup_Cell=" + pickup_Cell
				+ ", dropoff_Cell=" + dropoff_Cell + "]";
	}
	
	
}
