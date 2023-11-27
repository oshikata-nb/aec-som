/*
 * Created on 2008/10/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.master.accounts.AccountsListLogic;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.accountsnamelist.AccountsNameList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 支払処理のユーティリティクラス.(カスタマイズ)
 * @author tosco
 */
public final class PaymentCsmUtil {

	private static ClassificationListForComboboxesDao classificationListForComboboxesDao;

	/** 分類マスタ.データ種別＝支払(4) */
	public static final int CATEGORY_DATA_TYPE_PAYMENT = 4;

	/**
	 * コンストラクタ
	 */
	private PaymentCsmUtil() {
	}

	/**
	 * 科目コンボボックス内容取得
	 * @param accountsListLogic 科目マスタ一覧取得 ロジッククラス
	 * @return List<ComboBoxItems> 支払コンボボックス内容リスト
	 */
	public static List<ComboBoxItems> getClassComboBox(
			final AccountsListLogic accountsListLogic) {

		// 検索結果を格納用リスト
		List<ComboBoxItems> classList = new ArrayList<ComboBoxItems>();

		// コンボボックスの先頭行は空白にする
		ComboBoxItems comboBlank = new ComboBoxItems();
		comboBlank.setValues("");
		comboBlank.setLabales("         ");
		classList.add(comboBlank);

		try {
			// 分類マスタから検索結果取得
			List<AccountsNameList> result = accountsListLogic.getNameList(null);

			// 重複する科目コード排除する為のテンポラリ用（科目：補助科目でキーなので科目は重複する)
			String code = "";
			// 科目コード、科目名称を設定
			for (AccountsNameList bean : result) {
				String accoutCode = bean.getAccountsCd();
				if (!code.equals(accoutCode)) {
					// 異なる科目コードのみをコンボボックスに設定
					ComboBoxItems combo = new ComboBoxItems();
					// 科目コード設定(Value値)
					combo.setValues(accoutCode);
					// 科目コード：科目名称をラベルとして設定
					combo.setLabales(accoutCode + ":" + bean.getAccountsName());
					classList.add(combo);
					code = accoutCode;
				}
			}
		} catch (NoDataException e) {
			// 検索結果が存在しない場合、空白行のみ表示->処理なし
			if (getLog().isDebugEnabled()) {
				getLog().debug("科目マスタ検索結果無し.");
			}
		}
		return classList;
	}

	/**
	 * 補助科目コード＋補助科目名称のコンボボックス取得
	 * @param accountsCode 科目コード
	 * @param accountsListLogic 科目マスタ検索ロジッククラス
	 * @return List<ComboBoxItems> 補助科目のコンボボックス
	 */
	public static List<ComboBoxItems> getAccountsSubComboBox(
			final String accountsCode, final AccountsListLogic accountsListLogic) {

		List<ComboBoxItems> accountsSubList = new ArrayList<ComboBoxItems>();
		// １行目は空白
		ComboBoxItems comboEmp = new ComboBoxItems();
		// 補助科目コード設定(Value値)
		comboEmp.setValues("");
		// 補助科目コード＋科目名称設定(ラベル)
		comboEmp.setLabales("         ");
		accountsSubList.add(comboEmp);

		if (StringUtils.isNotEmpty(accountsCode)) {
			try {
				// 科目一覧取得
				List<AccountsNameList> bean = (List<AccountsNameList>) accountsListLogic
						.getNameList(accountsCode);

				// 補助科目コード、補助科目名称を取得して配列に設定
				for (int i = 0; i < bean.size(); i++) {
					AccountsNameList list = (AccountsNameList) bean.get(i);
					ComboBoxItems combo = new ComboBoxItems();
					// 補助科目コード設定(Value値)
					combo.setValues(list.getAccountsSubCd());
					// 補助科目コード＋科目名称設定(ラベル)
					combo.setLabales(list.getAccountsSubCd() + ":"
							+ getNullValue(list.getAccountsSubName()));
					accountsSubList.add(combo);
				}

			} catch (NoDataException e) {
				// 検索結果が存在しない場合、空白行のみ表示->処理なし
				if (getLog().isDebugEnabled()) {
					getLog().debug("科目マスタ検索結果無し.");
				}
			}
		}
		return accountsSubList;
	}

	/**
	 * 対象文字列がnullの場合、置換文字列を返す。
	 * @param source 対象文字列
	 * @param replace 置換文字列
	 * @return 文字列
	 */
	public static String getNullValue(final String source, final String replace) {
		String res = replace;
		if (StringUtils.isNotEmpty(source)) {
			res = source;
		}
		return res;
	}

	/**
	 * 対象文字列がnullの場合、""を返す。
	 * @param source 対象文字列
	 * @return 文字列
	 */
	public static String getNullValue(final String source) {
		return getNullValue(source, "");
	}

	/**
	 * 分類リスト取得
	 * @param dataType サイトデータ種別
	 * @return List<ClassificationListForComboboxes>
	 */
	public static List<ClassificationListForComboboxes> getClassificationList(
			final BigDecimal dataType) {
		List<ClassificationListForComboboxes> list = classificationListForComboboxesDao
				.getListForComboboxes(dataType, null);
		return list;
	}

	/**
	 * Logオブジェクトを返す.
	 * @return Logオブジェクト
	 */
	private static Log getLog() {
		return LogFactory.getLog(PaymentCsmUtil.class);
	}

	/**
	 * classificationListForComboboxesDaoを設定します。
	 * @param classificationListForComboboxesDao
	 *            classificationListForComboboxesDao
	 */
	public static void setClassificationListForComboboxesDao(
			final ClassificationListForComboboxesDao classificationListForComboboxesDao) {
		PaymentCsmUtil.classificationListForComboboxesDao = classificationListForComboboxesDao;
	}
}
