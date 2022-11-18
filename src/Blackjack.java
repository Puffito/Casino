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
        boolean parar = false;

        //Procesos
        // TODO: 18/11/2022 Hay que hacer que la casa u otro jugador también saquen carta
        while (total < 21 && !parar){
            String carta = mazo.getCarta();
            resultados.add(carta); // TODO: 18/11/2022 Hay que hacer que no pille la misma carta dos veces
            if (carta.contains("J")||carta.contains("Q")||carta.contains("K")){
                total += 10;
            }else if (carta.contains("A") && 21 - total >= 10){
                total += 11; // TODO: 18/11/2022 Hay que hacer que el A cambie de valor dinámicamente según el valor total
            } else if (carta.contains("A") && 21 - total < 10) {
                total +=1;
            }else {
                String[] numeroCarta = carta.split(" ");
                total += Integer.parseInt(numeroCarta[0]);
            }
            System.out.printf("""
                                Has sacado: %s
                                Tus cartas son: %s
                                El total es: %d
                               """, carta, resultados, total);

            //Mira si se llegó a 21 o se para
            if(total < 21){
                System.out.println("¿Quiéres seguir? 1.Si 2.No");
                if(Integer.parseInt(br.readLine())==2){
                    parar=true;
                }
            } else if (total == 21) {
                System.out.println("""
                        ¡¡¡BLACKJACK!!!
                        ¡Has Ganado!
                        """);
                parar=true;

            } else{
                System.out.println("Has perdido...");
                parar=true;
            }
        }

        System.out.println("Pulsa ENTER para continuar");
        br.readLine();
    }

}
