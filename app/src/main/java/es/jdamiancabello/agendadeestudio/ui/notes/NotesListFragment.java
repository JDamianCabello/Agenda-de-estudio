package es.jdamiancabello.agendadeestudio.ui.notes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.adapter.NoteAdapter;
import es.jdamiancabello.agendadeestudio.data.model.Note;


public class NotesListFragment extends Fragment implements NoteListContract.View{
    public static final String TAG = "NotesListFragment";
    private OnFragmentInteractionListener mListener;
    private NoteListContract.Presenter presenter;
    private NoteAdapter adapter;
    private RecyclerView recyclerView;



    public static NotesListFragment newInstance() {
        NotesListFragment fragment = new NotesListFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvNotes);
        adapter = new NoteAdapter(new NoteAdapter.OnNoteClickListener() {
            @Override
            public void onModify(Note note) {
                Toast.makeText(getContext(),note.getContent(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDelete(Note note) {

            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
    public void refresh(List<Note> noteList) {
        adapter.clear();
        adapter.addAll(noteList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSucess() {

    }

    @Override
    public void setPresenter(NoteListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showGenericError(String s) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAddorModifyNote(Note note);
    }
}
