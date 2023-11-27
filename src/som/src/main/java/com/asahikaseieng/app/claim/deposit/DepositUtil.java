/*
 * Created on 2008/08/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.deposit;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.master.accounts.AccountsListLogic;
import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCredit;
import com.asahikaseieng.dao.nonentity.master.accountsnamelist.AccountsNameList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 入金処理のユーティリティクラス.
 * @author tosco
 */
public final class DepositUtil {

	/** 分類マスタ.データ種別＝入金(2) */
	public static final int CATEGORY_DATA_TYPE_CREDIT = 2;

	/**
	 * コンストラクタ
	 */
	private DepositUtil() {
	}

	/**
	 * 勘定科目コンボボックス内容取得
	 * @param depositDetailLogic depositDetailLogic
	 * @return List<ComboBoxItems> 入金コンボボックス内容リスト
	 */
	public static List<ComboBoxItems> getClassComboBox(
			final DepositDetailLogic depositDetailLogic) {

		// 検索結果を格納用リスト
		List<ComboBoxItems> classList = new ArrayList<ComboBoxItems>();

		// コンボボックスの先頭行は空白にする
		ComboBoxItems comboBlank = new ComboBoxItems();
		comboBlank.setValues("");
		comboBlank.setLabales("         ");
		classList.add(comboBlank);

		try {
			// 分類マスタから検索結果取得
			List<DepositCredit> result = depositDetailLogic
					.getClassificationAccountsList();

			// 重複する勘定科目コード排除する為のテンポラリ用
			String code = "";
			// 勘定科目コード、勘定科目名称を設定
			for (DepositCredit bean : result) {
				String accoutCode = bean.getAccountsCd();
				if (!code.equals(accoutCode)) {
					// 異なる勘定科目コードのみをコンボボックスに設定
					ComboBoxItems combo = new ComboBoxItems();
					// 勘定科目コード設定(Value値)
					combo.setValues(accoutCode);
					// 科目名称をラベルとして設定
					combo.setLabales(bean.getAccountsName());
					classList.add(combo);
					code = accoutCode;
				}
			}
		} catch (NoDataException e) {
			// 検索結果が存在しない場合、空白行のみ表示->処理なし
			if (getLog().isDebugEnabled()) {
				getLog().debug("勘定科目マスタ検索結果無し.");
			}
		}
		return classList;
	}

	/**
	 * 手形種別コンボボックス内容取得
	 * @param depositDetailLogic depositDetailLogic
	 * @return List<ComboBoxItems> 手形種別コンボボックス内容リスト
	 */
	public static List<ComboBoxItems> getNoteComboBox(
			final DepositDetailLogic depositDetailLogic) {

		// 検索結果を格納用リスト
		List<ComboBoxItems> classList = new ArrayList<ComboBoxItems>();

		ComboBoxItems combo1 = new ComboBoxItems();
		combo1.setValues("1");
		combo1.setLabales("約束手形");
		classList.add(combo1);

		ComboBoxItems combo2 = new ComboBoxItems();
		combo2.setValues("2");
		combo2.setLabales("為替手形");
		classList.add(combo2);

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
		// 補助科目コード＋勘定科目名称設定(ラベル)
		comboEmp.setLabales("         ");
		accountsSubList.add(comboEmp);

		if (StringUtils.isNotEmpty(accountsCode)) {
			try {
				// 勘定科目一覧取得
				List<AccountsNameList> bean = (List<AccountsNameList>) accountsListLogic
						.getNameList(accountsCode);

				// 補助科目コード、補助科目名称を取得して配列に設定
				for (int i = 0; i < bean.size(); i++) {
					AccountsNameList list = (AccountsNameList) bean.get(i);
					ComboBoxItems combo = new ComboBoxItems();
					// 補助科目コード設定(Value値)
					combo.setValues(list.getAccountsSubCd());
					// 科目名称設定(ラベル)
					combo.setLabales(list.getAccountsSubName());
					accountsSubList.add(combo);
				}

			} catch (NoDataException e) {
				// 検索結果が存在しない場合、空白行のみ表示->処理なし
				if (getLog().isDebugEnabled()) {
					getLog().debug("勘定科目マスタ検索結果無し.");
				}
			}
		}
		return accountsSubList;
	}

	/**
	 * Logオブジェクトを返す.
	 * @return Logオブジェクト
	 */
	private static Log getLog() {
		return LogFactory.getLog(DepositUtil.class);
	}
}
