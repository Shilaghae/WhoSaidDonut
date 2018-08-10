package com.whosaiddonut.service

data class UserCreditInfo(val accountIDVStatus: String?,
        val creditReportInfo: CreditReportInfo?,
        val dashboardStatus: String?,
        val personaType: String?,
        val coatchinSummary: CoachingSummary?,
        val augmentedCreditScore: Int?
)