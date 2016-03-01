package com.szamani.sharifyar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szamani on 8/12/2015.
 */
public class InstructorsFragment extends Fragment {
    private ListView listInstructors;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_instructor, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listInstructors = (ListView) getActivity().findViewById(R.id.listInstructors);

        List<String> instructorNameList = new ArrayList<>();
        List<Integer> instructorLogoList = new ArrayList<>();
        List<Instructor> instructorList = InstructorLab.getInstance(getActivity()).getInstructorList();

        for (int i = 0; i < instructorList.size(); i++) {
            instructorNameList.add(instructorList.get(i).getName());
            instructorLogoList.add(instructorList.get(i).getLogo());
        }

        MyAdapter adapter = new MyAdapter(instructorNameList,
                 instructorLogoList);

        try {
            listInstructors.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "No instructor yet!",
                    Toast.LENGTH_LONG).show();
            return;
        }

        listInstructors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), InstructorActivity.class);
                intent.putExtra(Utils.EXTRA_INTENT_ABOUT_US, position);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });

        listInstructors.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    public class MyAdapter extends ArrayAdapter<String> {
        private List<String> instructorNameList;
        private List<Integer> instructorLogoList;

        public MyAdapter(List<String> instructorNameList,
                         List<Integer> instructorLogoList) {
            super(getActivity(), R.layout.single_about_us);

            this.instructorNameList = instructorNameList;
            this.instructorLogoList = instructorLogoList;
        }

        @Override
        public int getCount() {
            return instructorNameList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getActivity()
                    .getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.single_about_us, parent, false);

            TextView textInstructorName = (TextView)
                    view.findViewById(R.id.text_instructor_name);
            ImageView imViewInstructorLogo = (ImageView)
                    view.findViewById(R.id.im_view_instructor_pic);

            textInstructorName.setText(instructorNameList.get(position));
            imViewInstructorLogo.setImageResource(instructorLogoList.get(position));

            return view;
        }
    }
}
