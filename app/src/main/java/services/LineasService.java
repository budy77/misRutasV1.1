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

        linea lineaUno = new linea("Linea 132","ruta132_v1.geojson");
        /*lineaUno.setNombre("Linea 132");
        lineaUno.setArchivo("archivoUnoo.geojson");*/
        listaLineas.add(lineaUno);
        listaLineasFragment.mostrarLineas(listaLineas);

    }
}
