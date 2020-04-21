package com.fk.billsharing.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fk.billsharing.app.model.Group;
import com.fk.billsharing.app.model.Person;
import com.fk.billsharing.app.model.Transaction;
import com.fk.billsharing.app.service.BillManagerImpl;
import com.fk.billsharing.app.service.BillManagerService;

public class BillSharingAppApplication {

	private BillManagerService billManagerService;

	public BillSharingAppApplication(BillManagerService billManagerService) {
		this.billManagerService = billManagerService;
	}

	public static void main(String[] args) {
		// This is the inMemory Data storing all the groups and its
		Map<String, Group> data = new HashMap<>();
		Map<String, Person> personData = new HashMap<>();

		// Bill Split Service
		BillManagerService billManagerService = new BillManagerImpl(data, personData);

		// Bill Split App
		BillSharingAppApplication app = new BillSharingAppApplication(billManagerService);
		
		// Building up the inputs for testing 

		List<String> personList1 = Arrays.asList(new String[] { "Ashish", "Devbrat", "Anmol", "Abhishek" });

		List<String> personList2 = Arrays.asList(new String[] { "Rohit", "Ashish" });

		List<String> personList3 = Arrays.asList(new String[] { "Param", "Mannu", "Dipu" });

		List<Transaction> transaction1 = Arrays
				.asList(new Transaction[] { new Transaction("Ashish", 40), new Transaction("Devbrat", 160) });

		List<Transaction> transaction2 = Arrays.asList(new Transaction[] { new Transaction("Ashish", 30),
				new Transaction("Abhishek", 60), new Transaction("Anmol", 110) });

		List<Transaction> transaction3 = Arrays
				.asList(new Transaction[] { new Transaction("Ashish", 100), new Transaction("Rohit", 60) });

		List<Transaction> transaction4 = Arrays.asList(new Transaction[] { new Transaction("Param", 1000),
				new Transaction("Dipu", 400), new Transaction("Mannu", 300) });

		// Testing the service with the below service calls.
		
		billManagerService.createGroup("FK_PARTY");
		billManagerService.createGroup("SCHOOL_FRIENDS");
		billManagerService.createGroup("ROOM001");
		billManagerService.createGroup("COVIDFUNDRAISER");
		billManagerService.addPersons("ROOM001", personList3);
		billManagerService.addTransaction("ROOM001", transaction4);
		billManagerService.balanceOfPersonsInGroup("ROOM001");
		System.out.println("Param due in all groups :" +billManagerService.balanceOfPersonAcrossAllGroup("Param"));
		System.out.println();
		System.out.println();
		// Below examples were given in the problem statement
		
		billManagerService.addPersons("FK_PARTY", personList1);
		billManagerService.addPersons("SCHOOL_FRIENDS", personList2);
		billManagerService.addTransaction("FK_PARTY", transaction1);
		billManagerService.addTransaction("FK_PARTY", transaction2);
		billManagerService.addTransaction("SCHOOL_FRIENDS", transaction3);
		billManagerService.balanceOfPersonsInGroup("FK_PARTY");
		billManagerService.balanceOfPersonsInGroup("SCHOOL_FRIENDS");
		System.out.println("Ashish due in all groups :" + billManagerService.balanceOfPersonAcrossAllGroup("Ashish"));
		System.out.println("Rohit due in all groups :" + billManagerService.balanceOfPersonAcrossAllGroup("Rohit"));
	}

}
