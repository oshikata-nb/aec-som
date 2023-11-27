''' <summary>
''' 変換処理共通クラス
''' </summary>
''' <remarks></remarks>
Public Class Convert
    ''' <summary>
    ''' SQL文字列変換
    ''' </summary>
    ''' <param name="val"></param>
    ''' <param name="NotTrim"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function SqlStr(ByVal val As Object, Optional ByVal NotTrim As Boolean = False) As String

        Dim ret As String = ""

        Try
            If NotTrim = False Then
                ret = Convert.Cnv2String(val)
            Else
                ret = val.ToString
            End If

            'SQLインジェクション対策
            ret = ret.Replace("'", "''")
            Return "'" & ret & "'"

        Catch ex As Exception
            Return ret
        End Try
    End Function

    ''' <summary>
    ''' 文字列変換
    ''' </summary>
    ''' <param name="obj"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function Cnv2String(ByVal obj As Object) As String
        Dim str As String

        Try
            If obj Is Nothing Or obj Is System.DBNull.Value Then
                str = ""
            Else
                str = CType(obj, System.String).Trim
            End If

        Catch ex As Exception
            str = ""
        End Try

        Return str
    End Function

    ''' <summary>
    ''' 整数(int32)変換
    ''' </summary>
    ''' <param name="obj"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function Cnv2Int32(ByVal obj As Object) As Integer
        Dim num As Integer

        Try
            If obj Is Nothing Or obj Is System.DBNull.Value Or obj Is "" Then
                num = 0
            Else
                num = CType(obj, System.Int32)
            End If

        Catch ex As Exception
            num = 0
        End Try

        Return num
    End Function

    ''' <summary>
    ''' 10進数(Decimal)変換
    ''' </summary>
    ''' <param name="obj"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function Cnv2Decimal(ByVal obj As Object) As Decimal
        Dim num As Decimal

        Try
            If obj Is Nothing Or obj Is System.DBNull.Value Or obj Is "" Then
                num = 0
            Else
                num = CType(obj, System.Decimal)
            End If

        Catch ex As Exception
            num = 0
        End Try

        Return num
    End Function
End Class
