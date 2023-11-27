/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemtechlabeldetail;

/**
 * ItemTechLabelDetailクラス.
 * @author t0011036
 */
public class ItemTechLabelDetail extends ItemTechLabelDetailBase {

	private static final long serialVersionUID = 1L;

	private String dispLinkLabelPath;

	private String dispTechLabelPath;

	/**
	 * コンストラクタ.
	 */
	public ItemTechLabelDetail() {
		super();
	}

	/**
	 * dispLinkLabelPathを取得します。
	 * @return dispLinkLabelPath
	 */
	public String getDispLinkLabelPath() {
		return dispLinkLabelPath;
	}

	/**
	 * dispLinkLabelPathを設定します。
	 * @param dispLinkLabelPath dispLinkLabelPath
	 */
	public void setDispLinkLabelPath(final String dispLinkLabelPath) {
		this.dispLinkLabelPath = dispLinkLabelPath;
	}

	/**
	 * dispTechLabelPathを取得します。
	 * @return dispTechLabelPath
	 */
	public String getDispTechLabelPath() {
		return dispTechLabelPath;
	}

	/**
	 * dispTechLabelPathを設定します。
	 * @param dispTechLabelPath dispTechLabelPath
	 */
	public void setDispTechLabelPath(final String dispTechLabelPath) {
		this.dispTechLabelPath = dispTechLabelPath;
	}
}
