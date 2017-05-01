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

        linea lineaUno = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","maxresdefault");
        /*lineaUno.setNombre("Linea 132");
        lineaUno.setArchivo("archivoUnoo.geojson");*/
        listaLineas.add(lineaUno);
        listaLineasFragment.mostrarLineas(listaLineas);

    }
}
