package model;

import iu.Textos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Blackjack {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int jugar(int saldo) throws IOException {
        //Instrucciones
        System.out.println(Textos.REGLAS_BLACKJACK);
        br.readLine();

        //Creaci�n de Variables
        MazoDePoker mazo = new MazoDePoker();
        ArrayList<String> resultadosJugador = new ArrayList<>();
        ArrayList<String> resultadosCasa = new ArrayList<>();
        ArrayList<String> cartasSacadas = new ArrayList<>();
        int totalJugador = 0;
        int totalBanca = 0;
        boolean pararJugador = false;


        //Procesos

        while ((totalJugador < 21 && !pararJugador)
                || (totalBanca < 21 && totalBanca < totalJugador)) {

            //Sacamos carta del jugador
            if (totalJugador < 21 && !pararJugador) {
                resultadosJugador.add(mazo.getCarta(cartasSacadas));
                cartasSacadas.add(resultadosJugador.get(resultadosJugador.size() - 1));

                totalJugador = sumarCartas(resultadosJugador);
            }

            //Sacamos carta de la casa
            if (totalBanca < 17 && totalBanca < totalJugador) {
                resultadosCasa.add(mazo.getCarta(cartasSacadas));
                cartasSacadas.add(resultadosCasa.get(resultadosCasa.size() - 1));

                totalBanca = sumarCartas(resultadosCasa);
            }


            //Mostramos la mesa y los resultados
            // TODO: 21/11/2022 Estar�a bien mostrar las cartas gr�ficamente con Java FX
            System.out.printf("""
                     Has sacado: %s
                     Tus cartas son: %s
                     El total es: %d
                     
                    """, resultadosJugador.get(resultadosJugador.size() - 1), resultadosJugador, totalJugador);

            System.out.printf("""
                     La casa ha sacado: %s
                     Las cartas de la banca son: %s
                     Su total es: %d
                     
                    """, resultadosCasa.get(resultadosCasa.size() - 1), resultadosCasa, totalBanca);

            //Mira si se lleg� a 21, se pasa o el usuario quiere parar
            if (totalJugador < 21 && totalBanca <= 21) {
                System.out.println("�Quieres seguir? 1.Si 2.No");
                pararJugador = Integer.parseInt(br.readLine()) != 1;
            } else {
                pararJugador = true;
            }
        }

        return (resultados(totalJugador, totalBanca, resultadosJugador, saldo));
    }

    static int sumarCartas(ArrayList<String> cartasSacadas) {
        //Reiniciamos total y ases para volver a contarlos
        int total = 0;
        int ases = 0;

        //Hacemos suma de cartas en la mesa primero sumando los n�meros fijos y guardando los Ases para el final
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

        //Sumamos los Ases seg�n lo que haga falta, 1 u 11
        for (int i = 0; i < ases; i++) {
            if ((21 - total) < 11) {
                total += 1;
            } else {
                total += 11;
            }
        }
        return total;
    }

    static int resultados(int totalJugador, int totalBanca, ArrayList<String> resultadosJugador, int saldo) throws IOException {
        if (totalJugador > 21 || (totalJugador < totalBanca && totalBanca <= 21)) {
            System.out.println("Has perdido...");
            saldo -= 50;
        } else if (totalJugador == 21 && resultadosJugador.size() == 2) {
            System.out.println("""
                    ���BLACKJACK!!!
                    �Has ganado 50�!
                    """);
            saldo += 50;
        } else if (totalJugador == totalBanca && totalBanca < 21) {
            System.out.println("""
                    Empate
                    Recuperas tu apuesta inicial""");
        } else {
            System.out.println("�Has ganado 25�!");
            saldo += 25;
        }

        System.out.println("Pulsa ENTER para continuar");
        br.readLine();
        return saldo;
    }

}
