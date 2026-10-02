package service;

import java.util.ArrayList;
import java.util.List;

import exception.PersonajeNoEncontradoExcepcion;
import modelo.Personaje;
import util.Validador;

/** Capa de servicio: contiene la logica de creacion de nuestro sistemasistema
 *  Es responsable de:
 *      -mantenier la coleccion de personajes. 
 *      -asignar el id al guardar un nuevo personaje.
 *      -validar los datos antes de guardar o actualizar
 *      -Busca, modifica y elimina personajes por id.
 * 
 *  No tiene Scanner ni System.out : no interactua con el usuario.
 *  Quien quiera mostrar mensajes o leer datos lo hace por afuera ( lo hace la clase main )
 *  spoiler: esta separacion nos va a permitir en clases siguientes, reemplazar el menu por una API REST sin tocar este archivo
 */
public class PersonajeService {
    // coleccion en memoria que guarda los personajes
    private List<Personaje> personajes = new ArrayList<>();

    // Contador para asignar id's unicos. Es static porque pertenece a la clase y no a una instancia
    // garantiza que el id sea unico aunque hubiera varias instacias de PersonajeService

    public static int contadorId = 1;

    // OPERACIONES CRUD ( create, read, update, delete))

    // CREATE : agrega un nuevo personaje
    public Personaje guardar (Personaje p) {
        // validamos antes de guardar. Si algo esta mal, se lanza
        // excepcion y el personaje no se agrega a la lista.
        Validador.validarNombre(p.getNombre());
        Validador.validarVida(p.getVida());
        Validador.validarDefensa(p.getDefensa());
        Validador.validarClase(p.getClase());

        // El id lo asigna el servicio, no el usuario.
        // Despues de asignarlo, incrementamos el contador

        p.setId(contadorId);
        contadorId++;

        // guardo el personaje
        personajes.add(p);
        return p;
    }

    //Read: devuelve toda la lista de personajes
    public List<Personaje> listarTodos() {
        return personajes;
    }

    // buscar un personaje por id

    public Personaje obtenerPorId(int id) {
        for (Personaje p : personajes) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new PersonajeNoEncontradoExcepcion("No se encontro un personaje con el id " + id);
    }

    // update: actualiza los datos del personaje existente
    public Personaje actualizar(int id, Personaje datos) {
        // reutilizamos obtenerPorId. Si lanza excepcion la actualizacion se cancela
        Personaje p = obtenerPorId(id);

        //validamos los datos antes de aplicarlos
        Validador.validarNombre(datos.getNombre());
        Validador.validarVida(datos.getVida());
        Validador.validarDefensa(datos.getDefensa());
        Validador.validarClase(datos.getClase());
        
        // modificamos el producto encontrado
        // Como Java pasa los objetos por referencia, los cambios se reflejan en la lista
        // sin necesidad de hacer nada mas.
        p.setNombre(datos.getNombre());
        p.setVida(datos.getVida());
        p.setDefensa(datos.getDefensa());
        p.setClase(datos.getClase());
    
        return p;
    }

    // DELETE: eliminar un personaje por ID
    public void eliminar(int id) {
        Personaje p = obtenerPorId(id);
        personajes.remove(p);
    }
}
