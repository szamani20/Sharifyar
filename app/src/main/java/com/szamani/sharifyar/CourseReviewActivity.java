package com.szamani.sharifyar;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by Szamani on 8/4/2015.
 */

public class CourseReviewActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_review);
        setTitle(R.string.course_review_title);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.course_review_pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        tabLayout = (SlidingTabLayout) findViewById(R.id.course_review_tabs);
        tabLayout.setDistributeEvenly(true);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(CourseReviewActivity.this) != null)
                    onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new KonkurFragment();
                case 1:
                    return new UniversityFragment();
                default:
                    return new Fragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.konkur);
                case 1:
                    return getResources().getString(R.string.university);
                default:
                    return "";
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}