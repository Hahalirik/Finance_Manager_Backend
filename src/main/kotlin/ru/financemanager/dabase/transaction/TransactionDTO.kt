package ru.financemanager.dabase.transaction

import ru.financemanager.features.transactions.TransactionResponseRemote

class TransactionDTO(
    val name: String,
    val user_login: String,
    val id_transaction: String,
    val date_time: String,
    val summ_price: String,
    val category_name: String
)

fun TransactionDTO.mapToTransactionAllResponseRemote(): TransactionResponseRemote =
    TransactionResponseRemote(
        name = name,
        id_transaction = id_transaction,
        date_time = date_time,
        summ_price = summ_price,
        category_name = category_name
    )