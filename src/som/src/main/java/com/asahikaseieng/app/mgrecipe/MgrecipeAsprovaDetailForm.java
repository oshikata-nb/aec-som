/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaList;

/**
 * Asprovaタブ詳細画面 Formクラス.
 * @author tosco
 */
public final class MgrecipeAsprovaDetailForm extends AbstractMgrecipeForm {

	private static final long serialVersionUID = 1L;

	/** 設備グループコード */
	private String resouceGroupCd;

	/** 工程グループコード */
	private String operationGroupCd;

	/** 設備コード */
	private String resouceCd;

	/** 設備名称 */
	private String resouceName;

	/** 優先度 */
	private String recipePriority;

	/** 前段取時間（分） */
	private String maejikan;

	/** 後段取時間（分） */
	private String atojikan;

	/** 工程作業時間1（分） */
	private String processWorkTime1;

	/** 工程作業時間2（分） */
	private String processWorkTime2;

	/** 設備稼働時間1（分） */
	private String machineWorkTime1;

	/** 設備稼働時間2（分） */
	private String machineWorkTime2;

	/** 人作業時間1（分） */
	private String manWorkTime1;

	/** 人作業時間2（分） */
	private String manWorkTime2;

	/** 荷姿 */
	private String styleOfPacking;

	/** 検索結果 */
	private RecipeAsprovaList detailBean;

	/** リンク */
	private String srhLink;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeAsprovaDetailForm() {
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	@Override
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
	}

	/**
	 * 入力データの検証
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	@Override
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("update".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	@Override
	protected void clear() {
		super.clear();
		// 設備グループコード
		setResouceGroupCd(null);
		// 工程グループコード
		setOperationGroupCd(null);
		// 設備コード
		setResouceCd(null);
		// 設備名称
		setResouceName(null);
		// 優先度
		setRecipePriority(null);
		// 前段取時間
		setMaejikan(null);
		// 後段取時間
		setAtojikan(null);
		// 設備稼働時間1
		setMachineWorkTime1(null);
		// 設備稼働時間2
		setMachineWorkTime2(null);
		// 人作業時間1
		setManWorkTime1(null);
		// 人作業時間2
		setManWorkTime2(null);
		// 工程作業時間1
		setProcessWorkTime1(null);
		// 工程作業時間2
		setProcessWorkTime2(null);
		// 荷姿
		setStyleOfPacking(null);
		// 検索結果
		setDetailBean(null);
	}

	// getter,setter

	/**
	 * 設備グループコードを取得します。
	 * @return resouceGroupCd
	 */
	public String getResouceGroupCd() {
		return resouceGroupCd;
	}

	/**
	 * 設備グループコードを設定します。
	 * @param resouceGroupCd 設備グループコード
	 */
	public void setResouceGroupCd(final String resouceGroupCd) {
		this.resouceGroupCd = resouceGroupCd;
	}

	/**
	 * 工程グループコードを取得します。
	 * @return operationGroupCd
	 */
	public String getOperationGroupCd() {
		return operationGroupCd;
	}

	/**
	 * 工程グループコードを設定します。
	 * @param operationGroupCd 工程グループコード
	 */
	public void setOperationGroupCd(final String operationGroupCd) {
		this.operationGroupCd = operationGroupCd;
	}

	/**
	 * 設備コードを取得します。
	 * @return resouceCd
	 */
	public String getResouceCd() {
		return resouceCd;
	}

	/**
	 * 設備コードを設定します。
	 * @param resouceCd 設備コード
	 */
	public void setResouceCd(final String resouceCd) {
		this.resouceCd = resouceCd;
	}

	/**
	 * 設備名称を取得します。
	 * @return resouceName
	 */
	public String getResouceName() {
		return resouceName;
	}

	/**
	 * 設備名称を設定します。
	 * @param resouceName 設備名称
	 */
	public void setResouceName(final String resouceName) {
		this.resouceName = resouceName;
	}

	/**
	 * 優先度を取得します。
	 * @return recipePriority
	 */
	public String getRecipePriority() {
		return recipePriority;
	}

	/**
	 * 優先度を設定します。
	 * @param recipePriority 優先度
	 */
	public void setRecipePriority(final String recipePriority) {
		this.recipePriority = recipePriority;
	}

