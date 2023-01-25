package iu;

public class Textos {
    //Textos Menú Principal
    public static final String MENU_PRINCIPAL = """
            %s, tu saldo es de %d€
            Selecciona el juego al que quiere jugar:
            1. Blackjack - 50€
            2. Poker - 50€
            3. Tragaperras - 50€
            0. Salir
            """;
    public static final String SALUDO_INICIAL = """
            ¡Bienvenido al Pufficasino!
            ¿Cuál es tu nombre?""";
    public static final String SALUDO_DERROTADO = """
            Así que has vuelto ¿eh?
            Toma algo de cambio a ver si esta vez sacas algo...
            """;
    public static final String SALUDO_BIEN ="Bienvenido ";
    public static final String INCORRECT_OPTION = "Opción incorrecta";
    public static final String DESPEDIDA = "Gracias por jugar a los Puffijuegos.";
    public static final String QUIEBRA = "¡Fuera de aquí, estás en la quiebra!";

    //Textos Blackjack
    public static final String REGLAS_BLACKJACK = """
                ¡Bienvenido al Blackjack!
                Para jugar saca cartas del mazo intentando llegar a 21 sin pasarte.
                J, Q y K valen 10 puntos.
                El A vale 1 o 11 puntos.
                Si sacas un 21 sacando solo dos cartas consigues un BLACKJACK y ganas extra.
                ¡Mucha suerte!
                Pulsa ENTER para sacar la primera carta.
                """;
}
