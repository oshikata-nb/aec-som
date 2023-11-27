/*
 * Created on 2009/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquirypreparationlist;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * InquiryPreparationListクラス.
 * @author t0011036
 */
public class InquiryPreparationList extends InquiryPreparationListBase
		implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	private String nameCd;

	private String name01;

	private Boolean checked;

	/**
	 * コンストラクタ.
	 */
	public InquiryPreparationList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* チェックをクリア */
		setChecked(Boolean.FALSE);
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/**
	 * checkedを取得します。
	 * @return checked
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * checkedを設定します。
	 * @param checked checked
	 */
	public void setChecked(final Boolean checked) {
		this.checked = checked;
	}

	/**
	 * name01を取得します。
	 * @return name01
	 */
	public String getName01() {
		return name01;
	}

	/**
	 * name01を設定します。
	 * @param name01 name01
	 */
	public void setName01(final String name01) {
		this.name01 = name01;
	}

	/**
	 * nameCdを取得します。
	 * @return nameCd
	 */
	public String getNameCd() {
		return nameCd;
	}

	/**
	 * nameCdを設定します。
	 * @param nameCd nameCd
	 */
	public void setNameCd(final String nameCd) {
		this.nameCd = nameCd;
	}
}