	/**
	 * 前段取時間（分）を取得します。
	 * @return maejikan
	 */
	public String getMaejikan() {
		return maejikan;
	}

	/**
	 * 前段取時間（分）を設定します。
	 * @param maejikan 前段取時間（分）
	 */
	public void setMaejikan(final String maejikan) {
		this.maejikan = maejikan;
	}

	/**
	 * 後段取時間（分）を取得します。
	 * @return atojikan
	 */
	public String getAtojikan() {
		return atojikan;
	}

	/**
	 * 後段取時間（分）を設定します。
	 * @param atojikan 後段取時間（分）
	 */
	public void setAtojikan(final String atojikan) {
		this.atojikan = atojikan;
	}

	/**
	 * 設備稼働時間1（分）を取得します。
	 * @return machineWorkTime1
	 */
	public String getMachineWorkTime1() {
		return machineWorkTime1;
	}

	/**
	 * 設備稼働時間1（分）を設定します。
	 * @param machineWorkTime1 設備稼働時間1（分）
	 */
	public void setMachineWorkTime1(final String machineWorkTime1) {
		this.machineWorkTime1 = machineWorkTime1;
	}

	/**
	 * 設備稼働時間2（分）を取得します。
	 * @return machineWorkTime2
	 */
	public String getMachineWorkTime2() {
		return machineWorkTime2;
	}

	/**
	 * 設備稼働時間2（分）を設定します。
	 * @param machineWorkTime2 設備稼働時間2（分）
	 */
	public void setMachineWorkTime2(final String machineWorkTime2) {
		this.machineWorkTime2 = machineWorkTime2;
	}

	/**
	 * 人作業時間1（分）を取得します。
	 * @return manWorkTime1
	 */
	public String getManWorkTime1() {
		return manWorkTime1;
	}

	/**
	 * 人作業時間1（分）を設定します。
	 * @param manWorkTime1 人作業時間1（分）
	 */
	public void setManWorkTime1(final String manWorkTime1) {
		this.manWorkTime1 = manWorkTime1;
	}

	/**
	 * 人作業時間2（分）を取得します。
	 * @return manWorkTime2
	 */
	public String getManWorkTime2() {
		return manWorkTime2;
	}

	/**
	 * 人作業時間2（分）を設定します。
	 * @param manWorkTime2 人作業時間2（分）
	 */
	public void setManWorkTime2(final String manWorkTime2) {
		this.manWorkTime2 = manWorkTime2;
	}

	/**
	 * 工程作業時間1（分）を取得します。
	 * @return processWorkTime1
	 */
	public String getProcessWorkTime1() {
		return processWorkTime1;
	}

	/**
	 * 工程作業時間1（分）を設定します。
	 * @param processWorkTime1 工程作業時間1（分）
	 */
	public void setProcessWorkTime1(final String processWorkTime1) {
		this.processWorkTime1 = processWorkTime1;
	}

	/**
	 * 工程作業時間2（分）を取得します。
	 * @return processWorkTime2
	 */
	public String getProcessWorkTime2() {
		return processWorkTime2;
	}

	/**
	 * 工程作業時間2（分）を設定します。
	 * @param processWorkTime2 工程作業時間2（分）
	 */
	public void setProcessWorkTime2(final String processWorkTime2) {
		this.processWorkTime2 = processWorkTime2;
	}

	/**
	 * 荷姿を取得します。
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿を設定します。
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 検索結果を取得します。
	 * @return detailBean
	 */
	public RecipeAsprovaList getDetailBean() {
		return detailBean;
	}

	/**
	 * 検索結果を設定します。
	 * @param detailBean 検索結果
	 */
	public void setDetailBean(final RecipeAsprovaList detailBean) {
		this.detailBean = detailBean;
	}

	/**
	 * srhLinkを取得します。
	 * @return srhLink
	 */
	public String getSrhLink() {
		return srhLink;
	}

	/**
	 * srhLinkを設定します。
	 * @param srhLink srhLink
	 */
	public void setSrhLink(final String srhLink) {
		this.srhLink = srhLink;
	}
}
