/*
 * 所属マスタ追加用SQL
 * AECS佐藤 受注納入先区分を追加 2020/01/21
 *
 * entityName=BelongDetail
 * packageName=belongdetail
 * methodName=insert
 *
 */

INSERT INTO BELONG
(ORGANIZATION_CD, TANTO_CD, POST_ID, BELONG_KBN, ORDER_DELIVERY_KBN)
VALUES(/*bean.organizationCd*/'%', /*bean.tantoCd*/'%', /*bean.postId*/1, /*bean.belongKbn*/1, /*bean.orderDeliveryKbn*/'%')
