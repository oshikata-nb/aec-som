/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.stockinout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.entity.inoutrecord.InoutRecord;
import com.asahikaseieng.dao.entity.inoutrecord.InoutRecordDao;
import com.asahikaseieng.exception.LogicExceptionEx;

/**
 * 在庫・受払ソースを操作するクラス(Direction用) <BR>
 * **LogicImplから呼ぶこと。<BR>
 * コンストラクタでZaiCtrlDao(PLSQL実行DAO)を設定すること。<BR>
 * ＤＢデータを使って、包装・製造指図、指図確定、包装・製造実績、検査待在庫計上、製品検査完了<BR>
 * に関する在庫・受払ソース操作（入力、取消）を行う。
 * @author a7710658
 */
public class InoutRecordUpdate {
	/** DAO宣言 */
	private InoutRecordDao inoutRecordDao;

	private String updatorCd;

	/**
	 * コンストラクタ
	 * @param inoutRecordDao Dao
	 * @param updatorCd user
	 */
	public InoutRecordUpdate(final InoutRecordDao inoutRecordDao,
			final String updatorCd) {
		this.inoutRecordDao = inoutRecordDao;
		this.updatorCd = updatorCd;
	}

	/**
	 * 受払履歴の時刻だけを変更する
	 * @param inoutDivision 受払区分
	 * @param orderNo オーダーＮｏ
	 * @param time 時刻
	 * @return 正常終了 true
	 */
	private boolean updateInoutRecord(final BigDecimal inoutDivision,
			final String orderNo, final Timestamp time) {
		if (orderNo == null || inoutDivision == null || time == null) {
			return true;
		}
		try {
			List<InoutRecord> list = inoutRecordDao.getList(inoutDivision,
				orderNo);
			if (list.isEmpty()) {
				return true;
			}
			for (InoutRecord bean : list) {
				bean.setInoutDate(time);
				bean.setUpdatorCd(this.updatorCd);
				inoutRecordDao.update(bean);
			}
		} catch (LogicExceptionEx e) {
			throw new LogicExceptionEx("errors.direction.stock.update");
		}
		return true;
	}

	/**
	 * 受払履歴の時刻だけを変更する（製品）
	 * @param orderNo オーダーＮｏ
	 * @param time 時刻
	 * @return 正常終了 true
	 */
	public boolean updateInoutRecord4Pro(final String orderNo,
			final Timestamp time) {
		return updateInoutRecord(new BigDecimal(1), orderNo, time);
	}

	/**
	 * 受払履歴の時刻だけを変更する（原材料）
	 * @param orderNo オーダーＮｏ
	 * @param time 時刻
	 * @return 正常終了 true
	 */
	public boolean updateInoutRecord4Mat(final String orderNo,
			final Timestamp time) {
		return updateInoutRecord(new BigDecimal(2), orderNo, time);
	}
}
