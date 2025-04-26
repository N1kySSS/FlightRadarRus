package com.ortin.flightradar.domain.email

import android.util.Log
import jakarta.mail.Authenticator
import jakarta.mail.Message
import jakarta.mail.MessagingException
import jakarta.mail.PasswordAuthentication
import jakarta.mail.Session
import jakarta.mail.Transport
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import java.util.Properties

class GmailSender(private val email: String, private val password: String) {

    fun sendMail(body: String) {
        val props = Properties()
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        props["mail.smtp.host"] = "smtp.gmail.com"
        props["mail.smtp.port"] = "587"

        val session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(email, password)
            }
        })

        try {
            val message = MimeMessage(session)
            message.setFrom(InternetAddress(email))
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email))
            message.subject = "FlightRadarRus Feedback"
            message.setText(body)

            Transport.send(message)
        } catch (e: MessagingException) {
            Log.e("GmailSender", "Ошибка отправки письма:", e)
        }
    }
}
