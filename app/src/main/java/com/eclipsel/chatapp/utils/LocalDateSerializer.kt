package com.eclipsel.chatapp.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@ExperimentalSerializationApi
@Serializer(forClass = LocalDate::class)
class LocalDateSerializer : KSerializer<LocalDate>{
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    override fun serialize(
        encoder: Encoder,
        value: LocalDate
    ) {
        value.format(formatter)
    }

    override fun deserialize(decoder: Decoder): LocalDate {
        return LocalDate.parse(decoder.decodeString(), formatter )
    }
}