
package arbolesbinarios;


public class Nodo {
  
    private int Dato;
    private Nodo Izq;
    private Nodo Der;

    Nodo() {
    }

    Nodo(int dato) {
        this.Dato = dato;
        this.Izq = null;
        this.Der = null;
    }

    Nodo(int dato, Nodo izq) {
        this.Dato = dato;
        this.Izq = izq;
        this.Der = null;
    }

    Nodo(int dato, Nodo izq, Nodo der) {
        this.Dato = dato;
        this.Izq = izq;
        this.Der = der;
    }

    public int getDato() {
        return Dato;
    }

    public Nodo getIzq() {
        return Izq;
    }

    public Nodo getDer() {
        return Der;
    }

    public void setDato(int Dato) {
        this.Dato = Dato;
    }

    public void setIzq(Nodo Izq) {
        this.Izq = Izq;
    }

    public void setDer(Nodo Der) {
        this.Der = Der;
    }

}

