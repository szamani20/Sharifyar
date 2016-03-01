package com.szamani.sharifyar;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szamani on 8/4/2015.
 */
public class CourseJSONSerializer {
    private Context context;
    private String fileName;

    public CourseJSONSerializer(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

    public void saveCourses(List<Course> courseList) throws JSONException, IOException {
        JSONArray array = new JSONArray();

        for (Course course : courseList)
            array.put(course.toJSON());

        Writer writer = null;
        OutputStream outputStream = null;

        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(outputStream);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
            if (outputStream != null)
                outputStream.close();
        }

    }

    public List<Course> loadCourses() throws JSONException, IOException {
        List<Course> courseList = new ArrayList<>();
        BufferedReader reader = null;
        InputStream inputStream = null;

        try {
            inputStream = context.openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonString = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
                jsonString.append(line);

            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < array.length(); i++)
                courseList.add(new Course(array.getJSONObject(i)));

        } catch (FileNotFoundException e) {
        } finally {
            if (reader != null)
                reader.close();
            if (inputStream != null)
                inputStream.close();
        }

        return courseList;
    }
}
