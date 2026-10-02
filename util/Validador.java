package util;

import java.util.InputMismatchException;
import java.util.Scanner;

import exception.DefensaInsuficienteExcepcion;
import exception.VidaInsuficienteExcepcion;

/** 
 *  Clase con metodos de validacion reutilizables.
 *  Todos los metodos son estaticos: no necesitamos crear una instancia de validador para usarlos. Se invocan directamente.
 *  
 * 
 * 
 */
public class Validador {
    // Validaciones de datos del Personaje
    // Estos metodos lanzan una excepcion si el dato es invalido.
    // no retornan nada: si terminan sin lanzar la excepcion el dato es valido.

    public static void validarNombre(String nombre) {
        // Un nombre nulo o vacio no representa un personaje valido.
        if(nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
    }

    public static void validarVida(int vida) {
        // no sean negativos
        // aceptamos 0
        if (vida < 0) {
            throw new VidaInsuficienteExcepcion("La vida no pueder ser negativo.");
        }
    }

    public static void validarDefensa(int defensa) {
        // defensa negativa no es valido
        // usamos nuestra excepcion personalizado
        if ( defensa < 0) {
            throw new DefensaInsuficienteExcepcion("La defensa no puede ser negativo.");
        }
    }

    public static void validarClase (String clase) {
        if (clase == null || clase.isBlank()) {
            throw new IllegalArgumentException("La clase no puede estar vacia.");
        }
    }

    // Lectura por consola
    // 
    public static int leerEntero(Scanner sc, String mensaje) {
        // bulce infinito que se rompe cuando el usuario ingresa un entero valido.
        while(true) {
            System.out.println(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine(); //limpia el salto de linea pendiente.
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un numero entero. Intente nuevamente.");
                sc.nextLine(); //limpia el salto de linea pendiente.
            }
        }
    }

    public static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            System.out.println(mensaje);
            try {
                double valor = sc.nextDouble();
                sc.nextLine();
                return valor;
            } catch (Exception e) {
                System.out.println("Debe ingresar un numero decimal.(coma o punto)");
                sc.nextLine();
            }
        }
    }

    public static String leerTexto(Scanner sc, String mensaje) {
        // lectura simple de texto
        System.out.println(mensaje);
        return sc.nextLine();
    }


}
