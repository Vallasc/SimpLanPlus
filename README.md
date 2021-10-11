# SimpLanPlus

Progetto AA 2020-21

1) Realizzare un sistema di ANALISI SEMANTICA per il linguaggio SimpLanPlus in allegato.
In  particolare, il sistema deve controllare

* variabili/funzioni non dichiarate
* variabili dichiarate piu` volte nello stesso ambiente 
* uso di variabili non inizializzate
* corretto uso dei puntatori
* parametri attuali non conformi ai parametri formali (inclusa la verifica sui 
  parametri passati per var)
* la correttezza dei tipi 

Inoltre deve controllare gli accessi a identificatori "cancellati" con particolare
attenzione all'aliasing implementando il sistema visto a lezione

In tutto il progetto, si assuma che i programmi possano essere RICORSIVI ma NON MUTUAMENTE RICORSIVI.

2) Definire un linguaggio bytecode per eseguire programmi in SimpLanPlus, scrivere 
la compilazione e implementare l'interprete. In particolare:

A. Il bytecode deve avere istruzioni per una macchina a pila che memorizza in un 
   apposito registro il valore dell'ultima istruzione calcolata [vedi slide delle lezioni]
B. Implementare l'interprete per il bytecode.
C. Compilare ed eseguire i programmi del linguaggio ad alto livello.

CONSEGNA: Occorre consegnare il pacchetto completo di tutte le fasi della compilazione.
E` possibile anche modificare i file dell' assegnamento precedente, se necessario.
Allegare anche una breve descrizione/analisi delle scelte fatte e su come 
istallare/eseguire in Eclipse il vostro compilatore. In caso di problemi, chiamero`
in aiuto qualcuno di voi.

Il pacchetto dovra` essere chiamato Cognome1_Cognome2.zip (dove il Cognome1, Cognome2,
etc sono i cognomi dei componenti del gruppo). Potete consegnarlo QUANDO VOLETE caricandolo 
su google drive e inviandomi l'indirizzo. Tenete
conto che io ho bisogno di qualche giorno per correggerlo e fare l'orale.



CODICI DA VERIFICARE:

^^int x ; ^int y = new int ; y^ = 1 ; x = new int ; x^ = y ; print x^^ ;
=====
 ^int x = new int ; x^ = 1 ; delete x ; y = x^ ;   // questo codice e` sbagliato!
=====
void f(^int x, ^int y){ delete x; delete y; } 
    //  cosa accade in {^int x = new ; f(x,x) ;} 
=====
void h(^int x, ^int y){ if (y^==0) delete x ; else { x^ = x^ - 1; h(y,x) ;} }
=====
void g(^int x, int y){ if (y==0) delete x ; else { x^ = x^ - 1; g(x,y) ; delete x ;} }
=====
int x = 1;
void f(int y){ if (y == 0) print(x); else  f(y-1) ; }
f(54) ;
=====
int u = 1 ;
void f(^int x, int n){ 
	if (n == 0) { print(x) ; delete x ; }
	else { ^int y = new ; y^ = x^ * n ; f(y,n-1) ; }	
}
f(u,6) ;
=====
void f(int m, int n){
	if (m>n) { print(m+n) ;}
	else { int x = 1 ; f(m+1,n+1) ; }
}
f(5,4) ;
// cosa succede se la invoco con f(4,5)?
=====





ite = if_then_else

OPERAZIONI SUI PUNTATORI:
i qui presenti giacomo, andrea e simone stabiliscono che:
1. è possibile fare equivalenza tra puntatori (e bisogna aggiungere una regola nel typecheck)
2. non è possibile fare operazioni aritmetiche tra i puntatori
3. non è possibile confrontare i puntatori con operatori di > o <
4. è naturalmente possibile fare confronti e operazioni aritmetiche tra puntatori dereferenziati

OPERAZIONE NEW:
1. non è possibile usare la new in un confronto (nonostante la grammatica lo permetta)

Bologna & Comunanza, lì 8 maggio 2021
                                                                                                                        Andrea
                                                                                                                        Simone
                                                                                                                        Vallasc


                                                                                                        
checkSemantics
- Aggiunge id a sb
- Controlla se id è già presente (dichiarazione multipla della stessa variabile nello stesso scope)
- Controlla se id è dichiarato (assegnamento/uso senza dichiarazione precedente)
- Controlla id nella chiamata e definizione

typeCheck
- Controlla gli assegnamenti
- Controlla i tipi delle espressioni (aritmetiche, booleane...)
- Controlla che i tipi parametri attuali = parametri formali (  f(int x) => f(true) ERRORE)

Esempi:
^^int x = new ^int;

// CORRETTO POSSO ALLOCARE + MEMORIA PER LO STESSO PUNTATORE
x^ = new int;
x^ = new int;

// SBAGLIATO NON POSSO ELIMINARE UN PUNTATORE NON ALLOCATO
delete x^;
delete x^;

//QUESTO È GIUSTO :-)
^int x = new int;
delete x;
x = new int;

//QUESTO È SBAGLIATO :-(
^int x = new int;
delete x;
^int x = new int;

TODO:
nell'errore della variabile già dichiarata, è fico se oltre a dire "var già dichiarata"
dice anche la riga e la colonna dove è presente la dichiarazione precedente
(per fare ciò occorre salvarsi riga e colonna nella Symbol Table quando si fa un inserimento)

NOTA: == e != sono polimorfi, mentre >=, <, etc. si usano solo per interi

