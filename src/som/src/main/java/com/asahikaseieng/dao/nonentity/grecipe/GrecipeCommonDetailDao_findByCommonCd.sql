/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
*/

/**
 * テンプレート保存先マスタ共通の詳細データ取得SQL
 *
 * @author tosco
 *
 * entityName=GrecipeCommon
 * packageName=grecipe
 * methodName=findByCommonCd
 *
 */
SELECT
    COMMON.COMMON_CD
,	COMMON.COMMON_NAME
,	COMMON.COMMON_VALUE
,	COMMON.REMARK
,	COMMON.INPUTOR_CD
,	COMMON.INPUT_DATE
,	COMMON.UPDATOR_CD
,	COMMON.UPDATE_DATE
FROM
	COMMON
WHERE
	COMMON.COMMON_CD IS NOT NULL
/*IF (( commonCd != null ) && ( commonCd != "" ))*/
	AND COMMON.COMMON_CD = /*commonCd*/
/*END*/