package es.jdamiancabello.agendadeestudio.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import es.jdamiancabello.agendadeestudio.R;

public class DashboardFragment extends Fragment {
    public static final String TAG = "DashboardFragment";
    private ImageButton ibSubjectList, ibEventsList;
    private onDashboardListener activityListener;

    public static Fragment newInstance() {
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_dashboard,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ibSubjectList = view.findViewById(R.id.Dashboard_ibSubject);
        ibSubjectList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityListener.showSubjectsList();
            }
        });

        ibEventsList = view.findViewById(R.id.Dashboard_ibEventList);
        ibEventsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListener.showEventsList();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activityListener = (onDashboardListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityListener = null;
    }

    interface onDashboardListener{
        void showSubjectsList();
        void showEventsList();
    }


}
