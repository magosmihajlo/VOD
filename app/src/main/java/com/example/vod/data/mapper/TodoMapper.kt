// Create a new file e.g., data/mapper/TodoMapper.kt
package com.example.vod.data.mapper

import com.example.vod.data.remote.dto.TodoDto
import com.example.vod.domain.model.Todo

// Extension function to convert TodoDto to Todo
fun TodoDto.toDomain(): Todo {
    return Todo(
        value = this.valueString // Map the DTO's valueString to the Domain's value
    )
}