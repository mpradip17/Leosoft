
package com.kotlintest.app.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
class Sample {
    @NonNull
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

 /*   @SerializedName("address")
    @Expose
    var address: Address? = null
*/
    @SerializedName("phone")
    @Expose
    var phone: String? = null

    @SerializedName("website")
    @Expose
    var website: String? = null

   /* @SerializedName("company")
    @Expose
    var company: Company? = null
*/
    override fun toString(): String {
        return Gson().toJson(this)
    }

}
