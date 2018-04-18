package mz.co.insystems.mobicare.activities.farmacia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.base.BaseActivity;
import mz.co.insystems.mobicare.common.RecyclerTouchListener;
import mz.co.insystems.mobicare.common.SearchResultAdaper;
import mz.co.insystems.mobicare.common.SearchbleObject;
import mz.co.insystems.mobicare.model.farmacia.Farmacia;
import mz.co.insystems.mobicare.model.farmacia.servicos.Servico;
import mz.co.insystems.mobicare.model.farmaco.Farmaco;
import mz.co.insystems.mobicare.model.user.User;
import mz.co.insystems.mobicare.sync.MobicareSyncService;
import mz.co.insystems.mobicare.sync.SyncError;
import mz.co.insystems.mobicare.sync.VolleyResponseListener;
import mz.co.insystems.mobicare.util.Utilities;

public class SearchActivity extends BaseActivity implements Runnable{

    private MaterialSearchView searchView;
    private Thread searchThread;
    private String searchQuery;
    private boolean farmaciaSearchDone;
    private boolean servicoSearchDone;
    private boolean farmacoSearchDone;
    private  List<SearchbleObject> searchbleObjectList;
    private RecyclerView recyclerView;
    private SearchResultAdaper searchResultAdaper;

    public SearchActivity() {
        this.searchbleObjectList  = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        setCurrentUser(new User());
        getCurrentUser().setUserName("844441662");
        getCurrentUser().setPassword("1000");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView = findViewById(R.id.search_view);
        searchView.setVoiceSearch(true);
        searchView.setCursorDrawable(R.drawable.color_cursor_white);
        searchView.setEllipsize(true);
        //searchView.setSuggestionIcon();
        //searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                doSerarch(query);
                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
                        .show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    private void doSerarch(String query) {
        showLoading(SearchActivity.this, null, getString(R.string.searching));
        this.searchbleObjectList.clear();

        setSearchQuery(query.trim());
        if (Utilities.isNetworkAvailable(SearchActivity.this)){
            doWebSearch();
        }else {
            doLocalSearch();
        }
    }

    private void doLocalSearch() {

    }

    private void doWebSearch() {
        searchThread = new Thread(this);
        searchThread.start();
    }


    private void searchFarmaco(Uri.Builder uri) {
        uri.appendPath(Farmaco.TABLE_NAME_FARMACO);
        uri.appendPath(MobicareSyncService.SERVICE_SEARCH);
        uri.appendPath(this.searchQuery);
        final String url = uri.build().toString();

        getService().makeJsonArrayRequest(Request.Method.GET, url, null, getCurrentUser(), new VolleyResponseListener() {
            @Override
            public void onError(SyncError error) {
                setFarmacoSearchDone(true);
            }

            @Override
            public void onResponse(JSONObject response, int myStatusCode) { }

            @Override
            public void onResponse(JSONArray response, int myStatusCode) {
                for(int i=0;i<response.length();i++){

                    try {
                        if (response.getJSONObject(i).has("message")){

                        }else {
                            searchbleObjectList.add(new Farmaco().fromJsonObject(response.getJSONObject(i)));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                setFarmacoSearchDone(true);
            }
        });
    }

    private void searchServico(Uri.Builder uri) {
        uri.appendPath(Servico.TABLE_NAME_SERVICO);
        uri.appendPath(MobicareSyncService.SERVICE_SEARCH);
        uri.appendPath(this.searchQuery);
        final String url = uri.build().toString();

        getService().makeJsonArrayRequest(Request.Method.GET, url, null, getCurrentUser(), new VolleyResponseListener() {
            @Override
            public void onError(SyncError error) {
                setServicoSearchDone(true);
            }

            @Override
            public void onResponse(JSONObject response, int myStatusCode) { }

            @Override
            public void onResponse(JSONArray response, int myStatusCode) {
                for(int i=0;i<response.length();i++){

                    try {
                        if (response.getJSONObject(i).has("message")){

                        }else {
                            searchbleObjectList.add(new Servico().fromJsonObject(response.getJSONObject(i)));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                setServicoSearchDone(true);
            }
        });
    }

    private void searchFarmacia(Uri.Builder uri) {
        uri.appendPath(Farmacia.TABLE_NAME_FARMACIA);
        uri.appendPath(MobicareSyncService.SERVICE_SEARCH);
        uri.appendPath(this.searchQuery);
        final String url = uri.build().toString();

        getService().makeJsonArrayRequest(Request.Method.GET, url, null, getCurrentUser(), new VolleyResponseListener() {
            @Override
            public void onError(SyncError error) {
                setFarmaciaSearchDone(true);
            }

            @Override
            public void onResponse(JSONObject response, int myStatusCode) { }

            @Override
            public void onResponse(JSONArray response, int myStatusCode) {
                for(int i=0;i<response.length();i++){

                    try {
                        if (response.getJSONObject(i).has("message")){

                        }else {
                            searchbleObjectList.add(new Farmacia().fromJsonObject(response.getJSONObject(i)));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                setFarmaciaSearchDone(true);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void run() {
        final Uri.Builder uri = service.initServiceUri();

        //Implement Limits on data loading
        searchFarmacia(uri);
        searchFarmaco(uri);
        searchServico(uri);

        try {
            searchThread.join(2000);
            if (!this.isSearchFinished()) searchThread.join(2000);
            if (!this.isSearchFinished()) searchThread.join(2000);
            hideLoading();
            displaySearchResults();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void displaySearchResults() {
        if (Utilities.listHasElements(this.searchbleObjectList)){

            recyclerView = findViewById(R.id.recycler_view);

            searchResultAdaper = new SearchResultAdaper(searchbleObjectList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
            recyclerView.setAdapter(searchResultAdaper);

            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    SearchbleObject searchbleObject = searchbleObjectList.get(position);
                    //check instance and procced
                    if (searchbleObject instanceof Farmacia){

                    }else if (searchbleObject instanceof Servico){

                    }else if (searchbleObject instanceof Farmaco){

                    }

                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));

            //searchResultAdaper.notifyDataSetChanged();
        }else {

        }
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public boolean isSearchFinished(){
        return this.isFarmaciaSearchDone() && this.isFarmacoSearchDone() && this.isServicoSearchDone();
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public boolean isFarmaciaSearchDone() {
        return farmaciaSearchDone;
    }

    public void setFarmaciaSearchDone(boolean farmaciaSearchDone) {
        this.farmaciaSearchDone = farmaciaSearchDone;
    }

    public boolean isServicoSearchDone() {
        return servicoSearchDone;
    }

    public void setServicoSearchDone(boolean servicoSearchDone) {
        this.servicoSearchDone = servicoSearchDone;
    }

    public boolean isFarmacoSearchDone() {
        return farmacoSearchDone;
    }

    public void setFarmacoSearchDone(boolean farmacoSearchDone) {
        this.farmacoSearchDone = farmacoSearchDone;
    }
}
