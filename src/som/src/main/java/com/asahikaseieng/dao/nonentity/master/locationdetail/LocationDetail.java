/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationdetail;

/**
 * LocationDetailクラス.
 * @author t0011036
 */
public class LocationDetail extends LocationDetailBase {

	private static final long serialVersionUID = 1L;

	/* 可能重量 */
	private String strPossibleWeight;

	/**
	 * コンストラクタ.
	 */
	public LocationDetail() {
		super();
	}

	/**
	 * strPossibleWeightを取得します。
	 * @return strPossibleWeight
	 */
	public String getStrPossibleWeight() {
		return strPossibleWeight;
	}

	/**
	 * strPossibleWeightを設定します。
	 * @param strPossibleWeight strPossibleWeight
	 */
	public void setStrPossibleWeight(final String strPossibleWeight) {
		this.strPossibleWeight = strPossibleWeight;
	}
}
