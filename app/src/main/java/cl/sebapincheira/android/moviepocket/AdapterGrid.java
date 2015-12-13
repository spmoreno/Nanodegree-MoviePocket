package cl.sebapincheira.android.moviepocket;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seba on 12/12/2015.
 * Examples provided by http://www.exoguru.com/android/ui/recyclerview/custom-android-grids-using-recyclerview.html
 */
public class AdapterGrid extends RecyclerView.Adapter<AdapterGrid.ViewHolder> {

    public final List<ItemGrid> vMovieList;


    public AdapterGrid() {
        super();

        vMovieList = new ArrayList<ItemGrid>();

    }


    @Override
    public AdapterGrid.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ItemGrid nature = vMovieList.get(i);
        //viewHolder.vMovieName.setText("");//nature.getName());
        //viewHolder.imgThumbnail.setImageBitmap(null);
        Glide.with(viewHolder.imgThumbnail.getContext()).load(nature.mImageURI).into(viewHolder.imgThumbnail);
        viewHolder.itemView.setTag(nature);
        //viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
    }

    @Override
    public int getItemCount() {

        return vMovieList.size();
    }

    public List<ItemGrid> getMovieList() {
        return this.vMovieList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView vMovieName;
        public AlphaAnimation vAnimationAlpha;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            vAnimationAlpha = new AlphaAnimation(0.7f, 0.99f);
            vAnimationAlpha.setDuration(300);
            //vMovieName = (TextView)itemView.findViewById(R.id.tv_species);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "inside viewholder position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

                    v.startAnimation(vAnimationAlpha);
                }
            });
        }
    }

}
