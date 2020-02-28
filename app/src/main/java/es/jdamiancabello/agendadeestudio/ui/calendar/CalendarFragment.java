package es.jdamiancabello.agendadeestudio.ui.calendar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import es.jdamiancabello.agendadeestudio.R;


public class CalendarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String TAG = "CalendarFragment";

    private CalendarView calendarView;
    private OnFragmentInteractionListener mListener;


    public static CalendarFragment newInstance() {
        return new CalendarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendarView = view.findViewById(R.id.calendar_calendarView);

        //user cannot assign past dates
        calendarView.setMinimumDate(Calendar.getInstance());

        añadirEventosDummy();

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    private void añadirEventosDummy() {
        List<EventDay> events = new ArrayList<>();

        Random rnd = new Random();
        for (int i = 1; i <= 20; i++) {
            Calendar a = Calendar.getInstance();
            a.add(Calendar.DAY_OF_YEAR,i);
            events.add(new EventDay(a, randomIcon(),Color.RED));
        }

        calendarView.setEvents(events);

    }

    private int randomIcon() {
        Random rnd = new Random();
        switch (rnd.nextInt(5)){
            case 0:
                return R.drawable.ic_book;
            case 1:
                return R.drawable.ic_exit_to_app;
            case 2:
                return R.drawable.ic_logout;
            case 3:
                return R.drawable.com_facebook_button_icon_blue;
            case 4:
                return R.drawable.ic_nota;
            case 5:
                return R.drawable.back_button_arrow;
        }
        return R.drawable.ic_action_add;
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
        void calendar_selected_date();
    }
}
