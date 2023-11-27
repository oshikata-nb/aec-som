/*
 * Created on Mon Apr 09 13:53:49 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.gadgetconfig;

import java.util.List;

import com.asahikaseieng.dao.entity.gadget.Gadget;
import com.asahikaseieng.dao.entity.gadget.GadgetLink;

/**
 * GadgetConfigクラス.
 * @author jbd
 */
public class GadgetConfig extends GadgetConfigBase {

	private static final long serialVersionUID = 1L;

	/** gadget_RELNO */
	public static final int gadget_RELNO = 0;

	/** gadget_RELKEYS */
	public static final String gadget_RELKEYS
			= "GADGET_ID:GADGET_ID,MENU_ID:MENU_ID,TAB_ID:TAB_ID";

	private Gadget gadget;

	private String auth;

	// ガジェット表示用リンクデータ
	private List<GadgetLink> linkList;

	// ガジェット表示用リンクデータ件数
	private int dataCnt;

	/**
	 * authを取得します。
	 * @return auth
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * authを設定します。
	 * @param auth auth
	 */
	public void setAuth(final String auth) {
		this.auth = auth;
	}

	/**
	 * コンストラクタ.
	 */
	public GadgetConfig() {
		super();
	}

	/**
	 * gadgetを取得します。
	 * @return gadget
	 */
	public Gadget getGadget() {
		return gadget;
	}

	/**
	 * gadgetを設定します。
	 * @param gadget gadget
	 */
	public void setGadget(final Gadget gadget) {
		this.gadget = gadget;
	}

	/**
	 * ガジェットの識別子を取得する
	 * @return String
	 */
	public String getIdentifier() {
		return getTantoCd() + "_" + getGadgetId();
	}

	/**
	 * ガジェット表示用リンクデータを取得します。
	 * @return List<GadgetLink> ガジェット表示用リンクデータ
	 */
	public List<GadgetLink> getLinkList() {
		return linkList;
	}

	/**
	 * ガジェット表示用リンクデータを設定します。
	 * @param linkList ガジェット表示用リンクデータ
	 */
	public void setLinkList(final List<GadgetLink> linkList) {
		this.linkList = linkList;
	}

	/**
	 * ガジェット表示用リンクデータ件数を取得します。
	 * @return int ガジェット表示用リンクデータ件数
	 */
	public int getDataCnt() {
		return dataCnt;
	}

	/**
	 * ガジェット表示用リンクデータ件数を設定します。
	 * @param dataCnt ガジェット表示用リンクデータ件数
	 */
	public void setDataCnt(final int dataCnt) {
		this.dataCnt = dataCnt;
	}
}
