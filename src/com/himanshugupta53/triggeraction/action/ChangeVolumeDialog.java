package com.himanshugupta53.triggeraction.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.content.Context;
import android.media.AudioManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.trigger.TriggerActivity;
import com.himanshugupta53.triggeraction.trigger.TriggerDialog;
import com.himanshugupta53.triggeraction.utility.Config;


public class ChangeVolumeDialog extends TriggerDialog implements OnSeekBarChangeListener, OnClickListener {

	AudioManager audioManager;
	SeekBar notificationsVolumeBar, mediaVolumeBar, systemVolumeBar, ringtoneVolumeBar;
	int originalNotificationsVolume, originalMediaVolume, originalSystemVolume, originalRingtoneVolume;
	Button cancelButton, okButton;
	
	public ChangeVolumeDialog(Context context) {
		super(context);
		setLayout(R.layout.volume_change);
	}

	@Override
	public void startActivity() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		((ActionActivity)context).hideProgressDialog(null);
	}

	@Override
	public void setResult(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValuesToLayoutFields() {
		setTitleString("Volumes");
		
		audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		
		notificationsVolumeBar = (SeekBar) findViewById(R.id.notificationsVolumeBar);
		notificationsVolumeBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION));
		notificationsVolumeBar.setKeyProgressIncrement(1);
		originalNotificationsVolume = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
		notificationsVolumeBar.setProgress(originalNotificationsVolume);
		notificationsVolumeBar.setOnSeekBarChangeListener(this);
		
		mediaVolumeBar = (SeekBar) findViewById(R.id.mediaVolumeBar);
		mediaVolumeBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
		mediaVolumeBar.setKeyProgressIncrement(1);
		originalMediaVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		mediaVolumeBar.setProgress(originalMediaVolume);
		mediaVolumeBar.setOnSeekBarChangeListener(this);
		
		systemVolumeBar = (SeekBar) findViewById(R.id.systemVolumeBar);
		systemVolumeBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM));
		systemVolumeBar.setKeyProgressIncrement(1);
		originalSystemVolume = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
		systemVolumeBar.setProgress(originalSystemVolume);
		systemVolumeBar.setOnSeekBarChangeListener(this);
		
		ringtoneVolumeBar = (SeekBar) findViewById(R.id.ringtonVolumeBar);
		ringtoneVolumeBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_RING));
		ringtoneVolumeBar.setKeyProgressIncrement(1);
		originalRingtoneVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
		ringtoneVolumeBar.setProgress(originalRingtoneVolume);
		ringtoneVolumeBar.setOnSeekBarChangeListener(this);
		
		cancelButton = (Button) findViewById(R.id.cancelButton);
		cancelButton.setOnClickListener(this);
		okButton = (Button) findViewById(R.id.OKButton);
		okButton.setOnClickListener(this);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (seekBar == notificationsVolumeBar){
			audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, progress, AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);
		}
		else if (seekBar == mediaVolumeBar){
			audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);
		}
		else if (seekBar == systemVolumeBar){
			audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, progress, AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);
		}
		else if (seekBar == ringtoneVolumeBar){
			audioManager.setStreamVolume(AudioManager.STREAM_RING, progress, AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		int ringtoneVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
		int systemVolume = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
		int mediaVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		int notificationsVolume = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
		
		audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, originalNotificationsVolume, 0);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, originalMediaVolume, 0);
		audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, originalSystemVolume, 0);
		audioManager.setStreamVolume(AudioManager.STREAM_RING, originalRingtoneVolume, 0);
		
		if (v == okButton){
			Config.addActionInput(mediaVolume+"");
			Config.addActionInput(ringtoneVolume+"");
			Config.addActionInput(notificationsVolume+"");
			Config.addActionInput(systemVolume+"");
			Config.addActionInput(originalMediaVolume+"");
			Config.addActionInput(originalRingtoneVolume+"");
			Config.addActionInput(originalNotificationsVolume+"");
			Config.addActionInput(originalSystemVolume+"");
			((ActionActivity) context).setTriggerAction();
		}
		onBackPressed();
	}

}
