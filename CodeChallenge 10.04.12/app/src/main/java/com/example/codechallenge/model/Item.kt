package com.example.codechallenge.model

import java.io.Serializable

data class Item(
    var id: Int?,
    var title: String?,
    var description: String?,
    var type: String?,
    var goal: Double?,
    var reward: Reward?
) : Serializable