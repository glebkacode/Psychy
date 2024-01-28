package com.china.psychy.feature.lk.data

import com.china.psychy.feature.lk.model.Lk


interface LkRepository {
    fun getLkInformation(): Lk
}