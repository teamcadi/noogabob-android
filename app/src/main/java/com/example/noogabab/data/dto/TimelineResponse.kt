package com.example.noogabab.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TimelineResponse(
    @JsonProperty("data") val results: List<TimelineModel>
)