package es.jdamiancabello.agendadeestudio.ui.subjets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.adapter.SubjectAdapter;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class SubjectListFragment extends Fragment implements SubjectListContract.View{
    public final static String TAG = "SubjectListFragment";

    private onSubjectListListener listListener;
    private FloatingActionButton fabAdd;
    private RecyclerView recyclerView;
    private SubjectAdapter adapter;
    private SubjectListContract.Presenter presenter;
    private View loadingView;
    private boolean stopDelete = false;
    private Subject subjectAux;
    private ImageView noDataImg;

    public static SubjectListFragment newInstance() {
        return new SubjectListFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.context_menu_listorder,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.contextmenu_orderName:
                adapter.sortByName();
                break;
            case R.id.contextmenu_orderPercent:
                adapter.sortByPercent();
                break;
            case R.id.contextmenu_orderExam:
                adapter.sortByExam();
                break;
        }
        return super.onOptionsItemSelected(item);
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
        noDataImg = view.findViewById(R.id.subjectList_noData);
        adapter = new SubjectAdapter(new SubjectAdapter.onManegeSubjectListener() {
            @Override
            public void onShowSubjectInfo(Subject subject) {
                listListener.showSubjectInfo(subject);
            }


            @Override
            public void onDeleteSubjectListener(final Subject subject) {
                new AlertDialog.Builder(getContext()).setTitle("ELIMINAR").setMessage("¿Seguro que desea elmininar la asignatura " + subject.getSubject_name() + " y TODOS SUS TEMAS de la misma?").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.startDelete(subject);
                    }
                }).setNegativeButton(android.R.string.no,null).show();
            }

            @Override
            public void onEdditSubject(Subject subject) {
                listListener.addOrEdditSubject(subject);
            }
        });


        fabAdd= view.findViewById(R.id.fbAddSubject);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listListener.addOrEdditSubject(null);
            }
        });


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        registerForContextMenu(recyclerView);

//        //TODO: swipe to delete
//        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//            }
//        });
//        helper.attachToRecyclerView(recyclerView);

        ((AppCompatActivity)getActivity()).getSupportActionBar().show();

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.context_menu_listorder,menu);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listListener = (onSubjectListListener)context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(!stopDelete && subjectAux != null)
            presenter.onDelete(subjectAux);
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
        noDataImg.setVisibility(View.VISIBLE);
    }

    @Override
    public void refresh(ArrayList<Subject> subjectArrayList) {
        adapter.addAll(subjectArrayList);
    }

    @Override
    public void startDeleteView(Subject subject) {
        if(!stopDelete && subjectAux != null)
            presenter.onDelete(subjectAux);

        adapter.removeSubject(subject);
        subjectAux = subject;
        Snackbar snackbar = Snackbar.make(getView(), getString(R.string.subjectlist_undotext) + " " + subject.getSubject_name() + "?", Snackbar.LENGTH_LONG);
        snackbar.setBackgroundTint(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        snackbar.setActionTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
        snackbar.setAction(getString(R.string.subjectlist_undobuttontext), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addSubject(subject);
                stopDelete = true;
                Toast.makeText(getContext(), subject.getSubject_name() + " " + getString(R.string.subjectlist_restoreditem), Toast.LENGTH_SHORT).show();
                snackbar.dismiss();
            }
        });
        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                switch (event) {
                    case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                        if (stopDelete) {
                            stopDelete = false;
                        } else
                            subjectAux = null;
                            presenter.onDelete(subject);
                        break;
                }
            }

            @Override
            public void onShown(Snackbar snackbar) {
            }
        });

        snackbar.show();
    }

    @Override
    public void checkEmptyAdapter() {
        if(adapter.isEmpty())
            noDataImg.setVisibility(View.VISIBLE);
        else
            noDataImg.setVisibility(View.GONE);
    }

    @Override
    public void onSucess() {
    }

    @Override
    public void setPresenter(SubjectListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showGenericError(String s) {

    }

    public interface onSubjectListListener{
        void addOrEdditSubject(Subject subject);
        void showSubjectInfo(Subject subject);
    }
}
