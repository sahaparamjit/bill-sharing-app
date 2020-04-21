package com.fk.billsharing.app.model;

public class Transaction {
	private String personName;
	private float amount;

	public Transaction(String person, float amount) {
		super();
		this.personName = person;
		this.amount = amount;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [personName=" + personName + ", amount=" + amount + "]";
	}

}
