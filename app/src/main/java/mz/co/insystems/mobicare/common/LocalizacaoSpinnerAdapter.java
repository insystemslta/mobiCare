package mz.co.insystems.mobicare.common;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mz.co.insystems.mobicare.R;

/**
 * Created by Voloide Tamele on 11/26/2017.
 */

public class LocalizacaoSpinnerAdapter extends ArrayAdapter {

    private Context context;
    private List dataList;
    LayoutInflater inflater;

    public LocalizacaoSpinnerAdapter(@NonNull Activity activity, int textViewResourceId, List dataList) {
        super(activity, textViewResourceId, dataList);
        this.context = activity.getApplicationContext();
        this.dataList = dataList;
        inflater = activity.getLayoutInflater();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.simple_spinner_item, parent, false);
        }
        TextView label = convertView.findViewById(R.id.label);
        label.setText(((LocalizacaoObject)dataList.get(position)).getDescricao());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.simple_spinner_item,parent, false);
        }

        TextView label = convertView.findViewById(R.id.label);
        label.setText(((LocalizacaoObject)dataList.get(position)).getDescricao());
        return convertView;
    }
}
