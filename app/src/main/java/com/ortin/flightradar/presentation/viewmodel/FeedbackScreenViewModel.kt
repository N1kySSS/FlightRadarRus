package com.ortin.flightradar.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ortin.flightradar.domain.email.GmailSender
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FeedbackScreenViewModel(private val gmailSender: GmailSender) : ViewModel() {

    private val _body = MutableStateFlow("")
    val body = _body.asStateFlow()

    fun onBodyChange(text: String) {
        _body.value = text
    }

    fun sendMail() = viewModelScope.launch(Dispatchers.IO) {
        if (body.value != "") {
            gmailSender.sendMail(body.value)
        }
        _body.value = ""
    }
}
