package com.yanchelenko.piggybank.fearues.history.data

data class HistoryProductDto(
    val id: Long,
    val title: String?,
    val description: String?,
    val imageUrl: String?,
    val url: String?
)
/*
fun HistoryProductDto.toDomain() = HistoryProduct(
    id = id,
    title = title.orEmpty(),
    description = description.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    url = url.orEmpty()
)
 */
