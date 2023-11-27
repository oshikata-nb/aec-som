/*
 * 相殺グループマスタ削除用SQL
 *
 * entityName=OffsetGroupDelete
 * packageName=offsetgroupdelete
 * methodName=delete
 *
 */

DELETE
FROM OFFSET_GROUP
WHERE OFFSET_GROUP_CD = /*offsetGroupCd*/'%'
