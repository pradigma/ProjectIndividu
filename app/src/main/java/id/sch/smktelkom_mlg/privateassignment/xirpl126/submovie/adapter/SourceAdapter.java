package id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.DatabaseHelper;
import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.model.Source;


public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder> {
    public static final String IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w500";
    ArrayList<Source> list;
    Context context;

    public SourceAdapter(Context context, ArrayList<Source> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Source source = list.get(position);
        holder.tvName.setText(source.title);
        holder.tvDesc.setText(source.overview);
        //holder.itemView.setBackgroundColor(source.color);
        Glide.with(context)
                .load(IMAGE_URL_BASE_PATH + source.poster_path)
                .into(holder.ivPoster);

        holder.ibLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = source.title;
                String desc = source.overview;
                DatabaseHelper db = new DatabaseHelper(context);
                if (db.saveMovie(title, desc)) {
                    Toast.makeText(context, "Berhasil menyimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView tvName;
        TextView tvDesc;
        ImageButton ibLove;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.textViewTitle);
            tvDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            ivPoster = (ImageView) itemView.findViewById(R.id.imageViewBanner);
            ibLove = (ImageButton) itemView.findViewById(R.id.imageButtonLove);
        }
    }
}
