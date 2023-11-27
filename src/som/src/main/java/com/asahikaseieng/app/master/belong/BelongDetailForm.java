/*
 * Created on 2009/03/01
 * AECS佐藤 所属マスタ詳細画面へ受注納入先区分追加 2020/01/21
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belong;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 所属詳細 Formクラス.
 * @author t0011036
 */
public final class BelongDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 部署コード */
	private String organizationCd;

	private String savOrganizationCd;

	/* 部署名称 */
	private String organizationName;

	/* 担当者コード */
	private String tantoCd;

	/* 担当者名 */
	private String tantoNm;

	/* 役職コード */
	private String postId;

	private String savPostId;

	/* 役職名称 */
	private String postName;

	/* 所属区分 */
	private BigDecimal belongKbn;

	private BigDecimal savBelongKbn;
	
	/* 受注納入先区分 */
	private BigDecimal orderDeliveryKbn;
	private BigDecimal savOrderDeliveryKbn;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/**
	 * コンストラクタ.
	 */
	public BelongDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			/* クリア処理 */
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		setOrganizationCd(null);
		setOrganizationName(null);
		setTantoCd(null);
		setTantoNm(null);
		setPostId(null);
		setSavPostId(null);
		setPostName(null);
		setBelongKbn(null);
		setSavBelongKbn(null);
		setOrderDeliveryKbn(null);
		setSavOrderDeliveryKbn(null);
		setDirtyFlg(null);
		setNewFlg(null);
		setSavOrganizationCd(null);
	}

	/**
	 * newFlgを取得します。
	 * @return newFlg
	 */
	public String getNewFlg() {
		return newFlg;
	}

	/**
	 * newFlgを設定します。
	 * @param newFlg newFlg
	 */
	public void setNewFlg(final String newFlg) {
		this.newFlg = newFlg;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * belongKbnを取得します。
	 * @return belongKbn
	 */
	public BigDecimal getBelongKbn() {
		return belongKbn;
	}

	/**
	 * belongKbnを設定します。
	 * @param belongKbn belongKbn
	 */
	public void setBelongKbn(final BigDecimal belongKbn) {
		this.belongKbn = belongKbn;
	}
	
	/**
	 * orderDeliveryKbnを取得します。
	 * @return orderDeliveryKbn
	 */
	public BigDecimal getOrderDeliveryKbn() {
		return orderDeliveryKbn;
	}

	/**
	 * orderDeliveryKbnを設定します。
	 * @param orderDeliveryKbn orderDeliveryKbn
	 */
	public void setOrderDeliveryKbn(final BigDecimal orderDeliveryKbn) {
		this.orderDeliveryKbn = orderDeliveryKbn;
	}

	/**
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * postIdを取得します。
	 * @return postId
	 */
	public String getPostId() {
		return postId;
	}

	/**
	 * postIdを設定します。
	 * @param postId postId
	 */
	public void setPostId(final String postId) {
		this.postId = postId;
	}

	/**
	 * postNameを取得します。
	 * @return postName
	 */
	public String getPostName() {
		return postName;
	}

	/**
	 * postNameを設定します。
	 * @param postName postName
	 */
	public void setPostName(final String postName) {
		this.postName = postName;
	}

	/**
	 * tantoCdを取得します。
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * tantoCdを設定します。
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * tantoNmを取得します。
	 * @return tantoNm
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * tantoNmを設定します。
	 * @param tantoNm tantoNm
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * savPostIdを取得します。
	 * @return savPostId
	 */
	public String getSavPostId() {
		return savPostId;
	}

	/**
	 * savPostIdを設定します。
	 * @param savPostId savPostId
	 */
	public void setSavPostId(final String savPostId) {
		this.savPostId = savPostId;
	}

	/**
	 * savBelongKbnを取得します。
	 * @return savBelongKbn
	 */
	public BigDecimal getSavBelongKbn() {
		return savBelongKbn;
	}

	/**
	 * savBelongKbnを設定します。
	 * @param savBelongKbn savBelongKbn
	 */
	public void setSavBelongKbn(final BigDecimal savBelongKbn) {
		this.savBelongKbn = savBelongKbn;
	}

	/**
	 * savOrganizationCdを取得します。
	 * @return savOrganizationCd
	 */
	public String getSavOrganizationCd() {
		return savOrganizationCd;
	}

	/**
	 * savOrganizationCdを設定します。
	 * @param savOrganizationCd savOrganizationCd
	 */
	public void setSavOrganizationCd(final String savOrganizationCd) {
		this.savOrganizationCd = savOrganizationCd;
	}

	/**
	 * savOrderDeliveryKbnを取得します。
	 * @return savOrderDeliveryKbn
	 */
	public BigDecimal getSavOrderDeliveryKbn() {
		return savOrderDeliveryKbn;
	}

	/**
	 * savOrderDeliveryKbnを設定します。
	 * @param savOrderDeliveryKbn savOrderDeliveryKbn
	 */
	public void setSavOrderDeliveryKbn(BigDecimal savOrderDeliveryKbn) {
		this.savOrderDeliveryKbn = savOrderDeliveryKbn;
	}
}
