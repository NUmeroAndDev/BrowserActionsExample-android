package com.numero.browser_actions_example

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.browseractions.BrowserActionItem
import androidx.browser.browseractions.BrowserActionsIntent
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        simpleBrowserActionsButton.setOnClickListener {
            showSimpleBrowserActions()
        }
        browserActionsAndCustomButton.setOnClickListener {
            showBrowserActionsAndCustom()
        }
        browserActionsAndReceiverButton.setOnClickListener {
            showBrowserActionsAndReceiver()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSimpleBrowserActions() {
        val uri = GOOGLE_URL.toUri()
        BrowserActionsIntent.openBrowserAction(this, uri)
    }

    private fun showBrowserActionsAndCustom() {
        val uri = GOOGLE_URL.toUri()

        val openLinkIntent = Intent(Intent.ACTION_VIEW, GOOGLE_APP_URL.toUri())
        val pendingIntent = PendingIntent.getActivity(this, 0, openLinkIntent, 0)
        val myAction = BrowserActionItem("My Custom Action!", pendingIntent, R.drawable.ic_android)
        val items = arrayListOf(myAction)

        BrowserActionsIntent.openBrowserAction(this, uri, BrowserActionsIntent.URL_TYPE_NONE, items, null)
    }

    private fun showBrowserActionsAndReceiver() {
        val uri = GOOGLE_URL.toUri()

        val defaultIntent = Intent().apply {
            setClass(applicationContext, BrowserActionReceiver::class.java)
        }
        val defaultAction = PendingIntent.getBroadcast(this, 0, defaultIntent, 0)

        BrowserActionsIntent.openBrowserAction(this, uri, BrowserActionsIntent.URL_TYPE_NONE, arrayListOf(), defaultAction)
    }

    companion object {
        private const val GOOGLE_URL = "https://www.google.co.jp/"
        private const val GOOGLE_APP_URL = "market://details?id=com.google.android.googlequicksearchbox"
    }
}
