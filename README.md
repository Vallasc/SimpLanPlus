# SimpLanPlus

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
- Aggiunnge id a sb
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



