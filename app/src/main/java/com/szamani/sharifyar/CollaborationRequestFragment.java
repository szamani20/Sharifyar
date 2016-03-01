package com.szamani.sharifyar;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Szamani on 8/12/2015.
 */
public class CollaborationRequestFragment extends Fragment {
    private EditText editName;
    private EditText editFamily;
    private EditText editEmail;
    private EditText editPhone;
    private EditText editTeachingBackground;
    private Spinner spinnerEducationState;
    private Spinner spinnerUniversity;
    private EditText editTeacherRequestCourse;
    private EditText editMoreExplanation;
    private ImageView imViewOK;
    private int clickCounter = 0;

    public CollaborationRequestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collaboration_request, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        editName = (EditText) getActivity().findViewById(R.id.editName);
        editFamily = (EditText) getActivity().findViewById(R.id.editFamily);
        editEmail = (EditText) getActivity().findViewById(R.id.editEmail);
        editPhone = (EditText) getActivity().findViewById(R.id.editPhone);
        editTeachingBackground = (EditText) getActivity().findViewById(R.id.editTeachingBackground);
        spinnerEducationState = (Spinner) getActivity().findViewById(R.id.spinnerEducationState);
        spinnerUniversity = (Spinner) getActivity().findViewById(R.id.spinnerUniversity);
        editTeacherRequestCourse = (EditText) getActivity().findViewById(R.id.editTeacherRequestCourse);
        editMoreExplanation = (EditText) getActivity().findViewById(R.id.editMoreExplanation);
        imViewOK = (ImageView) getActivity().findViewById(R.id.imViewOK);

        ArrayAdapter<CharSequence> eduAdapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.education_state, android.R.layout.simple_spinner_item);
        eduAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEducationState.setAdapter(eduAdapter);

        ArrayAdapter<CharSequence> universityAdapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.university, android.R.layout.simple_spinner_item);
        universityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUniversity.setAdapter(universityAdapter);

        imViewOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickCounter % 50 == 0 && clickCounter != 0)
                    Toast.makeText(getActivity(),
                            R.string.troll, Toast.LENGTH_SHORT).show();

                if (!isNetworkAvailable(getActivity())) {
                    if (clickCounter % 10 == 0)
                        Toast.makeText(getActivity(),
                                R.string.no_internet, Toast.LENGTH_SHORT).show();
                    ++clickCounter;
                    return;
                }

                if (editName.getText().toString().equals("") ||
                        editFamily.getText().toString().equals("") ||
                        editEmail.getText().toString().equals("") ||
                        editPhone.getText().toString().equals("")) {
                    if (clickCounter % 10 == 0)
                        Toast.makeText(getActivity(),
                                R.string.fill_completely, Toast.LENGTH_SHORT).show();
                    ++clickCounter;
                    return;
                }

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + "szamani.sharifyar@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Collaboration");
                intent.putExtra(Intent.EXTRA_TEXT,
                        editName.getText().toString() + "\n" +
                                editFamily.getText().toString() + "\n" +
                                editEmail.getText().toString() + "\n" +
                                editPhone.getText().toString() + "\n" +
                                editTeachingBackground.getText().toString() + "\n" +
                                spinnerEducationState.getSelectedItem().toString() + "\n" +
                                spinnerUniversity.getSelectedItem().toString() + "\n" +
                                editTeacherRequestCourse.getText().toString() + "\n" +
                                editMoreExplanation.getText().toString() + "\n");

                try {
                    startActivity(Intent.createChooser(intent, "Send mail using..."));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "No Email Client Found", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
