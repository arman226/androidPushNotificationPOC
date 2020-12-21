package com.raywenderlich.android.drinkit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*

// TODO: import libraries

/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    // Switch to AppTheme for displaying the activity
    setTheme(R.style.AppTheme)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // TODO: create OnClickListener for the button_retrieve_token
      button_retrieve_token.setOnClickListener {
//          You get the instance of the app that’s tied to the Firebase back end and add a complete listener to it so you know when the task finishes, whether with an error or success.
//          You check if the task, which is the result of this function, isn’t successful and return an error to the console.
//          If the task is successful, you get the result from it.
//          After getting the token, you set it as a string and output it for both the terminal and a toast message.
          // 1
          FirebaseInstanceId.getInstance().instanceId
                  .addOnCompleteListener(OnCompleteListener { task ->
                      // 2
                      if (!task.isSuccessful) {
                          Log.w(TAG, "getInstanceId failed", task.exception)
                          return@OnCompleteListener
                      }
                      // 3
                      val token = task.result?.token

                      // 4
                      val msg = getString(R.string.token_prefix, token)
                      Log.d(TAG, msg)
                      Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
                  })

          if (checkGooglePlayServices()) {

          } else {
              //You won't be able to send notifications to this device
              Log.w(TAG, "Device doesn't have google play services")
          }
      }



    // TODO: check in bundle extras for notification data
  }


  override fun onStart() {
    super.onStart()
    //TODO: Register the receiver for notifications
  }

  override fun onStop() {
    super.onStop()
    // TODO: Unregister the receiver for notifications
  }

  // TODO: Add a method for receiving notifications

  // TODO: Add a function to check for Google Play Services

  // TODO: Create a message receiver constant

  companion object {
    private const val TAG = "MainActivity"
  }

//    First, you use Android’s availability API to check for Google Play Services.
//    If the status isn’t successful, you need to manage the error. In this case, you know the user can’t receive push notifications.
//    If it is successful, there’s no problem and you can continue with the notification process.

    private fun checkGooglePlayServices(): Boolean {
        // 1
        val status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
        // 2
        return if (status != ConnectionResult.SUCCESS) {
            Log.e(TAG, "Error")
            // ask user to update google play services and manage the error.
            false
        } else {
            // 3
            Log.i(TAG, "Google play services updated")
            true
        }
    }
}