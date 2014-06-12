package com.example.logicalchecksystem;

// 顧客一人分の情報を扱うクラスです。
public class Customer {

	private String _id;// 顧客に割り振るユニークなID(連番)
	private String state;// valid(有効)、invalid(無効)、退会済みであるか否かを判断します(ユーザーからは見えない要素)
	private String mail;// メールアドレス(ログインに用いるため重複不可)
	private String password;// 8文字の半角英数字(ハイフンやアンダーバーなどの記号は不可)
	private String name;// 名前(日本人以外も登録する可能性があるため制限なし)
	private String reading;// 全角カナ、読み方
	private String birthday;// 誕生日(2000-01-01形式)、現在よりも後の日付や閏年以外の2月29日など存在しない日は入力できない
	private String gender;// 性別(男女)

	public Customer() {
	}

	public Customer(String _id, String mail, String password, String name, String reading, String birthday, String gender) {
		this._id = _id;
		this.mail = mail;
		this.password = password;
		this.name = name;
		this.reading = reading;
		this.birthday = birthday;
		this.gender = gender;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
