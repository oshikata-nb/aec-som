Imports log4net
Imports Microsoft.Office.Interop
Imports Microsoft.Office.Interop.Excel
Imports Common
Imports System.Configuration

''' <summary>
''' メインクラス
''' </summary>
''' <remarks></remarks>
Class Main
    Inherits ReportCreatorBase

#Region "変数"

    ''' <summary>
    ''' ロガー
    ''' </summary>
    ''' <remarks></remarks>
    Private m_log As ILog _
            = LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType)

#Region "引数パラメータ"

    ''' <summary>
    ''' パラメータ1
    ''' </summary>
    ''' <remarks></remarks>
    Private m_param1 As String = String.Empty

    ''' <summary>
    ''' パラメータ2
    ''' </summary>
    ''' <remarks></remarks>
    Private m_param2 As String = String.Empty

    ''' <summary>
    ''' パラメータ3
    ''' </summary>
    ''' <remarks></remarks>
    Private m_param3 As String = String.Empty

#End Region

#End Region

#Region "Enum"

    ''' <summary>
    ''' パラメータINDEX（個別）
    ''' </summary>
    ''' <remarks></remarks>
    Private Enum PRM_IDX As Integer
        PARAM1 = PRM_IDX_COMMON.COUNT
        PARAM2
        PARAM3
        COUNT
    End Enum

#End Region

#Region "スタートアップ"

    ''' <summary>
    ''' スタートアップ関数
    ''' </summary>
    ''' <param name="args"></param>
    ''' <remarks></remarks>
    Public Shared Function Main(args As String()) As Integer
        Dim myReport As Main = Nothing
        Dim result As PROC_RESULT = PROC_RESULT.異常終了_システム例外
        Try
            myReport = New Main
            myReport.m_log.Info("Start.")
            myReport.m_log.Debug(" args = " & String.Join(", ", args))

            '処理実行
            result = myReport.ExecuteProc(args)
            myReport.m_log.Info("Result = " & result)

        Catch ex As Exception
            'エラーメッセージ表示
            myReport.m_log.Error(ex)
            result = PROC_RESULT.異常終了_システム例外
            myReport.m_log.Info("Result = " & result)
        Finally
            myReport.m_log.Info("End.")
        End Try

        Return result
    End Function

#End Region

#Region "オーバーライド"

    ''' <summary>
    ''' 引数のチェック（個別）
    ''' </summary>
    ''' <param name="args"></param>
    ''' <remarks></remarks>
    Protected Overrides Function GetParams(args As String()) As PROC_RESULT
        If args.Count < PRM_IDX.COUNT Then
            Return PROC_RESULT.異常終了_引数不足
        End If

        '引数の取得
        m_param1 = args(PRM_IDX.PARAM1)
        m_param2 = args(PRM_IDX.PARAM2)
        m_param3 = args(PRM_IDX.PARAM3)

        Return PROC_RESULT.正常終了
    End Function

    ''' <summary>
    ''' シート編集処理
    ''' </summary>
    ''' <param name="exlSheet"></param>
    ''' <remarks></remarks>
    Protected Overrides function EditSheet(exlSheet As Excel.Worksheet) As Boolean

        m_log.Info("Start.")

        Dim dt As Data.DataTable = GetDataSample()
        If dt Is Nothing Then
            Return False
        End If

        Dim rIdx As Integer = 7
        For Each dr As DataRow In dt.Rows
            exlSheet.Range("A" & rIdx.ToString).Value = dr("ORDER_NO")
            rIdx = rIdx + 1
        Next

        m_log.Info("End.")

		Return True
    End Function

#End Region

#Region "個別処理"

    ''' <summary>
    ''' データ取得
    ''' </summary>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Function GetDataSample() As Data.DataTable

        Try
            Dim sql As New System.Text.StringBuilder

            sql.Clear()
            sql.Append(" SELECT OH.*")
            sql.Append("   FROM ORDER_HEAD OH ")

            Return Me.GetData(sql.ToString)

        Catch ex As Exception
            'エラー出力
            m_log.Error("[GetDataSample]", ex)
        End Try

        Return Nothing

    End Function

#End Region

End Class
