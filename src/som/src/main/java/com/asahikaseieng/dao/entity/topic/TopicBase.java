/*
 * Created on Wed Nov 26 20:05:51 JST 2008
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.topic;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * TopicBaseクラス.
 * @author a1020630
 */
public class TopicBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TopicBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "TOPIC";


//	/** IDアノテーション topicId. */
//	public static final String topicId_ID = "assigned";

	/** COLUMNアノテーション topicId. */
	public static final String topicId_COLUMN = "TOPIC_ID";

	/** COLUMNアノテーション topicTitle. */
	public static final String topicTitle_COLUMN = "TOPIC_TITLE";

	/** COLUMNアノテーション topicContent. */
	public static final String topicContent_COLUMN = "TOPIC_CONTENT";

	/** COLUMNアノテーション topicContentRet. */
	public static final String topicContentRet_COLUMN = "TOPIC_CONTENT_RET";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション topicInputor. */
	public static final String topicInputor_COLUMN = "TOPIC_INPUTOR";

	/** COLUMNアノテーション topicRetInputor. */
	public static final String topicRetInputor_COLUMN = "TOPIC_RET_INPUTOR";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal topicId;

	private String topicTitle;

	private String topicContent;

	private String topicContentRet;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private String topicInputor;

	private String topicRetInputor;

	//
	// インスタンスメソッド
	//

	/**
	 * topicId取得.
	 * @return topicId
	 */
	public java.math.BigDecimal getTopicId() {
		return this.topicId;
	}

	/**
	 * topicId設定.
	 * @param topicId topicId
	 */
	public void setTopicId(final java.math.BigDecimal topicId) {
		this.topicId = topicId;
	}

	/**
	 * topicTitle取得.
	 * @return topicTitle
	 */
	public String getTopicTitle() {
		return this.topicTitle;
	}

	/**
	 * topicTitle設定.
	 * @param topicTitle topicTitle
	 */
	public void setTopicTitle(final String topicTitle) {
		this.topicTitle = topicTitle;
	}

	/**
	 * topicContent取得.
	 * @return topicContent
	 */
	public String getTopicContent() {
		return this.topicContent;
	}

	/**
	 * topicContent設定.
	 * @param topicContent topicContent
	 */
	public void setTopicContent(final String topicContent) {
		this.topicContent = topicContent;
	}

	/**
	 * topicContentRet取得.
	 * @return topicContentRet
	 */
	public String getTopicContentRet() {
		return this.topicContentRet;
	}

	/**
	 * topicContentRet設定.
	 * @param topicContentRet topicContentRet
	 */
	public void setTopicContentRet(final String topicContentRet) {
		this.topicContentRet = topicContentRet;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * topicInputor取得.
	 * @return topicInputor
	 */
	public String getTopicInputor() {
		return this.topicInputor;
	}

	/**
	 * topicInputor設定.
	 * @param topicInputor topicInputor
	 */
	public void setTopicInputor(final String topicInputor) {
		this.topicInputor = topicInputor;
	}

	/**
	 * topicRetInputor取得.
	 * @return topicRetInputor
	 */
	public String getTopicRetInputor() {
		return this.topicRetInputor;
	}

	/**
	 * topicRetInputor設定.
	 * @param topicRetInputor topicRetInputor
	 */
	public void setTopicRetInputor(final String topicRetInputor) {
		this.topicRetInputor = topicRetInputor;
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
