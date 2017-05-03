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
        lineasService.getListaLineas(this);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                /*int topRowVerticalPosition = (listView == null || listView.getChildCount() != 0)? 0: listView.getChildAt(0).getTop();
                Log.i("posiScr",topRowVerticalPosition+"-");*/
                if (firstVisibleItem + visibleItemCount == totalItemCount)
                {
                    state = listView.onSaveInstanceState();
                    agregarMasLineasLlamadoServicio();
                    Log.i("posiScr","se carga mas elemntos a la vista");
                }

            }
        });



        return view;
    }



    public void mostrarLineas(List<linea> listaLineas)
    {
        this.listaLineas = listaLineas;
        LineaAdapter lineaAdapter = new LineaAdapter(this.getActivity(),this.listaLineas);
        listView.setAdapter(lineaAdapter);

    }

    public void agregarMasLineasLlamadoServicio()
    {
        this.lineasService.getMoreListaLineas(this);
    }

    public void agregarMasLineas(List<linea> listaLineas)
    {
        for (int i=0;i<listaLineas.size();i++)
        {
            this.listaLineas.add(listaLineas.get(i));
        }
        LineaAdapter lineaAdapter = new LineaAdapter(getActivity(),this.listaLineas);
        listView.setAdapter(lineaAdapter);
        listView.onRestoreInstanceState(state);
    }

}
