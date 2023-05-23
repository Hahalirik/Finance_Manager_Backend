package ru.financemanager.features.edittransaction

import kotlinx.serialization.Serializable

@Serializable
data class DetailReceiveIdTransactionRemote(
    val id_transaction: String
)

@Serializable
data class DetailReceiveRemote(
    val name: String,
    val price: String
)

@Serializable
data class DetailResponseRemote(
    val name: String,
    val id_detail: String,
    val price: String
)
