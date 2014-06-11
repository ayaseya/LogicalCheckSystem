package com.example.logicalchecksystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	private PlaceholderFragment placeholderFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// actionbarのホームアイコンを非表示にします。
		getActionBar().setDisplayShowHomeEnabled(false);
		// actionbarのタイトルを非表示にします。
		getActionBar().setDisplayShowTitleEnabled(false);

		placeholderFragment = new PlaceholderFragment();

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, placeholderFragment)
					.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		menu.findItem(R.id.action_add).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		menu.findItem(R.id.action_table).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_add) {

			if (placeholderFragment == null) {
				getFragmentManager().beginTransaction()
						.replace(R.id.container, new PlaceholderFragment())
						.commit();
			} else {
				getFragmentManager().beginTransaction()
						.replace(R.id.container, placeholderFragment)
						.commit();
			}

			return true;
		} else if (id == R.id.action_table) {

			getFragmentManager().beginTransaction()
					.replace(R.id.container, new TableFragment())
					.commit();

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private TextView birthdayTV;
		private Dialog datePickerDialog;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);

			//日付情報を初期設定します。
			// Calendar.MONTHは0(JANUARY)から始まります。
			// http://www.javadrive.jp/start/calendar/index2.html

			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int monthOfYear = calendar.get(Calendar.MONTH);
			int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

			// SimpleDateFormatクラスによる書式設定
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			sdf.applyPattern("yyyy'年'MM'月'dd'日'HH':'mm':'ss");
			final String tmp = sdf.format(Calendar.getInstance().getTime());

			//日付設定時のリスナーを作成します。
			DatePickerDialog.OnDateSetListener DateSetListener = new DatePickerDialog.OnDateSetListener() {
				public void onDateSet(android.widget.DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

					birthdayTV.setText(year + "年" + monthOfYear + 1 + "月" + dayOfMonth + "日");

				}

			};

			// 日付設定ダイアログを作成します。
			datePickerDialog = new DatePickerDialog(getActivity(), DateSetListener, year, monthOfYear, dayOfMonth);

			birthdayTV = (TextView) rootView.findViewById(R.id.birthdaySettingTV);
			birthdayTV.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					// 日付設定ダイアログを表示します。
					datePickerDialog.show();

				}
			});

			return rootView;
		}
	}

	/**
	 * 一覧表をリスト表示するフラグメントです。
	 */
	public static class TableFragment extends Fragment {

		public TableFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_table, container, false);
			return rootView;
		}
	}

}
