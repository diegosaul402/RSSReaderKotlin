package com.diegosaul402.rssreader.model

/**
 * Created by diego on 09/08/2017.
 */
data class RSSObject(val status: String, val feed: Feed, val items: List<Item>)