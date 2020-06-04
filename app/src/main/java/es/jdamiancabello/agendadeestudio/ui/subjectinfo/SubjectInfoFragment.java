package es.jdamiancabello.agendadeestudio.ui.subjectinfo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.adapter.TopicAdapter;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class SubjectInfoFragment extends Fragment implements SubjectInfoContract.View {


    public static final String TAG = "SubjectInfoFragment";
    private OnFragmentInteractionListener mListener;
    private ImageView iv_backArrow;
    private TextView tv_subjectName, tv_totalTask, tv_totalTaskDone, tv_totalPercentComplete;
    private ProgressBar progressBarPercent;
    private TopicAdapter topicAdapter;
    private RecyclerView recyclerView;
    private ImageButton ibt_contextMenu;
    private View addNewTask;
    private TextInputEditText tiedAddTask;
    private CheckBox checkBoxHigPrio, checkBoxMidPrio, checkBoxLowPrio, isTask;
    private Spinner state;

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
        ibt_contextMenu = view.findViewById(R.id.subjectInfo_ibt_contextMenu);
        tv_subjectName = view.findViewById(R.id.subjectInfo_tv_subjectName);
        tv_totalTask = view.findViewById(R.id.subjectInfo_tv_totalTasks);
        tv_totalTaskDone=view.findViewById(R.id.subjectInfo_tv_totalTaskDone);
        tv_totalPercentComplete = view.findViewById(R.id.subjectInfo_tv_totalPercentComplete);
        progressBarPercent = view.findViewById(R.id.subjectInfo_progressBar_Percent);
        recyclerView = view.findViewById(R.id.subjectInfo_recyclerView);
        registerForContextMenu(ibt_contextMenu);
        addNewTask = view.findViewById(R.id.include_addNewTask);
        tiedAddTask = view.findViewById(R.id.textInput_addTask);
        checkBoxHigPrio = view.findViewById(R.id.checkBoxHig);
        checkBoxMidPrio = view.findViewById(R.id.checkBoxMed);
        checkBoxLowPrio = view.findViewById(R.id.checkBoxLow);
        state = view.findViewById(R.id.spinnerAddTaskOrTopic);
        stateFirstLoad();
        isTask = view.findViewById(R.id.checkbox_ChangeStateContent);

        checkBoxHigPrio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    changeCheck(buttonView.getId());
            }
        });

        checkBoxMidPrio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    changeCheck(buttonView.getId());
            }
        });

        checkBoxLowPrio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    changeCheck(buttonView.getId());
            }
        });

        isTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<String> options = new ArrayList<>();
                if(!isChecked){
                    options.add("1");
                    options.add("2");
                    options.add("3");
                    options.add("4");
                }else{
                    options.add("1 task");
                    options.add("2 task");
                }
                state.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, options));
            }
        });

        tiedAddTask.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count != 0)
                    addNewTask.setVisibility(View.VISIBLE);
                else
                    addNewTask.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        iv_backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSubjectInfoBack();
            }
        });

        ibt_contextMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopMenu();
            }
        });

        //Get the current subject
        Subject subject = getArguments().getParcelable(Subject.SUBJECT_KEY);
        tv_subjectName.setText(subject.getSubject_name());
        tv_subjectName.setTextColor(subject.getColor());
        //TODO: Eliminar esta mierda es pa pruebas hermano
        subject.setPercent(subject.getPercent());


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

    private void stateFirstLoad() {
        List<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("4");

        state.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, options));
    }

    private void changeCheck(int id) {
        switch (id){
            case R.id.checkBoxHig:
                checkBoxLowPrio.setChecked(false);
                checkBoxMidPrio.setChecked(false);
                break;

            case R.id.checkBoxMed:
                checkBoxLowPrio.setChecked(false);
                checkBoxHigPrio.setChecked(false);
                break;

            case R.id.checkBoxLow:
                checkBoxHigPrio.setChecked(false);
                checkBoxMidPrio.setChecked(false);
                break;
        }
    }

    private void showPopMenu() {
        PopupMenu popupMenu = new PopupMenu(getContext(),ibt_contextMenu);
        Menu menu = popupMenu.getMenu();
        popupMenu.getMenuInflater().inflate(R.menu.subjectfinfo_listorder,menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.subjectinfo_menu_orderby_name:
                        Toast.makeText(getContext(),"name",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.subjectinfo_menu_orderby_priority:
                        Toast.makeText(getContext(),"priority",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.subjectinfo_menu_orderby_state:
                        Toast.makeText(getContext(),"state",Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

        popupMenu.show();
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