package com.example.logicalchecksystem;

// 論理チェックを行うクラスです。
// import static com.example.logicalchecksystem.LogicalCheck.*;

public final class LogicalCheck {

	// 「カタカナ」のみ
	private static final String MATCH_KATAKANA = "\\u30A0-\\u30FF";

	// 「英字」のみ
	public static final String MATCH_ALPHA = "a-zA-Z";

	// 「数字」のみ
	public static final String MATCH_NUMBER = "0-9";

	// メールアドレス（簡易）
	public static final String MATCH_MAIL =
			"([a-zA-Z0-9][a-zA-Z0-9_.+\\-]*)@(([a-zA-Z0-9][a-zA-Z0-9_\\-]+\\.)+[a-zA-Z]{2,6})";

	// コンストラクタです。
	public LogicalCheck() {

	}

	/**
	 * メールアドレスに入力された文字列が@マークを含めた文字列で構成されているかチェックします。
	 * @param mail チェック対象文字列
	 * @return はい：true いいえ：false
	 */
	public static boolean checkMail(String mail) {
		return mail.matches(MATCH_MAIL);
	}


	/**
	 * パスワードに入力された文字列が全て半角英数字かチェックします。
	 * @param password チェック対象文字列
	 * @return はい：true いいえ：false
	 */
	public static boolean checkPassword(String password) {
		return password.matches("^[" + MATCH_ALPHA + MATCH_NUMBER + "]+$");
	}

	/**
	 * ヨミガナに入力された文字列が全て全角カタカナかチェックします。
	 * @param reading チェック対象文字列
	 * @return はい：true いいえ：false
	 */
	public static boolean checkReading(String reading) {
		return reading.matches("^[" + MATCH_KATAKANA + "]+$");
	}

}
