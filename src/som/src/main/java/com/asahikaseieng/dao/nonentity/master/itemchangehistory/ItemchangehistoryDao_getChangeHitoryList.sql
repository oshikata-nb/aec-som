/*
 * 更新情報取得ＳＱＬ
 *
 * entityName=Itemchangehistory
 * packageName=itemchangehistory
 * methodName=getChangeHitoryList
 */
SELECT 
	CNG_H.MENU_ID		-- メニューＩＤ（アイテムは60番）
,	CNG_H.ITEM_CD		-- 品目コード
,	CNG_H.UPDATOR_CD	-- 更新者コード
,	CNG_H.UPDATE_DATE	-- 更新日時
,	LOGIN.TANTO_NM		-- 担当者名
,	CNG_H.REASON		-- 更新理由
,	CNG_H.INPUTOR_CD	-- 追加者コード
,	CNG_H.INPUT_DATE	-- 追加日時
FROM 
	CHANGE_HISTORY CNG_H	
,	LOGIN LOGIN 
WHERE 
	CNG_H.UPDATOR_CD = LOGIN.TANTO_CD

AND ROWNUM <= 50

/*IF(condition.itemCd != null) && (condition.itemCd != "")*/
AND CNG_H.ITEM_CD = /*condition.itemCd*/'ITEM01'
/*END*/

/*IF(condition.menuId != null) && (condition.menuId != "")*/
AND CNG_H.MENU_ID = /*condition.menuId*/'60'
/*END*/

/*IF(condition.updatorCd != null) && (condition.updatorCd != "")*/
AND CNG_H.UPDATOR_CD = /*condition.updatorCd*/'a'
/*END*/

/*IF(condition.updateDateFrom != null) && (condition.updateDateFrom != "")*/
AND TO_CHAR(CNG_H.UPDATE_DATE, 'YYYY/MM/DD') >= /*condition.updateDateFrom*/'2007/07/30'
/*END*/

/*IF(condition.updateDateTo != null) && (condition.updateDateTo != "")*/
AND TO_CHAR(CNG_H.UPDATE_DATE, 'YYYY/MM/DD') <= /*condition.updateDateTo*/'2007/08/30'
/*END*/

ORDER BY CNG_H.UPDATE_DATE DESC

