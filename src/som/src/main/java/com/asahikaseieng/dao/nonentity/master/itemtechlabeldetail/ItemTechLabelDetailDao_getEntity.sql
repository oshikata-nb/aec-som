/*
 * 技術情報ファイル情報取得用SQL
 *
 * entityName=ItemTechLabelDetail
 * packageName=itemtechlabeldetail
 * methodName=getEntity
 *
 */

SELECT LABEL_CD, LABEL_PATH, LABEL.COMMON_CD, COMMON_NAME, COMMON_VALUE, LABEL.UPDATE_DATE
FROM LABEL, COMMON
WHERE LABEL_CD = /*labelCd*/'%'
AND LABEL.COMMON_CD = /*commonCd*/'%'
AND LABEL.COMMON_CD = COMMON.COMMON_CD(+)


