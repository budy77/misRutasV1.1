package util;

/**
 * Created by RUDDY on 20/05/2017.
 */

public class Config {

    private static String dominio="http://192.168.1.36/rutastrufis";


    public static final String URL_BASE = dominio+"/public";



    public static final String FILE_UPLOAD_URL = dominio+"/public/subasta/misc/fileUpload";
    public static final String FILE_PICTURE_ACCOUNT_UPLOAD_URL = dominio+"/public/subasta/misc/pictureUserUpload";
    public static final String RESET_PASSWORD_URL = dominio+"/public/subasta/misc/resetPassword";

    public static final String URL_BASE_IMAGE = dominio+"/public/subasta/imagenes";
    public static final String URL_LOGIN = URL_BASE+"/login2";

    // Directory name to store captured images and videos
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";

    //Mensajería con firebase
    public static final String URL_REGISTER_DEVICE = URL_BASE + "/deviceStore";
    public static final String URL_SEND_SINGLE_PUSH = URL_BASE + "/mandarPushSimple";
    public static final String URL_SEND_MULTIPLE_PUSH = URL_BASE + "/mandarMultiplePush";
    public static final String URL_FETCH_DEVICES = URL_BASE + "/listaDispositivos";

}
