/*
 * Created on 2009/01/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.stockinout;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.InoutDto;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.StrCodeDto;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.ZaikoCtrlExceptionEx;

/**
 * 在庫・受払リソースを操作するクラス <BR>
 * **LogicImplから呼ぶこと。 コンストラクタでZaiCtrlDaoを設定すること。 <BR>
 * PLSQL を実行します。
 * @author a7710658
 */
public class Stockinout {
	/** 在庫更新PROCEDURE DAO宣言 */
	private ZaiCtrlDao zaiCtrlDao;

	/** Log */
	private static Log log = LogFactory.getLog(Stockinout.class);

	/**
	 * コンストラクタ
	 * @param zaiCtrlDao PLSQL実行 Daoは必須です。
	 */
	public Stockinout(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * 棚卸調整入力 棚差を在庫に反映します。<BR>
	 * 数量が＋なら在庫増（LngOpno=13）<BR>
	 * 数量が－なら在庫減（LngOpno=14）します。<BR>
	 * 理由は「棚卸調整」です。処理区分(PLngFuncdivision)=8
	 * @param itemCd 品目コード
	 * @param qty 数量（＋在庫増PLngOpno=13、－在庫減PLngOpno=14）
	 * @param inoutDate 入出庫時刻
	 * @param locationCd ロケーションコード
	 * @param lotNo ロット番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean adjustInventory(final String itemCd, final BigDecimal qty,
			final Timestamp inoutDate, final String locationCd,
			final String lotNo, final String loginUser) {
		if (qty == null || inoutDate == null) {
			return false;
		}
		InoutDto dto = new InoutDto();
		if (qty.compareTo(new BigDecimal(0)) < 0) {
			dto.setLngPno(StockinoutConstant.PNO_TANA_OUT);
			dto.setDbQty(qty.negate());
			dto.setLngInout(StockinoutConstant.OUT_DIV);
		} else {
			dto.setLngPno(StockinoutConstant.PNO_TANA_IN);
			dto.setDbQty(qty);
			dto.setLngInout(StockinoutConstant.IN_DIV);
		}
		dto.setStrItemCd(itemCd);
		dto.setDtInoutDate(inoutDate);
		dto.setStrLocationCd(locationCd);
		dto.setStrLotNo(lotNo);
		dto.setStrReason("棚卸調整");
		dto.setStrRemark(null);
		dto.setLngFunc(StockinoutConstant.FUNC_TANASA);
		dto.setStrLoginUser(loginUser);
		dto.setStrReasonCd("999");
		this.zaiCtrlDao.entryInout(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara() + ":adjustInventory()"
					+ loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("adjustInventory()");
			throw e;
		}
		return true;
	}

	/**
	 * 棚卸調整取消 棚差を在庫に反映します。<BR>
	 * 数量が＋なら在庫減（LngOpno=13）<BR>
	 * 数量が－なら在庫増（LngOpno=14）します。<BR>
	 * 理由は「棚卸調整」です。処理区分(PLngFuncdivision)=88
	 * @param itemCd 品目コード
	 * @param qty 数量（＋在庫減PLngOpno=13、－在庫増PLngOpno=14）
	 * @param inoutDate 入出庫時刻
	 * @param locationCd ロケーションコード
	 * @param lotNo ロット番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deAdjustInventory(final String itemCd, final BigDecimal qty,
			final Timestamp inoutDate, final String locationCd,
			final String lotNo, final String loginUser) {
		if (qty == null || inoutDate == null) {
			return false;
		}
		InoutDto dto = new InoutDto();
		if (qty.compareTo(new BigDecimal(0)) < 0) {
			dto.setLngPno(StockinoutConstant.PNO_TANA_OUT);
			dto.setLngInout(StockinoutConstant.OUT_DIV);
			dto.setDbQty(qty);
		} else {
			dto.setLngPno(StockinoutConstant.PNO_TANA_IN);
			dto.setLngInout(StockinoutConstant.IN_DIV);
			dto.setDbQty(qty.negate());
		}
		dto.setStrItemCd(itemCd);
		dto.setDtInoutDate(inoutDate);
		dto.setStrLocationCd(locationCd);
		dto.setStrLotNo(lotNo);
		dto.setStrReason("棚卸調整");
		dto.setStrRemark(null);
		dto.setLngFunc(StockinoutConstant.FUNC_TANASA_C);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryInout(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara() + ":deAdjustInventory()"
					+ loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("deAdjustInventory()");
			throw e;
		}
		return true;
	}

	/**
	 * 例外入庫処理 <BR>
	 * 在庫を増加します。<BR>
	 * 処理区分(PLngFuncdivision)=3,PLngOpno=9
	 * @param itemCd 品目コード
	 * @param qty 数量
	 * @param inoutDate 入出庫時刻
	 * @param locationCd ロケーションコード
	 * @param lotNo 入荷ロット番号/包装指図番号
	 * @param remark 摘要
	 * @param reason 理由
	 * @param reasonCd 理由コード
	 * @param aliasLotNo 原料ロット番号/製品ロット番号
	 * @param cost 単価
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean stock(final String itemCd, final BigDecimal qty,
			final Timestamp inoutDate, final String locationCd,
			final String lotNo, final String remark, final String reason,
			final String reasonCd, final String aliasLotNo,
			final BigDecimal cost, final String loginUser) {
		if (qty == null || inoutDate == null) {
			return false;
		}
		InoutDto dto = new InoutDto();
		dto.setLngPno(StockinoutConstant.PNO_INVENTRY_IN);
		dto.setDbQty(qty);
		dto.setStrItemCd(itemCd);
		dto.setDtInoutDate(inoutDate);
		dto.setStrLocationCd(locationCd);
		dto.setStrLotNo(lotNo);
		dto.setStrReason(reason);
		if (reason == null || reason.equals("")) {
			dto.setStrReason("例外入庫");
		}
		dto.setStrReasonCd(reasonCd);
		dto.setStrRemark(remark);
		dto.setLngFunc(StockinoutConstant.FUNC_REIGAI);
		dto.setStrLoginUser(loginUser);
		dto.setLngInout(StockinoutConstant.IN_DIV);
		dto.setStrAlot(aliasLotNo);
		dto.setLngCost(cost);
		this.zaiCtrlDao.entryInout(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara() + ":stock()" + loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("stock()");
			throw e;
		}
		return true;
	}

	/**
	 * 例外入庫取消 <BR>
	 * 在庫を減少します。<BR>
	 * 処理区分(PLngFuncdivision)=83,PLngOpno=9
	 * @param itemCd 品目コード
	 * @param qty 数量
	 * @param inoutDate 入出庫時刻
	 * @param locationCd ロケーションコード
	 * @param lotNo 入荷ロット番号/包装指図番号
	 * @param remark 摘要
	 * @param reason 理由
	 * @param reasonCd 理由コード
	 * @param aliasLotNo 原料ロット番号/製品ロット番号
	 * @param cost 単価
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deStock(final String itemCd, final BigDecimal qty,
			final Timestamp inoutDate, final String locationCd,
			final String lotNo, final String remark, final String reason,
			final String reasonCd, final String aliasLotNo,
			final BigDecimal cost, final String loginUser) {
		if (qty == null || inoutDate == null) {
			return false;
		}
		InoutDto dto = new InoutDto();
		dto.setLngPno(StockinoutConstant.PNO_INVENTRY_IN);
		dto.setDbQty(qty.negate());
		dto.setStrItemCd(itemCd);
		dto.setDtInoutDate(inoutDate);
		dto.setStrLocationCd(locationCd);
		dto.setStrLotNo(lotNo);
		dto.setStrReason(reason);
		if (reason == null || reason.equals("")) {
			dto.setStrReason("例外入庫");
		}
		dto.setStrReasonCd(reasonCd);
		dto.setStrRemark(remark);
		dto.setLngFunc(StockinoutConstant.FUNC_REIGAI_C);
		dto.setLngInout(StockinoutConstant.IN_DIV);
		dto.setStrAlot(aliasLotNo);
		dto.setLngCost(cost);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryInout(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara() + ":deStock()" + loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("deStock()");
			throw e;
		}
		return true;
	}

	/**
	 * 例外出庫処理<BR>
	 * 在庫を減少します。<BR>
	 * 処理区分(PLngFuncdivision)=3,PLngOpno=10
	 * @param itemCd 品目コード
	 * @param qty 数量
	 * @param inoutDate 入出庫時刻
	 * @param locationCd ロケーションコード
	 * @param lotNo ロット番号
	 * @param remark 摘要
	 * @param reason 理由
	 * @param reasonCd 理由コード
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean delivery(final String itemCd, final BigDecimal qty,
			final Timestamp inoutDate, final String locationCd,
			final String lotNo, final String remark, final String reason,
			final String reasonCd, final String loginUser) {
		if (qty == null || inoutDate == null) {
			return false;
		}
		InoutDto dto = new InoutDto();
		dto.setLngPno(StockinoutConstant.PNO_INVENTRY_OUT);
		dto.setDbQty(qty);
		dto.setStrItemCd(itemCd);
		dto.setDtInoutDate(inoutDate);
		dto.setStrLocationCd(locationCd);
		dto.setStrLotNo(lotNo);
		dto.setStrReason(reason);
		if (reason == null || reason.equals("")) {
			dto.setStrReason("例外出庫");
		}
		dto.setStrReasonCd(reasonCd);
		dto.setStrRemark(remark);
		dto.setLngFunc(StockinoutConstant.FUNC_REIGAI);
		dto.setLngInout(StockinoutConstant.OUT_DIV);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryInout(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara() + ":delivery()" + loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("delivery()");
			throw e;
		}
		return true;
	}

	/**
	 * 例外出庫取消<BR>
	 * 在庫を増加します。<BR>
	 * 処理区分(PLngFuncdivision)=83,PLngOpno=10
	 * @param itemCd 品目コード
	 * @param qty 数量
	 * @param inoutDate 入出庫時刻
	 * @param locationCd ロケーションコード
	 * @param lotNo ロット番号
	 * @param remark 摘要
	 * @param reason 理由
	 * @param reasonCd 理由コード
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deDelivery(final String itemCd, final BigDecimal qty,
			final Timestamp inoutDate, final String locationCd,
			final String lotNo, final String remark, final String reason,
			final String reasonCd, final String loginUser) {
		if (qty == null || inoutDate == null) {
			return false;
		}
		InoutDto dto = new InoutDto();
		dto.setLngPno(StockinoutConstant.PNO_INVENTRY_OUT);
		dto.setDbQty(qty.negate());
		dto.setStrItemCd(itemCd);
		dto.setDtInoutDate(inoutDate);
		dto.setStrLocationCd(locationCd);
		dto.setStrLotNo(lotNo);
		dto.setStrReason(reason);
		if (reason == null || reason.equals("")) {
			dto.setStrReason("例外出庫");
		}
		dto.setStrReasonCd(reasonCd);
		dto.setStrRemark(remark);
		dto.setLngFunc(StockinoutConstant.FUNC_REIGAI_C);
		dto.setLngInout(StockinoutConstant.OUT_DIV);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryInout(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara() + ":delivery()" + loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("deDelivery()");
			throw e;
		}
		return true;
	}

	/**
	 * 返品入庫<BR>
	 * 在庫を増加します。<BR>
	 * 処理区分(PLngFuncdivision)=52,PLngOpno=16
	 * @param slipNo 売上番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean returnDelivery(final String slipNo, final String loginUser) {
		StrCodeDto dto = new StrCodeDto();
		dto.setLngFlg(StockinoutConstant.BD_NYURYOKU_FLG);
		dto.setStrCode(slipNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.returnDelivery(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara() + ":returnDelivery(" + slipNo
					+ "," + loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("returnDelivery()");
			throw e;
		}
		return true;
	}

	/**
	 * 返品入庫取消<BR>
	 * 在庫を減少します。<BR>
	 * 処理区分(PLngFuncdivision)=92,PLngOpno=16
	 * @param slipNo 売上番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deReturnDelivery(final String slipNo, final String loginUser) {
		StrCodeDto dto = new StrCodeDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_NYURYOKU_FLG);
		dto.setStrCode(slipNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.returnDelivery(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara() + ":deReturnDelivery(" + slipNo
					+ "," + loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("deReturnDelivery()");
			throw e;
		}
		return true;
	}

	/**
	 * 預かり品売上計上<BR>
	 * 移動前ロケーションからの出庫、<BR>
	 * 移動後ロケーションへの入庫を行います。<BR>
	 * 処理区分(PLngFuncdivision)=2,PLngOpno=17
	 * @param itemCd 品目コード
	 * @param qty 数量
	 * @param inoutDate 入出庫時刻
	 * @param beforeLocationCd 移動前ロケーションコード
	 * @param afterLocationCd 移動後ロケーションコード
	 * @param lotNo ロット番号
	 * @param salesNo 売上番号
	 * @param remark 摘要
	 * @param reason 理由
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean salesWithoutDelivery(final String itemCd,
			final BigDecimal qty, final Timestamp inoutDate,
			final String beforeLocationCd, final String afterLocationCd,
			final String lotNo, final String salesNo, final String remark,
			final String reason, final String loginUser) {
		if (qty == null || inoutDate == null) {
			return false;
		}
		InoutDto dto = new InoutDto();
		dto.setLngPno(StockinoutConstant.PNO_AZUKARI);
		dto.setDbQty(qty);
		dto.setStrItemCd(itemCd);
		dto.setDtInoutDate(inoutDate);
		dto.setStrLocationCd(beforeLocationCd);
		dto.setStrLotNo(lotNo);
		dto.setStrReason(reason);
		dto.setStrAlot(salesNo);

		if (reason == null || reason.equals("")) {
			dto.setStrReason("預品売上");
		}
		dto.setStrRemark(afterLocationCd);
		dto.setLngFunc(StockinoutConstant.FUNC_URI);
		dto.setLngInout(StockinoutConstant.OUT_DIV);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entrySales(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara() + ":salesWithoutDelivery"
					+ loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("salesWithoutDelivery()");
			throw e;
		}
		return true;
	}

	/**
	 * 預かり品売上計上取消<BR>
	 * 移動前ロケーションからの入庫、<BR>
	 * 移動後ロケーションへの出庫を行います。<BR>
	 * 処理区分(PLngFuncdivision)=82,PLngOpno=17
	 * @param itemCd 品目コード
	 * @param qty 数量
	 * @param inoutDate 入出庫時刻
	 * @param beforeLocationCd 移動前ロケーションコード
	 * @param afterLocationCd 移動後ロケーションコード
	 * @param lotNo ロット番号
	 * @param salesNo 売上番号
	 * @param remark 摘要
	 * @param reason 理由
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deSalesWithoutDelivery(final String itemCd,
			final BigDecimal qty, final Timestamp inoutDate,
			final String beforeLocationCd, final String afterLocationCd,
			final String lotNo, final String salesNo, final String remark,
			final String reason, final String loginUser) {
		if (qty == null || inoutDate == null) {
			return false;
		}
		InoutDto dto = new InoutDto();
		dto.setLngPno(StockinoutConstant.PNO_AZUKARI);
		dto.setDbQty(qty.negate());
		dto.setStrItemCd(itemCd);
		dto.setDtInoutDate(inoutDate);
		dto.setStrLocationCd(afterLocationCd);
		dto.setStrLotNo(lotNo);
		dto.setStrReason(reason);
		dto.setStrAlot(salesNo);
		if (reason == null || reason.equals("")) {
			dto.setStrReason("預品売上");
		}
		dto.setStrRemark(beforeLocationCd);
		dto.setLngFunc(StockinoutConstant.FUNC_URI_C);
		dto.setLngInout(StockinoutConstant.IN_DIV);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entrySales(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara() + ":deSalesWithoutDelivery "
					+ loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("deSalesWithoutDelivery()");
			throw e;
		}
		return true;
	}

}
