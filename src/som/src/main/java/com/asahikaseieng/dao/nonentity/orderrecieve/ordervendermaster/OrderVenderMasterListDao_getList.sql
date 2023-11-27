/*
 * 取引先グループ一覧用SQL
 *
 * entityName=OrderVenderMasterList
 * packageName=ordervendermasterlist
 * methodName=getList
 *
 */

SELECT
    OVM.VENDER_GROUP_CD
,   OVM.VENDER_GROUP_NAME
,   OVM.BALANCE_CD
FROM ORDER_VENDER_MASTER OVM
WHERE 
    OVM.VENDER_GROUP_CD IS NOT NULL
    /*IF ( condition.srhVenderGroupCd != "0" )*/
    AND OVM.VENDER_GROUP_CD LIKE /*condition.srhVenderGroupCd*/'%'
    /*END*/
    /*IF ( condition.srhBalanceCd != "0" )*/
    AND OVM.BALANCE_CD LIKE /*condition.srhBalanceCd*/'%'
    /*END*/
ORDER BY 
    OVM.VENDER_GROUP_CD