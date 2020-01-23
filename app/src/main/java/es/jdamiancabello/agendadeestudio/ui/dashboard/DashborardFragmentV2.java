package es.jdamiancabello.agendadeestudio.ui.dashboard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.jdamiancabello.agendadeestudio.R;


public class DashborardFragmentV2 extends Fragment {
    public static final String TAG = "DashborardFragmentV2";
    private OnFragmentInteractionListener mListener;
    private BottomNavigationView menu;
    private FrameLayout container;

    public static DashborardFragmentV2 newInstance() {
        DashborardFragmentV2 fragment = new DashborardFragmentV2();
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
        return inflater.inflate(R.layout.fragment_dashborard_fragment_v2, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menu = view.findViewById(R.id.dashboard_menu);
        container = view.findViewById(R.id.dashboard_container);


        setTodayFragment();

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashborad_callendar:
                        mListener.showCallendar(R.id.dashboard_container);
                        break;
                    case R.id.dashboard_organicer:
                        mListener.showOrganicer(R.id.dashboard_container);
                        break;
                    case R.id.dashboard_today:
                        mListener.showToday(R.id.dashboard_container);
                        break;
                    case R.id.dashboard_subjects:
                        mListener.showSubjects(R.id.dashboard_container);
                        break;
                    case R.id.dashboard_tools:
                        mListener.showTools(R.id.dashboard_container);
                        break;
                }
                return true;
            }
        });
    }

    private void setTodayFragment() {
        menu.setSelectedItemId(R.id.dashboard_today);
        mListener.showToday(R.id.dashboard_container);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void showCallendar(int containerID);
        void showOrganicer(int containerID);
        void showToday(int containerID);
        void showSubjects(int containerID);
        void showTools(int containerID);
    }
}
