package es.jdamiancabello.agendadeestudio.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.adapter.SubjectAdapter;
import es.jdamiancabello.agendadeestudio.data.model.Subject;

public class SubjectListFragment extends Fragment {
    public final static String TAG = "SubjectListFragment";

    private onSubjectListListener listListener;
    private RecyclerView recyclerView;
    private SubjectAdapter.onManegeSubjectListener adapterListener;
    private SubjectAdapter adapter;

    public static SubjectListFragment newInstance() {
        return new SubjectListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subjectlist,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= view.findViewById(R.id.rvsubjectlist);
        adapterListener = new SubjectAdapter.onManegeSubjectListener(){

            @Override
            public void onEditSubjectListener(Subject subject) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("subject",subject);
                listListener.addSubject(bundle);
            }

            @Override
            public void onDeleteSubjectListener(Subject subject) {
                new AlertDialog.Builder(getContext()).setTitle("").setMessage("").setPositiveButton(android.R.string.yes,null).setNegativeButton(android.R.string.no,null);
            }
        };
        adapter = new SubjectAdapter();
        adapter.setOnManageSubjectListener(adapterListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listListener = (onSubjectListListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listListener = null;
    }

    interface onSubjectListListener{
        void addSubject(Bundle subject);
    }
}
