package com.example.astamobiproject.db

import android.graphics.Bitmap
import android.net.Uri

class ItemDatabase() {

    var itemFirstBitmap: String? = null
    var itemSecondBitmap: Bitmap? = null
    var itemThirdBitmap: Bitmap? = null
    var itemFourthBitmap: Bitmap? = null

    var itemEUSize: String? = null
    var itemLength: String? = null
    var itemWidth: String? = null

    var itemModel: String? = null
    var itemMaterial: String? = null
    var itemDescription: String? = null
    var itemPrice: String? = null

    constructor(
        firstBitmap: String,

        euSize: String,
        length: String,
        width: String,

        model: String,
        material: String,
        description: String,
        price: String

    ) : this() {
        this.itemFirstBitmap = firstBitmap
        this.itemEUSize = euSize
        this.itemLength = length
        this.itemWidth = width
        this.itemModel = model
        this.itemMaterial = material
        this.itemDescription = description
        this.itemPrice = price

    }

}