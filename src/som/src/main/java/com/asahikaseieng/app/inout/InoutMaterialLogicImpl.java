/*
 * Created on 2009/04/06
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.inout;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inoutmaterial.InoutMaterial;
import com.asahikaseieng.dao.nonentity.inoutmaterial.InoutMaterialCondition;
import com.asahikaseieng.dao.nonentity.inoutmaterial.InoutMaterialDao;

/**
 * 原材料別入出庫一覧表 ロジック実装クラス
 * @author t1344224
 */
public class InoutMaterialLogicImpl implements InoutMaterialLogic {

	private InoutMaterialDao inoutMaterialDao;

	/**
	 * コンストラクタ.
	 */
	public InoutMaterialLogicImpl() {
	}

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<InoutRecordList>
	 */
	public List<InoutMaterial> getList(final InoutMaterialCondition condition) {

		List<InoutMaterial> list = inoutMaterialDao.getList(condition);

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * inoutMaterialDaoを設定します。
	 * @param inoutMaterialDao inoutMaterialDao
	 */
	public void setInoutMaterialDao(final InoutMaterialDao inoutMaterialDao) {
		this.inoutMaterialDao = inoutMaterialDao;
	}

}
