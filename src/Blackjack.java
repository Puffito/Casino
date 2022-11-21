import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Blackjack {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        boolean salir = false;

        //Menú de juegos
        while(!salir){
            System.out.println("""
                            ¡Bienvenido al Pufficasino!
                            Seleccione el juego al que quiere jugar:
                            1. Blackjack
                            0. Salir
                            """);
            switch(Integer.parseInt(br.readLine())){
                case (1) -> blackjack();
                default -> salir = true;
            }
        }
        System.out.println("Gracias por jugar a los Puffijuegos.");

    }

    private static void blackjack() throws IOException {

        //Instrucciones
        System.out.println("""
                ¡Bienvenido al Blackjack!
                Para jugar saca cartas del mazo intentando llegar a 21 sin pasarte.
                J, Q y K valen 10 puntos.
                El A vale 1 o 11 puntos.
                ¡Mucha suerte!
                Pulsa ENTER para sacar la primera carta.
                """);
        br.readLine();

        //Creación de Variables
        MazoDePoker mazo = new MazoDePoker();
        ArrayList<String> resultados = new ArrayList<>();
        int total = 0;
        int ases ;
        boolean parar = false;

        //Procesos
        // TODO: 18/11/2022 Hay que hacer que la casa u otro jugador también saquen carta

        //Sacamos carta del jugador
        while (total < 21 && !parar){
            String carta = mazo.getCarta();

            //Comprobamos que la carta no haya salido antes y la agregamos a la mesa
            if (!resultados.contains(carta)) {
                resultados.add(carta);
                total = 0;
                ases = 0;

                //Hacemos suma de cartas en la mesa primero sumando los números fijos y guardando los Ases para el final
                for (String comprobacion : resultados) {
                    if (comprobacion.contains("J") || comprobacion.contains("Q") || comprobacion.contains("K")) {
                        total += 10;
                    } else if (comprobacion.contains("A")) {
                        ases += 1;
                    } else {
                        String[] numeroCarta = comprobacion.split(" ");
                        total += Integer.parseInt(numeroCarta[0]);
                    }
                }

                //Sumamos los Ases según lo que haga falta, 1 u 11
                for (int i = 0; i < ases; i++){
                    if ((21 - total) < 11){
                        total += 1;
                    }else{
                        total +=11;
                    }
                }

                //Mostramos la mesa y los resultados
                // TODO: 21/11/2022 Estaría bien mostrar las cartas gráficamente 
                System.out.printf("""
                         Has sacado: %s
                         Tus cartas son: %s
                         El total es: %d
                        """, carta, resultados, total);

                //Mira si se llegó a 21, se pasa o el usuario quiere parar
                if (total < 21) {
                    System.out.println("¿Quiéres seguir? 1.Si 2.No");
                    if (Integer.parseInt(br.readLine()) == 2) {
                        parar = true;
                    }
                } else if (total == 21) {
                    System.out.println("""
                            ¡¡¡BLACKJACK!!!
                            ¡Has Ganado!
                            """);
                    parar = true;

                } else {
                    System.out.println("Has perdido...");
                    parar = true;
                }
            }
        }

        System.out.println("Pulsa ENTER para continuar");
        br.readLine();
    }

}
