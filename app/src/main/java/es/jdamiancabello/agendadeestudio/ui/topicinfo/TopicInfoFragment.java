package es.jdamiancabello.agendadeestudio.ui.topicinfo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.Topic;


public class TopicInfoFragment extends Fragment implements TopicInfoContract.View{
    public static final String TAG = "TopicInfoFragment";
    private TopicInfoContract.Presenter presenter;
    private OnfragmentIntercationsListener mListener;
    private EditText topicName;
    private CheckBox cbxHigPrio, cbxMidPrio, cbxLowPrio, isTask;
    private EditText notes;
    private ImageView goBack;
    private Spinner topicState;
    private Button btnUpdateTopic;

    public static TopicInfoFragment newInstance(Bundle bundle) {
        TopicInfoFragment fragment = new TopicInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        topicName = view.findViewById(R.id.topicInfo_ed_topicName);
        cbxHigPrio = view.findViewById(R.id.topicInfo_checkBoxHig);
        cbxMidPrio = view.findViewById(R.id.topicInfo_checkBoxMed);
        cbxLowPrio = view.findViewById(R.id.topicInfo_checkBoxLow);
        isTask = view.findViewById(R.id.topicInfo_checkBoxIsTask);
        notes = view.findViewById(R.id.topicInfo_ed_notes);
        goBack = view.findViewById(R.id.topicInfo_iv_goBack);
        topicState = view.findViewById(R.id.topicInfo_spinner_state);
        btnUpdateTopic = view.findViewById(R.id.topicInfo_btn_update);
        topicState.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, loadDefauldData()));
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onBack();
            }
        });
        Topic topic = getArguments().getParcelable(Topic.TOPICTAG);
        topicName.setText(topic.getName());
        selectPrio(topic.getPriority());

        isTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<String> options = new ArrayList<>();
                if(!isChecked){
                    options.addAll(loadDefauldData());
                }else{
                    options.add(getResources().getString(R.string.SubjectAdapter_task_state_0));
                    options.add(getResources().getString(R.string.SubjectAdapter_task_state_1));
                }
                topicState.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, options));
            }
        });

        cbxHigPrio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbxMidPrio.setChecked(false);
                    cbxLowPrio.setChecked(false);
                }
            }
        });

        cbxMidPrio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbxHigPrio.setChecked(false);
                    cbxLowPrio.setChecked(false);
                }
            }
        });

        cbxLowPrio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbxMidPrio.setChecked(false);
                    cbxHigPrio.setChecked(false);
                }
            }
        });

        isTask.setChecked(topic.isTask());
        notes.setText(topic.getNotes());

        setSpinnerSelection(topic.getState());

        btnUpdateTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateTopicData(makeTopic());
            }
        });

    }

    private Topic makeTopic() {
        Topic newTopic = getArguments().getParcelable(Topic.TOPICTAG);
        newTopic.setName(topicName.getText().toString());
        newTopic.setTask(isTask.isChecked());
        newTopic.setState(getTopicState());
        newTopic.setPriority(getPriority());
        newTopic.setNotes(notes.getText().toString());

        return newTopic;
    }

    private int getPriority() {
        if(cbxHigPrio.isChecked())
            return 2;
        if(cbxMidPrio.isChecked())
            return 1;
        return 0;
    }

    private int getTopicState() {
        if(isTask.isChecked()){
            if(topicState.getSelectedItemPosition() == 1)
                return 3;
            else
                return 0;
        }
        else
            return topicState.getSelectedItemPosition();
    }

    private void showEmptyNameError() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSpinnerSelection(int state) {
        if(isTask.isChecked()){
            if(state == 3)
                topicState.setSelection(1);
            else
                topicState.setSelection(0);
        }
        else {
            topicState.setSelection(state);
        }
    }

    private List<String> loadDefauldData() {
        return new ArrayList<String>(){{
            add(getResources().getString(R.string.SubjectAdapter_state_0));
            add(getResources().getString(R.string.SubjectAdapter_state_1));
            add(getResources().getString(R.string.SubjectAdapter_state_2));
            add(getResources().getString(R.string.SubjectAdapter_state_3));
        }};
    }

    private void selectPrio(int priority) {
        switch (priority){
            case 2:
                cbxHigPrio.setChecked(true);
                return;
            case 1:
                cbxMidPrio.setChecked(true);
                return;
        }
        cbxLowPrio.setChecked(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topic_info, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mListener = (OnfragmentIntercationsListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setPresenter(TopicInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onUpdated() {
        mListener.onBack();
    }

    @Override
    public void onErrorInUpdated() {

    }

    public interface OnfragmentIntercationsListener{
        void onBack();
    }
}