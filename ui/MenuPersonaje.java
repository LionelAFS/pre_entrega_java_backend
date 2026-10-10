package ui;

import service.PersonajeService;
import util.Validador;

import java.util.List;
import java.util.Scanner;

import modelo.Personaje;

/*
    Mantener la interaccion con el usuario: mostrar el menu, leer datos, mostrar mensajes de error o exito.

    responsable de:
        -Mostrar el menu al usuario
        -Pedirle los datos
        -Mostrar los resultados

        No contiene la logica del negocio
        No controla el flujo del programa

 */

public class MenuPersonaje {
    // Atributo : el scanner y el service se reciben por constructor ( no se crean aca adentro )
                //para quien instancie la clase tenga el control sobre que usar.

    private final Scanner sc;
    private final PersonajeService service;

    // "Inyeccion por constructor" patron utilizado en Spring Boot
    public MenuPersonaje(Scanner sc, PersonajeService service){
        this.sc = sc;
        this.service = service;
    }

    // Menu principal

    public void mostrarMenu() {
        System.out.println("========= TechLab - Gestion de Personajes =========");
        System.out.println("1) Agregar personaje");
        System.out.println("2) Listar personajes");
        System.out.println("3) Buscar producto por ID");
        System.out.println("4) Actualizar personaje");
        System.out.println("5) Eliminar personaje");
        System.out.println("6) Salir");
        System.out.println("===================================================");
    }

    // Operaciones del CRUD
    
    //cada metodo corresponde a una opcion del menu

    public void agregarPersonaje() {
        System.out.println("---- Nuevo Personaje ----");
        String nombre = Validador.leerTexto(sc, "Nombre: ");
        int vida = Validador.leerEntero(sc, "Vida: ");
        int defensa = Validador.leerEntero(sc, "Defensa: ");
        String clase = Validador.leerTexto(sc, "Clase: ");

        // Construimos el producto y lo enviamos al servicio.
        Personaje p = new Personaje(nombre, clase, vida, defensa);
        //lo enviamos al servicio
        Personaje guardado = service.guardar(p); // El servicio se encarga de validar y asignar el id

        System.out.println("Personaje agregado con el id " + guardado.getId());

    }

    public void listarPersonajes() {
        //Recibimos un List ( ver PersonajeService )
        List<Personaje> lista = service.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay personajes cargados.");
            return;
        }

        System.out.println("--- Lista ---");
        for (Personaje p : lista) {
            System.out.println(p);
        }
    }

    public void buscarPersonaje() {
        int id = Validador.leerEntero(sc, "Ingresa el id del personaje: ");

        Personaje p = service.obtenerPorId(id);
        System.out.println("Encontrado: " + p);
    }

    public void actualizarPersonaje() {
        int id = Validador.leerEntero(sc, "Ingrese el id del personaje a actualizar: ");

        // Mostramos primero los datos actuales para que el usuario
        // sepa que esta modificado.
        Personaje actual = service.obtenerPorId(id);
        System.out.println("Datos actuales: " + actual);

        System.out.println("--- Ingrese los nuevos datos ---");
        String nombre = Validador.leerTexto(sc, "Nombre: ");
        int vida = Validador.leerEntero(sc, "Vida: ");
        int defensa = Validador.leerEntero(sc, "Defensa: ");
        String clase = Validador.leerTexto(sc, "Clase: ");

        Personaje datos = new Personaje(nombre, clase, vida, defensa);
        Personaje actualizado = service.actualizar(id, datos);

        System.out.println("Personaje actualizado: " + actualizado);
    }

    public void eliminarPersonaje() {
        int id = Validador.leerEntero(sc, "Ingrese el id del personaje a eliminar: ");
        service.eliminar(id);
        System.out.println("Producto eliminado.");
    }





}