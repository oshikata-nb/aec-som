/*
 * 所属マスタ更新用SQL
 * AECS佐藤 受注納入先区分を追加 2020/01/21
 *
 * entityName=BelongDetail
 * packageName=belongdetail
 * methodName=update
 *
 */

UPDATE BELONG
SET POST_ID = /*bean.postId*/
, BELONG_KBN = /*bean.belongKbn*/
, ORDER_DELIVERY_KBN = /*bean.orderDeliveryKbn*/
, ORGANIZATION_CD = /*bean.organizationCd*/
WHERE ORGANIZATION_CD = /*bean.savOrganizationCd*/
AND TANTO_CD = /*bean.tantoCd*/
AND POST_ID = /*bean.savPostId*/

/*IF (bean.savOrderDeliveryKbn != null) && (bean.savOrderDeliveryKbn != "")*/
AND ORDER_DELIVERY_KBN = /*bean.savOrderDeliveryKbn*/
/*END*/

/*IF (bean.savBelongKbn != null) && (bean.savBelongKbn != "")*/
AND BELONG_KBN = /*bean.savBelongKbn*/
/*END*/

