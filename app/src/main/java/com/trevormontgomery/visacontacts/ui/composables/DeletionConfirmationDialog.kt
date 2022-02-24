package com.trevormontgomery.visacontacts.ui.composables

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DeletionConfirmationDialog(onConfirm: () -> Unit , onCancel : () -> Unit){
    AlertDialog(
        onDismissRequest = {
        },
        title = {
            Text(text = "Confirm Deletion")
        },
        text = {
            Text("Are you sure that you want to delete this contact?")
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(

                onClick = {
                    onCancel()
                }) {
                Text("Cancel")
            }
        }
    )
}