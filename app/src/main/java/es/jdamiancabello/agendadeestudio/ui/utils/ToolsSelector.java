package es.jdamiancabello.agendadeestudio.ui.utils;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.jdamiancabello.agendadeestudio.R;


public class ToolsSelector extends Fragment {


    public static final String TAG = "ToolsSelector";
    private OnFragmentInteractionListener mListener;
    private CardView timer, todolist, countdown, notes,flashcards,schedule;



    public static ToolsSelector newInstance() {
        return new ToolsSelector();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tools_selector, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        timer = view.findViewById(R.id.tools_timer);

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onGoTimerTool();
            }
        });

        todolist = view.findViewById(R.id.tools_todoList);

        todolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onGoToDoListTool();
            }
        });

        countdown = view.findViewById(R.id.tools_countdown);

        countdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onGoCountDownTool();
            }
        });

        notes = view.findViewById(R.id.tools_notes);

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onGoNotesTool();
            }
        });

        flashcards = view.findViewById(R.id.tools_flashCard);

        flashcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onGoFlashCardsTool();
            }
        });

        schedule = view.findViewById(R.id.tools_schedule);

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onGoScheduleTool();
            }
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
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

    public interface OnFragmentInteractionListener{
        void onGoTimerTool();
        void onGoToDoListTool();
        void onGoCountDownTool();
        void onGoNotesTool();
        void onGoFlashCardsTool();
        void onGoScheduleTool();
    }
}