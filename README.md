# SimpLanPlus
Progetto di compilatori e interpreti AA 2020-21

## Descrizione
1. Realizzare un sistema di ANALISI SEMANTICA per il linguaggio SimpLanPlus.
In  particolare, il sistema deve controllare

	* variabili/funzioni non dichiarate
	* variabili dichiarate piu` volte nello stesso ambiente 
	* uso di variabili non inizializzate
	* corretto uso dei puntatori
	* parametri attuali non conformi ai parametri formali (inclusa la verifica sui parametri passati per var)
	* la correttezza dei tipi 

	Inoltre deve controllare gli accessi a identificatori "cancellati" con particolare
	attenzione all'aliasing implementando il sistema visto a lezione

	In tutto il progetto, si assuma che i programmi possano essere ricorsivi ma non mutuamente ricorsivi.

2) Definire un linguaggio bytecode per eseguire programmi in SimpLanPlus, scrivere 
la compilazione e implementare l'interprete. In particolare:
	* Il bytecode deve avere istruzioni per una macchina a pila che memorizza in un apposito registro il valore dell'ultima istruzione calcolata
	* Implementare l'interprete per il bytecode.
	* Compilare ed eseguire i programmi del linguaggio ad alto livello.


Vincoli:
1. E' possibile fare equivalenza tra puntatori
2. Non è possibile fare operazioni aritmetiche tra i puntatori
3. Non è possibile confrontare i puntatori con operatori di > o <
4. Le funzioni possono ritonare solo valori di tipo intero e booleano
5. Ogni volta che vengono utilizzate { } viene aperto un nuovo scope
                                                                                                      
## Come eseguire il compilatore
Prerequesiti:
* Java version >= 1.8
* Linux distribution or MacOS(no Windows)


```sh
$> ./run.sh programma.slp
```

```sh
$> ./run.sh -h

SimpLanPlus compiler
	--ast, -a		Print abstract syntax three
	--msize, -m		Specify the total memory size
	--print, -p		Print memory at the end of execution
	--debug, -p		Print memory ad registers for each instruction
	--comments, -c	Print comments on assembly code
	--out, -o		Specify out filename (default out)
```
