package es.jdamiancabello.agendadeestudio.ui.topic;

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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;


public class TopicManagerFragment extends Fragment implements TopicManagerContract.View {
    public static final String TAG = "TopicManagerFragment";
    private Spinner subjects;
    private Spinner state;
    private TextInputEditText tiledTopicName;
    private FloatingActionButton fabSave;
    private TopicManagerContract.Presenter presenter;

    private OnFragmentInteractionListener mListener;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        state = view.findViewById(R.id.topicManager_subjectState);
        subjects = view.findViewById(R.id.topicManager_subjectSpinner);
        subjects.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, presenter.getSubjectList()));
        tiledTopicName = view.findViewById(R.id.topicManager_TILEDtopicName);

        if (getArguments() != null) {
            Topic t;
            t = getArguments().getParcelable(Topic.TOPICTAG);

            state.setSelection(t.getState());
            tiledTopicName.setText(t.getName());
            tiledTopicName.setEnabled(false);
            subjects.setEnabled(false);

            subjects.setSelection(selectSubject(t.getSubject_name()));

        }

        fabSave = view.findViewById(R.id.topicManager_saveTopicFAB);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getArguments() != null)
                    presenter.modifyTopic(((Subject)subjects.getSelectedItem()).getSubject_name(),tiledTopicName.getText().toString(),state.getSelectedItemPosition());
                else
                    presenter.addTopict(((Subject)subjects.getSelectedItem()).getSubject_name(),tiledTopicName.getText().toString(),state.getSelectedItemPosition());

            }
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        if(presenter.getSubjectList().size() == 0){
            fabSave.setVisibility(View.GONE);
            Snackbar.make(getView(),"Need almost one subject",Snackbar.LENGTH_INDEFINITE)
                    .setAction("Make one", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.onCreateNewSubject();
                        }
                    }).show();
        }

    }

    private int selectSubject(String subject_name) {
        List<Subject> aux = presenter.getSubjectList();

        for (int i = 0; i < aux.size(); i++) {
            if(subject_name == aux.get(i).getSubject_name())
                return i;
        }

        return 0;
    }


    public static TopicManagerFragment newInstance(Bundle bundle) {
        TopicManagerFragment fragment = new TopicManagerFragment();
        fragment.setArguments(bundle);
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
        return inflater.inflate(R.layout.fragment_topic_manager, container, false);
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
        mListener.onSaved();
    }

    @Override
    public void setPresenter(TopicManagerContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showGenericError(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }


    public interface OnFragmentInteractionListener {
        void onSaved();
        void onCreateNewSubject();
    }
}
