package com.example.mentoria

import android.media.RingtoneManager
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
class NFCActivity : AppCompatActivity(), NfcAdapter.ReaderCallback {

    private var nfcAdapter: NfcAdapter? = null
    private var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val keyboardController = LocalSoftwareKeyboardController.current
            var text by remember { mutableStateOf("") }

            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Introduce el texto para la URL") },
                    singleLine = true
                )
                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = {
                        if (text.isNotBlank()) {
                            url = "https://www.$text.com"
                            Toast.makeText(
                                this@NFCActivity,
                                "Url creada: $url. Acerca el dispositivo a la etiqueta NFC",
                                Toast.LENGTH_LONG
                            ).show()
                            keyboardController?.hide()
                        } else {
                            Toast.makeText(
                                this@NFCActivity,
                                "Por favor, introduce un texto para la URL",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Text("Crear y Escribir URL")
                }
            }
        }

        // La lógica de configuración de NFC
        val nfcManager = getSystemService(NFC_SERVICE) as NfcManager
        nfcAdapter = nfcManager.defaultAdapter

        if (nfcAdapter == null) {
            Toast.makeText(this, "Este dispositivo no cuenta con tecnología NFC.", Toast.LENGTH_LONG).show()
            return
        }

        if (nfcAdapter?.isEnabled == false) {
            Toast.makeText(this, "La antena NFC está desactivada.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onTagDiscovered(tag: Tag?) {
        if (url.isBlank()) {
            runOnUiThread { Toast.makeText(this, "Primero crea una URL para poder escribirla", Toast.LENGTH_SHORT).show() }
            return
        }

        val ndef = Ndef.get(tag)

        if (ndef == null) {
            runOnUiThread { Toast.makeText(this, "La etiqueta no es compatible con NDEF", Toast.LENGTH_SHORT).show() }
            return
        }

        val record = NdefRecord.createUri(url)
        val message = NdefMessage(record)

        try {
            ndef.use { it ->
                it.connect()
                if (it.maxSize < message.toByteArray().size) {
                    runOnUiThread { Toast.makeText(this, "La etiqueta no tiene suficiente espacio", Toast.LENGTH_SHORT).show() }
                    return@use
                }
                it.writeNdefMessage(message)
                runOnUiThread { Toast.makeText(this, "Etiqueta escrita con éxito", Toast.LENGTH_SHORT).show() }
                playSound()
            }
        } catch (e: Exception) {
            // Captura errores de conexión, escritura o si la etiqueta se mueve.
            runOnUiThread { Toast.makeText(this, "Error al escribir en la etiqueta: ${e.message}", Toast.LENGTH_SHORT).show() }
        }
    }

    private fun playSound() {
        try {
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext, notification)
            ringtone.play()
        } catch (e: Exception) {
            // No hacer nada si falla la reproducción del sonido
        }
    }

    override fun onResume() {
        super.onResume()
        nfcAdapter?.let {
            val options = Bundle().apply {
                putInt(NfcAdapter.EXTRA_READER_PRESENCE_CHECK_DELAY, 250)
            }
            it.enableReaderMode(
                this,
                this,
                NfcAdapter.FLAG_READER_NFC_A or
                        NfcAdapter.FLAG_READER_NFC_B or
                        NfcAdapter.FLAG_READER_NFC_F or
                        NfcAdapter.FLAG_READER_NFC_V,
                options
            )
        }
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableReaderMode(this)
    }
}
