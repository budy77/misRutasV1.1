package adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruddy.misrutasv11.R;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.SupportMapFragment;
import com.mapbox.mapboxsdk.storage.Resource;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import modelos.linea;

/**
 * Created by RUDDY on 28/04/2017.
 */

public class LineaAdapter extends ArrayAdapter<linea> {
    Activity context;
    List<linea> listaLineas;

    private MapboxMap map;
    private MapboxMapOptions options;
    private SupportMapFragment mapFragment;
    private boolean bandera = true;

    public LineaAdapter( Activity context, List<linea> objects) {
        super(context, R.layout.item_trufis,objects);
        this.context=context;
        this.listaLineas=objects;
    }

    public static class ViewHolder{
        TextView titulo;
        TextView nombreLinea;
        TextView descripcionLinea;
        ImageView imagenLinea;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_trufis,parent,false);
            ViewHolder viewHolder = new ViewHolder();
            //elementos de la vista
            viewHolder.titulo = (TextView)rowView.findViewById(R.id.titulo);
            viewHolder.nombreLinea = (TextView)rowView.findViewById(R.id.nombreLinea);
            viewHolder.descripcionLinea = (TextView)rowView.findViewById(R.id.descripcionLinea);
            viewHolder.imagenLinea = (ImageView)rowView.findViewById(R.id.imagenTrufi);

            //importante agregado elemento
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder)rowView.getTag();
        //se escoge el elemnto de la lista exacto
        final linea linea = listaLineas.get(position);
        //recogemos datoa de la linea especifica
        String tituloLinea = linea.getSindicato();
        String nombreLinea = linea.getNombre();
        String descripcionLinea = linea.getDescripcionLinea();
        final String archivoLinea = linea.getArchivo();
        final String archivoLineaDos = linea.getArchivoDos();
        String archivoImagen = linea.getImagenLinea();

        //evento click sobre el elemento de la lista
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentActivity fragmentActivity = (FragmentActivity)context;
                final FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
                LatLng cochabamba = new LatLng(-17.388591, -66.157334);
                options = new MapboxMapOptions();
                options.styleUrl(Style.MAPBOX_STREETS);
                options.camera(new CameraPosition.Builder()
                        .target(cochabamba)
                        .zoom(14)
                        .build());
                mapFragment = SupportMapFragment.newInstance(options);
                transaction.replace(R.id.container,mapFragment,"com.mapbox.map");
                transaction.commit();

                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(MapboxMap mapboxMap) {

                        // Customize map with markers, polylines, etc.
                        map = mapboxMap;




                        ArrayList<LatLng> points = new ArrayList<>();

                        try {
                            // Load GeoJSON file
                            InputStream inputStream = fragmentActivity.getAssets().open(archivoLinea);
                            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
                            StringBuilder sb = new StringBuilder();
                            int cp;
                            while ((cp = rd.read()) != -1) {
                                sb.append((char) cp);
                            }

                            inputStream.close();

                            // Parse JSON
                            JSONObject json = new JSONObject(sb.toString());
                            JSONArray features = json.getJSONArray("features");
                            JSONObject feature = features.getJSONObject(0);
                            JSONObject geometry = feature.getJSONObject("geometry");
                            if (geometry != null) {
                                String type = geometry.getString("type");

                                // Our GeoJSON only has one feature: a line string
                                if (!TextUtils.isEmpty(type) && type.equalsIgnoreCase("LineString")) {

                                    // Get the Coordinates
                                    JSONArray coords = geometry.getJSONArray("coordinates");
                                    for (int lc = 0; lc < coords.length(); lc++) {
                                        JSONArray coord = coords.getJSONArray(lc);
                                        LatLng latLng = new LatLng(coord.getDouble(1), coord.getDouble(0));
                                        points.add(latLng);
                                    }
                                }
                            }
                        } catch (Exception exception) {
                            //Log.e(TAG, "Exception Loading GeoJSON: " + exception.toString());
                        }

                        if (points.size() > 0) {

                            // Draw polyline on map
                            mapboxMap.addPolyline(new PolylineOptions()
                                    .addAll(points)
                                    .color(Color.parseColor("#3bb2d0"))
                                    .width(2));
                        }
                    }
                });

                FloatingActionButton fab = (FloatingActionButton) context.findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        map.clear();
                        if (bandera == true)
                        {
                            crearNuevaRuta(archivoLineaDos,"#FA0011");
                            bandera = false;
                            Snackbar.make(view, "Ruta Sur-Norte", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        else
                        {
                            crearNuevaRuta(archivoLinea,"#3bb2d0");
                            bandera = true;
                            Snackbar.make(view, "Ruta Norte-Sur", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }


                    }
                });
            }
        });

        Resources resource = context.getResources();
        final int resourceId = resource.getIdentifier(archivoImagen,"drawable",context.getPackageName());



        //cargado de los datos a la vista
        holder.titulo.setText(tituloLinea);
        holder.nombreLinea.setText(nombreLinea);
        holder.descripcionLinea.setText(descripcionLinea);
        Uri path2 = Uri.parse("android.resource://com.example.ruddy.misrutasv11/"+resourceId);
        Picasso.with(this.context)
                .load(path2)
                .resize(300,300)
                .into(holder.imagenLinea);
        return rowView;
    }


    public void crearNuevaRuta(String archivoLineaDos,String color)
    {
        ArrayList<LatLng> points = new ArrayList<>();

        try {
            // Load GeoJSON file
            InputStream inputStream = this.getContext().getAssets().open(archivoLineaDos);
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }

            inputStream.close();

            // Parse JSON
            JSONObject json = new JSONObject(sb.toString());
            JSONArray features = json.getJSONArray("features");
            JSONObject feature = features.getJSONObject(0);
            JSONObject geometry = feature.getJSONObject("geometry");
            if (geometry != null) {
                String type = geometry.getString("type");

                // Our GeoJSON only has one feature: a line string
                if (!TextUtils.isEmpty(type) && type.equalsIgnoreCase("LineString")) {

                    // Get the Coordinates
                    JSONArray coords = geometry.getJSONArray("coordinates");
                    for (int lc = 0; lc < coords.length(); lc++) {
                        JSONArray coord = coords.getJSONArray(lc);
                        LatLng latLng = new LatLng(coord.getDouble(1), coord.getDouble(0));
                        points.add(latLng);
                    }
                }
            }
        } catch (Exception exception) {
            Log.e("ErFi", "Exception Loading GeoJSON: " + exception.toString());
        }

        if (points.size() > 0) {
            //Log.i("lleg", "llego al mapa"+points.size());
            // Draw polyline on map
            map.addPolyline(new PolylineOptions()
                    .addAll(points)
                    .color(Color.parseColor(color))
                    .width(2));
        }
    }
}
