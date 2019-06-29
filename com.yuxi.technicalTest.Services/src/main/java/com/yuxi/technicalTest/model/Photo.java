package com.yuxi.technicalTest.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author duvao
 *
 *Model for a photo resource.
 *
 */
public class Photo implements Comparable<Photo>{

	@JsonIgnore
	private String name;
	
	private String newName;
	
	@JsonIgnore
	private String extension;
	
	@JsonIgnore
	private LocalDateTime takenOn;
	
	@JsonIgnore
	private String location;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public LocalDateTime getTakenOn() {
		return takenOn;
	}
	public void setTakenOn(LocalDateTime takenOn) {
		this.takenOn = takenOn;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int compareTo(Photo o) {
		return takenOn.compareTo(o.takenOn);
	}
		
	@JsonProperty(value = "oldName")
	public String getFullName() {
		String nameFormat = "%s.%s";
		return String.format(nameFormat, name, extension);
	}
	
}
