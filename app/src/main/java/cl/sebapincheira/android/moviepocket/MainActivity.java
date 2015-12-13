package cl.sebapincheira.android.moviepocket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    public RecyclerView.Adapter vAdapterLocal = new AdapterGrid();
    RecyclerView vRecyclerView;
    RecyclerView.LayoutManager vLayoutManager;
    CloudFetchMovieList vCloudMovieList;
    ProgressBar vSpinner;
    private Toolbar vToolbar_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vToolbar_main = (Toolbar) findViewById(R.id.main_toolbar); // Attaching the layout to the vToolbar_main object
        setSupportActionBar(vToolbar_main); // Setting vToolbar_main as the ActionBar with setSupportActionBar() call

        //Progress bar
        vSpinner = (ProgressBar) findViewById(R.id.progress_bar_main);

        // Calling the RecyclerView
        vRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_main);
        vRecyclerView.setHasFixedSize(true);

        // The number of Columns
        vLayoutManager = new GridLayoutManager(this, 2);
        vRecyclerView.setLayoutManager(vLayoutManager);


        //Load MovieList
        vCloudMovieList = new FetchGrid();
        vCloudMovieList.getMovieList();

        vAdapterLocal.getItemCount();

        //Set adapter
        vRecyclerView.setAdapter(vAdapterLocal);


        Log.i(LOG_TAG, "Numero items " + vAdapterLocal.getItemCount());// + " // Numero: " + vCloudMovieList.vAdapter.getItemCount());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class FetchGrid extends CloudFetchMovieList {

        public FetchGrid() {

        }

        @Override
        protected void onPreExecute() {
            vSpinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(AdapterGrid iResult) {


            if (iResult != null) {
                Log.i(LOG_TAG, "HOLA: " + "Movie list fetch finished! " + iResult.getItemCount());


                vAdapterLocal = iResult;


                vRecyclerView.setAdapter(vAdapterLocal);


                Toast.makeText(getApplicationContext(), "Movie list fetch finished!", Toast.LENGTH_SHORT).show();


            } else {
                Log.i(LOG_TAG, "HOLA: " + "There was a problem fetching movie list");
                Toast.makeText(getApplicationContext(), "There was a problem fetching movie list", Toast.LENGTH_SHORT).show();
            }

            vSpinner.setVisibility(View.GONE);

        }

    }

    /*
    public static class SpacingDecoration extends RecyclerView.ItemDecoration {



        //http://stackoverflow.com/questions/30524599/items-are-not-the-same-width-when-using-recyclerview-gridlayoutmanager-to-make-c

        private int mHorizontalSpacing = 5;
        private int mVerticalSpacing = 5;
        private boolean isSetMargin = true;

        public SpacingDecoration(int hSpacing, int vSpacing, boolean setMargin) {
            isSetMargin = setMargin;
            mHorizontalSpacing = hSpacing;
            mVerticalSpacing = vSpacing;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            boolean isSetMarginLeftAndRight = this.isSetMargin;
            int bottomOffset = mVerticalSpacing;
            int leftOffset = 0;
            int rightOffset = 0;

            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
            if (parent.getLayoutManager() instanceof GridLayoutManager) {
                GridLayoutManager lm = (GridLayoutManager) parent.getLayoutManager();
                GridLayoutManager.LayoutParams gridLp = (GridLayoutManager.LayoutParams) lp;

                if (gridLp.getSpanSize() == lm.getSpanCount()) {
                    // Current item is occupied the whole row
                    // We just need to care about margin left and right now
                    if (isSetMarginLeftAndRight) {
                        leftOffset = mHorizontalSpacing;
                        rightOffset = mHorizontalSpacing;
                    }
                } else {
                    // Current item isn't occupied the whole row
                    if (gridLp.getSpanIndex() > 0) {
                        // Set space between items in one row
                        leftOffset = mHorizontalSpacing;
                    } else if (gridLp.getSpanIndex() == 0 && isSetMarginLeftAndRight) {
                        // Set left margin of a row
                        //leftOffset = mHorizontalSpacing;
                        leftOffset = 0;
                    }
                    if (gridLp.getSpanIndex() == lm.getSpanCount() - gridLp.getSpanSize() && isSetMarginLeftAndRight) {
                        // Set right margin of a row
                        rightOffset = 0;
                        //rightOffset = mHorizontalSpacing;
                    }
                }
            }

            outRect.set(leftOffset, 0, rightOffset, bottomOffset);
        }
    }
*/


}
