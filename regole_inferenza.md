# Regole di inferenza tipi

bool: tipo booleano
int: tipo intero
^: rappresenta un puntatore a bool, int o ^
void: tipo vuoto
function: tipo delle funzioni

n in Integers
-------------------[Integer]
\Gamma |- n : int

n in Boolean
-------------------[Boolean]
\Gamma |- n : bool


x not-in dom(\Gamma)
-----------------------------------------[Dec]
\Gamma |- int x; : void, \Gamma[x -> int]

t = \Gamma(x)
--------------------------------------------[Dec]
\Gamma |- return x; : void, \Gamma[x -> int]




type(int x;) = void
type(x = 3) = void
type(x == 3) = bool
type(return x) = int

type({
    int x;
    x = 4;
}) = void

type({
    int x;
    x = 4;
    return x;
}) = int