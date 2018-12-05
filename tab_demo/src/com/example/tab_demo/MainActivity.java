package com.example.tab_demo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.tabFrameLayout);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab111").setIndicator("Tab 1",
                        getResources().getDrawable(android.R.drawable.star_on)),
                FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab222").setIndicator("Tab 2",
                        getResources().getDrawable(android.R.drawable.star_on)),
                FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab333").setIndicator("Tab 3",
                        getResources().getDrawable(android.R.drawable.star_on)),
                FragmentTab.class, null);
    }
}