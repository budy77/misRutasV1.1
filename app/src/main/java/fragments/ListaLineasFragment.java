package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        LineasService lineasService = new LineasService(this.getActivity());
        lineasService.getListaLineas(this);




        return view;
    }



    public void mostrarLineas(List<linea> listaLineas)
    {
        this.listaLineas = listaLineas;
        LineaAdapter lineaAdapter = new LineaAdapter(this.getActivity(),this.listaLineas);
        listView.setAdapter(lineaAdapter);
    }

}
