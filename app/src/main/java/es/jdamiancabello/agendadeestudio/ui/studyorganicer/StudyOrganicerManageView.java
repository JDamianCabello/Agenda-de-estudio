package es.jdamiancabello.agendadeestudio.ui.studyorganicer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.adapter.StudyOrganicerAdapter;
import es.jdamiancabello.agendadeestudio.data.model.StudyOrganicer;
import es.jdamiancabello.agendadeestudio.data.model.Subject;


public class StudyOrganicerManageView extends Fragment implements StudyOrganicerManageContract.View {
    public static final String TAG = "StudyOrganicerManageView";
    private TextInputLayout tilStudyOrgnanicerManagerDateTime, tilStudyOrgnanicerManagerDuration;
    private TextInputEditText tiledStudyOrgnanicerManagerDateTime, tiledStudyOrgnanicerManagerDuration;
    private Spinner spinnerSubjets, spinnerQuantifier;
    private FloatingActionButton fbSave;
    private OnSaveStudyOrganicerManageView viewListener;
    private StudyOrganicerManageContract.Presenter presenter;
    private StudyOrganicer studyOrganicer;


    public static Fragment newInstance(Bundle b) {
        StudyOrganicerManageView fragment = new StudyOrganicerManageView();
        if (b != null){
            fragment.setArguments(b);
        }
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_study_organicer_manage_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
        tiledStudyOrgnanicerManagerDateTime = view.findViewById(R.id.tiledStudyOrgnanicerManagerDateTime);
        tiledStudyOrgnanicerManagerDuration = view.findViewById(R.id.tiledStudyOrgnanicerManagerDuration);
        tilStudyOrgnanicerManagerDateTime = view.findViewById(R.id.tilStudyOrgnanicerManagerDateTime);
        tilStudyOrgnanicerManagerDuration = view.findViewById(R.id.tilStudyOrgnanicerManagerDuration);

        spinnerQuantifier = view.findViewById(R.id.spinner_quantifier);

        spinnerSubjets = view.findViewById(R.id.spinnerSectorManageInventory);
        presenter.onViewCreated();

        fbSave = view.findViewById(R.id.fabSectorManageSave);

        

        if (getArguments() != null){
            studyOrganicer = getArguments().getParcelable("event");
            tiledStudyOrgnanicerManagerDateTime.setText(studyOrganicer.getDateTime());
            tiledStudyOrgnanicerManagerDuration.setText(Integer.toString(studyOrganicer.getDuration()));

            spinnerSubjets.setSelection(presenter.getPosition(studyOrganicer.getSubject()),false);
            spinnerQuantifier.setSelection(quantifierPosition(studyOrganicer.getDurationQuantifier()),false);
        }

        fbSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments()!= null){
                    presenter.onModifyStudyOrganicer(createSector(studyOrganicer.getIdStudyOrganicer()));
                }else {
                    presenter.onAddStudyOrganicer(createSector(-1));

                }
            }
        });
    }

    private int quantifierPosition(String s) {
        for (int i = 0; i < spinnerQuantifier.getAdapter().getCount(); i++) {
            if(spinnerQuantifier.getItemAtPosition(i).toString().equals(s))
                return i;
        }
        return -1;
    }



    private StudyOrganicer createSector(int oldId) {
        StudyOrganicer studyOrganicer = new StudyOrganicer(
                tiledStudyOrgnanicerManagerDateTime.getText().toString(),
                Integer.parseInt(tiledStudyOrgnanicerManagerDuration.getText().toString()),
                spinnerQuantifier.getSelectedItem().toString(),
                (Subject)spinnerSubjets.getSelectedItem()
        );
        if(oldId != -1)
            studyOrganicer.setIdStudyOrganicer(oldId);
        return studyOrganicer;
    }



    @Override
    public void onSucess() {
        viewListener.onSaveStudyOrganicerManageView();

    }

    public void setPresenter(StudyOrganicerManageContract.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void showGenericError(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSaveStudyOrganicerManageView) {
            viewListener = (OnSaveStudyOrganicerManageView) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSaveStudyOrganicerManageView");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewListener = null;
    }


    @Override
    public void setupContentList(ArrayList<Subject> subjects) {
        ArrayAdapter<Subject> spinnerAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,subjects);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spinnerSubjets.setAdapter(spinnerAdapter);
    }


    public interface OnSaveStudyOrganicerManageView {
        void onSaveStudyOrganicerManageView();
    }

    
    @Override
    public void onDurationEmpty(String error) {

    }

    @Override
    public void onDurationNumber(String error) {

    }

    @Override
    public void onTimeEmpty(String error) {

    }

    @Override
    public void onTimeQuantifier(String error) {

    }

    @Override
    public void onClearErrorDurationEmpty() {

    }

    @Override
    public void onClearErrorDurationNumber() {

    }

    @Override
    public void onClearErroronTimeEmpty() {

    }

    @Override
    public void onClearErrorTimeQuantifier() {

    }


}
