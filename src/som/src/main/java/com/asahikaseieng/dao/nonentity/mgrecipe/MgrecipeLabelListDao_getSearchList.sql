/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/

/**
 * 帳票・ラベルマスタ一覧取得用SQL
 *
 * @author tosco
 *
 * entityName=MgrecipeLabel
 * packageName=mgrecipe
 * methodName=getSearchList
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
	LABEL.LABEL_CD = /*labelCd*/1

ORDER BY
      LABEL.LABEL_CD
    , LABEL.COMMON_CD

