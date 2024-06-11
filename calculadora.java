
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Stack;

class calculadora{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenido a la calculadora Java");
        System.out.println("Ingrese una expresión matemática:");
        
        String expresion = scanner.nextLine();
        
        try {
            BigDecimal resultado = evaluarExpresion(expresion);
            System.out.println("El resultado es: " + resultado);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al evaluar la expresión: " + e.getMessage());
        }
        
        scanner.close();
    }
    
    public static BigDecimal evaluarExpresion(String expresion) {
        Stack<BigDecimal> numeros = new Stack<>();
        Stack<Character> operadores = new Stack<>();
        
        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);
            
            if (caracter == ' ') {
                continue; // Ignorar espacios en blanco
            } else if (Character.isDigit(caracter) || caracter == '.') {
                StringBuilder numero = new StringBuilder();
                while (i < expresion.length() && (Character.isDigit(expresion.charAt(i)) || expresion.charAt(i) == '.')) {
                    numero.append(expresion.charAt(i++));
                }
                i--;
                numeros.push(new BigDecimal(numero.toString()));
            } else if (caracter == '(') {
                operadores.push(caracter);
            } else if (caracter == ')') {
                while (operadores.peek() != '(') {
                    realizarOperacion(numeros, operadores);
                }
                operadores.pop(); // Eliminar el '('
            } else if (esOperador(caracter)) {
                while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(caracter)) {
                    realizarOperacion(numeros, operadores);
                }
                operadores.push(caracter);
            } else {
                throw new IllegalArgumentException("Caracter no válido: " + caracter);
            }
        }
        
        while (!operadores.isEmpty()) {
            realizarOperacion(numeros, operadores);
        }
        
        return numeros.pop();
    }
    
    private static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    private static int precedencia(char operador) {
        if (operador == '+' || operador == '-') {
            return 1;
        } else if (operador == '*' || operador == '/') {
            return 2;
        }
        return 0;
    }
    
    private static void realizarOperacion(Stack<BigDecimal> numeros, Stack<Character> operadores) {
        BigDecimal num2 = numeros.pop();
        BigDecimal num1 = numeros.pop();
        char operador = operadores.pop();
        
        switch (operador) {
            case '+':
                numeros.push(num1.add(num2));
                break;
            case '-':
                numeros.push(num1.subtract(num2));
                break;
            case '*':
                numeros.push(num1.multiply(num2));
                break;
            case '/':
                if (num2.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ArithmeticException("División por cero");
                }
                numeros.push(num1.divide(num2));
                break;
            default:
                throw new IllegalArgumentException("Operador no válido: " + operador);
        }
    }
}
