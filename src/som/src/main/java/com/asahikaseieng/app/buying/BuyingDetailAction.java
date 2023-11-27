/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.buying;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboboxesBean;
import com.asahikaseieng.app.common.CommonLogic;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.app.tax.GetTaxRatioLogic;
import com.asahikaseieng.dao.entity.master.company.Company;
import com.asahikaseieng.dao.entity.master.company.CompanyDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.buying.BuyingDetail;
import com.asahikaseieng.dao.nonentity.buyinginout.BuyingGetInoutData;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetailDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 購買の仕入詳細 Actionクラス.
 * @author tosco
 */
public final class BuyingDetailAction extends AbstractAction {
	/** 仕入詳細 ロジッククラス interface */
	private BuyingDetailLogic buyingDetailLogic;

	/** 発番処理 ロジッククラス interface */
	private GetNumberLogic getNumberLogic;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/** 新規フラグ定数 insertは'1' */
	public static final int INSERT = 1;

	/** 新規フラグ定数 updateは'0' */
	public static final int UPDATE = 0;

	/** 仕入ステータス定数 入力中は'1' */
	public static final int INPUTTING = 1;

	/** 仕入ステータス定数 承認依頼中は'2' */
	public static final int APPROVAL_REQUEST = 2;

	/** 行番号定数 仕入入力では'1'固定 */
	public static final int ROW_NO = 1;

	/** データ種別設定 仕入は'3'固定 */
	public static final int STOCKING = 3;

	/** 単価区分 1:個あたり */
	public static final String UNIT_KO = "1";

	/** 単価区分 2:kgあたり */
	public static final String UNIT_KG = "2";

	/** 算出区分 1:明細単位 */
	public static final BigDecimal CALC_DIVISION_MEISAI = new BigDecimal(1);

	/** 算出区分 4:自社ﾏｽﾀ */
	public static final BigDecimal CALC_DIVISION_COMPANY = new BigDecimal(4);

	/** 消費税課税区分 1:外税 */
	public static final BigDecimal TAX_DIVISION_OUT = new BigDecimal(1);

	/** 消費税課税区分 2:内税 */
	public static final BigDecimal TAX_DIVISION_IN = new BigDecimal(2);

	/** スポット区分 2:スポット */
	public static final String SPOT_DIVISION_SPOT = "2";

	/** ﾃﾞｰﾀ集計区分 2:返品 * */
	public static final String DATA_TOTAL_DIVISION_HENPIN = "2";

	/** ﾃﾞｰﾀ集計区分 3:値引き * */
	public static final String DATA_TOTAL_DIVISION_NEBIKI = "3";

	/** 仕入区分[2:返品] */
	public static final String STOCKING_DIVISION_RETURN = "2";

	// 2014/2/3 新消費税対応 ->
	private GetTaxRatioLogic getTaxRatioLogic;

	private PurchaseSubcontractDao purchaseSubcontractDao;
	private CommonLogic commonLogic;

	private VenderDao venderDao;
	private CompanyDao companyDao;

	// 2014/2/3 新消費税対応 <-

	/** 軽減措置割合取得用Dao */
	private NamesDetailDao namesDetailDao;

	/**
	 * コンストラクタ.
	 */
	public BuyingDetailAction() {
		super();
	}

	/**
	 * 検索処理(一覧画面の仕入伝票番号リンク押下時)
	 * @param frm frm
	 * @throws Exception Exception
	 */
	public void research(final BuyingDetailForm frm) throws Exception {

		// 新規用切替フラグを更新(0)に設定
		frm.setInsertFlg(UPDATE);

		try {
			// 購買NOを条件に、仕入詳細を取得し、beanに格納
			BuyingDetail bean = buyingDetailLogic.getEntity(frm.getSlipNo());

			// 検索結果データをフォームにコピーする
			IgnoreCaseBeanUtils.copyProperties(frm, bean);

			// javascriptでの桁数丸め用に必要となる値取得
			getCheckDigit(frm, bean);

			// 2014/2/4 新消費税対応 ->
			// 消費税額
			PurchaseSubcontract purchase = purchaseSubcontractDao.getEntity(frm.getPurchaseNo());

			frm.setStrTaxAmount(checker.format("TAX_AMOUNT", Constants.VENDER_DIVISION_SI, bean.getVenderCd(), purchase.getTaxAmount()));
			frm.setStrTaxDivision(purchase.getTaxDivision().toString());
			// 2019/09/05 新消費税対応で税コードと税コードから消費税率を取得
			frm.setTaxCd(purchase.getTaxCd().toString());
			frm.setTaxRatio(commonLogic.getTaxRatio(frm.getTaxCd(), null, null, null, null));

			// 画面表示時の受払番号、仕入区分を保持
			frm.setBeforeCategoryDivision(frm.getCategoryDivision());
			// 区分が返品の場合
			if (frm.getCategoryDivision().equals(STOCKING_DIVISION_RETURN)) {

				// 仕入番号から受払データを取得
				BuyingGetInoutData inoutData = buyingDetailLogic.getInoutData(frm.getSlipNo());

				// 受払データが取得できた場合
				if (inoutData != null) {

					frm.setInoutNo(inoutData.getInoutNo());
					frm.setInoutLotNo(inoutData.getLotNo());

					// 受払の数量（KG換算係数で割った値）
					frm.setInoutQty(buyingDetailLogic.getInoutQty(inoutData.getInoutQty(), frm.getUnitOfOperationManagement(),frm.getVenderCd()));

					// 受払の重量
					frm.setInoutWeight(buyingDetailLogic.getInoutQty(inoutData.getInoutWeight(), frm.getUnitOfOperationManagement(), frm.getVenderCd()));

					frm.setInoutDate(inoutData.getInoutDate());
					frm.setRyName(inoutData.getRyName());
					frm.setInputorName(inoutData.getInputorName());
					frm.setBeforeInoutNo(inoutData.getInoutNo());

				}
			}
			if (frm.getInoutNo() == null) {
				frm.setInoutNo("");
			}
			frm.setBeforeInoutNo(frm.getInoutNo());

			// **************************************
			// 仕入区分、入力出来ない場合、表示項目とする
			// **************************************
			// 仕入ステータス 2 or 3 の時は表示項目
			// 分類コード先頭1文字が"-" の時は表示項目
			if (bean.getStatus2().intValue() == 2 || bean.getStatus2().intValue() == 3	|| bean.getCategoryDivision().substring(0, 1).equals("-")) {
				// 表示する名称の設定
				// 取消データの元データの場合 マスタから取ってきた名称の後ろに"取消済"と追加
				if (bean.getCancelSlipNo() != null	&& !(bean.getCategoryDivision().substring(0, 1).equals("-"))) {
					frm.setCategoryName(bean.getCategoryName() + " 取消済");
				}
				// 仕入区分入力不可
				frm.setCategoryFlg("0");

				// 分類コードリスト
				String[] categoryDivisionList = {bean.getCategoryDivision()};
				frm.setCategoryDivisionList(categoryDivisionList);
				// 仕分反転フラグリスト
				String[] reversalFlgList = new String[1];
				if (DATA_TOTAL_DIVISION_HENPIN.equals(bean.getDataTotalDivision())	|| DATA_TOTAL_DIVISION_NEBIKI.equals(bean.getDataTotalDivision())) {
					// ﾃﾞｰﾀ集計区分=2:返品,3:値引の場合
					reversalFlgList[0] = "1";
				} else {
					reversalFlgList[0] = "0";
				}
				frm.setReversalFlgList(reversalFlgList);
			} else {
				// 仕入区分コンボボックスの作成
				buyingDetailLogic.createBuyingStockingDivisionCombobox(false,frm);
				// 仕入区分入力可
				frm.setCategoryFlg("1");
			}
			// **************************************
			// 取消ボタン表示・非表示の判断
			// **************************************
			if ((bean.getStatus2().intValue() == 3)
			// 仕入ステータス(3:承認済み)
					&& ((bean.getPayableUpdateDivision().intValue() == 1) || (bean.getPaymentUpdateDivision().intValue() == 1))
					// 月次更新済データ （支払更新フラグ(1:処理済) または 買掛更新フラグ(1:処理済)）
					&& (bean.getCancelSlipNo() == null)
					// ノーマルデータ（仕入－取消 仕入番号がNULL）
					&& !(bean.getCategoryDivision().substring(0, 1).equals("-"))) {
				// ノーマルデータ（分類コードの先頭が"-"でない）
				// 取消ボタンを表示する
				frm.setCancelFlg("1");
			} else {
				// 取消ボタンを表示しない
				frm.setCancelFlg("0");
			}

			// 軽減措置金額計算用の税率を取得
			getReducedTaxRatio(frm);

			// 更新時のために、フォームにbeanを格納しておく。
			frm.setBean(bean);

		} catch (NoDataException e) {
			throw e;
		}
	}

