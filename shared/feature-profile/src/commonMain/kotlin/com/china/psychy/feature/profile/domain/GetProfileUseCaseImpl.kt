package com.china.psychy.feature.profile.domain

import com.china.psychy.feature.profile.data.UserRepository
import me.tatarka.inject.annotations.Inject

@Inject
class GetProfileUseCaseImpl(
    private val userRepository: UserRepository
) : GetProfileUseCase {
    override suspend fun invoke() = userRepository.getUser()
}