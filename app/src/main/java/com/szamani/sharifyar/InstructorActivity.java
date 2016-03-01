package com.szamani.sharifyar;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Szamani on 8/5/2015.
 */
public class InstructorActivity extends ActionBarActivity {
    private TextView textInstructorName;
    private TextView textInstructorHonor;
    private TextView textInstructorTeachingBackground;
    private ImageView imViewInstructorLogo;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
        setTitle(R.string.about_instructor);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        textInstructorName = (TextView)
                findViewById(R.id.textInstructorName);
        textInstructorHonor = (TextView)
                findViewById(R.id.textInstructorHonor);
        textInstructorTeachingBackground = (TextView)
                findViewById(R.id.textInstructorTeachingBackground);
        imViewInstructorLogo = (ImageView)
                findViewById(R.id.imViewInstructorLogo);

        int index = getIntent().getIntExtra(Utils.EXTRA_INTENT_ABOUT_US, 0);

        textInstructorName.setText(InstructorLab.getInstance(InstructorActivity.this)
                .getInstructorList().get(index).getName());
        textInstructorHonor.setText(InstructorLab.getInstance(InstructorActivity.this)
                .getInstructorList().get(index).getHonors());
        textInstructorTeachingBackground.setText(InstructorLab.getInstance(InstructorActivity.this)
                .getInstructorList().get(index).getTeachingBackground());
        imViewInstructorLogo.setImageResource(InstructorLab.getInstance(InstructorActivity.this)
                .getInstructorList().get(index).getLogo());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(InstructorActivity.this) != null) {
                    onBackPressed();
                }
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
}
