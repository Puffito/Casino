package model;

import java.util.ArrayList;
import java.util.Random;

public class MazoDePoker {

    String[][] mazo = new String[4][13];
    String palo = null;

    MazoDePoker(){
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case (0) -> palo = "Coraz�n";
                case (1) -> palo = "Pica";
                case (2) -> palo = "Diamante";
                case (3) -> palo = "Tr�bol";
            }
            for (int j = 0; j < 13; j++) {
                switch (j) {
                    case (0) -> mazo[i][j] = ("A " + palo);
                    case (12) -> mazo[i][j] = ("K " + palo);
                    case (11) -> mazo[i][j] = ("Q " + palo);
                    case (10) -> mazo[i][j] = ("J " + palo);
                    default -> mazo[i][j] = ((j + 1) + " " + palo);
                }
            }
        }
    }

    public String getCarta(ArrayList<String> resultados) {
        String carta;
        do {
            Random azar = new Random();
            carta = mazo[azar.nextInt(4)][azar.nextInt(13)];
        } while (resultados.contains(carta));

        return carta;
    }
}
