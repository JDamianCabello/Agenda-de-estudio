package es.jdamiancabello.agendadeestudio.ui.subjets;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.naz013.colorslider.ColorSlider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;
import es.jdamiancabello.agendadeestudio.ui.dashboard.DashboardActivity;
import es.jdamiancabello.agendadeestudio.utils.ImageArrayAdapter;


public class SubjectManagerFragment extends Fragment implements SubjectManagerContract.View{
    public static final String TAG = "SubjectManagerFragment";
    private OnFragmentInteractionListener mListener;
    private SubjectManagerContract.Presenter presenter;

    private TextInputEditText edsubjectName;
    private TextInputEditText edSubjectDate;
    private TextView colorChange;
    private ColorSlider colorSlider;
    private FloatingActionButton floatingActionButton;
    private TextInputLayout inputLayoutSubjectDate;
    private Spinner selectIcon;

    public static SubjectManagerFragment newInstance(Bundle b) {
        SubjectManagerFragment fragment = new SubjectManagerFragment();
        fragment.setArguments(b);
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
        return inflater.inflate(R.layout.fragment_subject_manager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edsubjectName = view.findViewById(R.id.subjectManager_edSubjectName);
        edSubjectDate = view.findViewById(R.id.subjectManager_edSubjectDate);
        inputLayoutSubjectDate = view.findViewById(R.id.subjectManager_tilSubjectDate);
        selectIcon = view.findViewById(R.id.subjectManager_spinner_selectIcon);

        ImageArrayAdapter adapter = new ImageArrayAdapter(getContext(),
                new Integer[]{
                        0,
                        R.drawable.back_button_arrow,
                        R.drawable.checkbox_checked_higprio,
                        R.drawable.checkbox_unchecked,
                        R.drawable.ic_book
                }
                );

        selectIcon.setAdapter(adapter);

        inputLayoutSubjectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar actualDate = Calendar.getInstance();
                DatePickerDialog picker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edSubjectDate.setText(String.format("%02d",dayOfMonth) + "-" + String.format("%02d",month + 1) + "-" + year);
                    }
                }, LocalDate.now().getYear(), LocalDate.now().getMonthValue() -1, LocalDate.now().getDayOfMonth());
                picker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                picker.show();
            }
        });

        edSubjectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputLayoutSubjectDate.callOnClick();
            }
        });

        floatingActionButton = view.findViewById(R.id.subjectManager_fabSave);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getArguments() == null)
                    presenter.addSubject(edsubjectName.getText().toString(), edSubjectDate.getText().toString(), colorSlider.getSelectedColor());
                else
                    presenter.modifySubject(edsubjectName.getText().toString(), edSubjectDate.getText().toString(), colorSlider.getSelectedColor());
            }
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        colorChange = view.findViewById(R.id.subjectManager_tvSelectColor);
        colorSlider = view.findViewById(R.id.color_slider);
        colorSlider.setColors(sliderColors());

        colorSlider.setListener(new ColorSlider.OnColorSelectedListener() {
            @Override
            public void onColorChanged(int position, int color) {
                colorChange.setTextColor(color);
            }
        });

        if(getArguments() != null){
            Subject subject;
            subject = getArguments().getParcelable(Subject.SUBJECT_KEY);

            edsubjectName.setText(subject.getSubject_name());
            edsubjectName.setEnabled(false);
            edSubjectDate.setText(subject.getExam_date());
            colorSlider.setSelection(searchColor(subject.getColor()));
        }
        colorChange.setTextColor(colorSlider.getSelectedColor());
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
    public void onSucess() {

    }

    @Override
    public void setPresenter(SubjectManagerContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showGenericError(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSucess(Subject subject) {

        Intent intent = new Intent(getContext(), DashboardActivity.class);
        intent.putExtra("NOTIFICATION", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Subject.SUBJECT_KEY, subject);
        intent.putExtras(bundle);

        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), new Random().nextInt(100), intent, PendingIntent.FLAG_UPDATE_CURRENT);



        Notification.Builder builder = new Notification.Builder(getContext(), FocusApplication.CHANNEL_ID)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(subject.getSubject_name())
                .setContentTitle(subject.getExam_date())
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());

        notificationManagerCompat.notify(new Random().nextInt(100), builder.build());


        mListener.onSavedSubject();
    }


    public interface OnFragmentInteractionListener {
        void onSavedSubject();
    }
}
