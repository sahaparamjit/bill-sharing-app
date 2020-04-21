package com.fk.billsharing.app.model;

import java.util.HashMap;
import java.util.Map;

public class Person {
	private String name;
	private float totalDue;
	private Map<String, Float> associatedGroupDues;

	public Person(String name) {
		this.name = name;
		this.totalDue = 0;
		this.associatedGroupDues = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(float totalDue) {
		this.totalDue = totalDue;
	}

	public Map<String, Float> getAssociatedGroupDues() {
		return associatedGroupDues;
	}

	public void setAssociatedGroupDues(Map<String, Float> associatedGroupDues) {
		this.associatedGroupDues = associatedGroupDues;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", totalDue=" + totalDue + ", associatedGroups=" + associatedGroupDues + "]";
	}

}
