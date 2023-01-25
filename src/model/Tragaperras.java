package model;

import iu.Colores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tragaperras {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int jugar(int saldo) throws IOException {
        System.out.println("""
        ¡Bienvenido a la Tragaperras!
        Tira la palanca para sacar 3 resultados, si salen 3 figuras iguales te llevas el ¡¡¡Jackpot!!!
        Si sacas dos resultados iguales recuperas tu apuesta.
        Mucha suerte.""");
        br.readLine();
        String[] resultados = {"","",""};
        System.out.println("Tirada de Tragaperras:");
        System.out.print("| ");
        for (int i = 0; i < 3 ; i++) {
            int num = (int)(Math.random()*5);
            switch (num) {
                case 1 -> resultados[i] = Colores.RED_BOLD_BRIGHT+"Corazón"+Colores.RESET;
                case 2 -> resultados[i] = Colores.PURPLE_BOLD+"Diamante"+Colores.RESET;
                case 3 -> resultados[i] = Colores.BLUE_BOLD_BRIGHT+"Herradura"+Colores.RESET;
                case 4 -> resultados[i] = Colores.YELLOW_BOLD_BRIGHT+"Campana"+Colores.RESET;
                case 0 -> resultados[i] = Colores.GREEN_BOLD_BRIGHT+"Limón"+Colores.RESET;
            }
            System.out.print(resultados[i] + " | ");
        }
        System.out.print("\n");
        if (resultados[0].equals(resultados[1]) || resultados[1].equals(resultados[2]) || resultados[0].equals(resultados[2])) {
            if (resultados[0].equals(resultados[1]) && resultados[1].equals(resultados[2])){
                saldo += 500;
                System.out.println("Enhorabuena, ha ganado 500€");
            }
            else System.out.println("Bien, has recuperado tu dinero.");
        }
        else{
            saldo -= 50;
            System.out.println("Lo siento has perdido...");
        }
        br.readLine();
        return saldo;
    }
}
