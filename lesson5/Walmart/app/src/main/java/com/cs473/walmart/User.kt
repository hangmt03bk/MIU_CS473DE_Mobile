package com.cs473.walmart

import java.io.Serializable

class User (var firstName: String?, var lastName: String?, var username: String?, var password: String?):
    Serializable {

    override fun toString(): String {
        return "UserAccount(firstName=$firstName, " +
                "lastName=$lastName," +
                "username=$username," +
                "password=$password)"
    }
}