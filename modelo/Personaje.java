package modelo;

/**
 * Modelo de dominio: representa un personaje del juego.
 * 
 * Aplica encapsulamiento: los atributos son privados y se accede
 * a ellos a traves de getters y setters. Esta clase no sabe nada
 * sobre como se almacenan los personajes ni como se muestran al
 * usuario; su unica responsabilidad es representar un personaje.
 */
public class Personaje {
    //Atributos privados: nadie de afuera puede modificarlos
    //directamente. Para acceder o modificarlos se usan los metodos
    //getters y setters definidos mas abajo.
    private static int id;
    private String nombre;
    private String clase;
    private String tribu;
    private int vida;
    private int defensa;
    private int ataque;

    //Constructor sin id: el id lo asigna el PersonajeService al
    //momento de guardar el personaje. El usuario nunca elige id.
    public Personaje(String nombre, String clase, String tribu, int vida, int defensa, int ataque) {
        this.nombre = nombre;
        this.clase = clase;
        this.tribu = tribu;
        this.vida = vida;
        this.defensa = defensa;
        this.ataque = ataque;
    }

    //Constructor vacio: util para crear un Personaje y completarlo
    //con setters despues. Tambien lo necesitara Spring/JPA mas adelante en el curso.
    public Personaje() {

    }
    
    //Getters y setters: la unica forma de acceder o modificar
    //los atributos privados desde afuera de la clase.
    public String getNombre() { return nombre;}
    public String getClase() { return clase;}
    public String getTribu() { return tribu;}
    public int getVida() { return vida;}
    public int getDefensa() { return defensa;}
    public int getAtaque() { return ataque;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setClase(String clase) {this.clase = clase;}
    public void setTribu(String tribu) {this.tribu = tribu;}
    public void setVida(int vida) {this.vida = vida;}
    public void setDefensa(int defensa) {this.defensa = defensa;}
    public void setAtaque(int ataque) {this.ataque = ataque;}

    //toString() sobreescribe el metodo heredado de Object.
    //Sirve para mostrar el personaje de forma legible al listarlo
    //en consola. Cuando hagamos System.out.println(producto), Java
    //llama automaticamente a este metodo.
    @Override
    public String toString() {
        return "ID: " + id +
                " | " + nombre +
                " | " + clase +
                " | " + tribu +
                " | " + vida +
                " | " + defensa +
                " | " + ataque;
    }
}