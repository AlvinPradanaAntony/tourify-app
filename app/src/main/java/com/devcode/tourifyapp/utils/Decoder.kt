package com.devcode.tourifyapp.utils

import android.util.Base64

class Decoder {
     fun decodeJwt(jwt: String): String {
        val parts = jwt.split(".")
        return String(Base64.decode(parts[1], Base64.DEFAULT))
    }
}