package es.jdamiancabello.agendadeestudio.ui.subjets;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.naz013.colorslider.ColorSlider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import es.jdamiancabello.agendadeestudio.R;


public class SubjectManagerFragment extends Fragment implements SubjectManagerContract.View{
    public static final String TAG = "SubjectManagerFragment";
    private OnFragmentInteractionListener mListener;
    private SubjectManagerContract.Presenter presenter;

    private TextInputEditText edsubjectName;
    private TextInputEditText edSubjectDate;
    private TextView colorChange;
    private ColorSlider colorSlider;
    private FloatingActionButton floatingActionButton;

    public static SubjectManagerFragment newInstance(Bundle b) {
        SubjectManagerFragment fragment = new SubjectManagerFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        floatingActionButton = view.findViewById(R.id.subjectManager_fabSave);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addSubject(edsubjectName.getText().toString(), edSubjectDate.getText().toString(), colorSlider.getSelectedColor());
            }
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        colorChange = view.findViewById(R.id.subjectManager_tvSelectColor);
        colorSlider = view.findViewById(R.id.color_slider);

        colorSlider.setListener(new ColorSlider.OnColorSelectedListener() {
            @Override
            public void onColorChanged(int position, int color) {
                colorChange.setTextColor(color);
            }
        });
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
        mListener.onSavedSubject();
    }

    @Override
    public void setPresenter(SubjectManagerContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showGenericError(String s) {

    }


    public interface OnFragmentInteractionListener {
        void onSavedSubject();
    }
}
