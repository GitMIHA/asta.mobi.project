package com.example.astamobiproject.db


class BaseItemDB() {

    lateinit var itemFirstBitmap: String
//    lateinit var itemSecondBitmap: Bitmap
//    lateinit var itemThirdBitmap: Bitmap
//    lateinit var itemFourthBitmap: Bitmap

    lateinit var itemEUSize: String
    lateinit var itemLength: String
    lateinit var itemWidth: String

    lateinit var itemModel: String
    lateinit var itemMaterial: String
//    lateinit var itemDescription: String
    lateinit var itemPrice: String

    constructor(
        firstBitmap: String,
        euSize: String,
        length: String,
        width: String,
        model: String,
        material: String,
        price: String
    ) : this() {
        this.itemFirstBitmap = firstBitmap

        this.itemEUSize = euSize
        this.itemLength = length
        this.itemWidth = width

        this.itemModel = model
        this.itemMaterial = material
        this.itemPrice = price
    }
//
//    fun getFirstBitmap() : String{
//        return itemFirstBitmap
//    }
//    fun setFirstBitmap(itemFirstBitmap: String){
//        this.itemFirstBitmap = itemFirstBitmap
//    }
//
//    fun getEUSize() : String{
//        return itemEUSize
//    }
//    fun setEUSize(itemEUSize: String){
//        this.itemEUSize = itemEUSize
//    }
//
//    fun getLength() : String{
//        return itemLength
//    }
//    fun setLength(itemLength: String){
//        this.itemLength = itemLength
//    }
//
//    fun getWidth() : String{
//        return itemWidth
//    }
//    fun setWidth(itemWidth: String){
//        this.itemWidth = itemWidth
//    }
//
//    fun getModel() : String{
//        return itemModel
//    }
//    fun setModel(itemModel: String){
//        this.itemModel = itemModel
//    }
//
//    fun getMaterial() : String{
//        return itemMaterial
//    }
//    fun setMaterial(itemMaterial: String){
//        this.itemMaterial = itemMaterial
//    }
//
//    fun getDescription() : String{
//        return itemDescription
//    }
//    fun setDescription(itemDescription: String){
//        this.itemDescription = itemDescription
//    }
//
//    fun getPrice() : String{
//        return itemPrice
//    }
//    fun setPrice(itemPrice: String){
//        this.itemPrice = itemPrice
//    }

}