	/**
	 * 検索処理(一覧画面の仕入伝票番号リンク押下時)
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward1
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		// formを仕入一覧 Formクラスにキャスト
		BuyingDetailForm frm = (BuyingDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_BUYING,	Constants.TAB_ID_BUYING_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}
		try {
			// 消費税のコンボボックスの設定
			setPurchaseTaxCombobox(frm);
			research(frm);
		} catch (NoDataException e) {
			// エラーメッセージを登録する
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");

	}

	/**
	 * 新規登録画面表示処理(一覧画面の新規ボタン押下時)
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initNew(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initNew.");
		}

		// formを仕入一覧 Formクラスにキャスト
		BuyingDetailForm frm = (BuyingDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_BUYING,	Constants.TAB_ID_BUYING_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 仕入区分コンボボックスの作成
		buyingDetailLogic.createBuyingStockingDivisionCombobox(false, frm);
		// 仕入区分入力可
		frm.setCategoryFlg("1");

		// 新規用切替フラグを新規(1)に設定
		frm.setInsertFlg(INSERT);
		// 画面の変更フラグをクリア
		frm.setDirtyFlg("false");
		frm.setCancelFlg("0");
		frm.setPreReversalFlg("0");
		// ***********************
		// 初期表示
		// ***********************
		// 仕入日付
		frm.setStrStockingDate(AecDateUtils.dateFormat(AecDateUtils.getCurrentTimestamp(), "yyyy/MM/dd"));
		// 担当者コード
		frm.setTantoCd(getLoginInfo(request).getTantoCd());
		// 担当者名称
		frm.setTantoNm(getLoginInfo(request).getTantoNm());
		// 部署コード
		frm.setOrganizationCd(getLoginInfo(request).getOrganizationCd());
		// 部署名称
		frm.setSectionName(getLoginInfo(request).getOrganizationName());

		// 新規なので申請業者の表示はしない
		frm.setReducedTaxTargetFlg("0");

		// 軽減措置金額計算用の税率を取得
		getReducedTaxRatio(frm);

		// 消費税のコンボボックスの設定
		setPurchaseTaxCombobox(frm);
		return mapping.findForward("success");
	}

	/**
	 * 更新処理(詳細画面の登録ボタン押下時)
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("update.");
		}

		// formを仕入一覧 Formクラスにキャスト
		BuyingDetailForm frm = (BuyingDetailForm) form;

		// 入力された各コードのマスタ存在チェック
		// 1つでもマスタに存在しない値が入力されていたら
		if (!isMasterCheck(frm, request)) {
			return mapping.findForward("success");
		}

		ActionMessages messages = new ActionMessages();
		// 2014/2/26 新消費税対応 <-
		// 消費税率が新消費税率適応開始日以前の場合エラーとする
		// メッセージのキー取得のため、resource取得
		if (!isValidTaxRatio(frm.getStrStockingDate(), frm.getTaxRatio())) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.valid.tax.ratio"));
			saveErrors(request, messages);
			return mapping.findForward("success");
		}
		// 2014/2/26 新消費税対応 ->
		// 仕入区分が[2:返品]の場合
		if (frm.getCategoryDivision().equals(STOCKING_DIVISION_RETURN)) {

			// 受払が選択されている場合のみ数量のチェックを行う
			if (frm.getInoutNo() != null && !frm.getInoutNo().equals("")) {

				// 購買数量と受払数量が異なる場合エラー
				BigDecimal stock = new BigDecimal(StringUtils.replace(frm.getStrStockingQuantity(), ",", "")); // 購買数量
				BigDecimal inout = new BigDecimal(StringUtils.replace(frm.getInoutQty(), ",", "")).multiply(new BigDecimal(-1)); // 受入数量

				if (stock.compareTo(inout) != 0) {
					messages.add(ActionMessages.GLOBAL_MESSAGE,	new ActionMessage("errors.buying.qty.notsame"));
					saveErrors(request, messages);
					return mapping.findForward("success");
				}
			}
		}

		// 更新対象データを作成する
		// 詳細取得時のデータとクラスが違うため、個別に作成。
		PurchaseSubcontract newBean = new PurchaseSubcontract();
		// 詳細取得時のデータを取得
		BuyingDetail detailBean = (BuyingDetail) frm.getBean();
		// 2014/2/4 新消費税対応 ->
/*		frm.setTaxRatio(frm.getStrTaxRatio());*/
		// 2014/2/4 新消費税対応 <-
		/* 値を更新用Beanにコピる */
		// 画面の内容を詳細Beanへコピー
		IgnoreCaseBeanUtils.copyProperties(detailBean, frm);

		// 消費税関連項目取得
		setTaxRelatedData(frm,false);	// 更新や新規の場合、税コードを再取得しない為、第二引数をfalseとする

		//軽減税率対応　消費税率を税コードから取得　20190823
		newBean.setTaxCd(frm.getTaxCd());

		// 消費税コードのセット
		newBean.setTaxRatio(new BigDecimal(frm.getTaxRatio()));

		// 日付・数値項目の編集
		// 発注日・納品希望日時・数量・重量・単価・金額
		convert(detailBean, frm);
		// 詳細Beanの内容を更新用Beanへコピー
		IgnoreCaseBeanUtils.copyProperties(newBean, detailBean);

		// データ集計区分を取得し、設定
		String dataTotalDivision = buyingDetailLogic.getDataTotalDivision(frm.getCategoryDivision());
		newBean.setDataTotalDivision(new BigDecimal(dataTotalDivision));

		// 勘定年月に仕入日の年月(YYMM)を設定
		String stockingDate = frm.getStrStockingDate().replace("/", ""); // 仕入日を取得し/を取り除く
		newBean.setAccountYears(stockingDate.substring(0, 6)); // 先頭6文字を勘定年月に設定

		// 仕入ステータスセット(入力中 '1'固定)
		newBean.setStatus2(new BigDecimal(INPUTTING));

		// データ種別設定 仕入は'3'固定
		newBean.setDataType(new BigDecimal(STOCKING));
		// 2014/2/4 新消費税対応 ->
		newBean.setTaxDivision(new BigDecimal(frm.getStrTaxDivision()));
		// 2023/04/18 インボイス対応 免税措置計算を加味したものを更新する
		newBean.setStockingAmount(AecNumberUtils.convertNullToZero(AecNumberUtils.convertBigDecimal(detailBean.getStrInvoiceAmount())));
		newBean.setTaxAmount(AecNumberUtils.convertNullToZero(AecNumberUtils.convertBigDecimal(detailBean.getStrInvoiceTaxAmount())));
		// 2014/2/4 新消費税対応 <-
		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();
		// 更新者(ログインユーザー)セット
		newBean.setUpdatorCd(loginUserId);

		// スポット品の場合、画面の品目名称をセットする。(購買外注オーダーテーブルに書き込む)
		if (frm.getSpotDivision().equals(SPOT_DIVISION_SPOT)) {
			newBean.setItemName(frm.getItemMasterName());
		} else {
			newBean.setItemName(null);
		}
		// 支払先コードの設定
		newBean.setPayeeCd(detailBean.getPaymentInvoiceCd());

		try {
			// セットしたデータを使用し、仕入詳細を更新する。
			buyingDetailLogic.update(newBean, frm);
		} catch (NoDataException e) {
			// エラーメッセージを登録
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		// 更新完了メッセージを登録
		saveMessage(request, "message.complete.update");

		return mapping.findForward("back");

	}

	/**
	 * 新規登録処理(詳細画面の登録ボタン押下時)
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward insert(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("insert.");
		}
		// formを仕入一覧 Formクラスにキャスト
		BuyingDetailForm frm = (BuyingDetailForm) form;

		// 入力された各コードのマスタ存在チェック
		// 1つでもマスタに存在しない値が入力されていたら
		if (!isMasterCheck(frm, request)) {
			return mapping.findForward("success");
		}

		ActionMessages messages = new ActionMessages();

		// 2014/2/26 新消費税対応 <-
		// 消費税率が新消費税率適応開始日以前の場合エラーとする
		// メッセージのキー取得のため、resource取得
		if (!isValidTaxRatio(frm.getStrStockingDate(), frm.getTaxRatio())) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.valid.tax.ratio"));
			saveErrors(request, messages);
			return mapping.findForward("success");
		}
		// 2014/2/26 新消費税対応 ->

		// 仕入区分が[2:返品]の場合
		if (frm.getCategoryDivision().equals(STOCKING_DIVISION_RETURN)) {

			// 受払選択されている場合数量チェック
			if (frm.getInoutNo() != null && !frm.getInoutNo().equals("")) {
				// 購買数量と受払数量が異なる場合エラー
				BigDecimal stock = new BigDecimal(StringUtils.replace(frm
						.getStrStockingQuantity(), ",", "")); // 購買数量
				BigDecimal inout = new BigDecimal(StringUtils.replace(frm
						.getInoutQty(), ",", "")).multiply(new BigDecimal(-1)); // 受入数量

				if (stock.compareTo(inout) != 0) {
					messages.add(ActionMessages.GLOBAL_MESSAGE,	new ActionMessage("errors.buying.qty.notsame"));
					saveErrors(request, messages);
					return mapping.findForward("success");
				}
			}

		}


		// 登録対象データを作成する
		PurchaseSubcontract newBean = new PurchaseSubcontract();

		BuyingDetail detailBean = new BuyingDetail();

		/* 値を更新用Beanにコピる */
		// 画面の内容を詳細Beanへコピー
		IgnoreCaseBeanUtils.copyProperties(detailBean, frm);

