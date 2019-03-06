package com.avfsoftware.pelinabeer.Models

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL

class HttpJsonParser{
    private fun makeRequest (url: URL) : String{
        with(url.openConnection() as HttpURLConnection){
            requestMethod = "GET"

            BufferedReader(InputStreamReader(inputStream) as Reader?).use {
                val response = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null){
                    inputLine = it.readLine()
                    response.append(inputLine)
                }
                return response.toString()
            }
        }
    }
}