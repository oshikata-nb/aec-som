/*
 * Created on 2007/03/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.gadgetconfig.GadgetConfig;
import com.asahikaseieng.struts.AbstractForm;

/**
 * マイページ Formクラス.
 * @author jbd
 */
public final class MyPageForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	private List<GadgetConfig> gadgetList;

	private String leftIds;

	private String rightIds;

	private String leftStatus;

	private String rightStatus;

	/**
	 * コンストラクタ.
	 */
	public MyPageForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
	}

	/**
	 * 値をクリアする.
	 */
	private void clear() {
		setGadgetList(null);
		setLeftIds("");
		setRightIds("");
		setLeftStatus("");
		setRightStatus("");
	}

	/**
	 * gadgetListを取得します。
	 * @return gadgetList
	 */
	public List<GadgetConfig> getGadgetList() {
		return gadgetList;
	}

	/**
	 * gadgetListを設定します。
	 * @param gadgetList gadgetList
	 */
	public void setGadgetList(final List<GadgetConfig> gadgetList) {
		this.gadgetList = gadgetList;
	}

	/**
	 * lIdsを取得します。
	 * @return leftIds
	 */
	public String getLeftIds() {
		return leftIds;
	}

	/**
	 * leftIdsを設定します。
	 * @param leftIds leftIds
	 */
	public void setLeftIds(final String leftIds) {
		this.leftIds = leftIds;
	}

	/**
	 * rightIdsを取得します。
	 * @return rightIds
	 */
	public String getRightIds() {
		return rightIds;
	}

	/**
	 * rightIdsを設定します。
	 * @param rightIds rightIds
	 */
	public void setRightIds(final String rightIds) {
		this.rightIds = rightIds;
	}

	/**
	 * leftStatusを取得します。
	 * @return leftStatus
	 */
	public String getLeftStatus() {
		return leftStatus;
	}

	/**
	 * leftStatusを設定します。
	 * @param leftStatus leftStatus
	 */
	public void setLeftStatus(final String leftStatus) {
		this.leftStatus = leftStatus;
	}

	/**
	 * rightStatusを取得します。
	 * @return rightStatus
	 */
	public String getRightStatus() {
		return rightStatus;
	}

	/**
	 * rightStatusを設定します。
	 * @param rightStatus rightStatus
	 */
	public void setRightStatus(final String rightStatus) {
		this.rightStatus = rightStatus;
	}

	/**
	 * １番目のレーンのGadgetConfigリストを取得する仮想getter.
	 * @return List<GadgetConfig>
	 */
	public List<GadgetConfig> getFirstLaneList() {

		List<GadgetConfig> list = new ArrayList<GadgetConfig>();
		for (GadgetConfig bean : this.getGadgetList()) {
			if (Constants.FIRST_LANE.equals(bean.getLaneNo())) {
				list.add(bean);
			}
		}
		return list;
	}

	/**
	 * ２番目のレーンのGadgetConfigリストを取得する仮想getter.
	 * @return List<GadgetConfig>
	 */
	public List<GadgetConfig> getSecondLaneList() {

		List<GadgetConfig> list = new ArrayList<GadgetConfig>();
		for (GadgetConfig bean : this.getGadgetList()) {
			if (Constants.SECOND_LANE.equals(bean.getLaneNo())) {
				list.add(bean);
			}
		}
		return list;
	}
}
