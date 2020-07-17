package com.sol.canada.mockserver

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.sol.canada.mockserver.AssetReaderUtil.asset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

/**
 * Returns facts data when invoked from MockWebServer
 */
class SuccessDispatcher(
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
) : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        val responseFile ="facts.json"

        return run {
            val responseBody = asset(context, responseFile)
            MockResponse().setResponseCode(200).setBody(responseBody)
        }
    }
}