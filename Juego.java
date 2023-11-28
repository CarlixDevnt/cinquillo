/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Juego {

    private final IU iu;
    private final Baraja baraja;
    private final LinkedList<Jugador> jugadores;
    private final int puntosPartida = 4;
    private int puntosAsDeOros = 2;

    /**
     * Crea un nuevo juego.
     *
     * @param iu La interfaz de usuario del juego, como IU (desde
     * es.uvigo.esei.aed1.iu.IU)
     */
    public Juego(IU iu) {
        this.iu = iu;
        baraja = new Baraja();
        jugadores = new LinkedList<>();
    }

    /**
     * Reparte todas las cartas a todos los jugadores.
     *
     * @param numJugadores EL número de jugadores de la partida
     */
    public void repartirCartas(int numJugadores) {
        int posJugador = 0;
        while (!baraja.estaVacia()) {
            Jugador receptor = jugadores.get(posJugador);
            receptor.insertarCarta(baraja.sacarCarta());
            if (posJugador == numJugadores - 1) {
                posJugador = 0;
            } else {
                posJugador++;
            }
        }
    }

    /**
     * Limpia las manos de los jugadores.
     */
    public void limpiarJugadores() {
        for (Jugador jugador : this.jugadores) {
            jugador.vaciarMano(this.baraja);
        }
    }

    /**
     * Resetea la mesa de juego, quitando todas las cartas.
     *
     * @param mesa La mesa de juego, como Mesa.
     */
    public void resetBaraja(Mesa mesa) {
        mesa.vaciarMesa(this.baraja);
        limpiarJugadores();
    }

    /**
     * Crea una lista con los ganadores del juego.
     *
     * @return La lista de ganadores, como LinkedList
     */
    public LinkedList listarGanadores() {
        LinkedList<Jugador> ganadores = new LinkedList();
        int maxPuntuacion = 0;
        for (Jugador j1 : this.jugadores) {
            if (j1.getPuntuacion() < maxPuntuacion) {
                maxPuntuacion = j1.getPuntuacion();
            }
        }
        for (Jugador j2 : this.jugadores) {
            if (j2.getPuntuacion() == maxPuntuacion) {
                ganadores.add(j2);
            }
        }
        return ganadores;
    }

    /**
     * Juega una partida. La partida acaba cuando alguien saca el as de oros. Al
     * terminar esa ronda, la partida acabará y aparecerán los ganadores en
     * pantalla.
     */
    public void jugar() {
        Collection<String> players = iu.pedirDatosJugadores();
        Iterator<String> it = players.iterator();
        Mesa mesa = new Mesa();

        while (it.hasNext()) {
            Jugador jugador = new Jugador(it.next());
            jugadores.add(jugador);
        }

        boolean acabarPartida = false;

        do {
            boolean colocadoAsOros;
            // Elegir primer jugador
            int min_num = 0;
            int max_num = players.size();
            int rand_num = (int) (Math.random() * (max_num - min_num));

            Jugador primerJugador = jugadores.get(rand_num);
            jugadores.addFirst(jugadores.remove(rand_num));

            // Barajar y repartir
            baraja.barajarCartas();
            repartirCartas(players.size());

            // Mostrar primer jugador
            iu.mostrarJugadores(jugadores);
            System.out.println("\n");

            iu.mostrarMensaje("El primer jugador es: " + primerJugador.getNombre());

            do {
                // Mostrar mesa y turno
                this.iu.mostrarMensaje("Turno de \u001B[35m" + jugadores.getFirst().getNombre() + "\u001B[0m");
                System.out.println(mesa);

                // Comprobar posibilidad de colocar alguna carta
                if (jugadores.getFirst().puedeColocarAlgo(mesa)) {
                    // Pudo colocar :D
                    // Pide el índice de la carta en la mano, mostrándola
                    int indiceCarta;
                    this.iu.mostrarMano(jugadores.getFirst());
                    indiceCarta = this.iu.leePosCartaMano(jugadores.getFirst());
                    // Comprobar que pueda colocar ESA carta
                    while (!mesa.puedeColocarCarta(jugadores.getFirst().devolverCarta(indiceCarta))) {
                        this.iu.mostrarMensaje("\u001B[31m\uD83E\uDD21 No puedes jugar esa carta\u001B[0m");
                        indiceCarta = this.iu.leePosCartaMano(jugadores.getFirst());
                    }

                    // Se coloca la carta en la mesa y se borra de la mano del jugador
                    colocadoAsOros = jugadores.getFirst().colocarCarta(mesa, indiceCarta);

                    if (colocadoAsOros) {
                        jugadores.getFirst().sumarPuntos(this.puntosAsDeOros);
                        acabarPartida = true;
                    }
                } else {
                    // No pudo colocar :(
                    this.iu.mostrarMensaje("\u001B[31m No puedes jugar ninguna carta\u001B[0m");
                }
                // Se comprueba que la mano del jugador actual no esté vacía para pasar al siguiente
                if (!jugadores.getFirst().manoVacia()) {
                    this.iu.mostrarMensaje("\n\u001B[34m\uD83D\uDC64\u001B[0m Siguiente jugador");
                    jugadores.addLast(jugadores.removeFirst());
                }
            } while (!jugadores.getFirst().manoVacia());

            // Se muestra al ganador de la ronda y se le suman 4 puntos por ganar
            this.iu.mostrarMensaje("\n\u001B[33m\uD83C\uDFC6\u001B[0m El ganador de la ronda es: \u001B[35m" + jugadores.getFirst().getNombre());
            jugadores.getFirst().sumarPuntos(this.puntosPartida);

            resetBaraja(mesa);

            this.puntosAsDeOros += 2;

        } while (!acabarPartida);

        iu.mostrarGanadores(listarGanadores());

    }
}
