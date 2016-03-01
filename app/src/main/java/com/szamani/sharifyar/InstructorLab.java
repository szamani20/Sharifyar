package com.szamani.sharifyar;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szamani on 8/5/2015.
 */
public class InstructorLab {
    private Context context;
    private static InstructorLab instructorLab;
    private List<Instructor> instructorList;

    private InstructorLab(Context context) {
        this.context = context;

        if (instructorList == null)
            instructorList = new ArrayList<>();
    }

    public List<Instructor> getInstructorList() {
        return instructorList;
    }

    public void addInstructor(Instructor instructor) {
        instructorList.add(instructor);
    }

    public static InstructorLab getInstance(Context context) {
        if (instructorLab == null)
            instructorLab = new InstructorLab(context);

        return instructorLab;
    }
}
