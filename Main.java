public class Main {
    public static void main(String[] args) {
        IU iu = new IU();
        Juego cinquillo = new Juego(iu);
        cinquillo.jugar();
    }   
}
