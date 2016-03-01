package com.szamani.sharifyar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Szamani on 8/4/2015.
 */
public class EnrollActivity extends ActionBarActivity {
    private ViewPager viewPager;
    private Toolbar toolbar;
    private SlidingTabLayout tabLayout;
    private int type, group, child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        setTitle(R.string.enroll_title);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        type = getIntent().getIntExtra(Utils.EXTRA_INTENT_ENROLL_TYPE, 0);
        group = getIntent().getIntExtra(Utils.EXTRA_INTENT_ENROLL_GROUP, 0);
        child = getIntent().getIntExtra(Utils.EXTRA_INTENT_ENROLL_CHILD, 0);

        viewPager = (ViewPager) findViewById(R.id.enroll_pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabLayout = (SlidingTabLayout) findViewById(R.id.enroll_tabs);
        tabLayout.setDistributeEvenly(true);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enroll_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_course:
                Intent intent = new Intent(EnrollActivity.this, CourseEnrolledActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                return true;
            case android.R.id.home:
                if (NavUtils.getParentActivityName(EnrollActivity.this) != null) {
                    onBackPressed();
                    overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        CourseLab.getInstance(EnrollActivity.this).saveCourses();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new EnrollExplanationFragment();
                case 1:
                    EnrollRequestFragment fragment = new EnrollRequestFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("Type", type);
                    bundle.putInt("Group", group);
                    bundle.putInt("Child", child);
                    fragment.setArguments(bundle);
                    return fragment;
                default:
                    return new Fragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.explanation);
                case 1:
                    return getResources().getString(R.string.enroll_title);
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
