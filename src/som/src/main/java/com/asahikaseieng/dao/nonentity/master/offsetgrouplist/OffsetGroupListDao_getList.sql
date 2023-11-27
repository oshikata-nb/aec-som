/*
 * 相殺グループマスタ一覧用SQL
 *
 * entityName=OffsetGroupList
 * packageName=offsetgrouplist
 * methodName=getList
 *
 */

SELECT DISTINCT	OFFSET_GROUP_CD
,		        OFFSET_GROUP_NAME
FROM 	OFFSET_GROUP
WHERE	OFFSET_GROUP_CD IS NOT NULL

/*IF (( condition.srhOffsetGroupCd != null ) && ( condition.srhOffsetGroupCd != "" ))*/
	AND	(OFFSET_GROUP_CD LIKE /*condition.srhOffsetGroupCd*/'%' OR OFFSET_GROUP_NAME LIKE /*condition.srhOffsetGroupCd*/'%')
/*END*/

ORDER BY OFFSET_GROUP_CD


