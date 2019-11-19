package es.jdamiancabello.agendadeestudio.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

public class HelpFragment extends Fragment {
    public static final String TAG = "HelpFragment";

    public static Fragment newInstance() {
        return new HelpFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
