package services;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fragments.ListaLineasFragment;
import modelos.linea;
import services.conexion.ConexionMaster;
import services.conexion.RESTService;
import util.Config;
import util.Utilidades;

/**
 * Created by RUDDY on 28/04/2017.
 */

public class LineasService extends ConexionMaster {
    protected Gson gson = new GsonBuilder().setDateFormat("yyyy-M-d HH:mm:ss").create();

    private  static final String TAG = LineasService.class.getSimpleName();


    public LineasService(FragmentActivity fragmentActivity)
    {
        super(fragmentActivity);
    }

    public void getListaLineas(final ListaLineasFragment listaLineasFragment,final int cantidadMostrar,final int cantidadSalto)
    {
        //List<linea> listaLineas = new ArrayList<linea>();

        /*linea lineaUno = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaDos = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaTres = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaCuatro = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaCinco = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaSeis = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaSiete = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaOcho = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaNueve = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        linea lineaDiez = new linea("Linea 132","Sindicato San Miguel","La linea se fundo en 1994, con 20 lineas iniciales cubriendo las necesidades de transporte de la zona de Valle hermoso","ruta132_v1.geojson","ruta132_v2.geojson","maxresdefault");
        *//*lineaUno.setNombre("Linea 132");
        lineaUno.setArchivo("archivoUnoo.geojson");*//*
        listaLineas.add(lineaUno);
        listaLineas.add(lineaDos);
        listaLineas.add(lineaTres);
        listaLineas.add(lineaCuatro);
        listaLineas.add(lineaCinco);
        listaLineas.add(lineaSeis);
        listaLineas.add(lineaSiete);
        listaLineas.add(lineaOcho);
        listaLineas.add(lineaNueve);
        listaLineas.add(lineaDiez);*/


        try {
            InputStream inputStream = listaLineasFragment.getActivity().getAssets().open("lineasActuales.json");
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }

            inputStream.close();
            //JSONObject json = new JSONObject(sb.toString());
            JSONArray jsonArray = new JSONArray(sb.toString());
            List<linea> listaLineas = gson.fromJson(jsonArray.toString(),new TypeToken<List<linea>>(){}.getType());
            Log.i("arrayLine",listaLineas.toString());
            listaLineasFragment.mostrarLineas(listaLineas.subList(0,cantidadMostrar));

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }



    }

    public void getMoreListaLineas(final ListaLineasFragment listaLineasFragment,final int cantidadMostrar,final int cantidadSalto)
    {
        List<linea> listaLineasDos = new ArrayList<linea>();
        /*
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
        *//*lineaUno.setNombre("Linea 132");
        lineaUno.setArchivo("archivoUnoo.geojson");*//*
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
        listaLineasFragment.agregarMasLineas(listaLineas);*/
        try {
            InputStream inputStream = listaLineasFragment.getActivity().getAssets().open("lineasActuales.json");
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }

            inputStream.close();
            //JSONObject json = new JSONObject(sb.toString());
            JSONArray jsonArray = new JSONArray(sb.toString());
            List<linea> listaLineas = gson.fromJson(jsonArray.toString(),new TypeToken<List<linea>>(){}.getType());
            Log.i("arrayLine",listaLineas.toString());
            Log.i("cant",cantidadSalto+"-"+cantidadMostrar+"-"+listaLineas.size());
            if (cantidadMostrar <= listaLineas.size())
            {
                listaLineasDos = listaLineas.subList(cantidadSalto,cantidadMostrar);
            }

            if (cantidadSalto <= listaLineas.size() && cantidadMostrar > listaLineas.size())
            {
                listaLineasDos = listaLineas.subList(cantidadSalto,listaLineas.size()-1);
            }


            listaLineasFragment.agregarMasLineas(listaLineasDos);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    public  void getListaLineasDos(final ListaLineasFragment listaLineasFragment ,int salto, int limite)
    {
        //limite y numero de salto de la consulta se envian al backend

        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Cargando la lista de subastas....");
        progressDialog.show();
        String urlConexion = Config.URL_BASE+"/showAll/"+salto+"/"+limite;
        HashMap<String,Object> objetos = new HashMap<>();
        HashMap<String, String> cabeceras = new HashMap<>();
        new RESTService(activity).get(urlConexion, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    if (response.getBoolean("success"))
                    {
                        Log.i(TAG+"JSON",response.toString());
                        JSONArray listaSubastasJson = response.getJSONArray("listaLineas");
                        Log.i(TAG+"JSON2",listaSubastasJson.toString());


                        progressDialog.dismiss();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Log.e(TAG,"Error de conexión en subastas");
                        Utilidades.mostrarMensajeToast(activity,response.getString("mensaje"));
                    }

                }catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();


            }
        },cabeceras);

    }


    public  void getRutaByLinea(final ListaLineasFragment listaLineasFragment ,int salto, int limite)
    {
        //limite y numero de salto de la consulta se envian al backend

        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Cargando la lista de subastas....");
        progressDialog.show();
        String urlConexion = Config.URL_BASE+"/getRutas";
        HashMap<String,Object> objetos = new HashMap<>();
        objetos.put("idLinea",5);
        objetos.put("tipoLineaRuta","I");
        String datosJson = gson.toJson(objetos);
        JSONObject datos = new JSONObject(objetos);
        HashMap<String, String> cabeceras = new HashMap<>();
        new RESTService(activity).post(urlConexion, datos,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("RESPUESTALinea",response.toString());

                            if(response.getBoolean("success")){

                                JSONArray listaJson = response.getJSONArray("listaRutas");
                                Utilidades.mostrarMensajeToast(activity,"Llego los datos total "+listaJson.length());
                                progressDialog.dismiss();
                            }else{
                                progressDialog.dismiss();
                                Utilidades.mostrarMensajeToast(activity,response.getString("mensaje"));
                            }



                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Utilidades.mostrarMensajeToast(activity,"La cuenta es invalida");
                    }
                }
                , cabeceras);


    }
}
