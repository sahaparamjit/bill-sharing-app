## Teksystem | Flipkart Coding assignment

Building the project
```mvn clean package```

App will be running on the main Driver Program. Only one custom scenario and all the scenarios given in the assigment sheet has been added .

>> Test Scenaio added
1 Create group ("FLIPKART_PARTY)
2 Add Person ("FLIPKART_PARTY", ["Ashish", "Devrat", "Anmol", "Abhishek"])
3 Create group ("SCHOOL_FRIENDS)
4 Add Persons ("SCHOOL_FRIENDS", ["Ashish", "Rohit"])
5 Add Transaction ("FLIPKART_PARTY", [("Ashish", 40),  ("Devrat", 160)])
6 Add Transaction ("FLIPKART_PARTY", [("Ashish", 40), ("Anmol", 110), ("Abhishek", 60)])
7 Add Transaction ("SCHOOL_FRIENDS", [("Ashish", 40), ("Rohit", 60)])
8 Balance of Person in group ("FLIPKART_PARTY", "Ashish")
9 Balance of Persons in group ("FLIPKART_PARTY")
10 Balance of Person across all groups ("Ashish")

Run the application with the below command
``java -jar target/bill-sharing-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar``