package com.example.csis3175_grp6_proj.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.databases.LeisureLinkDatabase;
import com.example.csis3175_grp6_proj.models.Sport;
import com.example.csis3175_grp6_proj.models.SportsIcon;
import com.example.csis3175_grp6_proj.adapters.SportsIconRecyclerViewAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeActivity extends AppCompatActivity implements SportsIconRecyclerViewAdapter.OnItemClickListener, NavigationBarView.OnItemSelectedListener {
    List<SportsIcon> SportsIconList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;
    LeisureLinkDatabase lldb;
    List<Sport> SportsList = new ArrayList<>();
    RecyclerView recyclerViewIcons;
    SportsIconRecyclerViewAdapter adapter;
    GridLayoutManager gm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Populate Sports Grid Layout
        LoadSportsDataFromRoomDB();

        // Ads banner
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });
        AdView adViewHome = findViewById(R.id.adViewHome);
        AdRequest adRequest = new AdRequest.Builder().build();
        adViewHome.loadAd(adRequest);

        // Populate Bottom Nav Bar
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(HomeActivity.this);

    }

    public void LoadSportsDataFromRoomDB() {
        lldb = Room.databaseBuilder(getApplicationContext(), LeisureLinkDatabase.class,"leisurelink.db").build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                SportsList = lldb.sportDao().GetAllSports();
                for (Sport sport : SportsList) {
                    String sportImageName = sport.getSportName().toLowerCase().replace(" ", "_");
                    Log.d("icyfung", sportImageName);
                    SportsIconList.add(
                            new SportsIcon(
                                    Integer.parseInt(sport.getSportId()),
                                    sport.getSportName(),
                                    getResources().getIdentifier(sportImageName, "drawable", getPackageName())
                            )
                    );
                }
                RecyclerView recyclerViewIcons = findViewById(R.id.recyclerViewSportsImgHome);
                GridLayoutManager gm = new GridLayoutManager(HomeActivity.this, 3);
                adapter =  new SportsIconRecyclerViewAdapter(
                        SportsIconList,
                        HomeActivity.this
                );
                recyclerViewIcons.setAdapter(adapter);
                recyclerViewIcons.setLayoutManager(gm);
            }
        });

//
//        SportsIconList.add(
//                new SportsIcon(101, "Basketball", R.drawable.basketball)
//        );
//        SportsIconList.add(
//                new SportsIcon(102, "Badminton", R.drawable.badminton)
//        );
//        SportsIconList.add(
//                new SportsIcon(103, "Table Tennis", R.drawable.table_tennis)
//        );
    }

    @Override
    public void onItemClick(int i) {
        String sportName = SportsIconList.get(i).getImgName();

        Bundle bundle = new Bundle();
        bundle.putString("sport", sportName);
        Intent intent = new Intent(HomeActivity.this, SportsBookingActivity2.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent myIntent;
        int itemId = item.getItemId();
        if (itemId == R.id.profile) {
            myIntent = new Intent(this, MyAccountActivity.class);
            startActivity(myIntent);
            overridePendingTransition(0, 0);

            return true;
        }
        else if (itemId == R.id.mybooking) {
            myIntent = new Intent(this, MyBookingActivity.class);
            startActivity(myIntent);
            overridePendingTransition(0, 0);

            return true;
        } else if (itemId == R.id.home) {
            return true;
        }
        return false;
    }
}