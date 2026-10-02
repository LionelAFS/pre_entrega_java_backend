package exception;

/** Excepcion personaliza que se lanza cuando se intenta asignar vida invalida a un personaje (ej: un valor negativo)
 * 
 * Si incorporamos una barra de vida, podemos señalar el caso en que un cliente quiera agregar mas vida de las disponible.
 * 
 * 
 * 
 * 
 */
public class DefensaInsuficienteExcepcion extends RuntimeException {
    public DefensaInsuficienteExcepcion (String mensaje) {
        super(mensaje);
    }
}
