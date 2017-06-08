package id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.DatabaseHelper;
import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.adapter.SavedAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.model.Saved;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaveFragment extends Fragment {
    private RecyclerView rv;
    private DatabaseHelper db;
    private SavedAdapter savedAdapter;
    private ArrayList<Saved> saved = new ArrayList<>();

    public SaveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        db = new DatabaseHelper(getContext());
        rv = (RecyclerView) getView().findViewById(R.id.recyclerViewSave);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        savedAdapter = new SavedAdapter(getActivity(), saved);
        fillData();
    }

    private void fillData() {
        saved.clear();
        Cursor c = db.getAllMovie();
        while (c.moveToNext()) {
            String title = c.getString(1);
            String desc = c.getString(2);

            Saved sve = new Saved(title, desc);
            saved.add(sve);

            if (!(saved.size() < 1)) {
                rv.setAdapter(savedAdapter);
            } else {
                rv.setVisibility(View.GONE);
            }
        }
    }

}
