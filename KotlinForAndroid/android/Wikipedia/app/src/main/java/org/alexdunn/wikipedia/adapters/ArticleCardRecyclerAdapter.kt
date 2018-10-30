package org.alexdunn.wikipedia.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.alexdunn.wikipedia.R
import org.alexdunn.wikipedia.holders.CardHolder
import org.alexdunn.wikipedia.models.WikiPage

/**
 * Created by alex on 10/25/17.
 */
class ArticleCardRecyclerAdapter() : RecyclerView.Adapter<CardHolder>() {
    val currentResults: ArrayList<WikiPage> = ArrayList<WikiPage>()

    override fun getItemCount(): Int {
        return currentResults.size
    }

    override fun onBindViewHolder(holder: CardHolder?, position: Int) {
        var page = currentResults[position]
        holder?.updateWithPage(page)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CardHolder {
        var cardItem = LayoutInflater.from(parent?.context).inflate(R.layout.article_card_item, parent, false)
        return CardHolder(cardItem)
    }
}