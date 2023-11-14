package com.example.csis3175_grp6_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements SportsIconRecyclerViewAdapter.OnItemClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    List<SportsIcon> SportsIconList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerViewIcons = findViewById(R.id.recyclerViewSportsImgMain);
        SportsIconRecyclerViewAdapter adapter =  new SportsIconRecyclerViewAdapter(
                SportsIconList,
                this
        );

        GridLayoutManager gm = new GridLayoutManager(this, 3);
        recyclerViewIcons.setAdapter(adapter);
        recyclerViewIcons.setLayoutManager(gm);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    public void AddData() {
        SportsIconList.add(
                new SportsIcon(101, "Basketball", R.drawable.basketball)
        );
        SportsIconList.add(
                new SportsIcon(102, "Badminton", R.drawable.badminton)
        );
        SportsIconList.add(
                new SportsIcon(103, "Table Tennis", R.drawable.table_tennis)
        );
    }

    @Override
    public void onItemClick(int i) {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent myIntent;
        int itemId = item.getItemId();
        if (itemId == R.id.profile) {
            myIntent = new Intent(this, MyAccountActivity.class);
            startActivity(myIntent);

            return true;
        }
        else if (itemId == R.id.mybooking) {
            myIntent = new Intent(this, MyBookingActivity.class);
            startActivity(myIntent);
        }
        return false;
    }
}