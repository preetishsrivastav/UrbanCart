package com.example.urbancart.model.mapper

import com.example.urbancart.extensions.capitalize
import com.example.urbancart.model.domain.user.Address
import com.example.urbancart.model.domain.user.Name
import com.example.urbancart.model.domain.user.User
import com.example.urbancart.model.network.NetworkUser
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun buildFrom(networkUser: NetworkUser): User {
        return User(
            id = networkUser.id,
            name = Name(
                firstname = networkUser.name.firstname.capitalize(),
                lastname = networkUser.name.lastname.capitalize()
            ),
            email = networkUser.email,
            username = networkUser.username,
            phoneNumber = networkUser.phone,
            address = Address(
                city = networkUser.address.city,
                number = networkUser.address.number,
                street = networkUser.address.street,
                zipcode = networkUser.address.zipcode.split("-")[0],
                lat = networkUser.address.geolocation.lat,
                long = networkUser.address.geolocation.long
            )
        )
    }
}