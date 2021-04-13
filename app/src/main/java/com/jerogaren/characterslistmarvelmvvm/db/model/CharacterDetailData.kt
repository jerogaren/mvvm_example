package com.jerogaren.characterslistmarvelmvvm.db.model


data class ResponseDetailApi(
    val code: Int,
    val etag: String,
    val data: DataDetail
)

data class DataDetail(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterDetail>
)

data class CharacterDetail(
    val id: Int,
    val name: String,
    val description: String?,
    val comics: Comics?,
    val series: Series?,
    val stories: Stories?,
    val events: Events?,
)

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Items>
)
data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Items>
)

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<Items>
)

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Items>
)


data class Items(
    val resourceURI: String,
    val name: String
)




