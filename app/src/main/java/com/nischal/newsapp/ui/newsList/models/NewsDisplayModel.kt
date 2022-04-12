package com.nischal.newsapp.ui.newsList.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsDisplayModel(
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("headline")
    val headline: String? = null,
    @field:SerializedName("theAbstract")
    val theAbstract: String? = null,
    @field:SerializedName("byLine")
    val byLine: String? = null,
    @field:SerializedName("relatedImages")
    val relatedImages: List<RelatedImagesItem>? = null,
    @field:SerializedName("url")
    val url: String? = null,
    @field:SerializedName("timeStamp")
    val timeStamp: Long? = null,
    @field:SerializedName("sponsored")
    val sponsored: Boolean? = null,
    @field:SerializedName("assetType")
    val assetType: String? = null
) : Parcelable {
    /**
     * @return image url or null from [relatedImages] list.
     * Cases I:
     * Returns thumbnail url, if the smallest image is available i.e. of type [ImageType.THUMBNAIL].
     * Case II:
     * Returns any other available image url, if thumbnail url is not present.
     * Case III:
     * Returns null, if images are not available.
     */
    fun getSmallestImageUrl(): String? {
        val thumbImageItem: RelatedImagesItem? = relatedImages?.firstOrNull {
            it.type == ImageType.THUMBNAIL
        }
        return if (thumbImageItem != null && !thumbImageItem.url.isNullOrEmpty()) {
            thumbImageItem.url
        } else {
            relatedImages?.firstOrNull {
                !it.url.isNullOrEmpty()
            }?.url
        }
    }

    /**
     * @return by line information to be displayed
     */
    fun getByLineToDisplay(): String {
        return "By $byLine"
    }
}

object ImageType {
    const val THUMBNAIL = "thumbnail"
}