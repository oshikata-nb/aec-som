/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.SelectDirectionListStatus;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 製造指図-タブの抽象親クラス Actionクラス.
 * @author tosco
 */
public abstract class AbstractDirectionAction extends AbstractAction {

	/** 製造指図-共通ロジッククラス */
	protected DirectionCommonsLogic directionCommonsLogic;

	/**
	 * コンストラクタ.
	 */
	public AbstractDirectionAction() {
		super();
	}

	/**
	 * 画面初期表示処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		AbstractDirectionForm commonForm = (AbstractDirectionForm) form;
		//前処理
		beforeInit(mapping, commonForm, request, response);
		//クリアするので、渡された指図番号を退避
		String directionNo = commonForm.getDirectionNo();
		//クリア
		commonForm.clear();
		commonForm.setDirectionNo(directionNo);

		//タブIDを設定
		commonForm.setTabId(getTabId());

		//共通情報検索処理
		DirectionDirectionHeaderList header = directionCommonsLogic.findByDirectionNo(directionNo);
		setCommonHeaderInfo(commonForm, header, request);
		//各子クラスの初期表示処理
		return initProcess(mapping, commonForm, request, response);
	}
	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	protected abstract String getTabId();
	/**
	 * 各子クラスの画面初期表示処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected abstract ActionForward initProcess(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	/**
	 *
	 * 画面表示初期処理が行われる前に,行う処理
	 * 子クラスで必要ならオーバーライドする
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws Exception Exception
	 */
	protected void beforeInit(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("beforeInit.");
		}
		//画面表示初期処理が行われる前に,何か処理を行いたい場合はオーバーライドすること
		//initメソッドでformのクリアを行うので、値を退避するときなどに使用する
	}
	/**
	 * 戻る処理(戻るボタン押下時)
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
	 * 共通ヘッダー情報を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param header 共通ヘッダー情報
	 * @param request request
	 * @return AbstractMgrecipeForm
	 */
	protected AbstractDirectionForm setCommonHeaderInfo(final AbstractDirectionForm form,
			final DirectionDirectionHeaderList header, final HttpServletRequest request) {
		//数値桁数チェック部品取得
		CheckDigitUtilsLogic checker = CheckDigitUtil.getCheckDigitUtils(request);

		form.setDirectionNo(header.getDirectionNo());
		form.setDirectionStatus(getBigDecimalString(header.getDirectionStatus()));
		form.setRecipeId(getBigDecimalString(header.getRecipeId()));
		form.setRecipeCd(header.getRecipeCd());
		form.setRecipeVersion(getBigDecimalString(header.getRecipeVersion()));
		form.setProductionLine(header.getProductionLine());
		form.setCompoundTankNo(header.getCompoundTankNo());
		form.setHoldTankNo(header.getHoldTankNo());
		form.setDissolutionTankNo(header.getDissolutionTankNo());
		form.setItemCd(header.getItemCd());
		//予定生産量|仕込数量設定
		String plandQty = checker.format(DirectionConst.UNIT_DIVISION_HAIGO, header.getPlanedQty());
//		String plandQty = checker.format(header.getUnitOfOperationManagement(), header.getPlanedQty());
		form.setPlanedQty(plandQty);
		form.setPlanedSdate(header.getPlanedSdate());
		form.setPlanedEdate(header.getPlanedEdate());
		//荷主
		int shipperDivision = header.getShipperDivision().intValue();
		form.setShipperDivision(String.valueOf(shipperDivision));
		form.setShipperDivisionLabel(DirectionUtil.getShipperDivisionlabel(shipperDivision));

		form.setOtherCompanyCd1(header.getOtherCompanyCd1());
		form.setProductionLineName(header.getProductionLineName());
		form.setItemName(header.getItemName());
		//洗浄水絶対重量計
		String water = checker.format(DirectionConst.WATER_WEIGHT_UNIT, header.getWaterWeight());
		form.setWaterWeight(water);
		form.setDirectionStatusName(
			SelectDirectionListStatus.getName(getBigDecimalString(header.getDirectionStatus())));
		form.setUnitOfOperationManagement(header.getUnitOfOperationManagement());
		form.setUnitName(header.getUnitName());
		form.setDirectionDivision(getBigDecimalString(header.getDirectionDivision()));

		//更新時の為に、検索時の処方ヘッダー情報を格納しておく
		form.setHeaderBean(header);

		return form;
	}
	/**
	 * 更新用データを作成する
	 * @param commonForm ActionForm
	 * @param request HttpServletRequest
	 * @return DirectionDirectionHeaderList
	 */
	protected DirectionDirectionHeaderList setDirectionHeader(final AbstractDirectionForm commonForm,
			final HttpServletRequest request) {
		DirectionDirectionHeaderList header = new DirectionDirectionHeaderList();
		try {
			//更新用beanに検索時の値をコピー
			IgnoreCaseBeanUtils.copyProperties(header, commonForm.getHeaderBean());
			Timestamp now = AecDateUtils.getCurrentTimestamp();
			header.setDirectionDate(now);
			String tantoCd = getLoginInfo(request).getTantoCd();	//担当者コード
			header.setUpdatorCd(tantoCd);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return header;
	}

//----------------------------------------------------
	/**
	 * エラーメッセージを追加する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param objects 置換パラメータ
	 */
	protected void addError(final HttpServletRequest request, final String key, final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
	}
	/**
	 * メッセージプロパティファイルから指定したkeyに対応する文字列を取得する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @return メッセージキーに対応するメッセージ文字列
	 */
	protected String getMessageResource(final HttpServletRequest request, final String key) {
		 MessageResources resource = getResources(request);
		 return resource.getMessage(key);
	}
	/**
	 * BigDecimalの文字列表現を取得する
	 * BigDecimal=null時はnullを返す
	 * @param dec BigDecimal
	 * @return BigDecimalの文字列表現
	 */
	protected String getBigDecimalString(final BigDecimal dec) {
		return DirectionUtil.getBigDecimalString(dec);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図-共通ロジッククラスを設定します。
	 * @param directionCommonsLogic 製造指図-共通ロジッククラス
	 */
	public void setDirectionCommonsLogic(final DirectionCommonsLogic directionCommonsLogic) {
		this.directionCommonsLogic = directionCommonsLogic;
	}




}
