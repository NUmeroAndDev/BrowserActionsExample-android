package com.numero.browser_actions_example

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BrowserActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {
        context ?: return
        Toast.makeText(context, "Received", Toast.LENGTH_LONG).show()
    }
}