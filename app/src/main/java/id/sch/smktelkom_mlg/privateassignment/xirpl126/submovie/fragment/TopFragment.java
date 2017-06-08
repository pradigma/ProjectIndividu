package id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.adapter.SourceAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.model.Source;
import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.model.SourcesResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {
    ArrayList<Source> mList = new ArrayList<>();
    SourceAdapter mAdapter;

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewTop);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SourceAdapter(this.getActivity(), mList);
        recyclerView.setAdapter(mAdapter);

        downloadDataSource();
    }

    private void downloadDataSource() {
        String url = "http://api.themoviedb.org/3/movie/top_rated?&api_key=0adb847d130269a4bd2a9c6bbc106ba0";

        GsonGetRequest<SourcesResponse> myRequest = new GsonGetRequest<SourcesResponse>
                (url, SourcesResponse.class, null, new Response.Listener<SourcesResponse>() {

                    @Override
                    public void onResponse(SourcesResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        if (response.page.equals("1")) {
                            mList.addAll(response.results);
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(myRequest);
    }

}