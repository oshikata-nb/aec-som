/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
*/

/**
 * 科目マスター存在チェック用SQL
 *
 * @author tosco
 *
 * entityName=int
 * packageName=accept
 * methodName=getCountAccounts
 *
 */
SELECT	COUNT(ACCOUNTS_CD) CNT
FROM	ACCOUNTS
WHERE	ACCOUNTS_CD = /*accountsCd*/