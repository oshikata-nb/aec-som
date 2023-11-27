/*
 * Created on 2007/08/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.classification;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.master.accounts.AccountsListLogic;
import com.asahikaseieng.dao.entity.classification.Classification;
import com.asahikaseieng.dao.nonentity.master.accountsnamelist.AccountsNameList;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 分類マスタ詳細 Actionクラス.
 * @author TanakaSatoru
 */
public final class ClassificationDetailAction extends AbstractAction {
	/** データ集計区分-売上 */
	public static final int DATA_TOTAL_DIVISION_SALES = 1;

	/** データ集計区分-入金 */
	public static final int DATA_TOTAL_DIVISION_CREDIT = 1;

	/** データ集計区分-仕入 */
	public static final int DATA_TOTAL_DIVISION_STOCKING = 1;

	/** データ集計区分-支払 */
	public static final int DATA_TOTAL_DIVISION_PAYMENT = 1;

	/** データ集計区分-返品 */
	public static final int DATA_TOTAL_DIVISION_RETURNED = 2;

	/** データ集計区分-相殺 */
	public static final int DATA_TOTAL_DIVISION_OFFSET = 2;

	/** データ集計区分-値引き */
	public static final int DATA_TOTAL_DIVISION_DISCOUNT = 3;

	/** データ集計区分-その他 */
	public static final int DATA_TOTAL_DIVISION_OTHER = 9;

	private ClassificationDetailLogic classificationDetailLogic;

	private AccountsListLogic accountsListLogic;

	/** Stringの'1' */
	private static final BigDecimal STR_ONE = new BigDecimal("1");

	/** Stringの'2' */
	private static final BigDecimal STR_TWO = new BigDecimal("2");

	/** Stringの'3' */
	private static final BigDecimal STR_THREE = new BigDecimal("3");

	/** Stringの'4' */
	private static final BigDecimal STR_FOUR = new BigDecimal("4");

	/** Stringの'5' */
	private static final BigDecimal STR_FIVE = new BigDecimal("5");

	/** Stringの'9' */
	private static final BigDecimal STR_NINE = new BigDecimal("9");

	/** 文字列「売上」 */
	private static final String STR_COLON = "：";

	/** 文字列「売上」 */
	private static final String STR_SALES = "売上";

	/** 文字列入金「入金」 */
	private static final String STR_CREDIT = "入金";

	/** 文字列仕入「仕入」 */
	private static final String STR_STOCKING = "仕入";

	/** 文字列支払「支払」 */
	private static final String STR_PAYMENT = "支払";

	/** 文字列「グループ間相殺」 */
	private static final String STR_GROUP_OFFSET = "グループ間相殺";

	/** 文字列 */
	private static final String STR_RETURNED = "返品";

	/** 文字列 */
	private static final String STR_DISCOUNT = "値引";

	/** 文字列 */
	private static final String STR_OFFSET = "相殺";

	/** 文字列 */
	private static final String STR_OTHER = "その他";

	/** データ種別配列 */
	private static final String[] DATA_TYPE_ARRAY = {"1", "2", "3", "4", "5"};

	/** データ集計区分コンボボックス内容 */
	private static final String[][][] TOTAL_DIVISION_ARRAY = {
			{ {"1", "1：売上"}, {"2", "2：返品"}, {"3", "3：値引"}, {"9", "9：その他"}},
			{ {"1", "1：入金"}, {"2", "2：相殺"}, {"9", "9：その他"}},
			{ {"1", "1：仕入"}, {"2", "2：返品"}, {"3", "3：値引"}, {"9", "9：その他"}},
			{ {"1", "1：支払"}, {"2", "2：相殺"}, {"9", "9：その他"}},
			{ {"1", "1：相殺"}, {"9", "9：その他"}}};

	/** データ集計区分コンボボックス内容格納Map */
	private static Map<String, List<ComboBoxItems>> totalDivisionMap;
	static {
		totalDivisionMap = new HashMap<String, List<ComboBoxItems>>();
		int len = DATA_TYPE_ARRAY.length;
		for (int i = 0; i < len; i++) {
			String[][] array = TOTAL_DIVISION_ARRAY[i];
			List<ComboBoxItems> comboItems = new ArrayList<ComboBoxItems>();
			totalDivisionMap.put(DATA_TYPE_ARRAY[i], comboItems);
			for (String[] s : array) {
				ComboBoxItems item = new ComboBoxItems();
				item.setValues(s[0]);
				item.setLabales(s[1]);
				comboItems.add(item);
			}
		}
	}

	/**
	 * コンストラクタ.分類マスタ詳細
	 */
	public ClassificationDetailAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		ClassificationDetailForm frm = (ClassificationDetailForm) form;

