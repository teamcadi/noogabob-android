package com.example.noogabab

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NoogabobFirebaseMessagingService : FirebaseMessagingService() {

//    private val externalCredentialsRepo: ExternalCredentialsRepository by inject()

    override fun onNewToken(token: String) {
//        /**
//         * This will happen when:
//         * - The app deletes instance ID
//         * - The app is restored on a new device
//         * - The user uninstall/reinstall the app
//         * - The user clears app data
//         * (Not when app is updated!)
//         *
//         * Even after a token changed,
//         * the old token will still work for short period.
//         */
//        Log.d("zz", "New token published!")
//
//        externalCredentialsRepo.saveFirebaseToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        /**
         * Notification message in 'notification',
         * The pushed number in 'data'
         */

        // Notify this app-wide
        sendBroadcast(Intent().apply {
            action = ACTION_PUSH_NUMBER_NOTIFICATION
            putExtra("message", message)
        })
    }

    companion object {
        const val ACTION_PUSH_NUMBER_NOTIFICATION = "zz"
    }
}
