import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HoldEMPoker {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int run(int saldo) throws IOException {
        //Instrucciones
        System.out.println("""
                ¡Bienvenido al Hold'em Poker!
                Recibirás dos cartas del mazo que solo verás tú.
                Se sacarán luego tres cartas que valen para todos los jugadores.
                Puedes seguir y subir la apuesta o retirarte.
                Y luego se sacarán dos cartas más una a una y puedes volver decidir seguir o retirarte.
                La apuesta inicial es de 50€, si te retiras no recuperas nada.
                Si tienes la mano ganadora te llevas las apuestas de la mesa.
                ¡Mucha Suerte!
                
                Pulsa ENTER para continuar""");
        br.readLine();

        MazoDePoker mazo = new MazoDePoker();
        ArrayList<String> resultadosJugador = new ArrayList<>();
        ArrayList<String> resultadosCasa = new ArrayList<>();
        ArrayList<String> resultadosMesa = new ArrayList<>();
        ArrayList<String> cartasSacadas = new ArrayList<>();
        saldo -= saldo;
        int totalJugador = 0;
        int totalBanca = 0;
        boolean pararJugador = false , pararBanca = false, fin = false;

// TODO: 23/11/2022 Acabar el funcionamiento del Poker
            resultadosJugador.add(mazo.getCarta(cartasSacadas));
            cartasSacadas.add(resultadosJugador.get(0));
            resultadosJugador.add(mazo.getCarta(cartasSacadas));
            cartasSacadas.add(resultadosJugador.get(resultadosJugador.size() - 1));

            resultadosCasa.add(mazo.getCarta(cartasSacadas));
            cartasSacadas.add(resultadosCasa.get(0));
            resultadosCasa.add(mazo.getCarta(cartasSacadas));
            cartasSacadas.add(resultadosCasa.get(resultadosCasa.size() - 1));

            resultadosMesa.add(mazo.getCarta(cartasSacadas));
            cartasSacadas.add(resultadosMesa.get(0));
            resultadosMesa.add(mazo.getCarta(cartasSacadas));
            cartasSacadas.add(resultadosMesa.get(resultadosMesa.size() - 1));
            resultadosMesa.add(mazo.getCarta(cartasSacadas));
            cartasSacadas.add(resultadosMesa.get(resultadosMesa.size() - 1));

            //Mostramos la mesa y los resultados
            // TODO: 21/11/2022 Estaría bien mostrar las cartas gráficamente con Java FX
            System.out.printf("""
                     Tu mano es: %s
                     Las cartas de la mesa son: %s
                     Estás apostando 50€
                     Hay 100€ en el bote.
                    """, resultadosJugador, resultadosMesa);

        while(!pararJugador && !pararBanca && resultadosMesa.size() != 5) {
            resultadosMesa.add(mazo.getCarta(cartasSacadas));
            cartasSacadas.add(resultadosMesa.get(resultadosMesa.size() - 1));
            System.out.printf("""
                     Tu mano es: %s
                     Las cartas de la mesa son: %s
                    """, resultadosJugador, resultadosMesa);

            System.out.println("¿Quieres seguir? 1.Si 2.No");
            pararJugador = Integer.parseInt(br.readLine()) != 1;
        }
        if(pararJugador){
            System.out.println("Te retiras, pierdes tu apuesta.");
        }else if (pararBanca){
            System.out.println("La banca se retira. ¡Te llevas el bote!");
        }

        return saldo;
    }

    static int resultado(int saldo){
        return saldo;
    }
}
