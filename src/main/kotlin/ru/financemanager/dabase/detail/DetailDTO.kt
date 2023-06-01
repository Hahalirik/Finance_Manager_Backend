package ru.financemanager.dabase.detail

import ru.financemanager.dabase.transaction.TransactionDTO
import ru.financemanager.features.edittransaction.DetailResponseRemote
import ru.financemanager.features.transactions.TransactionResponseRemote

class DetailDTO(
    val name: String,
    val id_detail: String,
    val fk_id_transaction: String,
    val price: String,
)

fun DetailDTO.mapToDetailAllResponseRemote(): DetailResponseRemote =
    DetailResponseRemote(
        name = name,
        id_detail = id_detail,
        price = price
    )