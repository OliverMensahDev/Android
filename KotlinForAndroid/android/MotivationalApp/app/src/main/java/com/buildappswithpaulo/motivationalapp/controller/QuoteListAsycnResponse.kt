package com.buildappswithpaulo.motivationalapp.controller

import com.buildappswithpaulo.motivationalapp.model.Quote

/**
 * Created by paulodichone on 2/9/18.
 */
interface QuoteListAsycnResponse {
    fun processFinished(quotes: ArrayList<Quote>)

}