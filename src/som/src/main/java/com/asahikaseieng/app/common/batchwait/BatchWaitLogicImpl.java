/*
 * Created on 2014/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.batchwait;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.entity.master.names.Names;
import com.asahikaseieng.dao.entity.master.names.NamesDao;
import com.asahikaseieng.dao.entity.procparam.ProcParam;
import com.asahikaseieng.dao.entity.procparam.ProcParamDao;
import com.asahikaseieng.dao.nonentity.common.batchwait.BatchWaitNamesArray;
import com.asahikaseieng.dao.nonentity.common.batchwait.BatchWaitNamesArrayDao;

/**
 * 
 * バッチ待ちLogicImpl
 * @author atts
 */
public class BatchWaitLogicImpl implements BatchWaitLogic {

	/** NamesDao */
	private NamesDao namesDao;

	/** ProcParamDao */
	private ProcParamDao procParamDao;

	/** batchWaitNamesArrayDao */
	private BatchWaitNamesArrayDao batchWaitNamesArrayDao;

	/**
	 * コンストラクタ.バッチ待ち
	 */
	public BatchWaitLogicImpl() {
	}

	/**
	 * 各種名称マスタを取得する。
	 * @param srhNameCd 名称コード
	 * @return Names 検索結果データ
	 */
	public Names getNames(final String srhNameCd) {
		if (StringUtils.isEmpty(srhNameCd)) {
			throw new IllegalArgumentException();
		}

		// 各種名称マスタを検索
		Names nameBean = namesDao.getEntity(srhNameCd, "PROC");

		return nameBean;
	}

	/**
	 * 各種名称リストを取得する。
	 * @param menuId メニューID
	 * @param num 枝番
	 * @param nameDiv 名称区分
	 * @return Names 検索結果データリスト
	 */
	public List<BatchWaitNamesArray> getBatchNames(final String menuId,
			final String num, final String nameDiv) {
		// 各種名称マスタを検索
		List<BatchWaitNamesArray> nameBean = batchWaitNamesArrayDao
				.getEntityList(menuId, num, nameDiv);

		return nameBean;
	}

	/**
	 * バッチパラメータ情報を取得する。
	 * @param srhProcCd プロシージャ名
	 * @return ProcParam 検索結果データ
	 */
	public ProcParam getProcParam(final String srhProcCd) {
		if (StringUtils.isEmpty(srhProcCd)) {
			throw new IllegalArgumentException();
		}

		// バッチテーブルを検索
		ProcParam procParamBean = procParamDao.getEntity(srhProcCd);

		return procParamBean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * NamesDaoを設定します。
	 * @param namesDao NamesDao
	 */
	public void setNamesDao(final NamesDao namesDao) {
		this.namesDao = namesDao;
	}

	/**
	 * ProcParamDaoを設定します。
	 * @param procParamDao ProcParamDao
	 */
	public void setProcParamDao(final ProcParamDao procParamDao) {
		this.procParamDao = procParamDao;
	}

	/**
	 * batchWaitNamesArrayDaoを設定します。
	 * @param batchWaitNamesArrayDao batchWaitNamesArrayDao
	 */
	public void setBatchWaitNamesArrayDao(
			final BatchWaitNamesArrayDao batchWaitNamesArrayDao) {
		this.batchWaitNamesArrayDao = batchWaitNamesArrayDao;
	}
}
