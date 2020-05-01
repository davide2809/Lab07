package it.polito.tdp.poweroutages.model;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;


public class PowerOutages {
	
	private int key;
	private int nercId;
	private int customersAffected;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventFinished;
	
	private long durata;
	
	
	
	/**
	 * @param key
	 * @param nercId
	 * @param customersAffected
	 * @param dateEventBegan
	 * @param dateEventFinished
	 */
	public PowerOutages(int key, int nercId, int customersAffected, Timestamp dateEventBegan,
			Timestamp dateEventFinished) {
		this.key = key;
		this.nercId = nercId;
		this.customersAffected = customersAffected;
		this.dateEventBegan = dateEventBegan.toLocalDateTime();
		this.dateEventFinished = dateEventFinished.toLocalDateTime();
//		In Ore
		Duration duration= Duration.between(this.dateEventBegan, this.dateEventFinished);
		this.durata= duration.toHours();
	}
	
	
	
	
	public long getDurata() {
		return durata;
	}





	public int getKey() {
		return key;
	}


	public void setKey(int key) {
		this.key = key;
	}


	public int getNercId() {
		return nercId;
	}


	public void setNercId(int nercId) {
		this.nercId = nercId;
	}


	public int getCustomersAffected() {
		return customersAffected;
	}


	public void setCustomersAffected(int customersAffected) {
		this.customersAffected = customersAffected;
	}


	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}


	public void setDateEventBegan(LocalDateTime dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}


	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}


	public void setDateEventFinished(LocalDateTime dateEventFinished) {
		this.dateEventFinished = dateEventFinished;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + key;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (key != other.key)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "[key=" + key + ", nercId=" + nercId + ", customersAffected=" + customersAffected+ ", dateEventBegan=" + dateEventBegan + ", dateEventFinished=" + dateEventFinished + "] \n";
	}
	
	
	
	
	
	
}
