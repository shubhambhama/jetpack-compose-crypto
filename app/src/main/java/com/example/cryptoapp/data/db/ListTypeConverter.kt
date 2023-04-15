package com.example.cryptoapp.data.db

import androidx.room.TypeConverter

class ListTypeConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun gettingFloatListFromString(floatList: String?) : List<Float>? {
            val list = arrayListOf<Float>()
            val array = floatList?.split(",".toRegex())?.dropLastWhile {
                it.isEmpty()
            }?.toTypedArray()

            if (array.isNullOrEmpty()) {
                return null
            }
            for (value in array) {
                if (value.isNotEmpty()) {
                    list.add(value.toFloat())
                }
            }
            return list
        }

        @TypeConverter
        @JvmStatic
        fun writingStringFromFloatList(list: List<Float>?) : String? {
            var ids = ""
            if (ids.isEmpty()) return null
            else {
                for (value in list ?: listOf()) {
                    ids += "$value"
                }
            }
            return ids
        }
    }
}