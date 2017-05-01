package modelos;

/**
 * Created by RUDDY on 18/04/2017.
 */

public class linea {
    String nombre;
    String sindicato;
    String descripcionLinea;
    String archivo;
    String imagenLinea;

    public linea(String nombre, String sindicato, String descripcionLinea, String archivo, String imagenLinea) {
        this.nombre = nombre;
        this.sindicato = sindicato;
        this.descripcionLinea = descripcionLinea;
        this.archivo = archivo;
        this.imagenLinea = imagenLinea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSindicato() {
        return sindicato;
    }

    public void setSindicato(String sindicato) {
        this.sindicato = sindicato;
    }

    public String getDescripcionLinea() {
        return descripcionLinea;
    }

    public void setDescripcionLinea(String descripcionLinea) {
        this.descripcionLinea = descripcionLinea;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getImagenLinea() {
        return imagenLinea;
    }

    public void setImagenLinea(String imagenLinea) {
        this.imagenLinea = imagenLinea;
    }

    @Override
    public String toString() {
        return "linea{" +
                "nombre='" + nombre + '\'' +
                ", sindicato='" + sindicato + '\'' +
                ", descripcionLinea='" + descripcionLinea + '\'' +
                ", archivo='" + archivo + '\'' +
                ", imagenLinea='" + imagenLinea + '\'' +
                '}';
    }
}
