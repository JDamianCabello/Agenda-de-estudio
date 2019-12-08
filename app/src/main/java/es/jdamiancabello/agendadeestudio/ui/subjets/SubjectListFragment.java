package es.jdamiancabello.agendadeestudio.ui.subjets;

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
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.adapter.SubjectAdapter;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository;

public class SubjectListFragment extends Fragment implements SubjectListContract.View{
    public final static String TAG = "SubjectListFragment";

    private onSubjectListListener listListener;
    private FloatingActionButton fabAdd;
    private RecyclerView recyclerView;
    private SubjectAdapter adapter;
    private SubjectListContract.Presenter presenter;
    private View loadingView;

    public static SubjectListFragment newInstance() {
        return new SubjectListFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
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
        loadingView = view.findViewById(R.id.loading);
        adapter = new SubjectAdapter(new SubjectAdapter.onManegeSubjectListener() {

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
                            presenter.delete(subject);
                        }
                    }).setNegativeButton(android.R.string.no,null).show();
                }
            });
        fabAdd= view.findViewById(R.id.fbAddSubject);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"No se permiten añadir más asignaturas",Toast.LENGTH_SHORT).show();
            }
        });


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

    @Override
    public void showProgress() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void noSubjets() {
    }

    @Override
    public void refresh(ArrayList<Subject> subjectArrayList) {
        adapter.clear();
        adapter.addAll(subjectArrayList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessDeleted(Subject subject) {
        //Toast.makeText(getContext(),subject.getName() + " "+getString(R.string.subjectlist_deleteditem),Toast.LENGTH_SHORT).show();
        presenter.undo(subject);
        adapter.removeSubject(subject);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSucessUndo(Subject subject) {
        Toast.makeText(getContext(),subject.getName() + " "+getString(R.string.subjectlist_restoreditem),Toast.LENGTH_SHORT).show();
        adapter.addSubject(subject);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onUndo(final Subject subject) {
        Snackbar.make(getView(),getString(R.string.subjectlist_undotext)+ " " + subject.getName()+"?",Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.subjectlist_undobuttontext), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.onSucessUndo(subject);
                    }
                }).show();
    }

    @Override
    public void onSucess() {
    }

    @Override
    public void setPresenter(SubjectListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showGenericError(String s) {

    }

    public interface onSubjectListListener{
        void addSubject(Subject subject);
    }
}