package ru.financemanager.dabase.transaction

class TransactionDTO(
    val name: String,
    val user_login: String,
    val id_transaction: Int,
    val date_time: String,
    val summ_price: String,
    val category_name: String
)