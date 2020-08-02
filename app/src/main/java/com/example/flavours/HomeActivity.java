package com.example.flavours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private boolean isInFront;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private HomeFragment homeFragment;
    private CartFragment cartFragment;


    @Override
    public void onResume() {
        super.onResume();
        isInFront = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isInFront = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar=findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Flavours");

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        mMainFrame=findViewById(R.id.main_frame);
        mMainNav=findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        cartFragment = new CartFragment();
        setFragment(homeFragment);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.menu1cart:
//                if (!isInFront)
//                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//                else
//                    drawerLayout.closeDrawer(GravityCompat.START);
                setFragment(cartFragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu1history:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu1offers:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu2share:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu2feedback:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu2help:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu3logout:
                userLogout();
                break;
        }
        return true;
    }
    private void userLogout() {
        Intent i= new Intent(this,LoginActivity.class);
        startActivity(i);
    }
}