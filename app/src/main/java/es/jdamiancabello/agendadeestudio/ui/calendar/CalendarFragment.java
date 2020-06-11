package es.jdamiancabello.agendadeestudio.ui.calendar;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.service.FocusBroadcastReceiver;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.utils.CommonUtils;


public class CalendarFragment extends Fragment implements CalendarContract.View{
    public static final String TAG = "CalendarFragment";

    private CalendarView calendarView;
    private OnFragmentInteractionListener mListener;
    private CalendarPresenter presenter;
    private Button service;


    public static CalendarFragment newInstance() {
        return new CalendarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendarView = view.findViewById(R.id.calendar_calendarView);

        //calendarView.setMinimumDate(Calendar.getInstance());

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Toast.makeText(getContext(), CommonUtils.dateLongToString(eventDay.getCalendar().getTimeInMillis()),Toast.LENGTH_SHORT).show();
            }
        });

        service = view.findViewById(R.id.pruevaDeService);
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        presenter.load();
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

    @Override
    public void putEvents(List<Subject> subjectList){
        List<EventDay> events = new ArrayList<>();
        for (Subject subject: subjectList) {
            Date dateAux = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                dateAux = simpleDateFormat.parse(subject.getExam_date());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateAux);
            events.add(new EventDay(calendar, subject.getIconId(),subject.getColor()));
        }
        calendarView.setEvents(events);
    }

    @Override
    public void setPresenter(CalendarContract.Presenter presenter) {
        this.presenter = (CalendarPresenter) presenter;
    }


    public interface OnFragmentInteractionListener {
        void calendar_selected_date();
    }
}
