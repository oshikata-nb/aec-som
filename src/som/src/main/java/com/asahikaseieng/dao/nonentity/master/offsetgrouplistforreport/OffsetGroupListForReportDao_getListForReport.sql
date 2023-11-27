/*
 * 相殺グループマスタ帳票用SQL
 *
 * entityName=OffsetGroupListForReport
 * packageName=offsetgrouplistforreport
 * methodName=getListForReport
 *
 */

SELECT OFFSET.*
, VENDER.VENDER_NAME1
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM OFFSET_GROUP OFFSET
, VENDER
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE OFFSET_GROUP_CD IS NOT NULL

/*IF (( condition.srhOffsetGroupCd != null ) && ( condition.srhOffsetGroupCd != "" ))*/
AND (OFFSET.OFFSET_GROUP_CD LIKE /*condition.srhOffsetGroupCd*/'%' OR OFFSET.OFFSET_GROUP_NAME LIKE /*condition.srhOffsetGroupCd*/'%')
/*END*/

AND OFFSET.VENDER_DIVISION = VENDER.VENDER_DIVISION(+)
AND OFFSET.VENDER_CD = VENDER.VENDER_CD(+)
AND OFFSET.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND OFFSET.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY OFFSET.OFFSET_GROUP_CD

