package com.example.noogabab.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TimelineModel(
    @JsonProperty("id") val id: Int,
    @JsonProperty("alarm") val alarm: Boolean,
    @JsonProperty("time") val time: String,
    @JsonProperty("content") val content: String,
    @JsonProperty("subContent") val subContent: String
)
