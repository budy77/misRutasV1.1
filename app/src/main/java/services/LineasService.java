package services;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import fragments.ListaLineasFragment;
import modelos.linea;

/**
 * Created by RUDDY on 28/04/2017.
 */

public class LineasService {
    public LineasService(FragmentActivity fragmentActivity)
    {

    }

    public void getListaLineas(final ListaLineasFragment listaLineasFragment)
    {
        List<linea> listaLineas = new ArrayList<linea>();

        linea lineaUno = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaDos = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaTres = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaCuatro = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaCinco = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaSeis = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaSiete = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaOcho = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaNueve = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaDiez = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        /*lineaUno.setNombre("Linea 132");
        lineaUno.setArchivo("archivoUnoo.geojson");*/
        listaLineas.add(lineaUno);
        listaLineas.add(lineaDos);
        listaLineas.add(lineaTres);
        listaLineas.add(lineaCuatro);
        listaLineas.add(lineaCinco);
        listaLineas.add(lineaSeis);
        listaLineas.add(lineaSiete);
        listaLineas.add(lineaOcho);
        listaLineas.add(lineaNueve);
        listaLineas.add(lineaDiez);
        listaLineasFragment.mostrarLineas(listaLineas);

    }

    public void getMoreListaLineas(final ListaLineasFragment listaLineasFragment)
    {
        List<linea> listaLineas = new ArrayList<linea>();

        linea lineaUno = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaDos = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaTres = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaCuatro = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaCinco = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaSeis = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaSiete = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaOcho = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaNueve = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaDiez = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        /*lineaUno.setNombre("Linea 132");
        lineaUno.setArchivo("archivoUnoo.geojson");*/
        listaLineas.add(lineaUno);
        listaLineas.add(lineaDos);
        listaLineas.add(lineaTres);
        listaLineas.add(lineaCuatro);
        listaLineas.add(lineaCinco);
        listaLineas.add(lineaSeis);
        listaLineas.add(lineaSiete);
        listaLineas.add(lineaOcho);
        listaLineas.add(lineaNueve);
        listaLineas.add(lineaDiez);
        listaLineasFragment.agregarMasLineas(listaLineas);
    }
}
