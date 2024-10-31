/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesbinarios;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
/**
 *
 * @author user
 */
public class ArbolB {
    
    BufferedReader datos = new BufferedReader(new InputStreamReader(System.in));
    Nodo raiz;

    ArbolB() {
        this.raiz = null;
    }

    //METODO PARA AGREGAR UN NODO EN EL ARBOL
    public void AgregarNodo(int dato) {
        Nodo nuevo = new Nodo(dato); //Crear nodo con valor=dato. Izq y der son nulos
        if (raiz == null) {//Si el arbol no existe aun se agrega el nodo como raiz
            raiz = nuevo;
        } else {
            Nodo aux = raiz;
            Nodo padre;

            while (true) {
                padre = aux;
                if (dato < aux.getDato()) {//Si es menor va a la izquierda
                    aux = aux.getIzq();
                    if (aux == null) {
                        padre.setIzq(nuevo);//Nuevo nodo a la izquierda.
                        return;//Sale de while
                    }
                } else {//Si es mayor va a la derecha
                    aux = aux.getDer();
                    if (aux == null) {
                        padre.setDer(nuevo);
                        return;
                    }
                }
            }
        }
    }

    public void MostrarArbol(Nodo raiz, int cont) {
        if (raiz != null) {
            MostrarArbol(raiz.getDer(), cont += 2);
            for (int i = 0; i < cont; i++) {
                System.out.print(" ");
            }
            System.out.println(raiz.getDato());
            MostrarArbol(raiz.getIzq(), cont += 2);

        } else {
        }

    }

    public boolean EstaVacio() {
        return raiz == null;//Return true si es nulo, sino false
    }

    public void InOrden(Nodo raiz) {//Izq>Raiz>Der
        if (raiz != null) {
            InOrden(raiz.getIzq());                     //Lee Izquierda
            System.out.print(raiz.getDato() + " --> "); //Lee Raiz
            InOrden(raiz.getDer());                     //Lee Derecha
        }
    }

    public void PreOrden(Nodo raiz) {//Raiz>Izq>Der
        if (raiz != null) {
            System.out.print(raiz.getDato() + " --> "); //Lee Raiz
            InOrden(raiz.getIzq());                     //Lee Izquierda
            InOrden(raiz.getDer());                     //Lee Derecha
        }
    }

    public void PosOrden(Nodo raiz) {
        if (raiz != null) {
            InOrden(raiz.getIzq());                     //Lee Izquierda
            InOrden(raiz.getDer());                     //Lee Derecha
            System.out.print(raiz.getDato() + " --> "); //Lee Raiz

        }
    }

    public Nodo BuscarNodo(int datoB) {
        Nodo aux = raiz;
        while (aux.getDato() != datoB) {
            if (datoB < aux.getDato()) {
                aux = aux.getIzq();
            } else {
                aux = aux.getDer();
            }
            if (aux == null) {//Si NO encontro nada retorna null
                return null;
            }
        }
        return aux;//Retona valor de aux si existe el valor
    }

    public boolean EliminarNodo(int valor) {
        Nodo aux = raiz;
        Nodo padre = raiz;
        boolean nIzq = true;//Variable 

        while (aux.getDato() != valor) {
            padre = aux;
            if (valor < aux.getDato()) {//Nodo por el lado izquierdo
                aux = aux.getIzq();
                nIzq = true;
            } else {//Nodo por el lado derecho
                aux = aux.getDer();
                nIzq = false;
            }
            if (aux == null) {//No se encontro el nodo
                return false;
            }
        }

        if (aux.getIzq() == null && aux.getDer() == null) {
            if (aux == raiz) {//raiz sin ramas
                raiz = null;
            } else if (nIzq) {//No en el lado izquierdo
                padre.setIzq(null);
            } else {
                padre.setDer(null);
            }
        } else if (aux.getDer() == null) {//No es hoja, no hay nodo derecho
            if (aux == raiz) {
                raiz = aux.getIzq();
            } else if (nIzq) {
                padre.setIzq(aux.getIzq());
            } else {
                padre.setDer(aux.getIzq());
            }
        } else if (aux.getIzq() == null) {//No es hoja, no hay nodo izquierdo
            if (aux == raiz) {
                raiz = aux.getDer();
            } else if (nIzq) {
                padre.setIzq(aux.getDer());
            } else {
                padre.setDer(aux.getIzq());
            }
        } else {//No esta en ninguno de los anteriores, a fuerzas entra por aqui
            Nodo reemplazo = obtenerNodoReemplazo(aux);
            if (aux == raiz) {
                raiz = reemplazo;
            } else if (nIzq) {
                padre.setIzq(reemplazo);
            } else {
                padre.setDer(reemplazo);
            }
            reemplazo.setIzq(aux.getIzq());
        }
        return true;//Encontro el nodo a eliminar
    }

    //Metodo para devolver el nodo que sustituye al nodo eliminado
    public Nodo obtenerNodoReemplazo(Nodo NodoRem) {
        Nodo reemplazarPadre = NodoRem;
        Nodo reemplazo = NodoRem;
        Nodo aux = reemplazo.getDer();

        while (aux != null) {
            reemplazarPadre = reemplazo;
            reemplazo = aux;
            aux = aux.getIzq();
        }
        if (reemplazo != NodoRem.getDer()) {
            reemplazarPadre.setIzq(reemplazo.getDer());
            reemplazo.setDer(NodoRem.getDer());
        }
        JOptionPane.showMessageDialog(null, "El nodo reemplazado es: " + reemplazo.getDato());
        return reemplazo;
    }

    public Nodo Eliminar(Nodo aux, int dato) {
        if (aux == null) {//Arbol vacio
            JOptionPane.showMessageDialog(null, "Arbol Vacio");
        } else if (dato < aux.getDato()) {//Se mueve al hijo izq
            Nodo izq = Eliminar(aux.getIzq(), dato);
            aux.setIzq(izq);
        } else if (dato > aux.getDato()) {//Se mueve al hijo der
            Nodo der = Eliminar(aux.getDer(), dato);
            aux.setDer(der);
        } else {
            Nodo p = aux;
            if (p.getDer() == null) {
                aux = p.getIzq();
            } else if (p.getIzq() == null) {
                aux = p.getDer();
            } else {
                p = Cambiar(p);
            }
        }
        return aux;
    }

    public Nodo Cambiar(Nodo aux) {
        Nodo p = aux;
        Nodo a = aux.getIzq();
        while (a.getDer() != null) {
            p = a;
            a = a.getDer();
        }
        aux.setDato(a.getDato());
        if (p == aux) {
            p.setIzq(a.getIzq());
        } else {
            p.setDer(a.getIzq());
        }
        return a;
    }
}
