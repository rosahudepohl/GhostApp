## Ghost ##

**Ghost Words** is an addictive game of word strategy and tactics where you can use the power of vocabulary to outsmart & outplay friends & foes alike. 

## Ghost Controllers ##

#### MainActivity.java ####

**Bestaat uit**:

2 buttons die beide gebruik maken van een onClickListener method om een nieuwe activity te openen:
- Play Game --> NewGame.java
- Highscores --> Highscores.java

De language setting die kan worden aangepast via de buttons *EN* en *NL* die gebruik maken van een
onClickListener method die ervoor zorgt dat er bij PlayGame.java gebruik wordt gemaakt van een woordenlijst in de geselecteerde taal door de juiste wordlist file mee te geven met behulp van Bundle().

![alt text](https://github.com/rosahudepohl/GhostApp/blob/master/Sketches/New%20Mockup%201.png "MainActivity")

##### NewGame.java #####

**Bestaat uit**:

2 text input fields voor Player 1 en Player 2. Nieuwe users worden in de User class opgeslagen. Eerdere users worden getoond in een drop down menu (spinner) met behulp van een SpinnerAdapter. Met de setOnItemSelectedListener wordt de gekozen username toegewezen aan een van de Players.

1 Lets Play button die gebruik maakt van een onClickListener method om een nieuwe activity te openen:
- Lets Play --> PlayGame.java waarbij de usernames van Player 1 en Player 2 moeten worden meegegeven met behulp van Bundle().


![alt text](https://github.com/rosahudepohl/GhostApp/blob/master/Sketches/New%20Mockup%202.png "NewGame")

##### Highscores.java #####

**Bestaat uit**:

Een Back button die gebruik maakt van een onCLickListener method om een nieuwe activity te openen:
- Back --> MainActivity.java

Een Listview van Highscores. Deze Highscores worden gelezen uit een ArrayList die wordt geupdate in PlayGame.java 





![alt text](https://github.com/rosahudepohl/GhostApp/blob/master/Sketches/New%20Mockup%203.png "Highscores")

##### PlayGame #####

**Bestaat uit**:

- Extra functies op keyboard moeten worden uitgeschakeld (?).........

![alt text](https://github.com/rosahudepohl/GhostApp/blob/master/Sketches/New%20Mockup%204.png "PlayGame")

![alt text](https://github.com/rosahudepohl/GhostApp/blob/master/Sketches/New%20Mockup%205.png "PlayGame1")

##### GameOver #####

![alt text](https://github.com/rosahudepohl/GhostApp/blob/master/Sketches/New%20Mockup%206.png "GameOver")
