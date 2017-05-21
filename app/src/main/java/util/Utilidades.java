package util;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.fasterxml.jackson.databind.ObjectMapper;



import com.squareup.picasso.Picasso;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 * Created by RUDDY on 20/05/2017.
 */

public class Utilidades {
    public static void mostrarMensajeToast(Context context, String mensaje){
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }
    public static void mostrarMensajeToastIdString(Context context, int idMensaje){
        String mensaje=context.getString(idMensaje);
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();

    }
    public static void mostrarMensajeToast(Activity context, String mensaje){
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }
    public static void mostrarMensajeToastIdString(Activity context, int idMensaje){
        String mensaje=context.getString(idMensaje);
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();

    }
    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public static <E> Iterable<E> iterable(final Iterator<E> iterator) {
        if (iterator == null) {
            throw new NullPointerException();
        }
        return new Iterable<E>() {
            public Iterator<E> iterator() {
                return iterator;
            }
        };
    }
    public static int getValorNumeroEditText(View view, int idElementoUI){
        int valorNumerico=0;
        try {
            valorNumerico = Integer.parseInt(String.valueOf(((EditText) view.findViewById(idElementoUI)).getText()));
        }catch (Exception e){}
        return valorNumerico;
    }
    public static double getValorDecimalEditText(View view, int idElementoUI){
        double valorNumerico=0.0;
        try {
            valorNumerico = Double.parseDouble(String.valueOf(((EditText) view.findViewById(idElementoUI)).getText()));
        }catch (Exception e){}
        return valorNumerico;
    }
    public static String getValorTextoEditText(View view, int idElementoUI){
        return ((EditText) view.findViewById(idElementoUI)).getText().toString();
    }
    public static Date getValorFechaEditText(View view, int idElementoUI,String formatoFecha){
        SimpleDateFormat sdf=new SimpleDateFormat(formatoFecha);
        String valor=((EditText) view.findViewById(idElementoUI)).getText().toString();
        Calendar calendar=Calendar.getInstance();
        calendar.set(0,0,0);
        if(valor.trim().length()==0){
            return calendar.getTime();
        }
        try {
            return sdf.parse(valor);
        } catch (ParseException e) {


            return calendar.getTime();
        }

    }
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.convertValue(obj, Map.class);
        return map;
    }
    public static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
    public static boolean validarEditTextString(int idView,View rootView,int minLength){
        EditText editText=((EditText) rootView.findViewById(idView));
        String valor=Utilidades.getValorTextoEditText(rootView, idView);
        if(valor.isEmpty()){
            editText.setError("Este campo es obligatorio");
            return false;
        }else{
            if(valor.length()<minLength){
                editText.setError("Ingreso por lo menos "+minLength+" letras");
                return false;
            }else{
                return true;
            }
        }
    }

    /**
     *
     * @param idView id del elemento UI
     * @param rootView View que carga el UI
     * @param minNumber Valor entero minimo
     * @return Si es verdadero el valor del campo es correcto
     */
    public static boolean validarEditTextInteger(int idView,View rootView,int minNumber){
        EditText editText=((EditText) rootView.findViewById(idView));
        String valor=Utilidades.getValorTextoEditText(rootView, idView);
        int valorNumerico=0;
        try{
            valorNumerico=Integer.parseInt(valor);
        }catch (Exception e){
            editText.setError("El valor tiene que ser un numero entero.");
            return false;
        }
        if(valor.isEmpty()){
            editText.setError("Este campo es obligatorio");
            return false;
        }else{
            if(valorNumerico<minNumber){
                editText.setError("El valor tiene que ser mayor o igual a "+minNumber);
                return false;
            }else{
                return true;
            }
        }
    }
    public static boolean validarEditTextInteger(int idView,View rootView,int minNumber,int maxNumber){
        EditText editText=((EditText) rootView.findViewById(idView));
        String valor=Utilidades.getValorTextoEditText(rootView, idView);
        int valorNumerico=0;
        try{
            valorNumerico=Integer.parseInt(valor);
        }catch (Exception e){
            editText.setError("El valor tiene que ser un numero entero.");
            return false;
        }
        if(valor.isEmpty()){
            editText.setError("Este campo es obligatorio");
            return false;
        }else{
            if(valorNumerico<minNumber || valorNumerico>maxNumber){
                editText.setError("El valor tiene que estar entre "+minNumber+ " y "+maxNumber);
                return false;
            }else{
                return true;
            }
        }
    }
    public static boolean validarEditTextDouble(int idView,View rootView,double minNumber){
        EditText editText=((EditText) rootView.findViewById(idView));
        String valor=Utilidades.getValorTextoEditText(rootView, idView);
        double valorNumerico=0.0;
        try{
            valorNumerico=Double.parseDouble(valor);
        }catch (Exception e){
            editText.setError("El valor tiene que ser un numero decimal o entero.");
            return false;
        }
        if(valor.isEmpty()){
            editText.setError("Este campo es obligatorio");
            return false;
        }else{
            if(valorNumerico<minNumber){
                editText.setError("El valor tiene que ser mayor o igual a "+minNumber);
                return false;
            }else{
                return true;
            }
        }
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    public static Long obtenerValorSeleccionadoSpinner(View rootView,int idSpinner){
        Spinner spinner = (Spinner) rootView.findViewById(idSpinner);
        int posicion = spinner.getSelectedItemPosition();
        Long id = ((Pair<Long, String>) spinner.getItemAtPosition(posicion)).first;
        return id;
    }
    public static void cargarImagen(FragmentActivity fragmentActivity,ImageView imageView,String nombreImagen){
        int idImageLoader = fragmentActivity.getResources().getIdentifier("com.primeraimpresion.subastaropa:drawable/download_img", null, null);


        String url=Config.URL_BASE_IMAGE+"/"+nombreImagen;
        Log.e("URL IMAGENES:",url);
        Picasso.with(fragmentActivity)
                .load(url)
                .placeholder(idImageLoader)
                .resize(300, 300)
                .centerCrop()
                .into(imageView);
    }
    public static void cargarImagenLocal(Activity activity,ImageView imageView,int idDrawable){
        Uri path2 = Uri.parse("android.resource://com.primeraimpresion.subastaropa/" + idDrawable);
        Picasso.with(activity)
                .load(path2)
                .resize(500,110)
                .into(imageView);
    }


    public static void cargarImagenItemSubasta(FragmentActivity fragmentActivity,ImageView imageView,String nombreImagen,int ancho,int alto)
    {
        int idImageLoader = fragmentActivity.getResources().getIdentifier("com.primeraimpresion.subastaropa:drawable/download_img", null, null);
        String url=Config.URL_BASE_IMAGE+"/"+nombreImagen;
        Log.e("URL IMAGENES:",url);
        Picasso.with(fragmentActivity)
                .load(url)
                .placeholder(idImageLoader)
                .resize(ancho, alto)
                .centerCrop()
                .into(imageView);
    }
    public static Date sumarRestarDiasFecha(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }

    public static File persistImage(File file,Activity activity) {
        //getActivity().getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()).getAbsolutePath();

        Uri uri = Uri.fromFile(file);

        //ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), uri);
            File filesDir = activity.getFilesDir();
            File imageFile = new File(filesDir, file.getName() + ".jpg");

            Bitmap bitmapSize=resizeImage(bitmap);


            OutputStream os;
            os = new FileOutputStream(imageFile);

            bitmapSize.compress(Bitmap.CompressFormat.JPEG, 80, os);
            os.flush();
            os.close();
            return imageFile;
        } catch (Exception e) {
            Log.e("Utilidades persistImage", "Error writing bitmap", e);
        }
        return null;
    }
    public static Bitmap resizeImage(Bitmap myBitmap){
        final int maxSize = 1000;
        if(myBitmap.getWidth()>maxSize) {

            int outWidth;
            int outHeight;
            int inWidth = myBitmap.getWidth();
            int inHeight = myBitmap.getHeight();
            if (inWidth > inHeight) {
                outWidth = maxSize;
                outHeight = (inHeight * maxSize) / inWidth;
            } else {
                outHeight = maxSize;
                outWidth = (inWidth * maxSize) / inHeight;
            }

            Bitmap resizedBitmap = Bitmap.createScaledBitmap(myBitmap, outWidth, outHeight, false);
            return resizedBitmap;
        }
        return myBitmap;
    }

}
