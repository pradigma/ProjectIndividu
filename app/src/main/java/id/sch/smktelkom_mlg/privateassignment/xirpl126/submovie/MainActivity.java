package id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.fragment.NowFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.fragment.SaveFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl126.submovie.fragment.TopFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        changePage(R.id.nav1);
        navigationView.setCheckedItem(R.id.nav1);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        changePage(id);
        return true;
    }

    private void changePage(int id) {
        Fragment fragment = null;
        if (id == R.id.nav1) {
            fragment = new NowFragment();
            setTitle("Now Playing");
        } else if (id == R.id.nav2) {
            fragment = new TopFragment();
            setTitle("Top Rated");
        } else if (id == R.id.nav3) {
            fragment = new SaveFragment();
            setTitle("Saved");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commitNow();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
