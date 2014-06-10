package com.example.logicalchecksystem;

// 顧客一人分の情報を扱うクラスです。
public class Customer {

	private String _id;// 顧客に割り振るユニークなID(連番)
	private String active;// 退会済みであるか否かを判断します(ユーザーからは見えない要素)
	private String mail;// メールアドレス(ログインに用いるため重複不可)
	private String password;// 8文字以上16文字以下の半角英数字(ハイフンやアンダーバーなどの記号は不可)
	private String name;// 名前(日本人以外も登録する可能性があるため制限なし)
	private String reading;// 全角カナ、読み方
	private String birthday;// 誕生日(2000-01-01形式)、現在よりも後の日付や閏年以外の2月29日など存在しない日は入力できない
	private String gender;// 性別(男女)
	private String telephone;// 電話番号(未入力可)
	private String address;// 住所(未入力可)




}
