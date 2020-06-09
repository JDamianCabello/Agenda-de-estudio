package es.jdamiancabello.agendadeestudio.ui.topicinfo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.jdamiancabello.agendadeestudio.R;


public class TopicInfoFragment extends Fragment {
    private static final String TAG = "TopicInfoFragment";
    private OnfragmentIntercationsListener mListener;

    public static TopicInfoFragment newInstance(Bundle bundle) {
        TopicInfoFragment fragment = new TopicInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topic_info, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mListener = (OnfragmentIntercationsListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnfragmentIntercationsListener{
        void onBack();
    }
}