package com.nischal.newsapp.ui.newsList.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class NewsResponse(

    @field:SerializedName("displayName")
    val displayName: String? = null,

    @field:SerializedName("sponsored")
    val sponsored: Boolean? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("assetType")
    val assetType: String? = null,

    @field:SerializedName("timeStamp")
    val timeStamp: Long? = null,

    @field:SerializedName("onTime")
    val onTime: Long? = null,

    @field:SerializedName("assets")
    val assets: List<AssetsItem>? = null,

    @field:SerializedName("relatedAssets")
    val relatedAssets: List<Any>? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("lastModified")
    val lastModified: Long? = null
)

@Parcelize
data class RelatedImagesItem(

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("sponsored")
    val sponsored: Boolean? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("assetType")
    val assetType: String? = null,

    @field:SerializedName("timeStamp")
    val timeStamp: Long? = null,

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("photographer")
    val photographer: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("lastModified")
    val lastModified: Long? = null,

    @field:SerializedName("height")
    val height: Int? = null,

    @field:SerializedName("xLarge")
    val xLarge: String? = null,

    @field:SerializedName("thumbnail@2x")
    val thumbnail2x: String? = null,

    @field:SerializedName("thumbnail")
    val thumbnail: String? = null,

    @field:SerializedName("large")
    val large: String? = null,

    @field:SerializedName("xLarge@2x")
    val xLarge2x: String? = null,

    @field:SerializedName("large@2x")
    val large2x: String? = null
) : Parcelable

data class Overrides(

    @field:SerializedName("overrideAbstract")
    val overrideAbstract: String? = null,

    @field:SerializedName("overrideHeadline")
    val overrideHeadline: String? = null
)

data class SourcesItem(

    @field:SerializedName("tagId")
    val tagId: String? = null
)

data class CategoriesItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("orderNum")
    val orderNum: Int? = null,

    @field:SerializedName("sectionPath")
    val sectionPath: String? = null
)

data class RelatedAssetsItem(

    @field:SerializedName("timeStamp")
    val timeStamp: Long? = null,

    @field:SerializedName("sponsored")
    val sponsored: Boolean? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("categories")
    val categories: List<CategoriesItem?>? = null,

    @field:SerializedName("lastModified")
    val lastModified: Long? = null,

    @field:SerializedName("headline")
    val headline: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("assetType")
    val assetType: String? = null,

    @field:SerializedName("onTime")
    val onTime: Long? = null
)

data class AssetsItem(

    @field:SerializedName("byLine")
    val byLine: String? = null,

    @field:SerializedName("acceptComments")
    val acceptComments: Boolean? = null,

    @field:SerializedName("sources")
    val sources: List<SourcesItem>? = null,

    @field:SerializedName("relatedImages")
    val relatedImages: List<RelatedImagesItem>? = null,

    @field:SerializedName("theAbstract")
    val theAbstract: String? = null,

    @field:SerializedName("legalStatus")
    val legalStatus: String? = null,

    @field:SerializedName("sponsored")
    val sponsored: Boolean? = null,

    @field:SerializedName("overrides")
    val overrides: Overrides? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("numberOfComments")
    val numberOfComments: Int? = null,

    @field:SerializedName("assetType")
    val assetType: String? = null,

    @field:SerializedName("timeStamp")
    val timeStamp: Long? = null,

    @field:SerializedName("signPost")
    val signPost: String? = null,

    @field:SerializedName("companies")
    val companies: List<Any>? = null,

    @field:SerializedName("relatedAssets")
    val relatedAssets: List<RelatedAssetsItem>? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("categories")
    val categories: List<CategoriesItem>? = null,

    @field:SerializedName("lastModified")
    val lastModified: Long? = null,

    @field:SerializedName("tabletHeadline")
    val tabletHeadline: String? = null,

    @field:SerializedName("indexHeadline")
    val indexHeadline: String? = null,

    @field:SerializedName("headline")
    val headline: String? = null,

    @field:SerializedName("authors")
    val authors: List<Any>? = null,

    @field:SerializedName("onTime")
    val onTime: Long? = null
) {
    fun getNewsDisplayModel(): NewsDisplayModel {
        return NewsDisplayModel(
            id = id,
            byLine = byLine,
            relatedImages = relatedImages,
            url = url,
            timeStamp = timeStamp,
            headline = headline,
            theAbstract = theAbstract
        )
    }
}
