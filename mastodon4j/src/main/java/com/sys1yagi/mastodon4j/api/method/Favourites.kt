package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.api.method.contract.FavouritesContract
import com.sys1yagi.mastodon4j.extension.genericType

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#favourites
 */
class Favourites(val client: MastodonClient): FavouritesContract.Public, FavouritesContract.AuthRequired {

    //  GET /api/v1/favourites
    @Throws(Mastodon4jRequestException::class)
    override fun getFavourites(range: Range): List<Status> {
        val response = client.get("favourites", range.toParameter())
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    genericType<List<Status>>()
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }
}
