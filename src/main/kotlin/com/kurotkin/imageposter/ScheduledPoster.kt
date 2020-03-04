package com.kurotkin.imageposter

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduledPoster {

    @Scheduled(fixedRate = 60_000)
    fun postCurrentTime() {
        Poster().postImage()
    }
}