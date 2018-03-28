package mz.co.insystems.mobicare.base;

import android.databinding.BaseObservable;

import java.io.Serializable;



/**
 * Created by Voloide Tamele on 9/19/2017.
 */
public class BaseVO extends BaseObservable implements Serializable {


    private  boolean keybordOpen;

    public BaseVO() {
    }

    public boolean isKeybordOpen() {
        return keybordOpen;
    }

    public void setKeybordOpen(boolean keybordOpen) {
        this.keybordOpen = keybordOpen;
    }
}
