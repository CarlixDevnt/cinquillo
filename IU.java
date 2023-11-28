/**
 * Representa la interfaz del juego del Cinquillo-Oro, en este proyecto va a ser una entrada/salida en modo texto
 * Se recomienda una implementación modular.
 */

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.LinkedList;

public class IU {

    private final Scanner teclado;

    /**
     * Crea una nueva interfaz de usuario.
     */
    public IU() {
        teclado = new Scanner(System.in).useDelimiter("\r?\n");
    }

    /**
     * Lee un num. de teclado
     *
     * @param msg El mensaje a visualizar.
     * @return El num., como entero
     */
    public int leeNum(String msg) {
        do {
            System.out.print(msg);

            try {
                return teclado.nextInt();
            } catch (InputMismatchException exc) {
                teclado.next();
                System.out.println("Entrada no válida. Debe ser un entero.");
            }
        } while (true);
    }

    /**
     * Lee un String.
     *
     * @param msg Un mensaje que se mostrará por pantalla, como String.
     * @return El texto leído, como String.
     */
    public String leeString(String msg) {
        System.out.print(msg);
        return teclado.next();
    }

    public String leeString(String msg, Object... args) {
        System.out.printf(msg, args);
        return teclado.next();
    }

    /**
     * Muestra un mensaje por pantalla.
     *
     * @param msg El mensaje a mostrar, como String.
     */
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public void mostrarMensaje(String msg, Object... args) {
        System.out.printf(msg, args);
    }

    /**
     * Muestra la mano del jugador.
     *
     * @param jugador El jugador del que se quiere mostrar la mano, como
     * Jugador.
     */
    public void mostrarMano(Jugador jugador) {
        mostrarMensaje("Elige una carta tecleando un número");
        for (Carta carta : jugador.getMano()) {
            StringBuilder apartadoCarta = new StringBuilder();
            apartadoCarta.append("\t").append(jugador.getMano().indexOf(carta)).append(": ").append(carta.toString());
            mostrarMensaje(apartadoCarta.toString());
        }
    }

    /**
     * Lee una posición de un carta en la mano de un jugador.
     *
     * @param jugador El jugador de cuya mano se quiere leer la posición de una
     * carta, como Jugador.
     * @return El número leído, como Entero.
     */
    public int leePosCartaMano(Jugador jugador) {
        int index;
        do {
            index = leeNum("Introduce un número válido: ");
        } while (index < 0 || index >= jugador.getMano().size());
        return index;
    }

    /**
     * Recopila los datos de los jugadores.
     *
     * @return Los datos recopilados de los jugadores, como Collection de
     * String.
     */
    public Collection<String> pedirDatosJugadores() {
        Collection<String> nombresJugadores = new LinkedList<>();
        mostrarMensaje("Este es un juego para 3 o 4 jugadores.");
        int num = 0;
        do {
            num = leeNum("¿Cuántos jugadores van a jugar? ");
        } while (num != 3 && num != 4);
        for (int i = 0; i < num; i++) {
            String nombre = leeString("Introduzca los datos del jugador " + (i + 1) + ": ");
            nombresJugadores.add(nombre);
        }
        return nombresJugadores;

    }

    /**
     * Muestra los datos de un jugador por pantalla.
     *
     * @param jugador El jugador del que se quieren mostrar los datos, como
     * Jugador.
     */
    public void mostrarJugador(Jugador jugador) {
        System.out.println(jugador.toString());
    }

    /**
     * Muestra a todos los jugadores.
     *
     * @param jugadores La colección de jugadores, como Collection de Jugador.
     */
    public void mostrarJugadores(Collection<Jugador> jugadores) {
        for (Jugador j : jugadores) {
            mostrarJugador(j);
        }
    }

    /**
     * Muestra los nombres y la puntuación de los jugadores ganadores.
     *
     * @param ganadores La lista de jugadores ganadores.
     */
    public void mostrarGanadores(LinkedList<Jugador> ganadores) {
        if (ganadores.size() == 1) {
            mostrarMensaje(ganadores.getFirst().getNombre() + " ha ganado con " + ganadores.getFirst().getPuntuacion() + " puntos");
        } else {
            StringBuilder str = new StringBuilder();
            str.append("Ha habido un empate a ").append(ganadores.getFirst().getPuntuacion()).append(" puntos entre los siguientes jugadores:\n");
            while (!ganadores.isEmpty()) {
                str.append(ganadores.removeFirst().getNombre());
                if (!ganadores.isEmpty()) {
                    str.append(" - ");
                }
            }
            mostrarMensaje(str.toString());
        }
    }
}
