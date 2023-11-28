/*
* Representa la baraja española pero con 8 y 9, en total 48 cartas, 4 palos, valores de las cartas de 1 a 12. 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */

import java.util.Random;
import java.util.Stack;

public class Baraja {

    private Stack<Carta> baraja;

    /**
     * Crea una nueva baraja y la llena con cartas españolas.
     */
    public Baraja() {
        baraja = new Stack<>();
        for (Carta.tipoPalo palo : Carta.tipoPalo.values()) {
            for (int j = 1; j <= 12; j++) {
                Carta card = new Carta(j, palo);
                baraja.add(card);
            }
        }
    }

    /**
     * Sirve para meter una carta en la baraja.
     *
     * @param carta La carta que quieres meter en la baraja.
     */
    public void meterCarta(Carta carta) {
        this.baraja.push(carta);
    }

    /**
     * Baraja la baraja, valga la redundancia, para reubicar las cartas en
     * posiciones aleatorias.
     */
    public void barajarCartas() {
        Carta aux[] = new Carta[48];
        int i = 0;
        // Sacar las cartas de la pila
        while (!baraja.isEmpty()) {
            aux[i] = baraja.pop();
            i++;
        }
        // Barajar
        Random randomNum = new Random();
        for (int mov = 0; mov < aux.length; mov++) {
            int j = randomNum.nextInt(48);
            Carta auxiliar = aux[j];
            aux[j] = aux[mov];
            aux[mov] = auxiliar;
        }
        // Volver a meter las cartas en la pila
        for (Carta carta : aux) {
            meterCarta(carta);
        }
    }

    /**
     * Saca una carta de la baraja.
     *
     * @return La carta que se ha sacado
     */
    public Carta sacarCarta() {
        return this.baraja.pop();
    }

    /**
     * Te dice si quedan cartas en la baraja.
     *
     * @return false Si quedan cartas en la baraja, true si la baraja está vacía
     */
    public boolean estaVacia() {
        return baraja.isEmpty();
    }
}
