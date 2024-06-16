package com.smiley.firebasenotificationjava.utills;

import android.util.Log;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.Lists;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class AccessToken {

    private static final String firebaseMessagingScope = "https://www.googleapis.com/auth/firebase.messaging";

    public String getAccessToken(){
     try{
         String json="{\n" +
                 "  \"type\": \"service_account\",\n" +
                 "  \"project_id\": \"httpv1-java-kotlin\",\n" +
                 "  \"private_key_id\": \"\",\n" +
                 "  \"private_key\": \"\",\n" +
                 "  \"client_email\": \"firebase-adminsdk-i6ay4@httpv1-java-kotlin.iam.gserviceaccount.com\",\n" +
                 "  \"client_id\": \"\",\n" +
                 "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                 "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                 "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                 "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-i6ay4%40httpv1-java-kotlin.iam.gserviceaccount.com\",\n" +
                 "  \"universe_domain\": \"googleapis.com\"\n" +
                 "}\n";

         InputStream stream=new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
         GoogleCredentials googleCredentials =GoogleCredentials.fromStream(stream).createScoped(Lists.newArrayList(firebaseMessagingScope));

         googleCredentials.refresh();

         return googleCredentials.getAccessToken().getTokenValue();
     } catch (IOException e) {
         Log.e("Error: ", Objects.requireNonNull(e.getMessage()));
         return  null;
     }
    }
}
