/*
 * Created on 2009/03/05
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
 * packageName=sales
 * methodName=getCountAccounts
 *
 */
SELECT	COUNT(ACCOUNTS_CD) CNT
FROM	ACCOUNTS
WHERE	ACCOUNTS_CD = /*accountsCd*/