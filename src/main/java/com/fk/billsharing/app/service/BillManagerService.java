package com.fk.billsharing.app.service;

import java.util.List;

import com.fk.billsharing.app.model.Transaction;

public interface BillManagerService {
	void createGroup(String groupName);

	void addPersons(String groupName, List<String> person);

	void addTransaction(String groupName, List<Transaction> transaction);

	float balanceOfPersonInGroup(String groupName, String personName);

	void balanceOfPersonsInGroup(String groupName);

	float balanceOfPersonAcrossAllGroup(String personName);
}
