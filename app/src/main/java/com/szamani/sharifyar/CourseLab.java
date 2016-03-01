package com.szamani.sharifyar;

import android.content.Context;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Szamani on 8/4/2015.
 */
public class CourseLab {
    private Context context;
    private static CourseLab courseLab;
    private List<Course> enrolledCourses;
    private List<Course> allCourses;
    private Map<Integer, Map<Integer, List<Course>>> courseTree;
    /////////konkur_uni////omoomi-ekhtes////dar//////////
    /////////0_1///////////0_1///////////////////////////
    private CourseJSONSerializer serializer;

    private CourseLab(Context context) {
        this.context = context;

        serializer = new CourseJSONSerializer(context, Utils.FILE_NAME);

        try {
            enrolledCourses = serializer.loadCourses();
        } catch (Exception e) {
            enrolledCourses = new ArrayList<>();
        }
    }

    public boolean saveCourses() {
        try {
            serializer.saveCourses(enrolledCourses);
            return true;
        } catch (JSONException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public List<Course> getCoursesByType(Integer type) {
        List<Course> courseList = new ArrayList<>();

        for (Course c : allCourses)
            if (c.getType() == type)
                courseList.add(c);

        return courseList;
    }

    public List<Course> getCoursesByTypeAndCategory(Integer type, int category) {
        List<Course> courseList = new ArrayList<>();

        for (Course c : allCourses)
            if (c.getType() == type&& c.getCategory() == category)
                courseList.add(c);

        return courseList;

    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }

    public Map<Integer, List<Course>> getCourseCategory(Integer type) {
        return courseTree.get(type);
    }

    public Map<Integer, Map<Integer, List<Course>>> getCourseTree() {
        return courseTree;
    }

    public void setCourseTree(Map<Integer, Map<Integer, List<Course>>> courseTree) {
        this.courseTree = courseTree;
    }

    public void setAllCourses(List<Course> allCourses) {
        this.allCourses = allCourses;
    }

    public void addCourse(Course course) {
        enrolledCourses.add(course);
    }

    public static CourseLab getInstance(Context context) {
        if (courseLab == null)
            courseLab = new CourseLab(context.getApplicationContext());

        return courseLab;
    }
}
