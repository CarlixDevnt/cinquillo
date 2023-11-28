/*
* Representa la Mesa de juego. 
* Estructura: se utilizará un TAD adecuado. Piensa que hay 4 palos y de cada palo se pueden colocar cartas 
* por cualquiera de los dos extremos (un array con 4 doblescolas parece lo más adecuado). 
    La DobleCola se comentó en clase de teoría
    * Funcionalidad: saber si es posible colocar una carta en la mesa, colocar una carta en la mesa, mostrar la mesa
 */

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class Mesa {

    Deque<Carta>[] mesa;

    /**
     * Crea una nueva mesa de juego.
     */
    public Mesa() {
        mesa = new Deque[4];
        for (int i = 0; i < mesa.length; i++) {
            mesa[i] = new LinkedList<>();
        }
    }

    /**
     * Quita todas las cartas que haya sobre la mesa y las guarda en una baraja.
     *
     * @param baraja La baraja donde se guardarán las cartas, como Baraja.
     */
    public void vaciarMesa(Baraja baraja) {
        for (Deque<Carta> monton : this.mesa) {
            while (!monton.isEmpty()) {
                baraja.meterCarta(monton.removeFirst());
            }
        }
    }

    /**
     * Te dice si se puede colocar una carta determinada sobre la mesa.
     *
     * @param carta La carta que se quiere colocar, como Carta.
     * @return true si la carta se puede colocar, false si no se puede colocar.
     */
    public boolean puedeColocarCarta(Carta carta) {
        boolean canPlace = false;

        Deque<Carta> monton = mesa[carta.getPalo().ordinal()];

        if (monton.isEmpty()) {
            if (carta.getNumero() == 5) {
                canPlace = true;
            }
        } else {
            if (carta.getPalo() == monton.getFirst().getPalo()) {
                if (monton.getFirst().getNumero() == carta.getNumero() - 1) {
                    canPlace = true;
                }
                if (monton.getLast().getNumero() == carta.getNumero() + 1) {
                    canPlace = true;
                }
            }
        }
        return canPlace;
    }

    /**
     * Coloca una carta sobre la mesa, sabiendo si es o no el as de oros.
     *
     * @param carta La carta que se quiera colocar, como Carta.
     * @return true si la carta es el as de oros, false si no lo es.
     */
    public boolean colocar(Carta carta) {
        if (carta.getNumero() == 5) {
            mesa[carta.getPalo().ordinal()].addFirst(carta);
        } else {
            if (mesa[carta.getPalo().ordinal()].getFirst().getNumero() < carta.getNumero()) {
                mesa[carta.getPalo().ordinal()].addFirst(carta);
            } else {
                mesa[carta.getPalo().ordinal()].addLast(carta);
            }
        }
        return (carta.getNumero() == 1 && carta.getPalo() == Carta.tipoPalo.OROS);
    }

    /**
     * Crea un String que contiene las cartas ya jugadas que hay sobre la mesa.
     *
     * @return Las cartas jugadas ya apiladas sobre la mesa, como String.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < mesa.length; i++) {
            sb.append("Palo ").append(i + 1).append(":( ");
            Iterator<Carta> itr = mesa[i].iterator();
            while (itr.hasNext()) {
                sb.append(itr.next()).append("; ");
            }
            sb.append(")");
            sb.append("\n");
        }
        return sb.toString();
    }

}
