package es.jdamiancabello.agendadeestudio.ui.calendar;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.github.naz013.colorslider.ColorSlider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.adapter.EventAdapter;
import es.jdamiancabello.agendadeestudio.data.model.Event;
import es.jdamiancabello.agendadeestudio.utils.CommonUtils;
import es.jdamiancabello.agendadeestudio.utils.ImageArrayAdapter;


public class CalendarFragment extends Fragment implements CalendarContract.View{
    public static final String TAG = "CalendarFragment";

    private CalendarView calendarView;
    private CalendarPresenter presenter;
    private ImageView noData;
    private RecyclerView todayRecycler;
    private EventAdapter todayAdapter;
    private FloatingActionButton calendarManage;
    private String calendarSelectDate;


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
        todayRecycler = view.findViewById(R.id.calendar_EventsRecyclerView);
        calendarManage = view.findViewById(R.id.calendar_fabAddEvent);
        noData = view.findViewById(R.id.calendar_noEvent);

        //calendarView.setMinimumDate(Calendar.getInstance());

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                calendarSelectDate = CommonUtils.dateLongToString(eventDay.getCalendar().getTimeInMillis());
                presenter.getTodayEvents(calendarSelectDate);
            }
        });

        todayAdapter = new EventAdapter(new EventAdapter.OnManageEventListener() {
            @Override
            public void onShowEventInfo(Event event) {
                makeAddEventAlertDialog(event);
            }

            @Override
            public void onDeleteEvent(Event event) {
                new AlertDialog.Builder(getContext()).setTitle(getString(R.string.calendar_deleteAlertDialogTitle)).setMessage(getString(R.string.calendar_deleteMessage)+" " + event.getEvent_name() + " ?" )
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteEvent(event);
                    }
                }).setNegativeButton(android.R.string.no,null).show();
            }
        });

        calendarManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAddEventAlertDialog(null);
            }
        });

        todayRecycler.setAdapter(todayAdapter);
        todayRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        calendarSelectDate = CommonUtils.dateLongToString(calendarView.getFirstSelectedDate().getTimeInMillis());
        presenter.getTodayEvents(calendarSelectDate);
        presenter.load();
    }

    private void makeAddEventAlertDialog(Event modifiedEvent) {
        View calendarManage = getLayoutInflater().inflate(R.layout.calendar_manage_view, null);

        EditText eventName = calendarManage.findViewById(R.id.calendar_ed_eventName);
        EditText eventResume = calendarManage.findViewById(R.id.calendar_ed_eventResume);
        ImageView selectDate = calendarManage.findViewById(R.id.calendar_iv_eventDate);
        TextView selectedDate = calendarManage.findViewById(R.id.calendar_tv_selectDate);

        if (CommonUtils.dateStringToLong(calendarSelectDate) >= CommonUtils.toFormatDate(Calendar.getInstance().getTime()))
            selectedDate.setText(calendarSelectDate);
        else
            selectedDate.setText(CommonUtils.dateLongToString(Calendar.getInstance().getTimeInMillis()));

        Spinner selectIcon = calendarManage.findViewById(R.id.scalendar_spinner_EventIcon);
        ImageArrayAdapter adapter = new ImageArrayAdapter(getContext(),iconsList() );
        selectIcon.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        ColorSlider eventColor = calendarManage.findViewById(R.id.calendar_color_slider);
        eventColor.setColors(sliderColors());
        eventColor.setListener(new ColorSlider.OnColorSelectedListener() {
            @Override
            public void onColorChanged(int position, int color) {
                eventName.setTextColor(color);
            }
        });
        EditText eventNotes = calendarManage.findViewById(R.id.calendar_ed_notes);
        CheckBox appnotify = calendarManage.findViewById(R.id.calendar_appnotify);


        if(modifiedEvent != null){
            eventName.setText(modifiedEvent.getEvent_name());
            eventName.setTextColor(modifiedEvent.getEvent_color());
            eventResume.setText(modifiedEvent.getEvent_resume());
            selectedDate.setText(modifiedEvent.getEvent_date());
            selectIcon.setSelection(getSelectedIcon(modifiedEvent.getEvent_iconId(),iconsList()));
            eventColor.setSelection(searchColor(modifiedEvent.getEvent_color()));
            eventNotes.setText(modifiedEvent.getEvent_notes());
            appnotify.setChecked(modifiedEvent.isAppnotification());
        }
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog picker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDate.setText(String.format("%02d",dayOfMonth) + "-" + String.format("%02d",month + 1) + "-" + year);
                    }
                }, LocalDate.now().getYear(), LocalDate.now().getMonthValue() -1, LocalDate.now().getDayOfMonth());
                picker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                picker.show();
            }
        });


        String positiveButtonText = "";
        TextView title = new TextView(getContext());
        if(modifiedEvent != null) {
            title.setText(getResources().getString(R.string.calendar_manage_update_title));
            positiveButtonText = getResources().getString(R.string.calendar_manage_update_button);
        }
        else {
            title.setText(getResources().getString(R.string.calendar_manage_title));
            positiveButtonText = getResources().getString(R.string.calendarManage_save);
        }

        title.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark,null));
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);


        AlertDialog alertDialog = new AlertDialog.Builder(getContext(), R.style.calendarAlertBuilder)
                .setView(calendarManage)
                .setCustomTitle(title)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(modifiedEvent != null)
                            presenter.updateEvent(makeEvent(modifiedEvent));
                        else
                            presenter.addEvent(makeEvent(null));
                    }

                    private Event makeEvent(Event event) {
                        Event evenAux;
                        if(event == null)
                            evenAux = new Event();
                        else
                            evenAux = event;
                        evenAux.setEvent_name(eventName.getText().toString());
                        evenAux.setEvent_resume(eventResume.getText().toString());
                        evenAux.setEvent_date(selectedDate.getText().toString());
                        evenAux.setEvent_color(eventColor.getSelectedColor());
                        evenAux.setEvent_iconId((int)selectIcon.getSelectedView().getTag());
                        evenAux.setAppnotification(appnotify.isChecked());
                        evenAux.setEvent_notes(eventNotes.getText().toString());
                        return evenAux;
                    }
                })
                .setNegativeButton(getResources().getString(R.string.calendarManage_dontSave), null)
                .show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark, null));
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
    }

    private int getSelectedIcon(int iconId, Integer[] iconsList) {
        for (int i = 0; i < iconsList.length; i++) {
            if (iconId == iconsList[i])
                return i;
        }
        return 0;
    }

    private Integer[] iconsList() {
        return new Integer[]{
                R.drawable.ic_subjecticons_01,
                R.drawable.ic_subjecticons_02,
                R.drawable.ic_subjecticons_03,
                R.drawable.ic_subjecticons_04,
                R.drawable.ic_subjecticons_05,
                R.drawable.ic_subjecticons_06,
                R.drawable.ic_subjecticons_07,
                R.drawable.ic_subjecticons_08,
                R.drawable.ic_subjecticons_09,
                R.drawable.ic_subjecticons_10
        };
    }

    private int searchColor(int color) {
        int[] aux = sliderColors();

        for (int i = 0; i < aux.length; i++) {
            if(aux[i] == color) {
                return i;
            }
        }
        return 0;
    }

    private int[] sliderColors() {
        return new int[]{
                Color.parseColor("#F44336"),
                Color.parseColor("#E91E63"),
                Color.parseColor("#9C27B0"),
                Color.parseColor("#673AB7"),
                Color.parseColor("#3F51B5"),
                Color.parseColor("#2196F3"),
                Color.parseColor("#03A9F4"),
                Color.parseColor("#00BCD4"),
                Color.parseColor("#009688"),
                Color.parseColor("#4CAF50"),
                Color.parseColor("#8BC34A"),
                Color.parseColor("#CDDC39"),
                Color.parseColor("#FFEB3B"),
                Color.parseColor("#FFC107"),
                Color.parseColor("#FF9800"),
                Color.parseColor("#FF5722"),
                Color.parseColor("#795548"),
                Color.parseColor("#9E9E9E"),
                Color.parseColor("#607D8B"),
                Color.parseColor("#000000")
        };
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void putEvents(List<Event> eventList) {
        List<EventDay> events = new ArrayList<>();
        if(eventList.isEmpty()) {
            calendarView.setEvents(events);
            return;
        }


        for (Event event: eventList) {
            Date dateAux = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                dateAux = simpleDateFormat.parse(event.getEvent_date());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateAux);
            events.add(new EventDay(calendar, event.getEvent_iconId(),event.getEvent_color()));
        }
        calendarView.setEvents(events);
    }

    @Override
    public void setPresenter(CalendarContract.Presenter presenter) {
        this.presenter = (CalendarPresenter) presenter;
    }

    @Override
    public void onSuccesAdded(Event event) {
        presenter.load();
        presenter.getTodayEvents(calendarSelectDate);
    }

    @Override
    public void loadTodayEvent(List<Event> eventList) {
        noData.setVisibility(View.GONE);
        todayAdapter.addAll(eventList);
    }

    @Override
    public void noTodayEvents() {
        noData.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateEvents(Event event) {
        todayAdapter.replace(event);
        presenter.load();
        presenter.getTodayEvents(calendarSelectDate);
    }

    @Override
    public void deleteEvent(Event event) {
        todayAdapter.remove(event);
        if(todayAdapter.isEmpty())
            noData.setVisibility(View.VISIBLE);
        else
            noData.setVisibility(View.GONE);
        presenter.load();
        presenter.StartUndo(event);
    }

    @Override
    public void showUndo(Event event) {
        Snackbar snackbar = Snackbar.make(getView(), getString(R.string.calendarRestoreText) + " " + event.getEvent_name() + "?", Snackbar.LENGTH_LONG);
        snackbar.setBackgroundTint(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        snackbar.setActionTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
        snackbar.setAnchorView(R.id.dashboard_menu);
        snackbar.setAction(getString(R.string.subjectlist_undobuttontext), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addEvent(event);
            }
        });

        snackbar.show();
    }
}
