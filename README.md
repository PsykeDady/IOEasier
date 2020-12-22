# IOEasier
Rendi più facile gestire l'IO con java!

`version 0.1`


## Usare le classi DB

iniziare la connessione (una tantum nel runtime) tramite `ConnessioneDB` :  

```java
if( ! ConnessioneDB.createInstance("nomeutente","psk")) {
	System.err.println("errore");
	System.exit(-1);
}
```

La connessione resta aperta ad oltranza fino a che non si usa il metodo `destroy` della medesima classe. 

ConnessioneDB è una classe statica che non richiede di portarsi un istanza, ma fornisce essa stessa un istanza di java.sql.Connection in singleton.

Alcune query, son fornite con `MySqlConnection`:  

- `creaDB(nomeDB)` : `boolean`
- `existDB(nomeDB)` : `boolean`
- `dropDB(nomeDB)` : `boolean`
- `esegui(queryCompleta)` : `boolean`
- `query(queryCompleta)` : `ResultSet`
- `listDB()` : `List<String>`

