package com.example.jsonapp

import com.google.gson.annotations.SerializedName

    class Datum {

        @SerializedName("date")
        var date: String? = null

        @SerializedName("eur")
        var eur: Currency? = null

        class Currency{

            @SerializedName("ada")
            var ada: String? = null

            @SerializedName("aed")
            var aed: String? = null

            @SerializedName("afn")
            var afn: String? = null

            @SerializedName("all")
            var all: String? = null

            @SerializedName("ang")
            var ang: String? = null

            @SerializedName("amd")
            var amd: String? = null

            @SerializedName("aoa")
            var aoa: String? = null

            @SerializedName("ars")
            var ars: String? = null

            @SerializedName("aud")
            var aud: String? = null

            @SerializedName("awg")
            var awg: String? = null

        }

    }

