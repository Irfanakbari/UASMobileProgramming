package com.example.utsmobileprogramming.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.utsmobileprogramming.R


class UsernameModal : DialogFragment() {
    private lateinit var usernameEditText: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = layoutInflater.inflate(R.layout.dialog_username, null)
        usernameEditText = view.findViewById(R.id.username_edit_text)
        val builder = AlertDialog.Builder(activity)
        builder
            .setView(view)
            .setPositiveButton("OK") { _, _ ->
                usernameEditText.text.toString()
                // Perform the necessary actions with the entered username
            }
            .setNegativeButton("Cancel") { _, _ ->
                // Perform the necessary actions when the Cancel button is clicked
            }
        return builder.create()
    }
}

