
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ahorcado {
    
    // Lista de palabras
    private static final String[] PALABRAS = {
        "bookshelf", "mafe", "millonarios", "falcao", "teclado", "pantalla", "aguardiente"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Selecciona una palabra al azar de la lista
        String palabraSecreta = PALABRAS[random.nextInt(PALABRAS.length)];
        char[] palabraAdivinada = new char[palabraSecreta.length()];
        
        // Inicializa la palabra adivinada con guiones bajos
        for (int i = 0; i < palabraAdivinada.length; i++) {
            palabraAdivinada[i] = '_';
        }
        
        int intentos = 6;
        List<Character> letrasUsadas = new ArrayList<>();
        boolean juegoTerminado = false;

        // Bucle del juego
        while (!juegoTerminado) {
            System.out.println("Palabra: " + new String(palabraAdivinada));
            System.out.println("Intentos restantes: " + intentos);
            System.out.println("Letras usadas: " + letrasUsadas);
            System.out.print("Introduce una letra: ");
            
            char letra = scanner.next().toLowerCase().charAt(0);

            // Verifica si la letra ya fue usada
            if (letrasUsadas.contains(letra)) {
                System.out.println("Ya has usado esa letra. Intenta con otra.");
                continue;
            }

            letrasUsadas.add(letra);
            boolean acierto = false;

            // Verifica si la letra está en la palabra secreta
            for (int i = 0; i < palabraSecreta.length(); i++) {
                if (palabraSecreta.charAt(i) == letra) {
                    palabraAdivinada[i] = letra;
                    acierto = true;
                }
            }

            // Si no acierta, decrementa los intentos
            if (!acierto) {
                intentos--;
            }

            // Verifica si el jugador ha ganado o perdido
            if (new String(palabraAdivinada).equals(palabraSecreta)) {
                juegoTerminado = true;
                System.out.println("¡Felicidades! Has adivinado la palabra: " + palabraSecreta);
            } else if (intentos == 0) {
                juegoTerminado = true;
                System.out.println("Lo siento, has perdido. La palabra era: " + palabraSecreta);
            }
        }
        
        scanner.close();
    }
}