package cs421.cs.mhu.edu.iou.listdebts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import cs421.cs.mhu.edu.iou.R;

public class ViewDebtListActivity extends AppCompatActivity {
    TabPageAdapter mAdapter;
    ViewPager mViewPager;

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 4242;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_debt_list);

        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                PERMISSIONS_REQUEST_READ_CONTACTS);

        mAdapter = new TabPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.iou_viewpager);
        mViewPager.setAdapter(mAdapter);

        TabLayout tLayout = findViewById(R.id.iou_tabs);
        tLayout.addTab(tLayout.newTab().setText("Tab 1"));
        tLayout.addTab(tLayout.newTab().setText("Tab 2"));
        tLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,
                        "Until permission is granted to read contacts, this app cannot function.",
                        Toast.LENGTH_LONG).show();
                finishAffinity();
            }
        }
    }

}
