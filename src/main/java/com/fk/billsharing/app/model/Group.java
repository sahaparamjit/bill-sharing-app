package com.fk.billsharing.app.model;

import java.util.Set;

public class Group {
	private Set<Person> setOfPersons;
	private float totalAmountDue;

	public Group(Set<Person> setOfPersons, float totalAmountDue) {
		super();
		this.setOfPersons = setOfPersons;
		this.totalAmountDue = totalAmountDue;
	}

	public Set<Person> getSetOfPersons() {
		return setOfPersons;
	}

	public void setSetOfPersons(Set<Person> setOfPersons) {
		this.setOfPersons = setOfPersons;
	}

	public float getTotalAmountDue() {
		return totalAmountDue;
	}

	public void setTotalAmountDue(float totalAmountDue) {
		this.totalAmountDue = totalAmountDue;
	}

	@Override
	public String toString() {
		return "Group [setOfPersons=" + setOfPersons + ", totalAmountDue=" + totalAmountDue + "]";
	}
}
