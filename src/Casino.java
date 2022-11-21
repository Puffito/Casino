import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Casino {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        boolean salir = false;

        System.out.println("""
                            ¡Bienvenido al Pufficasino!
                            ¿Cuál es tu nombre?
                            """);

        String nombre = br.readLine();
        int saldo = 500;
        System.out.print("Bienvenido ");

        // TODO: 21/11/2022 Empezar a pensar en otro juego
        //Menú de juegos 
        while(!salir && saldo >= 50){
            System.out.printf("""
                            %s, tu saldo es de %d€
                            Selecciona el juego al que quiere jugar:
                            1. Blackjack - 50€
                            0. Salir
                            """,nombre,saldo);
            switch(Integer.parseInt(br.readLine())){
                case (1) -> saldo = Blackjack.run(saldo);
                default -> salir = true;
            }
        }
        if(saldo < 50){
            System.out.println("¡Fuera de aquí, estás en la quiebra!");
        }
        System.out.println("Gracias por jugar a los Puffijuegos.");
    }
}
