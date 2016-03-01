package com.szamani.sharifyar;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szamani on 8/4/2015.
 */
public class CourseEnrolledActivity extends ActionBarActivity {
    private ListView listEnrolledCourse;
    private Toolbar toolbar;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_enrolled);
        setTitle(R.string.course_enrolled_title);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        listEnrolledCourse = (ListView) findViewById(R.id.list_enrolled_course);

        List<Course> enrolledCourse = CourseLab.getInstance(CourseEnrolledActivity.this)
                .getEnrolledCourses();

        List<String> courseName = new ArrayList<>();
        List<String> courseDescription = new ArrayList<>();
        List<Integer> courseLogo = new ArrayList<>();

        for (Course course : enrolledCourse) {
            courseName.add(course.getCourseName());
            courseDescription.add(course.getDescription());
            courseLogo.add(course.getLogo());
        }

        MyAdapter adapter = new MyAdapter(courseName, courseDescription, courseLogo);

        try {
            listEnrolledCourse.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(CourseEnrolledActivity.this,
                    R.string.no_course_enrolled, Toast.LENGTH_LONG).show();
            return;
        }

        listEnrolledCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(CourseEnrolledActivity.this) != null) {
                    NavUtils.navigateUpFromSameTask(CourseEnrolledActivity.this);
                    overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
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

    public class MyAdapter extends ArrayAdapter<String> {
        List<String> courseName;
        List<String> courseDescription;
        List<Integer> courseLogo;

        public MyAdapter(List<String> courseName,
                         List<String> courseDescription, List<Integer> courseLogo) {

            super(CourseEnrolledActivity.this, R.layout.single_course_review);

            this.courseName = courseName;
            this.courseDescription = courseDescription;
            this.courseLogo = courseLogo;
        }

        @Override
        public int getCount() {
            return courseName.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) CourseEnrolledActivity.this
                    .getSystemService(CourseEnrolledActivity.this.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.single_course_review, parent, false);

            TextView textCourseName = (TextView)
                    view.findViewById(R.id.text_course_name);
            TextView textCourseDescription = (TextView)
                    view.findViewById(R.id.text_course_description);
            ImageView imViewCourseLogo = (ImageView)
                    view.findViewById(R.id.im_view_course_icon);

            textCourseName.setText(courseName.get(position));
            textCourseDescription.setText(courseDescription.get(position));
            imViewCourseLogo.setImageResource(courseLogo.get(position));

            return view;
        }
    }
}
