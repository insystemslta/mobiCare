package mz.co.insystems.mobicare.base;

import android.databinding.BaseObservable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


/**
 * Created by Voloide Tamele on 9/19/2017.
 */
public abstract class BaseVO extends BaseObservable implements Serializable {

    public abstract BaseVO convertVoFromJSON(JSONObject jsonObject) throws JSONException;
    public abstract JSONObject generateJsonObject() throws JSONException;
}
