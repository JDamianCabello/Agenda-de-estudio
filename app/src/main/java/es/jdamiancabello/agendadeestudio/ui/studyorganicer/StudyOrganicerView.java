package es.jdamiancabello.agendadeestudio.ui.studyorganicer;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.adapter.StudyOrganicerAdapter;
import es.jdamiancabello.agendadeestudio.data.model.StudyOrganicer;


public class StudyOrganicerView extends Fragment implements StudyOrganicerListContract.View{
    public static final String TAG = "StudyOrganicerView";
    private StudyOrganicerListContract.Presenter presenter;
    private RecyclerView rvStudyOrganicer;
    private FloatingActionButton fabButton;
    private SectorListViewListener viewListener;
    private View loadData;

    private StudyOrganicerAdapter adapter;
    private StudyOrganicerAdapter.OnManageStudyOrganicerListener adapterOnManageStudyOrganicerListener;

    private StudyOrganicer deletedStudyOrganicer;



    public static Fragment newInstance(Bundle b) {
        StudyOrganicerView fragment = new StudyOrganicerView();
        if(b != null)
            fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.context_menu_listorder,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.contextmenu_orderName:
                adapter.sortByName();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvStudyOrganicer = view.findViewById(R.id.studyOrganicerList_rv);
        loadData = view.findViewById(R.id.studyorganicerlist_dataloading);

        fabButton = view.findViewById(R.id.studyOrganicerList_fab);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewListener.sectorAddEditFragmentShow(null);
            }
        });

        adapter = new StudyOrganicerAdapter();
        adapterOnManageStudyOrganicerListener = new StudyOrganicerAdapter.OnManageStudyOrganicerListener() {
            @Override
            public void onDeleteStudyOrganicerListener(StudyOrganicer studyOrganicer) {
                new AlertDialog.Builder(getContext()).setTitle("ELIMINAR").setMessage("¿Seguro que desea elmininar esto? ").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.delete(deletedStudyOrganicer);
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton(android.R.string.no,null).show();

                deletedStudyOrganicer = studyOrganicer;
            }

            @Override
            public void onAddorEditStudyOrganicerListener(StudyOrganicer studyOrganicer) {
                viewListener.sectorAddEditFragmentShow(studyOrganicer);
            }
        };
        adapter.setOnManageStudyOrganicerListener(adapterOnManageStudyOrganicerListener);
        rvStudyOrganicer.setAdapter(adapter);
        rvStudyOrganicer.setLayoutManager(new LinearLayoutManager(getContext()));


        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_study_organicerlist_view, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SectorListViewListener) {
            viewListener = (SectorListViewListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddSector");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewListener = null;
    }

    @Override
    public void showProgress() {
        loadData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loadData.setVisibility(View.GONE);
    }

    @Override
    public void noStudyOrganicers() {
        Toast.makeText(getContext(), getResources().getString(R.string.studyOrganicerList_NoEvents),Toast.LENGTH_LONG).show();
    }



    @Override
    public void refresh(ArrayList<StudyOrganicer> studyOrganicerArrayList) {
        adapter.clear();
        adapter.addAll(studyOrganicerArrayList);
        adapter.Sort(new StudyOrganicer.IdSort());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessDeleted() {
        adapter.delete(deletedStudyOrganicer);
        adapter.notifyDataSetChanged();
        showSnackBarDeleted();
    }



    private void showSnackBarDeleted() {
        Snackbar.make(getView(),R.string.undoDelete,Snackbar.LENGTH_LONG).setAction(R.string.undo, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                undoDelete(deletedStudyOrganicer);
            }
        }).show();
    }


    private void undoDelete(StudyOrganicer studyOrganicer) {
        presenter.undo(studyOrganicer);
    }



    @Override
    public void onSucessUndo(StudyOrganicer studyOrganicer) {
        adapter.add(studyOrganicer);
        adapter.Sort(new StudyOrganicer.IdSort());
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(),"Se ha restaurado el evento eliminado",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSucess() {

    }

    @Override
    public void setPresenter(StudyOrganicerListContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void showGenericError(String s) {

    }


    public interface SectorListViewListener {
        void sectorAddEditFragmentShow(StudyOrganicer studyOrganicer);
    }
}
