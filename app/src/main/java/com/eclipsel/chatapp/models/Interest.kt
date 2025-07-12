package com.eclipsel.chatapp.models

import com.eclipsel.chatapp.utils.Pickable

interface IInterest {
    val id: String
    val title: String
//    TODO: switch to image icons, that are served on some endpoint later
//    val iconUrl: String
    val iconUrl: Int
}

data class InterestDTO(override val id: String, override val title: String, override val iconUrl: Int) : IInterest

data class PickableInterest(
    override val id: String,
    override val title: String,
    override val iconUrl: Int,
    override val itemId: Int,
    override val picked: Boolean = false
) : IInterest, Pickable {
    companion object {
        fun fromListDTO(dtos: List<InterestDTO>) : List<PickableInterest> {
            return dtos
                .mapIndexed { idx, it ->
                    PickableInterest(
                        id = it.id,
                        title = it.title,
                        iconUrl = it.iconUrl,
                        itemId = idx
                    )
                }
        }
    }
}