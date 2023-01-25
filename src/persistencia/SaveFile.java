package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

final public class SaveFile {
    static final int SALDO_INICIAL = 100;
    // TODO: 23/11/2022 Hay que hacer que no salga el mensaje de error cuando no hay archivo de guardado creado
    public static int leerSaveFile(String nombre){
        int saldo = 0;
        try {
            FileReader file = new FileReader("saldoJugadores.txt");
            BufferedReader reader = new BufferedReader(file);
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partesLinea =  linea.split(", ");

                if(partesLinea[0].equals(nombre)){
                  saldo = Integer.parseInt(partesLinea[1]);
                }else{
                    saldo = SALDO_INICIAL;
                }
            }
            reader.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saldo;
    }

    public static void guardarSaveFile(String nombre, int saldo){
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
