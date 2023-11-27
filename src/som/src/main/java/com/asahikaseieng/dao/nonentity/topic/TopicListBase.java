/*
 * Created on 2008/11/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.topic;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Topicクラス.
 * @author a1020630
 */
public class TopicListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TopicListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション topicId */
	public static final String topicId_COLUMN = "TOPIC_ID";

	/** COLUMNアノテーション topicTitle */
	public static final String topicTitle_COLUMN = "TOPIC_TITLE";

	/** COLUMNアノテーション topicInputor. */
	public static final String topicInputor_COLUMN = "TOPIC_INPUTOR";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション topicRetInputor. */
	public static final String topicRetInputor_COLUMN = "TOPIC_RET_INPUTOR";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal topicId;

	private String topicTitle;

	private String topicInputor;

	private java.sql.Timestamp inputDate;

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
