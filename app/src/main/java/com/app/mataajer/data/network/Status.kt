package com.app.mataajer.data.network

import androidx.annotation.IntDef

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */

@IntDef(LOADING, SUCCESS, ERROR, LOGOUT)
@Retention(AnnotationRetention.SOURCE)
annotation class Status

const val LOADING = 0
const val SUCCESS = 1
const val ERROR = 2
const val LOGOUT = 3