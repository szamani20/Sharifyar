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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

/**
 * Created by Szamani on 8/4/2015.
 */
public class AboutUsActivity extends ActionBarActivity {
    private ViewPager viewPager;
    private Toolbar toolbar;
    private SlidingTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setTitle(R.string.about_us_title);

        int tabPosition = getIntent().getIntExtra(Utils.TAB_POSITION, 0);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.about_us_pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        tabLayout = (SlidingTabLayout) findViewById(R.id.about_us_tab);
        tabLayout.setDistributeEvenly(true);
        tabLayout.setViewPager(viewPager);

        viewPager.setCurrentItem(tabPosition);

        ImageView floatingView = new ImageView(this);
        floatingView.setImageResource(R.drawable.action_button);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(floatingView)
                .build();

        ImageView iconEnroll = new ImageView(this);
        ImageView iconCourseReview = new ImageView(this);
        ImageView iconInstructors = new ImageView(this);

        iconEnroll.setImageResource(R.drawable.action_enroll);
        iconCourseReview.setImageResource(R.drawable.action_list);
        iconInstructors.setImageResource(R.drawable.action_ostad);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        SubActionButton buttonEnroll = itemBuilder.setContentView(iconEnroll).build();
        SubActionButton buttonCourseReview = itemBuilder.setContentView(iconCourseReview).build();
        SubActionButton buttonInstructor = itemBuilder.setContentView(iconInstructors).build();

        buttonEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutUsActivity.this, EnrollActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });

        buttonCourseReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutUsActivity.this, CourseReviewActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });

        buttonInstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(4);
            }
        });

        new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonEnroll)
                .addSubActionView(buttonCourseReview)
                .addSubActionView(buttonInstructor )
                .attachTo(actionButton)
                .build();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(AboutUsActivity.this) != null)
                    onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                    return new LowTimeFragment();
                case 1:
                    return new MassiveTodosFragment();
                case 2:
                    return new WhatTodoFragment();
                case 3:
                    return new AboutUsFragment();
                case 4:
                    return new InstructorsFragment();
                default:
                    return new Fragment();
            }
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.low_time);
                case 1:
                    return getResources().getString(R.string.massive_todo);
                case 2:
                    return getResources().getString(R.string.what_todo);
                case 3:
                    return getResources().getString(R.string.app_name);
                case 4:
                    return getResources().getString(R.string.teachers);
                default:
                    return "tab!";
            }
        }
    }

}
