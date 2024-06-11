import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


class Contacto {
    private String nombre;
    private Long telefono;
    private String correo;


    public Contacto(String nombre, Long telefono, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Long getTelefono() {
        return telefono;
    }


    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }


    public String getCorreo() {
        return correo;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }

}

//clase para gestion de contactos

class GestionContactos{
    ArrayList<Contacto> contactos;

    // Constructor contacto
    public GestionContactos() {
        this.contactos = new ArrayList<>();
    } 

    // Método para agregar un contacto
    public void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
        System.out.println("Contacto agregado correctamente.");
    }


    public void buscarContacto(String criterio) {
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(criterio)) {
                System.out.println("-----------------------------\nNombre del contacto: " + contacto.getNombre() + "\nNumero de contacto: " + contacto.getTelefono() + "\nCorreo electronico: " + contacto.getCorreo() + "\n-----------------------------" + contacto.getCorreo() );
                return;
            }
        }
        System.out.println("Contacto no encontrado.");
    }

    // Método para mostrar todos los contactos disponibles
    public void mostrarContacto() {
        for (Contacto contacto : contactos) {
            System.out.println("-----------------------------\nNombre del contacto: " + contacto.getNombre() + "\nNumero de contacto: " + contacto.getTelefono() + "\nCorreo electronico: " + contacto.getCorreo() + "\n-----------------------------");
        }
    }

    // Método para eliminar un contacto
    public void eliminarContacto(Contacto contactoEliminar) {
        contactos.remove(contactoEliminar);
        System.out.println("Contacto eliminado correctamente.");
    }

}

//Main---------------------------------------------------------------------------------------
public class SistemaGestionContactos {
    public static void main(String[] args) {
        GestionContactos gestionContactos = new GestionContactos();
        Scanner ingresoDatos = new Scanner(System.in);
        int opcion = 0;
    
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Mostrar todos los contactos");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = ingresoDatos.nextInt();
                ingresoDatos.nextLine(); // Limpiar el buffer de entrada, sirve para que lea la siguiente linea de entrada SIN problema

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nombre del contacto: ");
                        String nombre = ingresoDatos.nextLine();
                        System.out.print("Ingrese el numero del contacto: ");
                        Long telefono = ingresoDatos.nextLong();
                        System.out.print("Ingrese el correo del contacto: ");
                        ingresoDatos.nextLine(); // Limpiar el buffer de entrada
                        String correo = ingresoDatos.nextLine();
                        gestionContactos.agregarContacto(new Contacto(nombre, telefono, correo));
                        break;
                    case 2:
                        System.out.print("Ingrese el nombre del contacto a buscar: ");
                        String buscar = ingresoDatos.nextLine();
                        gestionContactos.buscarContacto(buscar);
                        break;
                    case 3:
                        gestionContactos.mostrarContacto();
                        break;
                    case 4:
                        System.out.print("Ingrese el nombre del contacto a eliminar: ");
                        String nombreEliminar = ingresoDatos.nextLine();
                        // Buscar el contacto por nombre
                        Contacto contactoEliminar = null;
                        for (Contacto contacto : gestionContactos.contactos) {
                            if (contacto.getNombre().equalsIgnoreCase(nombreEliminar)) {
                                contactoEliminar = contacto;
                                break;
                            }
                        }
                        if (contactoEliminar != null) {
                            gestionContactos.eliminarContacto(contactoEliminar);
                        } else {
                            System.out.println("No se encontró ningún contacto con ese nombre.");
                        }
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
                }
            } catch (InputMismatchException e) { //cuando no coincide que sea un valor numerico se usa InputMismatchException
                System.out.println("Error: Ingrese un valor numérico válido.");
                ingresoDatos.nextLine(); // Limpiar el buffer de entrada
            }
        } while (opcion != 5);

        ingresoDatos.close();
    }
}



