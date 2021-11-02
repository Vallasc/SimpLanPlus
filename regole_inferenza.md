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

{
    bool topolino() {
        int x = 3;          void
        bool y = true;      void      
        y = false;          void

        int pippo(int coca){        void
            int d;                      void
            return 3;                   int
            {
                return 4;                   int
            }
        }

        if( x == 3) {
            return true;    bool
        } else {
            print("niente");    void
        }                       min(bool, void) -> void
        return false;
    }
    x = topolino()
    topolino()
    if( topolino() == false){

    }
    return;
}

int pippo () {

    int y = 5       void

    if (true){
        return 4;
    }               int

     ----

    if (5 == 5){
        return true;
    }               bool

     ----

    return 10;      int

}

typecheck function:
            - typecheck tipo funzione
            - typecheck blocco
    
            - se "tipo funzione == blocco" --> ok


{

}