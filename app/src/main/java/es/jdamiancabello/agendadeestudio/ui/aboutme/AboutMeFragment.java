package es.jdamiancabello.agendadeestudio.ui.aboutme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import es.jdamiancabello.agendadeestudio.R;

public class AboutMeFragment extends Fragment {

    public static final String TAG = "AboutMeFragment";

    public static Fragment newInstance() {
        return new AboutMeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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


}
