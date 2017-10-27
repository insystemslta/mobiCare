package mz.co.insystems.mobicare.util;

import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import mz.co.insystems.mobicare.model.entidade.user.User;
import mz.co.insystems.mobicare.sync.NetworkController;
import mz.co.insystems.mobicare.sync.VolleyResponseListener;

/**
 * Created by Voloide Tamele on 10/20/2017.
 */
public class Utilities {
    public static final int RESULT_CANCELED = 0;
    public static final int RESULT_FAIL = -1;
    public static final int RESULT_OK = 1;


    public static final String API_VERSION = "v1.0";
    public static final String URI_AUTHORITY = "mobicare.insystems.co.mz/"+API_VERSION+"/index.php/";

    public static final String USER_GET_BY_FULL_CREDENTIALS	= "user/getFullCredentials";

    public static final String JSON_OBJECT_REQUEST_TAG = "json_obj_req";
    private static int myStatusCode;

    private static MessageDigest digester;

    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    public static String MD5Crypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }

        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            }
            else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }

    public static void makeJsonObjectRequest(int method, String url, final User user, final VolleyResponseListener listener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (method, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onResponse(response, myStatusCode);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error.toString());
                    }
                }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    myStatusCode = response.statusCode;
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    return Response.success(new JSONObject(jsonString),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return buildAuthHeaders(user);
            }
        };
        // Access the RequestQueue through singleton class.
        NetworkController.getInstance().addToRequestQueue(jsonObjectRequest, JSON_OBJECT_REQUEST_TAG);
    }

    public static Map<String, String> buildAuthHeaders(User user){

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        String credentials = user.getUserName() + ":" + user.getPassword();
        String base64EncodedCredentials =
                Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        headerMap.put("Authorization", "Basic " + base64EncodedCredentials);
        return headerMap;
    }



}
