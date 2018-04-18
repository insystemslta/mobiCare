package mz.co.insystems.mobicare.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Voloide Tamele on 4/17/2018.
 */

public class SearchResultAdaper extends RecyclerView.Adapter<SearchResultAdaper.MyViewHolder>{
    List<SearchbleObject> searchbleObjectList;

    public SearchResultAdaper(List<SearchbleObject> searchbleObjectList) {
        this.searchbleObjectList = searchbleObjectList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return searchbleObjectList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
