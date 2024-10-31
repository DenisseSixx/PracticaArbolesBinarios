package arbolesbinarios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

public class ArbolesBinarios {

    public static BufferedReader datos = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        ArbolB arbol = new ArbolB();
        int op;
        int dato;
        int cont = 0;
        int datoN;

        try {
            do {
                op = Integer.parseInt(JOptionPane.showInputDialog(null, "1: Agregar Nodo"
                        + "\n2: Mostrar Arbol Completo"
                        + "\n3: Recorrido en IN Orden"
                        + "\n4: Recorrido en PRE Orden"
                        + "\n5: Recorrido en POS Orden"
                        + "\n6: Buscar Nodo"
                        + "\n7: Eliminar Nodo"
                        + "\n8: SALIR"
                        + "\nElige una opcion:", "ARBOLES BINARIOS", JOptionPane.QUESTION_MESSAGE));

                switch (op) {
                    case 1:
                        dato = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce el valor para el nodo", "Agregando valor", JOptionPane.QUESTION_MESSAGE));
                        arbol.AgregarNodo(dato);
                        break;
                    case 2:
                        arbol.MostrarArbol(arbol.raiz, cont);
                        System.out.println("--------------------");
                        break;
                    case 3:
                        if (!arbol.EstaVacio()) {
                            System.out.println("IN-ORDEN:");
                            arbol.InOrden(arbol.raiz);
                            System.out.println("");
                            System.out.println("--------------------");
                        } else {
                            JOptionPane.showMessageDialog(null, "ARBOL VACIO");
                        }
                        break;
                    case 4:
                        if (!arbol.EstaVacio()) {
                            System.out.println("PRE-ORDEN:");
                            arbol.PreOrden(arbol.raiz);
                            System.out.println("");
                            System.out.println("--------------------");
                        }
                        break;
                    case 5:
                        if (!arbol.EstaVacio()) {
                            System.out.println("POS-ORDEN:");
                            arbol.PosOrden(arbol.raiz);
                            System.out.println("");
                            System.out.println("--------------------");
                        }
                        break;
                    case 6:
                        if (!arbol.EstaVacio()) {
                            datoN = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce el Valor del Nodo a Buscar", "Buscando Nodo", JOptionPane.QUESTION_MESSAGE));
                            if (arbol.BuscarNodo(datoN) != null) {
                                JOptionPane.showMessageDialog(null, "El Nodo Fue Encontrado: '" + arbol.BuscarNodo(datoN).getDato() + "'");
                            } else {
                                JOptionPane.showMessageDialog(null, "El Nodo NO Existe");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Arbol Vacio");
                        }
                        break;
                    case 7:
                        if (!arbol.EstaVacio()) {
                            datoN = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce el Valor del Nodo a Buscar", "Eliminando Nodo", JOptionPane.QUESTION_MESSAGE));
                            arbol.raiz = arbol.Eliminar(arbol.raiz, datoN);
                        } else {
                            JOptionPane.showMessageDialog(null, "El Arbol esta vacio");
                        }

//                        if (!arbol.EstaVacio()) {
//                            datoN = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce el Valor del Nodo a Buscar", "Eliminando Nodo", JOptionPane.QUESTION_MESSAGE));
//                            if (arbol.EliminarNodo(datoN) == false) {
//                                JOptionPane.showMessageDialog(null, "El Nodo NO Existe");
//                            } else {
//
//                            }
//                        }
                        break;
                }

            } while (op != 8);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
