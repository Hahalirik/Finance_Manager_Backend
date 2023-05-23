package ru.financemanager.dabase.detail

class DetailDTO(
    val name: String,
    val id_detail: String,
    val fk_id_transaction: String,
    val price: String,
)