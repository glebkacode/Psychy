package com.china.psychy.android.feature.auth.signup

internal val statesToModel: (SignUpStore.State) -> Model = { state ->
    Model(
        email = state.email,
        password = state.password
    )
}