/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
*/

/**
 * 帳票・ラベルマスタ詳細取得用SQL
 *
 * @author tosco
 *
 * entityName=MgrecipeLabel
 * packageName=mgrecipe
 * methodName=findByLabelCdCommonCd
 *
 */
SELECT
    LABEL.LABEL_CD
,	LABEL.LABEL_NAME
,	LABEL.LABEL_PATH
,	LABEL.COMMON_CD
,	LABEL.INPUTOR_CD
,	LABEL.INPUT_DATE
,	LABEL.UPDATOR_CD
,	LABEL.UPDATE_DATE
FROM
	LABEL
WHERE
	LABEL.LABEL_CD IS NOT NULL
/*IF (( labelCd != null ) && ( labelCd != "" ))*/
	AND LABEL.LABEL_CD = /*labelCd*/
/*END*/
/*IF (( commonCd != null ) && ( commonCd != "" ))*/
	AND LABEL.COMMON_CD = /*commonCd*/
/*END*/
