/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.artrans;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.credit.artrans.ArTransJournalList;
import com.asahikaseieng.dao.nonentity.credit.artrans.ArTransJournalListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProJournalArCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;

/**
 * 会計送信ロジック 実装クラス.
 * @author t0011036
 */
public class ArTransLogicImpl implements ArTransLogic {

	private ProcedureCallDao procedureCallDao;

	private ArTransJournalListDao arTransJournalListDao;

	/**
	 * コンストラクタ.
	 */
	public ArTransLogicImpl() {
	}

	/**
	 * 実行
	 * @param organizationCd 部署コード
	 * @param inputDate 対象年月
	 * @param tantoCd 担当者コード
	 */
	public void insert(final String organizationCd, final String inputDate,
			final String tantoCd) {
		ProJournalArCallDto dto = new ProJournalArCallDto();

		dto.setPStrOrganizationCd(organizationCd);
		dto.setPStrTargetYears(inputDate);
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proJournalAr(dto);

			if (dto.getPStrErrorReturnCd() != null) {
				throw new LogicExceptionEx(dto.getPStrErrorReturnMsg());
			}
		} catch (LogicExceptionEx e) {
			throw new LogicExceptionEx(dto.getPStrErrorReturnMsg());
		}
	}

	/**
	 * CSVデータ検索
	 * @param inputDate 対象年月
	 * @throws NoDataException NoDataException
	 * @return List<ArTransJournalList>
	 */
	public List<ArTransJournalList> getList(final String inputDate)
			throws NoDataException {
		List<ArTransJournalList> list = arTransJournalListDao
				.getList(inputDate);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * CSVデータ作成
	 * @param list List<ArTransJournalList>
	 * @return List<String>
	 */
	public List<String> getData(final List<ArTransJournalList> list) {
		List<String> csvDataList = new ArrayList<String>();
		String rcString = null;

		for (ArTransJournalList bean : list) {
			rcString = addComma(bean.getDenYmd()); /* 伝票日付 */
			rcString += addComma(bean.getDenNo()); /* 伝票NO */
			rcString += addComma(bean.getGyoNo()); /* 行NO */
			rcString += addComma(bean.getDenKbn()); /* 伝票区分 */
			rcString += addComma(bean.getKrBnaibuNo()); /* 借方部門コード */
			rcString += addComma(bean.getKrKnaibuNo()); /* 借方科目コード */
			rcString += addComma(bean.getKrToriCd()); /* 借方取引先コード */
			rcString += addComma(bean.getKrTekimei()); /* 借方摘要名 */
			rcString += addComma(bean.getKrProjCd()); /* 借方ﾌﾟﾛｼﾞｪｸﾄｺｰﾄﾞ */
			rcString += addComma(bean.getKrKingaku()); /* 借方金額 */
			rcString += addComma(bean.getKrZei()); /* 借方消費税額 */
			rcString += addComma(bean.getKrKazei()); /* 借方課税区分 */
			rcString += addComma(bean.getKrZeiKbn()); /* 借方税処理区分 */
			rcString += addComma(bean.getKrToritYmd()); /* 借方取引日付 */
			rcString += addComma(bean.getKrGaikim()); /* 借方外貨金額 */
			rcString += addComma(bean.getKrGairate()); /* 借方外貨レート */
			rcString += addComma(bean.getKrGaiKbn()); /* 借方外貨区分 */
			rcString += addComma(bean.getKrBibou1()); /* 借方備忘１ */
			rcString += addComma(bean.getKrBibou2()); /* 借方備忘２ */
			rcString += addComma(bean.getKrBibou2()); /* 借方備忘３ */
			rcString += addComma(bean.getKrFusen()); /* 借方付箋区分 */
			rcString += addComma(bean.getKrMemo()); /* 借方付箋メモ */
			rcString += addComma(bean.getKrBiko1()); /* 借方備考１ */
			rcString += addComma(bean.getKrBiko2()); /* 借方備考２ */
			rcString += addComma(bean.getKrBiko3()); /* 借方備考３ */
			rcString += addComma(bean.getKrBiko4()); /* 借方備考４ */
			rcString += addComma(bean.getKrTegata()); /* 借方手形シリアル */
			rcString += addComma(bean.getKrSkazei()); /* 借方消課税区分 */
			rcString += addComma(bean.getKrSknnaibuNo()); /* 借方消科目ｺｰﾄﾞ */
			rcString += addComma(bean.getKsBnaibuNo()); /* 貸方部門コード */
			rcString += addComma(bean.getKsKnaibuNo()); /* 貸方科目コード */
			rcString += addComma(bean.getKsToriCd()); /* 貸方取引先コード */
			rcString += addComma(bean.getKsTekimei()); /* 貸方摘要名 */
			rcString += addComma(bean.getKsProjCd()); /* 貸方ﾌﾟﾛｼﾞｪｸﾄｺｰﾄﾞ */
			rcString += addComma(bean.getKsKingaku()); /* 貸方金額 */
			rcString += addComma(bean.getKsZei()); /* 貸方消費税額 */
			rcString += addComma(bean.getKsKazei()); /* 貸方課税区分 */
			rcString += addComma(bean.getKsZeiKbn()); /* 貸方税処理区分 */
			rcString += addComma(bean.getKsToritYmd()); /* 貸方取引日付 */
			rcString += addComma(bean.getKsGaikim()); /* 貸方外貨金額 */
			rcString += addComma(bean.getKsGaiRate()); /* 貸方外貨レート */
			rcString += addComma(bean.getKsGaiKbn()); /* 貸方外貨区分 */
			rcString += addComma(bean.getKsBibou1()); /* 貸方備忘１ */
			rcString += addComma(bean.getKsBibou2()); /* 貸方備忘２ */
			rcString += addComma(bean.getKsBibou3()); /* 貸方備忘３ */
			rcString += addComma(bean.getKsFusen()); /* 貸方付箋区分 */
			rcString += addComma(bean.getKsMemo()); /* 貸方付箋メモ */
			rcString += addComma(bean.getKsBiko1()); /* 貸方備考１ */
			rcString += addComma(bean.getKsBiko2()); /* 貸方備考２ */
			rcString += addComma(bean.getKsBiko3()); /* 貸方備考３ */
			rcString += addComma(bean.getKsBiko4()); /* 貸方備考４ */
			rcString += addComma(bean.getKsTegata()); /* 貸方手形シリアル */
			rcString += addComma(bean.getKsSkazei()); /* 貸方消課税区分 */
			rcString += addComma(bean.getKsSknnaibuNo()); /* 貸方消科目コード */

			rcString += addComma(""); /* 借方手形番号 */
			rcString += addComma(""); /* 借方手形種別 */
			rcString += addComma(""); /* 借方振出日 */
			rcString += addComma(""); /* 借方満期日 */
			rcString += addComma(""); /* 借方決済予定日 */

			rcString += addComma(""); /* 貸方手形番号 */
			rcString += addComma(""); /* 貸方手形種別 */
			rcString += addComma(""); /* 貸方振出日 */
			rcString += addComma(""); /* 貸方満期日 */
			rcString += trimNull(""); /* 貸方決済予定日 */

			csvDataList.add(rcString);
		}

		return csvDataList;
	}

	/**
	 * カンマ追加(文字)
	 * @param data 元データ
	 * @return String
	 */
	public String addComma(final String data) {
		String s = ",";

		if (!StringUtils.isEmpty(data)) {
			s = data + s;
		}

		return s;
	}

	/**
	 * カンマ追加(数値)
	 * @param data 元データ
	 * @return String
	 */
	public String addComma(final BigDecimal data) {
		String s = ",";

		if (data != null) {
			s = data.toString() + s;
		}

		return s;
	}

	/**
	 * カンマ追加(日付)
	 * @param data 元データ
	 * @return String
	 */
	public String addComma(final Timestamp data) {
		String s = ",";

		if (data != null) {
			s = data.toString() + s;
		}

		return s;
	}

	/**
	 * nullチェック
	 * @param data 元データ
	 * @return String
	 */
	public String trimNull(final String data) {
		String s = "";

		if (!StringUtils.isEmpty(data)) {
			s = data;
		}

		return s;
	}

	/* -------------------- setter -------------------- */

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * arTransJournalListDaoを設定します。
	 * @param arTransJournalListDao arTransJournalListDao
	 */
	public void setArTransJournalListDao(
			final ArTransJournalListDao arTransJournalListDao) {
		this.arTransJournalListDao = arTransJournalListDao;
	}
}
