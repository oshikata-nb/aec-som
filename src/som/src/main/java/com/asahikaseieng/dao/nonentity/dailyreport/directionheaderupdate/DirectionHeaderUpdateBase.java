/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.dailyreport.directionheaderupdate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * DirectionHeaderBaseクラス.
 * @author fml
 */
public class DirectionHeaderUpdateBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DirectionHeaderUpdateBase() {
	}


	//
	// 定数
	//

	/** COLUMNアノテーション directionDivision */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション productionLine */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション resultSdate */
	public static final String resultSdate_COLUMN = "RESULT_SDATE";

	/** COLUMNアノテーション resultEdate */
	public static final String resultEdate_COLUMN = "RESULT_EDATE";

	/** COLUMNアノテーション productionDate */
	public static final String enployJobtime_COLUMN = "ENPLOY_JOBTIME";

	/** COLUMNアノテーション productionDate */
	public static final String cooperJobtime_COLUMN = "COOPER_JOBTIME";

	/** COLUMNアノテーション productionDate */
	public static final String totalJobtime_COLUMN = "TOTAL_JOBTIME";

	//
	// インスタンスフィールド
	//

	private BigDecimal directionDivision;	// 指図区分

	private String directionNo;				// 指図番号

	private String productionLine;			// 製造ライン

	private Timestamp resultSdate;			// 開始実績日時

	private Timestamp resultEdate;			// 終了実績日時

	private BigDecimal enployJobtime;		// 社員作業時間

	private BigDecimal cooperJobtime;		// 協力会社作業時間

	private BigDecimal totalJobtime;		// 総作業時間

	//
	// インスタンスメソッド
	//

	/**
	 * 指図区分の取得.
	 * @return directionDivision BigDecimal
	 */
	public BigDecimal getDirectionDivision() {
		return this.directionDivision;
	}

	/**
	 * 指図区分の設定.
	 * @param directionDivision BigDecimal
	 */
	public void setDirectionDivision(final BigDecimal directionDivision) {
		this.directionDivision = directionDivision;
	}

	/**
	 * 指図番号の取得.
	 * @return directionNo String
	 */
	public String getDirectionNo() {
		return this.directionNo;
	}

	/**
	 * 指図番号の設定.
	 * @param directionNo String
	 */
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
	}

	/**
	 * 製造ラインの取得.
	 * @return productionLine String
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 製造ラインの設定.
	 * @param productionLine String
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 開始実績日時の取得.
	 * @return resultSdate Timestamp
	 */
	public Timestamp getResultSdate() {
		return this.resultSdate;
	}

	/**
	 * 開始実績日時の設定.
	 * @param resultSdate Timestamp
	 */
	public void setResultSdate(final Timestamp resultSdate) {
		this.resultSdate = resultSdate;
	}

	/**
	 * 終了実績日時の取得.
	 * @return resultEdate Timestamp
	 */
	public Timestamp getResultEdate() {
		return this.resultEdate;
	}

	/**
	 * 終了実績日時の設定.
	 * @param resultEdate Timestamp
	 */
	public void setResultEdate(final Timestamp resultEdate) {
		this.resultEdate = resultEdate;
	}

	/**
	 * 社員作業時間の取得.
	 * @return enployJobtime BigDecimal
	 */
	public BigDecimal getEnployJobtime() {
		return this.enployJobtime;
	}

	/**
	 * 社員作業時間の設定.
	 * @param enployJobtime BigDecimal
	 */
	public void setEnployJobtime(final BigDecimal enployJobtime) {
		this.enployJobtime = enployJobtime;
	}

	/**
	 * 協力会社作業時間の取得.
	 * @return cooperJobtime BigDecimal
	 */
	public BigDecimal getCooperJobtime() {
		return this.cooperJobtime;
	}

	/**
	 * 協力会社作業時間の設定.
	 * @param cooperJobtime BigDecimal
	 */
	public void setCooperJobtimee(final BigDecimal cooperJobtime) {
		this.cooperJobtime = cooperJobtime;
	}

	/**
	 * 総作業時間の取得.
	 * @return totalJobtime BigDecimal
	 */
	public BigDecimal getTotalJobtime() {
		return this.totalJobtime;
	}

	/**
	 * 総作業時間の設定.
	 * @param totalJobtime BigDecimal
	 */
	public void setTotalJobtime(final BigDecimal totalJobtime) {
		this.totalJobtime = totalJobtime;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
