package fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.ruddy.misrutasv11.R;

import java.util.List;

import adapters.LineaAdapter;
import modelos.linea;
import services.LineasService;

/**
 * Created by RUDDY on 19/04/2017.
 */

public class ListaLineasFragment extends Fragment {

    private List<linea> listaLineas;
    ListView listView;
    LineasService lineasService;
    Parcelable state;
    private int pos = 10;
    private LineaAdapter lineaAdapter;

    private int cantidadMostrar = 6;
    private int cantidadSalto = 0;

    public ListaLineasFragment()
    {

    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_lineas,container,false);
        listView = (ListView)view.findViewById(R.id.listaUno);

        //llamado al servicio para el cargado de las lineas
        final LineasService lineasService = new LineasService(this.getActivity());
        this.lineasService = lineasService;
        lineasService.getListaLineas(this,cantidadMostrar,cantidadSalto);
        lineasService.getListaLineasDos(this,0,10);
        lineasService.getRutaByLinea(this,0,10);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem + visibleItemCount == totalItemCount)
                {
                    state = listView.onSaveInstanceState();
                    agregarMasLineasLlamadoServicio();
                    //Log.i("posiScr","se carga mas elemntos a la vista");
                }

            }
        });



        return view;
    }



    public void mostrarLineas(List<linea> listaLineas)
    {
        this.listaLineas = listaLineas;
        this.lineaAdapter = new LineaAdapter(this.getActivity(),this.listaLineas);
        listView.setAdapter(lineaAdapter);

    }

    public void agregarMasLineasLlamadoServicio()
    {
        cantidadSalto = cantidadMostrar + 1;
        cantidadMostrar = cantidadMostrar + 7;
        this.lineasService.getMoreListaLineas(this,cantidadMostrar,cantidadSalto);
    }

    public void agregarMasLineas(List<linea> listaLineasAdd)
    {
        if (listaLineasAdd.size() > 0)
        {
            for (int i=0;i<listaLineasAdd.size();i++)
            {
                this.listaLineas.add(listaLineasAdd.get(i));
            }

            this.lineaAdapter.notifyDataSetChanged();
            //this.lineaAdapter = new LineaAdapter(this.getActivity(),this.listaLineas);
            //this.listView.setAdapter(null);
            //this.listView.setAdapter(lineaAdapter);
            //listView.setSelection(pos);
            //pos = pos +10;
            this.listView.onRestoreInstanceState(state);
        }

    }

}
