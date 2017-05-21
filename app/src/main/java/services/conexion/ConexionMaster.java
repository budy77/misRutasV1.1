package services.conexion;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import util.Utilidades;

/**
 * Created by RUDDY on 20/05/2017.
 */

public class ConexionMaster {
    //Context context;
    protected FragmentActivity activity;
    //public static final String URL_BASE = "http://catastrocbba.com/catastroBackend/public/catastrocbba/api/v1";


    protected static final int ESTADO_PETICION_FALLIDA = 107;
    protected static final int ESTADO_TIEMPO_ESPERA = 108;
    protected static final int ESTADO_ERROR_PARSING = 109;
    protected static final int ESTADO_ERROR_SERVIDOR = 110;
    protected static final int ESTADO_CUENTA_INVALIDA = 401;

    protected int intentosPorError=0;

    // Extras para intent local
    public static final String EXTRA_RESULTADO = "extra.resultado";
    protected static final String EXTRA_MENSAJE = "extra.mensaje";

    protected Gson gson = new GsonBuilder().setDateFormat("yyyy-M-d HH:mm:ss").create();

    private static final String TAG = ConexionMaster.class.getSimpleName();

    public ConexionMaster(FragmentActivity activity){
        this.activity=activity;
        intentosPorError=0;
    }

    protected void tratarErrores(VolleyError error,String tagCliente) {

        resetIntentos();
        // Crear respuesta de error por defecto
        RespuestaApi respuesta = new RespuestaApi(ESTADO_PETICION_FALLIDA, "Petición incorrecta");


        // Verificación: ¿La respuesta tiene contenido interpretable?
        if (error.networkResponse != null) {

            String s = new String(error.networkResponse.data);
            try {
                respuesta = gson.fromJson(s, RespuestaApi.class);
            } catch (JsonSyntaxException e) {
                Log.d(TAG, "Error de parsing: " + s);
            }

        }

        if (error instanceof NetworkError) {
            Utilidades.mostrarMensajeToast(activity,"No tiene conexion a internet.");
            respuesta = new RespuestaApi(ESTADO_TIEMPO_ESPERA, "Error en la conexión. Intentalo de nuevo");
        }

        // Error de espera al servidor
        if (error instanceof TimeoutError) {
            Utilidades.mostrarMensajeToast(activity,"Error de espera del servidor.");
            Log.e(TAG,error.getMessage()+"");
            error.printStackTrace();
            respuesta = new RespuestaApi(ESTADO_TIEMPO_ESPERA, "Error de espera del servidor");
        }

        // Error de parsing
        if (error instanceof ParseError) {
            Log.e("ERRORParse",error.toString());
            respuesta = new RespuestaApi(ESTADO_ERROR_PARSING, "La respuesta no es formato JSON");
        }

        // Error conexión servidor
        if (error instanceof ServerError) {
            Utilidades.mostrarMensajeToast(activity,"Posiblemente no tenga conexion a internet.");
            respuesta = new RespuestaApi(ESTADO_ERROR_SERVIDOR, "Error en el servidor");
        }

        // Cuenta en el server invalidad
        if (error instanceof AuthFailureError) {
            respuesta = new RespuestaApi(ESTADO_CUENTA_INVALIDA, "La cuenta de control no es valida");
        }

        if (error instanceof NoConnectionError) {
            Utilidades.mostrarMensajeToast(activity,"No tiene conexion a internet.");
            respuesta = new RespuestaApi(ESTADO_ERROR_SERVIDOR
                    , "Servidor no disponible, prueba mas tarde");
        }


        Log.e(TAG+"->"+tagCliente, "Error Respuesta:" + (respuesta != null ? respuesta.toString() : "()")
                + "\nDetalles:" + error.getMessage());

        enviarBroadcast(false, respuesta.getMensaje());

    }
    protected void enviarBroadcast(boolean estado, String mensaje) {
        Intent intentLocal = new Intent(Intent.ACTION_SYNC);
        intentLocal.putExtra(EXTRA_RESULTADO, estado);
        intentLocal.putExtra(EXTRA_MENSAJE, mensaje);
        LocalBroadcastManager.getInstance(activity).sendBroadcast(intentLocal);
    }
    protected void resetIntentos(){
        intentosPorError=0;
    }
    protected  int getIntentosPorError(String tag){
        intentosPorError++;
        Log.e(TAG+"_"+tag,"Intento:"+intentosPorError);
        return intentosPorError;
    }
}
