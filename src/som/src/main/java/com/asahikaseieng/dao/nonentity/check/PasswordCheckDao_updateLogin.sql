/*
 * ログインユーザ定義マスタ更新用SQL
 *
 * entityName=PasswordCheck
 * packageName=dao.nonentity.check
 * methodName=updateLogin
 *
 */
UPDATE LOGIN 
SET ACTIVE_FLG = '0'
WHERE TANTO_CD = /*tantoCd*/'nona000001' -- 担当者CODE

