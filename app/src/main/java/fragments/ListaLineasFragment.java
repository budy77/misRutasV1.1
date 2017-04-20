package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruddy.misrutasv11.R;

/**
 * Created by RUDDY on 19/04/2017.
 */

public class ListaLineasFragment extends Fragment {


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




        return view;
    }
}
