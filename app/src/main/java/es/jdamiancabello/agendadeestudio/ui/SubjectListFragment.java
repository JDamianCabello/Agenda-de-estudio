package es.jdamiancabello.agendadeestudio.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.adapter.SubjectAdapter;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository;

public class SubjectListFragment extends Fragment {
    public final static String TAG = "SubjectListFragment";

    private onSubjectListListener listListener;
    private FloatingActionButton fabAdd;
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
//                listListener.addSubject(bundle);
                Toast.makeText(getContext(),"Has pulsado en: "+subject.getName(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDeleteSubjectListener(final Subject subject) {
                new AlertDialog.Builder(getContext()).setTitle("ELIMINAR").setMessage("¿Seguro que desea elmininar " + subject.getName() + "?").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SubjectRepository.getInstance().remove(subject);
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton(android.R.string.no,null).show();
            }
        };
        fabAdd= view.findViewById(R.id.fbAddSubject);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"No se permiten añadir más asignaturas",Toast.LENGTH_SHORT).show();
            }
        });


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