		// 消費税関連項目取得
		setTaxRelatedData(frm,false);	// 更新や新規の場合、税コードを再取得しない為、第二引数をfalseとする

		// 消費税コードのセット
		newBean.setTaxCd(frm.getTaxCd());

		// 消費税コードのセット
		newBean.setTaxRatio(new BigDecimal(frm.getTaxRatio()));

		// 日付・数値項目の編集
		// 発注日・納品希望日時・数量・重量・単価・金額
		convert(detailBean, frm);
		// 詳細Beanの内容を更新用Beanへコピー
		IgnoreCaseBeanUtils.copyProperties(newBean, detailBean);

		// 仕入番号発番
		String slipNo = getNumberLogic.getSiSlipNo();
		// 発番した仕入番号セット
		newBean.setSlipNo(slipNo);

		// データ集計区分を取得し、設定
		String dataTotalDivision = buyingDetailLogic.getDataTotalDivision(frm.getCategoryDivision());
		newBean.setDataTotalDivision(new BigDecimal(dataTotalDivision));

		// 勘定年月に仕入日の年月(YYMM)を設定
		String stockingDate = frm.getStrStockingDate().replace("/", ""); // 仕入日を取得し/を取り除く
		newBean.setAccountYears(stockingDate.substring(0, 6)); // 先頭6文字を勘定年月に設定

		// 仕入ステータスセット(入力中 '1'固定)
		newBean.setStatus2(new BigDecimal(INPUTTING));

		// 購買ステータスセット(仕入は受入登録済'6'固定)
		newBean.setStatus(PurchaseStatus.ACCEPTED);

		// 行番号セット('1'固定)
		newBean.setRowNo(new BigDecimal(ROW_NO));

		// データ種別設定 仕入は'3'固定
		newBean.setDataType(new BigDecimal(STOCKING));

		// 2014/2/4 新消費税対応 ->
		newBean.setTaxDivision(new BigDecimal(frm.getStrTaxDivision()));

		// 2023/04/18 インボイス対応 免税措置計算を加味したものを更新する
		newBean.setStockingAmount(AecNumberUtils.convertNullToZero(AecNumberUtils.convertBigDecimal(detailBean.getStrInvoiceAmount())));
		newBean.setTaxAmount(AecNumberUtils.convertNullToZero(AecNumberUtils.convertBigDecimal(detailBean.getStrInvoiceTaxAmount())));
		// 2014/2/4 新消費税対応 <-
		// スポット品の場合、画面の品目名称をセットする。(購買外注オーダーテーブルに書き込む)
		if (frm.getSpotDivision().equals(SPOT_DIVISION_SPOT)) {
			newBean.setItemName(frm.getItemMasterName());
		} else {
			newBean.setItemName(null);
		}

		// システム日時、ログインユーザーの取得
		Timestamp now = AecDateUtils.getCurrentTimestamp();
		String loginUserId = getLoginInfo(request).getTantoCd();

		// 登録者セット
		newBean.setInputDate(now);
		newBean.setInputorCd(loginUserId);

		// 更新者セット
		newBean.setUpdateDate(now);
		newBean.setUpdatorCd(loginUserId);

		// 支払先コードの設定
		newBean.setPayeeCd(detailBean.getPaymentInvoiceCd());

		// セットしたデータを使用し、仕入詳細を登録する。
		buyingDetailLogic.insert(newBean, frm.getInoutNo());
		frm.setSlipNo(slipNo);
		try {
			research(frm);
		} catch (NoDataException e) {
			// エラーメッセージを登録する
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		// 登録完了メッセージの登録
		saveMessage(request, "message.complete.insert");

		return mapping.findForward("success");

	}

	/**
	 * 削除処理(詳細画面の削除ボタン押下時)
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delete(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("delete.");
		}

		// formを仕入一覧 Formクラスにキャスト
		BuyingDetailForm frm = (BuyingDetailForm) form;

		// 詳細表示時のデータを取得する
		BuyingDetail bean = frm.getBean();

		// 更新対象データを作成する
		PurchaseSubcontract newBean = new PurchaseSubcontract();

		// 画面の内容をnewBeanへコピー
		IgnoreCaseBeanUtils.copyProperties(newBean, bean);

		try {
			// 取得したBeanの削除を行う
			buyingDetailLogic.delete(newBean, getLoginInfo(request).getTantoCd(), frm);
		} catch (NoDataException e) {
			// エラーメッセージを登録する
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		// 削除完了メッセージを登録する
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");

	}

	/**
	 * 承認依頼処理(詳細画面の承認依頼ボタン押下時)
	 *
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approvalRrequest(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("approvalRrequest.");
		}

		// formを仕入一覧 Formクラスにキャスト
		BuyingDetailForm frm = (BuyingDetailForm) form;

		// 承認依頼対象データを作成する
		PurchaseSubcontract newBean = new PurchaseSubcontract();

		// 仕入番号をセット
		newBean.setSlipNo(frm.getSlipNo());

		// 仕入ステータス承認依頼中'2'(固定)セット
		newBean.setStatus2(new BigDecimal(APPROVAL_REQUEST));

		/* 更新者(ログインユーザー)セット */
		newBean.setUpdatorCd(getLoginInfo(request).getTantoCd());

		try {
			// 承認依頼処理を実行
			buyingDetailLogic.updateApprovalRequest(newBean);
		} catch (NoDataException e) {
			// エラーメッセージの登録
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		// 承認依頼完了メッセージの登録
		saveMessage(request, "message.buying.complete.request");

		return mapping.findForward("back");

	}

	/**
	 * 取消処理(詳細画面の取消ボタン押下時)
	 *
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward cancel(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("cancel.");
		}

		// formを仕入一覧 Formクラスにキャスト
		BuyingDetailForm frm = (BuyingDetailForm) form;

		// 仕入番号発番
		String slipNo = getNumberLogic.getSiSlipNo();

		// 登録対象データを作成する
		PurchaseSubcontract newBean = new PurchaseSubcontract();

		// ******************************************
		// 取消データの作成（新規追加）
		// ******************************************
		// 詳細取得時のデータを取得
		BuyingDetail preBean = (BuyingDetail) frm.getBean();

		// 担当部署コード
		newBean.setChargeOrganizationCd(preBean.getChargeOrganizationCd());
		// 部署コード
		newBean.setOrganizationCd(preBean.getOrganizationCd());
		// 担当者コード
		newBean.setTantoCd(preBean.getTantoCd());
		// 仕入先コード
		newBean.setVenderCd(preBean.getVenderCd());
		// 品目コード
		newBean.setItemCd(preBean.getItemCd());
		// 品目名称
		newBean.setItemName(preBean.getItemName());
		// 購買ステータスセット(仕入は受入登録済'6'固定)
		newBean.setStatus(PurchaseStatus.ACCEPTED);
		// データ種別設定 仕入は'3'固定
		newBean.setDataType(new BigDecimal(STOCKING));
		// データ集計区分を取得し、設定
		String dataTotalDivision = buyingDetailLogic
				.getDataTotalDivision(preBean.getCategoryDivision());
		newBean.setDataTotalDivision(new BigDecimal(dataTotalDivision));
		// 分類コード
		newBean.setCategoryDivision("-" + preBean.getCategoryDivision());
		// 仕入日付
		newBean.setStockingDate(AecDateUtils.getTimestampYmdFormat(frm.getStrStockingDate()));
		// newBean.setStockingDate(preBean.getStockingDate());
		// 勘定年月に仕入日の年月(YYYYMM)を設定
		newBean.setAccountYears(preBean.getAccountYears());
		// 仕入番号
		newBean.setSlipNo(slipNo);
		// 行番号セット('1'固定)
		newBean.setRowNo(new BigDecimal(ROW_NO));
		// 仕入取消－仕入番号（取消元の仕入番号）
		newBean.setCancelSlipNo(preBean.getSlipNo());
		// 仕入取消－行番号（取消元の行番号）
		newBean.setCancelRowNo(preBean.getRowNo());
		// 入庫ロケーション
		newBean.setHousingLocationCd(preBean.getHousingLocationCd());
		// 購入数量
		newBean.setStockingQuantity(preBean.getStockingQuantity());
		// 単価
		newBean.setHousingUnitprice(preBean.getHousingUnitprice());
		// 金額
		newBean.setStockingAmount(preBean.getStockingAmount());
		// 備考（ここだけ画面の内容をセット）
		newBean.setRemark2(frm.getRemark2());
		// 仕入ステータスセット(入力中 '1'固定)
		newBean.setStatus2(new BigDecimal(INPUTTING));
		// 消費税課税区分
		newBean.setTaxDivision(preBean.getTaxDivision());
		// 消費税率
		newBean.setTaxRatio(preBean.getTaxRatio());
		// 消費税額設定
		calcTaxAmount(newBean, preBean);
		// 勘定科目貸借を反転してセット
		// 会計部門
		newBean.setAccountDebitSectionCd(preBean.getAccountCreditSectionCd()); // 借方
		newBean.setAccountCreditSectionCd(preBean.getAccountDebitSectionCd()); // 貸方
		// 科目
		newBean.setDebitTitleCd(preBean.getCreditTitleCd()); // 借方
		newBean.setCreditTitleCd(preBean.getDebitTitleCd()); // 貸方
		// システム日時、ログインユーザーの取得
		Timestamp now = AecDateUtils.getCurrentTimestamp();
		String loginUserId = getLoginInfo(request).getTantoCd();
		// 登録者セット
		newBean.setInputDate(now);
		newBean.setInputorCd(loginUserId);
		// 更新者セット
		newBean.setUpdateDate(now);
		newBean.setUpdatorCd(loginUserId);

		// セットしたデータを使用し、仕入詳細を登録する。
		buyingDetailLogic.insert(newBean, "");

		// ******************************************
		// 取消元データの更新
		// ******************************************
		// 更新用データを作成する
		PurchaseSubcontract updateBean = new PurchaseSubcontract();

		// 詳細取得時のデータを取得
		BuyingDetail bean = (BuyingDetail) frm.getBean();

		// 承認依頼用Beanに詳細取得時のデータのコピーを行う
		IgnoreCaseBeanUtils.copyProperties(updateBean, bean);

		try {
			buyingDetailLogic.updateCancel(preBean.getSlipNo(), newBean.getSlipNo(), newBean.getRowNo(), loginUserId);

		} catch (NoDataException e) {
			// エラーメッセージの登録
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		// 登録完了メッセージの登録
		saveMessage(request, "message.buying.complete.cancel");

		return mapping.findForward("back");

	}

	/**
	 * 戻る処理(詳細画面または新規登録画面の戻るボタン押下時)
	 *
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");

	}

	/**
	 * マスタ存在チェック.
	 * @param frm マスタチェック対象フォーム
	 * @param response resource取得用のresponse
	 * @return boolean 全ての項目がマスタにある:true<br>
	 *         マスタにないものがある:false
	 */
	private boolean isMasterCheck(final BuyingDetailForm frm,final HttpServletRequest request) {

		// ActionMessagesの作成
		ActionMessages messages = new ActionMessages();
		// メッセージのキー取得のため、resource取得
		MessageResources resource = getResources(request);

		// 仕入先マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getVenderCd())) {
			if (0 == buyingDetailLogic.getCountVender(frm.getVenderCd())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.nodata.master", resource.getMessage("item.buying.supplier.cd")));
			} else {
				frm.setPaymentInvoiceCd(buyingDetailLogic.getPayeeCd(frm.getVenderCd()));
			}
		}

