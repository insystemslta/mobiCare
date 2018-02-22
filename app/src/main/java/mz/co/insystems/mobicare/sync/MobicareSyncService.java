package mz.co.insystems.mobicare.sync;

import android.net.Uri;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import mz.co.insystems.mobicare.model.user.User;

/**
 * Created by Voloide Tamele on 11/7/2017.
 */

public class MobicareSyncService {

    public static final int RESULT_CANCELED = 0;
    public static final int RESULT_FAIL = -1;
    public static final int RESULT_OK = 1;


    public static final String API_VERSION = "v1.0";
    public static final String URI_AUTHORITY = "mobicare.insystems.co.mz/"+API_VERSION;

    public static final String URI_AUTHORITY_TEST = "192.168.100.30";

    public static final String URL_SERVICE_USER_GET_BY_CREDENTIALS	= "user/getFullCredentials";
    public static final String SERVICE_ENTITY_USER = "user";
    public static final String SERVICE_ENTITY_CONTACT = "contacto";

    public static final String SERVICE_CREATE = "create";

    public static final String JSON_OBJECT_REQUEST_TAG = "json_obj_req";
    private static int myStatusCode;

    public Uri.Builder initServiceUri(){
        Uri.Builder uri =  new Uri.Builder();
        uri.scheme("http");
        uri.authority(URI_AUTHORITY_TEST);
        uri.appendPath("mobicare");
        uri.appendPath("index.php");
        return uri;
    }

    public void makeJsonObjectRequest(final int method, String url, final JSONObject requestBody , final User user, final VolleyResponseListener listener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (method, url, requestBody, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onResponse(response, myStatusCode);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NetworkError) {
                        } else if (error instanceof ServerError) {
                        } else if (error instanceof AuthFailureError) {
                        } else if (error instanceof ParseError) {
                        } else if (error instanceof NoConnectionError) {
                        } else if (error instanceof TimeoutError) {}
                        listener.onError(error.toString());
                    }
                }) {

            @Override
            public String getBodyContentType() {
               return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody.toString().getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody, "utf-8");
                    return null;
                }
            }

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
        String credentials = user.getUserName() + ":" + user.getPassword();
        String base64EncodedCredentials =
                Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        headerMap.put("Authorization", "Basic " + base64EncodedCredentials);
        return headerMap;
    }
}
