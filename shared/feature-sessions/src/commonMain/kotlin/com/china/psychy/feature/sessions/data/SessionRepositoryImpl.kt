package com.china.psychy.feature.sessions.data

import com.china.psychy.feature.sessions.model.Session
import me.tatarka.inject.annotations.Inject

@Inject
class SessionRepositoryImpl : SessionRepository {
    override fun getSessions(): List<Session> {
        TODO("Not yet implemented")
    }

    override fun findSessionById(id: Long): Session {
        TODO("Not yet implemented")
    }
}