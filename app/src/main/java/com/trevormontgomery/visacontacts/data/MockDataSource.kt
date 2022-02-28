package com.trevormontgomery.visacontacts.data

import com.trevormontgomery.visacontacts.entities.Contact

class MockDataSource :
    ContactManagementCapabilities{

    private var data = mutableMapOf(
        1 to Contact(
        1,
        "Trevor",
        "Montgomery",
        "email@email.com",
        "(206)707-5961"),
    )
    private val nextId = data.size + 1

    override fun addContact(firstName : String , lastName : String, email : String , phoneNumber : String){
        data[nextId] = Contact(nextId, firstName, lastName, email, phoneNumber)
    }

    override fun deleteContact(contact: Contact){
        data.remove(contact.id)
    }

    override fun editContact(contact : Contact){
        data[contact.id] = contact
    }

    override fun getAllContacts() : List<Contact> {
        return data.values.toList()
    }
}