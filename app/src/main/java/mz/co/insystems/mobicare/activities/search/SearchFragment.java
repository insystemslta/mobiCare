package mz.co.insystems.mobicare.activities.search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.common.RecyclerTouchListener;
import mz.co.insystems.mobicare.common.SearchResultAdaper;
import mz.co.insystems.mobicare.model.farmacia.Farmacia;
import mz.co.insystems.mobicare.model.farmacia.servicos.Servico;
import mz.co.insystems.mobicare.model.farmaco.Farmaco;
import mz.co.insystems.mobicare.model.search.Searchble;
import mz.co.insystems.mobicare.util.Utilities;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private List<Searchble> searchbleList;
    private RecyclerView recyclerView;
    private SearchResultAdaper searchResultAdaper;
    private View view;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_search, container, false);
        this.searchbleList = ((SearchActivity)getActivity()).getSearchbleList();
        displaySearchResults();
        return view;
    }

    public void notifyDataHasChanged(){
        this.searchbleList = ((SearchActivity)getActivity()).getSearchbleList();
        displaySearchResults();
    }

    private void displaySearchResults() {
        if (Utilities.listHasElements(this.searchbleList)){

            recyclerView = view.findViewById(R.id.recycler_view);

            searchResultAdaper = new SearchResultAdaper(searchbleList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            recyclerView.setAdapter(searchResultAdaper);

            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Searchble searchble = searchbleList.get(position);
                    //check instance and procced
                    if (searchble instanceof Farmacia){

                    }else if (searchble instanceof Servico){

                    }else if (searchble instanceof Farmaco){

                    }

                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));

            searchResultAdaper.notifyDataSetChanged();
        }else {

        }
    }

}
