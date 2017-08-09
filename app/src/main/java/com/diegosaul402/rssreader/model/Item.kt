package com.diegosaul402.rssreader.Model

/**
 * Created by diego on 09/08/2017.
 */
data class Item(val title: String, val pubDate: String, val link: String, val guid: String,
                val author: String, val thumbnail: String, val description: String,
                val content: String, val enclosure: kotlin.Any, val categories: List<String>)