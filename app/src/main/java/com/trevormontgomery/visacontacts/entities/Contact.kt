package com.trevormontgomery.visacontacts.entities

import androidx.compose.runtime.Immutable

@Immutable
data class Contact(val id: Int, val firstName: String, val lastName: String, val email: String, val phoneNumber: String) {

    override fun equals(other: Any?): Boolean {
        return other is Contact
                && other.id == id
                && other.firstName == firstName
                && other.lastName == lastName
                && other.email == email
                && other.phoneNumber == phoneNumber
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        return result
    }
}