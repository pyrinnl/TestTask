package com.example.test.utils

/**
 * Базовый класс для маппера
 */
abstract class BaseMapper<T, R> : (T) -> R {

    final override fun invoke(it: T): R =
        try {
            map(it)
        } catch (npe: NullPointerException) {
            throw NullPointerException("Mapping error, please fix map function logic first")
        } catch (ise: IllegalStateException) {
            throw IllegalStateException( "Mapping error, please fix controller logic first")
        }

    protected abstract fun map(it: T): R
}