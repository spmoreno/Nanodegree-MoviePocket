package cl.sebapincheira.android.moviepocket;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularFragment extends Fragment {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    public RecyclerView.Adapter vAdapterLocal = new AdapterGrid();
    RecyclerView vRecyclerView;
    RecyclerView.LayoutManager vLayoutManager;
    CloudFetchMovieList vCloudMovieList;
    private ProgressBar vProgressBar;


    public MostPopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_most_popular, container, false);


        //Progress bar
        vProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar_main);

        // Calling the RecyclerView
        vRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_main);
        vRecyclerView.setHasFixedSize(true);

        // The number of Columns
        vLayoutManager = new GridLayoutManager(this.getContext(), 2);
        vRecyclerView.setLayoutManager(vLayoutManager);


        //Load data from theMovieDB only when I don't have it.
        if (vAdapterLocal.getItemCount() == 0) {

            //Load MovieList
            vCloudMovieList = new FetchGrid();
            vCloudMovieList.getMovieList();
            vProgressBar.setVisibility(View.VISIBLE);

        } else {
            vProgressBar.setVisibility(View.GONE);
        }


        //vAdapterLocal.getItemCount();

        //Set adapter
        vRecyclerView.setAdapter(vAdapterLocal);

        return rootView;
    }

    public class FetchGrid extends CloudFetchMovieList {

        public FetchGrid() {

        }


        @Override
        protected void onPostExecute(AdapterGrid iResult) {


            if (iResult != null) {
                Log.i(LOG_TAG, "HOLA: " + "Movie list fetch finished! " + iResult.getItemCount());


                vAdapterLocal = iResult;


                vRecyclerView.setAdapter(vAdapterLocal);


                Toast.makeText(getContext(), "Movie list fetch finished!", Toast.LENGTH_SHORT).show();


            } else {
                Log.i(LOG_TAG, "HOLA: " + "There was a problem fetching movie list");
                Toast.makeText(getContext(), "There was a problem fetching movie list", Toast.LENGTH_SHORT).show();
            }

            vProgressBar.setVisibility(View.GONE);

        }

    }

}
