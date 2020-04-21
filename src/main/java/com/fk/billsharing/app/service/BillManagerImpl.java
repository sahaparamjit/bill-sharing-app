package com.fk.billsharing.app.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.fk.billsharing.app.model.Group;
import com.fk.billsharing.app.model.Person;
import com.fk.billsharing.app.model.Transaction;

public class BillManagerImpl implements BillManagerService {

	private Map<String, Group> billData;
	private Map<String, Person> personData;

	public BillManagerImpl(Map<String, Group> billData, Map<String, Person> personData) {
		this.billData = billData;
		this.personData = personData;
	}

	@Override
	public void createGroup(String groupName) {
		if (!billData.containsKey(groupName))
			billData.put(groupName, new Group(new HashSet<Person>(), 0));
	}

	@Override
	public void addPersons(String groupName, List<String> persons) {
		// validating the provided groupName, if incorrect it will a group with that
		// name
		if (!billData.containsKey(groupName))
			createGroup(groupName);
		Set<Person> setOfPersonInGroup = billData.get(groupName).getSetOfPersons();
		for (String person : persons) {
			Person personCreatedOrFetched = null;
			// Checking if the persons are already available in the system, if not then
			// create a new Person
			if (!personData.containsKey(person)) {
				personCreatedOrFetched = new Person(person);
				personData.put(person, personCreatedOrFetched);
			} else {
				personCreatedOrFetched = personData.get(person); // If the person is already present, then fetch the
																	// person
			}
			// Add the groupName by default due amount as 0
			personCreatedOrFetched.getAssociatedGroupDues().put(groupName, (float) 0);
			// Add the person in the setOfUsers in the Group Map
			setOfPersonInGroup.add(personCreatedOrFetched);
		}
	}

	/*
	 * Contribution is calculated based on the below formula Using this formula :
	 * 
	 * totalOverallDue = totalOverallDueInAllGroups + contribution (if any otherwise
	 * 0) - sharePerPerson
	 * 
	 * groupOverallDue = totalOverallDueInThatGroup + contribution (if any otherwise
	 * 0) - sharePerPerson
	 * 
	 * NOTE : sharePerPerson :- (sharePerPerson is only for the total transaction
	 * sum in that particular transaction call
	 */
	@Override
	public void addTransaction(String groupName, List<Transaction> transactions) {
		// validating the provided groupName, if incorrect it will throw a
		// RuntimeException
		if (!billData.containsKey(groupName))
			throw new RuntimeException("Group Not Found");
		Group group = billData.get(groupName);

		Set<Person> persons = group.getSetOfPersons(); // Set of Persons in that group

		float groupTotal = group.getTotalAmountDue();
		float transactionTotal = 0;
		Set<String> personWithTransaction = new HashSet<>();
		for (Transaction transaction : transactions) {
			// validating the provided person name in the transaction, if incorrect then it
			// will skip that entry
			if (personData.containsKey(transaction.getPersonName())) {
				transactionTotal += transaction.getAmount();
				personWithTransaction.add(transaction.getPersonName()); // Creating a list of users having the
																		// transactions
			}
		}
		float sharePerPerson = transactionTotal / persons.size(); // Total share for the current set of transactions

		for (Person person : persons) {
			float totalOverallDue = 0; // Total Overall Due is the total due for the person in all groups
			float groupOverallDue = 0; // Group Overall Due is the total due for the person in the current group
			if (personWithTransaction.contains(person.getName())) {
				for (Transaction transaction : transactions) {
					// Checking if the current transaction belongs to the current person
					if (person.getName().equals(transaction.getPersonName())) {

						totalOverallDue = person.getTotalDue() - sharePerPerson + transaction.getAmount();
						groupOverallDue = person.getAssociatedGroupDues().get(groupName) + transaction.getAmount()
								- sharePerPerson;
						person.setTotalDue(totalOverallDue);
					}
				}
			} else {
				// If the person didn't contribute to the current transaction, then his due
				// calculation here
				totalOverallDue = person.getTotalDue() - sharePerPerson;
				groupOverallDue = person.getAssociatedGroupDues().get(groupName) - sharePerPerson;
				person.setTotalDue(totalOverallDue);
			}
			// Update the overall due for this group
			person.getAssociatedGroupDues().put(groupName, groupOverallDue);
		}
		// Update the group overall due
		groupTotal += transactionTotal;
		group.setTotalAmountDue(groupTotal);
	}

	@Override
	public float balanceOfPersonInGroup(String groupName, String personName) {
		// If wrong group is supplied then, throw a runtime error
		if (!billData.containsKey(groupName))
			throw new RuntimeException("Group Not Found");
		float data = 0;

		Person person = personData.get(personName); // get the person from personData Map and check for the association
													// of the person with the provided group
		if (person.getAssociatedGroupDues().containsKey(groupName))
			data = person.getAssociatedGroupDues().get(groupName);

		return data;
	}

	@Override
	public void balanceOfPersonsInGroup(String groupName) {
		// validating the provided groupName, if incorrect it will throw a
		// RuntimeException
		if (!billData.containsKey(groupName))
			throw new RuntimeException("No group found with the name : " + groupName);
		Group group = billData.get(groupName);
		Set<Person> setOfPersons = group.getSetOfPersons();
		// Iterate through all the persons in that group and print on console
		for (Person person : setOfPersons) {
			System.out.println(person.getName() + " : " + person.getAssociatedGroupDues().get(groupName));
		}
	}

	@Override
	public float balanceOfPersonAcrossAllGroup(String personName) {
		float personBalance = 0;
		// Check for the person in the personData, if not found throw a runtime
		// exception
		if (!personData.containsKey(personName)) {
			throw new RuntimeException("No User found : " + personName);
		}
		Person person = personData.get(personName);
		personBalance = person.getTotalDue();
		return personBalance;
	}

}
