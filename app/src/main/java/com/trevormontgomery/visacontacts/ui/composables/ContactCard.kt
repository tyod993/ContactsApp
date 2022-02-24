package com.trevormontgomery.visacontacts.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.trevormontgomery.visacontacts.entities.Contact

@Composable
fun ContactCard(contact: Contact, onClick: () -> Unit){
    Card(elevation = 4.dp, modifier = Modifier
        .padding(all = 10.dp)
        .fillMaxWidth()
        .clickable { onClick() },
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            ContactDetailsView(contact = contact)
        }
    }
}

@Composable
fun ContactDetailsView(contact: Contact){
    Column(
        modifier = Modifier
            .padding(all = 10.dp)
    ) {
        Text(text = "${contact.firstName} ${contact.lastName}", softWrap = true)
        Text(text = contact.phoneNumber, softWrap = true)
        Text(text = contact.email, softWrap = true)
    }
}