package com.example.davaleba_14.dataClasses

data class ItemB(
    val id: Int,
    val name: String,
    val description: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemB

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}