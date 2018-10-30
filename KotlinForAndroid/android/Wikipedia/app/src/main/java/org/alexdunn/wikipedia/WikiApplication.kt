package org.alexdunn.wikipedia

import android.app.Application
import org.alexdunn.wikipedia.managers.WikiManager
import org.alexdunn.wikipedia.providers.ArticleDataProvider
import org.alexdunn.wikipedia.repositories.ArticleDatabaseOpenHelper
import org.alexdunn.wikipedia.repositories.FavoritesRepository
import org.alexdunn.wikipedia.repositories.HistoryRepository

/**
 * Created by alex on 12/28/17.
 */
class WikiApplication: Application() {
    private var dbHelper: ArticleDatabaseOpenHelper? = null
    private var favoritesRepository: FavoritesRepository? = null
    private var historyRepository: HistoryRepository? = null
    private var wikiProvider: ArticleDataProvider? = null
    var wikiManager: WikiManager? = null
        private set

    override fun onCreate() {
        super.onCreate()

        dbHelper = ArticleDatabaseOpenHelper(applicationContext)
        favoritesRepository = FavoritesRepository(dbHelper!!)
        historyRepository = HistoryRepository(dbHelper!!)
        wikiProvider = ArticleDataProvider()
        wikiManager = WikiManager(wikiProvider!!, favoritesRepository!!, historyRepository!!)
    }
}