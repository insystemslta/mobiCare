package mz.co.insystems.mobicare.sync;

import org.json.JSONObject;

/**
 * Created by Voloide Tamele on 10/9/2017.
 */
public interface VolleyResponseListener {

    void onError(String message);

    void onResponse(JSONObject response, int myStatusCode);
}
