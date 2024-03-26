# ApplicationTCP_C-S

Questo progetto è un'applicazione di chat che consente a due utenti di comunicare tramite un server.

## Struttura del progetto

Il progetto è suddiviso in quattro classi, di cui 2 main:

### Nel package `chat.client`:

- `Client`: Rappresenta il client della chat che si connette al server e invia/riceve messaggi.
- `MClient`: Classe di avvio del client.

### Nel package `chat.server`:

- `Server`: Rappresenta il server della chat che accetta connessioni dai client e gestisce la comunicazione.
- `MServer`: Classe di avvio del server.

## Utilizzo

Per avviare la chat:

1. Avvia il server eseguendo la classe `MServer`.
2. Avvia due istanze del client eseguendo la classe `MClient`. Puoi farlo in due finestre del terminale o in due IDE separati.
3. Una volta che il client e il server sono in esecuzione, i due utenti possono digitare e inviare messaggi tra loro.

### Nota:

- Entrambi i client e il server devono essere eseguiti sulla stessa rete o macchina.
- Per terminare la chat, digita "exit" nella console di uno dei client. Questo chiuderà la connessione e terminerà il programma per entrambi i client e il server.

## Funzionamento

Una volta avviato il programma e stabilita la connessione tra client e server:

- I client possono inviare messaggi al server digitandoli sulla console. Questi messaggi verranno trasmessi a tutti gli altri client connessi.
- I client riceveranno i messaggi inviati dagli altri client e li visualizzeranno sulla loro console.
- Il server funge da intermediario per instradare i messaggi tra i vari client connessi.
- La chat terminerà quando un client digita "exit", il che causerà la chiusura delle connessioni per tutti i client e il server.
