package es.jdamiancabello.agendadeestudio.ui.utils.stopwatch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import es.jdamiancabello.agendadeestudio.R;


public class StopWatchFragment extends Fragment {

    public static final String TAG = "StopWatchFragment";
    private ImageView icAnchor, icAnchorReverse;
    private Button btnStart;
    private Animation roundAnchor;
    private Chronometer time;
    private CountDownTimer countDownTimer;
    private CheckBox isCountDown;
    private CountDownTimer countDown;

    public static StopWatchFragment newInstance() {
        StopWatchFragment fragment = new StopWatchFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        icAnchor = view.findViewById(R.id.stopWatchAnchor);
        btnStart = view.findViewById(R.id.stopWatch_btn_start);
        time = view.findViewById(R.id.stopWatch_time);
        icAnchorReverse = view.findViewById(R.id.stopWatchAnchorReverse);

        isCountDown = view.findViewById(R.id.stopWatch_cbx_reverse);

        roundAnchor = AnimationUtils.loadAnimation(getContext(),R.anim.roundanimation);

        isCountDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    icAnchor.setVisibility(View.GONE);
                    icAnchorReverse.setVisibility(View.VISIBLE);
                    roundAnchor = AnimationUtils.loadAnimation(getContext(),R.anim.roundanimation_reverse);
                }
                else{
                    icAnchor.setVisibility(View.VISIBLE);
                    icAnchorReverse.setVisibility(View.GONE);
                    roundAnchor = AnimationUtils.loadAnimation(getContext(),R.anim.roundanimation);
                }
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCountDown.isChecked()){
                    icAnchorReverse.startAnimation(roundAnchor);
                    countDown = new CountDownTimer(10000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            long min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                            long sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
                        }

                        @Override
                        public void onFinish() {

                        }
                    }.start();
                }
                else{
                    icAnchor.startAnimation(roundAnchor);
                    time.setBase(SystemClock.elapsedRealtime());
                    time.start();
                }

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null)
            countDownTimer.cancel();
        if(time != null)
            time.stop();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stop_watch, container, false);
    }
}