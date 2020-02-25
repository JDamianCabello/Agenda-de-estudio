package es.jdamiancabello.agendadeestudio.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
    private ImageButton ibSubjectList, ibEventsList, ibSettings, ibChrono,ibSchedule, ibNotes, loggout;
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

        ibSubjectList = view.findViewById(R.id.Dashboard_ibSubjectList);
        ibSubjectList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityListener.showSubjectsList();
            }
        });

        ibEventsList = view.findViewById(R.id.Dashboard_ibEventsList);
        ibEventsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListener.showEventsList();
            }
        });

        ibSettings = view.findViewById(R.id.Dashboard_ibSettings);
        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListener.ShowSettingsView();
            }
        });

        ibChrono = view.findViewById(R.id.Dashboard_ibChrono);
        ibChrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListener.ShowChronoView();
            }
        });

        ibSchedule = view.findViewById(R.id.Dashboard_ibSchedule);
        ibSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListener.ShowScheduleView();
            }
        });

        ibNotes= view.findViewById(R.id.Dashboard_notes);
        ibNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListener.showNoteView();
            }
        });

        loggout = view.findViewById(R.id.loggout);
        loggout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListener.loggout();
            }
        });



        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //activityListener = (onDashboardListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityListener = null;
    }

    interface onDashboardListener{
        void showSubjectsList();
        void showEventsList();
        void ShowChronoView();
        void ShowSettingsView();
        void ShowScheduleView();
        void showNoteView();
        void loggout();
    }


}
