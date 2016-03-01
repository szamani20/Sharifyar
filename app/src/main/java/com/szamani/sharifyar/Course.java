package com.szamani.sharifyar;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Szamani on 8/4/2015.
 */
public class Course {
    private String studentName;
    private String studentFamily;
    private String studentEmail;
    private String studentPhone;
    private String courseName;
    private String description;
    private String detail;
    private Instructor instructor;
    private Integer logo;
    private Integer type; // true for konkur, false for university
    private Integer category;

    // All course version constructor
    public Course(String courseName, String description,
                  Instructor instructor, String detail, Integer logo,
                  Integer type, Integer category) {
        this.courseName = courseName;
        this.description = description;
        this.instructor = instructor;
        this.detail = detail;
        this.logo = logo;
        this.type = type;
        this.category = category;
    }

    // Enroll version constructor
    public Course(String studentName, String studentFamily,
                  String studentEmail, String studentPhone,
                  String courseName) {
        this.studentName = studentName;
        this.studentFamily = studentFamily;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.courseName = courseName;
    }

    public Course(JSONObject object) {
        try {
            studentName = object.getString(Utils.JSON_NAME);
            studentFamily = object.getString(Utils.JSON_FAMILY);
            studentEmail = object.getString(Utils.JSON_EMAIL);
            studentPhone = object.getString(Utils.JSON_PHONE);
            courseName = object.getString(Utils.JSON_COURSE);
            description = object.getString(Utils.JSON_DESCRIPTION);
            logo = object.getInt(Utils.JSON_LOGO);
            category = object.getInt(Utils.JSON_CATEGORY);
            type = object.getInt(Utils.JSON_TYPE);
        } catch (JSONException e) {
        }
    }

    public Course() {
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();

        object.put(Utils.JSON_NAME, studentName);
        object.put(Utils.JSON_FAMILY, studentFamily);
        object.put(Utils.JSON_EMAIL, studentEmail);
        object.put(Utils.JSON_PHONE, studentPhone);
        object.put(Utils.JSON_COURSE, courseName);
        object.put(Utils.JSON_DESCRIPTION, description);
        object.put(Utils.JSON_LOGO, logo);
        object.put(Utils.JSON_CATEGORY, category);
        object.put(Utils.JSON_TYPE, type);

        return object;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentFamily() {
        return studentFamily;
    }

    public void setStudentFamily(String studentFamily) {
        this.studentFamily = studentFamily;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Integer getLogo() {
        return logo;
    }

    public void setLogo(Integer logo) {
        this.logo = logo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
