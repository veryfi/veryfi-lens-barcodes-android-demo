package com.veryfi.lens.barcodes.demo.logs

import org.json.JSONObject

data class Log(
    var title: String = "",
    var message: JSONObject = JSONObject()
)