		try {
			/* 初期検索 */
			ClassificationDetail bean = classificationDetailLogic
					.getDetailEntity(frm.getDataType(), frm
							.getCategoryDivision());

			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, bean);

		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}
		String[] data = setText(frm.getDataType(), frm.getDataTotalDivision());

		frm.setTextDataType(data[0]);
		frm.setTextDataTotalDivision(data[1]);
		frm.setInsertFlg(0);
		// 科目のコンボボックス設定
		frm.setCreditAccountsCdList(getAccountsComboBox());
		// 科目のコンボボックス設定
		frm.setDebitAccountsCdList(getAccountsComboBox());
		// コンボボックス設定
		frm.setCreditSubAccountsCdList(getAccountsSubComboBox(frm
				.getCreditAccountsCd()));
		// コンボボックス設定
		frm.setDebitSubAccountsCdList(getAccountsSubComboBox(frm
				.getDebitAccountsCd()));
		// コンボボックス設定
		frm.setTotalDivisionList(new ArrayList<ComboBoxItems>());

		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc} 新規ボタン処理:
	 */
	public ActionForward initNew(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initNew.");
		}

		ClassificationDetailForm frm = (ClassificationDetailForm) form;

		frm.setInsertFlg(1);

		// 科目のコンボボックス設定
		frm.setCreditAccountsCdList(getAccountsComboBox());
		// 科目のコンボボックス設定
		frm.setDebitAccountsCdList(getAccountsComboBox());
		// コンボボックス設定
		frm.setCreditSubAccountsCdList(getAccountsSubComboBox(""));
		// コンボボックス設定
		frm.setDebitSubAccountsCdList(getAccountsSubComboBox(""));
		// コンボボックス設定
		frm.setTotalDivisionList(new ArrayList<ComboBoxItems>());

		return mapping.findForward("success");
	}

	/**
	 * 登録処理.update.ロケーションマスタ
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("update.");
		}
		ClassificationDetailForm frm = (ClassificationDetailForm) form;

		/* 更新対象データを作成する */
		Classification newBean = new Classification();
		setNewBean(frm, newBean);

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* 基本情報入力 */
		String tantoCd = getLoginInfo(request).getTantoCd();
		newBean.setUpdatorCd(tantoCd);

		try {
			/* 更新処理を実行 */
			classificationDetailLogic.update(newBean);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("back");
	}

	/**
	 * 追加処理.insert.ロケーションマスタ
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward insert(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("insert.");
		}
		ClassificationDetailForm frm = (ClassificationDetailForm) form;

		/* 更新対象データを作成する */
		Classification newBean = new Classification();
		setNewBean(frm, newBean);

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* 基本情報入力 */
		String tantoCd = getLoginInfo(request).getTantoCd();
		newBean.setUpdatorCd(tantoCd);
		newBean.setInputorCd(tantoCd);
		Date date = new Date();
		Timestamp nowDate = new Timestamp(date.getTime());
		newBean.setInputDate(nowDate);
		newBean.setUpdateDate(nowDate);

		/* 更新処理を実行 */
		classificationDetailLogic.insert(newBean);

		/* メッセージ */
		saveMessage(request, "message.complete.insert");
		frm.setInsertFlg(0);

		return mapping.findForward("back"); // 一覧へ戻る
	}

	/**
	 * ロケーションマスタ：delete
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("delete.");
		}

		ClassificationDetailForm frm = (ClassificationDetailForm) form;

		/* 更新対象データを作成する */
		Classification newBean = new Classification();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* 更新対象データを作成する:プライマリキー */
		Classification bean = classificationDetailLogic.getEntity(frm
				.getDataType(), frm.getDataTotalDivision(), frm
				.getCategoryDivision());

		try {
			/* 更新処理を実行 */
			classificationDetailLogic.delete(bean);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 戻る処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");
	}

	/**
	 * 借方勘定科目コンボ選択時
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward subdebit(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("subdebit.");
		}
		ClassificationDetailForm frm = (ClassificationDetailForm) form;
		// コンボボックス設定
		frm.setDebitSubAccountsCdList(getAccountsSubComboBox(frm
				.getDebitAccountsCd()));

		return mapping.findForward("success");
	}

	/**
	 * 貸方勘定科目コンボ選択時の設定
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward subcredit(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("subcredit.");
		}

		ClassificationDetailForm frm = (ClassificationDetailForm) form;
		// コンボボックス設定
		frm.setCreditSubAccountsCdList(getAccountsSubComboBox(frm
				.getCreditAccountsCd()));

		return mapping.findForward("success");
	}

	/**
	 * データ種別変更イベント.changeDataType
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward changeDataType(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("changeDataType.");
		}
		ClassificationDetailForm classForm = (ClassificationDetailForm) form;
		BigDecimal dataType = classForm.getDataType();
		List<ComboBoxItems> items = null;
		if (dataType != null) {
			items = totalDivisionMap.get(dataType);
		} else {
			items = new ArrayList<ComboBoxItems>();
		}
		classForm.setTotalDivisionList(items);

		return mapping.findForward("success");
	}

	/**
	 * 型の変換
	 * @param frm Form
	 * @param newBean Dao
	 */
	private void setNewBean(final ClassificationDetailForm frm,
			final Classification newBean) {

		BigDecimal numData = frm.getDataType();
		BigDecimal numDataTotalDivision = frm.getDataTotalDivision();
		newBean.setDataType(numData);
		newBean.setDataTotalDivision(numDataTotalDivision);
	}

	/**
	 * 科目コード＋科目名称のコンボボックス取得
	 * 
	 * @return List<ComboBoxItems> 科目のコンボボックス
	 * @throws NoDataException データ無しの例外
	 * 
	 */
	private List<ComboBoxItems> getAccountsComboBox() throws NoDataException {

		List<ComboBoxItems> accountsList = new ArrayList<ComboBoxItems>();

		try {
			// 科目一覧取得
			List<AccountsNameList> bean = accountsListLogic.getNameList("");

			// １行目は空白
			ComboBoxItems comboEmp = new ComboBoxItems();
			comboEmp.setValues("");
			comboEmp.setLabales("         ");
			accountsList.add(comboEmp);
			// 同じコードは１度だけ
			String tmpAccountsCd = "";
			// 科目コード、科目名称を取得して配列に設定
			for (AccountsNameList list : bean) {
				String accountCd = list.getAccountsCd().trim();
				if ((accountCd != null) && (!accountCd.equals(""))) {
					if (tmpAccountsCd.equals(list.getAccountsCd())) {
						continue;
					} else {
						ComboBoxItems combo = new ComboBoxItems();
						// 科目コード設定(Value値)
						combo.setValues(list.getAccountsCd());
						// 科目コード＋科目名称設定(ラベル)
						String accountName = list.getAccountsName().trim();
						if (accountName == null) {
							combo.setLabales(list.getAccountsCd() + ":");
						} else {
							combo.setLabales(list.getAccountsCd() + ":"
									+ accountName);
						}
						accountsList.add(combo);
						tmpAccountsCd = list.getAccountsCd();
					}
				}
			}

		} catch (NoDataException e) {
			ComboBoxItems combo = new ComboBoxItems();
			// 補助科目コード設定(Value値)
			combo.setValues("");
			// 補助科目コード＋科目名称設定(ラベル)
			combo.setLabales("　　　　　　　　");
			accountsList.add(combo);
		}
		return accountsList;
	}

	/**
	 * 補助科目コード＋補助科目名称のコンボボックス取得
	 * 
	 * @return List<ComboBoxItems> 補助科目のコンボボックス
	 * @throws NoDataException データ無しの例外
	 * 
	 */
	private List<ComboBoxItems> getAccountsSubComboBox(final String accountsCd)
			throws NoDataException {

		List<ComboBoxItems> accountsSubList = new ArrayList<ComboBoxItems>();

		if (!StringUtils.isEmpty(accountsCd)) {
			try {
				// 科目一覧取得
				List<AccountsNameList> bean = (List<AccountsNameList>) accountsListLogic
						.getNameList(accountsCd);

				// １行目は空白
				ComboBoxItems comboEmp = new ComboBoxItems();
				comboEmp.setValues("");
				comboEmp.setLabales("         ");
				accountsSubList.add(comboEmp);
				// 補助科目コード、補助科目名称を取得して配列に設定
				for (int i = 0; i < bean.size(); i++) {
					ComboBoxItems combo = new ComboBoxItems();
					AccountsNameList list = (AccountsNameList) bean.get(i);
					String accountSubCd = list.getAccountsSubCd().trim();
					if ((accountSubCd != null) && (!accountSubCd.equals(""))) {
						// 補助科目コード設定(Value値)
						combo.setValues(accountSubCd);
						// 補助科目コード＋科目名称設定(ラベル)
						String accountSubName = list.getAccountsSubName();
						if (accountSubName == null) {
							combo.setLabales(accountSubCd + ":");
						} else {
							combo.setLabales(accountSubCd + ":"
									+ accountSubName);
						}
						accountsSubList.add(combo);
					}
				}

			} catch (NoDataException e) {
				ComboBoxItems combo = new ComboBoxItems();
				// 補助科目コード設定(Value値)
				combo.setValues("");
				// 補助科目コード＋科目名称設定(ラベル)
				combo.setLabales("　　　　　　　　");
				accountsSubList.add(combo);
			}
		} else {

			ComboBoxItems combo = new ComboBoxItems();
			// 補助科目コード設定(Value値)
			combo.setValues("");
			// 補助科目コード＋科目名称設定(ラベル)
			combo.setLabales("　　　　　　　　");
			accountsSubList.add(combo);
		}

		return accountsSubList;
	}

	/**
	 * ﾃﾞｰﾀ種別、ﾃﾞｰﾀ集計区分を表示用に設定する
	 * @param dataType
	 * @param dataTotalDivision
	 */
	private String[] setText(final BigDecimal dataType,
			final BigDecimal dataTotalDivision) {

		String[] data = new String[2];
		if (dataType.equals(STR_ONE)) {
			data[0] = STR_ONE.toString().concat(STR_COLON).concat(STR_SALES);
			if (dataTotalDivision.equals(STR_ONE)) {
				data[1] = STR_ONE.toString().concat(STR_COLON)
						.concat(STR_SALES);
			} else if (dataTotalDivision.equals(STR_TWO)) {
				data[1] = STR_TWO.toString().concat(STR_COLON).concat(
					STR_RETURNED);
			} else if (dataTotalDivision.equals(STR_THREE)) {
				data[1] = STR_THREE.toString().concat(STR_COLON).concat(
					STR_DISCOUNT);
			} else if (dataTotalDivision.equals(STR_NINE)) {
				data[1] = STR_NINE.toString().concat(STR_COLON).concat(
					STR_OTHER);
			}
		} else if (dataType.equals(STR_TWO)) {
			data[0] = STR_TWO.toString().concat(STR_COLON).concat(STR_CREDIT);
			if (dataTotalDivision.equals(STR_ONE)) {
				data[1] = STR_ONE.toString().concat(STR_COLON).concat(
					STR_CREDIT);
			} else if (dataTotalDivision.equals(STR_TWO)) {
				data[1] = STR_TWO.toString().concat(STR_COLON).concat(
					STR_OFFSET);
			} else if (dataTotalDivision.equals(STR_NINE)) {
				data[1] = STR_NINE.toString().concat(STR_COLON).concat(
					STR_OTHER);
			}
		} else if (dataType.equals(STR_THREE)) {
			data[0] = STR_THREE.toString().concat(STR_COLON).concat(
				STR_STOCKING);
			if (dataTotalDivision.equals(STR_ONE)) {
				data[1] = STR_ONE.toString().concat(STR_COLON).concat(
					STR_STOCKING);
			} else if (dataTotalDivision.equals(STR_TWO)) {
				data[1] = STR_TWO.toString().concat(STR_COLON).concat(
					STR_RETURNED);
			} else if (dataTotalDivision.equals(STR_THREE)) {
				data[1] = STR_THREE.toString().concat(STR_COLON).concat(
					STR_DISCOUNT);
			} else if (dataTotalDivision.equals(STR_NINE)) {
				data[1] = STR_NINE.toString().concat(STR_COLON).concat(
					STR_OTHER);
			}
		} else if (dataType.equals(STR_FOUR)) {
			data[0] = STR_FOUR.toString().concat(STR_COLON).concat(STR_PAYMENT);
			if (dataTotalDivision.equals(STR_ONE)) {
				data[1] = STR_ONE.toString().concat(STR_COLON).concat(
					STR_PAYMENT);
			} else if (dataTotalDivision.equals(STR_TWO)) {
				data[1] = STR_TWO.toString().concat(STR_COLON).concat(
					STR_OFFSET);
			} else if (dataTotalDivision.equals(STR_NINE)) {
				data[1] = STR_NINE.toString().concat(STR_COLON).concat(
					STR_OTHER);
			}
		} else if (dataType.equals(STR_FIVE)) {
			data[0] = STR_FIVE.toString().concat(STR_COLON).concat(
				STR_GROUP_OFFSET);
			if (dataTotalDivision.equals(STR_ONE)) {
				data[1] = STR_ONE.toString().concat(STR_COLON).concat(
					STR_OFFSET);
			} else if (dataTotalDivision.equals(STR_NINE)) {
				data[1] = STR_NINE.toString().concat(STR_COLON).concat(
					STR_OTHER);
			}
		}
		return data;
	}

	/* -------------------- setter -------------------- */

	/**
	 * ClassificationDetailLogicを設定します。
	 * @param classificationDetailLogic classificationDetailLogic
	 */
	public void setClassificationDetailLogic(
			final ClassificationDetailLogic classificationDetailLogic) {
		this.classificationDetailLogic = classificationDetailLogic;

	}

	/**
	 * AccountsListLogicを設定します。
	 * @param accountsListLogic accountsListLogic
	 */
	public void setAccountsListLogic(final AccountsListLogic accountsListLogic) {
		this.accountsListLogic = accountsListLogic;

	}

}
