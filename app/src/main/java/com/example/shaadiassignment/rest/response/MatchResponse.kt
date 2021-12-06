package com.example.shaadiassignment.db.entities

data class Response(val results : Array<Match>)

data class Match(val name: Name, val dob: Dob, val picture: Picture)

data class Name(val title: String, val first: String, val last: String)

data class Dob(val date: String, val age: Int)

data class Picture(val thumbnail: String, val medium: String, val large: String)