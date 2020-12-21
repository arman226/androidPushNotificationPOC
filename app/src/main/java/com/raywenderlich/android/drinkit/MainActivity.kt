package com.raywenderlich.android.drinkit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
}