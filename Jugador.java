/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 */

import java.util.LinkedList;

public class Jugador {

    private final String nombre;
    private final LinkedList<Carta> mano;
    private int puntuacion;

    /**
     * Crea un nuevo jugador.
     *
     * @param nombre El nombre del jugador, como String.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new LinkedList<>();
        this.puntuacion = 0;
    }

    /**
     * Devuelve el nombre del jugador.
     *
     * @return El nombre del jugador, como String.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la mano del jugador.
     *
     * @return La lista de cartas que el jugador tiene en su mano, como
     * LinkedList.
     */
    public LinkedList<Carta> getMano() {
        return mano;
    }
    
    /**
     * Devuelve el número de cartas que tiene el jugador en su mano.
     * @return el número de cartas que tiene el jugador en su mano, como Entero.
     */
    public int getCartasEnMano() {
        return mano.size();
    }

    /**
     * Devuelve la puntuación actual del jugador.
     *
     * @return La puntuación del jugador, como Entero.
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Te dice si la mano del jugador está vacía.
     *
     * @return false si la mano del jugador no está vacía, true si la mano del
     * jugador está vacía
     */
    public boolean manoVacia() {
        return getMano().isEmpty();
    }

    /**
     * Suma un número de puntos al jugador.
     *
     * @param puntos Los puntos a sumar, como Entero.
     */
    public void sumarPuntos(int puntos) {
        this.puntuacion += puntos;
    }

    /**
     * Mete una carta en la mano del jugador.
     *
     * @param laCarta La carta a añadir en la mano del jugador, como Carta.
     *
     */
    public void insertarCarta(Carta laCarta) {
        this.mano.add(laCarta);
    }

    /**
     * Devuelve una carta de la mano del jugador sin sacarla de la baraja.
     *
     * @param index El índice en la lista de cartas que tiene el jugador en su
     * mano, como Entero.
     * @return La carta correspondiente a ese índice, como Carta.
     */
    public Carta devolverCarta(int index) {
        return getMano().get(index);
    }

    /**
     * Comprueba si el jugador puede colocar alguna de las cartas de su mano en
     * la mesa.
     *
     * @param laMesa La mesa en la que se está jugando, como Mesa.
     * @return true si puede colocar alguna carta, false si no puede.
     */
    public boolean puedeColocarAlgo(Mesa laMesa) {
        boolean canPlace = false;
        int cardIndex = 0;
        do {
            canPlace = laMesa.puedeColocarCarta(this.mano.get(cardIndex));
            cardIndex++;
        } while (!canPlace && cardIndex < this.mano.size());
        return canPlace;
    }

    /**
     * Coloca una carta en la mesa, quitándola de la mano del jugador; y
     * sabiendo si es el as de oros.
     *
     * @param mesa La mesa donde se va a colocar la carta, como Mesa.
     * @param indice El índice en la lista de cartas que tiene el jugador en su
     * mano, como Entero.
     * @return true si la carta colocada es el as de oros, false si no lo es.
     */
    public boolean colocarCarta(Mesa mesa, int indice) {
        return mesa.colocar(mano.remove(indice));
    }

    /**
     * Vacía la mano del jugador y coloca sus cartas en una baraja.
     *
     * @param baraja La baraja donde se guardarán las cartas, como Baraja.
     */
    public void vaciarMano(Baraja baraja) {
        while (!this.mano.isEmpty()) {
            baraja.meterCarta(this.mano.remove());
        }
    }

    /**
     * Devuelve el nombre del jugador y la lista con sus cartas.
     *
     * @return Los datos del jugador, como String.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNombre: ").append(getNombre());
        sb.append("\u001B[0m\nCartas: ").append(mano);

        return sb.toString();
    }

}
