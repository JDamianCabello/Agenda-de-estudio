package es.jdamiancabello.agendadeestudio.ui.aboutme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import es.jdamiancabello.agendadeestudio.R;

public class AboutMeFragment extends Fragment {

    public static final String TAG = "AboutMeFragment";
    private ImageButton logout;
    private OnFragmentInteractionListener mListener;
    public static Fragment newInstance() {
        return new AboutMeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        setHasOptionsMenu(true);
        AboutView aboutView = AboutBuilder.with(getContext())
                .setPhoto(android.R.mipmap.sym_def_app_icon)
                .setCover(R.mipmap.profile_cover)
                .setName("Damián Cabello")
                .setSubTitle("Mobile Developer")
                .setBrief("To esto es pa ná seguro suspendo")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGooglePlayStoreLink("")
                .addGitHubLink("jdamiancabello/agenda-de-estudio")
                .addFacebookLink("jdamiancabello")
                .addWebsiteLink("www.stackoverflow.com")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();
        return aboutView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logoutMenuButton:
                mListener.onLogout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    public interface OnFragmentInteractionListener{
        void onLogout();
    }
}
