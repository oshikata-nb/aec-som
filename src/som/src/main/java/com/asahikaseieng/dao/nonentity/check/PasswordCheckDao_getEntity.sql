/*
 * 自社マスタ検索用SQL
 *
 * entityName=PasswordCheck
 * packageName=dao.nonentity.check
 * methodName=getEntity
 *
 */
SELECT 	COMPANY.PASSWORD_VALID_TERM 		--パスワード有効期限
, 		COMPANY.PASSWORD_KETA_LOWER_LIMIT	--パスワード桁数下限
, 		COMPANY.PASSWORD_KETA_UPPER_LIMIT 	--パスワード桁数上限
, 		COMPANY.COMPANY_CD 	                --会社コード
FROM 	COMPANY COMPANY
WHERE 	ROWNUM <= 1
ORDER BY COMPANY.COMPANY_CD

