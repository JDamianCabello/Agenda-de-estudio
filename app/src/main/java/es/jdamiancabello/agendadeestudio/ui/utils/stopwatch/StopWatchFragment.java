package es.jdamiancabello.agendadeestudio.ui.utils.stopwatch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.utils.CountDownTimer;
import es.jdamiancabello.agendadeestudio.utils.PausableChronometer;


public class StopWatchFragment extends Fragment {

    public static final String TAG = "StopWatchFragment";
    private ImageView icAnchor, stop, play, pause;
    private Animation roundAnchor;
    private Chronometer time;
    private long pauseTimer;
    private boolean started = false;


    public static StopWatchFragment newInstance() {
        StopWatchFragment fragment = new StopWatchFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        icAnchor = view.findViewById(R.id.stopWatchAnchor);
        roundAnchor = AnimationUtils.loadAnimation(getContext(),R.anim.roundanimation);
        time = view.findViewById(R.id.stopWatch_time);

        play = view.findViewById(R.id.stopWatch_iv_play);
        stop = view.findViewById(R.id.stopWatch_iv_stop);
        pause = view.findViewById(R.id.stopWatch_iv_pause);

        stop.setEnabled(false);
        pause.setEnabled(false);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.setEnabled(false);
                stop.setEnabled(true);
                pause.setEnabled(true);
                pause.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_button_filled,null));
                play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_button_outlined,null));
                stop.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_button_filled,null));

                if(!started) {
                    icAnchor.startAnimation(roundAnchor);
                    time.setBase(SystemClock.elapsedRealtime() - pauseTimer);
                    time.start();

                    started = true;
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.setEnabled(true);
                stop.setEnabled(false);
                pause.setEnabled(false);
                pause.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_button_filled,null));
                play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_button_filled,null));
                stop.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_outline,null));

                time.setBase(SystemClock.elapsedRealtime());
                icAnchor.clearAnimation();
                pauseTimer = 0;
                started = false;
                time.stop();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.setEnabled(true);
                stop.setEnabled(true);
                pause.setEnabled(false);
                pause.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_button_outline,null));
                play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_button_filled,null));
                stop.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_button_filled,null));

                if(started) {
                    icAnchor.clearAnimation();
                    time.stop();
                    started = false;
                    pauseTimer = SystemClock.elapsedRealtime() - time.getBase();
                }
            }
        });

//        isCountDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    icAnchor.setVisibility(View.GONE);
//                    icAnchorReverse.setVisibility(View.VISIBLE);
//                    roundAnchor = AnimationUtils.loadAnimation(getContext(),R.anim.roundanimation_reverse);
//                }
//                else{
//                    icAnchor.setVisibility(View.VISIBLE);
//                    icAnchorReverse.setVisibility(View.GONE);
//                    roundAnchor = AnimationUtils.loadAnimation(getContext(),R.anim.roundanimation);
//                }
//            }
//        });



        //                if(isCountDown.isChecked()){
//                    icAnchorReverse.startAnimation(roundAnchor);
//                    countDown = new CountDownTimer(10000, 1000) {
//                        @Override
//                        public void onTick(long millisUntilFinished) {
//                            long min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
//                            long sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
//                        }
//
//                        @Override
//                        public void onFinish() {
//
//                        }
//                    }.start();
//                }
    }

    private String getFormedTime(long base) {
        return (new SimpleDateFormat("hh:mm:ss")).format(new Date(base));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if(countDownTimer != null)
//            countDownTimer.cancel();
//        if(time != null)
//            time.stop();
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