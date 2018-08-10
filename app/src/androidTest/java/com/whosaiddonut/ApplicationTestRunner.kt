package com.whosaiddonut

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner

class ApplicationTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, WhoSaidDonutTestApplication::class.qualifiedName, context)
    }
}