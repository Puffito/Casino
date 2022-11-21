import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Blackjack {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int run(int saldo) throws IOException {
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
        ArrayList<String> resultadosJugador = new ArrayList<>();
        ArrayList<String> resultadosCasa = new ArrayList<>();
        ArrayList<String> cartasSacadas = new ArrayList<>();
        int totalJugador = 0;
        int totalCasa = 0;
        boolean pararJugador = false;


        //Procesos
        // TODO: 21/11/2022 Hacer que la Casa siga jugando aunque el jugador se retire 

        //Sacamos carta del jugador
        while (totalJugador < 21 && !pararJugador) {
            resultadosJugador.add(mazo.getCarta(cartasSacadas));
            cartasSacadas.add(resultadosJugador.get(resultadosJugador.size() - 1));

            totalJugador = sumarCartas(resultadosJugador);


            if (totalCasa < 21 && totalCasa < totalJugador) {
                resultadosCasa.add(mazo.getCarta(cartasSacadas));
                cartasSacadas.add(resultadosCasa.get(resultadosCasa.size() - 1));

                totalCasa = sumarCartas(resultadosCasa);
            }


            //Mostramos la mesa y los resultados
            // TODO: 21/11/2022 Estaría bien mostrar las cartas gráficamente
            System.out.printf("""
                     Has sacado: %s
                     Tus cartas son: %s
                     El total es: %d
                     
                    """, resultadosJugador.get(resultadosJugador.size() - 1), resultadosJugador, totalJugador);

            System.out.printf("""
                     La casa ha sacado: %s
                     Las cartas de la casa son: %s
                     Su total es: %d
                     
                    """, resultadosCasa.get(resultadosCasa.size() - 1), resultadosCasa, totalCasa);

            //Mira si se llegó a 21, se pasa o el usuario quiere parar
            if (totalJugador < 21) {
                System.out.println("¿Quieres seguir? 1.Si 2.No");
                if (Integer.parseInt(br.readLine()) == 2) {
                    pararJugador = true;
                }
            }  else {
                pararJugador = true;
            }
        }

        if(totalJugador > 21 || (totalJugador < totalCasa && totalCasa <= 21)){
            System.out.println("Has perdido...");
            saldo -= 50;
        }else if (totalJugador == 21 && resultadosJugador.size() == 2) {
            System.out.println("""
            ¡¡¡BLACKJACK!!!
            ¡Has ganado 50€!
            """);
            saldo += 50;
        }
        else if (totalJugador == totalCasa && totalCasa < 21) {
            System.out.println("""
            Empate
            Recuperas tu apuesta inicial""");
        }else{
            System.out.println("¡Has ganado 25€!");
            saldo += 25;
        }

        System.out.println("Pulsa ENTER para continuar");
        br.readLine();
        return saldo;
    }

    static int sumarCartas(ArrayList<String> cartasSacadas) {
        //Reiniciamos total y ases para volver a contarlos
        int total = 0;
        int ases = 0;

        //Hacemos suma de cartas en la mesa primero sumando los números fijos y guardando los Ases para el final
        for (String comprobacion : cartasSacadas) {
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
        for (int i = 0; i < ases; i++) {
            if ((21 - total) < 11) {
                total += 1;
            } else {
                total += 11;
            }
        }
        return total;
    }

}
