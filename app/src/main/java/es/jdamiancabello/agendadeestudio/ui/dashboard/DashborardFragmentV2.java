package es.jdamiancabello.agendadeestudio.ui.dashboard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.naz013.smoothbottombar.SmoothBottomBar;
import com.github.naz013.smoothbottombar.Tab;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.R;


public class DashborardFragmentV2 extends Fragment {
    public static final String TAG = "DashborardFragmentV2";
    private OnFragmentInteractionListener mListener;
    private SmoothBottomBar menu;
    private FrameLayout container;

    public static DashborardFragmentV2 newInstance() {
        DashborardFragmentV2 fragment = new DashborardFragmentV2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
        menu.setTabs(createBottonMenu());

        menu.setOnTabSelectedListener(new SmoothBottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int i) {
                changeFragment(i);
            }
        });

        container = view.findViewById(R.id.dashboard_container);


        setTodayFragment();


    }

    private void changeFragment(int i) {
        switch (i){
            case 0:
                mListener.showCallendar(R.id.dashboard_container);
                break;
            case 1:
                mListener.showSubjects(R.id.dashboard_container);
                break;
            case 2:
                mListener.showTools(R.id.dashboard_container);
                break;
            case 3:
                mListener.showHelp(R.id.dashboard_container);
                break;
        }
    }

    private void setTodayFragment() {
//        menu.setSelectedItemId(R.id.dashboard_today);
        mListener.dashboardv2FirstLoad(R.id.dashboard_container);
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
        void dashboardv2FirstLoad(int containerID);
        void showCallendar(int containerID);
        void showOrganicer(int containerID);
        void showToday(int containerID);
        void showTopics(int containerID);
        void showSubjects(int containerID);
        void showTools(int containerID);
        void showHelp(int dashboard_container);
    }

    private List<Tab> createBottonMenu(){
        return new ArrayList<Tab>(){{
            add(new Tab(R.drawable.ic_calendar,getString(R.string.dashboard_callendar)));
            add(new Tab(R.drawable.ic_book_white,getString(R.string.dashboard_subjects)));
            add(new Tab(R.drawable.ic_note,getString(R.string.dashboard_topics)));
            add(new Tab(R.drawable.ic_help,getString(R.string.help)));
        }};

    }
}
