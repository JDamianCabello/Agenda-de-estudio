package es.jdamiancabello.agendadeestudio.ui.calendar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Random;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.adapter.SubjectAdapter;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.utils.CommonUtils;


public class CalendarFragment extends Fragment implements CalendarContract.View{
    public static final String TAG = "CalendarFragment";

    private CalendarView calendarView;
    private OnFragmentInteractionListener mListener;
    private CalendarPresenter presenter;


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

        presenter.load();
    }

    private void añadirEventosDummy() {
        List<EventDay> events = new ArrayList<>();



//        Random rnd = new Random();
//        for (int i = 1; i <= 20; i++) {
//            Calendar a = Calendar.getInstance();
//            a.add(Calendar.DAY_OF_YEAR,i);
//            events.add(new EventDay(a, randomIcon(),Color.rgb(rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255))));
//        }

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
