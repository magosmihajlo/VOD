/*
Defines the domain model for a to-do item.

This is a data class that represents a to-do item in your app’s core logic.

Although it looks similar to TodoDto, it is free from any external concerns like JSON annotations.

This separation allows your app to evolve independently of the data source.
 */

package com.example.vod.domain.model

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
