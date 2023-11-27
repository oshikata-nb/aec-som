/*
 * 所属マスタ削除用SQL
 *
 * entityName=BelongDetail
 * packageName=belongdetail
 * methodName=delete
 *
 */

DELETE FROM BELONG
WHERE ORGANIZATION_CD = /*bean.organizationCd*/
AND TANTO_CD = /*bean.tantoCd*/
AND POST_ID = /*bean.savPostId*/
AND BELONG_KBN = /*bean.belongKbn*/
