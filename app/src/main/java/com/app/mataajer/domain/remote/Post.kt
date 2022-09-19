package com.app.mataajer.domain.remote


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 13/09/2022
 */
import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("result")
    val result: List<Result>,
    @SerializedName("totalCount")
    val totalCount: Int
) {
    data class Result(
        @SerializedName("categoryName")
        val categoryName: String?,
        @SerializedName("doctorName")
        val doctorName: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("isActive")
        var isActive: Boolean?,
        @SerializedName("medicalTestPackagesNo")
        val medicalTestPackagesNo: Int?,
        @SerializedName("noOfTests")
        val noOfTests: Int?,
        @SerializedName("packageId")
        val packageId: Int?,
        @SerializedName("orderDate")
        val orderDate: String?,
        @SerializedName("orderStatus")
        val orderStatus: Any?,
        @SerializedName("requestType")
        val requestType: String?,

        @SerializedName("lastRequestDate")
        val lastRequestDate: String?,
        @SerializedName("noOfOrders")
        val noOfOrders: Int?,
        @SerializedName("orderType")
        val orderType: String?,
        @SerializedName("patientName")
        val patientName: String?,
        @SerializedName("patientUserId")
        val patientUserId: Int?
    )
}
