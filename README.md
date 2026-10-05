# SYPGruppe3Project26-27

## Mitglieder
+ Niklas Abl
+ Pascal Schnedl
+ David Gasparin
+ Martin Knöbelreiter
+ Christian Zaloznik
+ Denise Reinthaler
+ Philip Hammer

## Ideen
+ 2D-Game mit Score – 3 Stimmen
+ Workoutplaner mit Streak
+ Habit-Tracker mit Streak – 1 Stimme
+ Kalorientracker
+ Ausgabentracker (Finanzen) – 3 Stimmen
+ Routenplaner / Fahrtentracker für Arbeitsspritbezahlung

**Das Glücksrad hat entschieden:**  
Ausgabentracker (Finanzen)

## Ausgabentracker (Finanzen) – MVP
+ Eine minimale GUI, in der man seine Einnahmen und Ausgaben einsehen kann
+ Eine erste Version einer Datenbank
+ Die Funktion, eine Zahlung einzutragen und wieder zu löschen

Einzelne Planungsschritte sind in Mendix etwas näher ausgeführt.

## Geplante Schnittstellen

### Service-Schicht

#### User Service
- getAllUsers()
- addUser(User user)
- removeUser(User user)
- getUserById(long id)
- getUserByEmail(String email)

#### Transaction-Service
- getAllTransactions()
- getTransactionsInTimespan(LocalDate from, LocalDate to)
- addTransaction(Transaction transaction)
- removeTransaction(Transaction transaction)
- getTransactionById(long id)
- getTransactionByName(String name)
- getTransactionsAllByType(TransactionType type)

#### Account-Service
- getAllAccounts()
- addAccount(Account account)
- removeAccount(Account account)
- getAccountById(long id)
