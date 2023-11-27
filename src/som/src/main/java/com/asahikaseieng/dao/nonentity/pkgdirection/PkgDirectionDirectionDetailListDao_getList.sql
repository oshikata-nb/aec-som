/*
 * 包装指図－製造指図明細検索用SQL
 *
 * entityName=PkgDirectionDirectionDetailList
 * packageName=pkgdirection
 * methodName=getList
 *
 */

SELECT	DIRECTION_DETAIL.PKG_DIRECTION_NO		-- 包装指図番号
,		DIRECTION_DETAIL.ROW_NO					-- 行番号
,		DIRECTION_DETAIL.DIRECTION_NO			-- 製造指図番号
FROM	DIRECTION_DETAIL
WHERE	DIRECTION_DETAIL.PKG_DIRECTION_NO = /*pkgDirectionNo*/'H0903100001'
ORDER BY DIRECTION_DETAIL.DIRECTION_NO