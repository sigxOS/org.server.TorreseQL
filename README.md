# Torrese Query Language

Ecco una versione ulteriormente perfezionata del file Markdown. Ho aggiunto dettagli estetici e funzionali per renderlo più accattivante, professionale e chiaro, includendo una migliore separazione visiva, un linguaggio raffinato e miglioramenti alla formattazione.

---

# TorreseQL

![TorreseQL Logo](https://via.placeholder.com/300x100?text=TorreseQL)

**TorreseQL** è un dialetto SQL innovativo che trasforma il linguaggio delle query in un'esperienza unica e intuitiva, ispirata al dialetto torrese. Perfetto per chi desidera un approccio più naturale e divertente alla gestione dei dati!

---

## **Come Iniziare**

Puoi utilizzare TorreseQL in due modalità principali:

### 🛠 **Come Libreria Java**

1. **Scarica** l'ultima versione del file JAR dalla [pagina release](#).
2. **Importa** il JAR nel tuo progetto Java insieme al driver del database (es. MySQL, PostgreSQL).
3. Usa le classi di TorreseQL per scrivere query e interagire con il database.

Se incontri problemi, TorreseQL lancia un'eccezione dedicata:  
```java
iDontKnow();
```

### 🖥 **Come Client Standalone**

1. **Scarica** il client standalone dal repository ufficiale.
2. **Avvia** il client con il comando:
   ```bash
   java -cp TorreseQL:<JDBC> org.server.TorreseQL.TorreseShell
   ```
3. Connettiti al database con la stringa JDBC e inizia a scrivere le tue query in puro TorreseQL! 🎉

---

## **Le Parole Chiave di TorreseQL**

Ecco una tabella con tutte le keyword disponibili, per passare facilmente da SQL standard al dialetto torrese.

### 🌟 **Parole Chiave di Base**

| **TorreseQL**  | **SQL Standard** | **Descrizione**                  |
|----------------|------------------|----------------------------------|
| `VOGLJ`        | SELECT           | Seleziona dati.                  |
| `AMMESC`       | UPDATE           | Aggiorna record esistenti.       |
| `NGAS`, `IND`  | INSERT INTO      | Inserisce nuovi dati.            |
| `LIEV A MIEZZ` | DELETE           | Rimuove dati esistenti.          |
| `MMIEZ A`      | FROM             | Specifica la tabella di origine. |
| `ADDO'`        | WHERE            | Filtra i dati.                   |
| `PO`           | AND              | Operatore logico E.              |
| `O`            | OR               | Operatore logico O.              |
| `NIENT`        | NULL             | Valore nullo.                    |
| `APPARTEN`     | IS               | Condizione IS.                   |
| `NUN APPARTEN` | IS NOT           | Condizione IS NOT.               |
| `TUTT COS`     | *                | Seleziona tutte le colonne.      |

---

### 🔧 **Parole Chiave Avanzate**

| **TorreseQL**      | **SQL Standard**  | **Descrizione**                 |
|--------------------|-------------------|---------------------------------|
| `AMMESC TUTT COS`  | JOIN              | Unisce due o più tabelle.       |
| `ITTAMM I MMAN`    | BEGIN TRANSACTION | Inizia una transazione.         |
| `JAMMUNCENN A CAS` | COMMIT            | Conferma una transazione.       |
| `CAZZ`             | ROLLBACK          | Annulla una transazione.        |
| `UGUAL`            | =                 | Operatore di uguaglianza.       |
| `CHEST`            | VALUES            | Valori per l'operazione INSERT. |
| `APPARAMM`         | SET               | Imposta valori per UPDATE.      |

---

## **Operatori Supportati**

TorreseQL include tutti i principali operatori logici e di confronto:

| **Operatore** | **Significato**   |
|---------------|-------------------|
| `>`           | Maggiore di       |
| `<`           | Minore di         |
| `=`           | Uguaglianza       |
| `!=`, `<>`    | Diverso da        |
| `>=`          | Maggiore o uguale |
| `<=`          | Minore o uguale   |

---

## **Esempi Pratici di Query**

### 📌 Recupero Dati (SELECT)

**TorreseQL**:
```tql
VOGLJ TUTT COS MMIEZ A utenti ADDO' eta > 18 PO citta UGUAL "Napoli"
```

**SQL**:
```sql
SELECT * FROM utenti WHERE eta > 18 AND citta = 'Napoli';
```

---

### 📌 Inserimento Dati (INSERT)

**TorreseQL**:
```tql
NGAS utenti (nome, eta) CHEST "Mario", 25
```

**SQL**:
```sql
INSERT INTO utenti (nome, eta) VALUES ('Mario', 25);
```

---

### 📌 Aggiornamento Dati (UPDATE)

**TorreseQL**:
```tql
AMMESC utenti APPARAMM eta UGUAL 30 ADDO' nome UGUAL "Luigi"
```

**SQL**:
```sql
UPDATE utenti SET eta = 30 WHERE nome = 'Luigi';
```

---

### 📌 Eliminazione Dati (DELETE)

**TorreseQL**:
```tql
LIEV A MIEZZ utenti ADDO' eta < 18
```

**SQL Standard**:
```sql
DELETE FROM utenti WHERE eta < 18;
```

---

## **Transazioni**

TorreseQL supporta pienamente le transazioni ACID con comandi intuitivi:

- **Inizio di una transazione**:
   ```tql
   ITTAMM I MMAN
   ```

- **Conferma di una transazione**:
   ```tql
   JAMMUNCENN A CAS
   ```

- **Annullamento di una transazione**:
   ```tql
   CAZZ
   ```

---

## **Vantaggi di TorreseQL**

- **Intuitivo**: TorreseQL è progettato per essere facile da leggere e scrivere.
- **Divertente**: Impara SQL con un tocco di ironia!
- **Compatibile**: Funziona con i principali database relazionali (MySQL, PostgreSQL, etc.).
- **Flessibile**: Usa TorreseQL come libreria Java o client standalone.

---

## **Contatti**

📧 Email: [Alessio](mailto:alessio.attilio@aleat.it) <br />
🌐 Sito web: [aleat.it/blog/torrese-ql](#) <br />
📖 Documentazione completa: [Documentazione](aleat.it/blog/torrese-ql#docs) <br />

---

## **Licenza**

TorreseQL è distribuito sotto licenza **MIT**, consentendo l'utilizzo per scopi personali e commerciali. Consulta la licenza per ulteriori dettagli.

> "Cu’ ll’arte e cu’ ‘a parte, tutte e porte so’ aperte!"
