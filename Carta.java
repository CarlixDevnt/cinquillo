/*
 * Representa una carta, formada por un número y su palo correspondiente
 */

public class Carta {

    private final int numero;

    public enum tipoPalo {
        OROS, BASTOS, ESPADAS, COPAS
    };
    private final tipoPalo palo;

    /**
     * Crea una nueva carta.
     *
     * @param numero El valor numérico Entero de la carta, entre 1 y 12.
     * @param palo El palo de la carta, puede ser OROS, BASTOS, ESPADAS o COPAS,
     * siempre como tipoPalo o Carta.tipoPalo.
     */
    public Carta(int numero, tipoPalo palo) {
        this.numero = numero;
        this.palo = palo;

    }

    /**
     * Devuelve el valor numérico de la carta.
     *
     * @return El valor numérico de la carta, como Entero.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Devuelve el tipo de palo de la carta.
     *
     * @return El tipo de palo de la carta, como tipoPalo o Carta.tipoPalo.
     */
    public tipoPalo getPalo() {
        return palo;
    }

    /**
     * Pasa una carta a un formato de String.
     *
     * @return El string, estilizado, de la carta.
     */
    @Override
    public String toString() {

        String colorPalo = "";
        switch (this.palo) {
            case OROS:
                colorPalo = "\u001B[33m";
                break;
            case BASTOS:
                colorPalo = "\u001B[32m";
                break;
            case ESPADAS:
                colorPalo = "\u001B[36m";
                break;
            case COPAS:
                colorPalo = "\u001B[31m";
                break;
        }

        String nombreNumero;
        switch (this.numero) {
            case 1:
                nombreNumero = "As";
                break;
            case 10:
                nombreNumero = "Sota";
                break;
            case 11:
                nombreNumero = "Caballo";
                break;
            case 12:
                nombreNumero = "Rey";
                break;
            default:
                nombreNumero = "" + this.numero;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(nombreNumero).append(" de ").append(colorPalo).append(this.palo.toString().toLowerCase()).append("\u001B[0m");

        return sb.toString();
    }
}
