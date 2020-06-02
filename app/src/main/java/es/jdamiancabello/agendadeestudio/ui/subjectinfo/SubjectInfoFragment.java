package es.jdamiancabello.agendadeestudio.ui.subjectinfo;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.adapter.TopicAdapter;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.data.repository.TopicRepository_room;
import es.jdamiancabello.agendadeestudio.ui.dashboard.DashborardFragmentV2;

public class SubjectInfoFragment extends Fragment implements SubjectInfoContract.View {


    public static final String TAG = "SubjectInfoFragment";
    private OnFragmentInteractionListener mListener;
    private ImageView iv_backArrow;
    private TextView tv_subjectName, tv_totalTask, tv_totalTaskDone, tv_totalPercentComplete;
    private ProgressBar progressBarPercent;
    private TopicAdapter topicAdapter;
    private RecyclerView recyclerView;

    private SubjectInfoContract.Presenter presenter;

    public static SubjectInfoFragment newInstance(Bundle b) {
        SubjectInfoFragment fragment = new SubjectInfoFragment();
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
        return inflater.inflate(R.layout.fragment_subject_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iv_backArrow = view.findViewById(R.id.subjectInfo_iv_backArrow);
        tv_subjectName = view.findViewById(R.id.subjectInfo_tv_subjectName);
        tv_totalTask = view.findViewById(R.id.subjectInfo_tv_totalTasks);
        tv_totalTaskDone=view.findViewById(R.id.subjectInfo_tv_totalTaskDone);
        tv_totalPercentComplete = view.findViewById(R.id.subjectInfo_tv_totalPercentComplete);
        progressBarPercent = view.findViewById(R.id.subjectInfo_progressBar_Percent);
        recyclerView = view.findViewById(R.id.subjectInfo_recyclerView);

        iv_backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSubjectInfoBack();
            }
        });

        //Get the current subject
        Subject subject = getArguments().getParcelable(Subject.SUBJECT_KEY);
        tv_subjectName.setText(subject.getSubject_name());
        tv_subjectName.setTextColor(subject.getColor());
        //TODO: Eliminar esta mierda es pa pruebas hermano
        subject.setPercent(getSubjectPercent(subject.getSubject_name()));


        tv_totalPercentComplete.setText(subject.getPercent()+"%" + " / 100%");
        progressBarPercent.setProgress(subject.getPercent());


        //TODO: tareas completadas / a completar
        tv_totalTaskDone.setText("0");
        tv_totalTask.setText("0");

        topicAdapter = new TopicAdapter(new TopicAdapter.ItemActions() {
            @Override
            public void onClick(Topic topic) {
                Toast.makeText(getContext(),"ClickEvent",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(Topic topic) {
                Toast.makeText(getContext(),"LongClickEvent",Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(topicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        presenter.loadData();
    }

    private int getSubjectPercent(String subjectName) {
        List<Topic> topics = TopicRepository_room.getInstance().getListFromSubject(subjectName);
        if(topics.isEmpty() || topics == null)
            return 0;

        int aux = 0;
        for (Topic t:topics) {
            aux += t.getState();
        }
        return (aux * 100) / (topics.size()*3);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void refresh(List<Topic> topicList) {
        topicAdapter.addAll(topicList);
        topicAdapter.notifyDataSetChanged();

        tv_totalTask.setText(Integer.toString(topicList.size()));
        tv_totalTaskDone.setText(Integer.toString(getTaskDone(topicList)));
    }

    private int getTaskDone(List<Topic> topicList) {
        int aux = 0;

        for (Topic t: topicList) {
            if(t.getState() == 3)
                aux++;
        }
        return aux;
    }


    @Override
    public void noTopics() {
        Snackbar.make(getView(),"No hay temas",Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void onSucess() {

    }

    @Override
    public void setPresenter(SubjectInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showGenericError(String s) {

    }

    public interface OnFragmentInteractionListener{
        void onSubjectInfoBack();
    }
}