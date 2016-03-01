package com.szamani.sharifyar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.andexert.library.RippleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ActionBarActivity {
    private RippleView rippleAboutUs;
    private RippleView rippleCourseReview;
    private RippleView rippleEnroll;
    private RippleView rippleCollaborate;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        initializeAllCourses();
        initializeCourseTree();
        initializeAllInstructors();

        toolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);

        rippleAboutUs = (RippleView) findViewById(R.id.imViewAboutUs);
        rippleCourseReview = (RippleView) findViewById(R.id.imViewCourseList);
        rippleEnroll = (RippleView) findViewById(R.id.imViewEnroll);
        rippleCollaborate = (RippleView) findViewById(R.id.imViewCollaborate);

        rippleAboutUs.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });

        rippleCourseReview.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent intent = new Intent(MainActivity.this, CourseReviewActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });

        rippleEnroll.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent intent = new Intent(MainActivity.this, EnrollActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });

        rippleCollaborate.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent intent = new Intent(MainActivity.this, CollaborationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
        System.exit(0);
    }

    private void initializeAllInstructors() {

        InstructorLab.getInstance(MainActivity.this).addInstructor(new Instructor(
                getResources().getString(R.string.zamani),
                getResources().getString(R.string.zamani_honor),
                getResources().getString(R.string.zamani_teaching_background),
                R.drawable.zamani));

        InstructorLab.getInstance(MainActivity.this).addInstructor(new Instructor(
                getResources().getString(R.string.jalali),
                getResources().getString(R.string.jalali_honor),
                getResources().getString(R.string.jalali_teaching_background),
                R.drawable.jalali));

        InstructorLab.getInstance(MainActivity.this).addInstructor(new Instructor(
                getResources().getString(R.string.faraji),
                getResources().getString(R.string.faraji_honor),
                getResources().getString(R.string.faraji_teaching_background),
                R.drawable.faraji));

        InstructorLab.getInstance(MainActivity.this).addInstructor(new Instructor(
                getResources().getString(R.string.rezai),
                getResources().getString(R.string.rezai_honor),
                getResources().getString(R.string.rezai_teaching_background),
                R.drawable.rezai));

        InstructorLab.getInstance(MainActivity.this).addInstructor(new Instructor(
                getResources().getString(R.string.taheri),
                getResources().getString(R.string.taheri_honor),
                getResources().getString(R.string.taheri_teaching_background),
                R.drawable.taheri));

        InstructorLab.getInstance(MainActivity.this).addInstructor(new Instructor(
                getResources().getString(R.string.naiji),
                getResources().getString(R.string.naiji_honor),
                getResources().getString(R.string.naiji_teaching_background),
                R.drawable.naiji));

        InstructorLab.getInstance(MainActivity.this).addInstructor(new Instructor(
                getResources().getString(R.string.karimipour),
                getResources().getString(R.string.karimipour_honor),
                getResources().getString(R.string.karimipour_teaching_background),
                R.drawable.karimipour));

        InstructorLab.getInstance(MainActivity.this).addInstructor(new Instructor(
                getResources().getString(R.string.barooni),
                getResources().getString(R.string.barooni_honor),
                getResources().getString(R.string.barooni_teaching_background),
                R.drawable.barooni
        ));

        InstructorLab.getInstance(MainActivity.this).addInstructor(new Instructor(
                getResources().getString(R.string.atashi),
                getResources().getString(R.string.atashi_honor),
                getResources().getString(R.string.atashi_teaching_background),
                R.drawable.atashi
        ));

    }

    private void initializeCourseTree() {
        Map<Integer, Map<Integer, List<Course>>> courseTree = new HashMap<>();
        Map<Integer, List<Course>> konkurType = new HashMap<>();
        Map<Integer, List<Course>> universityType = new HashMap<>();

        konkurType.put(0, CourseLab.getInstance(MainActivity.this)
                .getCoursesByTypeAndCategory(0, 0));
        konkurType.put(1, CourseLab.getInstance(MainActivity.this)
                .getCoursesByTypeAndCategory(0, 1));
        universityType.put(0, CourseLab.getInstance(MainActivity.this)
                .getCoursesByTypeAndCategory(1, 0));
        universityType.put(1, CourseLab.getInstance(MainActivity.this)
                .getCoursesByTypeAndCategory(1, 1));
        universityType.put(2, CourseLab.getInstance(MainActivity.this)
                .getCoursesByTypeAndCategory(1, 2));

        courseTree.put(0, konkurType);
        courseTree.put(1, universityType);

        CourseLab.getInstance(MainActivity.this).setCourseTree(courseTree);  // holy fuck!

    }


    private void initializeAllCourses() {
        List<Course> allCourses = new ArrayList<>();
        allCourses.add(new Course(
                getResources().getString(R.string.java_zamani),
                getResources().getString(R.string.java_des),
                new Instructor(getResources().getString(R.string.zamani), getResources().getString(R.string.zamani_honor)),
                "",
                R.drawable.java,
                1,
                0
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.java_swing_zamani),
                getResources().getString(R.string.swing_des),
                new Instructor(getResources().getString(R.string.zamani), getResources().getString(R.string.zamani_honor)),
                "",
                R.drawable.javaswing,
                1,
                0
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.javafx_zamani),
                getResources().getString(R.string.javafx_des),
                new Instructor(getResources().getString(R.string.zamani), getResources().getString(R.string.zamani_honor)),
                "",
                R.drawable.javafx,
                1,
                0
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.java_rezai),
                getResources().getString(R.string.java_des),
                new Instructor(getResources().getString(R.string.rezai), getResources().getString(R.string.rezai_honor)),
                "",
                R.drawable.java,
                1,
                0
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.c_faraji),
                getResources().getString(R.string.c_des),
                new Instructor(getResources().getString(R.string.faraji), getResources().getString(R.string.faraji_honor)),
                "",
                R.drawable.c,
                1,
                0
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.c_rezai),
                getResources().getString(R.string.c_des),
                new Instructor(getResources().getString(R.string.rezai), getResources().getString(R.string.rezai_honor)),
                "",
                R.drawable.c,
                1,
                0
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.discrete_rezai),
                getResources().getString(R.string.discrete_des),
                new Instructor(getResources().getString(R.string.rezai), getResources().getString(R.string.rezai_honor)),
                "",
                R.drawable.discrete,
                1,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.madar_faraji),
                getResources().getString(R.string.madar_des),
                new Instructor(getResources().getString(R.string.faraji), getResources().getString(R.string.faraji_honor)),
                "",
                R.drawable.madar,
                1,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.gen_pysics_taheri),
                getResources().getString(R.string.gen_physics_des),
                new Instructor(getResources().getString(R.string.taheri), getResources().getString(R.string.taheri_honor)),
                "",
                R.drawable.gen_physics,
                1,
                2
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.gen_pysics_barooni),
                getResources().getString(R.string.gen_physics_des),
                new Instructor(getResources().getString(R.string.barooni), getResources().getString(R.string.barooni_honor)),
                "",
                R.drawable.gen_physics,
                1,
                2
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.gen_math_taheri),
                getResources().getString(R.string.gen_math_des),
                new Instructor(getResources().getString(R.string.taheri), getResources().getString(R.string.taheri_honor)),
                "",
                R.drawable.gen_math,
                1,
                2
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.gen_math_barooni),
                getResources().getString(R.string.gen_math_des),
                new Instructor(getResources().getString(R.string.barooni), getResources().getString(R.string.barooni_honor)),
                "",
                R.drawable.gen_math,
                1,
                2
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.gen_math_rezai),
                getResources().getString(R.string.gen_math_des),
                new Instructor(getResources().getString(R.string.rezai), getResources().getString(R.string.rezai_honor)),
                "",
                R.drawable.gen_math,
                1,
                2
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.dif_equations_taheri),
                getResources().getString(R.string.dif_equations_des),
                new Instructor(getResources().getString(R.string.taheri), getResources().getString(R.string.taheri_honor)),
                "",
                R.drawable.differential_equation,
                1,
                2
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.dif_equations_barooni),
                getResources().getString(R.string.dif_equations_des),
                new Instructor(getResources().getString(R.string.barooni), getResources().getString(R.string.barooni_honor)),
                "",
                R.drawable.differential_equation,
                1,
                2
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.gen_chemistry_taheri),
                getResources().getString(R.string.gen_chemistry_des),
                new Instructor(getResources().getString(R.string.taheri), getResources().getString(R.string.taheri_honor)),
                "",
                R.drawable.gen_chemistry,
                1,
                2
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.dif_jalali),
                getResources().getString(R.string.dif_des),
                new Instructor(getResources().getString(R.string.jalali), getResources().getString(R.string.jalali_honor)),
                "",
                R.drawable.dif,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.dif_taheri),
                getResources().getString(R.string.dif_des),
                new Instructor(getResources().getString(R.string.taheri), getResources().getString(R.string.taheri_honor)),
                "",
                R.drawable.dif,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.dif_naiji),
                getResources().getString(R.string.dif_des),
                new Instructor(getResources().getString(R.string.naiji), getResources().getString(R.string.naiji_honor)),
                "",
                R.drawable.dif,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.dif_karimipour),
                getResources().getString(R.string.dif_des),
                new Instructor(getResources().getString(R.string.karimipour), getResources().getString(R.string.karimipour_honor)),
                "",
                R.drawable.dif,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.physics_jalali),
                getResources().getString(R.string.physics_des),
                new Instructor(getResources().getString(R.string.jalali), getResources().getString(R.string.jalali_honor)),
                "",
                R.drawable.physics,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.physics_taheri),
                getResources().getString(R.string.physics_des),
                new Instructor(getResources().getString(R.string.taheri), getResources().getString(R.string.taheri_honor)),
                "",
                R.drawable.physics,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.physics_naiji),
                getResources().getString(R.string.physics_des),
                new Instructor(getResources().getString(R.string.naiji), getResources().getString(R.string.naiji_honor)),
                "",
                R.drawable.physics,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.physics_karimipour),
                getResources().getString(R.string.physics_des),
                new Instructor(getResources().getString(R.string.karimipour), getResources().getString(R.string.karimipour_honor)),
                "",
                R.drawable.physics,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.basic_math_jalali),
                getResources().getString(R.string.basic_math_des),
                new Instructor(getResources().getString(R.string.jalali), getResources().getString(R.string.jalali_honor)),
                "",
                R.drawable.basic_math,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.basic_math_taheri),
                getResources().getString(R.string.basic_math_des),
                new Instructor(getResources().getString(R.string.taheri), getResources().getString(R.string.taheri_honor)),
                "",
                R.drawable.basic_math,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.gen_geometry_taheri),
                getResources().getString(R.string.gen_geometry_des),
                new Instructor(getResources().getString(R.string.taheri), getResources().getString(R.string.taheri_honor)),
                "",
                R.drawable.gen_geometry,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.gen_geometry_barooni),
                getResources().getString(R.string.gen_geometry_des),
                new Instructor(getResources().getString(R.string.barooni), getResources().getString(R.string.barooni_honor)),
                "",
                R.drawable.gen_geometry,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.analytic_geometry_taheri),
                getResources().getString(R.string.analytic_geometry_des),
                new Instructor(getResources().getString(R.string.taheri), getResources().getString(R.string.taheri_honor)),
                "",
                R.drawable.analytic_geometry,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.analytic_geometry_barooni),
                getResources().getString(R.string.analytic_geometry_des),
                new Instructor(getResources().getString(R.string.barooni), getResources().getString(R.string.barooni_honor)),
                "",
                R.drawable.analytic_geometry,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.statistics_taheri),
                getResources().getString(R.string.statistics_des),
                new Instructor(getResources().getString(R.string.taheri), getResources().getString(R.string.taheri_honor)),
                "",
                R.drawable.statistics,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.chemistry_taheri),
                getResources().getString(R.string.chemistry_des),
                new Instructor(getResources().getString(R.string.taheri), getResources().getString(R.string.taheri_honor)),
                "",
                R.drawable.chemistry,
                0,
                1
        ));

        allCourses.add(new Course(
                getResources().getString(R.string.chemistry_atashi),
                getResources().getString(R.string.chemistry_des),
                new Instructor(getResources().getString(R.string.atashi), getResources().getString(R.string.atashi_honor)),
                "",
                R.drawable.chemistry,
                0,
                1
        ));

        CourseLab.getInstance(MainActivity.this).setAllCourses(allCourses);
    }
}