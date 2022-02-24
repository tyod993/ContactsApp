package com.trevormontgomery.visacontacts.data

import com.trevormontgomery.visacontacts.entities.Contact

class ContactsListInteractor(private val capabilities: ContactManagementCapabilities) {

    fun addContact(firstName : String , lastName : String, email : String , phoneNumber : String){
        capabilities.addContact(
            firstName = firstName,
            lastName = lastName ,
            email = email,
            phoneNumber = phoneNumber)
    }

    fun deleteContact(contact : Contact){
        capabilities.deleteContact(contact)
    }

    fun editContact(contact : Contact){
        capabilities.editContact(contact)
    }
    fun getAllContacts() : List<Contact>{
        return capabilities.getAllContacts()
    }
}

interface ContactManagementCapabilities{
    fun deleteContact(contact: Contact)
    fun addContact(firstName : String , lastName : String, email : String , phoneNumber : String)
    fun editContact(contact: Contact)
    fun getAllContacts() : List<Contact>
}