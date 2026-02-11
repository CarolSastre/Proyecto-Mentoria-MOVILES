package com.example.mentoria.core.domain.util

import android.util.Base64
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

object RsaHelper {

    private const val PUBLIC_KEY_STRING = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs8ZWOkaKMuPgqibkV3NY\n" +
            "77d1vn/t7zek2kSVfmZ5LF/YHWrsU9mh3e4x3VCNWZrk0KtMhkRx0gsEQ8oAaFgp\n" +
            "37cMF3QhO+aRRLhkNJmocq6W8HLfXHrJ1a3Fd3jJa+f/G7xemZGiimPh8J5MPuDc\n" +
            "AvEXLWhDdNbHtRAH1U/afX4a+DXHFr465B4zukJyy3pnNmFgQ1MLGHPagOtWsTdg\n" +
            "ROih1AvFf6Azm7rrLOZjHXQ9edwDiboLPVH8v+MR9XuB6SsWLxtd2QD7FjVa2a62\n" +
            "ggErJzuJKo6PeWson1qOYu470cZBIiCm50ZUCQSU41POcQ7O0xhRvdaJaMAehfIY\n" +
            "yQIDAQAB."

    fun encrypt(plainText: String): String {
        return try {
            // 1. Convertimos el String de la clave en un objeto PublicKey real
            val publicKey = getPublicKey(PUBLIC_KEY_STRING)

            // 2. Configuramos el cifrado RSA (El estándar suele ser RSA/ECB/PKCS1Padding)
            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.ENCRYPT_MODE, publicKey)

            // 3. Ciframos los bytes de la contraseña
            val encryptedBytes = cipher.doFinal(plainText.toByteArray())

            // 4. Devolvemos el resultado en Base64 para que pueda viajar por JSON
            Base64.encodeToString(encryptedBytes, Base64.NO_WRAP)
        } catch (e: Exception) {
            e.printStackTrace()
            "" // En caso de error, devolvemos cadena vacía
        }
    }

    private fun getPublicKey(base64PublicKey: String): PublicKey {
        // Limpieza de seguridad por si acaso se cuelan espacios o saltos de línea
        val keyFormatted = base64PublicKey
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
            .replace("\\s".toRegex(), "")

        val decodedBytes = Base64.decode(keyFormatted, Base64.DEFAULT)
        val keySpec = X509EncodedKeySpec(decodedBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePublic(keySpec)
    }
}
