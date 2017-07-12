package com.example.ruddy.misrutasv11;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.SupportMapFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.mapbox.mapboxsdk.style.layers.RasterLayer;
import com.mapbox.mapboxsdk.style.sources.RasterSource;
import com.mapbox.mapboxsdk.style.sources.TileSet;

import fragments.ListaLineasFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MapboxMap map;
    private MapboxMapOptions options;
    private SupportMapFragment mapFragment;
    protected Gson gson = new GsonBuilder().setDateFormat("yyyy-M-d HH:mm:ss").create();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Mapbox.getInstance(this,getString(R.string.access_tokendos));

        if (savedInstanceState == null)
        {
            final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            LatLng cochabamba = new LatLng(-17.3333, -66.1667);
            //LatLng cochabamba = new LatLng(45.5076, -122.6736);
            options = new MapboxMapOptions();
            //options.styleUrl("mapbox://styles/budy777/cj1utr3b300022rmd2foas7qj");
            options.styleUrl(Style.MAPBOX_STREETS);
            options.camera(new CameraPosition.Builder()
                    .target(cochabamba)
                    .zoom(11)
                    .build());
            mapFragment = SupportMapFragment.newInstance(options);
            transaction.add(R.id.container,mapFragment,"com.mapbox.map");
            transaction.commit();




        }else {
            mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentByTag("com.mapbox.map");
        }

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {

                // Customize map with markers, polylines, etc.
                map = mapboxMap;

                String urlEnviar = "http://192.241.189.44/mapcache?layers=tileset_uso_suelo_l_20170513140544&format=image/png&version=1.3.0";
                /*RasterSource webMapSource = new RasterSource(
                        "web-map-source",
                        new TileSet("tileset", "https://geodata.state.nj.us/imagerywms/Natural2015?bbox={"
                                + "bbox-epsg-3857}&format=image/png&service=WMS&version=1.1.1&request=GetMap&"
                                + "srs=EPSG:3857&width=256&height=256&layers=Natural2015"), 256);*/
                RasterSource webMapSource = new RasterSource(
                        "web-map-source",
                        new TileSet("tileset", "http://192.241.189.44/mapcache?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&FORMAT=image/png&TRANSPARENT=true&LAYERS=tileset_limitecercado_20170418202932&WIDTH=256&HEIGHT=256&CRS=EPSG:3857&STYLES=&BBOX={bbox-epsg-3857}"), 256);

                mapboxMap.addSource(webMapSource);

                RasterSource webMapSourceTwo = new RasterSource(
                        "web-map-source-two",
                        new TileSet("tileset", "http://192.241.189.44/mapcache?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&FORMAT=image/png&TRANSPARENT=true&LAYERS=tileset_uso_suelo19_20170418195531&WIDTH=256&HEIGHT=256&CRS=EPSG:3857&STYLES=&BBOX={bbox-epsg-3857}"), 256);

                mapboxMap.addSource(webMapSourceTwo);

                RasterSource webMapSourceThree = new RasterSource(
                        "web-map-source-three",
                        new TileSet("tileset", "http://192.241.189.44/mapcache?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&FORMAT=image/png&TRANSPARENT=true&LAYERS=tileset_uso_ext19&WIDTH=256&HEIGHT=256&CRS=EPSG:3857&STYLES=&BBOX={bbox-epsg-3857}"), 256);

                mapboxMap.addSource(webMapSourceThree);

                RasterSource webMapSourceFour = new RasterSource(
                        "web-map-source-four",
                        new TileSet("tileset", "http://192.241.189.44/mapcache?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&FORMAT=image/png&TRANSPARENT=true&LAYERS=tileset_rios19&WIDTH=256&HEIGHT=256&CRS=EPSG:3857&STYLES=&BBOX={bbox-epsg-3857}"), 256);

                mapboxMap.addSource(webMapSourceFour);

                RasterSource webMapSourceFive = new RasterSource(
                        "web-map-source-five",
                        new TileSet("tileset", "http://192.241.189.44/mapcache?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&FORMAT=image/png&TRANSPARENT=true&LAYERS=tileset_lagunas19_20170420191010&WIDTH=256&HEIGHT=256&CRS=EPSG:3857&STYLES=&BBOX={bbox-epsg-3857}"), 256);
                mapboxMap.addSource(webMapSourceFive);

                RasterSource webMapSourceSix = new RasterSource(
                        "web-map-source-six",
                        new TileSet("tileset", "http://192.241.189.44/mapcache?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&FORMAT=image/png&TRANSPARENT=true&LAYERS=tileset_sitios19_20170418163318&WIDTH=256&HEIGHT=256&CRS=EPSG:3857&STYLES=&BBOX={bbox-epsg-3857}"), 256);
                mapboxMap.addSource(webMapSourceSix);

                RasterSource webMapSourceSeven = new RasterSource(
                        "web-map-source-seven",
                        new TileSet("tileset", "http://192.241.189.44/mapcache?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&FORMAT=image/png&TRANSPARENT=true&LAYERS=tileset_vias19_20170418192020&WIDTH=256&HEIGHT=256&CRS=EPSG:3857&STYLES=&BBOX={bbox-epsg-3857}"), 256);
                mapboxMap.addSource(webMapSourceSeven);


                // Add the web map source to the map.
                RasterLayer webMapLayer = new RasterLayer("web-map-layer", "web-map-source");
                mapboxMap.addLayerBelow(webMapLayer, "aeroway-taxiway");

                RasterLayer webMapLayerTwo = new RasterLayer("web-map-layer-two", "web-map-source-two");
                mapboxMap.addLayerBelow(webMapLayerTwo, "aeroway-taxiway");

                RasterLayer webMapLayerThree = new RasterLayer("web-map-layer-three", "web-map-source-three");
                mapboxMap.addLayer(webMapLayerThree);

                RasterLayer webMapLayerFour = new RasterLayer("web-map-layer-four", "web-map-source-four");
                mapboxMap.addLayer(webMapLayerFour);

                RasterLayer webMapLayerFive = new RasterLayer("web-map-layer-five", "web-map-source-five");
                mapboxMap.addLayer(webMapLayerFive);

                RasterLayer webMapLayerSix = new RasterLayer("web-map-layer-six", "web-map-source-six");
                mapboxMap.addLayer(webMapLayerSix);


                RasterLayer webMapLayerSeven = new RasterLayer("web-map-layer-seven", "web-map-source-seven");
                mapboxMap.addLayer(webMapLayerSeven);


                //new DrawGeoJson().execute();
            }
        });




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                map.clear();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            /*Log.i(TAG+"JSON",response.toString());
            JSONArray listaSubastasJson = response.getJSONArray("listaSubastas");
            List<SubastaAux> listaSubastas = gson.fromJson(listaSubastasJson.toString(),new TypeToken<List<SubastaAux>>(){}.getType());
            Log.i(TAG+"ArrayS",listaSubastas.toString());
            subastaFragment.mostrarListaSubastas(listaSubastas);
            progressDialog.dismiss();*/

            /*Log.i(TAG+"JSON",response.toString());
            JSONObject listaPrendasJson = response.getJSONObject("listaPrendas");
            Prenda listaPrendas = gson.fromJson(listaPrendasJson.toString(),new TypeToken<Prenda>(){}.getType());
            Log.i("listaPrendas",listaPrendas.toString());
            itemSubastaFragment.cargarPrenda(listaPrendas);*/

            // Handle the camera action

            final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            LatLng cochabamba = new LatLng(-17.388591, -66.157334);
            options = new MapboxMapOptions();
            options.styleUrl(Style.SATELLITE);
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
                        InputStream inputStream = getAssets().open("ruta132_v1.geojson");
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



        } else if (id == R.id.nav_gallery) {
            final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            LatLng cochabamba = new LatLng(-17.388591, -66.157334);
            options = new MapboxMapOptions();
            options.styleUrl(Style.DARK);
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
                    /*map = mapboxMap;

                    new DrawGeoJson().execute();*/


                    ArrayList<LatLng> points = new ArrayList<>();

                    try {
                        // Load GeoJSON file
                        InputStream inputStream = getAssets().open("ruta132_v1.geojson");
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



        } else if (id == R.id.nav_slideshow) {

            final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            LatLng cochabamba = new LatLng(-17.388591, -66.157334);
            options = new MapboxMapOptions();
            options.styleUrl(Style.LIGHT);
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
                    /*map = mapboxMap;

                    new DrawGeoJson().execute();*/


                    ArrayList<LatLng> points = new ArrayList<>();

                    try {
                        // Load GeoJSON file
                        InputStream inputStream = getAssets().open("ruta132_v1.geojson");
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

        } else if (id == R.id.nav_manage) {
            final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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
                    /*map = mapboxMap;

                    new DrawGeoJson().execute();*/



                    //prueba de commit agregado
                    ArrayList<LatLng> points = new ArrayList<>();

                    try {
                        // Load GeoJSON file
                        InputStream inputStream = getAssets().open("ruta132_v1.geojson");
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

        } else if (id == R.id.nav_share) {


            mostrarFragmetListLineas();

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void mostrarFragmetListLineas()
    {
        ListaLineasFragment listaLineasFragment = new ListaLineasFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,listaLineasFragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    private class DrawGeoJson extends AsyncTask<Void, Void, List<LatLng>> {
        @Override
        protected List<LatLng> doInBackground(Void... voids) {

            ArrayList<LatLng> points = new ArrayList<>();

            try {
                // Load GeoJSON file
                InputStream inputStream = getAssets().open("exampledos.geojson");
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

            return points;
        }

        @Override
        protected void onPostExecute(List<LatLng> points) {
            super.onPostExecute(points);

            if (points.size() > 0) {

                // Draw polyline on map
                map.addPolyline(new PolylineOptions()
                        .addAll(points)
                        .color(Color.parseColor("#3bb2d0"))
                        .width(2));
            }
        }
    }
}
