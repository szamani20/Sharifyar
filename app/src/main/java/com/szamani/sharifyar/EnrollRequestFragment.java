package com.szamani.sharifyar;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import net.steamcrafted.loadtoast.LoadToast;

import java.util.List;

/**
 * Created by Szamani on 8/19/2015.
 */
public class EnrollRequestFragment extends Fragment {
    private EditText editName;
    private EditText editFamily;
    private EditText editEmail;
    private EditText editPhone;
    private Spinner spinner;
    private ImageView imViewOk;
    private int clickCounter = 0;
    private int type, group, child;

    public EnrollRequestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_enroll_request, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        editName = (EditText) getActivity().findViewById(R.id.editName);
        editFamily = (EditText) getActivity().findViewById(R.id.editFamily);
        editEmail = (EditText) getActivity().findViewById(R.id.editEmail);
        editPhone = (EditText) getActivity().findViewById(R.id.editPhone);
        spinner = (Spinner) getActivity().findViewById(R.id.spinner);
        imViewOk = (ImageView) getActivity().findViewById(R.id.imBtnOK);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        type = getArguments().getInt("Type");
        group = getArguments().getInt("Group");
        child = getArguments().getInt("Child");

        setSpinnerDefault(type, group, child, spinner);

        final LoadToast toast = new LoadToast(getActivity());
        toast.setBackgroundColor(getResources().getColor(R.color.backgroundColor)).
                setProgressColor(getResources().getColor(R.color.progressColor));
        toast.setText(getResources().getString(R.string.connecting));

        imViewOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clickCounter % 25 == 0 && clickCounter != 0)
                    Toast.makeText(getActivity(),
                            R.string.troll, Toast.LENGTH_SHORT).show();

                if (!isNetworkAvailable(getActivity())) {
                    if (clickCounter % 5 == 0)
                        Toast.makeText(getActivity(),
                                R.string.no_internet, Toast.LENGTH_SHORT).show();
                    ++clickCounter;
                    return;
                }

                if (editName.getText().toString().equals("") ||
                        editFamily.getText().toString().equals("") ||
                        editEmail.getText().toString().equals("") ||
                        editPhone.getText().toString().equals("")) {
                    if (clickCounter % 5 == 0)
                        Toast.makeText(getActivity(),
                                R.string.fill_completely, Toast.LENGTH_SHORT).show();
                    ++clickCounter;
                    return;
                }

                Course course = new Course(editName.getText().toString(),
                        editFamily.getText().toString(),
                        editEmail.getText().toString(),
                        editPhone.getText().toString(),
                        spinner.getSelectedItem().toString());

                setCourseDescriptionAndLogo(course, spinner.getSelectedItemPosition());

                if (checkForInvalidDate(course)) {
                    if (clickCounter % 5 == 0)
                        Toast.makeText(getActivity(),
                                R.string.invalid_data, Toast.LENGTH_SHORT).show();
                    ++clickCounter;
                    return;
                }

                if (checkForDuplicateRequest(course)) {
                    if (clickCounter % 5 == 0)
                        Toast.makeText(getActivity(),
                                R.string.duplicate_data, Toast.LENGTH_SHORT).show();
                    ++clickCounter;
                    return;
                }

                TelephonyManager tManager = (TelephonyManager) (getActivity().getSystemService(Context.TELEPHONY_SERVICE));
                String uid = tManager.getDeviceId();

                if (!
                        securityCheck(uid)) {
                    if (clickCounter % 5 == 0)
                        Toast.makeText(getActivity(),
                                R.string.block, Toast.LENGTH_LONG).show();
                    ++clickCounter;
                    return;
                }

                CourseLab.getInstance(getActivity()).addCourse(course);

                ParseObject testObject = new ParseObject("TestObject");
                testObject.put("Name", editName.getText().toString());
                testObject.put("Family", editFamily.getText().toString());
                testObject.put("Email", editEmail.getText().toString());
                testObject.put("Phone", editPhone.getText().toString());
                testObject.put("Course", spinner.getSelectedItem().toString());
                testObject.put("DeviceID", uid);
                toast.show();

                testObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            toast.success();
                            Toast.makeText(getActivity(),
                                    R.string.success, Toast.LENGTH_LONG).show();
                        } else toast.error();
                    }
                });

            }
        });
    }

    private void setSpinnerDefault(Integer type, int group, int child, Spinner spinner) {
        if (type == 1) { // unniversity
            switch (group) {
                case 0:
                    switch (child) {
                        case 0:
                            spinner.setSelection(0);
                            break;
                        case 1:
                            spinner.setSelection(1);
                            break;
                        case 2:
                            spinner.setSelection(2);
                            break;
                        case 3:
                            spinner.setSelection(3);
                            break;
                        case 4:
                            spinner.setSelection(4);
                            break;
                        case 5:
                            spinner.setSelection(5);
                            break;
                        default:
                            break;
                    }
                    break;

                case 1:
                    switch (child) {
                        case 0:
                            spinner.setSelection(6);
                            break;
                        case 1:
                            spinner.setSelection(7);
                            break;
                        default:
                            break;
                    }
                    break;

                case 2:
                    switch (child) {
                        case 0:
                            spinner.setSelection(8);
                            break;
                        case 1:
                            spinner.setSelection(9);
                            break;
                        case 2:
                            spinner.setSelection(10);
                            break;
                        case 3:
                            spinner.setSelection(11);
                            break;
                        case 4:
                            spinner.setSelection(12);
                            break;
                        case 5:
                            spinner.setSelection(13);
                            break;
                        case 6:
                            spinner.setSelection(14);
                            break;
                        case 7:
                            spinner.setSelection(15);
                            break;
                        default:
                            break;
                    }
                    break;

                default:
                    break;
            }
        } else { // konkur
            switch (group) {
                case 0:
                    switch (child) {
                        default:
                            break;
                    }
                    break;

                case 1:
                    switch (child) {
                        case 0:
                            spinner.setSelection(16);
                            break;
                        case 1:
                            spinner.setSelection(17);
                            break;
                        case 2:
                            spinner.setSelection(18);
                            break;
                        case 3:
                            spinner.setSelection(19);
                            break;
                        case 4:
                            spinner.setSelection(20);
                            break;
                        case 5:
                            spinner.setSelection(21);
                            break;
                        case 6:
                            spinner.setSelection(22);
                            break;
                        case 7:
                            spinner.setSelection(23);
                            break;
                        case 8:
                            spinner.setSelection(24);
                            break;
                        case 9:
                            spinner.setSelection(25);
                            break;
                        case 10:
                            spinner.setSelection(26);
                            break;
                        case 11:
                            spinner.setSelection(27);
                            break;
                        case 12:
                            spinner.setSelection(28);
                            break;
                        case 13:
                            spinner.setSelection(29);
                            break;
                        case 14:
                            spinner.setSelection(30);
                            break;
                        case 15:
                            spinner.setSelection(31);
                            break;
                        case 16:
                            spinner.setSelection(32);
                            break;
                        default:
                            break;
                    }

                default:
                    break;
            }
        }
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private boolean checkForDuplicateRequest(Course course) {
        List<Course> temporaryList = CourseLab.getInstance(getActivity()).getEnrolledCourses();

        for (Course temp : temporaryList)
            if (temp.getStudentName().equals(course.getStudentName()) &&
                    temp.getStudentFamily().equals(course.getStudentFamily()) &&
                    temp.getStudentEmail().equals(course.getStudentEmail()) &&
                    temp.getStudentPhone().equals(course.getStudentPhone()) &&
                    temp.getCourseName().equals(course.getCourseName()))
                return true;

        return false;
    }

    private boolean checkForInvalidDate(Course course) {
        // TODO: CHECK FOR INVALID DATA

        return course.getStudentName().length() < 3 ||
                course.getStudentName().length() > 15 ||
                course.getStudentFamily().length() < 3 ||
                course.getStudentFamily().length() > 25 ||
                !course.getStudentEmail().matches(Utils.EMAIL_REGEX) ||
                (course.getStudentPhone().length() != 11 && course.getStudentPhone().length() != 12);
    }

    private boolean securityCheck(String id) {
        if (CourseLab.getInstance(getActivity()).getEnrolledCourses().size() >= 10)
            return false;

        if (Utils.block == null)
            return true;

        if (Utils.block.contains(id))
            return false;

        return true;
    }

    private void setCourseDescriptionAndLogo(Course course, int position) {
        course.setDescription(CourseLab.getInstance(getActivity())
                .getAllCourses().get(position).getDescription());

        course.setLogo(CourseLab.getInstance(getActivity())
                .getAllCourses().get(position).getLogo());
    }
}
