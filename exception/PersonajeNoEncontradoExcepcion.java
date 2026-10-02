package exception;


/** Excepcion personaliza que se lanza cuando se busca un personaje por su id y no existe en el sistema.
 *  hereda de RuntimeExcepcion ( excepciones no chequeadas ): no obliga a quien usa el metdo a envolver la llamada en try/catch , pero si permite capturarla cuando nos interesa.
 * 
 *  Crear nuestras propias nos peermite comunicar errores de dominio con nombres claros, en lugar de usar excepciones genericas como Excepcion o IllegalArgumentExcepcion.
 */
public class PersonajeNoEncontradoExcepcion  extends RuntimeException {
    public PersonajeNoEncontradoExcepcion(String mensaje) {
        // super() llama al constructor de la clase padre (RuntimeExcepcion)
        // que es quien guarda el mensaje y lo expone con getmessage();
        super(mensaje);
    }
}
