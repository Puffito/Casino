package iu;

import model.Blackjack;
import model.HoldEMPoker;
import model.Tragaperras;

import java.io.*;

import static persistencia.SaveFile.guardarSaveFile;
import static persistencia.SaveFile.leerSaveFile;

public class Casino {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        System.out.println(Textos.SALUDO_INICIAL);

        String nombre = br.readLine();

        int saldo = leerSaveFile(nombre);

        if (saldo < 50) {
            System.out.println(Textos.SALUDO_DERROTADO);
            saldo = 50;
            br.readLine();
        }else{System.out.print(Textos.SALUDO_BIEN);}

        menuJuegos(nombre, saldo);

        guardarSaveFile(nombre, saldo);

        System.out.println(Textos.DESPEDIDA);
    }

    static public void menuJuegos(String nombre, int saldo) throws IOException {
        // TODO: 21/11/2022 Empezar a pensar en otro juego
        //Menú de juegos
        boolean salir = false;

        while (!salir && saldo >= 50) {
            System.out.printf(String.format(Textos.MENU_PRINCIPAL, nombre, saldo));
            switch (Integer.parseInt(br.readLine())) {
                case (1) -> saldo = Blackjack.jugar(saldo);
                case (2) -> saldo = HoldEMPoker.jugar(saldo);
                case (3) -> saldo = Tragaperras.jugar(saldo);
                case (0) -> salir = true;
                default -> System.out.println(Textos.INCORRECT_OPTION);
            }
        }
        if (saldo < 50) {
            System.out.println(Textos.QUIEBRA);
        }
    }
}