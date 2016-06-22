package com.restaurantapplication.common;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;



public class DialogUtility {

	public static final String TAG = "DialogUtility";
	private static AlertDialog dialog;
	private static ProgressDialog mProgressDialog;



	/**
	 * Static method to show the progress dialog.
	 * 
	 * @param context
	 *            : Context of the activity where to show the dialog
	 * @param isCancelable
	 *            : Sets whether this dialog is cancelable with the BACK key.
	 * @param message
	 *            : Message to be shwon on the progress dialog.
	 * @return Object of progress dialog.
	 */
	public static Dialog showProgressDialog(Context context,
			boolean isCancelable, String message) {
		mProgressDialog = new ProgressDialog(context);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setMessage(message);
		mProgressDialog.show();
		mProgressDialog.setCancelable(isCancelable);
		return mProgressDialog;
	}

	/**
	 * Static method to pause the progress dialog.
	 */
	public static void pauseProgressDialog() {
		try {
			if (mProgressDialog != null) {
				mProgressDialog.cancel();
				mProgressDialog.dismiss();
				mProgressDialog = null;
			}
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Static method to cancel the Dialog.
	 */
	public static void cancelDialog() {

		try {
			if (dialog != null) {
				dialog.cancel();
				dialog.dismiss();
				dialog = null;
			}
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Static method to hide the dialog if visible
	 */
	public static void hideDialog() {

		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
			dialog.cancel();
			dialog = null;
		}
	}

	/**
	 * This method will create alert dialog
	 * 
	 * @param context
	 *            Context of calling class
	 * @param title
	 *            Title of the dialog to be shown
	 * @param msg
	 *            Msg of the dialog to be shown
	 * @param btnText
	 *            array of button texts
	 * @param listener
	 */
	public static void showAlertDialog(Context context, String title,
			String msg, String btnText,
			DialogInterface.OnClickListener listener) {

		if (listener == null)
			listener = new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface paramDialogInterface,
						int paramInt) {
					paramDialogInterface.dismiss();
					paramDialogInterface.dismiss();
				}
			};

		Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton(btnText, listener);
		dialog = builder.create();
		dialog.setCancelable(false);
		dialog.show();
	}

	public static AlertDialog showCustomtDialog(Context context,
			String title, String msg, String[] btnText,int layout_id,
			DialogInterface.OnClickListener listener) {
		if (listener == null)
			listener = new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface paramDialogInterface,
						int paramInt) {
					paramDialogInterface.dismiss();
				}
			};
		LayoutInflater factory = LayoutInflater.from(context);
		final View textEntryView = factory.inflate(layout_id,
				null);
		Builder builder = new Builder(context);
		builder.setTitle(title);
		// builder.setMessage(msg);
		// builder.setView(mEmail_forgot);

		builder.setPositiveButton(btnText[0], listener);
		if (btnText.length != 1) {
			builder.setNegativeButton(btnText[1], listener);
		}
		dialog = builder.create();
		dialog.setCancelable(false);
		dialog.setView(textEntryView, 10, 10, 10, 10);
		dialog.show();
		return dialog;

	}
	public static void showFinishAlertDialog(final Activity mactivity,String title,
			String msg, String btnText,
			DialogInterface.OnClickListener listener) {

		if (listener == null)
			listener = new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface paramDialogInterface,
						int paramInt) {
					paramDialogInterface.dismiss();
					paramDialogInterface.dismiss();
					mactivity.finish();
				}
			};

		Builder builder = new Builder(mactivity);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton(btnText, listener);
		dialog = builder.create();
		dialog.setCancelable(false);
		dialog.show();
	}

}
