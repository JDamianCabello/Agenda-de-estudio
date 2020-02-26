package es.jdamiancabello.agendadeestudio.ui.topic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.adapter.TopicAdapter;
import es.jdamiancabello.agendadeestudio.data.model.Topic;


public class TopicListFragment extends Fragment implements TopicListContract.View{

    public static final String TAG = "TopicListFragment";
    private RecyclerView contenido;
    private FloatingActionButton floatingActionButton;
    private TopicAdapter topicAdapter;
    private TopicListContract.Presenter presenter;
    private OnFragmentInteractionListener mListener;


    public static TopicListFragment newInstance() {
        return new TopicListFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topicAdapter = new TopicAdapter(new TopicAdapter.ItemActions() {
            @Override
            public void onClick(Topic topic) {
                mListener.onAddOrModify(topic);
            }

            @Override
            public void onLongClick(Topic topic) {
                new AlertDialog.Builder(getContext()).setTitle("ELIMINAR").setMessage("¿Seguro que desea elmininar " + topic.getName() + "?").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.delete(topic);
                    }
                }).setNegativeButton(android.R.string.no,null).show();
            }
        });


        contenido = view.findViewById(R.id.topicList_content);
        contenido.setAdapter(topicAdapter);
        contenido.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        floatingActionButton = view.findViewById(R.id.topicList_fabAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAddOrModify(null);
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topic_list, container, false);
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
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void noTopics() {

    }

    @Override
    public void refresh(List<Topic> topicList) {
        topicAdapter.addAll(topicList);
        topicAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessDeleted(Topic topic) {

    }

    @Override
    public void onSucessUndo(Topic topic) {

    }

    @Override
    public void onUndo(Topic topic) {

    }

    @Override
    public void onSucess() {

    }

    @Override
    public void setPresenter(TopicListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showGenericError(String s) {

    }

    public interface OnFragmentInteractionListener {
        void onAddOrModify(Topic topic);
    }
}
