/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.drinkit

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


//TODO: Add imports

class MyFirebaseMessagingService : FirebaseMessagingService() {

  // TODO: add onCreate
  private var broadcaster: LocalBroadcastManager? = null

  override fun onCreate() {
    broadcaster = LocalBroadcastManager.getInstance(this)
  }
  // TODO: override onNewToken

  // TODO: add an onMessageReceived function
  //    The notification and the data are both handled here
  override fun onMessageReceived(remoteMessage: RemoteMessage) {
    super.onMessageReceived(remoteMessage)
    handleMessage(remoteMessage)
  }

  // TODO: add a handle message method
  private fun handleMessage(remoteMessage: RemoteMessage) {

    //1 You create a handler to get the message and extract the data.
    val handler = Handler(Looper.getMainLooper())

    //2 You use that handler to post the toast through a runnable.
    handler.post(Runnable {
//      This takes the information that comes in the payload of the notification
//      with the key text and puts it in an intent called MyData with another key called message, which is directed to MainActivity.
      remoteMessage.notification?.let {
        val intent = Intent("MyData")
        intent.putExtra("message", remoteMessage.data["text"]);
        broadcaster?.sendBroadcast(intent);
      }
    }
    )
  }



  // TODO: Create a message receiver constant


  companion object {
    private const val TAG = "MyFirebaseMessagingS"
  }
}