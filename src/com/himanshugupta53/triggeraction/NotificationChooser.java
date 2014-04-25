package com.himanshugupta53.triggeraction;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.Toast;

import com.himanshugupta53.triggeraction.main.MainActivity;
import com.himanshugupta53.triggeraction.utility.Config;
import com.himanshugupta53.triggeraction.utility.MyUserPreferences;

public class NotificationChooser extends Activity implements OnCheckedChangeListener {

	RadioButton alertDialogRadioButton, toastMessageRadioButton, openAppRadioButton, notificationRadioButton, noneOfTheseRadioButton;
	boolean showNotificationTypeToUser = false;
	NotificationType notificationType = NotificationType.NONE_OF_THE_ABOVE;
	
	public static enum NotificationType{
		ALERT_DIALOG, TOAST_MESSAGE, OPEN_APP, NOTIFICATION, NONE_OF_THE_ABOVE;
		
		public static NotificationType getValueOf(String str){
			NotificationType nType;
			try{
				nType = NotificationType.valueOf(str);
			}
			catch(Exception e){
				nType = TOAST_MESSAGE;
			}
			return nType;
		}
		
		private static NotificationType getNotificationTypeFromRadioButton(NotificationChooser notificationChooserActivity, RadioButton radioButton){
			if (notificationChooserActivity.alertDialogRadioButton == radioButton)
				return ALERT_DIALOG;
			else if (notificationChooserActivity.toastMessageRadioButton == radioButton)
				return TOAST_MESSAGE;
			else if (notificationChooserActivity.openAppRadioButton == radioButton)
				return OPEN_APP;
			else if (notificationChooserActivity.notificationRadioButton == radioButton)
				return NOTIFICATION;
			else if (notificationChooserActivity.noneOfTheseRadioButton == radioButton)
				return NONE_OF_THE_ABOVE;
			return TOAST_MESSAGE;
		}
		
		private RadioButton getRadioButtonFromNotificationType(NotificationChooser notificationChooserActivity){
			switch(this){
			case ALERT_DIALOG:
				return notificationChooserActivity.alertDialogRadioButton;
			case TOAST_MESSAGE:
				return notificationChooserActivity.toastMessageRadioButton;
			case OPEN_APP:
				return notificationChooserActivity.openAppRadioButton;
			case NOTIFICATION:
				return notificationChooserActivity.notificationRadioButton;
			case NONE_OF_THE_ABOVE:
				return notificationChooserActivity.noneOfTheseRadioButton;
				default:
			}
			return null;
		}
		
		public void performActionRelatedToNotificationType(Context notificationChooser, String message){
			switch(this){
			case ALERT_DIALOG:
				Intent intent = new Intent(notificationChooser, AlertDialogActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("description", message);
				notificationChooser.startActivity(intent);
				break;
			case TOAST_MESSAGE:
				Toast.makeText(notificationChooser, message, Toast.LENGTH_LONG).show();
				break;
			case OPEN_APP:
				notificationChooser.startActivity(notificationChooser.getPackageManager().getLaunchIntentForPackage(notificationChooser.getPackageName()));
				break;
			case NOTIFICATION:
				intent = new Intent(notificationChooser, MainActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(notificationChooser, 0, intent, 0);
				
				Notification n  = new NotificationCompat.Builder(notificationChooser)
				.setContentTitle("TriggerAction")
				.setContentText(message)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentIntent(pendingIntent)
				.build();

				NotificationManager notificationManager = (NotificationManager) notificationChooser.getSystemService(Context.NOTIFICATION_SERVICE);

				notificationManager.notify(0, n);
				break;
			case NONE_OF_THE_ABOVE:
				default:
			}
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_chooser);
		
		alertDialogRadioButton = (RadioButton) findViewById(R.id.alertDialogRadioButton);
		toastMessageRadioButton = (RadioButton) findViewById(R.id.toastMessageRadioButton);
		openAppRadioButton = (RadioButton) findViewById(R.id.openAppRadioButton);
		notificationRadioButton = (RadioButton) findViewById(R.id.notificationRadioButton);
		noneOfTheseRadioButton = (RadioButton) findViewById(R.id.noneOfTheseRadioButton);
		
		alertDialogRadioButton.setOnCheckedChangeListener(this);
		toastMessageRadioButton.setOnCheckedChangeListener(this);
		openAppRadioButton.setOnCheckedChangeListener(this);
		notificationRadioButton.setOnCheckedChangeListener(this);
		noneOfTheseRadioButton.setOnCheckedChangeListener(this);
		
		showNotificationTypeToUser = false;
		MyUserPreferences.setContext(this);
		notificationType = NotificationType.getValueOf(MyUserPreferences.getString(Config.notificationTypeUserPreferenceKey));
		RadioButton radioButton = notificationType.getRadioButtonFromNotificationType(this);
		if (radioButton != null)
			radioButton.setChecked(true);
		showNotificationTypeToUser = true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification_chooser, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (!isChecked || !showNotificationTypeToUser)
			return;
		if (buttonView == alertDialogRadioButton){
			notificationType = NotificationType.ALERT_DIALOG;
		}
		else if (buttonView == toastMessageRadioButton){
			notificationType = NotificationType.TOAST_MESSAGE;
		}
		else if (buttonView == openAppRadioButton){
			notificationType = NotificationType.OPEN_APP;
			Toast.makeText(this, "When ever an action is completed, this app will open up", Toast.LENGTH_LONG).show();
//			Intent intent = new Intent(this, MainActivity.class, MainActivity.class);
//			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		}
		else if (buttonView == notificationRadioButton){
			notificationType = NotificationType.NOTIFICATION;
		}
		else{
			notificationType = NotificationType.NONE_OF_THE_ABOVE;
		}
		notificationType.performActionRelatedToNotificationType(this, "This is how "+notificationType.toString().toLowerCase().replace('_', ' ')+" looks like");
		MyUserPreferences.setString(Config.notificationTypeUserPreferenceKey, notificationType.toString());
	}

}
