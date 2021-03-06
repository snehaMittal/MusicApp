package com.javahelps.musicapp;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth firebaseAuth ;
    private RecyclerView mRecyclerView  , mRecylerView2;
    private RecyclerView.LayoutManager mlayoutManager;
    private RecyclerView.Adapter madapter;
    private ArrayList<String> mDataSet ;
    private AlbumAdapter adapter ;
    private List<Album> albumsList ;
    private String image[] = {"https://www.google.co.in/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwjP2Pq6o8vXAhXKP48KHY08DfsQjRwIBw&url=https%3A%2F%2Fwww.allposters.com%2F&psig=AOvVaw0PEShbC2TlQ0ARaPb_bX0n&ust=1511202814147065",
    "https://www.google.co.in/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwjP2Pq6o8vXAhXKP48KHY08DfsQjRwIBw&url=https%3A%2F%2Fwww.allposters.com%2F&psig=AOvVaw0PEShbC2TlQ0ARaPb_bX0n&ust=1511202814147065" ,
    "https://www.google.co.in/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwjP2Pq6o8vXAhXKP48KHY08DfsQjRwIBw&url=https%3A%2F%2Fwww.allposters.com%2F&psig=AOvVaw0PEShbC2TlQ0ARaPb_bX0n&ust=1511202814147065" ,
    "https://www.google.co.in/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwjP2Pq6o8vXAhXKP48KHY08DfsQjRwIBw&url=https%3A%2F%2Fwww.allposters.com%2F&psig=AOvVaw0PEShbC2TlQ0ARaPb_bX0n&ust=1511202814147065"};

    ViewPager viewPager ;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this , LoginActivity.class));
        }

        mRecyclerView = (RecyclerView)findViewById(R.id.recylerview);
        mRecyclerView.setHasFixedSize(true);
        mDataSet = new ArrayList<>();
        for (int i=0 ; i<30 ; i++){
            mDataSet.add("New title#" + i);
        }
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this , image);
        viewPager.setAdapter(viewPagerAdapter);
        mlayoutManager = new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false);
        mRecyclerView.setLayoutManager(mlayoutManager);
        madapter = new MainAdapter(mDataSet) ;
        mRecyclerView.setAdapter(madapter);
        mDataSet = new ArrayList<>();
        for (int i=0 ; i<30 ; i++){
            mDataSet.add("New title#" + i);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        albumsList = new ArrayList<>();
        adapter = new AlbumAdapter(this, albumsList, new AlbumAdapter.Onclicklistener() {
            @Override
            public void newActivity(int position, View v) {
                Intent intent = new Intent(MainActivity2.this , SavedSongList.class);
                startActivity(intent);
            }
        });
        setSupportActionBar(toolbar);



        prepareAlbums();
        mRecylerView2 = (RecyclerView)findViewById(R.id.recylerview2);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this ,2 );
        mRecylerView2.setLayoutManager(manager);

        mRecylerView2.addItemDecoration(new GridSpacingItemDecoration(2, true));
        mRecylerView2.setAdapter(adapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
        if (id == R.id.LogOut) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this , LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }



    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album3,
                R.drawable.album4};

        Album a = new Album("Maroon5", 13, covers[0]);
        albumsList.add(a);


        a = new Album("Sugar Ray", 8, covers[1]);
        albumsList.add(a);

        a = new Album("Bon Jovi", 11, covers[2]);
        albumsList.add(a);

        a = new Album("The Corrs", 12, covers[3]);
        albumsList.add(a);


        adapter.notifyDataSetChanged();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount,  boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = 10;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}
