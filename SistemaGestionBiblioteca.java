import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



// Clase libro
class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private int anoPublicacion;

    // Constructor
    public Libro(String titulo, String autor, String isbn, int anoPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anoPublicacion = anoPublicacion;
    }

    // Getters y setters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }
}

// Clase para representar la biblioteca y gestion de los libros
class Biblioteca {
    private ArrayList<Libro> libros;

    // Constructor biblioteca
    public Biblioteca() {
        this.libros = new ArrayList<>();
    }

    // Método para agregar un libro a la biblioteca
    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println("Libro agregado correctamente.");
    }

    // Método para buscar un libro por título o autor
    public void buscarLibro(String criterio) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(criterio) || libro.getAutor().equalsIgnoreCase(criterio)) {
                System.out.println("-----------------------------\nTitulo de la obra: " + libro.getTitulo() + "\nAutor: " + libro.getAutor() + "\nISBN: " + libro.getIsbn() + "\nAño de publicacion: " + libro.getAnoPublicacion() + "\n-----------------------------");
                return;
            }
        }
        System.out.println("Libro no encontrado.");
    }

    // Método para mostrar todos los libros disponibles
    public void mostrarLibros() {
        for (Libro libro : libros) {
            System.out.println("----------------------------\nTitulo de la obra: " + libro.getTitulo() + "\nAutor: " + libro.getAutor() + "\nISBN: " + libro.getIsbn() + "\nAño de publicacion: " + libro.getAnoPublicacion());
        }
    }
}

//Main
public class SistemaGestionBiblioteca {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner ingresoDatos = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar libro");
            System.out.println("2. Buscar libro");
            System.out.println("3. Mostrar todos los libros");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = ingresoDatos.nextInt();
                ingresoDatos.nextLine(); // Limpiar el buffer de entrada, sirve para que lea la siguiente linea de entrada SIN problema

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el título del libro: ");
                        String titulo = ingresoDatos.nextLine();
                        System.out.print("Ingrese el autor del libro: ");
                        String autor = ingresoDatos.nextLine();
                        System.out.print("Ingrese el ISBN del libro: ");
                        String isbn = ingresoDatos.nextLine();
                        System.out.print("Ingrese el año de publicación del libro: ");
                        int anoPublicacion = ingresoDatos.nextInt();
                        ingresoDatos.nextLine(); // Limpiar el buffer de entrada
                        biblioteca.agregarLibro(new Libro(titulo, autor, isbn, anoPublicacion));
                        break;
                    case 2:
                        System.out.print("Ingrese el título o autor del libro a buscar: ");
                        String buscar = ingresoDatos.nextLine();
                        biblioteca.buscarLibro(buscar);
                        break;
                    case 3:
                        biblioteca.mostrarLibros();
                        break;
                    case 4:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
                }
            } catch (InputMismatchException e) { //cuando no coincide que sea un valor numerico se usa InputMismatchException
                System.out.println("Error: Ingrese un valor numérico válido.");
                ingresoDatos.nextLine(); // Limpiar el buffer de entrada
            }
        } while (opcion != 4);

        ingresoDatos.close();
    }
}
