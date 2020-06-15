package es.jdamiancabello.agendadeestudio.ui.utils.countdown;

import android.app.TimePickerDialog;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import es.jdamiancabello.agendadeestudio.R;


public class CountDownFragment extends Fragment {

    public static final String TAG = "CountDownFragment";
    private ImageView selectTime;
    private ImageView icAnchor, stop, play, pause;
    private TextView tv_time;
    private long selectedTime;
    private long timeLeftMillis;
    private boolean started = false;
    private Animation roundAnchor;
    private CountDownTimer countDownTimer;


    public static CountDownFragment newInstance() {
        return new CountDownFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_time = view.findViewById(R.id.countDown_time);
        roundAnchor = AnimationUtils.loadAnimation(getContext(),R.anim.roundanimation_reverse);
        selectTime = view.findViewById(R.id.countDown_iv_SelectTime);
        icAnchor = view.findViewById(R.id.countDownAnchor);

        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tv_time.setText( String.format("%02d:%02d:00",selectedHour,selectedMinute));
                        selectedTime += selectedHour * 60 * 60 * 1000;
                        selectedTime += selectedMinute * 60 * 1000;
                    }
                }, 0, 10, true);//Yes 24 hour time
                timePickerDialog.setTitle("Select countdown time");
                timePickerDialog.show();
            }
        });

        play = view.findViewById(R.id.countDown_iv_play);
        stop = view.findViewById(R.id.countDown_iv_stop);
        pause = view.findViewById(R.id.countDown_iv_pause);

        stop.setEnabled(false);
        pause.setEnabled(false);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTime > 0) {
                    play.setEnabled(false);
                    stop.setEnabled(true);
                    pause.setEnabled(true);
                    pause.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_button_filled, null));
                    play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_button_outlined, null));
                    stop.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_button_filled, null));

                    if (!started) {
                        icAnchor.startAnimation(roundAnchor);
                        selectTime.setEnabled(false);
                        countDownTimer = new CountDownTimer(selectedTime, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                selectedTime = millisUntilFinished;
                                updateCountDownText();
                            }

                            @Override
                            public void onFinish() {
                                icAnchor.clearAnimation();
                                started = false;
                                countDownTimer.cancel();
                                tv_time.setText("00:00:00");
                                selectedTime = 0;
                                play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_button_filled, null));
                                stop.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_outline, null));

                                play.setEnabled(true);
                                stop.setEnabled(false);
                                pause.setEnabled(false);
                                selectTime.setEnabled(true);


                                MediaPlayer ring = MediaPlayer.create(getActivity(), R.raw.countdowntimer_end);
                                ring.start();

                            }
                        }.start();
                        started = true;
                    }
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTime.setEnabled(true);
                play.setEnabled(true);
                stop.setEnabled(false);
                pause.setEnabled(false);
                pause.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_button_filled,null));
                play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_button_filled,null));
                stop.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_outline,null));

                icAnchor.clearAnimation();
                started = false;
                countDownTimer.cancel();
                tv_time.setText("00:00:00");
                selectedTime = 0;
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


                icAnchor.clearAnimation();
                countDownTimer.cancel();
                started = false;
            }
        });
    }

    private void updateCountDownText() {
        long auxTime = selectedTime;
        long hours = TimeUnit.MILLISECONDS.toHours(auxTime);
        auxTime -= TimeUnit.HOURS.toMillis(hours);
        long mins = TimeUnit.MILLISECONDS.toMinutes(auxTime);
        auxTime -= TimeUnit.MINUTES.toMillis(mins);
        long secs = TimeUnit.MILLISECONDS.toSeconds(auxTime);
        tv_time.setText(String.format("%02d:%02d:%02d",hours,mins,secs));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_count_down, container, false);
    }
}