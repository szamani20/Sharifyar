package com.szamani.sharifyar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Szamani on 8/11/2015.
 */
public class KonkurFragment extends Fragment {
    private ExpandableListView expandableListView;

    public KonkurFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_konkur, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        expandableListView = (ExpandableListView) getActivity().findViewById(R.id.expandable_konkur);
        MyAdapter adapter = new MyAdapter(getActivity(), CourseLab.getInstance(getActivity()).getCourseCategory(0));
        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(getActivity(), EnrollActivity.class);
                intent.putExtra(Utils.EXTRA_INTENT_ENROLL_GROUP, groupPosition);
                intent.putExtra(Utils.EXTRA_INTENT_ENROLL_CHILD, childPosition);
                intent.putExtra(Utils.EXTRA_INTENT_ENROLL_TYPE, 0);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                return true;
            }
        });

    }

    class MyAdapter extends BaseExpandableListAdapter {
        private Context context;
        private Map<Integer, List<Course>> courseCategory;

        public MyAdapter(Context context, Map<Integer, List<Course>> courseCategory) {
            this.context = context;
            this.courseCategory = courseCategory;
        }

        @Override
        public int getGroupCount() {
            return courseCategory.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return courseCategory.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return courseCategory.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return courseCategory.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null)
                convertView = inflater.inflate(R.layout.single_sub_list_course, null);

            TextView textCategory = (TextView)
                    convertView.findViewById(R.id.textCourseCategory);

            switch (groupPosition) {
                case 0:
                    textCategory.setText(getResources().getString(R.string.omoomi));
                    break;
                case 1:
                    textCategory.setText(getResources().getString(R.string.ekhesasi));
                    break;
            }

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null)
                convertView = inflater.inflate(R.layout.single_course_review, null);

            TextView textCourseName = (TextView)
                    convertView.findViewById(R.id.text_course_name);
            TextView textCourseDescription = (TextView)
                    convertView.findViewById(R.id.text_course_description);
            ImageView imViewCourseLogo = (ImageView)
                    convertView.findViewById(R.id.im_view_course_icon);

            textCourseName.setText(courseCategory.get(groupPosition).get(childPosition).getCourseName());
            textCourseDescription.setText(courseCategory.get(groupPosition).get(childPosition).getDescription());
            imViewCourseLogo.setImageResource(courseCategory.get(groupPosition).get(childPosition).getLogo());

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
