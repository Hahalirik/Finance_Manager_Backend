package ru.financemanager.features.transactions

import kotlinx.serialization.Serializable
import ru.financemanager.features.edittransaction.DetailReceiveRemote


@Serializable
data class TransactionReceiveCategoryRemote(
    val token: String,
    val category_name: String
)

@Serializable
data class TransactionReceiveDateTimeRemote(
    val token: String,
    val start: String,
    val end: String
)

@Serializable
data class TransactionReceiveRemote(
    val name: String,
    val date_time: String,
    val summ_price: String,
    val category_name: String,
    val token: String,
    val detailList: List<DetailReceiveRemote>
)

@Serializable
data class TransactionResponseRemote(
    val id_transaction: String,
)
