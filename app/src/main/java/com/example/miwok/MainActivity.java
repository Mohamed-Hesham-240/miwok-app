package com.example.miwok;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager myViewPager = findViewById(R.id.view_pager);
        //create the adapter
        myPagerAdapter adapter = new myPagerAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(adapter);
       // TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
       // tabLayout.setupWithViewPager(myViewPager);

    }

}
