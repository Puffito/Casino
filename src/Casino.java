import java.io.*;
import java.util.ArrayList;

public class Casino {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        boolean salir = false;

        System.out.println("""
                            ¡Bienvenido al Pufficasino!
                            ¿Cuál es tu nombre?""");
        String nombre = br.readLine();
        int saldo = leerSaveFile(nombre);


        // TODO: 21/11/2022 Empezar a pensar en otro juego
        //Menú de juegos 
        while(!salir && saldo >= 50){
            System.out.printf("""
                            %s, tu saldo es de %d€
                            Selecciona el juego al que quiere jugar:
                            1. Blackjack - 50€
                            2. Poker -50€
                            0. Salir
                            """,nombre,saldo);
            switch(Integer.parseInt(br.readLine())){
                case (1) -> saldo = Blackjack.run(saldo);
                case (2) -> saldo = HoldEMPoker.run(saldo);
                default -> salir = true;
            }
        }
        if(saldo < 50){
            System.out.println("¡Fuera de aquí, estás en la quiebra!");
        }

        guardarSaveFile(nombre,saldo);
        System.out.println("Gracias por jugar a los Puffijuegos.");
    }

    // TODO: 23/11/2022 Hay que hacer que no salga el mensaje de error cuando no hay archivo de guardado creado 
    static int leerSaveFile(String nombre){

        int saldo = 500;

        try {
            FileReader file = new FileReader("saldoJugadores.txt");
            BufferedReader reader = new BufferedReader(file);
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partesLinea =  linea.split(", ");

                if(partesLinea[0].equals(nombre)){
                    saldo = Integer.parseInt(partesLinea[1]);
                }
            }
            reader.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Bienvenido ");
        return saldo;
    }
    static void guardarSaveFile(String nombre, int saldo){
        boolean nuevo = true;
        ArrayList<String> lineasSaveFile = new ArrayList<>();
        String linea;

        try {
        FileReader file = new FileReader("saldoJugadores.txt");
        BufferedReader reader = new BufferedReader(file);
            while ((linea = reader.readLine()) != null) {
                String[] partesLinea =  linea.split(", ");
                if(partesLinea[0].equals(nombre)){
                    lineasSaveFile.add(nombre + ", " + saldo);
                    nuevo = false;
                }
                else{
                    lineasSaveFile.add(linea);
                }
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("SaldoJugadores.txt");
            for (String s : lineasSaveFile) {
                writer.write(s + "\r\n");
            }
            if (nuevo) {
                writer.write(nombre + ", " + saldo + "\r\n");
            }
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}