package ru.financemanager.dabase.detail

class DetailDTO(
    val name: String,
    val id_detail: Int,
    val fk_id_transaction: Int,
    val price: String,
)