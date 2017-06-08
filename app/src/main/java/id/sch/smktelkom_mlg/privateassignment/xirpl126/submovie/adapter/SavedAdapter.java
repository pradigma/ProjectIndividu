package id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.model.Saved;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.ViewHolder> {
    Context context;
    ArrayList<Saved> savedList;

    public SavedAdapter(Context context, ArrayList<Saved> savedList) {
        this.context = context;
        this.savedList = savedList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.save_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTitle.setText(savedList.get(position).getTitle());
        holder.tvDesc.setText(savedList.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return savedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.viewTitle);
            tvDesc = (TextView) itemView.findViewById(R.id.viewDesc);
        }
    }
}
