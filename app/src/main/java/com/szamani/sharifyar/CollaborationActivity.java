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
 * Created by Szamani on 8/12/2015.
 */
public class CollaborationActivity extends ActionBarActivity {
    private ViewPager viewPager;
    private Toolbar toolbar;
    private SlidingTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaborration);
        setTitle(R.string.collaborate);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.collaboration_pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabLayout = (SlidingTabLayout) findViewById(R.id.collaboration_tabs);
        tabLayout.setDistributeEvenly(true);
        tabLayout.setViewPager(viewPager);

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
                Intent intent = new Intent(CollaborationActivity.this, EnrollActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });

        buttonCourseReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollaborationActivity.this, CourseReviewActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });

        buttonInstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollaborationActivity.this, AboutUsActivity.class);
                intent.putExtra(Utils.TAB_POSITION, 4);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonEnroll)
                .addSubActionView(buttonCourseReview)
                .addSubActionView(buttonInstructor )
                .attachTo(actionButton)
                .build();
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
                if (NavUtils.getParentActivityName(CollaborationActivity.this) != null)
                    onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CollaborationExplanationFragment();
                case 1:
                    return new CollaborationRequestFragment();
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
                    return getResources().getString(R.string.collaborate);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
