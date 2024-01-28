package com.china.psychy.feature.lk.domain

import com.china.psychy.feature.lk.data.LkRepository
import me.tatarka.inject.annotations.Inject

@Inject
class GetLkInfoUseCaseImpl(
    private val lkRepository: LkRepository
) : GetLkInfoUseCase {
    override suspend fun invoke() = lkRepository.getLkInformation()
}