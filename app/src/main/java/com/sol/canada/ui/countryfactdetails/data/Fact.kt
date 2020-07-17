package com.sol.canada.ui.countryfactdetails.data

/**
 * Model class that holds Fact data form API
 */
data class Fact(
    val rows: List<FactDetail>,
    val title: String
)