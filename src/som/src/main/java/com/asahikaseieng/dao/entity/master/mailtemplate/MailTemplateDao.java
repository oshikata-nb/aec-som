/*
 /*
 * Created on 2018/02/06
 *
 * $copyright$ atts
 *
 */
package com.asahikaseieng.dao.entity.master.mailtemplate;

import java.math.BigDecimal;

/**
 * MailTemplateDaoクラス
 *
 * @author ssv
 */
public interface MailTemplateDao {

	/** BEANアノテーション */
	Class<MailTemplate> BEAN = com.asahikaseieng.dao.entity.master.mailtemplate.MailTemplate.class;

	/** ARGSアノテーション getEntity */
	String getTemplate_ARGS = "MAIL_FORMAT_ID";

	/**
	 * getTemplateメソッド
	 *
	 * @param mailFormatId
	 *            mailFormatId
	 * @return MailTemplate
	 */
	MailTemplate getTemplate(final String mailFormatId);
}
