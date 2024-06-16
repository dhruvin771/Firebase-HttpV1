package com.smiley.firebasenotificationkotlin.utills

import android.util.Log
import com.google.auth.oauth2.GoogleCredentials
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.util.Objects


class AccessToken {
    val accessToken: String?
        get() = try {
            val json = """{
  "type": "service_account",
  "project_id": "httpv1-java-kotlin",
  "private_key_id": "",
  "private_key": "",
  "client_email": "firebase-adminsdk-i6ay4@httpv1-java-kotlin.iam.gserviceaccount.com",
  "client_id": "",
  "auth_uri": "https://accounts.google.com/o/oauth2/auth",
  "token_uri": "https://oauth2.googleapis.com/token",
  "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
  "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-i6ay4%40httpv1-java-kotlin.iam.gserviceaccount.com",
  "universe_domain": "googleapis.com"
}
"""
            val stream: InputStream = ByteArrayInputStream(json.toByteArray(StandardCharsets.UTF_8))
            val googleCredentials: GoogleCredentials =
                GoogleCredentials.fromStream(stream).createScoped(
                    com.google.common.collect.Lists.newArrayList(
                        firebaseMessagingScope
                    )
                )
            googleCredentials.refresh()
            googleCredentials.accessToken.tokenValue
        } catch (e: IOException) {
            Objects.requireNonNull(e.message).let {
                it?.let { it1 -> Log.e("Error: ", it1) }
            }
            null
        }

    companion object {
        private const val firebaseMessagingScope =
            "https://www.googleapis.com/auth/firebase.messaging"
    }
}

