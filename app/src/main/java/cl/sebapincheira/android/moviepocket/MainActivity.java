package cl.sebapincheira.android.moviepocket;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

/*
Tabs + viewpager build using http://www.androidhive.info/2015/09/android-material-design-working-with-tabs/
 */

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    public RecyclerView.Adapter vAdapterLocal = new AdapterGrid();
    RecyclerView vRecyclerView;
    RecyclerView.LayoutManager vLayoutManager;
    CloudFetchMovieList vCloudMovieList;
    private ProgressBar vProgressBar;

    private Toolbar vToolbar_main;
    private TabLayout vTabLayout;
    private ViewPager vViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vToolbar_main = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the vToolbar_main object
        setSupportActionBar(vToolbar_main); // Setting vToolbar_main as the ActionBar with setSupportActionBar() call

        vViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(vViewPager);

        vTabLayout = (TabLayout) findViewById(R.id.tabs);
        vTabLayout.setupWithViewPager(vViewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MostPopularFragment(), "MOST POPULAR");
        adapter.addFragment(new HighestRatedFragment(), "HIGHEST RATED");
        /*
        Adding for app-stage 2
        adapter.addFragment(new FavouritesFragment(), "FAVOURITES");
        */
        viewPager.setAdapter(adapter);
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

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
