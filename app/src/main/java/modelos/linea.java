package modelos;

/**
 * Created by RUDDY on 18/04/2017.
 */

public class linea {
    String nombre;
    String archivo;
    public linea(String nombre, String archivo) {
        this.nombre = nombre;
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return "linea{" +
                "nombre='" + nombre + '\'' +
                ", archivo='" + archivo + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
}
