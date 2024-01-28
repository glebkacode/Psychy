package com.china.psychy.feature.sessions.data

import com.china.psychy.feature.sessions.model.Session

interface SessionRepository {
    fun getSessions(): List<Session>
    fun findSessionById(id: Long): Session
}