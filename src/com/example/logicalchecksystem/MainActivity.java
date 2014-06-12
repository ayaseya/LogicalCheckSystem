package com.example.logicalchecksystem;

import static com.example.logicalchecksystem.LogicalCheck.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

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

		private TextView birthdaySettingTV;
		private Dialog datePickerDialog;
		private EditText readingET;
		private TextView readingTV;
		private StringBuilder lines;
		private EditText mailET;
		private TextView mailTV;
		private EditText passwordET;
		private TextView passwordTV;
		private RadioGroup radioGroup;
		private View rootView;
		private String genderRB;
		private TextView genderTV;
		private EditText nameET;
		private TextView nameTV;
		private TextView birthdayTV;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			rootView = inflater.inflate(R.layout.fragment_main, container, false);

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

					birthdaySettingTV.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");

				}

			};

			// 日付設定ダイアログを作成します。
			datePickerDialog = new DatePickerDialog(getActivity(), DateSetListener, year, monthOfYear, dayOfMonth);

			// メールアドレスを入力するEditTextのインスタンスを取得します。
			mailET = (EditText) rootView.findViewById(R.id.mailET);
			mailTV = (TextView) rootView.findViewById(R.id.mailTV);

			// メールアドレスを入力するEditTextのインスタンスを取得します。
			passwordET = (EditText) rootView.findViewById(R.id.passwordET);
			passwordTV = (TextView) rootView.findViewById(R.id.passwordTV);

			// 名前を入力するEditTextのインスタンスを取得します。
			nameET = (EditText) rootView.findViewById(R.id.nameET);
			nameTV = (TextView) rootView.findViewById(R.id.nameTV);

			// ヨミガナを入力するEditTextのインスタンスを取得します。
			readingET = (EditText) rootView.findViewById(R.id.readingET);
			readingTV = (TextView) rootView.findViewById(R.id.readingTV);

			// 誕生日を表示するTextViewのインスタンスを取得します。
			birthdayTV = (TextView) rootView.findViewById(R.id.birthdayTV);
			birthdaySettingTV = (TextView) rootView.findViewById(R.id.birthdaySettingTV);
			birthdaySettingTV.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					// 日付設定ダイアログを表示します。
					datePickerDialog.show();

				}
			});

			// 性別を選択するRadioGroupのインスタンスを取得します。
			radioGroup = (RadioGroup) rootView.findViewById(R.id.genderRG);
			genderTV = (TextView) rootView.findViewById(R.id.genderTV);

			radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					RadioButton radio = (RadioButton) rootView.findViewById(checkedId);

					if (radio.isChecked() == true) {
						// チェックされた状態の時の処理を記述します。
						if (checkedId == R.id.maleRB) {
							genderRB = "男";
						} else if (checkedId == R.id.femaleRB) {
							genderRB = "女";
						}
					}
					else {
						// チェックされていない状態の時の処理を記述します。
					}

				}
			});

			// 登録ボタン用のリスナーを設定します。
			rootView.findViewById(R.id.registrationBtn).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					lines = new StringBuilder();

					/**
					 * メールアドレスをチェックします。
					 */
					if (!"".equals(mailET.getText().toString())) {// 入力の有無をチェックします。
						if (checkMail(mailET.getText().toString())) {
							//							lines.append("メールアドレス > Check OK");
							//							lines.append(System.getProperty("line.separator"));
							mailTV.setTextColor(Color.BLACK);
						} else {
							lines.append("メールアドレスが不正です");
							lines.append(System.getProperty("line.separator"));
							mailTV.setTextColor(Color.RED);
						}
					} else {
						lines.append("メールアドレスが入力されていません");
						lines.append(System.getProperty("line.separator"));
						mailTV.setTextColor(Color.RED);
					}

					/**
					 * パスワードをチェックします。
					 */
					if (!"".equals(passwordET.getText().toString())) {// 入力の有無をチェックします。
						if (checkPassword(passwordET.getText().toString())) {
							//							lines.append("パスワード > Check OK");
							//							lines.append(System.getProperty("line.separator"));

							if (passwordET.getText().toString().length() > 7) {
								passwordTV.setTextColor(Color.BLACK);

							}else{
								lines.append("パスワードは8文字で構成してください");
								lines.append(System.getProperty("line.separator"));
								passwordTV.setTextColor(Color.RED);
							}

						} else {
							lines.append("パスワードは半角英数字で入力してください");
							lines.append(System.getProperty("line.separator"));
							passwordTV.setTextColor(Color.RED);
						}
					} else {
						lines.append("パスワードが入力されていません");
						lines.append(System.getProperty("line.separator"));
						passwordTV.setTextColor(Color.RED);
					}

					/**
					 * 名前が入力された状態かチェックします。
					 */
					if (!"".equals(nameET.getText().toString())) {// 入力の有無をチェックします。
						//						lines.append("名前 > Check OK");
						//						lines.append(System.getProperty("line.separator"));
						nameTV.setTextColor(Color.BLACK);

					} else {
						lines.append("名前が入力されていません");
						lines.append(System.getProperty("line.separator"));
						nameTV.setTextColor(Color.RED);
					}

					/**
					 * フリガナがカタカナかチェックします。
					 */
					if (!"".equals(readingET.getText().toString())) {// 入力の有無をチェックします。
						if (checkReading(readingET.getText().toString())) {
							//							lines.append("ヨミガナ > Check OK");
							//							lines.append(System.getProperty("line.separator"));
							readingTV.setTextColor(Color.BLACK);
						} else {
							lines.append("フリガナは全角カタカナで入力してください");
							lines.append(System.getProperty("line.separator"));
							readingTV.setTextColor(Color.RED);
						}
					} else {
						lines.append("フリガナが入力されていません");
						lines.append(System.getProperty("line.separator"));
						readingTV.setTextColor(Color.RED);
					}

					/**
					 * 誕生日が入力済みかチェックします。
					 */
					if (!"----年--月--日".equals(birthdaySettingTV.getText().toString())) {// 入力の有無をチェックします。
						//						lines.append("誕生日 > Check OK");
						//						lines.append(System.getProperty("line.separator"));
						birthdayTV.setTextColor(Color.BLACK);

					} else {
						lines.append("誕生日が入力されていません");
						lines.append(System.getProperty("line.separator"));
						birthdayTV.setTextColor(Color.RED);
					}

					/**
					 * 性別が選択されているかチェックします。
					 */
					if (genderRB != null) {// 入力の有無をチェックします。
						//						lines.append("性別 > Check OK");
						//						lines.append(System.getProperty("line.separator"));
						genderTV.setTextColor(Color.BLACK);

					} else {
						lines.append("性別が選択されていません");
						lines.append(System.getProperty("line.separator"));
						genderTV.setTextColor(Color.RED);
					}

					// 最後にマッチしたインデックスを取得します。
					int index = lines.lastIndexOf(System.getProperty("line.separator"));
					// インデックスを指定して削除します。
					if (index != -1) {
						lines.deleteCharAt(index);
					} else {
						lines.append("登録完了");
					}

					// メッセージを表示します。
					Toast.makeText(getActivity(), lines.toString(), Toast.LENGTH_LONG).show();

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
