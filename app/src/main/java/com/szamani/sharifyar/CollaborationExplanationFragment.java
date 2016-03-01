package com.szamani.sharifyar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Szamani on 8/12/2015.
 */
public class CollaborationExplanationFragment extends Fragment {
    public CollaborationExplanationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collaboration_explanation, container, false);
    }
}
