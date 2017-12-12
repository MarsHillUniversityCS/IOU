package cs421.cs.mhu.edu.iou.listdebts;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import cs421.cs.mhu.edu.iou.R;

public class ViewDebtListActivity extends AppCompatActivity {
    TabPageAdapter mAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_debt_list);

        mAdapter = new TabPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.iou_viewpager);
        mViewPager.setAdapter(mAdapter);

        TabLayout tLayout = findViewById(R.id.iou_tabs);
        tLayout.addTab(tLayout.newTab().setText("Tab 1"));
        tLayout.addTab(tLayout.newTab().setText("Tab 2"));
        tLayout.setupWithViewPager(mViewPager);

    }

}
