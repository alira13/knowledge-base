package com.example.collections.mutableCollection.myMap

import com.example.collections.mutableCollection.myCollection.MyCollection
import com.example.collections.mutableCollection.myCollection.MySet

interface MyMap<out K, out V> {

    val size: Int

    operator fun get(key: @UnsafeVariance K): V?

    fun containsKey(key: @UnsafeVariance K): Boolean

    fun containsValue(value: @UnsafeVariance V): Boolean

    val keys: MySet<K>

    val values: MyCollection<V>
}
