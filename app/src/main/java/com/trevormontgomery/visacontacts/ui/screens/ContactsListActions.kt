package com.trevormontgomery.visacontacts.ui.screens

import com.trevormontgomery.visacontacts.entities.Contact

sealed class ContactsListAction {

    object AddNewContactAction : ContactsListAction()
    object RequestDeletionConfirmation : ContactsListAction()
    object CancelDeletion : ContactsListAction()

    data class SubmitNewContactAction(val firstName : String , val lastName : String, val email : String , val phoneNumber : String) : ContactsListAction()

    data class SubmitEditedContact(val contact : Contact) : ContactsListAction()

    data class EditContactAction(val contact: Contact) : ContactsListAction()

    data class RemoveContactAction(val contact: Contact) : ContactsListAction()

    data class ViewContactAction( val contact: Contact) : ContactsListAction()
}

interface ContactsListActionHandler{
    fun submitAction(action : ContactsListAction)
}