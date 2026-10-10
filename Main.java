import java.util.Scanner;

import exception.DefensaInsuficienteExcepcion;
import exception.PersonajeNoEncontradoExcepcion;
import exception.VidaInsuficienteExcepcion;
import modelo.Personaje;
import service.PersonajeService;
import ui.MenuPersonaje;
import util.Validador;

public class Main {
    public static void main(String[] args) {
        // Creamos las dependencias una sola vez y las compartimos
        // durante toda la ejecucion del programa.
        PersonajeService service = new PersonajeService();
        Scanner sc = new Scanner(System.in);
        MenuPersonaje menu = new MenuPersonaje(sc, service);
        cargarDatosDePrueba(service);

        int opcion;

        // do-while garantiza que el menu se muestre al menos una
        // vez. Se repite hasta que el usuario elige la opcion 6
        do {
            menu.mostrarMenu();
            opcion = Validador.leerEntero(sc, "Elija una opcion: ");

            // Cada opcion del menu esta envuelta en try/catch
            //Si una operacion lanza una excepcion (personaje
            // inexistente, datos invalidos, etc.), el programa NO
            // se cae: muestra el mensaje y vuelve a mostrar el menu.

            try {
                switch (opcion) {
                    case 1 -> menu.agregarPersonaje();
                    case 2 -> menu.listarPersonajes();
                    case 3 -> menu.buscarPersonaje();
                    case 4 -> menu.actualizarPersonaje();
                    case 5 -> menu.eliminarPersonaje();
                    case 6 -> System.out.println("¡Hasta Luego!");
                    default -> System.out.println("Opcion invalida. Elija un numero del 1 al 6.");
                }
            } catch (PersonajeNoEncontradoExcepcion | VidaInsuficienteExcepcion | DefensaInsuficienteExcepcion e) {
                // capturamos las excepciones personalizadas.
                System.out.println(e.getMessage());
            } catch(IllegalArgumentException e) {
                // Valilador de datos genericos invalidos.
                // (nombre, vida negativa, etc...)
                System.out.println(e.getMessage());
            }



        } while (opcion != 6);

        sc.close();

    }

    private static void cargarDatosDePrueba(PersonajeService service) {
        service.guardar(new Personaje("Troll", "Guerrero", 100, 30));
        service.guardar(new Personaje("Goblin", "Cazador", 50, 15));
        service.guardar(new Personaje("Human", "Mago", 50, 10));
        service.guardar(new Personaje("Elf", "Arquero", 100, 10));
        service.guardar(new Personaje("Triton", "Guerrero", 75, 25));
        System.out.println(" Se cargaron 5 personajes de prueba.\n");
    }
}