		// 品目マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getItemCd())) {
			if (0 == buyingDetailLogic.getCountItem(frm.getItemCd())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.nodata.master", resource.getMessage("item.buying.item.cd")));
			}
		}

		// 会計部門借方マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getAccountDebitSectionCd())) {
			if (0 == buyingDetailLogic.getCountBumon(frm.getAccountDebitSectionCd())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.nodata.master",resource.getMessage("item.buying.account.debit.section.cd")));
			}
		}

		// 会計部門貸方マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getAccountCreditSectionCd())) {
			if (0 == buyingDetailLogic.getCountBumon(frm.getAccountCreditSectionCd())) {
				messages
						.add(
							ActionMessages.GLOBAL_MESSAGE,
							new ActionMessage(
									"errors.nodata.master",
									resource
											.getMessage("item.buying.account.credit.section.cd")));
			}
		}

		// 借方科目マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getDebitTitleCd())) {
			if (0 == buyingDetailLogic.getCountAccounts(frm.getDebitTitleCd())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.nodata.master", resource
								.getMessage("item.buying.debit.title.cd")));
			}
		}

		// 借方借方マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getCreditTitleCd())) {
			if (0 == buyingDetailLogic.getCountAccounts(frm.getCreditTitleCd())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.nodata.master", resource
								.getMessage("item.buying.credit.title.cd")));
			}
		}

		// 担当部署マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getChargeOrganizationCd())) {
			if (0 == buyingDetailLogic.getCountOrganization(frm
					.getChargeOrganizationCd())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.nodata.master", resource
								.getMessage("item.buying.tanto.section.cd")));
			}
		}

		// 部署マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getOrganizationCd())) {
			if (0 == buyingDetailLogic.getCountOrganization(frm
					.getOrganizationCd())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.nodata.master", resource
								.getMessage("item.buying.section.cd")));
			}
		}

		// 担当者マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getTantoCd())) {
			if (0 == buyingDetailLogic.getCountLogin(frm.getTantoCd())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.nodata.master", resource
								.getMessage("item.buying.tanto.cd")));
			}
		}

		// 1つでもマスタに存在しないものがあれば
		if (!messages.isEmpty()) {
			// エラーメッセージの登録
			saveErrors(request, messages);
			return false;
		}

		return true;
	}

	/**
	 * 消費税関連項目を取得する。
	 * @param frm 仕入入力画面フォーム
	 */
	private void setTaxRelatedData(final BuyingDetailForm frm,final boolean blnGetTaxCd) {

		// 自社マスタから消費税率を取得している処理
//		BuyingDetail bean = buyingDetailLogic.getTaxRelatedData(frm.getItemCd(), frm.getVenderCd());
//		if (bean != null) {
//			frm.setCalcDivision(convertString(bean.getCalcDivision())); // 取引先マスタ.算出区分
//			frm.setCompCalcDivision(convertString(bean.getCompCalcDivision())); // 自社マスタ.消費税算出区分
//			frm.setTaxDivision(convertString(bean.getTaxDivision())); // 消費税課税区分
//			frm.setTaxRatio(convertString(bean.getTaxRatio())); // 自社マスタ.消費税率
//		}

		// 税コードを取得する場合再取得
		if(blnGetTaxCd){
			// 税コードを取得
			frm.setTaxCd(commonLogic.getTaxCd(frm.getStrStockingDate(), frm.getItemCd(), Constants.VENDER_DIVISION_SI, frm.getVenderCd(), "STOCKING", null, null, null, null, frm.getReducedTaxTargetFlg()));
		}

		// 税コードから消費税率を取得
		frm.setTaxRatio(commonLogic.getTaxRatio(frm.getTaxCd(), null, null, null, null));

		// 消費税区分をセット
		frm.setTaxDivision(commonLogic.getTaxDivision(frm.getItemCd(), Constants.VENDER_DIVISION_SI, frm.getVenderCd(), null, null));

		// 取引先マスタ.算出区分
		Vender vender = venderDao.getEntity(frm.getVenderCd(), Constants.VENDER_DIVISION_SI);
		if(vender != null){
			frm.setCalcDivision(convertString(vender.getCalcDivision()));
		}

		// 自社マスタ.消費税算出区分
		Company company = companyDao.getEntity(Constants.COMPANY_CD);
		if(company != null){
			frm.setCompCalcDivision(convertString(company.getCalcDivision()));
		}

	}

	/**
	 * BigDecimalからStringへ型変換を行う
	 * @param decimal BigDecimal値
	 * @return String String型に変換した値
	 */
	private String convertString(final BigDecimal decimal) {
		String ret = null;
		if (decimal != null) {
			ret = decimal.toString();
		}
		return ret;
	}

	/**
	 * 日付・数値項目の編集 DBへ書き込む前に行う 日付：スラッシュ除去 数値：カンマ除去
	 * @param detailBean
	 * @param frm
	 * @throws Exception
	 */
	private void convert(final BuyingDetail detailBean,	final BuyingDetailForm frm) throws Exception {

		// 仕入日付
		detailBean.setStockingDate(AecDateUtils.getTimestampYmdFormat(frm.getStrStockingDate()));
		// 数量 画面のカンマあり数量からカンマを除去
		detailBean.setStockingQuantity(AecNumberUtils.convertBigDecimal(frm.getStrStockingQuantity()));
		// 単価 画面のカンマあり単価からカンマを除去
		detailBean.setHousingUnitprice(AecNumberUtils.convertBigDecimal(frm.getStrHousingUnitprice()));
		// *************************************
		// 重量 再計算後セットする
		// *************************************
		// KG換算係数(在庫)、カンマ除去
		BigDecimal kg = AecNumberUtils.convertBigDecimal(frm.getKgOfFractionManagement());
		if (kg != null) {
			// 重量計算して小数点以下の処理後セット (数量*Kg換算係数)
			detailBean.setOrderConvertQuantity(checker.round("1", Constants.VENDER_DIVISION_SI, frm.getVenderCd(), detailBean.getStockingQuantity().multiply(kg)));
		}
		// *************************************
		// 金額 再計算後セットする
		// *************************************
		if (StringUtils.isEmpty(frm.getUnitpriceDivision())) {
			// 取引先単価マスタが存在しない場合は、個あたりと同じ計算とする
			// 金額計算して小数点以下の処理後セット (数量*単価)
			detailBean.setStockingAmount(checker.round("SIKINGAKU", Constants.VENDER_DIVISION_SI, frm.getVenderCd(), detailBean.getStockingQuantity().multiply(detailBean.getHousingUnitprice())));
		} else {
			// 個あたり
			if (frm.getUnitpriceDivision().equals(UNIT_KO)) {
				// 金額計算して小数点以下の処理後セット (数量*単価)
				detailBean.setStockingAmount(checker.round("SIKINGAKU", Constants.VENDER_DIVISION_SI,frm.getVenderCd(), detailBean.getStockingQuantity().multiply(detailBean.getHousingUnitprice())));
			}
			// Kgあたり
			if (kg != null) {
				if (frm.getUnitpriceDivision().equals(UNIT_KG)) {
					// 金額計算して小数点以下の処理後セット (数量*単価*Kg換算係数)
					detailBean.setStockingAmount(checker.round("SIKINGAKU",	Constants.VENDER_DIVISION_SI, frm.getVenderCd(), detailBean.getStockingQuantity().multiply(detailBean.getHousingUnitprice()).multiply(kg)));
				}
			}
		}
	}

	/**
	 * 品目コード入力時連動変更項目の検索を行う.
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getItemRelatedData(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getItemRelatedData.");
		}
		/* formをBuyingDetailFormにキャスト */
		BuyingDetailForm frm = (BuyingDetailForm) form;

		BuyingDetail getBean = new BuyingDetail();

		frm.setItemMasterName(null);
		frm.setOtherCompanyCd1(null);
		frm.setVenderCd(null);
		frm.setUnitOfOperationManagement(null);
		frm.setStockingQuantityUnit(null); // 数量の単位の名称
		frm.setKgOfFractionManagement(null);
		frm.setSpotDivision(null);
		frm.setHousingLocationCd(null);
		frm.setLocationName(null);
		frm.setAccountDebitSectionCd(null); // 借方
		frm.setAccountDebitSectionName(null); // 借方
		frm.setDebitTitleCd(null); // 借方
		frm.setDebitTitleName(null); // 借方

		frm.setVenderName1(null);
		frm.setUnitpriceDivision(null);
		frm.setHousingUnitpriceUnit(null); // 単価の単位の名称
		frm.setChargeOrganizationCd(null);
		frm.setTantoSectionName(null);
		frm.setAccountCreditSectionCd(null); // 貸方
		frm.setAccountCreditSectionName(null); // 貸方
		frm.setCreditTitleCd(null); // 貸方
		frm.setCreditTitleName(null); // 貸方

		frm.setStrHousingUnitprice(null);

		// 品目入力時に連動して変更する項目を検索
		// 品目コードによりDBから値取得
		getBean = buyingDetailLogic.getItemRelatedData(frm.getItemCd());

		if (getBean == null) {
			return mapping.findForward("success");
		}

		// 品目名称
		frm.setItemMasterName(getBean.getItemMasterName());
		// 他社コード
		frm.setOtherCompanyCd1(getBean.getOtherCompanyCd1());
		// 仕入先コード
		frm.setVenderCd(getBean.getVenderCd());
		// 運用管理単位
		frm.setUnitOfOperationManagement(getBean.getUnitOfOperationManagement());
		// 数量の単位の名称
		frm.setStockingQuantityUnit(getBean.getStockingQuantityUnit());
		// Kg換算係数(在庫)
		frm.setKgOfFractionManagement(getBean.getKgOfFractionManagement());
		// スポット区分
		if (getBean.getSpotDivision() != null) {
			frm.setSpotDivision(getBean.getSpotDivision().toString());
		}

		// 在庫管理区分
		if (getBean.getStockDivision() != null) {
			frm.setStockDivision(getBean.getStockDivision().toString());
		}

		// 仕訳設定
		setReversalSiwake(frm, getBean, "item");

		// ****************************************
		// 仕入先入力時に連動して変更する項目を検索
		// ****************************************
		if (getVenderRelatedDataFunc(mapping, form, request, response)) {
			// ****************************************
			// 仕入数量入力時に連動して変更する項目を検索
			// ****************************************
			getStockingQuantityRelatedDataFunc(mapping, form, request, response);
//
//			calcStockingAmountAndTax(mapping, form, request, response);
		}
		// 消費税関連項目取得
		setTaxRelatedData(frm,true);	// 更新や新規の場合、税コードを再取得しない為、第二引数をfalseとする

		// 2014/2/5 新消費税対応 ->
		frm.setStrTaxDivision(getTaxDivisionFromItem(frm.getItemCd()));
		// 2014/2/5 新消費税対応 <-

		return mapping.findForward("success");
	}

	/**
	 * 仕入先コード入力時連動変更項目の検索を行う.
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getVenderRelatedData(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getVenderRelatedData.");
		}

		/* formをBuyingDetailFormにキャスト */
		BuyingDetailForm frm = (BuyingDetailForm) form;

		// ****************************************
		// 仕入先入力時に連動して変更する項目を検索
		// ****************************************
		if (getVenderRelatedDataFunc(mapping, form, request, response)) {
			// ****************************************
			// 仕入数量入力時に連動して変更する項目を検索
			// ****************************************
			getStockingQuantityRelatedDataFunc(mapping, form, request, response);
		}

		setTaxRelatedData(frm,true);	// 更新や新規の場合、税コードを再取得しない為、第二引数をfalseとする

		return mapping.findForward("success");
	}

	/**
	 * 仕入先コード入力時連動変更項目の検索を行う.
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public Boolean getVenderRelatedDataFunc(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		/* formをBuyingDetailFormにキャスト */
		BuyingDetailForm frm = (BuyingDetailForm) form;

		BuyingDetail getBean = new BuyingDetail();

		frm.setVenderName1(null);
		frm.setVenderShortedName(null);
		frm.setUnitpriceDivision(null); // 単価の単位区分(仕入先別単価マスタより)
		frm.setHousingUnitpriceUnit(null); // 単価の単位の名称
		frm.setChargeOrganizationCd(null);
		frm.setTantoSectionName(null);
		// 仕訳クリア
		clearSiwake(frm);

		frm.setStrHousingUnitprice(null); // 単価

		// 仕入先入力時に連動して変更する項目を検索
		// 品目コード、仕入先コード、仕入数量によりDBから値取得
		// 仕入数量は、画面表示項目のカンマ編集を解除した値を渡す
		getBean = buyingDetailLogic.getVenderRelatedData(frm.getItemCd(), frm.getVenderCd());
		if (getBean == null) {
			return false;
		}

		// 画面表示項目にセット
		// 仕入先名称
		frm.setVenderName1(getBean.getVenderName1());
		// 仕入先略称
		frm.setVenderShortedName(getBean.getVenderShortedName());

		// 単価区分(仕入先別単価マスタの単位区分を使用する)
		if (getBean.getUnitpriceDivision() != null) {
			frm.setUnitpriceDivision(getBean.getUnitpriceDivision().toString());
		}
		// 単価名称
		frm.setHousingUnitpriceUnit(getBean.getHousingUnitpriceUnit());
		// 担当部署コード
		frm.setChargeOrganizationCd(getBean.getChargeOrganizationCd());
		// 担当部署名称
		frm.setTantoSectionName(getBean.getTantoSectionName());
		// 仕訳設定
		setReversalSiwake(frm, getBean, "vender");

		// 支払先コード
		frm.setPaymentInvoiceCd(getBean.getPaymentInvoiceCd());

		// 免税計算対象フラグ
		frm.setReducedTaxTargetFlg(getBean.getReducedTaxTargetFlg());

		getCheckDigit(frm, getBean);

		return true;
	}

	/**
	 * 仕入数量入力時連動変更項目の検索を行う.
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getStockingQuantityRelatedData(final ActionMapping mapping, final ActionForm form,	final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getStockingQuantityRelatedData.");
		}

		getStockingQuantityRelatedDataFunc(mapping, form, request, response);

		return mapping.findForward("success");
	}

	/**
	 * 仕入数量入力時連動変更項目の検索を行う.
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public Boolean getStockingQuantityRelatedDataFunc(final ActionMapping mapping, final ActionForm form,	final HttpServletRequest request, final HttpServletResponse response)	throws Exception {

		/* formをBuyingDetailFormにキャスト */
		BuyingDetailForm frm = (BuyingDetailForm) form;
		BuyingDetail getBean = new BuyingDetail();

		// 仕入数量が未入力の場合は検索しないで終了。
		if (StringUtils.isEmpty(frm.getStrStockingQuantity())) {
			return false;
		}

		// *************************************
		// 仕入数量フォーマット
		// *************************************
		// 仕入数量(画面入力した時のままなので、フォーマットなし。だからフォーマットする)(運用管理単位を渡す)
		frm.setStrStockingQuantity(checker.format(frm.getUnitOfOperationManagement(), Constants.VENDER_DIVISION_SI, frm.getVenderCd(),AecNumberUtils.convertBigDecimal(frm.getStrStockingQuantity())));

		// DBから取得する箇所をクリアする
		// 単価
		frm.setStrHousingUnitprice(null);
		frm.setStrStockingAmount(null);

		// *************************************
		// 仕入数量入力時に連動して変更する項目を検索
		// 品目コード、仕入先コード、仕入数量によりDBから値取得
		// 仕入数量は、画面表示項目のカンマ編集を解除した値を渡す
		// *************************************
		getBean = buyingDetailLogic.getStockingQuantityRelatedData(frm.getItemCd(), frm.getVenderCd(), AecNumberUtils.convertBigDecimal(frm.getStrStockingQuantity()));

		if (getBean == null) {
			return false;
		}

		// 画面表示項目にセット
		// 仕入単価(DBから取得した値を数値フォーマット編集後、画面にセット)
		frm.setStrHousingUnitprice(checker.format("SITANKA", Constants.VENDER_DIVISION_SI, frm.getVenderCd(), getBean.getHousingUnitprice()));

		// *************************************
		// 重量・金額の計算で必要な値の取得
		// *************************************
		// 仕入数量、カンマ除去
		BigDecimal decQuantity = AecNumberUtils.convertBigDecimal(frm.getStrStockingQuantity());
		// 単価、カンマ除去
		BigDecimal decPrice = AecNumberUtils.convertBigDecimal(frm.getStrHousingUnitprice());
		// KG換算係数(在庫)、カンマ除去
		BigDecimal decKgOfFraction = AecNumberUtils.convertBigDecimal(frm.getKgOfFractionManagement());

		// *************************************
		// 金額計算し、画面にセット
		// *************************************
		if (StringUtils.isEmpty(frm.getUnitpriceDivision())) {
			// 取引先単価マスタが存在しない場合は、個あたりと同じ計算とする
			// 仕入金額
			frm.setStrStockingAmount(checker.format("SIKINGAKU", Constants.VENDER_DIVISION_SI, getBean.getVenderCd(), decQuantity.multiply(decPrice)));
		} else {
			// 個あたり
			if (frm.getUnitpriceDivision().equals(UNIT_KO)) {
				// 仕入金額
				frm.setStrStockingAmount(checker.format("SIKINGAKU", Constants.VENDER_DIVISION_SI,getBean.getVenderCd(), decQuantity.multiply(decPrice)));
			}
			// kgあたり
			if (frm.getUnitpriceDivision().equals(UNIT_KG)) {
				// 仕入金額
				frm.setStrStockingAmount(checker.format("SIKINGAKU", Constants.VENDER_DIVISION_SI,getBean.getVenderCd(), decQuantity.multiply(decPrice).multiply(decKgOfFraction)));
			}
		}
		return true;
	}

	/**
	 * 数値桁数チェック部品からjavascriptでの桁数丸め用に必要となる値を取得
	 * @param frm 発注画面Form
	 * @param bean 発注Bean
	 */
	private void getCheckDigit(final BuyingDetailForm frm,final BuyingDetail bean) {

		// *******************************
		// 数値桁数チェックマスタメンテから設定を取得
		// *******************************
		NumberChkDisitDetail checkDetail;

		// 仕入数量
		checkDetail = checker.getCheckDigit(frm.getUnitOfOperationManagement(),	Constants.VENDER_DIVISION_SI, bean.getVenderCd());
		if (checkDetail.getSmallnumLength() != null) { // 小数点以下桁数
			frm.setStrStockingQuantityDecimalPoint(checkDetail.getSmallnumLength().toString());
		}
		if (checkDetail.getRoundDivision() != null) { // 端数区分
			frm.setStrStockingQuantityRound(checkDetail.getRoundDivision().toString());
		}

		// 仕入単価
		checkDetail = checker.getCheckDigit("SITANKA", Constants.VENDER_DIVISION_SI, bean.getVenderCd());
		if (checkDetail.getSmallnumLength() != null) { // 小数点以下桁数
			frm.setStrHousingUnitpriceDecimalPoint(checkDetail.getSmallnumLength().toString());
		}
		if (checkDetail.getRoundDivision() != null) { // 端数区分
			frm.setStrHousingUnitpriceRound(checkDetail.getRoundDivision().toString());
		}

		// 仕入金額
		checkDetail = checker.getCheckDigit("SIKINGAKU", Constants.VENDER_DIVISION_SI, bean	.getVenderCd());
		if (checkDetail.getSmallnumLength() != null) { // 小数点以下桁数
			frm.setStrStockingAmountDecimalPoint(checkDetail.getSmallnumLength().toString());
		}
		if (checkDetail.getRoundDivision() != null) { // 端数区分
			frm.setStrStockingAmountRound(checkDetail.getRoundDivision().toString());
		}
		// 消費税額(TAX_AMOUNT)
		checkDetail = checker.getCheckDigit("TAX_AMOUNT", Constants.VENDER_DIVISION_SI, bean.getVenderCd());
		if (checkDetail.getSmallnumLength() != null) { // 小数点以下桁数
			frm.setStrTaxAmountDecimalPoint(checkDetail.getSmallnumLength()
					.toString());
		}
		if (checkDetail.getRoundDivision() != null) { // 端数区分
			frm.setStrTaxAmountRound(checkDetail.getRoundDivision().toString());
		}

	}

	/**
	 * 消費税額算出
	 * @param updBean 購買外注オーダ更新Bean
	 * @param frm 仕入入力画面Form
	 * @param check 数値項目用表示ロジッククラス
	 */
	private void calcTaxAmount(final PurchaseSubcontract updBean,final BuyingDetailForm frm) {

		// 2014/2/4 新消費税対応 ->
		// 消費税関連項目取得
		setTaxRelatedData(frm,false);	// 更新や新規の場合、税コードを再取得する為、第二引数をfalseとする
		// 2014/2/4 新消費税対応 <-

		BigDecimal calcDiv = AecNumberUtils.convertBigDecimal(frm.getCalcDivision()); // 取引先ﾏｽﾀ.算出区分
		BigDecimal compCalcDiv = AecNumberUtils.convertBigDecimal(frm.getCompCalcDivision()); // 自社ﾏｽﾀ.消費税算出区分

		// 取引先ﾏｽﾀ.算出区分＝1(明細)の場合
		// 取引先ﾏｽﾀ.算出区分＝4(自社ﾏｽﾀ) かつ 自社ﾏｽﾀ.消費税算出区分＝1(明細)の場合
		if (CALC_DIVISION_MEISAI.equals(calcDiv)|| (CALC_DIVISION_COMPANY.equals(calcDiv) && CALC_DIVISION_MEISAI.equals(compCalcDiv))) {

			// 消費税課税区分=1:外税または2:内税
			// 2014/2/4 新消費税対応 ->
			// if (TAX_DIVISION_OUT.equals(updBean.getTaxDivision())) {
			if (TAX_DIVISION_OUT.toString().equals(frm.getStrTaxDivision())) {
				// 2014/2/4 新消費税対応 <-
				BigDecimal stockingAmount = AecNumberUtils.convertBigDecimal(frm.getStrStockingAmount()); // 仕入金額
				// 2014/2/4 新消費税対応 ->
				BigDecimal taxRatio = AecNumberUtils.convertBigDecimal(frm.getTaxRatio()); // 消費税率
				// 2014/2/4 新消費税対応 ->
				taxRatio = taxRatio.divide(new BigDecimal(100)); // 消費税率は％のため100で割る
				// 消費税額＝仕入金額×消費税率
				BigDecimal taxAmount = stockingAmount.multiply(taxRatio);
				// 丸め処理
				taxAmount = checker.round("TAX_AMOUNT", Constants.VENDER_DIVISION_SI,frm.getVenderCd(), taxAmount);
				updBean.setTaxAmount(taxAmount);
				// 2014/2/4 新消費税対応 ->
			} else if (TAX_DIVISION_IN.toString().equals(
				frm.getStrTaxDivision())) {
				// 2014/2/4 新消費税対応 ->
				BigDecimal stockingAmount = AecNumberUtils.convertBigDecimal(frm.getStrStockingAmount()); // 仕入金額
				// 2014/2/4 新消費税対応 ->
				BigDecimal taxRatio = AecNumberUtils.convertBigDecimal(frm.getTaxRatio()); // 消費税率
				// 2014/2/4 新消費税対応 ->
				taxRatio = taxRatio.divide(new BigDecimal(100)).add(BigDecimal.ONE); // 消費税率は％のため100で割る
				// 身代額＝仕入金額／（1+消費税率）
				BigDecimal taxAmount = stockingAmount.divide(taxRatio, 10,BigDecimal.ROUND_DOWN);
				// 消費税＝仕入金額－身代
				taxAmount = stockingAmount.subtract(taxAmount);
				// 丸め処理
				taxAmount = checker.round("TAX_AMOUNT", Constants.VENDER_DIVISION_SI,frm.getVenderCd(), taxAmount);
				updBean.setTaxAmount(taxAmount);
				// 身代＝仕入金額－消費税額
				stockingAmount = stockingAmount.subtract(taxAmount);
				updBean.setStockingAmount(stockingAmount);
			} else {
				updBean.setTaxAmount(BigDecimal.ZERO);
			}
		} else {
			updBean.setTaxAmount(BigDecimal.ZERO);
		}
	}

	/**
	 * 仕入金額と消費税額を計算する
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward calcStockingAmountAndTax(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("calcStockingAmountAndTax.");
		}
		BuyingDetailForm frm = (BuyingDetailForm) form;

		if (frm.getStrStockingAmount() == null	|| frm.getStrStockingAmount().equals("")	|| !isNumber(frm.getStrStockingAmount())) {
			frm.setStrStockingAmount(BigDecimal.ZERO.toString());
		}
		if (frm.getStrHousingUnitprice() == null || frm.getStrHousingUnitprice().equals("")	|| !isNumber(frm.getStrHousingUnitprice())) {
			frm.setStrHousingUnitprice(BigDecimal.ZERO.toString());
		}
		frm.setTaxRatio(frm.getTaxRatio());

		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

//		//軽減税率対応　消費税率を税コードから取得　20190823
//		frm.setTaxCd(commonLogic.getTaxCd(frm.getStrStockingDate(), frm.getItemCd(), Constants.VENDER_DIVISION_SI, frm.getVenderCd(), "STOCKING", null, null, null, null));

		// 消費税コードから消費税率をセット
		frm.setTaxRatio(commonLogic.getTaxRatio(frm.getTaxCd(), null, null, null, null));
		BigDecimal taxRatio = new BigDecimal(frm.getTaxRatio());

		// 取引先ﾏｽﾀ.算出区分＝1(明細)の場合
		// 取引先ﾏｽﾀ.算出区分＝4(自社ﾏｽﾀ) かつ 自社ﾏｽﾀ.消費税算出区分＝1(明細)の場合
		if (CALC_DIVISION_MEISAI.equals(AecNumberUtils.convertBigDecimal(frm.getCalcDivision()))
				|| (CALC_DIVISION_COMPANY.equals(AecNumberUtils.convertBigDecimal(frm.getCalcDivision())) &&
						CALC_DIVISION_MEISAI.equals(AecNumberUtils.convertBigDecimal(frm.getCompCalcDivision())))) {

			// 消費税課税区分=1:外税または2:内税
			if (TAX_DIVISION_OUT.equals(AecNumberUtils.convertBigDecimal(frm.getStrTaxDivision()))) {
				BigDecimal stockingAmount = AecNumberUtils.convertNullToZero(AecNumberUtils.convertBigDecimal(frm.getStrStockingAmount())); // 仕入金額
				taxRatio = taxRatio.divide(new BigDecimal(100)); // 消費税率は％のため100で割る
				// 消費税額＝仕入金額×消費税率
				BigDecimal taxAmount = stockingAmount.multiply(taxRatio);
				// 丸め処理
				frm.setStrTaxAmount(check.format("TAX_AMOUNT", Constants.VENDER_DIVISION_SI, frm
						.getVenderCd(), taxAmount));
			} else if (TAX_DIVISION_IN.equals(AecNumberUtils.convertBigDecimal(frm.getStrTaxDivision()))) {
				BigDecimal stockingAmount = AecNumberUtils.convertNullToZero(AecNumberUtils.convertBigDecimal(frm.getStrStockingAmount())); // 仕入金額
				taxRatio = taxRatio.divide(new BigDecimal(100)).add(BigDecimal.ONE); // 消費税率は％のため100で割る
				// 身代額＝仕入金額／（1+消費税率）
				BigDecimal taxAmount = stockingAmount.divide(taxRatio, 10,	BigDecimal.ROUND_DOWN);
				// 消費税＝仕入金額－身代
				taxAmount = stockingAmount.subtract(taxAmount);
				// 丸め処理
				frm.setStrTaxAmount(check.format("TAX_AMOUNT", Constants.VENDER_DIVISION_SI, frm.getVenderCd(), taxAmount));

				// 身代＝仕入金額－消費税額
				stockingAmount = stockingAmount.subtract(taxAmount);
				frm.setStrStockingAmount(check.format("SIKINGAKU", Constants.VENDER_DIVISION_SI, frm.getVenderCd(), stockingAmount));

			} else {
				frm.setStrTaxAmount("0");
			}
		} else {
			frm.setStrTaxAmount("0");
		}
		frm.setTaxCdChangeFlg("false");

		return mapping.findForward("success");
	}


	/**
	 * 消費税額算出
	 * @param updBean 購買外注オーダ更新Bean
	 * @param bean 保持している購買外注データBean
	 * @param check 数値項目用表示ロジッククラス
	 */
	private void calcTaxAmount(final PurchaseSubcontract updBean,final BuyingDetail bean) {

		BigDecimal calcDiv = bean.getCalcDivision(); // 取引先ﾏｽﾀ.算出区分
		BigDecimal compCalcDiv = bean.getCompCalcDivision(); // 自社ﾏｽﾀ.消費税算出区分

		// 取引先ﾏｽﾀ.算出区分＝1(明細)の場合
		// 取引先ﾏｽﾀ.算出区分＝4(自社ﾏｽﾀ) かつ 自社ﾏｽﾀ.消費税算出区分＝1(明細)の場合
		if (CALC_DIVISION_MEISAI.equals(calcDiv)
				|| (CALC_DIVISION_COMPANY.equals(calcDiv) && CALC_DIVISION_MEISAI
						.equals(compCalcDiv))) {

			// 消費税課税区分=1:外税または2:内税
			if (TAX_DIVISION_OUT.equals(updBean.getTaxDivision())) {
				BigDecimal stockingAmount = bean.getStockingAmount(); // 仕入金額
				BigDecimal taxRatio = bean.getTaxRatio(); // 消費税率
				taxRatio = taxRatio.divide(new BigDecimal(100)); // 消費税率は％のため100で割る
				// 消費税額＝仕入金額×消費税率
				BigDecimal taxAmount = stockingAmount.multiply(taxRatio);
				// 丸め処理
				taxAmount = checker.round("TAX_AMOUNT", Constants.VENDER_DIVISION_SI, bean
						.getVenderCd(), taxAmount);
				updBean.setTaxAmount(taxAmount);
			} else if (TAX_DIVISION_IN.equals(updBean.getTaxDivision())) {
				BigDecimal stockingAmount = bean.getStockingAmount(); // 仕入金額
				BigDecimal taxRatio = bean.getTaxRatio(); // 消費税率
				taxRatio = taxRatio.divide(new BigDecimal(100)).add(
					BigDecimal.ONE); // 消費税率は％のため100で割る
				// 身代額＝仕入金額／（1+消費税率）
				BigDecimal taxAmount = stockingAmount.divide(taxRatio, 10,
					BigDecimal.ROUND_DOWN);
				// 消費税＝仕入金額－身代
				taxAmount = stockingAmount.subtract(taxAmount);
				// 丸め処理
				taxAmount = checker.round("TAX_AMOUNT", Constants.VENDER_DIVISION_SI, bean
						.getVenderCd(), taxAmount);
				updBean.setTaxAmount(taxAmount);
				// 身代＝仕入金額－消費税額
				stockingAmount = stockingAmount.subtract(taxAmount);
				updBean.setStockingAmount(stockingAmount);
			} else {
				updBean.setTaxAmount(BigDecimal.ZERO);
			}
		} else {
			updBean.setTaxAmount(BigDecimal.ZERO);
		}
	}

	/**
	 * 仕入により仕訳を反転して設定する。 [品目の場合] 仕入のデータ集計区分＝2:返品,3:値引の場合 貸方に設定
	 * 仕入のデータ集計区分＝上記以外の場合 借方に設定 [仕入先の場合] 仕入のデータ集計区分＝2:返品,3:値引の場合 借方に設定
	 * 仕入のデータ集計区分＝上記以外の場合 貸方に設定
	 *
	 * @param frm 仕入入力画面フォーム
	 * @param getBean 品目関連Bean
	 * @param value "item":品目、"vender":仕入先
	 */
	private void setReversalSiwake(final BuyingDetailForm frm,final BuyingDetail getBean, final String value) {
		String selCategoryDivision = frm.getCategoryDivision();
		String reversalFlg = null;

		int index = 0;
		for (String categoryDivision : frm.getCategoryDivisionList()) {
			// 選択した仕入(分類コード)に対する反転フラグ取得
			if (selCategoryDivision.equals(categoryDivision)) {
				reversalFlg = frm.getReversalFlgList()[index];
				break;
			}
			index++;
		}

		if ("item".equals(value)) {
			// 品目の場合
			if ("1".equals(reversalFlg)) {
				// 反転あり
				frm.setAccountCreditSectionCd(getBean.getAccountDebitSectionCd()); // 貸方
				frm.setAccountCreditSectionName(getBean.getAccountDebitSectionName()); // 貸方
				frm.setCreditTitleCd(getBean.getDebitTitleCd()); // 貸方
				frm.setCreditTitleName(getBean.getDebitTitleName()); // 貸方
			} else {
				// 反転なし
				frm.setAccountDebitSectionCd(getBean.getAccountDebitSectionCd()); // 借方
				frm.setAccountDebitSectionName(getBean.getAccountDebitSectionName()); // 借方
				frm.setDebitTitleCd(getBean.getDebitTitleCd()); // 借方
				frm.setDebitTitleName(getBean.getDebitTitleName()); // 借方
			}
		} else {
			// 仕入先の場合
			if ("1".equals(reversalFlg)) {
				// 反転あり
				frm.setAccountDebitSectionCd(getBean.getAccountCreditSectionCd()); // 借方
				frm.setAccountDebitSectionName(getBean.getAccountCreditSectionName()); // 借方
				frm.setDebitTitleCd(getBean.getCreditTitleCd()); // 借方
				frm.setDebitTitleName(getBean.getCreditTitleName()); // 借方
			} else {
				// 反転なし
				frm.setAccountCreditSectionCd(getBean.getAccountCreditSectionCd()); // 貸方
				frm.setAccountCreditSectionName(getBean.getAccountCreditSectionName()); // 貸方
				frm.setCreditTitleCd(getBean.getCreditTitleCd()); // 貸方
				frm.setCreditTitleName(getBean.getCreditTitleName()); // 貸方
			}
		}
	}

	/**
	 * 仕入先コード入力時、仕訳のクリア処理を行う
	 *
	 * @param frm 仕入入力画面フォーム
	 */
	private void clearSiwake(final BuyingDetailForm frm) {
		String selCategoryDivision = frm.getCategoryDivision();
		String reversalFlg = null;

		int index = 0;
		for (String categoryDivision : frm.getCategoryDivisionList()) {
			// 選択した仕入(分類コード)に対する反転フラグ取得
			if (selCategoryDivision.equals(categoryDivision)) {
				reversalFlg = frm.getReversalFlgList()[index];
				break;
			}
			index++;
		}

		if ("1".equals(reversalFlg)) {
			// 反転あり
			frm.setAccountDebitSectionCd(null); // 借方
			frm.setAccountDebitSectionName(null); // 借方
			frm.setDebitTitleCd(null); // 借方
			frm.setDebitTitleName(null); // 借方
		} else {
			// 反転なし
			frm.setAccountCreditSectionCd(null); // 貸方
			frm.setAccountCreditSectionName(null); // 貸方
			frm.setCreditTitleCd(null); // 貸方
			frm.setCreditTitleName(null); // 貸方
		}
	}

	// 2014/1/29 新消費税対応 ->
	/**
	 * 仕入金額と消費税額を計算する
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward calcSalesAmountAndTax(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("calcSalesAmountAndTax.");
		}
		BuyingDetailForm frm = (BuyingDetailForm) form;

		// 更新対象データを作成する
		// 詳細取得時のデータとクラスが違うため、個別に作成。
		PurchaseSubcontract newBean = new PurchaseSubcontract();
		// 詳細取得時のデータを取得
		BuyingDetail detailBean = new BuyingDetail();

		/* 値を更新用Beanにコピる */
		// 画面の内容を詳細Beanへコピー
		IgnoreCaseBeanUtils.copyProperties(detailBean, frm);

		if (frm.getStrStockingQuantity() == null || frm.getStrStockingQuantity().equals("")	|| !isNumber(frm.getStrStockingQuantity())) {
			frm.setStrStockingQuantity(BigDecimal.ZERO.toString());
		}

		if (frm.getStrStockingAmount() == null || frm.getStrStockingAmount().equals("")	|| !isNumber(frm.getStrStockingAmount())) {
			frm.setStrStockingAmount(BigDecimal.ZERO.toString());
		}
		if (frm.getStrHousingUnitprice() == null	|| frm.getStrHousingUnitprice().equals("")	|| !isNumber(frm.getStrHousingUnitprice())) {
			frm.setStrHousingUnitprice(BigDecimal.ZERO.toString());
		}

		// 日付・数値項目の編集
		// 発注日・納品希望日時・数量・重量・単価・金額
		convert(detailBean, frm);
		// 詳細Beanの内容を更新用Beanへコピー
		IgnoreCaseBeanUtils.copyProperties(newBean, detailBean);

		newBean.setTaxDivision(new BigDecimal(frm.getStrTaxDivision()));

		// 消費税額設定
		calcTaxAmount(newBean, frm);

		frm.setStrStockingAmount(checker.format("SIKINGAKU", Constants.VENDER_DIVISION_SI, newBean.getVenderCd(), newBean.getStockingAmount()));

		frm.setStrTaxAmount(checker.format("TAX_AMOUNT", Constants.VENDER_DIVISION_SI, newBean.getVenderCd(), newBean.getTaxAmount()));

		return mapping.findForward("success");
	}

	/**
	 *
	 * 文字列を数値であるかチェックを行う
	 * @param val チェック文字列
	 * @return true:数値 false:数値以外
	 */
	public boolean isNumber(final String val) {
		try {
			AecNumberUtils.convertBigDecimal(val);
			return true;
		} catch (NumberFormatException nfex) {
			return false;
		}
	}

	/**
	 * 仕入日を変更した際、消費税コードを取得する
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward changeStockingDate(final ActionMapping mapping,final ActionForm form,
			final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getTaxRatio.");
		}
		BuyingDetailForm frm = (BuyingDetailForm) form;

		// 品目と取引先が決定している場合、税コードを取得する
		if(frm.getItemCd()!=null && frm.getVenderCd() != null){
			frm.setTaxCd(commonLogic.getTaxCd(frm.getStrStockingDate(), frm.getItemCd(), Constants.VENDER_DIVISION_SI, frm.getVenderCd(), "STOCKING", null, null, null, null, frm.getReducedTaxTargetFlg()));
			// 消費税コードから消費税率を取得
			frm.setTaxRatio(commonLogic.getTaxRatio(frm.getTaxCd(), null, null, null, null));
		}

		// 消費税額を計算する
		calcSalesAmountAndTax(mapping, form, request, response);

		// 軽減措置金額計算用の税率を取得
		getReducedTaxRatio(frm);

		return mapping.findForward("success");
	}


	/**
	 *
	 * 品目から消費税区分を取得する
	 * @param itemCd 品目コード
	 * @return 消費税区分
	 */
	public String getTaxDivisionFromItem(final String itemCd) {

		return getTaxRatioLogic.getTaxDivisionFromItem(itemCd, "1");
	}

	/**
	 * 軽減措置計算の税率を取得
	 * @param form BuyingDetailForm
	 */
	private void getReducedTaxRatio(final BuyingDetailForm frm) {

		// 税額控除割合を取得(名称マスタの期限と仕入日によって決定する)
		String checkDate =  frm.getStrStockingDate();
		if( checkDate.length() == 6 ){
			checkDate = "20"+ checkDate;
		}
		NamesDetail bean = namesDetailDao.getTaxFreeRatio(StringUtils.replace(checkDate, "/", ""));
		if(bean != null){
			frm.setInvoiceTaxRatio(bean.getNmqty01());
		} else {
			frm.setInvoiceTaxRatio(BigDecimal.ONE);
		}
	}

	/**
	 * 消費税率が有効か確認する
	 * @param date date
	 * @param taxRatio taxRatio
	 * @return true:有効 false:無効
	 */
	public boolean isValidTaxRatio(final String date, final String taxRatio) {
		return getTaxRatioLogic.isValidTax(date, taxRatio);
	}

	// 2014/1/29 新消費税対応 <-
	// 軽減税率対応
	/**
	 * 消費税率マスタコンボボックス取得
	 *
	 * @param frm
	 *            画面フォーム
	 * @param locale
	 *            言語コード
	 * @throws Exception
	 *            NoDataException
	 */
	private void setPurchaseTaxCombobox(BuyingDetailForm frm) throws NoDataException {
		// マスタのデータを取得(すべて)
		ComboboxesBean combBean = buyingDetailLogic.getPurchaseTaxCombobox();

		frm.setTaxLabels(combBean.getLabels());
		frm.setTaxValues(combBean.getValues());
		frm.setTaxKeys(combBean.getInvisibility());
	}

	/* -------------------- setter -------------------- */

	/**
	 * 仕入詳細 ロジッククラスを設定します。
	 * @param buyingDetailLogic 仕入詳細 ロジッククラス
	 */
	public void setBuyingDetailLogic(final BuyingDetailLogic buyingDetailLogic) {
		this.buyingDetailLogic = buyingDetailLogic;
	}

	/**
	 * 発番処理 ロジッククラスを設定します。
	 * @param getNumberLogic 発番処理ロジッククラス
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

	/**
	 * 消費税ロジッククラスを設定します。
	 * @param getTaxRatioLogic getTaxRatioLogic
	 */
	public void setGetTaxRatioLogic(final GetTaxRatioLogic getTaxRatioLogic) {
		this.getTaxRatioLogic = getTaxRatioLogic;
	}

	/**
	 * purchaseSubcontractDaoを設定します。
	 * @param purchaseSubcontractDao purchaseSubcontractDao
	 */
	public void setPurchaseSubcontractDao(
			final PurchaseSubcontractDao purchaseSubcontractDao) {
		this.purchaseSubcontractDao = purchaseSubcontractDao;
	}

	/**
	 * commonLogicを取得します。
	 * @return commonLogic
	 */
	public CommonLogic getCommonLogic() {
		return commonLogic;
	}

	/**
	 * commonLogicを設定します。
	 * @param commonLogic commonLogic
	 */
	public void setCommonLogic(CommonLogic commonLogic) {
		this.commonLogic = commonLogic;
	}

	/**
	 * venderDaoを設定します。
	 * @param venderDao venderDao
	 */
	public void setVenderDao(VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * companyDaoを設定します。
	 * @param companyDao companyDao
	 */
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public void setNamesDetailDao(NamesDetailDao namesDetailDao) {
		this.namesDetailDao = namesDetailDao;
	}
}
